#!/usr/bin/env python3

# Pour les tests
import tp2_ex1

# Pour les fonctions mathematiques
import math

# Pour l'affichage des graphiques
from matplotlib.pyplot import plot, show

# Pour l'affichage des résultats
from ea4lib import printcol

###############################################################################
# Exercice 2.1

def produit_matrice_2_2 (M1, M2) :
    '''Effectue le produit de deux matrices 2x2'''
    res = [ [0, 0], [0, 0] ]
    res[0]=[M1[0][0]*M2[0][0]+M1[0][1]*M2[1][0], M1[0][0]*M2[0][1]+M1[0][1]*M2[1][1]]
    res[1]=[M1[1][0]*M2[0][0]+M1[1][1]*M2[1][0], M1[1][0]*M2[0][1]+M1[1][1]*M2[1][1]]
    return res

###############################################################################
# Exercice 2.2

def puissance_matrice_2_2 (M, n) :
    '''Calcule la matrice M a la puissance n par exponentiation rapide'''
    if n == 0 : return [ [1, 0], [0, 1] ]
    if n == 1 : return M
    tmp=puissance_matrice_2_2(M, n//2)
    res=produit_matrice_2_2(tmp, tmp)
    if(n%2==0):
        return res
    return produit_matrice_2_2(M, res)

def fibo_4(n) :
    '''Calcule le n-eme terme de la suite de Fibonacci'''
    if n <= 0 : return 0
    if n <= 2 : return 1
    M = [ [1, 1], [1, 0] ]
    N = puissance_matrice_2_2 (M, n-1)
    return N[0][0]

###############################################################################
# Exercice 2.3

def produit_matrice_2_2_ops (M1, M2) :
    ''' produit_matrice_2_2 avec calcul du nombre d'operations arithmétiques'''
    res = [ [0, 0], [0, 0] ]
    res[0]=[M1[0][0]*M2[0][0]+M1[0][1]*M2[1][0], M1[0][0]*M2[0][1]+M1[0][1]*M2[1][1]]
    res[1]=[M1[1][0]*M2[0][0]+M1[1][1]*M2[1][0], M1[1][0]*M2[0][1]+M1[1][1]*M2[1][1]]
    return res, 12

def puissance_matrice_2_2_ops (M, n) :
    ''' puissance_matrice_2_2 avec calcul du nombre d'operations arithmétiques''' 
    if n == 0 : return [ [1, 0], [0, 1] ], 0
    if n == 1 : return M, 0
    tmp, opsP=puissance_matrice_2_2_ops(M, n//2)
    res, opsM=produit_matrice_2_2_ops(tmp, tmp)
    opsTot=opsP+opsM
    if(n%2==0):
        return res, opsTot
    res, opsM=produit_matrice_2_2_ops(M, res)
    return res, opsTot+opsM
    
  
def fibo_4_ops(n) :
    '''fibo_4 avec calcul du nombre d'operations arithmétiques'''
    if n <= 0 : return 0, 0
    if n <= 2 : return 1, 0
    M = [ [1, 1], [1, 0] ]
    M, ops = puissance_matrice_2_2_ops(M, n-1)
    return M[0][0], ops

###############################################################################
# Exercice 2.4

def courbes_ops(n, pas=1) :
    ''' affiche les courbes des additions effectuées par fibo_3 et des
    operations arithmétiques effectuées par fibo_4 '''
    ops = [[]] * 2
    col = tp2_ex1.colors()        

    ops[0] = [ tp2_ex1.fibo_3_adds(i)[1] for i in range(0, n, pas) ]
    plot(range(0,n,pas), ops[0], col[2])
    
    ops[1] = [ fibo_4_ops(i)[1] for i in range(0, n, pas) ]
    plot(range(0,n,pas), ops[1], col[3])
    
    show()

###############################################################################
# Exercice 2.5

def produit_matrice_2_2_bits (M1, M2) :
    ''' produit_matrice_2_2 avec calcul du nombre d'operations sur les bits'''
    res = [ [0, 0], [0, 0] ]
    res[0]=[M1[0][0]*M2[0][0]+M1[0][1]*M2[1][0], M1[0][0]*M2[0][1]+M1[0][1]*M2[1][1]]
    res[1]=[M1[1][0]*M2[0][0]+M1[1][1]*M2[1][0], M1[1][0]*M2[0][1]+M1[1][1]*M2[1][1]]
   
    bitsMul=tp2_ex1.nbOfBits(M1[0][0])*tp2_ex1.nbOfBits(M2[1][0])
    bitsMul+=tp2_ex1.nbOfBits(M1[0][0])*tp2_ex1.nbOfBits(M2[1][0])
    bitsMul+=tp2_ex1.nbOfBits(M1[0][0])*tp2_ex1.nbOfBits(M2[1][0])
    bitsMul+=tp2_ex1.nbOfBits(M1[0][0])*tp2_ex1.nbOfBits(M2[1][0])
    bitsMul+=tp2_ex1.nbOfBits(M1[0][0])*tp2_ex1.nbOfBits(M2[1][0])
    bitsMul+=tp2_ex1.nbOfBits(M1[0][0])*tp2_ex1.nbOfBits(M2[1][0])
    bitsMul+=tp2_ex1.nbOfBits(M1[0][0])*tp2_ex1.nbOfBits(M2[1][0])
    bitsMul+=tp2_ex1.nbOfBits(M1[0][0])*tp2_ex1.nbOfBits(M2[1][0])
     
    bitsAdd=tp2_ex1.nbOfBits(M1[0][0]*M2[0][0]+M1[0][1]*M2[1][0])
    bitsAdd+=tp2_ex1.nbOfBits(M1[0][0]*M2[0][1]+M1[0][1]*M2[1][1])
    bitsAdd+=tp2_ex1.nbOfBits(M1[1][0]*M2[0][0]+M1[1][1]*M2[1][0])
    bitsAdd+=tp2_ex1.nbOfBits(M1[1][0]*M2[0][1]+M1[1][1]*M2[1][1])
    
    return res, bitsMul+bitsAdd

def puissance_matrice_2_2_bits (M, n) :
    '''puissance_matrice_2_2 avec calcul du nombre d'operations sur les bits'''
    if n == 0 : return [ [1, 0], [0, 1] ], 0
    if n == 1 : return M, 0
    tmp, opsP=puissance_matrice_2_2_bits(M, n//2)
    res, opsM=produit_matrice_2_2_bits(tmp, tmp)
    opsTot=opsP+opsM
    if(n%2==0):
        return res, opsTot
    res, opsM=produit_matrice_2_2_bits(M, res)
    return res, opsTot+opsM


def fibo_4_bits(n) :
    '''fibo_4 avec calcul du nombre d'operations sur les bits'''
    if n <= 0 : return 0, 0
    if n <= 2 : return 1, 0
    M = [ [1, 1], [1, 0] ]
    M, ops = puissance_matrice_2_2_bits(M, n-1)
    return M[0][0], ops

###############################################################################
# Exercice 2.6

def courbes_bits(n, pas=1) :
    ''' affiche les courbes des operations elementaires effectuees pour
    le calcul de Fn par les algos fibo_3 et fibo_4 '''
    ops = [[]] * 2
    col = tp2_ex1.colors()
    
    ops[0] = [ tp2_ex1.fibo_3_bits(i)[1] for i in range(0, n, pas) ]
    plot(range(0,n,pas), ops[0], col[2])
    ops[1] = [ fibo_4_bits(i)[1] for i in range(0, n, pas) ]
    plot(range(0,n,pas), ops[1], col[3])
    
    show()
    
# Avec les courbes des opérations, fibo_4 était plus efficace que fibo_3 à partir
# d'un certain moment. Or avec les courbes en bits, c'est l'inverse qui est montré.
# Or c'est en effet fibo_4 qui est plus efficace, donc nous pouvons penser que la
# multiplication n'est pas codée comme nous l'avions fait dans les fonctions.
  
#####################################################################################
#####################################################################################
################# TESTS #############################################################
def test_prod22Data() :
    return [ [ [ [3, 0], [5, 6] ], [ [1, 4], [2, 1] ], [ [3, 12 ], [ 17 , 26 ] ] ] ,
           [ [ [20, 16], [ 5, 10 ]  ],  [ [10, 7], [2, 3] ],  [ [232, 188], [70, 65] ] ],
           [ [  [60, 5], [4, 1]  ], [ [54, 30], [11, 4]  ], [ [3295, 1820], [227, 124]] ],
           [ [ [34, 70], [2, 18] ], [ [56, 29], [10, 16] ], [ [2604, 2106], [292, 346]] ],
          [ [ [54, 30], [11, 4] ], [ [60, 5], [4, 1] ] , [ [3360, 300], [676, 59] ] ]
    ]

def test_prod22():
    printcol('{Test produit_matrice_2_2:}','bold')
    score = 0
    data = test_prod22Data()
    ldata = len(data)
    for i, dt in enumerate(data) :
        print('** test %d/%d : ' % (i + 1, ldata), end='')
        n = dt[0]
        refr = dt[ 2 ]
        fb = produit_matrice_2_2 ( dt[0], dt[1] )
        if fb == refr :
            score += 1
            printcol('{ok}','green')
        else :
            printcol('{Mauvais résultat}','red')
            print('    entree  : %s x %s' % (n,dt[1]))
            print('    calcule : %s' % fb)
            print('    attendu : %s' % refr)
    printcol('{** Score %d/%d : %s}' % (score, ldata, "super !" if score==ldata else "essaie encore !"),'bold')
    print()

def test_puissanceData() :
    return [ [ [ [1, 4], [2, 1] ], 2, [[9, 8], [4, 9]]],
           [ [ [3, 0], [5, 6] ], 3, [[27, 0], [315, 216]] ],
           [ [ [3, 12 ], [ 17 , 26 ] ], 8, [[252018687525, 442324154508], [626625885553, 1099806650332]] ],
           [ [ [20, 16], [ 5, 10 ]  ], 6,  [[192672000, 202176000], [63180000, 66312000]] ],
           [ [ [232, 188], [70, 65] ], 5, [[1640021237352, 1367968885308], [509350116870, 424857387105]] ],
           [ [ [10, 7], [2, 3] ], 4, [[15362, 12467], [3562, 2895]] ],
           [ [  [60, 5], [4, 1]  ], 7 , [[2894868940020, 243934318805], [195147455044, 16443978121]] ],
           [ [ [54, 30], [11, 4]  ], 10, [[538139813380894176, 288789026527531200], [105889309726761440, 56824769168342176]] ],
           [ [ [34, 70], [2, 18] ], 3,  [[51344, 156240], [4464, 15632]] ],
           [ [ [56, 29], [10, 16] ], 2, [[3426, 2088], [720, 546]] ],
           [ [ [54, 30], [11, 4] ], 5 ,  [[697669224, 374399400], [137279780, 73670224]] ],
           [ [ [60, 5], [4, 1] ], 9, [[10538945536660820, 888057647401005], [710446117920804, 59865297328961]] ]
    ]

#
# NE PAS MODIFIER
#
def test_puissance():
    printcol('{Test puissance_matrice_2_2:}','bold')
    score = 0
    data = test_puissanceData()
    ldata = len(data)
    for i, dt in enumerate(data) :
        print('** test %2d/%2d : ' % (i + 1, ldata), end='')
        n = dt[0]
        refr = dt[ 2 ]
        fb = puissance_matrice_2_2 ( dt[0], dt[1] )
        if fb == refr :
            score += 1
            printcol('{ok}','green')
        else :
            printcol('{Mauvais résultat}','red')
            print('    entree  : %s ^ %d' % (n,dt[1]))
            print('    calcule : %s' % fb)
            print('    attendu : %s' % refr)
    printcol('{** Score %d/%d : %s}' % (score, ldata, "super !" if score==ldata else "essaie encore !"),'bold')
    print()

#
# AJOUTER D'AUTRES DONNEES DE TEST
# [<valeur n>, (<valeur F_n> , <nb ops possibles>) ]
def test_fibo_4_opsData() :
  return [
 (0,  (0,    {0})),
 (1,  (1,    {0})),
 (2,  (1,    {0})),
 (3,  (2,    {12,14,16,18})),
 (4,  (3,    {24,26,32,34})),
 (5,  (5,    {24,28,32,36})),
 (6,  (8,    {36,40,48,52})),
 (7,  (13,   {36,40,48,52})),
 (8,  (21,   {48,52,64,68})),
 (9,  (34,   {36,42,48,54})),
 (10, (55,   {48,54,64,70})),
 (11, (89,   {48,54,64,70})),
 (12, (144,  {60,66,80,86})),
 (13, (233,  {48,54,64,70})),
 (14, (377,  {60,66,80,86})),
 (15, (610,  {60,66,80,86})),
 (16, (987,  {72,78,96,102})),
 (17, (1597, {48,56,64,72})),
 (18, (2584, {60,68,80,88})),
 (19, (4181, {60,68,80,88}))]

#
# NE PAS MODIFIER
#
def test_fibo_4():
    printcol('{Test fibo_4_ops:}','bold')
    score = 0
    data = test_fibo_4_opsData()
    ldata = len(data)
    for i, dt in enumerate(data) :
        print('** test %2d/%2d : ' % (i + 1, ldata), end='')
        n = dt[0]
        Tres, Tops = dt[1]
        Tops=sorted(list(Tops))
        fb, ops = fibo_4_ops(n)
        if (fb == Tres and ops in Tops):
            score += 1
            printcol('{ok}','green')
        elif (fb == Tres):
            printcol('{Mauvais nombre d\'opérations}','yellow')
            print('    entree  : %s' % n)
            print('    calcule : %d en %d ops' % (fb,ops) )
            print('    attendu : %d en %s ops' % (Tres,(" ou ".join([str(i) for i in Tops])) ) )
        else :
            printcol('{Mauvais résultat}','red')
            print('    entree  : %s' % n)
            print('    calcule : %d en %d ops' % (fb,ops) )
            print('    attendu : %d en %s ops' % (Tres,(" ou ".join([str(i) for i in Tops])) ) )
    printcol('{** Score %d/%d : %s}' % (score, ldata, "super !" if score==ldata else "essaie encore !"),'bold')
    print()

  
  


if __name__ == '__main__':
  test_prod22()
  test_puissance()
  test_fibo_4()
  courbes_ops(1000,1)      
  courbes_bits(1000,1)