//DAI Anna 21953144 Info-1
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

//1.
char* sans_lettre(const char* s, char x){
    int size=0;
    int last=0;
    for(int i=0; s[i]!='\0'; i++){
        if(s[i]!=x) size++;
        last++;
    }
    char* res=malloc(size+1);
    assert(res!=NULL);
    size=0;
    for(int i=last-1; i>=0; i--){
        if(s[i]!=x){
            res[size++]=s[i];
        }
    }
    res[size]='\0';
    return res;
}

//2.
int main(){
    const char* s1="";
    const char* s2="aaaaa";
    const char* s3="ala ma kota";
    
    printf("%s -> ", s1);
    char* ss1=sans_lettre(s1, 'a');
    printf("%s\n", ss1);
    
    printf("%s -> ", s2);
    char* ss2=sans_lettre(s2, 'a');
    printf("%s\n", ss2);
    
    printf("%s -> ", s3);
    char* ss3=sans_lettre(s3, 'a');
    printf("%s\n", ss3);    
    
    return 0;
}