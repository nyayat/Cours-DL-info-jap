// -*- coding: utf-8 -*-
// Time-stamp: <main.cpp  25 nov 2020 20:59:47> 

#include <iostream>
#include <string>
#include <vector>
using namespace std;

#include "selection.hpp"
#include "insertion.hpp"
#include "quicksort.hpp"

int main(int argc, char* argv[]) {
  int size = argc-1;
  // Fill array with command line arguments
  string* array = new string[size];
  for (int i = 0; i < size; i++)
    array[i] = argv[i+1];
  // Sort array
  // Sorter<string>* sorter = new Insertion<string>();
  // Sorter<string>* sorter = new Selection<string>();
  Sorter<string>* sorter = new Quicksort<string>();
  sorter->sort(array, array+size);
  // Print array
  for (int i = 0; i < size; i++)
    cout << array[i] << ' ';
  cout << endl;
}
