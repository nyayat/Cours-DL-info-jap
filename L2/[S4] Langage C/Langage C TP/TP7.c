#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include<string.h>

typedef struct node node;
struct node{
    int val;
    node *left;
    node *right;
};

void print_head(int depth, int addr){
    if(depth>1){
	int pre=addr/2;
	print_head(depth-1, pre);
	printf("%s", (pre%2)!=(addr%2)?"|    ":"     ");
	return;
    }
    if(depth==1) printf("     ");
}

void pretty_rec(node *t, int depth, int addr){
    if(t==NULL){
	print_head(depth, addr);
	printf("|----N\n");
	return;
    }
    pretty_rec(t -> right, depth+1, 2*addr+1);
    print_head(depth, addr);
    char c=(depth == 0)?'-':'|';
    printf("%c----%d\n", c, t->val);
    pretty_rec(t->left, depth+1, 2*addr);
}

// fonction principale d'affichage.
void pretty_print(node *t){
    pretty_rec(t, 0, 0);
}

//1.
node* cons_tree(int val, node *left, node *right){
    node* res=malloc(sizeof(node));
    assert(res!=NULL);
    res->val=val;
    res->right=right;
    res->left=left;
    return res;
}

//2.
void free_tree(node* t){
    if(t==NULL) return;
    if(t->left!=NULL) free_tree(t->left);
    if(t->right!=NULL) free_tree(t->right);
    free(t);
}

//3.
int size_tree(node* t){
    if(t==NULL) return 0;
    return 1+size_tree(t->left)+size_tree(t->right);
}

int sum_tree(node* t){
    if(t==NULL) return 0;
    return t->val+sum_tree(t->left)+sum_tree(t->right);
}

int max(int a, int b){
    if(a>b) return a;
    return b;
}

int depth_tree(node* t){
    if(t==NULL) return 0;
    return 1+max(depth_tree(t->right), depth_tree(t->left));
}

//4.
void print_abr(node* t){
    if(t==NULL) return;
    print_abr(t->left);
    printf("%d ", t->val);
    print_abr(t->right);
}

//5.
node* insert_abr(node* t, int val){
    if(t==NULL) t=cons_tree(val, NULL, NULL);
    else if(t->val!=val){
        if(t->val<val) t->right=insert_abr(t->right, val);
        else t->left=insert_abr(t->left, val);
    }
    return t;
}

//6.
node* search_abr(node* t, int val){
    if(t==NULL) return NULL;
    if(t->val==val) return t;
    if(t->val<val) return search_abr(t->right, val);
    return search_abr(t->left, val);
}

//7.1
node* max_abr(node* t){
    if(t==NULL) return NULL;
    if(t->right!=NULL) return max_abr(t->right);
    return t;
}

//7.2
node* min_abr(node* t){
    if(t==NULL) return NULL;
    if(t->left!=NULL) return min_abr(t->left);
    return t;
}

//7.3
int check_abr(node* t){
   if(t==NULL) return 1;
   node* tmp=max_abr(t);
   int max=(tmp==NULL)?t->val+1:tmp->val;
   tmp=min_abr(t);
   int min=(tmp==NULL)?t->val-1:tmp->val;
   if(min>t->val || t->val>max) return 0;
   return (check_abr(t->right)+check_abr(t->left)==2)?1:0;
}

//8.
node* delete_abr(node* t, int val){
    if(t==NULL) return t;
    if(val<t->val) t->left=delete_abr(t->left, val);
    else if(val>t->val) t->right=delete_abr(t->right, val);
    else{
        if(t->left==NULL && t->right==NULL){
            free_tree(t->left);
            free_tree(t->right);
            t=NULL;
        }
        else if(t->left!=NULL && t->right!=NULL){
            node* max=max_abr(t->left);
            int valMax=max->val;
            t=delete_abr(t, max->val);//suppression de max et réorganisation de l'arbre
            t->val=valMax;
        }
        else{
            node* tmp=(t->left!=NULL)?t->left:t->right;
            free(t);
            t=tmp;
        }
    }
    return t;
}

int main(){
    node* t;
    t=cons_tree(1, cons_tree(3, NULL, NULL), cons_tree(6, cons_tree(4, NULL, NULL), NULL));
    //pretty_print(t);
    //printf("size : %d ; sum : %d ; depth : %d\n", size_tree(t), sum_tree(t), depth_tree(t));
    //print_abr(t);
    
    t=NULL;
    int vals[10]={8, 3, 1, 2, 6, 4, 7, 10, 14, 13};
    for(int i=0; i<10; i++) t=insert_abr(t, vals[i]);
    /*print_abr(t);
    printf("\n");
    pretty_print(t);*/
    
    /*node* s=max_abr(t);
    if(s!=NULL) printf("%d ", s->val);
    else printf("NULL ");
    s=min_abr(t);
    if(s!=NULL) printf("%d ", s->val);
    else printf("NULL\n");*/
    
    //printf("%d ", check_abr(t));
    
    //pretty_print(t);
    //printf("\n");
    pretty_print(delete_abr(t, 3));
    return 0;
}