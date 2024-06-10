#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<string.h>
#include<assert.h>

//1.1
void f1(const char* s){//ok
    for(int i=0; s[i]!='\0'; i++) printf("%c", s[i]);
}

void f2(const char* s){//ok
    for(; *s; s++) printf("%c", *s);
}

//1.2
char* g1(const char* s){//pas d'affectation de mémoire
    char* t;
    strcpy(t, s);
    return t;
}

char* g2(const char* s){//ajouter un bit en plus pour l'allocation (pour '\0')
    char* t=malloc(strlen(s));
    strcpy(t, s);
    return t;
}

char* g3(const char* s){//ok
    char* t=malloc(strlen(s)+1);
    strcpy(t, s);
    return t;
}

//2.
int nbrWords(const char* s){
    /*int res=0;
    int len=strlen(s);
    int c=0;
    while(c<len){
        if(!isspace(s[c])){
            res++;
            while(c<len && !isspace(s[c])) c++;
        }
        if(isspace(s[c])) c++;
    }
    return res;*/
    
    int res=0;
    for(int i=0; s[i]!='\0'; i++){
        if(!isspace(s[i]) && (isspace(s[i-1]) || i==0)) res++;
    }
    return res;
}

//3.1
int wordLen(const char* w){
    if(isspace(w[0]) || w[0]=='\0') return 0;
    int res=1;
    while(!isspace(w[res]) && w[res]!='\0') res++;
    return res;
}

//3.2
char* extractWord(const char* w, int* p1){
    int len=wordLen(w);
    char* res=malloc(len+1);
    assert(res!=NULL);
    for(int i=0; i<len; i++) res[i]=w[i];
    res[len]='\0';
    *p1=len;
    return res;
}

typedef struct{
    int nbr;
    char** words;
} wIndex;

//4.
void freeIndex(wIndex* pi){
    if(pi!=NULL){
        if(pi->words!=NULL){
            for(int i=0; i<pi->nbr; i++) free(pi->words[i]);
        }
        free(pi->words);
    }
    free(pi);
}

//5.1
int sizeWords(wIndex* pi){
    int res=0;
    if(pi!=NULL){
        if(pi->words!=NULL){
            for(int i=0; i<pi->nbr; i++) res+=strlen(pi->words[i]);
        }
    }
    return res;
}

//5.2
char* concatWords(wIndex* pi){
    char* res=malloc((sizeWords(pi)+pi->nbr)*sizeof(char));//pas besoin de +1 pour \0
    assert(res!=NULL);
    int c=0;
    for(int i=0; i<pi->nbr; i++){
        for(int j=0; pi->words[i][j]!='\0'; j++){
            res[c++]=pi->words[i][j];
        }
        res[c++]=' ';
    }
    res[--c]='\0';//au niveau du dernier espace
    return res;
}

//6.
wIndex* consIndex(const char* s){
    wIndex* res=malloc(sizeof(wIndex));
    assert(res!=NULL);
    res->nbr=nbrWords(s);
    res->words=malloc(res->nbr*sizeof(char*));
    assert(res->words!=NULL);
    int nbW=0;//nombre de mots
    while(*s!='\0'){
        if(!isspace(*s)){//première lettre d'un mot
            int tmpLen;
            res->words[nbW++]=extractWord(s, &tmpLen);
            s+=tmpLen;
        }
        else s++;
    }    
    return res;
}


int main(){
    //1.
    //const char* s="Hello World";
    //f1(s);//Hello World
    //f2(s);//Hello World
    
    //printf("%s\n", g1(s));//Hello World
    //printf("%s\n", g2(s));//Hello World
    //printf("%s\n", g3(s));//Hello World
    
    //2.
    const char* s2="   a aa   ba a bbb   ";
    /*printf("%d\n", nbrWords(s));//2
    printf("%d\n", nbrWords(s2));//5*/
    
    //3.
    char* s3="Hello World";
    char* s4="   a aa   ba a bbb   ";
    /*printf("%d\n", wordLen(s3));//5
    printf("%d\n", wordLen(s4));//0
    
    int* p=malloc(2*sizeof(int));
    printf("%s\n", extractWord(s3, p));//Hello
    printf("%s\n", extractWord(s4, p+1));//""
    printf("%d %d\n", p[0], p[1]);//5 0*/
    
    wIndex *pi=consIndex(" ab   cde f");
    char* s=concatWords(pi);
    printf("%s\n", s);
    free(s);
    freeIndex(pi);
    
    return 0;
}