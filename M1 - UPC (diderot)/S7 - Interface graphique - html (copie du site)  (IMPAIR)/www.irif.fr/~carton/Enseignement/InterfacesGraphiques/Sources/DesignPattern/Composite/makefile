# -*- coding: utf-8 -*-
# Time-stamp: <makefile  26 nov 2020 22:13:49>

CPP = clang++
# CPP = g++
CFLAGS = -Wall 

main: shape.hpp disk.hpp rectangle.hpp composite.hpp main.cpp
	$(CPP) $(CFLAGS) -o main main.cpp	

composite.tgz: shape.hpp disk.hpp rectangle.hpp composite.hpp main.cpp makefile 
	tar zcvf composite.tgz $^

composite.zip: shape.hpp disk.hpp rectangle.hpp composite.hpp main.cpp makefile
	zip composite.zip $^

archives:
	make composite.tgz composite.zip

clean:
	rm -f *.o main composite.tgz composite.zip
