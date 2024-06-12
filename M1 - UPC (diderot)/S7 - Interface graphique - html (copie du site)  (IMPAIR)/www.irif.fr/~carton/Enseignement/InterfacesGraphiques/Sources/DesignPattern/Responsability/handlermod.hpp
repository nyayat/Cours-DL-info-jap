// -*- coding: utf-8 -*-
// Time-stamp: <handlermod.hpp  25 nov 2020 20:11:45>

#ifndef HANDLERMOD_HPP
#define HANDLERMOD_HPP

#include <iostream>
#include "partial.hpp"

class HandlerMod : public Partial {
public:
  // Constructor
  HandlerMod(Handler* next, int m, int v) : Partial(next), mod(m), val(v) {}
  // Handle even requests
  virtual void handle(int request) override {
    if (request % mod == val) {
      // Case handled
      std::cout << "Handler % " << mod << " == " << val;
      std::cout <<  " handles request " << request << std::endl;
    } else {
      // Fall back to parent class method
      Partial::handle(request);
    }
  }
private:
  int mod;                      // Modulo
  int val;                      // Value
};

#endif 
