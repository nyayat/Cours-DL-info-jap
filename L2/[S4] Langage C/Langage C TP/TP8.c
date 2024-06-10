#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

typedef struct element element;
struct element{
    int val;
    element* next;
    element* previous;
};

//1.1
element* setList(){
    element* res=malloc(sizeof(element));
    assert(res!=NULL);
    res->next=res;
    res->previous=res;
    return res;
}

//1.2
void addFirst(element* l, int v){
    element* tmp=setList();
    tmp->val=v;
    tmp->next=l->next;
    l->next->previous=tmp;
    tmp->previous=l;
    l->next=tmp;
}

//1.3
void addLast(element* l, int v){
    element* tmp=setList();
    tmp->val=v;
    tmp->previous=l->previous;
    l->previous->next=tmp;
    tmp->next=l;
    l->previous=tmp;
}

//2.1
int isEmpty(element* l){//1=empty, 0=notEmpty
    return l->next==l;
}

//2.2
int len(element* l){
    if(isEmpty(l)) return 0;
    element* tmp=l->next;
    int res=0;
    for(; tmp!=l; res++, tmp=tmp->next);
    return res;
}

//2.3
void print(element* l){
    if(isEmpty(l)){
        printf("Liste vide\n");
        return;
    }
    element* tmp=l->next;
    for(int i=0; i<len(l); i++, tmp=tmp->next) printf("%d ", tmp->val);
    printf("\n");
}

//3.1
int deleteFirst(element* l){
    if(isEmpty(l)){
        printf("Liste vide\n");
        return 0;
    }
    int res=l->next->val;
    l->next=l->next->next;
    free(l->next->previous);
    l->next->previous=l;
    return res;
}

//3.2
int deleteLast(element* l){
    if(isEmpty(l)){
        printf("Liste vide\n");
        return 0;
    }
    int res=l->previous->val;
    l->previous=l->previous->previous;
    free(l->previous->next);
    l->previous->next=l;
    return res;
}

//3.3
void freeList(element* l){
    int size=len(l);
    element* tmp=l->next;
    for(int i=0; i<size; i++, tmp=tmp->next) free(tmp);
    free(l);
}

//3.4
element* copyList(element* l){
    element* res=setList();
    int size=len(l);
    element* tmp=l->next;
    for(int i=0; i<size; i++, tmp=tmp->next) addLast(res, tmp->val);
    return res;
}

//4.1
void insertPrev(element* p, int v){
    element* toIns=setList();
    toIns->val=v;
    p->previous->next=toIns;
    toIns->previous=p->previous;
    p->previous=toIns;
    toIns->next=p;
}

//4.2
void insertNext(element* p, int v){
    element* toIns=setList();
    toIns->val=v;
    p->next->previous=toIns;
    toIns->next=p->next;
    p->next=toIns;
    toIns->previous=p;
}

//4.3
void delete(element* p){
    p->previous->next=p->next;
    p->next->previous=p->previous;
    free(p);
}

//5.1
element* getElement(element* l, int v){
    int size=len(l);
    element* tmp=l->next;
    for(int i=0; i<size; i++, tmp=tmp->next){
        if(tmp->val==v) return tmp;
    }
    return NULL;
}

//5.2
void concat(element* l1, element* l2){//nouvelle tete=l2
    l2->previous->next=l1->next;
    l1->next->previous=l2->previous;
    l1->previous->next=l2;
    l2->previous=l1->previous;
    free(l1);
}

//5.3
int getVal(element* l, int i){
    i%=len(l);
    element* res=l->next;
    for(int j=0; j<i; j++, res=res->next);
    return res->val;
}

//5.4
void reverse(element* l){
    int size=len(l)+1;//ajout de la tete
    element* tmp=l;
    element* saveN;
    for(int i=0; i<size; i++, tmp=saveN){
        saveN=tmp->next;
        tmp->next=tmp->previous;
        tmp->previous=saveN;
    }
}

//6.
element* fusion(element* l1, element* l2){
    int c1=0;
    int c2=0;
    int size1=len(l1);
    int size2=len(l2);
    element* res=setList();
    while(c1<size1 && c2<size2){
        if(getVal(l1, c1)<getVal(l2, c2)) addLast(res, getVal(l1, c1++));
        else addLast(res, getVal(l2, c2++));
    }
    if(c2==size2){
        while(c1<size1) addLast(res, getVal(l1, c1++));
    }
    else{
        while(c2<size2) addLast(res, getVal(l2, c2++));        
    }
    return res;
}


//2.4
int main(){
    element* l=setList();
    //print(l);
    addLast(l, 5);
    addLast(l, 6);
    addFirst(l, 2);
    //print(l);
    
    /*printf("%d %d \n", deleteLast(l), deleteFirst(l));
    print(l);*/
    
    /*element* l2=copyList(l);
    print(l2);*/
    
    insertPrev(l->next->next, 4);
    insertNext(l->next, 3);
    //print(l);
    
    /*delete(l->next->next);
    print(l);*/
    
    /*element* s=getElement(l, 5);
    print(s);
    element* s=getElement(l, 7);
    if(s==NULL) printf("N");*/
    
    element* l4=setList();
    addLast(l4, 1);
    addLast(l4, 2);
    addLast(l4, 3);
    element* l3=setList();
    addLast(l3, 3);
    addLast(l3, 4);
    addLast(l3, 5);
    addLast(l3, 6);
    //concat(l3, l4);
    //print(l4);
    
    //printf("%d\n", getVal(l4, 4));
    
    /*reverse(l4);
    print(l4);*/
    
    element* l5=fusion(l3, l4);
    print(l5);
    
    return 0;
}