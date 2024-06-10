#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <libgen.h>
#include <assert.h>

//1.1
/*void fill(char* name, int n){
    FILE* f=fopen(name, "w");;
    if(f!=NULL){
        for(int i=0; i<n; i++) fputs("Hello World\n", f);
    }
    fclose(f);
}

int main(){
    //1.1
    fill("test.txt", 5);
    fill("test2.txt", 2);
    //si plusieurs appels avec même nom de fichier, le plus récent écrase les autres
    
    //1.2 : pour écrire à la suite, il faut ouvrir le fichier avec "a+", et non "w"
    return 0;
}*/

/*int main(int argc, char** argv){
    if(argc!=3){
        fprintf(stderr, "usage : %s nom_fichier  n\n", basename(argv[0]));
        return EXIT_FAILURE;
    }
    FILE* flot=fopen(argv[1], "w");
    if(flot==NULL){
        perror("fopen");
        exit(EXIT_FAILURE);
    }
    int n=atoi(argv[2]);
    char message[]="Message :D";
    for(int i=0; i<n ; i++){
        fputs(message, flot);
        fputc('\n', flot);
    }
    fclose(flot);
    return EXIT_SUCCESS;
}*/

//1.3
int copie(FILE* fsrc, FILE* fdst){
    int last=fgetc(fsrc);
    if(last==EOF) return -1;
    for(; last!=EOF; last=fgetc(fsrc)) fputc(last, fdst);
    return 0;
}

//1.4
/*int main(int argc, char** argv){
    if(argc!=3){
        fprintf(stderr, "usage : %s file_src file_dst\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    FILE* src=fopen(argv[1],"r");
    if(src==NULL){
        perror("fopen src");
        exit(EXIT_FAILURE);
    }
    FILE* dst=fopen(argv[2], "w");
    if(dst==NULL){
        perror("fopen sdt");
        exit(EXIT_FAILURE);
    }
    copie(src, dst);
    return EXIT_SUCCESS;
    
    //la copie a le même poids que l'original
}*/

//1.5
/*int main(int argc, char** argv){
    if(argc==1){
        copie(stdin, stdout);
        return EXIT_SUCCESS;
    }
    FILE* toRead;
    for(int i=1; i<argc; i++){
        toRead=fopen(argv[i], "r");
        if(toRead==NULL){
            fprintf(stderr, "fopen %s : %s\n", argv[i], strerror(errno));
            continue;
        }
        copie(toRead, stdout);
        fclose(toRead);
    }
    exit(EXIT_SUCCESS);
}*/

//1.6
int copieParLignes(FILE* src, FILE* dst, int lig, int len){
    char* tmp;//pour stocker les lignes obtenues
    char ch[len];
    int c=0;//compteur
    while(c<lig && (tmp=fgets(ch, len, src))!=NULL){
        fputs(tmp, dst);
        c++;
    }
    if(tmp==NULL) return 0;//fin du fichier
    return 1;//pas la fin
}

//1.7
/*int main(int argc, char** argv){
    if(argc==1){
        fprintf(stderr, "usage : %s file_1 file_2 ... file_n\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    FILE* toRead;
    int display;
    char entry;
    for(int i=1; i<argc; i++){
        toRead=fopen(argv[i]);
        if(toRead==NULL){
            fprintf(stderr, "fopen %s : %s\n", argv[i], strerror(errno));
            continue;
        }
        int n=atoi(getenv("LINES"));
        do{
            display=copieParLigne(toRead, stdout, n);
            scanf("%c", &entry);
        }
        while(entry=='\n' && display);
        fclose(toRead);
    }
    exit(EXIT_SUCCESS);
}*/

//2.1
int longueur(char* name){
    int res=0;
    FILE* f=fopen(name, "r");
    assert(f!=NULL);
    while(fgetc(f)!=EOF) res++;
    fclose(f);
    return res;
}

//2.2
int longueurMaxLignes(char* name){
    FILE* f=fopen(name, "r");
    assert(f!=NULL);
    int res=0;
    int tmp=0;
    int current=fgetc(f);
    while(current!=EOF){
        if(current=='\n'){
            if(tmp>res) res=tmp;
            tmp=0;
        }
        else tmp++;
        current=fgetc(f);
    }
    fclose(f);
    return res;
}

//2.3
typedef struct{
    unsigned len;
    char* txt;
    unsigned nbl;
    char** lignes;
} document;

int nbLignes(char* name){
    FILE* f=fopen(name, "r");
    assert(f!=NULL);
    int res=0;
    int current=fgetc(f);
    while(current!=EOF){
        if(current=='\n') res++;
        current=fgetc(f);
    }
    fclose(f);
    return res;
}

document* decouperEnLignes(char* name){
    document* res;
    FILE* f=fopen(name, "r");
    assert(f!=NULL);
    res->len=longueur(name);
    res->nbl=nbLignes(name);
    
    res->txt=malloc(sizeof(char)*(res->len+1));
    assert(res->txt!=NULL);
    
    res->lignes=malloc(sizeof(char)*res->nbl);
    assert(res->lignes!=NULL);
    
    int largeurL=longueurMaxLignes(name);
    int i=0;
    char* tmp;
    while(i<res->nbl){
        tmp=fgets(res->txt, largeurL+1, f);
     //   setvbuf(f, res->lignes[i], _IOLBF, largeurL+1);
        res->lignes[i++]=tmp;
    }
    
    fclose(f);
    return res;
}

//2.4
void afficher(document* doc){
    printf("longueur=%d, nombre de lignes=%d\n", doc->len, doc->nbl);
    char t[doc->len];
    for(int i=0; i<doc->nbl; i++){
        
    }
}

int main(){
    document* d=decouperEnLignes("test.txt");
    afficher(d);
    
    return 0;
}