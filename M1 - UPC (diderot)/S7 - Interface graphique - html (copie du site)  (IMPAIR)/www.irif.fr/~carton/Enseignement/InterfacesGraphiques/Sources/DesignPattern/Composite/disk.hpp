// -*- coding: utf-8 -*-
// Time-stamp: <disk.hpp  25 nov 2020 20:39:56>

// Disk

#ifndef DISK_HPP
#define DISK_HPP

#include <iostream>
#include "shape.hpp"

class Disk : public Shape {
public:
  // Constructor
  Disk(double r) : radius(r) {}
  // Overriding surface method
  virtual double surface() override {
    return radius * radius * 3.1415;
  }
  // Overriding draw method
  virtual void draw(double dx, double dy) override {
    std::cout << "Draw disk" << std::endl;
  }
private:
  double radius;                // Radius of the disk
};

#endif 
