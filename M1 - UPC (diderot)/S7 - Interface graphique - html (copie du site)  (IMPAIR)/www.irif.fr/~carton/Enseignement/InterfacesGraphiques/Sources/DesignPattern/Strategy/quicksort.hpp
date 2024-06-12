// -*- coding: utf-8 -*-
// Time-stamp: <quicksort.hpp  25 nov 2020 21:00:18>

// Quicksort sorting algorithm
// https://en.wikipedia.org/wiki/Quicksort
// Hoare partition scheme

#ifndef QUICKSORT_HPP
#define QUICKSORT_HPP

#include <utility>
#include "sorter.hpp"

template<class T>
class Quicksort : public Sorter<T> {
public:
  // Sort from begin included to end excluded
  virtual void sort(T* begin, T* end) {
    // Do nothing if array has either 0 or 1 element
    if (begin < end-1) {
      // Partitioning
      T* p = partition(begin, end);
      // Sorting left part
      sort(begin, p+1);
      // Sorting right part
      sort(p+1, end);
    }
  }
private:
  // Partitioning procedure returning pivot position
  static T* partition(T* begin, T* end) {
    // Pivot is chosen in the middle of the array
    // It helps if the array is already sorted.
    T pivot = begin[(end-begin-1)/2];
    T* b = begin-1;
    T* e = end;
    // Forever loop
    while (true) {
      do  ++b; while (*b < pivot);
      do  --e; while (*e > pivot);
      if (e <= b)
        return e;
      std::swap(*b, *e);
    }
  }
};

#endif 
