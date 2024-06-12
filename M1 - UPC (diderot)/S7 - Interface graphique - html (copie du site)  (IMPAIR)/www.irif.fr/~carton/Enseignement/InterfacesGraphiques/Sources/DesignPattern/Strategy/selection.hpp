// -*- coding: utf-8 -*-
// Time-stamp: <selection.hpp  30 mar 2020 13:19:02>

// Selection sorting algorithm
// https://en.wikipedia.org/wiki/Selection_sort

#ifndef SELECTION_HPP
#define SELECTION_HPP

#include <utility>
#include "sorter.hpp"

template<class T>
class Selection : public Sorter<T> {
public:
  // Sort from begin included to end excluded
  virtual void sort(T* begin, T* end) {
    while (begin < end) {
      // Look for least element from begin included to end excluded
      T* min = begin;           // By default begin achieves mininum
      for (T* current = begin+1; current < end; ++current) 
        if (*current < *min)
          min = current;        // New minimum found
      // Swap current position and min if needed
      if (begin < min)
        std::swap(*begin, *min);
      ++begin;
    }
  }
};

#endif 
