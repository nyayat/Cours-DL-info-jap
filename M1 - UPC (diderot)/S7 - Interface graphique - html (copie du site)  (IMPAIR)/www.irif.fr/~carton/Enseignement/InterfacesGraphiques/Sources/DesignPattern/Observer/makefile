# -*- coding: utf-8 -*-
# Time-stamp: <makefile  26 nov 2020 22:09:37>

CPP = clang++
#CPP = g++
CFLAGS = -Wall 

main: source.hpp observer.hpp target.hpp main.cpp
	$(CPP) $(CFLAGS) -o main main.cpp	

observer.tgz: source.hpp observer.hpp target.hpp main.cpp makefile
	tar zcvf observer.tgz $^

observer.zip: source.hpp observer.hpp target.hpp main.cpp makefile
	zip observer.zip $^

archives:
	make observer.tgz observer.zip

clean:
	rm -f *.o *.gch main observer.tgz observer.zip
