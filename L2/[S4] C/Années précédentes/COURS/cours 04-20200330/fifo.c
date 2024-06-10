#define LEN 1024
typedef struct {
  size_t nb;  /*le bnombre d'element dans fifo*/
  int tab[LEN]; /*  le fifo de int */
} fifo;
typedef fifo *pfifo;

pfifo creer_fifo(){
  pfifo f = malloc( sizeof( fifo ));
  if( f == NULL )
    return NULL;
  f->nb = 0;
  return f;
}

/* ajouter a dans fifo,
   retourne f si OK, ou NULL si fifo plein*/
pfifo put_fifo(pfifo f, int a){
  if( f->nb >= LEN )
    return NULL;
  f->tab[f->nb] = a;
  f->nb ++;
}

/* 
