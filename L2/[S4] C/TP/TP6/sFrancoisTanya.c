/*
FRANCOIS Tanya
21952540
Groupe 1 (info - japonais)
*/
#include <stdio.h>

typedef struct{
    int x;
    int y;
} point;

void translate( point *p, int dist, char dir);
void afficher_point( point q );
void translate_tab( int len, point v[], int dist, char dir);

int main(int argc, char const *argv[])
{
    /*point p1 = { .x = 7, .y = 13 };
    point p2 = { .x = -7, .y = -13 };
    point p3 = { .x = 7, .y = -13 };
    point tab[3] = { p1, p2, p3};
    */


    point tab[3] = { 
        { .x = 7, .y = 13 },
        { .x = -7, .y = -13 },
        { .x = 7, .y = -13 }
    };

    for (int i = 0; i < 3; i++)
    {
        afficher_point(tab[i]);
    }
    printf("\n");
    
    translate(&tab[0], 10, 'x');
    translate_tab(2, &tab[1], -50, 'y');

    for (int i = 0; i < 3; i++)
    {
        afficher_point(tab[i]);
    }
    
    

    return 0;
}



void translate( point *p, int dist, char dir){
    if(dir == 'x'){
        p -> x += dist;
    }
    else if (dir == 'y'){
        p -> y += dist;
    }

}


void translate_tab( int len, point v[], int dist, char dir){
    for (int i = 0; i < len; i++)
    {
        
        translate(&v[i], dist, dir);
    }

}

void afficher_point( point q ){
    printf( "(%d,%d)\n" , q.x, q.y );
}