#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include<math.h>

//1.
int g(int n);
int f(int n);

int f(int n){
    if(n==1) return 2;
    return g(n-1)*2;
}

int g(int n){
    if(n==1) return 1;
    return f(n/2)*3;
}

//2.
typedef enum {VALIDEE, ENCOURS, EXPEDIEE} etat;

typedef struct comm{
    int numeroC;
    int prix_exp;
    int prix_prod;
    etat etatC;
} commande;

//2.1
commande comAlea(int num){
    int exp=rand()%20;
    int prod=rand()%2000;
    etat et=rand()%3;
    commande res={.numeroC=num, .prix_exp=exp, .prix_prod=prod, .etatC=et};
    return res;
}

//2.3
void affCommande(commande c){
    printf("numéro : %d, expédition : %d€, produit : %d€, état : ",
            c.numeroC, c.prix_exp, c.prix_prod);
    switch((int)c.etatC){
        case 0:
            printf("validée\n");
            break;
        case 1:
            printf("en cours\n");
            break;
        case 2:
            printf("expédiée\n");
            break;
    }
}

//2.4
void affExpediees(commande T[], int taille){
    for(int i=0; i<taille; i++){
        if(T[i].etatC==EXPEDIEE) affCommande(T[i]);
    }
}

//2.5
int nbCEC(commande T[], int taille){
    int res=0;
    for(int i=0; i<taille; i++){
        if(T[i].etatC==ENCOURS) res++;
    }
    return res;
}

//2.6
int coutVal(commande T[], int taille){
    int res=0;
    for(int i=0; i<taille; i++){
        if(T[i].etatC==VALIDEE) res+=T[i].prix_exp;
    }
    return res;
}

//3.1
typedef struct frac{
    long int num;
    long int den;
}fraction;

//3.2 & 3.3
fraction build(long int n, long int d){
    assert(d!=0);
    fraction res={.num=n, .den=d};
    return res;
}

void printFrac(fraction f){
    printf("%d/%d\n", f.num, f.den);
}

//3.4
int eq(fraction f, fraction g){
    return (f.num*g.den==f.den*g.num)?1:0;
}

//3.5
int isInteger(fraction f){
    return (f.num%f.den==0)?1:0;
}

//3.6
fraction sum(fraction f, fraction g){
    int newNum=f.num*g.den+g.num*f.den;
    int newDen=f.den*g.den;
    fraction res={.num=newNum, .den=newDen};
    return res;
}

fraction sub(fraction f, fraction g){
    int newNum=f.num*g.den-g.num*f.den;
    int newDen=f.den*g.den;
    fraction res={.num=newNum, .den=newDen};
    return res;
}

fraction mul(fraction f, fraction g){
    int newNum=f.num*g.num;
    int newDen=f.den*g.den;
    fraction res={.num=newNum, .den=newDen};
    return res;
}

//3.7
long pgcd(long a, long b){
    long sign=((a<0 && b>0) || (a>0 && b<0))?-1:1;
    int x=(a<0)?-a:a;
    int y=(b<0)?-b:b;
    int r;
    while(y!=0){
        r=x%y;
        x=y;
        y=r;
    }
    return sign*x;
}

fraction reduce(fraction f){
    long div=pgcd(f.num, f.den);
    long sign=(div<0)?-1:1;//on récupère le signe final de la fraction
    if(sign<0) div*=-1;//pgcd positif
    if(f.num<0) f.num*=-1;//numérateur positif
    if(f.den<0) f.den*=-1;//dénominateur positif
    long newNum=sign*(f.num/div);//numérateur peut devenir négatif
    long newDen=(f.den/div);//mais pas le dénominateur
    fraction res={.num=newNum, .den=newDen};
    return res;
}

//3.8
typedef struct{
    fraction x;
    fraction y;
}point;

//3.9
int egaliteFrac(fraction f, fraction g){
    f=reduce(f);
    g=reduce(g);
    return (f.den==g.den && f.num==g.num)?1:0;
}

int eqp(point p1, point p2){
    return (egaliteFrac(p1.x, p2.x) && egaliteFrac(p1.y, p2.y))?1:0;
}

//3.10
double dist(point p1, point p2){
    fraction tmp=reduce(sum(mul(sub(p1.x, p2.x), sub(p1.x, p2.x)), 
                            mul(sub(p1.y, p2.y), sub(p1.y, p2.y))));
    double toSqrt=(double)(tmp.num)/(double)(tmp.den);
    return sqrt(toSqrt);
}


int main(){
    //1.
    //int res=f(20);
    //printf("%d\n", res); //432
    
    //2.
    /*int nbC=10;
    commande t[nbC];
    for(int i=0; i<nbC; i++){
        t[i]=comAlea(i);
        affCommande(t[i]);
    }
    printf("--------------\n");
    affExpediees(t, nbC);
    printf("--------------\n");
    printf("nb en cours : %d, coût exp : %d\n", nbCEC(t, nbC), coutVal(t, nbC));
    */
    
    //3.
    fraction ex_fractions[8];
    ex_fractions[0]=build(1, 1);
    ex_fractions[1]=build(1, 2);
    ex_fractions[2]=build(2, 4);
    ex_fractions[3]=build(-9, 3);
    ex_fractions[4]=build(8, -20);
    ex_fractions[5]=build(-5, -1);
    ex_fractions[6]=build(1, -3);
    //for(int i=0; i<7; i++) printFrac(ex_fractions[i]);
    
    //printf("%d\n", eq(ex_fractions[1], ex_fractions[2]));//1
    //printf("%d\n", eq(ex_fractions[3], ex_fractions[6]));//0
    
    //printf("%d\n", isInteger(ex_fractions[3]));//1
    //printf("%d\n", isInteger(ex_fractions[2]));//0
    
    /*printFrac(reduce(sum(ex_fractions[2], ex_fractions[2])));// 1/1
    printFrac(reduce(sum(ex_fractions[6], ex_fractions[2])));// 1/6
    
    printFrac(reduce(sub(ex_fractions[2], ex_fractions[2])));// 0/1
    printFrac(reduce(sub(ex_fractions[1], ex_fractions[0])));// -1/2
    
    printFrac(reduce(mul(ex_fractions[2], ex_fractions[6])));// -1/6
    printFrac(reduce(mul(ex_fractions[5], ex_fractions[4])));// -2/1*/
    
    point p1={.x=ex_fractions[0], .y=ex_fractions[1]};
    point p2={.x=ex_fractions[0], .y=ex_fractions[2]};
    point p3={.x=ex_fractions[0], .y=ex_fractions[3]};
    //printf("%d\n", eqp(p1, p2));//1
    //printf("%d\n", eqp(p1, p3));//0
    
    printf("%d\n", dist(p1, p2));//0
    printf("%f\n", dist(p1, p3));//3.5
    
    return 0;
}