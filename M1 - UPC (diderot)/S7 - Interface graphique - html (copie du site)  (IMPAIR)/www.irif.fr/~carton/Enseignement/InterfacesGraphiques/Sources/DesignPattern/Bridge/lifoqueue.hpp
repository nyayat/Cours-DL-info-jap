// -*- coding: utf-8 -*-
// Time-stamp: <lifoqueue.hpp  25 mar 2020 17:02:29>

// Last In First Out queue

#ifndef LIFOQUEUE_HPP
#define LIFOQUEUE_HPP

#include "queue.hpp"

template<class T>
class LifoQueue : public Queue<T> {
public:
  // Constructor
  LifoQueue(Container<T>* container) : Queue<T>(container) {}
  // Add new element 
  virtual void push(T object) override { this->container->pushBack(object); }
  // Remove and return element
  virtual T pop() override { return this->container->popBack(); }
};

#endif 
