#include <stdio.h>
#include <math.h>
#include <assert.h>

struct frac{
    long int num;
    long int den;

};
typedef struct frac fraction;
fraction build(long int n, long int d);
int eq(fraction f, fraction g);
int isInteger(fraction f);
void affiche(fraction f);
fraction sum(fraction f, fraction g);// somme
fraction sub(fraction f, fraction g);// soustraction
fraction mul(fraction f, fraction g); // multiplication
long pgcd(long a,long b);
fraction reduce(fraction f);


int main(int argc, char const *argv[])
{
    fraction ex_fractions[7];
    ex_fractions[0] = build(1,1);
    ex_fractions[1] = build(1,2);
    ex_fractions[2] = build(2,4);
    ex_fractions[3] = build(-9,3);
    ex_fractions[4] = build(8,-20);
    ex_fractions[5] = build(-5,-1);
    ex_fractions[6] = build(1,-3);

    //printf("%d\n", eq(ex_fractions[0], ex_fractions[2]));
    //printf("%d\n", eq(ex_fractions[1], ex_fractions[2]));

    //printf("%d\n", isInteger(ex_fractions[0]));
    //printf("%d\n", isInteger(ex_fractions[3]));
    //printf("%d\n", isInteger(ex_fractions[2]));

    fraction ex_sum = sum(ex_fractions[0], ex_fractions[1]);
    affiche(ex_sum);

    return 0;
}

void affiche(fraction f){
    printf("%d \n_ \n%d \n\n", f.num, f.den);
}
fraction build(long int n, long int d){
    assert(d!=0);
    fraction f;
    f.num = n;
    f.den = d;
    return f;
}

int eq(fraction f, fraction g){
    return f.num * g.den == f.den * g.num;
}

int isInteger(fraction f){
    return f.num % f.den == 0;
}


fraction sum(fraction f, fraction g){
    if(f.den == g.den) return build(f.num + g.num, f.den);
    return build( (f.num * g.den) + (g.num * f.den), f.den * g.den );
}
fraction sub(fraction f, fraction g){
    if(f.den == g.den) return build(f.num - g.num, f.den);
    return build( (f.num * g.den) - (g.num * f.den), f.den * g.den );
}
fraction mul(fraction f, fraction g){
    return build( (f.num * g.num), f.den * g.den );
} // multiplication

long pgcd(long a,long b){
    int x = a;
    int y = b;
    while (y !=0){
        int r = x%y; 
        x = y;
        y = r;
    }
    return x
}

fraction reduce(fraction f){
    int red = pgcd(f.num, f.den);
    int res = f.num/f.den;
    if(res < 0) return build(-(f.num/red), f.den/red);

    return build(f.num/red, f.den/red);
}