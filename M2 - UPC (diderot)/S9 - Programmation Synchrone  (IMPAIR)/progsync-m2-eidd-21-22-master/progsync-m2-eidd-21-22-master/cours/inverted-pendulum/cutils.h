/* This file is part of SyncContest.
   Copyright (C) 2017-2020 Eugene Asarin, Mihaela Sighireanu, Adrien Guatto. */

#ifndef CUTILS_H
#define CUTILS_H

#include <stdarg.h>

typedef enum {
  LOG_FATAL = 0,
  LOG_INFO  = 1,
  LOG_DEBUG = 2,
} log_verbosity_level;

/* Calling `log_init(fn)` initializes the logging subsystem, asking it to save
   log messages to the file `fn`. This pointer may be NULL, in which case the
   messages are not saved. */
void log_init(const char *filename);
void log_shutdown();

void log_set_verbosity_level(log_verbosity_level level);

void log_message_v(log_verbosity_level level, const char *fmt, va_list);
void log_message(log_verbosity_level level, const char *fmt, ...);

void log_fatal(const char *fmt, ...);
void log_info(const char *fmt, ...);
void log_debug(const char *fmt, ...);

#endif  /* CUTILS_H */
