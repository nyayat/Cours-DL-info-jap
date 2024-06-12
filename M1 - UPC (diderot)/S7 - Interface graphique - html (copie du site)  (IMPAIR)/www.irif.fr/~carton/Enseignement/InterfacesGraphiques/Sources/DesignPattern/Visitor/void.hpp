// -*- coding: utf-8 -*-
// Time-stamp: <void.hpp   2 déc 2020 09:38:08>

// Visitor that do nothing but still visits all node
#ifndef VOIDVISITOR_HPP
#define VOIDVISITOR_HPP

#include <iostream>
#include "visitor.hpp"

template<class T>
class VoidVisitor : public Visitor<T> {
public:
  // Visit tree
  void visit(Tree<T>* tree) { tree->accept(this); }
  // Visit internal node: do nothing
  void visit(Internal<T>* node) override {
    std::cout << "Visit internal node " << node << std::endl;
    // Visit of left children
    node->getls()->accept(this);
    // Visit of right children
    node->getrs()->accept(this);
  }
  // Visit leaf: do nothing
  void visit(Leaf<T>* leaf) override {
    std::cout << "Visit leaf " << leaf << std::endl;
  }
};

#endif 
