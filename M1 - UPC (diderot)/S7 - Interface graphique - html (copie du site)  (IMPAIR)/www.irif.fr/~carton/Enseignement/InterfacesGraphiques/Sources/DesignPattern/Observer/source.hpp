// -*- coding: utf-8 -*-
// Time-stamp: <source.hpp  25 mar 2020 11:12:58>

#ifndef SOURCE_HPP
#define SOURCE_HPP

#include <iostream>
#include <vector>
#include "observer.hpp"

class Source : public Observer {
public:
  // Constructor 
  Source(int id) : id(id) {}
  // Register new observer
  void regist(Observer* observer) {
    observers.push_back(observer); 	// Add observer to list
  }
  // Update and notify observers by calling their update method
  void update(int event) {
    std::cout << "Source " << id << " sends event " << event << std::endl;
    for (Observer* observer : observers)
      observer->update(event);
  }
private:
  // Note that observers contains pointers
  std::vector<Observer*> observers;	// Observer list
  int id;			// Identifier 
};

#endif 
