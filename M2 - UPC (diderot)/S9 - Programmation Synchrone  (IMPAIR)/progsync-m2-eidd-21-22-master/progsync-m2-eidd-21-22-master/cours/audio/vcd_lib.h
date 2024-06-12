#ifndef VCD_LIB_H
#define VCD_LIB_H

#include <stdbool.h>
#include <stddef.h>

typedef enum vcd_signal_type {
  VCD_SIGNAL_TYPE_FLOAT,
  VCD_SIGNAL_TYPE_INT,
  VCD_SIGNAL_TYPE_BOOL
} vcd_signal_type_t;

size_t vcd_sizeof_signal_type(vcd_signal_type_t);

typedef struct vcd_signal vcd_signal_t;

vcd_signal_t *vcd_signal_alloc(const char *name,
                               vcd_signal_type_t type,
                               size_t initial_buffer_size);
void vcd_signal_free(vcd_signal_t *signal);

void vcd_add_samples(vcd_signal_t *signal, void *samples, size_t count);

typedef enum vcd_time_unit {
  VCD_TIME_UNIT_S,
  VCD_TIME_UNIT_MS,
  VCD_TIME_UNIT_US,
  VCD_TIME_UNIT_NS,
  VCD_TIME_UNIT_PS,
  VCD_TIME_UNIT_FS,
} vcd_time_unit_t;

const char *vcd_time_unit_repr(vcd_time_unit_t);

typedef struct vcd_file vcd_file_t;

vcd_file_t *vcd_file_alloc(const char *filename,
                           vcd_time_unit_t time_unit,
                           size_t time_unit_factor);
void vcd_file_free(vcd_file_t *);

vcd_signal_t *vcd_file_lookup_signal(const vcd_file_t *vcd, const char *name);
bool vcd_file_add_signal(const vcd_file_t *vcd, vcd_signal_t *signal);

bool vcd_file_write(vcd_file_t *);

#endif  /* VCD_LIB_H */
