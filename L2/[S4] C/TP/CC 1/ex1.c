#include <stdio.h>
#include <math.h>

int f(int n);
int g(int n);

int main(int argc, char const *argv[])
{
    printf("%d", f(20));
    return 0;
}


int f(int n){
    if(n<1) return 2;
    return 2*g(n-1);
}

int g(int n){
    if(n<1) return 1;
    return 3*g(n/2);
}
