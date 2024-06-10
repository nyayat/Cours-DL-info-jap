#ifndef TP11_EX2_H
#define TP11_EX2_H

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>

typedef struct file{
    void* first;
    void* last;
    size_t te;
    void* occupe;
    void* libre;
} *fif;

fif createFifo(size_t cap, size_t taille);
void deleteFifo(fif f);
int emptyFifo(fif f);
void* getFifo(fif f, void* element);
void* putFifo(fif f, void* pelem);

#endif /* TP11_EX2_H */