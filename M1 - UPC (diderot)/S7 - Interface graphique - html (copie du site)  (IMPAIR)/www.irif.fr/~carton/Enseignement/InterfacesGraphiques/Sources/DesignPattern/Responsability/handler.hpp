// -*- coding: utf-8 -*-
// Time-stamp: <handler.hpp   1 avr 2020 09:48:50>

#ifndef HANDLER_HPP
#define HANDLER_HPP

class Handler {
public:
  virtual void handle(int request) = 0;
};

#endif 
