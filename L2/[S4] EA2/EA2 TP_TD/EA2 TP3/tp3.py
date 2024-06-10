#DAI Anna 21953144

#!/usr/bin/env python3

import sys

version = sys.version_info
if version.major < 3:
    sys.exit(
        "Python2 n'est PLUS supporté depuis le 1er Janvier 2020, merci d'installer Python3"
    )

import random
from time import process_time as clock

try:
    import matplotlib.pyplot as plt
except ModuleNotFoundError:
    sys.exit("Le module matplolib est nécessaire pour ce TP.")

############################################################
# Exercice 1.1
#
# Tri selection
#

def triSelection(T):
    for i in range(len(T)-1):
        indMin=i;
        for j in range(i+1, len(T)):
            if(T[j]<T[indMin]):
                indMin=j
        T[i], T[indMin]=T[indMin], T[i]
    return T

############################################################
# Exercice 1.2
#
# randomPerm prend en paramètre un entier n et renvoie une
# permutation aléatoire de longueur n
#

def randomPerm(n):
    res=[i+1 for i in range(n)]
    for i in range(n):
        indAlea=random.randint(i, n-1)
        res[i], res[indAlea]=res[indAlea], res[i]
    return res

############################################################
# Exercice 1.3
#
# testeQueLaFonctionTrie prend en paramètre une fonction de
# tri f et l’applique sur des permutations aléatoires de
# taille i variant de 2 à 100 et vérifie que le résultat est
# un tableau trié
#

def testeQueLaFonctionTrie(f):
    for i in range(2, 101):
        t=randomPerm(i)
        trie=f(t)
        for i in range(len(t)):
            if(trie[i]!=i+1):
                print(t)
                return False
    return True

############################################################
# Exercice 1.4
#
# randomTab prend des entiers n, a et b et renvoie un tableau
# aléatoire de taille n contenant des entiers compris entre
# les bornes a et b.
#

def randomTab(n,a,b):
    T = [0]*n
    for i in range(n):
        T[i]=random.randint(a, b)
    return T

############################################################
# Exercice 1.5
#
# derangeUnPeu prend des entiers n, k et un booleen rev et
# effectue k échanges entre des positions aléatoires sur la
# liste des entiers de 1 à n si rev vaut False ou sur la
# liste des entiers n à 1 si rev vaut True.
#

def derangeUnPeu(n,k,rev):
    T = [n-i for i in range(n)] if rev else [i+1 for i in range(n)]
    for i in range(k):
        pos1=random.randint(0, n-1)
        pos2=random.randint(0, n-1)
        T[pos1], T[pos2]=T[pos2], T[pos1]
    return T


############################################################
# Exercice 2.1
#
# Trois variantes du tri par insertion : Avec échanges successifs,
# insertion directe à la bonne position, et avec recherche
# dichotomique de la positio
#

def triInsertionEchange(T):
    for i in range(1, len(T)):
        j=i-1
        while(j>=0 and T[j]>T[j+1]):
            T[j], T[j+1]=T[j+1], T[j]
            j-=1
    return T

def triInsertionRotation(T):
    for i in range(1, len(T)):
        j=i
        while(j-1>=0 and T[j-1]>T[i]):
            j-=1
        tmp=T[i]
        for k in range(i, j, -1):
            T[k]=T[k-1]
        T[j]=tmp
    return T

def triInsertionRapide(T):
    for i in range(1, len(T)):
        if(T[i]<T[i-1]):
            first=0
            last=i-1
            mid=last//2
            while(last>0 and last!=first):
                if(T[mid]>T[i]): last=mid
                else: first=mid+1
                mid=(first+last)//2
            tmp=T[i]
            for k in range(i, mid, -1):
                T[k]=T[k-1]
            T[mid]=tmp
    return T

############################################################
# Exercice 2.2
#
# Tri fusion
#

def fusion(T1, T2):
    c1, c2, res=0, 0, []
    for i in range(len(T1)+len(T2)):
        if(c1==len(T1)): return res+T2[c2:]
        if(c2==len(T2)): return res+T1[c1:]
        if(T1[c1]<T2[c2]):
            res+=[T1[c1]]
            c1+=1
        else:
            res+=[T2[c2]]
            c2+=1
    return res

