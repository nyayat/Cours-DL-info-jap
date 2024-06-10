#include <stdio.h>
#include <math.h>

void stat(int m, int n);

int main(int argc, char const *argv[])
{
    srand(time(NULL));
/*
    int num = rand();
    if(num%2==0){
        printf("Le nombre %d est paire", num);
    }
    else{
        printf("Le nombre %d est impair", num);
    }
*/
    stat(10,20);

    return 0;
}

void stat(int m, int n){
    int x, i, j;
    if( m > n){
        printf("Erreur : m > n\n");
        return;
    }
    
    i = j = 0;
    x = rand() % (n + m/2);
    do{
        if(x < m){
            i++;
        }
        else {
            j++;
        }
    x = rand() % (n + m/2);  
    }
    while(x <= n);
    
    if (i + j == 0){
        printf("Aucun nombre aléatoire tiré\n");
    }
    else{
        printf("%d nombres aléatoires ont été tirés, %d sont dans l'intervalle [0, %d] (soit %.2f %%) et %d dans l'intervalle [%d, %d] (soit %.2f %%).\n", i + j, i, m, 100. * i / (i + j), j, m , n, 100. * j / (i + j));
    }

    return;



    
}
