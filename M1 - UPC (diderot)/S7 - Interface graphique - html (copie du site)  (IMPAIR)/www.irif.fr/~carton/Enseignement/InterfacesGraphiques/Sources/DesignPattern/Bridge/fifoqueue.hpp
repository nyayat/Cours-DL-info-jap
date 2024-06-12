// -*- coding: utf-8 -*-
// Time-stamp: <fifoqueue.hpp  25 mar 2020 17:02:17>

// First In First Out queue

#ifndef FIFOQUEUE_HPP
#define FIFOQUEUE_HPP

#include "queue.hpp"

template<class T>
class FifoQueue : public Queue<T> {
public:
  // Constructor
  FifoQueue(Container<T>* container) : Queue<T>(container) {}
  // Add new element 
  virtual void push(T object) override { this->container->pushBack(object); }
  // Remove and return element
  virtual T pop() override { return this->container->popFront(); }
};

#endif 
