# -*- coding: utf-8 -*-
# Time-stamp: <makefile  26 nov 2020 22:16:16>

CPP = clang++
# CPP = g++
CFLAGS = -Wall 

main: sorter.hpp selection.hpp insertion.hpp quicksort.hpp main.cpp
	$(CPP) $(CFLAGS) -o main main.cpp	

strategy.tgz: sorter.hpp selection.hpp insertion.hpp quicksort.hpp main.cpp makefile 
	tar zcvf strategy.tgz $^

strategy.zip: sorter.hpp selection.hpp insertion.hpp quicksort.hpp main.cpp makefile
	zip strategy.zip $^

archives:
	make strategy.tgz strategy.zip

clean:
	rm -f *.o main strategy.tgz strategy.zip
