#ifndef TRACE_LIB_H
#define TRACE_LIB_H

#include <stdbool.h>
#include <stddef.h>

typedef enum trace_signal_type {
  TRACE_SIGNAL_TYPE_FLOAT,
  TRACE_SIGNAL_TYPE_INT,
  TRACE_SIGNAL_TYPE_BOOL
} trace_signal_type_t;

size_t trace_sizeof_signal_type(trace_signal_type_t);

typedef struct trace_signal trace_signal_t;

trace_signal_t *trace_signal_alloc(const char *name,
                                   trace_signal_type_t type,
                                   size_t initial_buffer_size);
void trace_signal_free(trace_signal_t *signal);

void trace_add_samples(trace_signal_t *signal, void *samples, size_t count);

typedef enum trace_time_unit {
  TRACE_TIME_UNIT_S,
  TRACE_TIME_UNIT_MS,
  TRACE_TIME_UNIT_US,
  TRACE_TIME_UNIT_NS,
  TRACE_TIME_UNIT_PS,
  TRACE_TIME_UNIT_FS,
} trace_time_unit_t;

const char *trace_time_unit_repr(trace_time_unit_t);

typedef struct trace_file trace_file_t;

trace_file_t *trace_file_alloc(trace_time_unit_t time_unit,
                               size_t time_unit_factor);
void trace_file_free(trace_file_t *);

trace_signal_t *trace_file_lookup_signal(const trace_file_t *trace,
                                         const char *signal_name);
bool trace_file_add_signal(const trace_file_t *trace, trace_signal_t *signal);

bool trace_file_write(trace_file_t *, const char *file_name);

#endif  /* TRACE_LIB_H */
