# -*- coding: utf-8 -*-
# Time-stamp: <makefile  26 nov 2020 22:09:02>

# CPP = clang++
CPP = g++
CFLAGS = -Wall 

main: tracker.hpp pool.hpp limited-pool.hpp main.cpp
	$(CPP) $(CFLAGS) -o main main.cpp	

tracker-test: tracker.hpp tracker-test.cpp
	$(CPP) $(CFLAGS) -fno-elide-constructors -o tracker-test tracker-test.cpp

pool.tgz: tracker.hpp pool.hpp limited-pool.hpp main.cpp makefile
	tar zcvf pool.tgz $^

pool.zip: tracker.hpp pool.hpp limited-pool.hpp main.cpp makefile
	zip pool.zip $^

archives:
	make pool.tgz pool.zip

clean:
	rm -f *.o tracker-test main pool.tgz pool.zip
