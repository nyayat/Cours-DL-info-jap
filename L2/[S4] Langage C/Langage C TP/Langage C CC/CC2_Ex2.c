//DAI Anna 21953144 Info-1
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

typedef struct{
    int x;
    int y;
} point;

//3.
void translate(point* p, int dist, char dir){
    assert(p!=NULL);
    if(dir=='x') p->x=(p->x)+dist;
    else if(dir=='y') p->y=(p->y)+dist;
}

//4.
void translate_tab(int len, point v[], int dist, char dir){
    for(int i=0; i<len; i++){
        translate(&v[i], dist, dir);
    }
}

//5.
void afficher_point(point q){
    printf("(%d,%d)\n", q.x, q.y);
}

int main(){
    point v[3]={{.x=1, .y=1}, {.x=-1, .y=-1}, {.x=1, .y=-1}};
    for(int i=0; i<3; i++) afficher_point(v[i]);//(1,1) (-1,-1) (1,-1)
    
    translate(&v[0], 10, 'x');
    translate_tab(3, &v[1], -50, 'y');
    for(int i=0; i<3; i++) afficher_point(v[i]);//(11,1) (-1, -51) (1, -51)
    
    return 0;
}