#include <stdio.h>
#include <math.h>
enum etat {VALIDEE, ENCOURS, EXPEDIEE} ;
struct comm {
    int numeroC;
    int prix_exp;
    int prix_prod;
    enum etat etatC;
};
typedef struct comm commande;
commande comAlea(int num);
void remplirCom(commande tab[], int taille);
void affCommande(commande c);
void affExpediees(commande T[], int taille);
int nbCEC(commande T[], int taille);
int coutVal(commande T[],int taille); 


int main(int argc, char const *argv[])
{
    commande c = comAlea(3);
    commande NBC[10];
    remplirCom(NBC, 10);
    affExpediees(NBC, 10);
    printf("en préparation : %d", nbCEC(NBC, 10));
    printf("cout exp : %d", coutVal(NBC, 10));
    

    return 0;
}

commande comAlea(int num){
    commande c;
    c.numeroC = num;
    c.prix_exp = (rand()%20)+1;
    c.prix_prod = (rand()%2000)+1;
    c.etatC = rand()%3;
    
    return c;
}

void remplirCom(commande tab[], int taille){
    for (int i = 0; i < taille; i++)
    {
        tab[i] = comAlea(i+1);
    }
}

void affCommande(commande c){
    printf("numero : %d \n",c.numeroC);
    printf("prix exp : %d \n",c.prix_exp);
    printf("prix prod : %d \n",c.prix_prod);
    switch (c.etatC)
    {
    case 0: printf("VALIDEE \n");
        break;
    case 1: printf("ENCOURS \n");
        break;
    case 2: printf("EXPEDIEE \n");
        break;
    }
    printf("\n");
}

void affExpediees(commande T[], int taille){
    for (int i = 0; i < taille; i++)
    {
        affCommande(T[i]);
    }

}

int nbCEC(commande T[], int taille){
    int res = 0;
    for (int i = 0; i < taille; i++)
    {
        if (T[i].etatC == 1) res +=1;
    }
    return res;    
}

int coutVal(commande T[],int taille){
    int res = 0;
    for (int i = 0; i < taille; i++)
    {
        res += T[i].prix_exp;
    }
    return res;   

}

