// -*- coding: utf-8 -*-
// Time-stamp: <main.cpp  25 mar 2020 11:14:06> 

#include <iostream>
#include <string>
using namespace std;
#include "observer.hpp"
#include "source.hpp"
#include "target.hpp"

int main() {
  // Create one source
  Source source0(0);
  // Create first observer and register it to the source0
  Target target0(0);
  source0.regist(&target0);
  // Create second observer and register it to the source0
  Target target1(1);
  source0.regist(&target1);
  // Update twice source0
  source0.update(0);
  source0.update(1);

  // Chain of sources
  // Create another source
  Source source1(1);
  // Register source0 and target1 to suject1
  source1.regist(&source0);
  source1.regist(&target1);
  // Update source1
  source1.update(2);
  
}
