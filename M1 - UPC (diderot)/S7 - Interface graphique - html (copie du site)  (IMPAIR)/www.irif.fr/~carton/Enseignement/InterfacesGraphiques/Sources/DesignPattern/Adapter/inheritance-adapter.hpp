// -*- coding: utf-8 -*-
// Time-stamp: <inheritance-adapter.hpp  23 mar 2020 18:03:23>

// Adapter by inheritance
// The adapter contains a copy of a legacy object obtained by inheritance

#ifndef INHERITANCEADAPTER_HPP
#define INHERITANCEADAPTER_HPP

#include "adapter.hpp"
#include "legacy.hpp"

class InheritanceAdapter : public Adapter, public Legacy {
  // DoThis is done by calling inherited method doThat
  virtual string doThis() override {
    return doThat() + " called by inheritance adapter";
  }
};

#endif 