def triFusion(T) :
    if(len(T)>1): T=fusion(triFusion(T[:(len(T)//2)]), triFusion(T[(len(T)//2):]))
    return T

############################################################
# Exercice 2.3
#
# Tri à bulles
#

def triBulles(T) :
    for i in range(len(T)-1, 0, -1):
        for j in range(i):
            if(T[j+1]<T[j]): T[j], T[j+1]=T[j+1], T[j]
    return T


############################################################
# Exercice 3.1
#
# Tri rapide avec mémoire auxiliaire et en place
#

def triRapideAuxiliaire(T):
    pivot=T[0]
    petit=[e for e in T if e<pivot]
    grand=[e for e in T if e>pivot]
    return petit, pivot, grand

def triRapide(T):
    if(len(T)<2): return T
    petit, pivot, grand=triRapideAuxiliaire(T)
    return triRapide(petit)+[pivot]+triRapide(grand)

    
def triRapideEnPlace(T):
    def triRapideEnPlace2(T, start, end):
        if(start<end):
            if(end-start==1 and T[start]<T[end]): return
            pivotIndex=partition(T, start, end)
            triRapideEnPlace2(T, start, pivotIndex-1)
            triRapideEnPlace2(T, pivotIndex+1, end)
    def partition(T, start, end):
        inf, pivot, sup=start+1, T[start], end
        fini=False
        while(not fini):
            while(inf<=sup and T[inf]<=pivot): inf+=1
            while(inf<=sup and T[sup]>pivot): sup-=1
            if(sup<inf): fini=True
            else: T[inf], T[sup]=T[sup], T[inf]
        T[start], T[sup]=T[sup], T[start]
        return sup
    triRapideEnPlace2(T, 0, len(T)-1)
    return T

############################################################
# Exercice 3.2
#
# Tri rapide avec mémoire auxiliaire et en place avec pivot
# aléatoire
#

def triRapideAleaAuxiliaire(T):
    pivot=T[random.randint(0,len(T)-1)]
    petit=[e for e in T if e<pivot]
    grand=[e for e in T if e>pivot]
    return petit, pivot, grand

def triRapideAleatoire(T):
    if(len(T)<2): return T
    petit, pivot, grand=triRapideAleaAuxiliaire(T)
    return triRapideAleatoire(petit)+[pivot]+triRapideAleatoire(grand)

def triRapideEnPlaceAleatoire(T):
    def triRapideEnPlaceAlea(T, start, end):
        if(start<end):
            if(end-start==1):
                if(T[start]<T[end]): return
                else:
                    T[start], T[end]=T[end], T[start]
                    return
            pivotIndex=partitionAlea(T, start, end)
            triRapideEnPlaceAlea(T, start, pivotIndex)
            triRapideEnPlaceAlea(T, pivotIndex+1, end)
    def partitionAlea(T, start, end):
        inf, pivot, sup=start, T[random.randint(start, end)], end
        fini=False
        while(not fini):
            while(inf<=sup and T[inf]<=pivot): inf+=1
            while(inf<=sup and T[sup]>pivot): sup-=1
            if(sup<=inf): fini=True
            else: T[inf], T[sup]=T[sup], T[inf]
        T[start], T[sup]=T[sup], T[start]
        return sup
    triRapideEnPlaceAlea(T, 0, len(T)-1)
    return T


############################################################
# Exercice 4.1
#
# les tableaux de taille < 15 sont triés par insertion, le
# reste avec l'algo de tri rapide usuel.
#

def triRapideAmeliore(T) :
    if(len(T)<15):
        for i in range(1, len(T)):
            if(T[i]<T[i-1]):
                first=0
                last=i-1
                mid=(i-1)//2
                while(last>0 and last!=first):
                    if(T[mid]>T[i]): last=mid
                    else: first=mid+1
                    mid=(first+last)//2
                tmp=T[i]
                for k in range(i, mid, -1):
                    T[k]=T[k-1]
                T[mid]=tmp
        return T
    else:
        petit, pivot, grand=triRapideAleaAuxiliaire(T)
        return triRapideAmeliore(petit)+[pivot]+triRapideAmeliore(grand)

############################################################
# Exercice 4.2
#
# Tri rapide seulement pour les tableaux de taille >= 15 et
# ne fait rien pour les tableaux de taille < 15
#

def triRapideIncomplet(T) :
    if(len(T)>=15):
        petit, pivot, grand=triRapideAleaAuxiliaire(T)
        T=triRapideIncomplet(petit)+[pivot]+triRapideIncomplet(grand)
    return T
    
############################################################
# Exercice 4.3
#
# Trie par insertion le résultat de triRapideIncomplet(T).
#
def triSedgewick(T) :
    return triInsertionRapide(triRapideIncomplet(T))

############################################################
# Exercice 5.1
#
# Trie par insertion le sous-tableau
# T[debut::gap] de T
#

def triInsertionPartiel(T, gap, debut) :
    for i in range(debut+gap, len(T), gap):
        if(T[i]<T[i-gap]):
            first=0
            last=i-gap
            mid=last//gap//2*gap
            while(last>0 and last!=first):
                if(T[mid]>T[i]): last=mid
                else: first=mid+gap
                mid=(first+last)//gap//2*gap
            tmp=T[i]
            for k in range(i, mid, -gap):
                T[k]=T[k-gap]
            T[mid]=tmp
    return None

############################################################
# Exercice 5.2
#
# Tri Shell

def triShell(T) :
    for gap in [57, 23, 10, 4, 1]:
        triInsertionPartiel(T, gap, 0)
    return T

##############################################################
#
# Mesure du temps
#

def mesure(algo, T) :
    debut = clock()
    algo(T)
    return clock() - debut

def mesureMoyenne(algo, tableaux) :
  return sum([ mesure(algo, t[:]) for t in tableaux ]) / len(tableaux)

couleurs = ['b', 'g', 'r', 'm', 'c', 'k', 'y', '#ff7f00', '.5', '#00ff7f', '#7f00ff', '#ff007f', '#7fff00', '#007fff' ]
marqueurs = ['o', '^', 's', '*', '+', 'd', 'x', '<', 'h', '>', '1', 'p', '2', 'H', '3', 'D', '4', 'v' ]

def courbes(algos, tableaux, styleLigne='-') :
  x = [ t[0] for t in tableaux ]
  for i, algo in enumerate(algos) :
    print('Mesures en cours pour %s...' % algo.__name__)
    y = [ mesureMoyenne(algo, t[1]) for t in tableaux ]
    plt.plot(x, y, color=couleurs[i%len(couleurs)], marker=marqueurs[i%len(marqueurs)], linestyle=styleLigne, label=algo.__name__)

def affiche(titre) :
  plt.xlabel('taille du tableau')
  plt.ylabel('temps d\'execution')
  plt.legend(loc='upper left')
  plt.title(titre)
  plt.show()

def compareAlgos (algos) :
  for tri in algos :
      if testeQueLaFonctionTrie(tri):
          print(tri.__name__ + ": OK")
      else:
          print(tri.__name__ + ": échoue")
  taille = 1000 # taille maximale des tableaux à trier
  pas = 100 # pas entre les tailles des tableaux à trier
  ech = 5 # taille de l'échantillon pris pour faire la moyenne
  print()
  print("Comparaison à l'aide de randomPerm")
  tableaux = [[i, [randomPerm(i) for j in range(ech)]] for i in range(2, taille, pas)]
  courbes(algos, tableaux, styleLigne='-')
  affiche("Comparaison à l'aide de randomPerm")
  print()
  print("Comparaison à l'aide de randomTab")
  tableaux = [[i, [randomTab(i,0,1000000) for j in range(ech)]] for i in range(2, taille, pas)]
  courbes(algos, tableaux, styleLigne='-')
  affiche("Comparaison à l'aide de randomTab")
  print()
  print("Comparaison à l'aide de derangeUnPeu (rev = False)")
  tableaux = [[i, [derangeUnPeu(i,20,False) for j in range(ech)]] for i in range(2, taille, pas)]
  courbes(algos, tableaux, styleLigne='-')
  affiche("Comparaison à l'aide de derangeUnPeu (rev = False)")
  print()
  print("Comparaison à l'aide de derangeUnPeu (rev = True)")
  tableaux = [[i, [derangeUnPeu(i,20,True) for j in range(ech)]] for i in range(2, taille, pas)]
  courbes(algos, tableaux, styleLigne='-')
  affiche("Comparaison à l'aide de derangeUnPeu (rev = True)")

##############################################################
#
# Main
#

if __name__ == '__main__':
  trisInsertion = [ triInsertionEchange, triInsertionRotation, triInsertionRapide ]
  trisLents = [ triSelection, triBulles ]
  trisRapides = [ triFusion, triRapide, triRapideEnPlace, triRapideAleatoire, triRapideEnPlaceAleatoire, triRapideAmeliore, triSedgewick, triShell]

  #exercice1
  #print("Exercice 1")
  #algos = [triSelection]
  #compareAlgos(algos)

  #exercice2
  #print("Exercice 2")
  #algos += trisInsertion + [triFusion, triBulles]
  #compareAlgos(algos)

  #exercice3
  #print("Exercice 3")
  #algos = [triRapide, triRapideEnPlace, triRapideAleatoire, triRapideEnPlaceAleatoire]+[triRapideAmeliore, triSedgewick]
  #compareAlgos(algos)

  #exercice4
  #print("Exercice 4")
  #algos2 = [triRapideAmeliore, triSedgewick]
  #compareAlgos(algos2)
  #algos += algos2
  #compareAlgos(algos)

  #exercice5
  #print("Exercice 5")
  #algos = [triShell]
  ##compareAlgos(algos)


  #compare tous les algos
  #print("Comparaisons de tous les algos")
  algos = trisInsertion + trisLents + trisRapides
  compareAlgos(algos)
 
