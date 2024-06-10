#include "TP11_ex2.h"

int main(){
    fif f=createFifo(1, sizeof(int));
    printf("%p %p\n", f->first, f->last);
    printf("%p %p\n\n", f->libre, f->occupe);
    //printf("%d\n", emptyFifo(f));
    putFifo(f, (void*)9);
    putFifo(f, (void*)8);
    putFifo(f, (void*)7);
    int* tmp;
    tmp=getFifo(f, (void*)tmp);
    //printf("%d\n", *tmp);
    
    printf("\n%p %p\n", f->first, f->last);
    printf("%p %p\n", f->libre, f->occupe);
    
    return 0;
}