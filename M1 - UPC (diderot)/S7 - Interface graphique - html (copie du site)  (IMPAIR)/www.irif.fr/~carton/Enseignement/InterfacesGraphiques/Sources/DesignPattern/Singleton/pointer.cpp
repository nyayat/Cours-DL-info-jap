// -*- coding: utf-8 -*-
// Time-stamp: <pointer.cpp  18 nov 2020 10:29:02> 

// Singleton template with a static pointer
// The object is only allocated when needed.
// The object is allocated as late as possible
// and can be initializaed with valued from the context.

#include <iostream>
using namespace std;

// Class with a default constructor
class A {
public:
  int value() { return a; }
private:
  int a = 1;
};

// Template to make a singleton of any class
// The constructor is deleted because no object of this class is created
// The class has a static pointer to an object of class T
template<class T>
class Singleton {
public:
  // Getter of the static object  
  static T& get();
private:
  Singleton() = delete;		// Pas de constructor
  static T* pointer;            // Pointer to the T object
};

// Getter
template<class T>
T& Singleton<T>::get() {
  // Allocate objet if needed
  if (pointer == nullptr) {
    // Allocate
    pointer = new T();
  }
  // Return object by reference
  return *pointer;
}

// Initialization of the static pointer
template<class T>
T* Singleton<T>::pointer = nullptr;

int main() {
  // Get the singleton of class A
  A& a = Singleton<A>::get();
  // Print address
  cout << &a << endl;
  // Print value
  cout << a.value() << endl;
}
