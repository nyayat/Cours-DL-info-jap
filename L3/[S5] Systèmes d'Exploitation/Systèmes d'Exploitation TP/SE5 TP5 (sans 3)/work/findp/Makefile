.PHONY: clean all

CC ?= gcc
CCFLAGS ?= -g -Wall -std=c99

all: findp

findp: findp.c
	$(CC) $(CCFLAGS) -o findp findp.c

check: findp
	/bin/bash test.sh

clean:
	rm findp
