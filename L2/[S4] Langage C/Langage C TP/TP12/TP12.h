#ifndef TP12_H
#define TP12_H

#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include<string.h>

typedef struct node node;
typedef node* mset;

mset newNode(int val, unsigned num);
mset addVal(int val, unsigned num, mset m);
mset build(int* values, size_t size);
void printMset(mset m, short verbose);
mset removeVal(int val, unsigned num, mset m, unsigned* numRem);
mset mplus(mset m, mset n);
mset mmoins(mset m, mset n);
mset minter(mset m, mset n);
mset munion(mset m, mset n);

#endif /* TP12_H */

