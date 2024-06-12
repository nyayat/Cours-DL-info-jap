// -*- coding: utf-8 -*-
// Time-stamp: <factory.cpp  25 nov 2020 18:40:34> 

#include "factory.hpp"
#include "handler.hpp"
#include "handlermod.hpp"
#include "global.hpp"

// Return chain of handlermod specified by desc
// Desc if a vector of pairs (mod, val) for each HandlerMod to be added
Handler* Factory::getInstance(std::vector<std::pair<int, int>> desc) const {
  Handler* instance = new Global();
  // Add each handlermod given by description desc
  // Extract each pair p from vector desc
  for (auto p : desc)
    // Add HandlerMod with mod = p.first and val = p.second
    instance = new HandlerMod(instance, p.first, p.second);
  return instance;
}
