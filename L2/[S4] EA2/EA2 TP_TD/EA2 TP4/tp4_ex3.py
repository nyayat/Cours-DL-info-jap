#!/usr/bin/env python3

#importe indirectement tp4, data, ea4lib et tp4_ex* pour * < 2
from tp4_ex2 import *
import random

#
# À COMPLÉTER !
#
def suppressionABR(arbre, elt, alea=False) :
  ''' supprime le noeud d'étiquette elt dans l'arbre; en version
  déterministe, remplacement par le prédécesseur; sinon, à pile ou face '''
  if(not contientABR(arbre, elt)): return
  place=rechercheABR(arbre, elt)
  if(estFeuilleVide(arbre, filsGauche(arbre, place)) and estFeuilleVide(arbre, filsDroit(arbre, place))):
      setFilsDroit(arbre, place, None)
      setFilsGauche(arbre, place, None)
      setEtiquette(arbre, place, None)
  else:
      toRaise=-1#noeud qu'on va monter
      if(not(estFeuilleVide(arbre, filsGauche(arbre, place)) or estFeuilleVide(arbre, filsDroit(arbre, place)))):
          ind, pile=-1, random.randint(0, 1)#pile=0=gauche, face=1=droite
          if(not alea or pile==0):
              ind=maximumABR(arbre, filsGauche(arbre, place))#prédecesseur
              toRaise=filsGauche(arbre, ind)
          else:
              ind=minimumABR(arbre, filsDroit(arbre, place))#successeur
              toRaise=filsDroit(arbre, ind)
          setEtiquette(arbre, place, etiquette(arbre, ind))
          place=ind#pour généraliser les cas un fils et deux fils, à partir de la ligne 34
      else:#un fils
          if(estFeuilleVide(arbre, filsGauche(arbre, place))): toRaise=filsDroit(arbre, place)
          else: toRaise=filsGauche(arbre, place)
      if(estRacine(arbre, place)):
          setEtiquette(arbre, place, etiquette(arbre, toRaise))
          setFilsGauche(arbre, place, filsGauche(arbre, toRaise))
          setPere(arbre, filsGauche(arbre, toRaise), 0)
          setFilsDroit(arbre, place, filsDroit(arbre, toRaise))
          setPere(arbre, filsDroit(arbre, toRaise), 0)
      else:
          setPere(arbre, toRaise, pere(arbre, place))
          if(estFilsGauche(arbre, place)): setFilsGauche(arbre, pere(arbre, place), toRaise)
          else: setFilsDroit(arbre, pere(arbre, place), toRaise)


#####################################################################
##  TESTS
#####################################################################

def testSuppression():
  arbres = [arbre3ABR1, arbre3ABR1, arbre10ABR2, arbre10ABR2, arbre100ABR1, arbre100ABR1, arbre100ABR1, arbre100ABR1]
  elements = [1, 4, 3, 7, 55, 1, 49, 43]
  res = res_suppression()
  score = 0
  print('Test Suppression')
  for i in range(len(arbres)):
    print (' - test %d/%d: ' % (i + 1, len(arbres)), end='')
    a = copier(arbres[i])
    dessineArbreBinaire(a,'avant_'+str(i))
    elt = elements[i]
    suppressionABR(a,elt)
    if egalite(a,res[i]):
      printcol(" {ok}", "green")
      score += 1
      dessineArbreBinaire(a,'obtenu_'+str(i))
    else:
        printcol(" {echec}", "red", end='')
        #print(": obtenu ", a, end='')
        #print(" <> attendu ", res[i])
        dessineArbreBinaire(a,'obtenu_'+str(i))
        dessineArbreBinaire(res[i], 'attendu_'+str(i))

  printcol ('  score {%d/%d} ' % (score, len(arbres)), "cyan")
    
if __name__ == '__main__':
  testSuppression()
