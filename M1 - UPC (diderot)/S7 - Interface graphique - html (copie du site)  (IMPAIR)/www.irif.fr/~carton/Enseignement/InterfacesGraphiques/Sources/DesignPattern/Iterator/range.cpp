// -*- coding: utf-8 -*-
// Time-stamp: <range.cpp  26 mar 2020 07:47:07> 

#include <iostream>
#include <string>
#include <math.h>
using namespace std;

// Range of integers
class range {
public:
  range(int f, int l, int i = 1) :
    first(f), last(f + ceil((l-f)/((float)i))*i), incr(i) {}
  class iterator {
  public:
    // Constructor
    iterator(int c, int i) : current(c), incr(i) {}
    // Comparator
    bool operator==(iterator it) { return current == it.current; }
    bool operator!=(iterator it) { return current != it.current; }
    bool operator<(iterator it) { return current < it.current; }
    // Increment
    iterator& operator++() { current += incr; return *this; }
    // Dereference
    int operator*() { return current; }
  private:
    int current;                // Current value
    int incr;                   // Increment
  };
  iterator begin() { return iterator(first, incr); }
  iterator end() { return iterator(last, incr); }
private:
  int first;                    // First value inclusive
  int last;                     // Last value exclusive
  int incr;                     // Increment
};


int main() {
  auto r = range(0, 10);
  for (auto it = r.begin(); it != r.end(); ++it)
    cout << *it << ' ';
  cout << endl;
  for (int i : range(10, 1, -2))
    cout << i << ' ';
  cout << endl;
}
