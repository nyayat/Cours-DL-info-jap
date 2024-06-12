// -*- coding: utf-8 -*-
// Time-stamp: <base.hpp  25 nov 2020 16:59:29>

#ifndef BASE_HPP
#define BASE_HPP

#include <iostream>
#include "interface.hpp"

class Base : public Interface {
public:
  virtual void doIt() override { std::cout << "I do it in Base" << std::endl; }
  virtual bool hasFeatureA() const override { return false; }
  virtual void featureA() override { throw std::exception(); };
  virtual bool hasFeatureB() const override { return false; }
  virtual void featureB() override { throw std::exception(); };
  virtual bool hasFeatureC() const override { return false; }
  virtual void featureC() override { throw std::exception(); };
};

#endif 
