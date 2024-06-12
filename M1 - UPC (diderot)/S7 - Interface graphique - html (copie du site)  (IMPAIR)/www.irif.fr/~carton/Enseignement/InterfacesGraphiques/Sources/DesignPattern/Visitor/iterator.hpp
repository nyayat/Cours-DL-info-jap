// -*- coding: utf-8 -*-
// Time-stamp: <iterator.hpp   4 déc 2020 21:31:15>

#ifndef ITERATOR_HPP
#define ITERATOR_HPP

#include <vector>
#include "visitor.hpp"

// Iterator base class
// This iterator provides three visits to each internal node of the tree:
// - one visit before all its chlildren (prefix)
// - one visit before all its left children but before all its right children
// - one visit after all its chlildren (prefix)
template<class T>
class Iterator : public Visitor<T> {
public:
  // Constructor
  Iterator(Tree<T>* t, bool end = false) : tree(t), ended(end) { next(); }
  // Destructor must be virtual because this class is derived
  virtual ~Derived() {}
  // Check whether more elements are available
  bool hasNext() const { return !ended; }
  // Move to next element
  Iterator<T>& operator++() { next(); return *this; }
  // Equality of iterators
  bool operator==(const Iterator<T>& rhs) const {
    // Trees must be the same, ended status abd length of branches also
    if (tree != rhs.tree || ended != rhs.ended ||
        branch.size() != rhs.branch.size())
      return false;
    // Check that branches are equal
    for (int i = 0; i < branch.size(); ++i)
      if (branch[i] != rhs.branch[i])
        return false;
    // Match
    return true;
  }
  // Non-equality of iterators
  bool operator!=(const Iterator<T>& rhs) const { return !(*this == rhs); }
  // Return value of the current node
  T operator*() {
    if (!branch.empty())
      // Current node is the last node of the branch
      return branch.back().first->getval();
    else
      // No current node
      throw exception();
  }
  // Visits
  virtual void visit(Internal<T>* node) override {
    if (branch.empty() || branch.back().first != node) {
      // First visit to node
      branch.push_back(make_pair(node, 0));
    } else {
      // Next visit to the same node
      branch.back().second++;
    }
  }
  virtual void visit(Leaf<T>* leaf) override {
    // Leafs are ignored: only internal nodes are visited
    if (branch.empty()) {
      // The tree is empty
      ended = true;
    } else {
      // Next visit to the parent
      branch.back().second++;
    }
  }
  // Members are protected and not private because they are used
  // by the two derived classes PrefixIterator and InfixIterator
protected:
  // Go to next internal node 
  virtual void next() {
    if (ended)
      // Visit is over
      return;
    if (branch.empty()) {
      // Start visit from the root
      tree->accept(this);
    } else {
      // Current node 
      Internal<T>* current = branch.back().first;
      switch(branch.back().second) {
      case 0:
        // Visit left subtree after first visit 
        current->getls()->accept(this);
        break;
      case 1:
        // Visit right subtree after second visit 
        current->getrs()->accept(this);
        break;
      case 2:
        // Go up after third visit
        // Remove current node from branch
        branch.pop_back();
        if (branch.empty()) {
          // Visit of the tree is over
          ended = true;
        } else {
          // Next visit of the parent
          branch.back().second++;
        }
        break;
      default:
        // Should not be used.  Just in case of a bug.
        throw exception();
      }
    }
  }
  // Visited tree
  Tree<T>* tree;
  // Becomes true when the visit is over
  bool ended;
  // Branch: sequence of nodes from the root to reach the current node
  // which is the last of the branch.
  // Each node in the branch is paired with an integer which gives
  // the number of visits to this node:
  // first visit:  0  (prefix)
  // second visit: 1  (infix)
  // third visit:  2  (suffix)
  // The pointer to the node and the number of visits are thus given by
  // branch.back().first and branch.back().second
  vector<pair<Internal<T>*, int>> branch;
};

// Prefix iterator provides values in the tree in the prefix ordering
template<class T>
class PrefixIterator : public Iterator<T> {
public:
  // Constructor
  // Since Iterator::next() moves to the first visit of an Internal node,
  // PrefixIterator::next() does not have to be called in the constructor.
  PrefixIterator(Tree<T>* t, bool end = false) : Iterator<T>(t, end) {}
private:
  // Go to next first visit of an internal node 
  virtual void next() override {
    do {
      // Call of the inherited next() method and not a recursive call
      Iterator<T>::next();
      // Fields ended and branch must be specified by this->
      // Otherwise, the compiler does not find them.
    } while (!this->ended && this->branch.back().second != 0);
  }
};

// Infix iterator provides values in the tree in the infix ordering
template<class T>
class InfixIterator : public Iterator<T> {
public:
  // Constructor
  // InfixIterator::next() must be called to move to the first second visit
  // of an internal node.
  InfixIterator(Tree<T>* t, bool end = false) : Iterator<T>(t, end) { next(); }
private:
  // Go to next second visit of an internal node 
  virtual void next() override {
    do {
      // Call of the inherited next() method and not a recursive call
      Iterator<T>::next();
      // Fields ended and branch must be specified by this->
      // Otherwise, the compiler does not find them.
    } while (!this->ended && this->branch.back().second != 1);
  }
};

#endif 
