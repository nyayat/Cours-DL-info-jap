#!/usr/bin/env python3

import tp5_ex2_ex3 as t
import math

def mot_to_int(mot) :
    res=0
    for i in range(len(mot)): res+=ord(mot[i])*31**(len(mot)-i)
    return res%(2**32)

def creer_dico(taille=0) :
    if(taille!=0): taille=math.log(taille, 2)
    return t.creer_table(taille, [t.hash3, t.hash4], 0.25, 0.5)

def ajouter_mot(dico, mot) :
    return t.inserer(dico, mot_to_int(mot))

def retirer_mot(dico, mot) :
    return t.supprimer(dico, mot_to_int(mot))

def dans_dico(dico, mot):
    return t.rechercher(dico, mot_to_int(mot), False)!=None

##############################################################
#
# crée un générateur des mots contenus dans le roman de Marcel Proust
#
def proust() :
    with open("proust.txt", 'r', encoding='utf-8') as f :
        for ligne in f :
            for mot in ligne.split() :
                tmp = mot.strip('-,.?!;:"«»()^').lower()
                if tmp != '' : yield tmp


##############################################################
#
# Main
#

if __name__ == '__main__':
    dico=creer_dico()
    for mot in proust(): ajouter_mot(dico, mot)
    print("Il y a", dico[5], "mots dans le livre.")
    print("Le mot \"temps\" y est avant suppression :", dans_dico(dico, "temps"))
    retirer_mot(dico, "temps")
    print("Le mot \"temps\" y est après suppression :", dans_dico(dico, "temps"))
