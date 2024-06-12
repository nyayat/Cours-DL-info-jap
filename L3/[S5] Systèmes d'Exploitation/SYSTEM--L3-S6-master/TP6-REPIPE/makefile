CFLAGS=-Wall
GENFILES=dispatch add sub mul div

.PHONY: all
all: $(GENFILES)

add: calc.c
	cc $(CFLAGS) -D OP="+" -o $@ $<

sub: calc.c
	cc $(CFLAGS) -D OP="-" -o $@ $<

mul: calc.c
	cc $(CFLAGS) -D OP="*" -o $@ $<

div: calc.c
	cc $(CFLAGS) -D OP="/" -o $@ $<

.PHONY: clean
clean:
	rm -f $(GENFILES)
