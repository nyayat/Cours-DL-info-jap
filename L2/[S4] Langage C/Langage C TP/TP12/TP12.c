#include "TP12.h"

struct node{
    int val;
    unsigned num;
    struct node* next;
};

//1.1
mset newNode(int val, unsigned num){
    mset res=malloc(sizeof(node));
    assert(res!=NULL);
    res->val=val;
    res->num=num;
    res->next=NULL;
    return res;
}

//1.2
mset addVal(int val, unsigned num, mset m){
    if(m==NULL) m=newNode(val, num);
    else{
        node* currentNode=m;
        if(val<currentNode->val){
            node* head=newNode(val, num);
            head->next=m;
            return head;
        }
        while(currentNode->next!=NULL && currentNode->next->val<=val) currentNode=currentNode->next;
        if(currentNode->val==val) currentNode->num+=num;
        else{
            node* toAdd=newNode(val, num);
            toAdd->next=currentNode->next;
            currentNode->next=toAdd;
        }
    }
    return m;
}

//1.3
mset build(int* values, size_t size){
    mset res=NULL;
    for(int i=0; i<size; i++) res=addVal(values[i], 1, res);
    return res;
}

//2.
void printMset(mset m, short verbose){
    if(m==NULL){
        printf("vide\n");
        return;
    }
    node* currentN=m;
    if(verbose==0){
        while(currentN!=NULL){
            printf("%d(%d) ", currentN->val, currentN->num);
            currentN=currentN->next;
        }
    }
    else{
        while(currentN!=NULL){
            for(int i=0; i<currentN->num; i++) printf("%d ", currentN->val);
            currentN=currentN->next;
        }
    }
    printf("\n");
}

//3.
mset removeVal(int val, unsigned num, mset m, unsigned* numRem){
    *numRem=0;
    if(m!=NULL){
        node* currentN=m;
        node* prec=NULL;
        while(currentN!=NULL && currentN->val<val){
            prec=currentN;
            currentN=currentN->next;
        }
        if(currentN!=NULL && currentN->val==val){
            if(currentN->num>num){
                currentN->num-=num;
                *numRem=num;
            }
            else{
                *numRem=currentN->num;
                if(prec==NULL) m=currentN->next;
                else prec->next=currentN->next;
                free(currentN);
            }
        }
    }
    return m;
}

//4.1
mset mplus(mset m, mset n){
    mset res=NULL;
    node* currentN=m;
    while(currentN!=NULL){
        res=addVal(currentN->val, currentN->num, res);
        currentN=currentN->next;
    }
    currentN=n;
    while(currentN!=NULL){
        res=addVal(currentN->val, currentN->num, res);
        currentN=currentN->next;
    }
    return res;
}

//4.2
mset mmoins(mset m, mset n){
    mset res=NULL;
    node* currentN=m;
    while(currentN!=NULL){
        res=addVal(currentN->val, currentN->num, res);
        currentN=currentN->next;
    }
    currentN=n;
    unsigned* nb=malloc(sizeof(unsigned));
    while(currentN!=NULL){
        res=removeVal(currentN->val, currentN->num, res, nb);
        currentN=currentN->next;
    }
    return res;
}

//4.3
int min(int a, int b){
    if(a<b) return a;
    return b;
}

mset minter(mset m, mset n){
    mset res=NULL;
    node* currentM=m;
    node* currentN=n;
    while(currentM!=NULL || currentN!=NULL){
        if(currentM->val==currentN->val){
            res=addVal(currentM->val, min(currentM->num, currentN->num), res);
            currentM=currentM->next;
            currentN=currentN->next;
        }
        else if(currentM->val>currentN->val) currentN=currentN->next;
        else currentM=currentM->next;
    }
    return res;
}

//4.4
int max(int a, int b){
    if(a>b) return a;
    return b;
}

mset munion(mset m, mset n){
    mset res=NULL;
    node* currentM=m;
    node* currentN=n;
    while(currentM!=NULL || currentN!=NULL){
        if(currentM->val==currentN->val){
            res=addVal(currentM->val, max(currentM->num, currentN->num), res);
            currentM=currentM->next;
            currentN=currentN->next;
        }
        else if(currentM->val>currentN->val){
            res=addVal(currentN->val, currentN->num, res);
            currentN=currentN->next;
        }
        else{
            res=addVal(currentM->val, currentM->num, res);
            currentM=currentM->next;
        }
    }
    return res;
}