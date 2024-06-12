// -*- coding: utf-8 -*-
// Time-stamp: <interface.hpp   2 déc 2020 10:34:25>

#ifndef INTERFACE_HPP
#define INTERFACE_HPP

class Interface {
public:
  virtual ~Interface() {}
  virtual void doIt() = 0;
  virtual bool hasFeatureA() const = 0;
  virtual void featureA() = 0;
  virtual bool hasFeatureB() const = 0;
  virtual void featureB() = 0;
  virtual bool hasFeatureC() const = 0;
  virtual void featureC() = 0;
};

#endif 
