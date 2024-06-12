/* This file is part of SyncContest.
   Copyright (C) 2017-2020 Eugene Asarin, Mihaela Sighireanu, Adrien Guatto. */

#include "simulation_loop.h"

#include <SDL.h>

#include "mymath.h"
#include "challenge.h"
#include "cutils.h"
#include "map.h"

#ifndef ASSET_DIR_PATH
#define ASSET_DIR_PATH "./assets"
#endif

void find_absolute_asset_path(char *path, size_t path_size,
                              const char *filename) {
  snprintf(path, path_size, "%s/%s", ASSET_DIR_PATH, filename);
}

void load_asset_wav(const char *filename, asset_wav_t *wav) {
  assert (filename);
  assert (wav);

  char filepath[512];

  find_absolute_asset_path(filepath, sizeof filepath, filename);
  if (!SDL_LoadWAV(filepath, &wav->spec, &wav->buffer, &wav->size))
    log_fatal("[sdl] could not open WAV file %s (%s)\n",
              filepath, SDL_GetError());
}

void free_asset_wav(asset_wav_t *wav) {
  assert (wav);
  SDL_FreeWAV(wav->buffer);
  wav->buffer = NULL;
}

SDL_Surface *load_asset_bmp_surface(const char *filename) {
  SDL_Surface *r;
  char filepath[512];

  find_absolute_asset_path(filepath, sizeof filepath, filename);

  if ((r = SDL_LoadBMP(filepath)) == NULL)
    log_fatal("[sdl] could not load %s\n", filepath);

  return r;
}

SDL_Texture *load_asset_bmp_texture(const char *filename, SDL_Renderer *r,
                                    int *texture_width, int *texture_height) {
  SDL_Surface *s = load_asset_bmp_surface(filename);
  SDL_Texture *t = SDL_CreateTextureFromSurface(r, s);

  if (!t)
    log_fatal("[sdl] could not load texture from surface %s (%s)\n",
              filename, SDL_GetError());
  SDL_FreeSurface(s);

  if (texture_width && texture_height
      && SDL_QueryTexture(t, NULL, NULL, texture_width, texture_height))
    log_fatal("[sdl] could not query texture %s (%s)\n",
              filename, SDL_GetError());

  log_info("[sdl] loaded texture %s\n", filename);
  return t;
}

/* Our simulation assumes the origin is on the bottom-left corner of the window,
   unlike SDL which assumes it is on the top-left corner. The function below
   transforms a point from geometric space into SDL space. */
void sdl_space_of_position(Globals__position *position, int *x, int *y) {
  *x = position->x;
  *y = MAX_Y - position->y;
}

void sdl_point_of_position(Globals__position *position, SDL_Point *point) {
  sdl_space_of_position(position, &point->x, &point->y);
}

int draw_point(SDL_Renderer *rd, Globals__position *p) {
  int x, y;
  sdl_space_of_position(p, &x, &y);
  return SDL_RenderDrawPoint(rd, x, y);
}

int draw_line(SDL_Renderer *rd,
              Globals__position *startp, Globals__position *endp) {
  int x1, y1, x2, y2;
  sdl_space_of_position(startp, &x1, &y1);
  sdl_space_of_position(endp, &x2, &y2);
  return SDL_RenderDrawLine(rd, x1, y1, x2, y2);
}

void draw_rectangle(SDL_Renderer *rd, Globals__position *center, size_t l,
                    uint32_t r, uint32_t g, uint32_t b) {
  SDL_Rect rect = (SDL_Rect){ 0, 0, l, l };
  /* Compute coordinates. */
  sdl_space_of_position(center, &rect.x, &rect.y);
  rect.x -= l / 2;
  rect.y -= l / 2;
  /* Render rectangle. */
  SDL_SetRenderDrawColor(rd, r, g, b, 0x00);
  if (SDL_RenderFillRect(rd, &rect) < 0)
    log_fatal("[sdl] could not draw rectangle (%s)\n", SDL_GetError());
  SDL_SetRenderDrawColor(rd, 0xFF, 0xFF, 0xFF, 0);
  if (draw_point(rd, center) < 0)
    log_fatal("[sdl] could not draw point (%s)\n", SDL_GetError());
}

