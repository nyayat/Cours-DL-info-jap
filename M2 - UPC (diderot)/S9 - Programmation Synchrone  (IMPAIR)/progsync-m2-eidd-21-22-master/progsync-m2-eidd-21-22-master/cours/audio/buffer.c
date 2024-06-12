#include "buffer.h"

#include <assert.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define max(a, b) ((a) <= (b) ? (a) : (b))

void *malloc_checked(size_t size) {
  void *result = malloc(size);
  if (!result) {
    perror("malloc()");
    exit(EXIT_FAILURE);
  }
  return result;
}

char *strdup_checked(const char *s) {
  char *result = strdup(s);
  if (!result) {
    perror("strdup()");
    exit(EXIT_FAILURE);
  }
  return result;
}

buffer_t *buffer_alloc(size_t initial_size) {
  buffer_t *buff = malloc_checked(sizeof *buff);
  buff->data = malloc_checked(initial_size * sizeof *buff->data);
  buff->size = initial_size;
  buff->occupancy = 0;
  return buff;
}

void buffer_free(buffer_t *buffer) {
  assert (buffer);

  free(buffer->data);
  free(buffer);
}

void buffer_resize(buffer_t *buff, size_t new_size) {
  assert (buff);
  assert (new_size >= buff->size);

  unsigned char *new_data = malloc_checked(new_size);
  memcpy(new_data, buff->data, buff->occupancy);
  free(buff->data);
  buff->data = new_data;
  buff->size = new_size;
}

void buffer_write(buffer_t *buff, void *data, size_t data_size) {
  assert (buff);
  if (buff->occupancy + data_size > buff->size)
    buffer_resize(buff, max(buff->size + data_size, 2 * buff->size));
  memcpy(buff->data + buff->occupancy, data, data_size);
  buff->occupancy += data_size;
}
