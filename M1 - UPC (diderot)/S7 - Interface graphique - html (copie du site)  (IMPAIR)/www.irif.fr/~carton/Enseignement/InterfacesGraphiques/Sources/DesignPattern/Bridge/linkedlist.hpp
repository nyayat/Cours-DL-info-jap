// -*- coding: utf-8 -*-
// Time-stamp: <linkedlist.hpp  25 mar 2020 20:01:40>

// Lists implemented by linked cells

#ifndef LINKEDLIST_HPP
#define LINKEDLIST_HPP

#include <iostream>
#include "container.hpp"

template<class T>
class LinkedList : public Container<T> {
public:
  // Constructor
  LinkedList() {}
  // No copy constructor and no copy assignment operator
  LinkedList(const LinkedList<T>&) = delete;
  LinkedList<T>& operator=(const LinkedList<T>&) = delete;
  // Empty test
  virtual bool empty() const override { return first == nullptr; };
  // Access first element
  virtual T& front() override {
    if (first != nullptr) {
      return first->value;
    } else {
      throw exception();
    }
  }
  // Access last element
  virtual T& back() override {
    if (last != nullptr) {
      return last->value;
    } else {
      throw exception();
    }
  }
  // Add element at the beginning
  virtual void pushFront(T object) override {
    // Insert new cell in front
    first = new Cell(first, nullptr, object);
    if (last == nullptr) {
      // It is the first cell to be inserted
      last = first;
    } else {
      // It becomes the previous one of the former first one
      first->next->prev = first;
    }
  }
  // Add element at the end
  virtual void pushBack(T object) override {
    // Insert new cell at the end
    last = new Cell(nullptr, last, object);
    if (first == nullptr) {
      // It is the first cell to be inserted
      first = last;
    } else {
      // It becomes the next one of the former last one
      last->prev->next = last;
    }
  }
  // Remove and return first element
  virtual T popFront() override {
    if (first != nullptr) {
      Cell* cell = first;       // Cell to be removed
      first = cell->next;       // Next cell becomes first one
      if (first == nullptr) {
        // No other cell
        last = nullptr;
      } else {
        // Still another cell
        first->prev = nullptr;
      }
      T value = cell->value;    // Save value before deleting cell
      delete cell;              // Delete cell
      return value;
    } else {
      throw exception();
    }
  }
  // Remove and return last element
  virtual T popBack() override {
    if (last != nullptr) {
      Cell* cell = last;        // Cell to be removed
      last = cell->prev;        // Previous cell becomes last one
      if (last == nullptr) {
        // No other cell
        first = nullptr;
      } else {
        // Still another cell
        last->next = nullptr;
      }
      T value = cell->value;    // Save value before deleting cell
      delete cell;              // Delete cell
      return value;
    } else {
      throw exception();
    }
  }
private:
  // Cells for double linked lists
  class Cell {
    friend class LinkedList<T>;
    // Constructor
    Cell(Cell* n, Cell* p, T v) : next(n), prev(p), value(v) {}
  private:
    Cell* next;         // Pointer to next cell
    Cell* prev;         // Pointer to previous cell
    T value;            // Value contained in the cell
  };
  Cell* first = nullptr;// Pointer to first cell in the list
  Cell* last = nullptr; // Pointer to last cell in the list
};

#endif 
