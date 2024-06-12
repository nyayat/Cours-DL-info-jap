// -*- coding: utf-8 -*-
// Time-stamp: <inherited.cpp  18 nov 2020 13:46:35> 

// Singleton template with inheritance
// The object is a static attribute.

#include <iostream>
using namespace std;

// Class with a protected constructor
// The constructor is only used by the derived class Singleton
class A {
public:
  int value() { return a; }
protected:
  A(int n = 0) : a(n) {}
private:
  int a;
};

// Template to make a singleton of any class
// The constructor must exist to construct the static object
// but it is private so only the class can use it.
template<class T>
class Singleton : public T {
public:
  // Getter of the static object  
  static Singleton& get() { return singleton; }
private:
  // This constructor calls the default constructor of the class T
  Singleton() {}                // Private Constructor
  Singleton(int n) : T(n) {}    // Private Constructor
  static Singleton singleton;   // Static object
};

// Initialization of the static object
template<class T>
Singleton<T> Singleton<T>::singleton(5);

int main() {
  // Get the singleton of class A
  Singleton<A>& singleton = Singleton<A>::get();
  A& a = Singleton<A>::get();
  // Print address
  cout << &singleton << endl;
  // Print value
  cout << singleton.value() << endl;
  cout << a.value() << endl;
}
