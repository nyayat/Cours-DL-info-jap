// -*- coding: utf-8 -*-
// Time-stamp: <limited-pool.hpp  18 nov 2021 11:21:19>

#ifndef LIMITEDPOOL_HPP
#define LIMITEDPOOL_HPP

#include "pool.hpp"

// Pool of objects having a default constructor
// The number of objects is limited to the number given to the constructor.
template <class T>
class LimitedPool : public Pool<T> {
public:
  // Constructor
  LimitedPool(unsigned int m) : max(m) {}
  // Put object back to the pool
  virtual void put(T* object) override {
    if (Pool<T>::freelist.size() < max) { 
      Pool<T>::freelist.push_back(object);   // Add to list
    } else {
      delete object;            // Free object
    }
  }
private:
  const unsigned int max;      // Maximum number of objets in the pool
};

#endif 
