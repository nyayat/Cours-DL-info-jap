#ifndef TP10_H
#define TP10_H


#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

typedef struct{
    size_t size;
    int content[];
} buffer;

void printBuffer(buffer* pb);
buffer* allocBuffer(size_t size);
void writeBuffer(buffer* pb, const char* fileName);
buffer* readBuffer(const char* fileName);
void appendBuffer(buffer* pb, const char* fileName);


#endif /* TP10_H */

