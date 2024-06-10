#!/usr/bin/env python3

from time import process_time
from random import randint

def mesure_temps(f, *param, rep=1, cle=True):
    tps = 0
    for i in range(rep):
      if cle:
        x = randint(1,1000000)
        deb = process_time()
        f(x, *param)
        tps += (process_time() - deb)
      else:
        deb = process_time()
        f(*param)
        tps += (process_time() - deb)
    return tps/rep
    
def cherche(x, I):
    for elem in I:
        if(elem==x): return True
    return False

def nb_elts_diff_liste(L):
    L, res=triFusion(L), 0
    for i in range(len(L)-1):
        if(L[i]!=L[i+1]): res+=1
    return res

def triFusion(L):
    if(len(L)>1): L=fusion(triFusion(L[:(len(L)//2)]), triFusion(L[(len(L)//2):]))
    return L
    
def fusion(L1, L2):
    c1, c2, res=0, 0, []
    for i in range(len(L1)+len(L2)):
        if(c1==len(L1)): return res+L2[c2:]
        if(c2==len(L2)): return res+L1[c1:]
        if(L1[c1]<L2[c2]):
            res+=[L1[c1]]
            c1+=1
        else:
            res+=[L2[c2]]
            c2+=1
    return res

def nb_elts_diff_ens(E):
    '''res, E2=0, set()
    for elem in E:
        if(elem not in E2):
            E2.add(elem)
            res+=1
    return res'''
    return len(E) #ensemble sans doublon

def comparaison_rech(L, E):
    print()
    print("##############################################################")
    print()
    print("temps moyen d'acces a une liste de longeur", len(L),":", mesure_temps(cherche,L,rep=10))
    print("temps moyen d'acces a un ensemble de longueur", len(L),":", mesure_temps(cherche,E,rep=10))

  
def comparaison_nb_elts(L, E):
    print()
    print("##############################################################")
    print()
    print("temps moyen pour compter les elements distincts dans une liste de longeur", len(L),":", mesure_temps(nb_elts_diff_liste, L, rep=10, cle=False))
    print("temps moyen pour compter les elements distincts dans un ensemble de longueur", len(L),":", mesure_temps(nb_elts_diff_ens, E, rep=10, cle=False))
    print()
    print("##############################################################")
    print()

if __name__ == '__main__':
    L=[randint(1,1000000) for i in range(1000000)]
    E=set(L)
    comparaison_rech(L, E)
    comparaison_nb_elts(L, E)
