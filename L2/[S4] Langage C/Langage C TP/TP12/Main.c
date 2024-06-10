#include "TP12.h"

int main(){
    int* values=malloc(3*sizeof(int));
    assert(values!=NULL);
    int t[3]={0, 0, 2};
    values=&t[0];
    
    mset set1=build(values, 3);
    printMset(set1, 0);
    
    /*unsigned* nb=malloc(sizeof(unsigned));
    set1=removeVal(0, 2, set1, nb);
    printMset(set1, 0);
    printf("%d\n", *nb);
    
    set1=removeVal(2, 1, set1, nb);
    printMset(set1, 0);
    printf("%d\n", *nb);*/
    
    int* values2=malloc(3*sizeof(int));
    assert(values2!=NULL);
    int t2[3]={2, 1, 2};
    values2=&t2[0];
    
    mset set2=build(values2, 3);
    printMset(set2, 0);
    
    mset plus=mplus(set1, set2);
    printMset(plus, 0);
    
    mset moins=mmoins(set1, set2);
    printMset(moins, 0);
    
    mset inter=minter(set1, set2);
    printMset(inter, 0);
    
    mset un=munion(set1, set2);
    printMset(un, 0);
    
    //5.
    mset (*operation)(mset, mset);
    operation=minter;
    mset op=operation(set1, set2);
    printMset(op, 0);
    
    return 0;
}