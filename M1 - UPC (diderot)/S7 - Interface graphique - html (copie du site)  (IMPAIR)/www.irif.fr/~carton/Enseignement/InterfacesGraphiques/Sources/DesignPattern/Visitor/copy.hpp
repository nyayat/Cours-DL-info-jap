// -*- coding: utf-8 -*-
// Time-stamp: <copy.hpp   2 déc 2020 09:41:03>

// Return deep copy of the tree

#ifndef COPY_HPP
#define COPY_HPP

#include "visitor.hpp"

template<class T>
class Copy : public Visitor<T> {
public:
  // Return global value
  Tree<T> visit(Tree<T>* tree) {
    tree->accept(this);
    return Tree<T>(partial);
  }
  // Visit internal nodes: return copy of internal node
  void visit(Internal<T>* node) override {
    node->getls()->accept(this);
    Node<T>* ls = partial;
    node->getrs()->accept(this);
    Node<T>* rs = partial;
    partial = new Internal<T>(node->getval(), ls, rs);
  }
  // Visit leaf: return new leaf
  void visit(Leaf<T>* leaf) override {
    partial = common;
  }
private:
  Node<T>* partial;		// Partial tree constructed so far
  Leaf<T>* common = new Leaf<T>();		// Common leaf
};

#endif 
