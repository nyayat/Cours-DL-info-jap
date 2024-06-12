// -*- coding: utf-8 -*-
// Time-stamp: <main.cpp  25 mar 2020 19:57:38> 

#include <iostream>
#include <string>
using namespace std;

#include "arraylist.hpp"
#include "linkedlist.hpp"
#include "fifoqueue.hpp"
#include "lifoqueue.hpp"

int main(int argc, char* argv[]) {
  // 4 possibilities
  Queue<string>* queue = new FifoQueue<string>(new ArrayList<string>);
  // Queue<string>* queue = new FifoQueue<string>(new LinkedList<string>);
  // Queue<string>* queue = new LifoQueue<string>(new ArrayList<string>);
  // Queue<string>* queue = new LifoQueue<string>(new LinkedList<string>);
  // Adding command line arguments to the queue
  for (int i = 1; i < argc; i++)
    queue->push(argv[i]);
  // Running over elements of the queue
  while (!queue->empty()) 
    cout << queue->pop() << endl;
}
