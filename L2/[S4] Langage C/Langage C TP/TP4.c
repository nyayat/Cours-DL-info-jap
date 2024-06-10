#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

//2.1
void affiche(int t[], int a, int b){
    for(int i=a; i<b-1; i++) printf("%d, ", t[i]);
    printf("%d\n", t[b-1]);
}

//2.2
/*int* build_tab1(int a, int n){
    int t[n];
    for(int i=0; i<n; i++) t[i]=a+i;
    printf("t : %p : ", t); // TEST
    affiche(t, 0, 15); // TEST
    return t; // WARNING! return adress of local variable (qui disparaît)
}*/

//2.3
int* build_tab2(int a, int n){
    int* t;
    t=malloc(n*sizeof(int));  
    for(int i=0; i<n; i++) t[i]=a+i;
    printf("t : %p : ", t); // TEST
    affiche(t, 0, 15); // TEST
    return t;
}

//2.4
int build_tab3(int a, int n){
    int* t;
    t=malloc(n*sizeof(int));
    if(t==NULL) return -1;
    for(int i=0; i<n; i++) t[i]=a+i;
    printf("t : %p : ", t); // TEST
    affiche(t, 0, 15); // TEST
    return 0;
}

//3.
typedef struct array{
    int* ptr;
    int size;
}array;

//3.1
void array_print(array* t){
    for (int i=0; i<(*t).size; i++) printf("%d ", t->ptr[i]);
    printf("\n");
}

//3.2
array* array_init(int n){
    array* res;
    res=malloc(sizeof(array));
    if(res==NULL){
        perror("malloc");
        exit(1);
    }    
    int* t=malloc(n*sizeof(int));
    if(t==NULL){
        perror("malloc");
        exit(1);
    }
    for(int i=0; i<n; i++) t[i]=1+rand()%10;
    res->size=n;
    res->ptr=t;
    return res;
}

//3.3
void array_destroy(array* t){
    free(t->ptr);
}

//3.4
int array_get(array* t, int index){
    assert(index<(*t).size && index>=0);
    return t->ptr[index];
}

void array_set(array* t, int index, int valeur){
    assert(index<t->size && index>=0);
    t->ptr[index]=valeur;
}

//3.5
int array_insert(array* t, int index, int valeur){
    assert(index<=(*t).size && index>=0);
    int* tmp=malloc((t->size+1)*sizeof(int));
    if(tmp==NULL){
        perror("malloc");
        exit(0);
    }
    for(int i=0; i<index; i++) tmp[i]=t->ptr[i];
    tmp[index]=valeur;
    for(int i=index; i<t->size; i++) tmp[i+1]=t->ptr[i];
    free(t->ptr);
    t->ptr=tmp;
    t->size++;
    return 1;
}

//3.6
void array_erase(array* t, int index){
    assert(index<(*t).size && index>=0);
    for(int i=index; i<t->size-1; i++) t->ptr[i]=t->ptr[i+1];
    t->size--;
    t->ptr=realloc(t->ptr, t->size);
}

int main(){
    //1.1
    /*int t[]={1, 2, 3}, *pt;
    pt=t;*/
     //ok
        
    /*int t[3]={1, 2, 3}, *pt;
    pt=&t[0];*/
    //ok
    
    /*int t[]={1, 2, 3}, *pt;
    t=pt;*/
    //erreur de compilation, affectation à un tableau
    
    //1.2
    /*int t[3]={1, 2, 3}, *pt;
    pt=t+1;*/
    //ok, pt contient 2 et 3 + des nombres étranges
    
    /*int t[3]={1, 2, 3}, *pt;
    pt=&t[1];*/
    //idem que précédent
    
    //1.3
    /*int t[3], *pt;
    pt=malloc(5*sizeof(int));
    pt=t;*/
    //ok
    
    //int t[5]=malloc(5*sizeof(int));
    //erreur de compilation, pas de tableau avec malloc
    
    //1.4
    /*int* pt;
    pt=malloc(5*sizeof(int));
    *pt=10;
    *(pt+1)=20;
    *(pt+12)=30;*/
    //ok
    
    /*int* pt;
    pt=malloc (5*sizeof(int));
    pt[0]=10;
    pt[1]=20;
    pt[12]=30;*/
    //idem, ok
    
    //2.1
    //int t[]={1, 2, 3, 4};
    //affiche(t, 0, 4);//ok
    //affiche(t, 0, 6);//ok, mais dépasse tableau donc nombres étranges
    //affiche(t, -2, 4);//idem
    
    //2.2
    /*printf("\nbuild tab\n");
    //int *tab=build_tab1(0, 15);//variable locale qui disparaît après fin d'exécution
    int *tab=build_tab2(0, 15);//ok
    printf("tab : %p\n", tab);
    affiche(tab, 0, 15);*/
    
    
    //3.
    array* t=array_init(10);
    array_print(t); // 10 valeurs aleatoires, mais exactement 10.
    printf("\n");
    
    for(int i=0; i<t->size; i++) array_set(t, i, i);
    array_print(t);
    printf("\n");// 0 1 2 3 4 5 6 7 8 9
    
    for(int i=0; i<t->size; i++) printf ("%d ", array_get (t, i));
    printf("\n");// 0 1 2 3 4 5 6 7 8 9
    
    array_insert(t, 3, 42);
    array_print(t);
    printf("\n");// 0 1 2 42 3 4 5 6 7 8 9

    array_insert(t, 11, 43);
    array_print(t);
    printf("\n");// 0 1 2 42 3 4 5 6 7 8 9 43

    array_erase(t, 11);
    array_print(t);
    printf("\n");// 0 1 2 42 3 4 5 6 7 8 9

    array_erase(t, 3);
    array_print(t);
    printf("\n");// 0 1 2 3 4 5 6 7 8 9*/

    array_erase(t, 0);
    array_print(t);
    printf("\n");// 1 2 3 4 5 6 7 8 9*/
    
    return 0;
}