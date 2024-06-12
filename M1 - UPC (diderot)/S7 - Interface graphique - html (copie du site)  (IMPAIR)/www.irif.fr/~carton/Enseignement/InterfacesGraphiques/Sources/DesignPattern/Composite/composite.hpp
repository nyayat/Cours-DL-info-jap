// -*- coding: utf-8 -*-
// Time-stamp: <composite.hpp  25 nov 2020 20:39:21>

#ifndef COMPOSITE_HPP
#define COMPOSITE_HPP

#include <iostream>

#include <vector>
#include <numeric>
#include "shape.hpp"

class Composite : public Shape {
public:
  // Add new shape
  void add(Shape* shape) {
    shapes.push_back(shape);    // Add shape to list
  }
  // Overriding surface method
  virtual double surface() override {
    // Use accumulate algorithm and a lambda
    return std::accumulate(shapes.begin(), shapes.end(), 0.0,
          [] (double x, Shape* s) -> double { return x + s->surface(); });
  }
  // Overriding draw method
  virtual void draw(double dx, double dy) override {
    // Call draw for each shape in shapes
    for (Shape* shape : shapes)
      shape->draw(dx, dy);
  }
private:
  // Note that shapes contains pointers
  std::vector<Shape*> shapes;   // Shape list
};

#endif 
