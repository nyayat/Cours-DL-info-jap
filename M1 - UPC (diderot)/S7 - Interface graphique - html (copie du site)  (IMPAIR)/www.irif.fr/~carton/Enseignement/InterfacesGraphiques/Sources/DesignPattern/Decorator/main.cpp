// -*- coding: utf-8 -*-
// Time-stamp: <main.cpp  26 nov 2020 11:47:44> 

#include <iostream>
#include <string>
#include "factory.hpp"

// Use object by testing each feature A, B, C
void use(Interface* interface) {
  interface->doIt();
  if (interface->hasFeatureA())
    interface->featureA();
  else
    std::cout << "No feature A" << std::endl;
  if (interface->hasFeatureB())
    interface->featureB();
  else
    std::cout << "No feature B" << std::endl;
  if (interface->hasFeatureC())
    interface->featureC();
  else
    std::cout << "No feature C" << std::endl;
}

// Main function
int main() {
  Factory factory;
  use(factory.getInstance(""));
  use(factory.getInstance("A"));
  use(factory.getInstance("B"));
  use(factory.getInstance("AB"));
  use(factory.getInstance("C"));
  use(factory.getInstance("AC"));
  use(factory.getInstance("BC"));
  use(factory.getInstance("ABC"));
}
