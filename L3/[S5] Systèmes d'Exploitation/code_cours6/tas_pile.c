#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int M = 12;

void f(){
  int n = 3;  				// dans la pile
  int* t = malloc(3*sizeof(int)); 	// t dans la pile,
                                 	// *t dans le tas
  printf("adresse de n :\t %p\n", &n);
  printf("adresse de t :\t %p\n", &t);
  printf("contenu de t :\t %p\n", t);
}


int main(int argc, char ** argv){
  printf("pid = %d\n", getpid());
  printf("adresse de M :\t %p\n", &M);
  printf("adresse de argc :\t %p\n", &argc);
  printf("adresse de argv :\t %p\n", &argv);
  printf("contenu de argv :\t %p\n", argv);
  printf("contenu de argv[0] :\t %p\n", argv[0]);
  printf("contenu de argv[1] :\t %p\n", argv[1]); 

  f();
  pause();
  return 0;
}
