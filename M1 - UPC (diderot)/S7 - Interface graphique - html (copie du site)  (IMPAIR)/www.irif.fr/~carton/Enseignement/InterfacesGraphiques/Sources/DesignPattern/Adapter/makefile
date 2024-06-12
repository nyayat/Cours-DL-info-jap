# -*- coding: utf-8 -*-
# Time-stamp: <makefile  26 nov 2020 22:12:31>

# CPP = clang++
CPP = g++
CFLAGS = -Wall 

main: legacy.hpp adapter.hpp aggregation-adapter.hpp delegation-adapter.hpp inheritance-adapter.hpp builder.hpp main.cpp
	$(CPP) $(CFLAGS) -o main main.cpp	

adapter.tgz: legacy.hpp adapter.hpp aggregation-adapter.hpp delegation-adapter.hpp inheritance-adapter.hpp builder.hpp main.cpp makefile 
	tar zcvf adapter.tgz $^

adapter.zip: legacy.hpp adapter.hpp aggregation-adapter.hpp delegation-adapter.hpp inheritance-adapter.hpp builder.hpp main.cpp makefile
	zip adapter.zip $^

archives:
	make adapter.tgz adapter.zip

clean:
	rm -f *.o main adapter.tgz adapter.zip
