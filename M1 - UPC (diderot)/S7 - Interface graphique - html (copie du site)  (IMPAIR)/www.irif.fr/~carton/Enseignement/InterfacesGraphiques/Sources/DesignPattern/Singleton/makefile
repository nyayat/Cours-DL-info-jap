# -*- coding: utf-8 -*-
# Time-stamp: <makefile  26 nov 2020 22:10:24>

CPP = g++
CFLAGS = -Wall 


inherited: inherited.cpp
	$(CPP) $(CFLAGS) $<

composed: composed.cpp
	$(CPP) $(CFLAGS) $<

pointer: pointer.cpp
	$(CPP) $(CFLAGS) $<

singleton.tgz: inherited.cpp composed.cpp pointer.cpp makefile
	tar zcvf singleton.tgz $^

singleton.zip: inherited.cpp composed.cpp pointer.cpp makefile
	zip singleton.zip $^

archives:
	make singleton.tgz singleton.zip

clean:
	rm -f *.o a.out singleton.tgz singleton.zip
