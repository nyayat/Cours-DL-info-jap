// -*- coding: utf-8 -*-
// Time-stamp: <factory.hpp  25 nov 2020 18:40:51>

#ifndef FACTORY_HPP
#define FACTORY_HPP

#include <vector>
#include "handler.hpp"

class Factory {
public:
  Handler* getInstance(std::vector<std::pair<int, int>> desc) const;
};

#endif 
