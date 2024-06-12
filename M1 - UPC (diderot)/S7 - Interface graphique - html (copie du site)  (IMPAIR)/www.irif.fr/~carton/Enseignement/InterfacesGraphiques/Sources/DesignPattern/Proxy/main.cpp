// -*- coding: utf-8 -*-
// Time-stamp: <main.cpp  26 nov 2021 10:09:03> 

#include <iostream>
#include <string>
using namespace std;

#include "proxy.hpp"

int main(int argc, char* argv[]) {
  int size = argc-1;
  // Fill proxy with command line arguments
  Proxy<string> proxy;
  for (int i = 0; i < size; i++)
    proxy.push_back(argv[i+1]);
  // Print array
  for (int i = 0; i < size; i++)
    cout << proxy[i] << ' ';
  cout << endl;
}
