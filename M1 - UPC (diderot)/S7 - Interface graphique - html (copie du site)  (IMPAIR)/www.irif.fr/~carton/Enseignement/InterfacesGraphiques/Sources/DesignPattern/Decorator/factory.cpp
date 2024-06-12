// -*- coding: utf-8 -*-
// Time-stamp: <factory.cpp  26 nov 2020 11:49:21> 

#include "factory.hpp"
#include "base.hpp"
#include "decoratorA.hpp"
#include "decoratorB.hpp"
#include "decoratorC.hpp"

// Return Base object with decorators specified by string desc.
// String desc should contain occurrences of characters 'A', 'B', and 'C'
Interface* Factory::getInstance(std::string desc) const {
  Interface* instance = new Base();
  // Add DecoratorA if requested
  if (desc.find('A') != std::string::npos)
    instance = new DecoratorA(instance);
  // Add DecoratorB if requested
  if (desc.find('B') != std::string::npos)
    instance = new DecoratorB(instance);
  // Add DecoratorC if requested
  if (desc.find('C') != std::string::npos)
    instance = new DecoratorC(instance);
  return instance;
}
