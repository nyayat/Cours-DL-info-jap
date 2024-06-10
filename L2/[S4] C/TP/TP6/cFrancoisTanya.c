/*
FRANCOIS Tanya
21952540
Groupe 1 (info - japonais)
*/
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

char *sans_lettre( const char *s, char x );
void affiche (const char *s);


int main(int argc, char const *argv[])
{
    printf("test chaine vide : %s \n", sans_lettre("", 'a'));
    printf("test même lettre : %s \n", sans_lettre("aaaaa", 'a'));
    printf("test plusieurs lettres : %s \n", sans_lettre("ala ma kota",'a'));

    return 0;
}


void affiche (const char *s) {
    for(int i = 0; s[i] != '\0'; i++)
    printf("%c", s[i]);
    }


char *sans_lettre( const char *s, char x ){
    char* res;
    int taille = 0;
    for(int i = 0; s[i] != '\0'; i++){
        if(s[i] != x){
            taille += 1;
        }
    }
    if(taille==0) return "";
    
    res = malloc(taille);
    assert(res!=NULL);
    for(int i = 0; s[i] != '\0'; i++){
        if(s[i] != x){
            res[taille-1] = s[i];
            taille -= 1;
        }
    }

    //affiche(res);
    //printf(("\n"));


    return res;

}

