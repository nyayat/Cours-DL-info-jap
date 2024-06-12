#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <SDL2/SDL.h>
#include <sndfile.h>

#include "audio.h"
#include "vcd.h"

const size_t sample_rate = Audio__period;

void die(const char *message) {
  fprintf(stderr, message);
  exit(EXIT_FAILURE);
}

SNDFILE *file_out = NULL;

int main(int argc, char** argv)
{
  Audio__main_mem mem;
  Audio__main_out res;
  SDL_AudioSpec spec;
  SDL_AudioDeviceID dev;
  SF_INFO info_out;
  int opt = -1;
  bool quiet = false;
  size_t max_sec = SIZE_MAX;
  const char *filename = NULL;
  Uint32 buffered;

  while ((opt = getopt(argc, argv, "ho:qm:t:")) != -1) {
    switch (opt) {
    case 'h':
      printf("Usage: %s OPTIONS\n", argv[0]);
      printf("Options:\n");
      printf("  -o <file.wav>   write samples to <file.wav>\n");
      printf("  -q              do not play sound\n");
      printf("  -m <sec>        play for <sec> seconds\n");
      printf("  -t <file.vcd>   dump traces in <file.vcd>\n");
      printf("  -h              display this message\n");
      return 0;
    case 'q':
      quiet = true;
      break;
    case 'o':
      filename = optarg;
      break;
    case 'm':
      max_sec = atoi(optarg);
      break;
    case 't':
      hept_vcd_init(optarg, VCD_TIME_UNIT_US, 20);
      break;
    default:
      fprintf(stderr, "Unknown option '%c'\n", opt);
      exit(EXIT_FAILURE);
    }
  }

  if (SDL_Init(SDL_INIT_AUDIO) < 0)
    die("Could not initialize SDL2\n");

  bzero(&spec, sizeof spec);
  spec.freq = sample_rate;
  spec.format = AUDIO_F32;
  spec.channels = 2;
  spec.samples = 4096;
  spec.callback = NULL;

  if (!(dev = SDL_OpenAudioDevice(NULL, 0, &spec, NULL, 0)))
    die("Could not open audio device\n");

  info_out.channels = 2;
  info_out.samplerate = sample_rate;
  info_out.format = SF_FORMAT_WAV | SF_FORMAT_PCM_16;

  if (filename != NULL) {
    if (!(file_out = sf_open(filename, SFM_WRITE, &info_out))) {
      fprintf(stderr, "Could not open WAVE file %s for writing\n", argv[1]);
      SDL_Quit();
      exit(EXIT_FAILURE);
    }
  }

  Audio__main_reset(&mem);
  float *buffer = calloc(spec.samples, sizeof *buffer);
  SDL_PauseAudioDevice(dev, 0);
  for (size_t samples = 0;
       samples < max_sec * sample_rate;
       samples += spec.samples / 2) {

    printf("\rSent %08zu samples", samples);
    if (max_sec != SIZE_MAX) {
      printf(" (%2.0f%)", 100. * (double)samples / (max_sec * sample_rate));
    }
    fflush(stdout);

    for (size_t i = 0; i < spec.samples; i += 2) {
      Audio__main_step(&res, &mem);
      buffer[i+0] = res.o.l;
      buffer[i+1] = res.o.r;
    }
    if (!quiet)
      SDL_QueueAudio(dev, buffer, spec.samples * sizeof *buffer);
    sf_writef_float(file_out, buffer, spec.samples / 2);

    /* Throttle queued audio, otherwise we will certainly end up consuming all
       available memory. */
    buffered = SDL_GetQueuedAudioSize(dev);
    while (!quiet && buffered >= 1 << 22) {
      buffered = SDL_GetQueuedAudioSize(dev);
      SDL_Delay(50);
    }
  }
  printf("\n");

  /* Wait until the audio buffer is empty. */
  printf("Waiting for queue flush... "); fflush(stdout);
  while ((buffered = SDL_GetQueuedAudioSize(dev)) != 0)
    SDL_Delay(50);
  printf("done.\n");

  free(buffer);
  if (file_out)
    sf_close(file_out);
  SDL_Quit();

  return 0;
}
