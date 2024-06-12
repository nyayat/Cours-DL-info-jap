// -*- coding: utf-8 -*-
// Time-stamp: <builder.hpp  25 mar 2020 11:51:57>

#ifndef BUILDER_HPP
#define BUILDER_HPP

#include "aggregation-adapter.hpp"
#include "delegation-adapter.hpp"
#include "inheritance-adapter.hpp"

// Class in charge of creating adapters
class Builder {
private:
  // Hashing function for C style strings
  // It is constexpr since it can be computed at compile time.
  // This is inspired by the hashCode method for String in Java
  // This definition must be before its use in getAdapter.
  constexpr static unsigned int hash(const char* str) {
    return *str == '\0' ? 0 : (hash(str+1) * 31) + *str;
  }
public:
  // Returns a new adapter created on the fly
  // The dynamic type of the returned adapter is chosen upon the desc string
  Adapter* getAdapter(string desc, Legacy* legacy = nullptr) const {
    switch (hash(desc.c_str())) {
    case hash("aggregation"):
      return new AggregationAdapter();
    case hash("delegation"):
      return legacy != nullptr ? new DelegationAdapter(legacy)
                               : new DelegationAdapter();
    case hash("inheritance"):
      return new InheritanceAdapter();
    default:
      return new AggregationAdapter();
    }
  }
};

#endif 
