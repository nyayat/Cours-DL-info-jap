.PHONY: all clean

all:
	dune build marthe.exe
	ln -sf _build/default/marthe.exe marthe

clean:
	dune clean
	rm -fr *~ marthe
