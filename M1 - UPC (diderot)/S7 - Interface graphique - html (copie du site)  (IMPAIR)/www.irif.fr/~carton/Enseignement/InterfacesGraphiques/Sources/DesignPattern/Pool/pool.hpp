// -*- coding: utf-8 -*-
// Time-stamp: <pool.hpp  18 nov 2021 11:27:17>

#ifndef POOL_HPP
#define POOL_HPP

#include <vector>

// Pool of objects having a default constructor
template <class T>
class Pool {
public:
  // Virtual destructor
  virtual ~Pool() {
    // Free all objects in pool when pool is deleted
    for (T* object : freelist)
      delete object;
  } 
  // Get object from the pool
  T* get() {
    if (freelist.empty()) {
      // Return new one if none is available
      return new T();           // Create new one
    } else {
      // Return one freelist object
      T* result = freelist.back();  // Get last one of the list
      freelist.pop_back();          // Remove it from the list
      return result;            // Return it
    }
  }
  // Put object back to the pool
  virtual void put(T* object) {
    freelist.push_back(object);     // Add to list
  }
protected:
  std::vector<T*> freelist;         // List of free objects
};

#endif 
