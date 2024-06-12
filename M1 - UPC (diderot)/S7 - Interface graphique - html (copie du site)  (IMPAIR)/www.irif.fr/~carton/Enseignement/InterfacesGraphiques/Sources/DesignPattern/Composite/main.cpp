// -*- coding: utf-8 -*-
// Time-stamp: <main.cpp  25 mar 2020 10:14:06> 

#include <iostream>
#include <string>
using namespace std;

#include "disk.hpp"
#include "rectangle.hpp"
#include "composite.hpp"

int main() {
  Disk disk0(1);
  cout << disk0.surface() << endl;
  disk0.draw(0, 0);
  Rectangle rectangle0(1,2);
  cout << rectangle0.surface() << endl;
  rectangle0.draw(0, 0);
  Composite composite0;
  composite0.add(&disk0);
  composite0.add(&rectangle0);
  cout << composite0.surface() << endl;
  composite0.draw(0, 0);
}
