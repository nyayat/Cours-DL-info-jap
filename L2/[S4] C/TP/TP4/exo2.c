#include<stdio.h>
#include<stdlib.h>

void affiche (int t[], int a, int b);
int *build_tab (int a, int n);

/*
//main exo 1 : pb d'execution
int main () {
    int t[] = {1, 2, 3, 4};
    affiche (t, 0, 4);
    affiche (t, 0, 6);
    affiche (t, -2, 4);
    return 0;
}
*/
int main (void) {
    printf ("\nbuild tab\n");
    int *tab = build_tab (0, 15);
    printf ("tab : %p\n", tab);
    //affiche (tab, 0, 15);    // SEG_FAULT!
    return 0;
}

void affiche (int t[], int a, int b) {
    for (int i = a; i < b - 1; i++) {
        printf ("%d, ", t[i]);
    }
    printf("%d\n", t[b - 1]);    
}

int *build_tab (int a, int n) {
    int t[n];
    for (int i = 0; i < n; i++) {
        t[i] = a + i;
    }
    printf  ("t : %p : ", t);// TEST
    affiche (t, 0, 15);// TEST
    return t;// WARNING!
}