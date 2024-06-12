// -*- coding: utf-8 -*-
// Time-stamp: <main.cpp  25 mar 2020 11:40:53> 

#include <iostream>
#include <string>
using namespace std;

#include "builder.hpp"

// Function to use an adapter, that is, call its doThis method
void use(Adapter* adapter) {
  // Print return value of doThis method
  cout << adapter->doThis() << endl;
}

int main() {
  Builder builder;
  
  Adapter* adapter1 = builder.getAdapter("aggregation");
  use(adapter1);
  delete adapter1;

  Adapter* adapter2 = builder.getAdapter("inheritance");
  use(adapter2);
  delete adapter2;

  Adapter* adapter3 = builder.getAdapter("delegation");
  use(adapter3);
  delete adapter3;

  Legacy* legacy4 = new Legacy();
  Adapter* adapter4 = builder.getAdapter("delegation", legacy4);
  use(adapter4);
  delete adapter4;
  // legacy4 must be deleted 
  delete legacy4;
}
