// -*- coding: utf-8 -*-
// Time-stamp: <aggregation-adapter.hpp  23 mar 2020 15:29:19>

// Adapter by aggregation
// The adapter contains a copy of a legacy object as an attribute

#ifndef AGGREGATIONADAPTER_HPP
#define AGGREGATIONADAPTER_HPP

#include "adapter.hpp"
#include "legacy.hpp"

class AggregationAdapter : public Adapter {
  // Overriding inherited virtual method
  virtual string doThis() override {
    return legacy.doThat() + " called by aggregation adapter";
  }
private:
  Legacy legacy;
};

#endif 