void draw_tile(SDL_Renderer *rd,
               SDL_Texture *texture,
               Globals__position *p,
               double angle,
               int w,
               int h) {
  SDL_Rect dst_rect = { 0, 0, w, h };
  Globals__position center = { p->x - w / 2, p->y + h / 2 };
  sdl_space_of_position(&center, &dst_rect.x, &dst_rect.y);

  SDL_RenderCopyEx(rd,             /* renderer */
                   texture,        /* texture */
                   NULL,           /* entire texture */
                   &dst_rect,      /* destination */
                   angle,          /* angle (cw) */
                   NULL,           /* center dst_rec */
                   SDL_FLIP_NONE); /* no flipping */
}

race_result_t simulation_loop(bool show_guide,
                              int initial_top,
                              float sps,
                              bool headless,
                              bool audio,
                              size_t max_synchronous_steps) {
  SDL_Window *w;
  bool quit = false;                /* Shall we quit? */
  race_result_t res = RACE_TIMEOUT; /* Did we complete the race? */
  int top = initial_top;            /* Has the race started? */
  bool verbose = false, debug = false;
  int car_w, car_h, obs_w, obs_h;
  SDL_Texture *bg, *car, *obs;
  SDL_Renderer *r;
  size_t current_tick = 0;

  /* Initialize SDL and acquire resources. */

  if (!headless) {
    if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_AUDIO) < 0)
      log_fatal("[sdl] could not initialize SDL library (%s)\n",
                SDL_GetError());

    if ((w = SDL_CreateWindow("Synchronous Contest " YEAR " v" VERSION,
                              SDL_WINDOWPOS_UNDEFINED,
                              SDL_WINDOWPOS_UNDEFINED,
                              MAX_X,
                              MAX_Y,
                              SDL_WINDOW_SHOWN)) == NULL)
      log_fatal("[sdl] could not open window (%s)\n", SDL_GetError());

    r = SDL_CreateRenderer(w, -1, SDL_RENDERER_ACCELERATED);
    if (!r)
      log_fatal("[sdl] could not create renderer (%s)\n", SDL_GetError());

    if (!r)
      log_fatal("[sdl] could not create renderer (%s)\n", SDL_GetError());

    bg = load_asset_bmp_texture(show_guide ? map->guide : map->graphics,
                                r,
                                NULL,
                                NULL);
    car = load_asset_bmp_texture("orange.bmp",
                                 r,
                                 &car_w,
                                 &car_h);
    obs = load_asset_bmp_texture("obst.bmp",
                                 r,
                                 &obs_w,
                                 &obs_h);

    /* Load sounds and setup audio device. */

    load_asset_wav("collision.wav", &collision);
    load_asset_wav("direction.wav", &wrong_dir);
    load_asset_wav("exit.wav", &exit_road);
    load_asset_wav("light.wav", &light_run);
    load_asset_wav("speed.wav", &speed_excess);

    if (audio) {
      if (!(audio_device =
            SDL_OpenAudioDevice(NULL, 0, &collision.spec, NULL, 0))) {
        log_info("[sdl] could not open audio device\n");
      } else
        SDL_PauseAudioDevice(audio_device, 0);
    }
  }

  /* Initialize synchronous state. */

  Challenge__the_challenge_out out;
  Challenge__the_challenge_mem mem;
  Challenge__the_challenge_reset(&mem);

  /* Setup time counters. */

  const uint32_t sync_dt_ms = 1000.f * Globals__timestep;
  const uint32_t simulation_dt_ms = (1. / (double)sps) * 1000.;
  uint32_t time_budget_ms = sync_dt_ms; /* Enough to do one initial step. */

  log_info("[simulation] starting (%zu ms/cycle)\n", simulation_dt_ms);

  while (!quit
         && (!max_synchronous_steps || current_tick < max_synchronous_steps)) {
    SDL_Event e;
    uint32_t start_time_ms = SDL_GetTicks();

    /* Perform as many synchronous steps as possible within our budget. */
    while (time_budget_ms >= sync_dt_ms
           && (!max_synchronous_steps
               || current_tick < max_synchronous_steps)) {
      time_budget_ms -= sync_dt_ms;
      Challenge__the_challenge_step(map->init_phase, top, &out, &mem);
      current_tick++;
      /* Check robot status once simulation has started. */
      if (top) {
        switch (out.sta) {
        case Globals__Preparing:
        case Globals__Running:
          break;
        case Globals__Arrived:
          log_info("[simulation %08zu] race finished\n", current_tick);
          res = RACE_SUCCESS;
          quit = true;
          break;
        case Globals__Stopped:
          log_info("[simulation %08zu] car stopped\n", current_tick);
          res = RACE_CRASH;
          quit = true;
          break;
        }
      }
      if (!debug && !verbose && !quit) {
        printf("\e[?25l");  /* Disable cursor */
        printf("H %06.2f\tV %06.2f\tT %06.2f\tS %09d\r",
               out.ph.ph_head, out.ph.ph_vel, out.time, out.scoreA);
        printf("\e[?25h");  /* Re-enable cursor */
      }
    }

    if (!headless) {
      /* Process events, including key presses. */
      while (SDL_PollEvent(&e) != 0) {
        switch (e.type) {
        case SDL_QUIT:
          quit = true;
          break;
        case SDL_KEYDOWN:
          switch (e.key.keysym.sym) {
          case SDLK_q:
            quit = true;
            break;
          case SDLK_t:
            top = true;
            break;
          case SDLK_d:
            debug = !debug;
            break;
          case SDLK_v:
            if (verbose)
              log_set_verbosity_level(LOG_INFO);
            else
              log_set_verbosity_level(LOG_DEBUG);
            verbose = !verbose;
            break;
          case SDLK_UP:
            map->init_phase.ph_head += 2;
            break;
          case SDLK_DOWN:
            map->init_phase.ph_head -= 2;
            break;
          }
          break;
        }
      }

      /* Render the scene, which includes the background as well as the car. */
      SDL_SetRenderDrawColor(r, 0xFF, 0xFF, 0xFF, 0xFF);

      SDL_RenderClear(r);
      SDL_RenderCopy(r, bg, NULL, NULL);

      if (!debug)
        draw_tile(r, car, &out.ph.ph_pos, 360.f - out.ph.ph_head, car_w, car_h);
      else {
        /* In debug mode, render the car as a plain square. */
        Globals__phase *ph; Globals__position endp; float f = 20.0;
        if (top) {
          ph = &out.ph;
          f *= ph->ph_vel / SPEED_MAX;
        } else
          ph = &map->init_phase;
        draw_rectangle(r, &ph->ph_pos, 5, 0x00, 0x00, 0x00);
        /* Draw direction vector. */
        endp.x = ph->ph_pos.x + f * cos(ph->ph_head / 360. * 2. * M_PI);
        endp.y = ph->ph_pos.y + f * sin(ph->ph_head / 360. * 2. * M_PI);
        SDL_SetRenderDrawColor(r, 0x00, 0x00, 0x00, 0x00);
        draw_line(r, &ph->ph_pos, &endp);
        /* Draw detected map vector. */
        Map__lookup_pos_out out;
        Map__lookup_pos_step(ph->ph_pos, &out);
        endp.x = ph->ph_pos.x + out.data.dir_x;
        endp.y = ph->ph_pos.y + out.data.dir_y;
        SDL_SetRenderDrawColor(r, 0xAE, 0xB4, 0xC0, 0x00);
        draw_line(r, &ph->ph_pos, &endp);
        /* Reset color. */
        SDL_SetRenderDrawColor(r, 0xFF, 0xFF, 0xFF, 0);
      }

      /* We draw the signalization info, when relevant. */
      if (!debug) {
        for (size_t i = 0; i < MAX_OBST_COUNT; i++) {
          Globals__obstacle *o = &out.sign.si_obstacles[i];
          if (o->o_pres)
            draw_tile(r, obs, &o->o_pos, 0.f, obs_w, obs_h);
        }

        for (size_t i = 0; i < MAX_TL_COUNT; i++) {
          Utilities__encode_color_out enc;
          Globals__traflight *t = &out.sign.si_tlights[i];
          Utilities__encode_color_step(t->tl_color, &enc);
          draw_rectangle(r, &t->tl_pos, 10, enc.a.red, enc.a.green, enc.a.blue);
        }
      }

      /* In debug mode, we also render the raw information coming from the
         map. This is useful to understand the map file contents. */
      if (debug) {
        SDL_SetRenderDrawColor(r, 0x00, 0x00, 0xFF, 0xFF);
        for (size_t i = 0; i < map->road_sz; i++) {
          switch (map->road_arr[i].kind) {
          case RD_LINE_1:
            draw_line(r,
                      &map->road_arr[i].u.line.startp,
                      &map->road_arr[i].u.line.endp);
            break;
          default:
            /* TODO draw curved roads. */
            break;
          }
        }

        /* Draw waypoints. */
        for (size_t i = 0; i < map->wayp_sz; i++)
          draw_rectangle(r, &map->wayp_arr[i].position,
                         10, 0xFF, 0x00, 0x00);

        /* Draw traffic lights. */
        for (size_t i = 0; i < map->tlight_sz; i++)
          draw_rectangle(r, &map->tlight_arr[i].tl.ptl_pos,
                         10, 0x00, 0x00, 0xFF);

        /* Draw stops. */
        for (size_t i = 0; i < map->stop_sz; i++)
          draw_rectangle(r, &map->stop_arr[i].position,
                         10, 0x84, 0x21, 0xFF);

        /* Draw obstacles. */
        for (size_t i = 0; i < map->obst_sz; i++)
          draw_rectangle(r, &map->obst_arr[i].pot_pos,
                         10, 0x12, 0xAE, 0x00);
      }

      SDL_RenderPresent(r);
    }

    /* Sleep for our remaining per-simulation time. */
    uint32_t stop_time_ms = SDL_GetTicks();
    uint32_t frame_time_ms = stop_time_ms - start_time_ms;
    if (frame_time_ms < simulation_dt_ms) {
      log_debug("[simulation %08zu] %zu elapsed, sleeping for %zu ms\n",
                current_tick, frame_time_ms, simulation_dt_ms - frame_time_ms);
      SDL_Delay(simulation_dt_ms - frame_time_ms);
    }

    /* Accumulate time for the synchronous step. */
    time_budget_ms += fmax(simulation_dt_ms, frame_time_ms);
  }
  log_info("[simulation %08zu] shutting down, score = %zu, time = %f\n",
           current_tick, out.scoreA, out.time);

  /* Wait for audio queue to be empty. */
  if (audio_device) {
    Uint32 audio_buffered;
    while ((audio_buffered = SDL_GetQueuedAudioSize(audio_device)) != 0)
      SDL_Delay(50);
  }

  free_asset_wav(&collision);
  free_asset_wav(&wrong_dir);
  free_asset_wav(&exit_road);
  free_asset_wav(&light_run);
  free_asset_wav(&speed_excess);

  SDL_DestroyTexture(obs);
  SDL_DestroyTexture(car);
  SDL_DestroyTexture(bg);
  SDL_DestroyRenderer(r);
  SDL_DestroyWindow(w);
  SDL_Quit();

  return res;
}
