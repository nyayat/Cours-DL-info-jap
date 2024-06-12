# -*- coding: utf-8 -*-
# Time-stamp: <makefile  26 nov 2020 22:14:45>

#CPP = clang++
 CPP = g++
CFLAGS = -Wall 

main: interface.hpp base.hpp decorator.hpp decoratorA.hpp decoratorB.hpp decoratorC.hpp factory.o main.cpp
	$(CPP) $(CFLAGS) -o main factory.o main.cpp	

factory.o: interface.hpp base.hpp decorator.hpp decoratorA.hpp decoratorB.hpp decoratorC.hpp factory.hpp factory.cpp
	$(CPP) $(CFLAGS) -c factory.cpp

decorator.tgz: interface.hpp base.hpp decorator.hpp decoratorA.hpp decoratorB.hpp decoratorC.hpp factory.hpp factory.cpp main.cpp makefile 
	tar zcvf decorator.tgz $^

decorator.zip: interface.hpp base.hpp decorator.hpp decoratorA.hpp decoratorB.hpp decoratorC.hpp factory.hpp factory.cpp main.cpp makefile
	zip decorator.zip $^

archives:
	make decorator.tgz decorator.zip

clean:
	rm -f *.o main decorator.tgz decorator.zip
