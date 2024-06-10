#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include <string.h>

#define affiche_nbr(a) printf("%p (%lu)\n", a, (unsigned long)a)

typedef struct pileAmortie{
    int occupation;
    int capacite;
    int* elements;
} pileAmortie;

//1.1
pileAmortie* allouePileAmortie(){
    pileAmortie* res=malloc(sizeof(pileAmortie));
    if(res==NULL) return NULL;
    res->elements=malloc(sizeof(int));
    if(res->elements==NULL){
        free(res);
        return NULL;
    }
    res->capacite=1;
    res->occupation=0;
    return res;
}

//1.2
void liberePileAmortie(pileAmortie* pile){
    free(pile->elements);
    free(pile);
}

//1.3
int empilePileAmortie(pileAmortie * pile, int n){
    assert(pile!=NULL);
    if(pile->occupation==pile->capacite){
        int* p=realloc(pile->elements, sizeof(int)*2*pile->capacite);
        if(p==NULL) return -1;
        pile->elements=p;
        pile->capacite*=2;
    }
    pile->elements[(pile->occupation)++]=n;
    return 0;
}

//1.4
int depilePileAmortie(pileAmortie* pile, int* e){
    assert(pile!=NULL);
    if(pile->occupation==0) return -1;
    int toDel=pile->elements[--(pile->occupation)];
    if(pile->capacite>=2 && 4*pile->occupation<=pile->capacite){
        int* p=realloc(pile->elements, sizeof(int)*pile->capacite/2);
        if(pile->elements==NULL){
            pile->occupation++;
            return -1;
        }
        pile->elements=p;
        pile->capacite/=2;
    }
    *e=toDel;
    return 0;
}

//1.5
pileAmortie* copiePileAmortie(pileAmortie* pile){
    pileAmortie* res=malloc(sizeof(pileAmortie));
    int *e=malloc(pile->capacite*sizeof(int));
    if(res==NULL || e==NULL) return NULL;
    memcpy(e, pile->elements, pile->capacite*sizeof(int));
    res->capacite=pile->capacite;
    res->occupation=pile->occupation;
    res->elements=e;
    return res;
}

//1.6
void printPile(pileAmortie* pile){
    assert(pile!=NULL);
    if(pile->occupation==0) printf("vide\n");
    else{
        for(int i=0; i<pile->occupation; i++) printf("%d ", pile->elements[i]);
        printf("\n");
    }
}

//2.1
char c1, c2;
int i1, i2;
int* p1, p2;

//2.3
void f1(int* p){
    int i=7;
    char c='A';
    printf("%p\n", p);
    printf("%d\n", p[0]);
    affiche_nbr(&p);
    
    printf("%d\n", i);
    affiche_nbr(&i);
    
    printf("%c\n", c);
    affiche_nbr(&c);
    
    p[0]=0;
    p=NULL;
}

//2.5
void f2(int* p){
    int i;
    char c;
    
    affiche_nbr(&p);
    
    printf("%d\n", i);
    affiche_nbr(&i);
    
    printf("%c\n", c);
    affiche_nbr(&c);
    
    //2.6
    f1(p);
}

int main(){
    //1.
    /*int v;
    pileAmortie* p=allouePileAmortie();
    printPile(p);//vide
    printf("\n");
    
    for(int i=0;i<100;i++) empilePileAmortie(p, i);
    printPile(p);//0 a 99
    printf("\n");
    
    pileAmortie* p2=copiePileAmortie(p);
    printPile(p2);//0 a 99
    printf("\n");
    
    
    for(int i=0;i<100;i++){
        depilePileAmortie(p, &v);
        printf("%d ", v);//99 a 0
    }
    printf("\n");
    printf("\n");
    
    printPile(p2);//0 a 99
    printf("\n");
    
    printPile(p);//vide
    printf("\n");
    
    liberePileAmortie(p);//rien
    printPile(p);*/
    
    //2.1
    char cm1, cm2;
    int im1, im2;
    int* pm1, pm2;
    
    /*affiche_nbr(&c1);//4227192
    affiche_nbr(&c2);//4227193
    affiche_nbr(&i1);//4227188
    affiche_nbr(&i2);//4227200
    affiche_nbr(&p1);//4227196
    affiche_nbr(&p2);//4227184
    printf("\n");
    affiche_nbr(&cm1);//6422303
    affiche_nbr(&cm2);//6422302
    affiche_nbr(&im1);//6422296
    affiche_nbr(&im2);//6422292
    affiche_nbr(&pm1);//6422288
    affiche_nbr(&pm2);//6422284
    printf("\n");
    
    //2.2
    printf("%zu\n", sizeof(int));//4
    printf("%zu\n", sizeof(char));//1
    printf("%zu\n", sizeof(int*));//4
    printf("\n");*/
    
    //2.4
    /*p1=&i1;//6422288 -> pm1 ; 6422268 ; 6422267
    f1(p1);
    printf("%d\n", p1[0]);//0
    affiche_nbr(&p1);//4223100
    
    f2(p1);//6422288 -> pm1 ; 6422268 ; 6422267
    //meme adresse de variables locales et pointeur*/
    
    affiche_nbr(malloc(1));//11736472
    affiche_nbr(malloc(2));//11736504
    affiche_nbr(malloc(25));//11741720
    affiche_nbr(malloc(26));//11741800
    affiche_nbr(malloc(1));//11741880
    
    return 0;
}