#include "vcd.h"

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#include "vcd_lib.h"

vcd_file_t *vcd = NULL;

bool enabled = false;

void hept_vcd_cleanup() {
  if (vcd) {
    printf("[vcd] saving trace\n");
    vcd_file_write(vcd);
    vcd_file_free(vcd);
  }
}

void hept_vcd_init(const char *filename,
                   size_t time_number,
                   vcd_time_unit_t unit) {
  if (vcd) {
    fprintf(stderr, "[vcd] initialization has already been performed\n");
  } else {
    vcd = vcd_file_alloc(filename, time_number, unit);
    assert (vcd);
    if (atexit(hept_vcd_cleanup)) {
      perror("[vcd] atexit() failed");
      exit(EXIT_FAILURE);
    }
    printf("[vcd] will save trace to %s\n", filename);
  }
}

static inline void trace_samples(vcd_signal_t **signal,
                          const char *name, vcd_signal_type_t type,
                          void *samples, size_t count) {
  if (!vcd)
    return;

  if (!*signal) {
    *signal = vcd_signal_alloc(name, type, 1 << 17);
    if (!vcd_file_add_signal(vcd, *signal)) {
        perror("vcd_file_add_signal()\n");
        exit(EXIT_FAILURE);
    }
  }
  vcd_add_samples(*signal, samples, count);
}

DEFINE_HEPT_NODE_RESET(Vcd, trace_bool) {
  mem->signal = NULL;
}

DEFINE_HEPT_NODE_STEP(Vcd, trace_bool, (string name, int v)) {
  trace_samples(&mem->signal, name, VCD_SIGNAL_TYPE_BOOL, &v, 1);
}

DEFINE_HEPT_NODE_RESET(Vcd, trace_int) {
  mem->signal = NULL;
}

DEFINE_HEPT_NODE_STEP(Vcd, trace_int, (string name, int v)) {
  trace_samples(&mem->signal, name, VCD_SIGNAL_TYPE_INT, &v, 1);
}

DEFINE_HEPT_NODE_RESET(Vcd, trace_float) {
  mem->signal = NULL;
}

DEFINE_HEPT_NODE_STEP(Vcd, trace_float, (string name, float v)) {
  trace_samples(&mem->signal, name, VCD_SIGNAL_TYPE_FLOAT, &v, 1);
}
