#!/usr/bin/env python3

from random import randint
from time import process_time
import matplotlib.pyplot as plt
import math
# Pour l'affichage des résultats
from ea4lib import printcol

MARQUE = (None, None)
A = (math.sqrt(5)-1)/2

def creer_table(puiss, h, tmin, tmax):
    taille=2**puiss
    return [[None]*taille, h, taille, tmin, tmax, 0]

def redimensionner(table, t):
    table[2]=t
    tmp=table[0]
    table[0]=[None]*t
    for cle in tmp:
        if(cle!=None and cle!=MARQUE):
            pos=rechercher(table, cle, True)
            table[0][pos]=cle
    return table

def rechercher(table, cle, flag=False):
    for pos in gen_hash(hash1, hash2, cle, table[2]):
        if(table[0][pos]==None):
            if(flag): return pos
            return None
        if(table[0][pos]==cle): return pos

def inserer(table, cle):
    if(cle!=None):
        if(table[5]/table[2]>=table[4]): table=redimensionner(table, table[2]*2)
        pos=rechercher(table, cle, True)
        if(table[0][pos]!=cle):
            table[0][pos]=cle
            table[5]+=1
    return table

def supprimer(table, cle):
    if(cle!=None):
        pos=rechercher(table, cle, False)
        if(pos!=None):
            table[0][pos]=MARQUE
            table[5]-=1
            if(table[5]/table[2]<=table[3]): table=redimensionner(table, table[2]//2)
    return table

##############################################################
#
# fonctions de hachage
#

def gen_hash(h1, h2, cle, taille):
    ind = h1(cle, taille) % taille
    pas = h2(cle, taille)
    for i in range(taille):
        yield ind
        ind = (ind+pas) % taille

def hash1(cle, taille):
    return cle

def hash2(cle, taille):
    return 1

def hash3(cle, taille):
    return ((cle*A)%1)*taille

def hash4(cle, taille):
    return 2*cle+1

##############################################################
#
# Mesures
#

def random_liste(taille, maxi):
    L = []
    S = set()
    for i in range(taille):
        r = randint(0, maxi)
        while r in S:
            r = randint(0, maxi)
        S.add(r)
        L.append(r)
    return L

def liste_paquets(taille, maxi):
    L = []
    S = set()
    i = 0
    while i < taille :
        r = randint(0, maxi)
        while r in S:
            r = randint(0, maxi)
        S.add(r)
        L.append(r)
        i += 1
        if i == taille: break
        for j in range(maxi//200):
            x = r+randint(0, maxi//200)-maxi//400
            if not x in S:
                S.add(x)
                L.append(x)
                i += 1
                if i == taille: break
        
    return L

def taille_max_cluster(table):
    cles, h, taille, tmin, tmax, nbCles = table
    maxi = 0
    max_tmp = 0
    for i in range(taille):
        if cles[i] == None:
            maxi = max_tmp if max_tmp > maxi else maxi
            max_tmp = 0
        else:
            max_tmp += 1
    return maxi

def stats(h, taille_max, tmin, tmax, alea=False, rep=1, pas=10, redim=False):
    t_crea = [None] * (taille_max//pas)
    clust = [None] * (taille_max//pas)
    t_rech = [None] * (taille_max//pas)
    if not redim:
        p = 1
    for i in range(0,taille_max,pas):
        cluster = 0
        tps = 0
        max_cl = 0
        tr = 0
        for j in range(rep):
            if alea: L = random_liste(i+1, 100000)
            else: L = liste_paquets(i+1, 100000)
            #else: L = liste_collisions(i+1, j+1)
            if redim: table = creer_table(6, h, tmin, tmax)
            else: table = creer_table(p, h, tmin, tmax)
            for cle in L:
                deb = process_time()
                table = inserer(table, cle)
                tps += process_time() - deb
            cluster += taille_max_cluster(table)
            tr += test_recherche(table, L)
        t_crea[i//pas] = tps/rep
        clust[i//pas] = cluster/rep
        t_rech[i//pas] = tr/rep
        if not redim:
            p += 1
    return t_crea, clust, t_rech




def test_recherche(table, L):
    tps = 0
    for cle in L: 
        deb = process_time()
        rechercher(table, cle)
        tps += process_time() - deb
    #print("temps moyen pour la recherche dans la table", table[1].__name__, ":", tps)
    return tps/len(L)
    
##############################################################
#
# Courbes
#

def mesure(algo, T) :
    debut = process_time()
    algo(T)
    return process_time() - debut

couleurs = ['b', 'g', 'r', 'm', 'c', 'k', 'y']


def courbes(liste_h, taille_max, tmin, tmax, alea=False, styleLigne='-', rep=1, pas=10, redim=False):
    x = [i for i in range(0,taille_max,pas)]
    s = [None] * len(liste_h)
    for i, h in enumerate(liste_h):
        s[i] = stats(h, taille_max, tmin, tmax, alea, rep, pas, redim)
    for i in range(len(s)):
        tps = s[i][0]
        plt.plot(x, tps, color=couleurs[i%len(couleurs)], linestyle=styleLigne, label=liste_h[i][0].__name__+"_"+liste_h[i][1].__name__[-1])        
    affiche("tps d'exec moyen pour céer une table par insertions successives")
    for i in range(len(s)):
        clust = s[i][1]
        plt.plot(x, clust, color=couleurs[i%len(couleurs)], linestyle=styleLigne, label=liste_h[i][0].__name__+"_"+liste_h[i][1].__name__[-1])
    affiche("taille moyenne du cluster max")
    for i in range(len(s)):
        clust = s[i][2] 
        plt.plot(x, clust, color=couleurs[i%len(couleurs)], linestyle=styleLigne, label=liste_h[i][0].__name__+"_"+liste_h[i][1].__name__[-1])
    affiche("temps d'execution moyen pour une recherche reussie")

def affiche(label) :
  plt.xlabel('taille du tableau')
  plt.ylabel(label)
  plt.legend(loc='upper left')
  plt.show()
  
##############################################################
#
# Main
#

if __name__ == '__main__':
    L = [13, 15, 70, 28, 18, 7, 20, 6, 5, 8, 32, 4, 38]
    printcol("{création de la table}", 'bold')
    table = creer_table(2, [hash1, hash2], 0.25, 0.5)
    for cle in L:
        table = inserer(table, cle)
    print("  liste de cles :",L)
    if table != None :
        print("  table de hachage :", table[0])
        print("  taille de la table :", table[2])
    print()
    trouve = True
    for cle in L:
        if(rechercher(table, cle)) == None:
            printcol("{  Il y a un problème : }", 'red', end='')
            print("cle ", cle, " non trouvée")
            trouve = False
    if trouve:
        printcol("{  insertions et recherches réussies. Bravo !}", 'green')
    print()

    printcol("{suppression de l'element 6:}", 'bold')
    if table != None :
        print("  ", supprimer(table, 6)[0])
        print()
        if rechercher(table, 6) == None :
            printcol("{  suppression réussie. Bravo !}", 'green')
        else:
            printcol("{  echec de la suppression}", 'red')
    print()
    
    printcol("{recherche de l'élément 38: }", 'bold', end="")
    if rechercher(table, 38) != None :
         printcol("{recherche réussie. Bravo !}", 'green')
    else:
        printcol("{echec de la recherche. Attention hash1(6)%32 = hash1(38)%32}", 'red')
    print()
    
    printcol("{puis insertion de l'element 6:}", 'bold')
    if table != None :
        print("  ", inserer(supprimer(table, 6), 6)[0])
        print()
        if rechercher(table, 6) != None :
            printcol("{  insertion de 6 réussie. Bravo !}", 'green')
        else:
            printcol("{  echec de l'insertion}", 'red')
    print()
    
    printcol("{test redimensionnement avec ajouts de [19, 78, 234, 93]: }", 'bold', end="")
    for cle in [19, 78, 234, 93]:
        table = inserer(table, cle)
    if table != None :
        if table[2] == 64 :
            printcol("{redimensionnement lors de l'insertion réussie. Bravo !}", 'green')
        else:
            printcol("{échec du redimensionnement lors de l'insertion}", 'red')
    else:
        print()
    print()
    
    printcol("{test redimensionnement avec suppressions de [15, 18, 7, 20]:} ", 'bold', end="")
    for cle in [15, 18]:
        table = supprimer(table, cle)
    if table != None :
        if table[2] == 32 :
            printcol("{redimensionnement lors de la suppression réussie. Bravo !}", 'green')
        else:
            printcol("{échec du redimensionnement lors de la suppression}", 'red')
    print()
    
        
    #Testez différents taux...
    tmin, tmax = 0.1, 0.95
    #Completez la liste avec d'autres fonctions de hachage.
    liste_hash = [[hash1,hash2], [hash3, hash2], [hash1, hash4], [hash3, hash4]]

    #Tests avec jusqu'à 2000 (par pas de 50) clés de valeurs proches par plages, avec taux tmin et tmax.
    #Pour chaque taille, on fait des tests sur rep=10 listes.
    courbes(liste_hash, 2000, tmin, tmax, rep=10, pas=100)

    #Tests avec jusqu'a 2000 (par pas de 50) clés aléatoires, avec taux tmin et tmax.
    #Pour chaque taille, on fait des tests sur rep=10 listes.
    courbes(liste_hash, 2000, tmin, tmax, True, rep=10, pas=100)

    #Avec redimensionnement

    #Tests avec jusqu'à 2000 (par pas de 50) clés de valeurs proches par plages, avec taux tmin et tmax.
    #Pour chaque taille, on fait des tests sur rep=10 listes.
    #courbes(liste_hash, 2000, tmin, tmax, rep=10, pas=100, redim=True)

    #Tests avec jusqu'a 2000 (par pas de 50) clés aléatoires, avec taux tmin et tmax.
    #Pour chaque taille, on fait des tests sur rep=10 listes.
    #courbes(liste_hash, 2000, tmin, tmax, True, rep=10, pas=100, redim=True)
    
    
    
    '''
        Quand les valeurs sont très proches, les temps d'exécution et la taille des clusters
        sont globalement largement supérieur par rapport au cas où les valeurs sont distribuées.
        Ce serait donc beaucoup plus efficace d'avoir des valeurs pas trop proches dans la table.
        
        Quand l'écart entre tmin et tmax est petit, les temps et la taille des clusters sont
        globalement plus basses dans les deux cas.
        Inversement, quand cet écart est grand : les chiffres sont plus petits quand les valeurs
        sont distribuées, et plus grands quand les valeurs sont proches.
    '''
