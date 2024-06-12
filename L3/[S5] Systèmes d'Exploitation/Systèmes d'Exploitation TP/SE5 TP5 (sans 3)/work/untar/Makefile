.PHONY: clean all

CC ?= gcc
CCFLAGS ?= -Wall -std=c99

all: untar

untar: untar.c tarutils.o

check: untar
	/bin/bash test.sh

clean:
	rm untar *.o
