// -*- coding: utf-8 -*-
// Time-stamp: <target.hpp  25 mar 2020 11:13:23>

#ifndef TARGET_HPP
#define TARGET_HPP

#include <iostream>

class Target : public Observer {
public:
  // Constructor 
  Target(int id) : id(id) {}
  // Overriding method update of Observer
  void update(int event) override {
    std::cout << "Target " << id << " received event " << event << std::endl;
  }
private:
  int id;			// Identifier 
};
#endif 
