#include <stdio.h>
#include <math.h>
void aff_tab(int tab[], int taille);
int maxim(int tab[], int taille);

int main(int argc, char const *argv[])
{
    int m = 10;
    int n = 15;
    int tab[m];
    srand(time(NULL));
    for (int i = 0; i <m ; i++)
    {
        tab[i] = rand()%n;
    }
    aff_tab(tab, sizeof(tab)/sizeof(tab[0]));
    maxim (tab, sizeof(tab)/sizeof(tab[0]));
    return 0;
}


void aff_tab(int tab[], int taille){
    for (int i = 0; i < taille; i++)
    {
        printf("indice :%d , contenu : %d \n", i, tab[i]);
        /* code */
    }
    
}

int maxim (int tab[], int taille){
    int max = -1;
    for (int i = 0; i < taille; i++)
    {
        if(tab[i] > tab[max]) max = i;
        /* code */
    }
    printf("indice : %d", max);
    return max;
    
}