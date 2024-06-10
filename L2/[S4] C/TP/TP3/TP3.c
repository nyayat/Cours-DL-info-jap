#include <stdio.h>
typedef struct{
    int x;
    int y;
} point;

void swap(int* pa, int* pb);
void minmax(int n, int t[], int* pmin, int* pmax);
void occurences(int n, int t[], int e, int* pocc, int* first);
void occurences_bis(int n,int t[],int e,int* pocc,int** first);
void sort(int t[],int start,int end);
void sort_point(int* start,int* end); 

int main(int argc, char const *argv[])
{
    //I)
    /* //ex. 1/2
    int x,y;
    x=5;
    y=6;
    printf("Valeur de x avant échange : %d\n",x);
    printf("Valeur de y avant échange : %d\n",y);
    swap(&x,&y);
    printf("Valeur de x après échange : %d\n",x);
    printf("Valeur de y après échange : %d\n",y);
    */
    /* //Ex.3
    int tab[5] = {1, 2, 3, 4, 5};
    printf("Valeur de t[0] avant échange : %d\n",tab[0]);
    printf("Valeur de t[4] avant échange : %d\n",tab[4]);
    swap(&tab[0], &tab[4]);
    printf("Valeur de t[0] après échange : %d\n", tab[0]);
    printf("Valeur de t[4] après échange : %d\n", tab[4]);
    */
    
    
    /* //Ex. 4
    point p={.x=5, .y=20};
    printf("Valeur de x avant échange : %d\n",p.x);
    printf("Valeur de y avant échange : %d\n",p.y);
    swap(&p.x, &p.y);
    printf("Valeur de x après échange : %d\n",p.x);
    printf("Valeur de y après échange : %d\n",p.y);
    */

    //II)
    
    /*//Ex.1
    int t[5]={22,3,56,78,4};
    int min,max;
    minmax(5,t,&min,&max);
    */

    /* //Ex.2
    int t[6]={22,3,4,56,78,4};
    int occ,first;
    int* first2;
    //occurences(6,t,4,&occ,&first);
    occurences_bis(6,t,4,&occ,&first2);
    */

   ///III)
    int t[6]={22,3,4,56,78,4};
    //sort(t, 0, 6);
    sort_point(&t[0], &t[5]); 
    
    for (int i = 0; i < 6; i++)
    {
        printf("%d, ", t[i]);
    }


   
    return 0;
}

void swap(int* pa, int* pb){
    int tmp = *pa;
    *pa = *pb;
    *pb = tmp;    
}


void minmax(int n, int t[], int* pmin, int* pmax){
    *pmin = 0;
    *pmax = 0;

    for (int i = 1; i < n; i++)
    {
        if(t[i] < t[*pmin]){
            *pmin = i;
        }
        if(t[i] > t[*pmax]){
            *pmax = i;
        }
        
    }

    printf("min : %d \n", *pmin);
    printf("max : %d", *pmax);
    
}


void occurences(int n, int t[], int e, int* pocc, int* first){
    *pocc = 0;
    *first = -1;
    for (int i = 0; i < n; i++)
    {
        if (t[i] == e)
        {
            *pocc +=1 ;
            if (*first == -1)
            {
                *first = i;
            }
            
        }
        
    }
    printf("premiere occurence : %d \n", *first);
    printf("nb occ : %d", *pocc);
    
}


void occurences_bis(int n,int t[],int e,int* pocc,int** first){
    *pocc = 0;
    for (int i = 0; i < n; i++)
    {
        if (t[i] == e)
        {
            *pocc +=1 ;
            if (*pocc == 1)
            {
                *first = &t[i];
            }
            
        }
        
    }
    printf("adresse premiere occurence : %p \n", **first);
    printf("nb occ : %d", *pocc);
}


void sort(int t[],int start,int end){
    for (int i = start; i < end - 1; i++)
    {
        if( t[i] > t[i+1] ){
            printf("ici \n");
            swap(&t[i], &t[i+1]);
        }
    }

    
    printf("\n");
    if(start < end){
        sort(t, start, end-1);
    }
}

void sort_point(int* start,int* end){
    for (int* i = start; i <= end -1; i++)
    {
        if ( *i > *(i+1) )
        {
            swap(i, i+1);
        }
        
    }
    if(start != end){
        sort_point(start, end-1);
    }
    

}