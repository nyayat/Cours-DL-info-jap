#!/usr/bin/env python3

#importe indirectement tp4, data, et tp4_ex* pour * < 3
from tp4_ex3 import *
from math import sqrt

#
# À COMPLÉTER !
#
def genererABRparInsPuisSup(perm) :
    ''' renvoie un ABR de taille n construit par insertions successives
    des éléments de la permutation perm (de taille n^2), puis suppression
    d'éléments aléatoires '''
    res=genererABRparInsertion(perm)
    nbDel=int(len(perm)-sqrt(len(perm)))
    for i in range(nbDel):
        infixe=parcoursInfixe(res)
        ind=randint(0, len(infixe)-1)
        suppressionABR(res, infixe[ind], True)
    return res
    
#
# À COMPLÉTER !
#
def statsHauteursABRparInsPuisSup(n, m) :
    ''' renvoie le tableau des hauteurs de m arbres de taille n,
    construits par genererABRparInsPuisSup '''
    res=[]
    for i in range(m):
        tmp=genererABRparInsPuisSup(permutation(n))
        res+=[hauteur(tmp)]
    return res

#
# À COMPLÉTER !
#
def genererABRparInsEtSup(permins, permsup) :
    ''' renvoie un couple (ABR, taille) construit par
    insertions/supressions successives entremêlées des éléments de
    permins et permsup respectivement '''
    res=nouvelABRVide()
    taille=0
    for i in range(len(permins)):
        if(random.randint(0,1)==0):#0=insertion, 1=suppression
            insertionABR(res, permins[i])
            taille+=1
        else:
            if(contientABR(res, permsup[i])):
                suppressionABR(res, permsup[i])
                taille-=1
    return res, taille

#
# À COMPLÉTER !
#
def statsHauteursABRparInsEtSup(n, m) :
    ''' renvoie le tableau des (taille, hauteur)s de m arbres
    construits par genererABRparInsEtSup sur 2 permutations de taille 2n'''
    res=[[],[]]
    for i in range(m):
        arbre, taille=genererABRparInsEtSup(permutation(2*n), permutation(2*n))
        res[0]+=[taille]
        res[1]+=[hauteur(arbre)]
    return res

#
# NE PAS MODIFIER
#

def tracerInsPuisSup(limite, pas, m):
    lx, ly, ly_moy = [], [], []
    for i in range(1, limite, pas) :
        tmp = statsHauteursABRparInsPuisSup(i, m)
        lx.extend([i]*m)
        ly.extend(tmp)
        ly_moy.append(sum(tmp)/m)
    plt.plot([(math.log(i,2) if i>0 else 0) for i in range(limite)], color="blue")
    plt.plot(lx, ly, '.', color="orange")
    plt.plot(range(1,limite,pas), ly_moy, color="red")
    plt.ylabel('hauteur(n)')
    plt.xlabel('n = nombre noeuds')
    plt.title('Distribution des hauteurs d\'arbres aléatoires obtenus par insertions puis suppressions')
    plt.show()

def tracerInsEtSup(limite, pas, m):
    lx, ly = [], []
    plt.plot([(math.log(i,2) if i>0 else 0) for i in range(limite)], color="blue")
    for i in range(1, limite, pas) :
        tailles, hauteurs = list(zip(statsHauteursABRparInsEtSup(i, m)))
        lx.extend(tailles)
        ly.extend(hauteurs)
    plt.plot(lx, ly, '.', color="green")
    plt.ylabel('hauteur(n)')
    plt.xlabel('n = nombre noeuds')
    plt.title('Distribution des hauteurs d\'arbres aléatoires obtenus par insertions et suppressions')
    plt.show()


if __name__ == '__main__':
    #tracerInsPuisSup(100,5,5)
    tracerInsEtSup(1000,50,10)