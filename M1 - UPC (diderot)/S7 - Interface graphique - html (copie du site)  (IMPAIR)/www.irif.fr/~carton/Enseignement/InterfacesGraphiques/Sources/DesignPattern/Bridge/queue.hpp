// -*- coding: utf-8 -*-
// Time-stamp: <queue.hpp  25 mar 2020 17:03:05>

#ifndef QUEUE_HPP
#define QUEUE_HPP

#include "container.hpp"

template<class T>
class Queue {
public:
  // Constructor
  Queue(Container<T>* c) : container(c) {}
  // Empty test
  virtual bool empty() const { return container->empty(); };
  // Add new element 
  virtual void push(T object) = 0;
  // Remove and return element
  virtual T pop() = 0;
protected:
  // Container for the elements
  Container<T>* container;
};

#endif 
