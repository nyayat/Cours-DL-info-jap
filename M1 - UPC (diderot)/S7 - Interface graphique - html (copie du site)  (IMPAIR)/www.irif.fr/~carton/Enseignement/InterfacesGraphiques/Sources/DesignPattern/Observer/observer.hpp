// -*- coding: utf-8 -*-
// Time-stamp: <observer.hpp  22 mar 2020 14:57:37>

#ifndef OBSERVER_HPP
#define OBSERVER_HPP

class Observer {
public:
  // Receive notification with an int as event
  virtual void update(int) = 0;
};
  
#endif 
