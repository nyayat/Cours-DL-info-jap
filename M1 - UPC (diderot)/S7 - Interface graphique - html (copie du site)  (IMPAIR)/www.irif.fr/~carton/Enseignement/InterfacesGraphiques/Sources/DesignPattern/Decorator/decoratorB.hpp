// -*- coding: utf-8 -*-
// Time-stamp: <decoratorB.hpp  26 nov 2020 11:02:55>

#ifndef DECORATORB_HPP
#define DECORATORB_HPP

#include "decorator.hpp"

class DecoratorB : public Decorator {
public:
  // Constructor
  DecoratorB(Interface* next) : Decorator(next) {}
  // Object has feature B
  virtual bool hasFeatureB() const override { return true; }
  virtual void featureB() override {
    std::cout << "I do feature B in Decorator B" << std::endl;
  }
};

#endif 
