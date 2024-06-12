// -*- coding: utf-8 -*-
// Time-stamp: <shape.hpp  25 mar 2020 09:33:36>

#ifndef SHAPE_HPP
#define SHAPE_HPP

class Shape {
public:
  // Surface
  virtual double surface() = 0;
  // Draw the shape
  virtual void draw(double dx, double dy) = 0;
  // Virtual destructor is needed
  virtual ~Shape() {}
};

#endif 
