CC      ?= gcc
CFLAGS  ?= -Wall 

.PHONY: clean

all : mppsh copie

copie : copie.c mppsh-exec.o mppsh-exec.h
	$(CC) $(CFLAGS)  copie.c mppsh-exec.o -o $@
	cp copie concatene

mppsh : mppsh-main.o mppsh-exec.o mppsh-parsing.o mppsh-exec.h mppsh-parsing.h
	$(CC) $(CFLAGS) mppsh-main.o mppsh-exec.o mppsh-parsing.o -o $@ 

clean : 
	rm *o copie concatene mppsh
