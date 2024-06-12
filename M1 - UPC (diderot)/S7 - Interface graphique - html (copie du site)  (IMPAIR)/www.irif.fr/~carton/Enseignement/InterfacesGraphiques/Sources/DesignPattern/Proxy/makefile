# -*- coding: utf-8 -*-
# Time-stamp: <makefile  26 nov 2021 10:14:24>

#CPP = clang++
CPP = g++
CFLAGS = -Wall 

main: proxy.hpp main.cpp
	$(CPP) $(CFLAGS) -o main main.cpp	

proxy.tgz: proxy.hpp main.cpp makefile 
	tar zcvf proxy.tgz $^

proxy.zip: proxy.hpp main.cpp makefile
	zip proxy.zip $^

archives:
	make proxy.tgz proxy.zip

clean:
	rm -f *.o main proxy.tgz proxy.zip
