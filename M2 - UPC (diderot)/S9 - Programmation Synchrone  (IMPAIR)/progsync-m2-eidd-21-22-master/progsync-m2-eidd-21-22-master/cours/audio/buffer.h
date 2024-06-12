#ifndef BUFFER_H
#define BUFFER_H

#include <sys/types.h>

/* A simple type of append-only buffers. */

typedef struct buffer {
  unsigned char *data;
  size_t size;
  size_t occupancy;
} buffer_t;

void *malloc_checked(size_t size);
char *strdup_checked(const char *);

buffer_t *buffer_alloc(size_t initial_size);
void buffer_free(buffer_t *buff);

void buffer_write(buffer_t *buff, void *data, size_t data_size);

#define buffer_foreach(ty, var, buffer)                          \
  for (ty *var = (ty *)buffer->data;                             \
       var < (ty *)(buffer->data + buffer->occupancy);           \
       var++)

#endif  /* BUFFER_H */
