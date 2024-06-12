// -*- coding: utf-8 -*-
// Time-stamp: <decoratorA.hpp  26 nov 2020 11:03:08>

#ifndef DECORATORA_HPP
#define DECORATORA_HPP

#include "decorator.hpp"

class DecoratorA : public Decorator {
public:
  // Constructor
  DecoratorA(Interface* next) : Decorator(next) {}
  // Object has feature A
  virtual bool hasFeatureA() const override { return true; }
  virtual void featureA() override {
    std::cout << "I do feature A in Decorator A" << std::endl;
  }
};

#endif 
