// -*- coding: utf-8 -*-
// Time-stamp: <sorter.hpp  30 mar 2020 15:03:52>

// Sorting algorithm interface

#ifndef SORTER_HPP
#define SORTER_HPP

template<class T>
class Sorter {
public:
  // Sort from begin included to end excluded
  virtual void sort(T* begin, T* end) = 0;
};

#endif 
