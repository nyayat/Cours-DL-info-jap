// -*- coding: utf-8 -*-
// Time-stamp: <delegation-adapter.hpp  25 mar 2020 13:28:31>

// Adapter by delegation
// The adapter points to a legacy object 

#ifndef DELEGATIONADAPTER_HPP
#define DELEGATIONADAPTER_HPP

#include "adapter.hpp"
#include "legacy.hpp"

class DelegationAdapter : public Adapter {
public:
  // Constructor that creates the Legacy object
  DelegationAdapter() : responsability(true), legacy(new Legacy) {}
  // Constructor that receives a Legacy object
  DelegationAdapter(Legacy* l, bool r = false)
    : responsability(r), legacy(l) {}
  // Destructor: delete the Legacy object if needed
  ~DelegationAdapter() {
    if (responsability)
      delete legacy;
  }
  // No copy constructor and no copy assignment operator
  DelegationAdapter(const DelegationAdapter&) = delete;
  DelegationAdapter& operator=(const DelegationAdapter&) = delete;
  // Overriding inherited virtual method
  virtual string doThis() override {
    return legacy->doThat() + " called by delegation adapter";
  }
private:
  // Responsability deleting of Legacy object when this is deleted
  const bool responsability;
  // Pointer to reference the Legacy object
  // The pointer is constant, that is, always points to the same object.
  // The Legacy object is not constant.
  Legacy* const legacy;
};

#endif 
