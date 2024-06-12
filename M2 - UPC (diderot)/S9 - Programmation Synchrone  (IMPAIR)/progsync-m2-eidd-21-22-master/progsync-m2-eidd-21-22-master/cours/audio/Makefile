HEPTC=heptc
HEPTLIB=$(shell heptc -where)
CC=gcc
CFLAGS=-g $(shell pkg-config --cflags --libs sdl2 sndfile) -I$(HEPTLIB)/c -lm
PYGMENTS=python -m pygments -x -l ../../../notes/heptagon.py:HeptagonLexer

TARGET=audio
SOURCES=audio_c/audio_types.c \
	audio_c/audio.c \
	buffer.c \
	vcd_lib.c \
	mathext.c \
	vcd.c \
	main.c

.PHONY: all clean test

all: $(TARGET)

%.html: %.ept
	$(PYGMENTS) -O full -o $@ $^

clean:
	rm -f $(TARGET) *.{epci,log,mls,obc}
	rm -rf audio_c

test: $(TARGET)
	./$(TARGET)

$(TARGET): $(SOURCES)
	@pkg-config --exists sdl2 || \
		( echo "La bibliothèque SDL2 est absente."; exit 1 )
	@pkg-config --exists sndfile || \
		( echo "La bibliothèque sndfile est absente."; exit 1 )
	$(CC) $(CFLAGS) -o $@ -I audio_c -I. $^

audio_c/audio_types.c audio_c/audio.c: audio.ept main.c mathext.epci vcd.epci
	$(HEPTC) -target c $<

%.epci: %.epi
	$(HEPTC) $<
