#ifndef VCD
#define VCD

#include <stdbool.h>
#include <stddef.h>

#include "hept_ffi.h"
#include "vcd_lib.h"

void hept_vcd_init(const char *filename,
                   size_t time_number,
                   vcd_time_unit_t unit);

DECLARE_HEPT_NODE(Vcd, trace_bool, (string, int),, vcd_signal_t *signal);
DECLARE_HEPT_NODE(Vcd, trace_int, (string, int),, vcd_signal_t *signal);
DECLARE_HEPT_NODE(Vcd, trace_float, (string, float),, vcd_signal_t *signal);

#endif  /* VCD */
