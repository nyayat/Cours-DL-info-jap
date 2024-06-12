// -*- coding: utf-8 -*-
// Time-stamp: <legacy.hpp  23 mar 2020 15:53:48>

#ifndef LEGACY_HPP
#define LEGACY_HPP

#include <iostream>
#include <string>
using namespace std;

class Legacy {
public:
  // Tracking constructor and destructor (just for fun)
  Legacy() { cout << "Legacy()" << endl; }
  ~Legacy() { cout << "~Legacy()" << endl; }
  string doThat() { return "doThat of legacy object"; }
};

#endif 
