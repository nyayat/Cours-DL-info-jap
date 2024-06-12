// -*- coding: utf-8 -*-
// Time-stamp: <container.hpp  25 mar 2020 17:13:04>

#ifndef CONTAINER_HPP
#define CONTAINER_HPP

template<class T>
class Container {
public:
  // Empty test
  virtual bool empty() const = 0;
  // Access to first and last elements
  virtual T& front() = 0;
  virtual T& back() = 0;
  // Add new element 
  virtual void pushFront(T object) = 0;
  virtual void pushBack(T object) = 0;
  // Remove and return element
  virtual T popFront() = 0;
  virtual T popBack() = 0;
};

#endif 
