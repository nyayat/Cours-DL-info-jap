#include "TP10.h"

//1.
void printBuffer(buffer* pb){
    for(int i=0; i<pb->size; i++) printf("%d ", pb->content[i]);
    printf("\n");
}

//2.
buffer* allocBuffer(size_t size){
    buffer* res=malloc(sizeof(buffer)+sizeof(int)*size);
    assert(res!=NULL);
    res->size=size;
    return res;
}

//3.
void writeBuffer(buffer* pb, const char* fileName){
    FILE* f=fopen(fileName, "w");
    assert(f!=NULL);
    fwrite(pb, sizeof(buffer)+pb->size*sizeof(int), 1, f);
    fclose(f);
}

//4.
buffer* readBuffer(const char* fileName){
    FILE* f=fopen(fileName, "r");
    assert(f!=NULL);
    buffer* res=malloc(sizeof(buffer));
    assert(res!=NULL);
    
    fread(res, sizeof(buffer), 1, f);
    res=realloc(res, sizeof(buffer)+sizeof(int)*res->size);
    assert(res!=NULL);
    
    fread(res->content, sizeof(int), res->size, f);
    fclose(f);
    return res;
}

//5.
void appendBuffer(buffer* pb, const char* fileName){
    FILE* f=fopen(fileName, "r+");
    assert(f!=NULL);
    
    buffer tmp;
    fread(&tmp, sizeof(buffer), 1, f);
    tmp.size+=pb->size;
    
    rewind(f);
    fwrite(&tmp, sizeof(buffer), 1, f);
    
    fseek(f, 0, SEEK_END);
    fwrite(pb->content, sizeof(int), pb->size, f);
    fclose(f);
}