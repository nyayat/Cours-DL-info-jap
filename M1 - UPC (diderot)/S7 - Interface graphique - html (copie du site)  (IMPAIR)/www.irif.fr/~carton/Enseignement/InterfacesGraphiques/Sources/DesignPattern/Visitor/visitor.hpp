// -*- coding: utf-8 -*-
// Time-stamp: <visitor.hpp  27 mar 2020 18:07:57>

#ifndef VISITOR_HPP
#define VISITOR_HPP

#include "tree.hpp"

template<class T>
class Visitor {
public:
  // Visits
  virtual void visit(Internal<T>* node) = 0;
  virtual void visit(Leaf<T>* leaf) = 0;
};

#endif 
