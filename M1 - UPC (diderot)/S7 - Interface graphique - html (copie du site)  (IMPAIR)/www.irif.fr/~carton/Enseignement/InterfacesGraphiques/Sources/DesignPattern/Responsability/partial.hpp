// -*- coding: utf-8 -*-
// Time-stamp: <partial.hpp  25 nov 2020 18:03:13>

#ifndef PARTIAL_HPP
#define PARTIAL_HPP

#include "handler.hpp"

class Partial : public Handler {
public:
  // Constructor
  Partial(Handler* n) : next(n) {}
  // Each request is sent to the next object
  virtual void handle(int request) override { next->handle(request); }
private:
  Handler* next;		// Link to next handler
};

#endif 
