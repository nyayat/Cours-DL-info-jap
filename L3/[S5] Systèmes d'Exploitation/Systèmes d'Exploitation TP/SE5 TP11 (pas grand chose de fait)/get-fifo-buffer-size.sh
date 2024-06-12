#!/bin/bash

DIR=$(mktemp -d)

cd "$DIR"

mkfifo FIFO

cat > a.c << EOF
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <poll.h>
#include <stdio.h>
#include <unistd.h>
#include <errno.h>

#define SIZE 1048576
#define FIFO "FIFO"

int main() {
  int fdr = open(FIFO, O_RDONLY | O_NONBLOCK);
  if (fdr < 0) goto error;
  int fdw = open(FIFO, O_WRONLY | O_NONBLOCK);
  if (fdw < 0) goto error;

  char * zeros = malloc(SIZE);
  if (zeros == NULL) goto error;
  memset(zeros, 0, SIZE);

  ssize_t nwritten = write(fdw, zeros, SIZE);
  if (nwritten < 0) goto error;

  printf("%zd\n", nwritten);

  exit(0);

 error:
  perror("");
  exit(1);
}
EOF

gcc -o a a.c &&
./a

cd /
rm -rf "$DIR"
