/* This file is part of SyncContest.
   Copyright (C) 2017-2020 Eugene Asarin, Mihaela Sighireanu, Adrien Guatto. */

#include "cutils.h"

#include <assert.h>
#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

log_verbosity_level level = LOG_INFO;
FILE *f = NULL;
char *filename = NULL;

void log_set_verbosity_level(log_verbosity_level l) {
  level = l;
}

void log_message_v(log_verbosity_level msg_level, const char *fmt, va_list va) {
  va_list vb;
  FILE *out = msg_level == LOG_INFO ? stdout : stderr;

  if (msg_level > level)
    return;

  va_copy(vb, va);
  if (f != NULL) {
    vfprintf(f, fmt, va);
  }

  vfprintf(out, fmt, vb);
  fflush(out);
}

void log_message(log_verbosity_level msg_level, const char *fmt, ...) {
  va_list va;
  va_start(va, fmt);
  log_message_v(msg_level, fmt, va);
  va_end(va);
}

void log_fatal(const char *fmt, ...) {
  va_list va;
  va_start(va, fmt);
  log_message_v(LOG_FATAL, fmt, va);
  va_end(va);
  exit(EXIT_FAILURE);
}

void log_info(const char *fmt, ...) {
  va_list va;
  va_start(va, fmt);
  log_message_v(LOG_INFO, fmt, va);
  va_end(va);
}

void log_debug(const char *fmt, ...) {
  va_list va;
  va_start(va, fmt);
  log_message_v(LOG_DEBUG, fmt, va);
  va_end(va);
}

void log_init(const char *fn) {
  if (!fn) {
    log_info("[log] initializing, not saving to file\n");
    return;
  }

  filename = strdup(fn);
  assert (filename);
  f = fopen(filename, "w");
  if (!f)
    log_fatal("[log] could not open log file %s (fopen)", filename);

  log_info("[log] logging to %s\n", filename);
}

void log_shutdown() {
  if (f) {
    log_info("[log] shutting down, closing %s\n", filename);
    fclose(f);
    free(filename);
  } else {
    log_info("[log] shutting down\n");
  }
}
