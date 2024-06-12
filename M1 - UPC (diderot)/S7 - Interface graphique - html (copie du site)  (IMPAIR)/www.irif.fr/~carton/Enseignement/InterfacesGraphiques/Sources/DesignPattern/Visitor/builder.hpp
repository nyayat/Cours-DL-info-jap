// -*- coding: utf-8 -*-
// Time-stamp: <builder.hpp   1 déc 2021 10:07:31>

#ifndef BUILDER_HPP
#define BUILDER_HPP

#include <string>
#include <iostream>
#include "tree.hpp"

template<class T>
class BuilderBase {
public:
  Tree<T> build(std::string string = "") const {
    try {
      // Build the nodes
      return Tree<T>(buildnodes(string));
    }
    catch(std::exception& e) {
      std::cerr << "Invalid tree : '" << string << '\'' << std::endl;
      exit(1);
    }
  }
private:
  // Method to parse a value of type T
  // This method should be implemented for each type T
  virtual T parse(std::string string) const = 0;
  // Build nodes recursively
  Node<T>* buildnodes(std::string string) const;
};

// Method buildnodes of BuilderBase class
template<class T>
Node<T>* BuilderBase<T>::buildnodes(std::string string) const {
  // std::cout << "buildnodes : '" << string << "'" << std::endl;
  // Check if string is only made of spaces, tab, ...
  if (string.find_first_not_of(" \t\n\v\f\r") == std::string::npos) {
    // If empty then just return Leaf
    return new Leaf<T>();
  } else {
    // Look for first occurrence of '('
    int left = string.find_first_of('(');
    // Look for last  occurrence of ')'
    int right = string.find_last_of(')');
    // If no '(' and no ')', just return internal node with the parsed value
    if (left == std::string::npos && right  == std::string::npos) {
      return new Internal<T>(parse(string), new Leaf<T>(), new Leaf<T>());
    }
    // If '(' and ')' occurs and that '(' is before ')'
    if (left == std::string::npos ||
	right == std::string::npos || left > right)
      throw std::exception();
    // Look for the first occurrence of ',' such that
    // between left and that position
    // the number of occurrences of '(' is equal to the number of ')'
    int diff = 0;		// Difference between numbers of '(' and ')'
    int i;			// i is declared out of the for loop
    // Start qt left+1 not to take into account the '(' at left
    for (i = left+1; i < right && (string[i] != ',' || diff != 0); i++) {
      if (string[i] == '(')
	diff++;
      if (string[i] == ')')
	diff--;
    }
    // Check that ',' has been found
    int middle;
    if (string[i] == ',' && diff == 0) {
      middle = i;
    } else {
      throw std::exception();
    }
    T value = parse(string.substr(0, left));
    Node<T>* ls = buildnodes(string.substr(left+1, middle-left-1));
    Node<T>* rs = buildnodes(string.substr(middle+1, right-middle-1));
    return new Internal<T>(value, ls, rs);
  }
}

// Class Builder has to be declared to define specializations
template<class T>
class Builder;

// Specialization for T = int of Builder class
template<>
class Builder<int> : public BuilderBase<int> {
private:
  int parse(std::string string) const override { return std::stoi(string); }
};

// Specialization for T = string of Builder class
template<>
class Builder<std::string> : public BuilderBase<std::string> {
private:
  std::string parse(std::string string) const override { return string; }
};

#endif 
