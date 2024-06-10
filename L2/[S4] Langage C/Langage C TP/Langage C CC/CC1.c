//DAI Anna 21953144 Groupe 1

#include<stdio.h>
#define DEG 6

typedef struct{
    int degre;
    int coef[DEG];
}poly;

//2.
void print_monome(int coeff, int degre){
    printf("%+dx^%d", coeff, degre);
}

void print_poly(poly p){
    for(int i=0; i<=p.degre; i++){
        if(p.coef[i]!=0 || (p.degre==0 && p.coef[i]==0))
            print_monome(p.coef[i], i);
    }
    printf("\n");
}

//3.
poly add(poly p, poly q){
    int max=(p.degre>q.degre)?p.degre:q.degre;
    poly res;
    res.degre=max;
    for(int i=0; i<=max; i++){
        if(p.degre>=i) res.coef[i]=p.coef[i];
        if(q.degre>=i) res.coef[i]+=q.coef[i];  //res.coef[i] initialisé à 0
    }
    return res;
}

//1.
int main(){
    poly p={.degre=4, .coef={2, 0, -3, 0, 7}};
    poly q;
    q.degre=4;
    q.coef[0]=-3;
    q.coef[1]=1;
    q.coef[2]=0;
    q.coef[3]=-6;
    q.coef[4]=-7;
    
    //2.
    poly cst={.degre=0, .coef={0}};
    print_poly(p);  //+2x^0-3x^2+7x^4
    print_poly(q);  //-3x^0+1x^1-6x^3-7x^4
    print_poly(cst);  //+0x^0
    
    //3.
    poly test=add(p, q);
    print_poly(test);  //-1x^0+1x^1-3x^2-6x^3
    poly test2=add(p, cst);
    print_poly(test2);  //+2x^0-3x^2+7x^4
    
    return 0;
}