#include "TP10.h"

int main(){
    buffer* pb=allocBuffer(10);
    for(int i=0; i<10; i++) pb->content[i]=i;
    printBuffer(pb);
    // 0 1 2 3 4 5 6 7 8 9
    
    // tests exercices 3 et 4
    writeBuffer(pb, "save.dat");
    free(pb);
    pb=readBuffer("save.dat");
    printBuffer(pb);
    // 0 1 2 3 4 5 6 7 8 9
    
    // test exercice 5
    appendBuffer(pb, "save.dat");
    free(pb);
    pb=readBuffer("save.dat");
    printBuffer(pb);
    // 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
    free(pb);
    
    return 0;
}