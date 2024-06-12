// -*- coding: utf-8 -*-
// Time-stamp: <factory.hpp  26 nov 2020 11:45:21>

#ifndef FACTORY_HPP
#define FACTORY_HPP

#include <string>
#include "interface.hpp"

class Factory {
public:
  // Constructor fixing default value for added features
  // By default, this is no feature  
  Factory(std::string d = "") : def(d) {}
  // Get default instance 
  Interface* getInstance() const { return getInstance(def); }
  // Get instance specified by desc
  Interface* getInstance(std::string desc) const;
private:
  const std::string def;	// Default value
};

#endif 
