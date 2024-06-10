#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <assert.h>

/* node : une structure pour le noeud de la liste */
typedef struct node{
  int val;  /* valeur stocke dans un noeud*/
  struct node *suivant; /* pointeur vers le noeud suivant */
}node;

/* head : la tete de la liste.
 * la liste commence par la tete suivie de nodes*/ 
typedef struct{
  size_t nb;
  node *first;
} head;

typedef head *liste;


liste creer_liste(){
  liste l = malloc( sizeof(head) );
  assert( l != NULL );
  l->first=NULL;
  l->nb = 0;
  return l;
}

size_t nb_elem(liste l){
  assert( l );
  return l->nb;
}

void afficher_liste(liste l){
  assert( l );
  for( node *n = l->first; n ; n=n->suivant)
    printf(" %d ", n->val);
  printf("\n");
  return;
}

/* creer un node */
node *creer_node(int v){
  node *n = malloc(sizeof(node));
  assert(n != NULL );
  n->suivant = NULL;
  n->val = v;
  return n;
}

/* inseret dans la liste l un nouveau node avec la valeur v
 * Si n != NULL on insere le nouveau element juste apers la node n.
 * Si n==NULL on insere comme le premier elemeny de la liste */ 
node *insert_after(liste l, node *n, int v){
  assert(l != NULL);
  node *x=creer_node(v);
  l->nb++;
  if( n==NULL ){
    x->suivant = l->first;
    l->first = x;
  }else{
    x->suivant = n->suivant;
    n->suivant = x;
  }
  return x;
}

/* make_empty supprime tous les elements de la liste.
   La liste devient vide, juste avec le head */  
void make_empty(liste l){
  assert( l );
  if( l->first == NULL )
    return;
  for( node *n = l->first;  n != NULL ; /* empty */  ){
    node *s=n->suivant;
    free(n);
    n = s;
  }
  l->first = NULL;
  l->nb = 0;

  return;
}


/* detruire la liste, y compris le head */
void delete_liste(liste l){
  make_empty(l);
  free(l);
}

/* supprimer de laliste l le premier element qui contient v.
 * Renvoyer 1 si un element a ete supprime,
 * renvoyer 0 si la liste ne conient pas v */
 
int delete_elem(liste l, int v){

  node *prev = NULL;    /* precedent du courant */
  node *cur = l->first; /* element courant */
  
  while( cur != NULL && cur->val != v){
    prev = cur;
    cur = cur->suivant;
  }
  if( cur == NULL ){/* v n'est pas dans la liste */
    return 0;
  }
  if( prev == NULL ){ /* v est dans le premier node de la liste*/
    l->first = cur->suivant;
  }else{ /* v est dans la liste mais pas dans le premier element */
    prev->suivant = cur->suivant;
  }  
  free(cur);
  l->nb--;
  return 1;
}
    
int main(void){
  int t[]={9,8,7,6,5,4,3,2,1,-1,-2};
  size_t nb = sizeof(t)/sizeof(t[0]);
  liste l = creer_liste();
  assert(l != NULL);
  /* on insere tous les elements de t[], un par un, chaque element
   * insere au debut de la liste */  
  for(size_t i=0; i < nb; i++){
    insert_after(l, NULL, t[i]);
  }

  /* a l'affichage on doit retrouver tous les elements de t[] dans l'ordre 
   * inverse*/
  afficher_liste(l);


  
  make_empty(l);
  /* on onsere tous elements de t[] mais cette fois t[i+1] sera
   *  insere apres t[i] */
  node *n = NULL;
  for(size_t i=0; i < nb; i++){
    n=insert_after(l, n, t[i]);
  }
  /* l'affichage doit donner les element de t[] dans l'ordre */ 
  afficher_liste(l);
  /* supprimer le premier, le dernier element, et un element qui n'est
  ni premier ni dernier */
  delete_elem(l,5); 
  delete_elem(l,9);
  delete_elem(l,-2);
  /* supprimer un element qui n'est pas dans la liste*/
  delete_elem(l,50);


  
  afficher_liste(l);
  
}
