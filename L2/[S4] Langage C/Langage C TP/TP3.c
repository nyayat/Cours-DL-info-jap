#include<stdio.h>

//1.1
void swap(int *pa, int *pb){
    int tmp=*pa;
    *pa=*pb;
    *pb=tmp;
}

//2.1
void minmax(int n, int t[], int *pmin, int *pmax){//*p=valeur, p=adresse
    if(n==0){
        *pmin=-1;
        *pmax=-1;
        return;
    }
    *pmin=0;
    *pmax=0;
    for(int i=1; i<n; i++){
        if(t[*pmax]<t[i]) *pmax=i;
        else if(t[*pmin]>t[i]) *pmin=i;
    }
}

//2.2
void occurences(int n, int t[], int e, int *pocc, int *first){
    *pocc=0;
    *first=-1;
    for(int i=0; i<n; i++){
        if(t[i]==e){
            if(*first==-1) *first=i;
            *pocc+=1;
        }
    }
}

//2.3
void occurences_bis(int n, int t[], int e, int *pocc, int **first){
    *pocc=0;
    *first=NULL;
    for(int i=0; i<n; i++){
        if(t[i]==e){
            if(*pocc==0) *first=&t[i];
            *pocc+=1;
        }
    }
}

//3.1
void sort(int t[], int start, int end){//start inclus, end exclus
    for(int max=1; max<end; max++){
        for(int i=start; i<end-max; i++){
            if(t[i]>t[i+1]) swap(&t[i], &t[i+1]);
        }
    }
}

//3.2
void sort_point(int *start, int *end){
    //sort(start, 0, end-start);
    //ou bien :
    int *t=start;
    for(int max=1; max<end-start; max++){
        for(int i=0; i<end-start-max; i++){
            if(t[i]>t[i+1]) swap(&t[i], &t[i+1]);
        }
    }
}

//3.4
void opt_sort(int *start, int *end){
    int *t=start;
    int flag=0;//1=non-trié, 0=trié
    for(int max=1; max<end-start; max++){
        for(int i=0; i<end-start-max; i++){
            if(t[i]>t[i+1]){
                swap(&t[i], &t[i+1]);
                flag=1;
            }
        }
        if(flag=0) return;
    }
}


int main(){
    //1.2
    /*int x=5;
    int y=6;
    printf("x: %d, y: %d\n", x, y);
    swap(&x, &y);
    printf("x: %d, y: %d\n", x, y);*/
    
    //1.3
    /*int t[]={0, 1};
    printf("t[0]: %d, t[1]: %d\n", t[0], t[1]);
    swap(&t[0], &t[1]);
    printf("t[0]: %d, t[1]: %d\n", t[0], t[1]);*/
    
    //1.4
    /*typedef struct{
        int x;
        int y;
    }point;
    point p={.x=5, .y=20};
    printf("x: %d, y: %d\n", p.x, p.y);
    swap(&p.x, &p.y);
    printf("x: %d, y: %d\n", p.x, p.y);*/
    
    //2.
    int t[9]={6,9,0,8,8,8,7,8,1};
    /*int min, max;
    minmax(9, t, &min, &max);//adresse en dehors du tableau
    printf("min à %d, max à %d\n", min, max);//2 et 1*/
    
    /*int occ, first;
    occurences(9, t, 8, &occ, &first);
    printf("%d occurences, première à %d\n", occ, first);//4 et 3*/
    
    /*int occ2, *first2;
    occurences_bis(9, t, 8, &occ2, &first2);
    printf("%d occurences, première à %p\n", occ2, first2);//4 et 3*/
    
    //3.
    /*sort(t, 6, 9);
    for(int i=0; i<9; i++){
        printf("%d ", t[i]);
    }*/
    
    /*sort_point(&t[0], &t[9]);
    for(int i=0; i<9; i++){
        printf("%d ", t[i]);
    }*/
    
    opt_sort(&t[0], &t[9]);
    for(int i=0; i<9; i++){
        printf("%d ", t[i]);
    }
    
    return 0;
}