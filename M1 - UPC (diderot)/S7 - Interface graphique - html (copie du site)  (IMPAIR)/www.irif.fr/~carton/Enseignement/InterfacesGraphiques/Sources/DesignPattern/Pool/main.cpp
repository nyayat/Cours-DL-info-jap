// -*- coding: utf-8 -*-
// Time-stamp: <main.cpp  18 nov 2021 11:28:33> 

#include <iostream>
#include <string>
using namespace std;
#include "tracker.hpp"
#include "limited-pool.hpp"

class A : public Tracker {};

int main() {
  const int n = 5;
  vector<A*> tmp;
  LimitedPool<A> pool(n);
  std::cout << "-----------------" << std::endl;
  for(int i = 0; i < 2*n; i++) {
    A* p = pool.get();
    tmp.push_back(p);
  }
  std::cout << "-----------------" << std::endl;
  while(!tmp.empty()) {
    pool.put(tmp.back());
    tmp.pop_back();
  }
  std::cout << "-----------------" << std::endl;
  for(int i = 0; i < 2*n; i++) {
    A* p = pool.get();
    tmp.push_back(p);
  }
  std::cout << "-----------------" << std::endl;
  while(!tmp.empty()) {
    pool.put(tmp.back());
    tmp.pop_back();
  }
}
