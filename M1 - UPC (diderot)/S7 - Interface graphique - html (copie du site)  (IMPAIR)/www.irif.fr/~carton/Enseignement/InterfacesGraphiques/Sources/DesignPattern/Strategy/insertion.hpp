// -*- coding: utf-8 -*-
// Time-stamp: <insertion.hpp  30 mar 2020 13:17:06>

// Insertion sorting algorithm
// https://en.wikipedia.org/wiki/Insertion_sort

#ifndef INSERTION_HPP
#define INSERTION_HPP

#include <utility>
#include "sorter.hpp"

template<class T>
class Insertion : public Sorter<T> {
public:
  // Sort from begin included to end excluded
  virtual void sort(T* begin, T* end) {
    for (T* current = begin; current < end; ++current) {
      // Insert current in [begin, current)
      for (T* position = current;
           begin < position && position[-1] > position[0];
           --position)
        std::swap(position[-1], position[0]);
    }
  }
};

#endif 
