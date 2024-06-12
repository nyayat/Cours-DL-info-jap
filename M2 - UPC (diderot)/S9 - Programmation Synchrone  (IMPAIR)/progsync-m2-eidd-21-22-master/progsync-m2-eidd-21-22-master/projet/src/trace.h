#ifndef TRACE_H
#define TRACE_H

#include <stdbool.h>
#include <stddef.h>

#include "hept_ffi.h"
#include "trace_lib.h"

void hept_trace_init();
void hept_trace_quit();

DECLARE_HEPT_NODE(Trace, trace_bool, (string, int),, trace_signal_t *signal);
DECLARE_HEPT_NODE(Trace, trace_int, (string, int),, trace_signal_t *signal);
DECLARE_HEPT_NODE(Trace, trace_float, (string, float),, trace_signal_t *signal);

#endif  /* TRACE */
