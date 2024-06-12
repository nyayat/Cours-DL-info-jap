// -*- coding: utf-8 -*-
// Time-stamp: <arraylist.hpp  25 mar 2020 20:31:38>

// Lists implemented by arrays

#ifndef ARRAYLIST_HPP
#define ARRAYLIST_HPP

#include <iostream>
#include "container.hpp"

template<class T>
class ArrayList : public Container<T> {
public:
  // Constructor and destructor
  ArrayList() : array(new T[capacity]) {}
  ~ArrayList() { delete [] array; }
  // No copy constructor and no copy assignment operator
  ArrayList(const ArrayList<T>&) = delete;
  ArrayList<T>& operator=(const ArrayList<T>&) = delete;
  // Empty test
  virtual bool empty() const override { return size == 0; };
  // Access first element
  virtual T& front() override {
    if (size > 0) {
      return array[start];
    } else {
      throw exception();
    }
  }
  // Access last element
  virtual T& back() override {
    if (size > 0) {
      return array[(start+size-1) % capacity];
    } else {
      throw exception();
    }
  }
  // Add element at the beginning
  virtual void pushFront(T object) override {
    if (size == capacity) {
      enlarge();
    }
    // Move start one step backwards
    start = (start+capacity-1) % capacity;
    // Copy object
    array[start] = object;
    // Update size
    size++;
  }
  // Add element at the end
  virtual void pushBack(T object) override {
    if (size == capacity) {
      enlarge();
    }
    // Copy object
    array[(start+size) % capacity] = object;
    // Update size
    size++;
  }
  // Remove and return first element
  virtual T popFront() override {
    if (size > 0) {
      // Position of the element to be returned
      unsigned int position = start;
      // Move start one step forwards
      start = (start+1) % capacity;
      // Update size
      size--;
      return array[position];
    } else {
      throw exception();
    }
  }
  // Remove and return last element
  virtual T popBack() override {
    if (size > 0) {
      // Position of the element to be returned
      unsigned int position = (start+size-1) % capacity;
      // Update size
      size--;
      return array[position];
    } else {
      throw exception();
    }
  }
private:
  // Enlarge array
  void enlarge() {
    T* oldarray = array;        // Pointer to current array
    unsigned int oldcapacity = capacity;
    // Update capacity
    capacity *= 2;
    // Allocate new array
    array = new T[capacity];
    // Copy elements from old array to new array
    for (unsigned int i = 0; i < size; i++)
      array[i] = oldarray[(start+i) % oldcapacity];
    // Update start
    start = 0;
    // Free old array
    delete [] oldarray;
  }
  // The array is used cyclically
  // The size satisfies 0 <= size <= capacity
  // The array is empty iff size == 0
  // The array is full iff size == capacity
  unsigned int size = 0;        // Number of elements
  unsigned int start = 0;       // Position of the first element in array
  unsigned int capacity = 4;    // Size of the array
  T* array;                     // Array containing elements
};

#endif 
