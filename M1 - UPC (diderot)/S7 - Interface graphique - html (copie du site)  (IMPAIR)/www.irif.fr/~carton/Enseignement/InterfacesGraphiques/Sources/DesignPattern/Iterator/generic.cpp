// -*- coding: utf-8 -*-
// Time-stamp: <generic.cpp  26 nov 2020 23:23:01> 

// Generic range 
// The class range provides two iterators through methods begin() and end().

#include <iostream>
#include <string>
#include <math.h>
using namespace std;
#include "date.hpp"

// Generic range 
template<class T>
class range {
public:
  range(T f, T l, int i = 1) : first(f), last(l), incr(i) {}
  class iterator {
  public:
    // Constructor
    iterator(T c, int i) : current(c), incr(i) {}
    // Comparator
    bool operator==(iterator it) { return current == it.current; }
    bool operator!=(iterator it) { return current != it.current; }
    bool operator<(iterator it)  { return current <  it.current; }
    // Increment
    iterator& operator++();
    // Dereference
    T operator*() { return current; }
  private:
    T current;                  // Current value
    int incr;                   // Increment
  };
  iterator begin() const { return iterator(first, incr); }
  iterator end()   const { return iterator(last, incr); }
private:
  const T first;                // First value inclusive
  const T last;                 // Last value exclusive
  const int incr;               // Increment
};

template<class T>
typename range<T>::iterator& range<T>::iterator::operator++() {
  if (incr > 0) {
    for (int i = 0; i < incr; ++i)
      ++current;
  } else {
    for (int i = 0; i > incr; --i)
      --current;
  }
  return *this;
}

int main() {
  auto r = range<int>(0, 10);
  for (auto it = r.begin(); it != r.end(); ++it)
    cout << *it << ' ';
  cout << endl;
  for (int i : range<int>(0, 10, 2)) 
    cout << i << ' ';
  cout << endl;
  for (Date d : range<Date>(Date(25, 12, 2016), Date(29, 12, 2016))) 
    cout << d << endl;
}
