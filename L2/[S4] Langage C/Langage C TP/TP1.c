#include<stdlib.h>
#include<stdio.h>
#include<time.h>

//2.1
void alea(){
    srand(time(NULL));
    int n=rand();
    if(n%2==0) printf("%d est pair\n",n);
    else printf("%d est impair\n",n);
}

//2.2
void stat(int m, int n){
    if(m<=n){
        int cMin=0;  //€[0;m[
        int cMax=0;  //€[m;n]
        int k=(int)(n+m/2);
        int r=-1;
        srand(time(NULL));
        do{
            r=rand()%k;
            (r<m)?cMin++:cMax++;
        }
        while(k<=n && r<=n);
        printf("Il y a %d valeur(s) dans [0;m[ et %d valeur(s) dans [m;n].\n", cMin, cMax);
    }
    else printf("Erreur\n");
}

//3.1
void tableau(int t[], int m, int n){
    srand(time(NULL));
    for(int i=0; i<m; i++) t[i]=rand()%(n+1);
}

//3.2
void aff_tab(int t[], int taille){
    for(int i=0; i<taille; i++) printf("%d ", t[i]);
    printf("\n");
}

//3.3
int max(int t[], int taille){
    int res=taille>0?0:-1;
    for(int i=1; i<taille; i++){
        if(t[i]>t[res]) res=i;
    }
    return res;
}

//4.1
void syracuse(int n){
    printf("%d\n", n);
    while(n!=1){
        if(n%2==0) n/=2;
        else n=3*n+1;
        printf("%d\n", n);
    }
}

//4.2
void syracuse2(int n){
    int vol=0;
    int s=n;
    //printf("%d\n", s);
    while(s!=1){
        if(s%2==0) s/=2;
        else s=3*s+1;
        vol++;
        //printf("%d\n", s);
    }
    printf("%d : %d\n", n, vol);
}

//4.3
void syracuse3(int n){
    for(int i=1; i<n+1; i++){
        syracuse2(i);
    }
}

//5.
void crible(int sup){
    int res[sup];
    for(int i=2; i<sup; i++) res[i]=i;
    for(int i=2; i<sup; i++){
        if(res[i]!=0){
            printf("%d ", i);
            for(int j=2*i; j<sup; j+=i) res[j]=0;
        }
    }
}

//1.
int main(){
    //1.1
    //printf("Hello World\n");
    //1.2
    int z=7;
    //printf("La variable z a pour valeur %d\n",z);
    
    //2.1
    //alea();
    //2.3
    //stat(15,7); //Erreur
    //stat(7,8);
    
    //3.1
    int n=2;
    int t[z];
    //tableau(t, z, n);
    //3.2
    //aff_tab(t, z);
    //3.3
    //printf("%d\n",max(t, z));
    int vide[0];
    //printf("%d\n",max(vide, 0));
    
    //4.1
    //syracuse(27);
    //4.2
    //syracuse2(27);
    //4.3
    //syracuse3(8);
    
    //5.
    crible(16);
}