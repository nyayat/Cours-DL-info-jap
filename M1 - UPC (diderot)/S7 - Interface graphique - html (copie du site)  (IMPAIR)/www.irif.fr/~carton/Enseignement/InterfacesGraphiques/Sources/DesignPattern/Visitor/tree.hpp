// -*- coding: utf-8 -*-
// Time-stamp: <tree.hpp  26 nov 2020 21:48:48>

#ifndef TREE_HPP
#define TREE_HPP

// Declaration of Visitor class
template<class T>
class Internal;
template<class T>
class Leaf;
#include "visitor.hpp"

// Interface for tree nodes
template<class T>
class Node {
public:
  virtual void accept(Visitor<T>* visitor) = 0;
};

// Class for tree leaves
template<class T>
class Leaf : public Node<T> {
public:
  // Visit
  void accept(Visitor<T>* visitor) override { visitor->visit(this); }
};

// Class for internal nodes of tree
template<class T>
class Internal : public Node<T> {
public:
  // Constructor
  Internal(T v, Node<T>* l, Node<T>* r) : value(v), ls(l), rs(r) {}
  // Getters
  T getval() const { return value; }
  Node<T>* getls() const { return ls; }
  Node<T>* getrs() const { return rs; }
  // Visit
  void accept(Visitor<T>* visitor) override { visitor->visit(this); }
private:
  T value;                      // Value carried by the internal node
  Node<T>* ls;                  // Left son
  Node<T>* rs;                  // Right son
};

// Main class for trees
template<class T>
class Tree {
public:
  // Constructor
  Tree(Node<T>* r) : root(r) {}
  // Visit
  void accept(Visitor<T>* visitor) { root->accept(visitor); }
private:
  Node<T>* root;                // Root node
};

#endif 
