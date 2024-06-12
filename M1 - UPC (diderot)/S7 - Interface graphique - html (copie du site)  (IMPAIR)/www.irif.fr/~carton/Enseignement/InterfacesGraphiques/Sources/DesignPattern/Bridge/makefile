# -*- coding: utf-8 -*-
# Time-stamp: <makefile  26 nov 2020 22:02:59>

CPP = clang++
# CPP = g++
CFLAGS = -Wall 

main: container.hpp arraylist.hpp linkedlist.hpp queue.hpp fifoqueue.hpp lifoqueue.hpp main.cpp
	$(CPP) $(CFLAGS) -o main main.cpp	

bridge.tgz: container.hpp arraylist.hpp linkedlist.hpp queue.hpp fifoqueue.hpp lifoqueue.hpp main.cpp makefile 
	tar zcvf bridge.tgz $^

bridge.zip: container.hpp arraylist.hpp linkedlist.hpp queue.hpp fifoqueue.hpp lifoqueue.hpp main.cpp makefile
	zip bridge.zip $^

archives:
	make bridge.tgz bridge.zip

clean:
	rm -f *.o main bridge.tgz bridge.zip
