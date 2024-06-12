# -*- coding: utf-8 -*-
# Time-stamp: <makefile  30 nov 2020 10:12:54>

CPP = clang++
#CPP = g++
CFLAGS = -Wall 

main: tree.hpp visitor.hpp void.hpp copy.hpp iterator.hpp builder.hpp main.cpp
	$(CPP) $(CFLAGS) -o main main.cpp	

visitor.tgz: tree.hpp visitor.hpp void.hpp copy.hpp iterator.hpp builder.hpp main.cpp makefile 
	tar zcvf visitor.tgz $^

visitor.zip: tree.hpp visitor.hpp void.hpp copy.hpp iterator.hpp builder.hpp  main.cpp makefile
	zip visitor.zip $^

archives:
	make visitor.tgz visitor.zip

clean:
	rm -f *.o main visitor.tgz visitor.zip
