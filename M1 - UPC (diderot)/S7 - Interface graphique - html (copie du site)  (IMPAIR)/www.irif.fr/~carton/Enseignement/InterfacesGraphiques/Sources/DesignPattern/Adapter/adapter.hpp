// -*- coding: utf-8 -*-
// Time-stamp: <adapter.hpp  23 mar 2020 15:42:02>

#ifndef ADAPTER_HPP
#define ADAPTER_HPP

class Adapter {
public:
  // Virtual interface to implement
  virtual string doThis() = 0;
  // Virtual destructor is needed
  virtual ~Adapter() {}
};

#endif 
