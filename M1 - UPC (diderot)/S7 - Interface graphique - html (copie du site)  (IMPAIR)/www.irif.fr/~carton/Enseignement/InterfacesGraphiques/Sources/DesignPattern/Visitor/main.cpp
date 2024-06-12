// -*- coding: utf-8 -*-
// Time-stamp: <main.cpp   2 déc 2020 09:42:38> 

#include <iostream>
#include <string>
using namespace std;

#include "tree.hpp"
#include "void.hpp"
#include "copy.hpp"
#include "iterator.hpp"
#include "builder.hpp"

int main(int argc, char* argv[]) {
  // Trees with int values
  Builder<int> builder0;
  Tree<int> tree0 = builder0.build("1 (2 (4, 5), 3 (6, 7))");
  for (PrefixIterator<int> it(&tree0); it.hasNext(); ++it)
    std::cout << *it << ' ';
  std::cout << std::endl;
  for (InfixIterator<int> it(&tree0); it.hasNext(); ++it)
    std::cout << *it << ' ';
  std::cout << std::endl;
  Tree<int> tree1 = Copy<int>().visit(&tree0);
  for (PrefixIterator<int> it(&tree1); it.hasNext(); ++it)
    std::cout << *it << ' ';
  std::cout << std::endl;
  VoidVisitor<int> visitor;
  visitor.visit(&tree0);
  visitor.visit(&tree1);
  
  // Trees with string values
  Builder<std::string> builder1;
  Tree<std::string> tree2 = builder1.build("x (a (b, c),d (e, f (g, h)))");
  for (PrefixIterator<std::string> it(&tree2); it.hasNext(); ++it)
    std::cout << *it << ' ';
  std::cout << std::endl;
}
