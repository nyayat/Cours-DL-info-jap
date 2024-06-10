#include <stdio.h>
#include <math.h>

#define DEG 6
typedef struct{
    int degre;
    int coef[DEG];
}poly;
void print_monome(int coeff, int degre );
void print_poly(poly p);

int main(int argc, char const *argv[])
{
    poly p = 
    {
        .degre = 4,
        .coef[0] = 2,
        .coef[2] = -3,
        .coef[4] = 7
        //.coef = {2, 0, -3, 0, 7, 0},
    };
    poly q;
    q.degre = 4;
    q.coef[0] = -3;
    q.coef[1] = 1;
    q.coef[3] = -6;
    q.coef[0] = -7;

    print_poly(p);

    return 0;
}

void print_monome(int coeff, int degre )
{
    printf("%+dx^%d", coeff, degre );
}
void print_poly(poly p){
    for (int i = 0; i < DEG; i++)
    {
        if(p.coef[i] != 0){
            print_monome(p.coef[i], i);
            if (i < p.degre)
            {
                printf(" + ");
            }
            
        }
    }
    

}
