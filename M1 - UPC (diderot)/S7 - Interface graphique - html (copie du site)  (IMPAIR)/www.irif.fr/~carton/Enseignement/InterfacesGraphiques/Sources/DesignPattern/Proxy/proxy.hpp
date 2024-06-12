// -*- coding: utf-8 -*-
// Time-stamp: <proxy.hpp  26 nov 2021 10:11:34>

// Proxy deferring vector creation

#ifndef PROXY_HPP
#define PROXY_HPP
#include <vector>

template<class T>
class Proxy {
public:
  bool empty() { return v == nullptr || v->empty(); }
  void push_back(T t) {
    if (v == nullptr)
      v = new vector<T>();
    v->push_back(t);
  }
  T& operator[](int i) {
    if (v == nullptr)
      v = new vector<T>();
    return (*v)[i];
  }
  ~Proxy() { if (v != nullptr) delete v; }
private:
  vector<T>* v = nullptr;
};

#endif 
