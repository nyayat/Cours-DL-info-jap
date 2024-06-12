/* This file is part of SyncContest.
   Copyright (C) 2017-2020 Eugene Asarin, Mihaela Sighireanu, Adrien Guatto. */

#ifndef SIMULATION_LOOP_H
#define SIMULATION_LOOP_H

#include <stdbool.h>
#include <stddef.h>

typedef enum {
  RACE_SUCCESS,
  RACE_CRASH,
  RACE_TIMEOUT
} race_result_t;

race_result_t simulation_loop(bool show_guide,
                              int initial_top,
                              float sps,
                              bool headless,
                              bool audio,
                              size_t max_synchronous_steps);

#endif  /* SIMULATION_LOOP_H */
