#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//1.4
typedef struct{
    int x, y;
} paire;


//1.
int main(){
    //1.1
    int a=0;
    void* pt=&a;
    
    //1.2
    *((int*)pt)=42;
    printf("%d\n", a);
    
    //1.3
    *((int*)pt)*=*((int*)pt);
    printf("%d\n", a);
    
    //1.5
    paire b={.x=0, .y=0};
    pt=&b;
    
    //1.6
    ((paire*)p)->y=12;
    printf("%d %d", b.x, b.y);
    
    return 0;
}