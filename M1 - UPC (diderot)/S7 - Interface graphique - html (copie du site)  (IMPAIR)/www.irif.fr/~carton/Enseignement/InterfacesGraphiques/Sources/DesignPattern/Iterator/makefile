# -*- coding: utf-8 -*-
# Time-stamp: <makefile  26 nov 2020 23:24:10>

CPP = g++
CFLAGS = -Wall 

range: range.cpp
	$(CPP) $(CFLAGS) $<

date.o: date.hpp date.cpp
	$(CPP) $(CFLAGS) -c date.cpp

generic: date.o generic.cpp
	$(CPP) $(CFLAGS) date.o generic.cpp

iterator.tgz: range.cpp date.hpp date.cpp generic.cpp makefile
	tar zcvf iterator.tgz $^

iterator.zip: range.cpp date.hpp date.cpp generic.cpp makefile
	zip iterator.zip $^

archives:
	make iterator.tgz iterator.zip

clean:
	rm -f *.o a.out iterator.tgz iterator.zip

