// -*- coding: utf-8 -*-
// Time-stamp: <main.cpp  25 nov 2020 20:12:21> 

#include <vector>
#include "factory.hpp"

int main() {
  Factory factory;              // Factory of chain of handlers
  // Description of requested handlers
  // Handler 
  std::vector<std::pair<int, int>> desc = { std::make_pair(7, 0),
                                            std::make_pair(2, 0),
                                            std::make_pair(3, 0) };
  // Create chain of handlers
  Handler* handler = factory.getInstance(desc);
  // Sending requests 0 ... 6
  for (int request = 0; request < 9; ++request)
    handler->handle(request);
}
