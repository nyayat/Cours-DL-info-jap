# -*- coding: utf-8 -*-
# Time-stamp: <makefile  26 nov 2020 22:15:32>

CPP = clang++
#CPP = g++
CFLAGS = -Wall 

main: handler.hpp global.hpp handlermod.hpp factory.o main.cpp
	$(CPP) $(CFLAGS) -o main factory.o main.cpp	

factory.o: handler.hpp global.hpp handlermod.hpp factory.hpp factory.cpp
	$(CPP) $(CFLAGS) -c factory.cpp

responsability.tgz: handler.hpp global.hpp handlermod.hpp factory.hpp factory.cpp main.cpp makefile 
	tar zcvf responsability.tgz $^

responsability.zip: handler.hpp global.hpp handlermod.hpp factory.hpp factory.cpp main.cpp makefile
	zip responsability.zip $^

archives:
	make responsability.tgz responsability.zip

clean:
	rm -f *.o main responsability.tgz responsability.zip
