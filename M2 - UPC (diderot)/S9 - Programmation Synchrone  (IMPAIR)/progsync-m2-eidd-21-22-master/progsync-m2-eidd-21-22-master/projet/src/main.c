/* This file is part of SyncContest.
   Copyright (C) 2017-2020 Eugene Asarin, Mihaela Sighireanu, Adrien Guatto. */

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "trace.h"
#include "cutils.h"
#include "map.h"
#include "simulation_loop.h"

void usage() {
  fprintf(stderr, "Usage: scontest [OPTIONS] file.map\n");
  fprintf(stderr, "Options:\n");
  fprintf(stderr, "  -v              Be verbose\n");
  fprintf(stderr, "  -g              Show graphics\n");
  fprintf(stderr, "  -t              Start racing immediately\n");
  fprintf(stderr, "  -s <fps>        Simulation step/s (default: 60)\n");
  fprintf(stderr, "  -o <file>       Save log messages to <file>\n");
  fprintf(stderr, "  -w              Run in headless mode\n");
  fprintf(stderr, "  -m <ticks>      Run at most <ticks> synchronous steps\n");
  fprintf(stderr, "  -a              Enable audio\n");
  fprintf(stderr, "  -e              Enable obstacles\n");
  fprintf(stderr, "  -h              Display this message\n");
}

int main(int argc, char **argv) {
  bool show_guide = true, headless = false, audio = false, obstacles = false;
  int initial_top = false, opt;
  char *log_filename = NULL;
  size_t max_synchronous_steps = 0;
  float sps = 60.f;

  hept_trace_init();

  /* Parse command line. */
  while ((opt = getopt(argc, argv, "vgtf:o:wm:hae")) != -1) {
    switch (opt) {
    case 'v':
      log_set_verbosity_level(LOG_DEBUG);
      break;

    case 'g':
      show_guide = false;
      break;

    case 't':
      initial_top = true;
      break;

    case 'f':
      sps = atof(optarg);
      break;

    case 'h':
      usage();
      return EXIT_SUCCESS;

    case 'o':
      log_filename = optarg;
      break;

    case 'w':
      headless = true;
      break;

    case 'm':
      max_synchronous_steps = atoi(optarg);
      break;

    case 'a':
      audio = true;
      break;

    case 'e':
      obstacles = true;
      break;

    default:
      usage();
      return EXIT_FAILURE;
    }
  }

  if (optind >= argc) {
    usage();
    return EXIT_FAILURE;
  }

  /* Initialize logging system. */
  log_init(log_filename);

  /* Load the map. */
  const char *filename = argv[optind];
  map_load(filename);

  /* Mark all obstacles as absent if they have been disabled. */
  if (!obstacles) {
    for (size_t i = 0; i < map->obst_sz; i++)
      map->obst_arr[i].pot_since = map->obst_arr[i].pot_till = -1.f;
  }

  /* Run the simulation loop. */
  race_result_t r =
    simulation_loop(show_guide,
                    initial_top,
                    sps,
                    headless,
                    audio,
                    max_synchronous_steps);

  switch (r) {
  case RACE_SUCCESS:
    log_info("[simulation] race succeeded\n");
    break;
  case RACE_TIMEOUT:
    log_info("[simulation] race quit before finishing\n");
    break;
  case RACE_CRASH:
    log_info("[simulation] car crashed\n");
    break;
  }

  /* Free the map, shutdown logs, and return. */
  map_destroy();
  log_shutdown();
  hept_trace_quit();

  /* Return the race result as an exit code. */
  return r;
}
