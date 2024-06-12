// -*- coding: utf-8 -*-
// Time-stamp: <decorator.hpp   2 déc 2020 10:35:47>

#ifndef DECORATOR_HPP
#define DECORATOR_HPP

#include "interface.hpp"

class Decorator : public Interface {
public:
  // Constructor
  Decorator(Interface* n) : next(n) {}
  // Virtual destructor also destroys next
  ~Decorator() { next->~Interface(); }
  // Each operation is delegated to the next object
  virtual void doIt() override { next->doIt(); }
  virtual bool hasFeatureA() const override { return next->hasFeatureA(); }
  virtual void featureA() override { next->featureA(); }
  virtual bool hasFeatureB() const override { return next->hasFeatureB(); }
  virtual void featureB() override { next->featureB(); }
  virtual bool hasFeatureC() const override { return next->hasFeatureC(); }
  virtual void featureC() override { next->featureC(); }
private:
  Interface* const next;       // Link to next Decorator/Base object
};

#endif 
