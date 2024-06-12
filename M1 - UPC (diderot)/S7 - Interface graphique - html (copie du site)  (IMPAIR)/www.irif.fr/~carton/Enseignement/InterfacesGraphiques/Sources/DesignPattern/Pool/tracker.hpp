// -*- coding: utf-8 -*-
// Time-stamp: <tracker.hpp  23 mar 2020 10:17:46>

#ifndef TRACKER_HPP
#define TRACKER_HPP

// Class tracing calls to constructors and destructor
class Tracker {
public:
  // Friend external output operator
  friend ostream& operator<<(ostream&, const Tracker&);
  // Construtors
  Tracker() { cout << this << " : Tracker()" << endl; }
  Tracker(int n) { cout << this << " : Tracker(" << n << ")" << endl; }
  // Copy constructors
  Tracker(const Tracker& a) {
    cout << this << " : Tracker(const & " << &a << ")" << endl;
  }
  Tracker(Tracker& a) {
    cout << this << " : Tracker(& " << &a << ")" << endl;
  }
  // Move constructor
  Tracker(Tracker&& a) {
    cout << this << " : Tracker(&& " << &a << ")" << endl;
  }
  // Copy assignment operator
  Tracker& operator=(const Tracker& a) {
    cout << this << " : = (& " << &a << ")" << endl;
    return *this;
  }
  // Move assignment operator
  Tracker& operator=(const Tracker&& a) {
    cout << this << " : = (&& " << &a << ")" << endl;
    return *this;
  }
  // Destructor
  ~Tracker() { cout << this << " : ~Tracker()" << endl; }
};

// External output operator
ostream& operator<<(ostream& out, const Tracker& a) {
  out << &a;
  return out;
}

#endif 
