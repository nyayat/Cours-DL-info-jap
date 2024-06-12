#include "trace.h"

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "trace_lib.h"

trace_file_t *trace = NULL;

#define HEPT_TRACE_ENV_VAR "HEPT_TRACE"

void hept_trace_init() {
  if (trace || !getenv(HEPT_TRACE_ENV_VAR))
    return;
  trace = trace_file_alloc(TRACE_TIME_UNIT_S, 1);
}

void hept_trace_quit() {
  if (trace) {
    trace_file_write(trace, getenv(HEPT_TRACE_ENV_VAR));
    trace_file_free(trace);
    trace = NULL;
  }
}

static inline void trace_samples(trace_signal_t **signal,
                          const char *name, trace_signal_type_t type,
                          void *samples, size_t count) {
  if (!trace)
    return;

  if (!*signal) {
    *signal = trace_file_lookup_signal(trace, name);
    if (!*signal) {
      *signal = trace_signal_alloc(name, type, 1 << 17);
      if (!trace_file_add_signal(trace, *signal)) {
        perror("trace_file_add_signal()\n");
        exit(EXIT_FAILURE);
      }
    }
  }
  assert (*signal);
  trace_add_samples(*signal, samples, count);
}

DEFINE_HEPT_NODE_RESET(Trace, trace_bool) {
  mem->signal = NULL;
}

DEFINE_HEPT_NODE_STEP(Trace, trace_bool, (string name, int v)) {
  trace_samples(&mem->signal, name, TRACE_SIGNAL_TYPE_BOOL, &v, 1);
}

DEFINE_HEPT_NODE_RESET(Trace, trace_int) {
  mem->signal = NULL;
}

DEFINE_HEPT_NODE_STEP(Trace, trace_int, (string name, int v)) {
  trace_samples(&mem->signal, name, TRACE_SIGNAL_TYPE_INT, &v, 1);
}

DEFINE_HEPT_NODE_RESET(Trace, trace_float) {
  mem->signal = NULL;
}

DEFINE_HEPT_NODE_STEP(Trace, trace_float, (string name, float v)) {
  trace_samples(&mem->signal, name, TRACE_SIGNAL_TYPE_FLOAT, &v, 1);
}
