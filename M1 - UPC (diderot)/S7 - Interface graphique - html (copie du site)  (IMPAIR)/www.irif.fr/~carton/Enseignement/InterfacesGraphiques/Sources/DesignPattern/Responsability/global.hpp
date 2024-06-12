// -*- coding: utf-8 -*-
// Time-stamp: <global.hpp  25 nov 2020 18:37:20>

#ifndef GLOBAL_HPP
#define GLOBAL_HPP

#include <iostream>
#include "handler.hpp"

class Global : public Handler {
public:
  virtual void handle(int request) override {
    std::cout << "Global handles request " << request << std::endl;
  }
};

#endif 
