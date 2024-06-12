// -*- coding: utf-8 -*-
// Time-stamp: <composed.cpp  18 nov 2020 18:07:04> 

// Singleton template with composition

#include <iostream>
using namespace std;

template<class T>
class Singleton;

// Class with a private constructor
// This constructor can only be used by the friend class Singleton
class A {
  // Allow Singleton class to use private constructor
  template<class T> friend class Singleton;
public:
  // Getter of the value
  int value() { return a; }
private:
  // Private constructor
  A(int n = 1) : a(n) {}
  int a;
};

// Template to make a singleton of any class
template<class T>
class Singleton {
public:
  // Getter of the static object  
  static T& get() { return singleton.value; }
private:
  // This constructor calls the default constructor of the class A
  Singleton() {}                // Private Constructor
  T value;                      // Contained object of type T
  static Singleton singleton;   // Static object
};

// Initialization of the static object
template<class T> Singleton<T> Singleton<T>::singleton;

int main() {
  // Get the singleton of class A
  A& singleton = Singleton<A>::get();
  // Print address
  cout << &singleton << endl;
  // Print value
  cout << singleton.value() << endl;
}
