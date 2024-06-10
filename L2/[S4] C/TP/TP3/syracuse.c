#include <stdio.h>
int syracuse(int n);
void Collatz(int m);

int main(int argc, char const *argv[])
{
    //syracuse(27);
    Collatz(10);

    return 0;
}

int syracuse(int n){
    int m = n;
    int compteur = 0;
    while(n != 1){
        //printf("%d \n", n);
        if( n%2==0 ) n = n/2;
        else n = (3*n)+1;
        compteur+=1;
    }
    printf("%d : %d \n", m, compteur);
    return compteur;
}

void Collatz(int m){
    for (int i = 1; i < m+1; i++)
    {
        syracuse(i);
    }
    
}


