# Time-stamp: <makefile  22 Mar 2004 08:58:15>

CPP = g++
CFLAGS = -Wall -L/usr/qt/3/lib/ -lqt -isystem /usr/qt/3/include/

clock: main.o clock.o
	$(CPP) $(CFLAGS) -o clock clock.o main.o

clock.o: clock.cpp
	$(CPP) $(CFLAGS) -c clock.cpp

main.o: main.cpp
	$(CPP) $(CFLAGS) -c main.cpp

