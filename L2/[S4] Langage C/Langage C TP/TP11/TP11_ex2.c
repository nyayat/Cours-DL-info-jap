#include "TP11_ex2.h"

//2.1
static void* decale(void* f, ssize_t d){
    return f+d;
}

//2.2
fif createFifo(size_t cap, size_t taille){
    fif res;
    res->te=taille;
    res->first=malloc(sizeof(void*));
    if(res->first==NULL) return NULL;
    res->last=decale(res->first, cap*taille);
    res->libre=res->first;
    res->occupe=res->first;
    return res;
}

//2.3
void deleteFifo(fif f){
    free(f->first);
    free(f->last);
    free(f->libre);
    free(f->occupe);
}

//2.4
int emptyFifo(fif f){
    if(f->first==f->libre) return 1;
    return 0;
}

//2.5
void* getFifo(fif f, void* element){
    if(emptyFifo(f)) return NULL;
    memmove(element, f->occupe, f->te);
    f->occupe=decale(f->occupe, f->te);
    return element;
}

//2.6
static void* putFifoNoShift(fif f, void* pelem){
    if(f->last==f->libre) return NULL;
    void* res=memcpy(f->libre, &pelem, f->te);
    f->libre=decale(f->libre, f->te);
    return res;
}

//2.7
void* putFifo(fif f, void* pelem){
    void* res=putFifoNoShift(f, pelem);
    if(res==NULL){
        int diff=((int)(f->last))-((int)(f->first));
        if((((int)(f->libre))-((int)(f->occupe)))>diff*0.75){
            void* tmp=realloc(f->last, ((size_t)diff)*2);
            assert(tmp!=NULL);
            f->last=tmp;
        }
        else memmove(f->first, f->occupe, ((int)f->occupe-(int)f->first)*f->te);
        return putFifoNoShift(f, pelem);
    }
    return res;
}