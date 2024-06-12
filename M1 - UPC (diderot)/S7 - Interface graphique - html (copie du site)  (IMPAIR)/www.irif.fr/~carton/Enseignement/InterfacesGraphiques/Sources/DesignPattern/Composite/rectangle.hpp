// -*- coding: utf-8 -*-
// Time-stamp: <rectangle.hpp  25 nov 2020 20:40:09>

// Rectangle

#ifndef RECTANGLE_HPP
#define RECTANGLE_HPP

#include <iostream>
#include "shape.hpp"

class Rectangle : public Shape {
public:
  // Constructor
  Rectangle(double w, double h) : width(w), height(h) {}
  // Overriding surface method
  virtual double surface() override {
    return width * height;
  }
  // Overriding draw method
  virtual void draw(double dx, double dy) override {
    std::cout << "Draw rectangle" << std::endl;
  }
private:
  double width;         // Width
  double height;        // Height
};

#endif 
