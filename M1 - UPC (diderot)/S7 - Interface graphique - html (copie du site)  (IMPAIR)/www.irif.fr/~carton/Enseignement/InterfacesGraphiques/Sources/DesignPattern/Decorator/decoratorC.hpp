// -*- coding: utf-8 -*-
// Time-stamp: <decoratorC.hpp  26 nov 2020 11:03:28>

#ifndef DECORATORC_HPP
#define DECORATORC_HPP

#include "decorator.hpp"

class DecoratorC : public Decorator {
public:
  // Constructor
  DecoratorC(Interface* next) : Decorator(next) {}
  // Object has feature C
  virtual bool hasFeatureC() const override { return true; }
  virtual void featureC() override {
    std::cout << "I do feature C in Decorator C" << std::endl;
  }
};

#endif 
