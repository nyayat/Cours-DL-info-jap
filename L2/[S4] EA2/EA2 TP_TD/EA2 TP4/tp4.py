#!/usr/local/bin/python3

from os import system

# *******************************
# ** Quelques fonctions utiles **
# *******************************

def nouvelABRVide() :
  return (0,[[None,None,None,0]])

def nouvelleFeuilleVide(arbre, pere) :
    ''' crée une feuille vide (de complétion), de père pere, l'ajoute
    au tableau noeuds(arbre), et renvoie son indice '''
    res = len(noeuds(arbre))
    noeuds(arbre).append([None, None, None, pere])
    return res

# *******************************
# ** Accesseurs 
# *******************************

def racine(arbre) :
  ''' retourne l'indice de la racine si elle existe '''
  return arbre[0]

def noeuds(arbre) :
  ''' retourne le tableau de noeuds de l'arbre '''
  return arbre[1]

def etiquette(arbre, i) :
  ''' retourne l'étiquette du noeud d'indice i (ou None) '''
  if i is None or i < 0 or i >= len(noeuds(arbre)) or noeuds(arbre)[i] is None : return None
  else : return noeuds(arbre)[i][0]

def filsGauche(arbre, i) :
  ''' retourne l'indice du fils gauche (ou None) '''
  if i is None or i < 0 or i >= len(noeuds(arbre)) or noeuds(arbre)[i] is None : return None
  else : return noeuds(arbre)[i][1]

def filsDroit(arbre, i) :
  ''' retourne l'indice du fils droit (ou None) '''
  if i is None or i < 0 or i >= len(noeuds(arbre)) or noeuds(arbre)[i] is None : return None
  else : return noeuds(arbre)[i][2]

def pere(arbre, i) :
  ''' retourne l'indice du pere (ou None) '''
  if i is None or i < 0 or i >= len(noeuds(arbre)) or noeuds(arbre)[i] is None : return None
  else : return noeuds(arbre)[i][3]



# *******************************
# ** Tests 
# *******************************

def estVide(arbre) :
  ''' teste si arbre est une représentation valide de l'arbre vide (complet) ''' 
  # vérifie que le tableau de noeuds contient un seul élément non-None,
  # et que celui-ci est une feuille vide
  flag = False
  for i, noeud in enumerate(noeuds(arbre)) :
      if noeud is not None :
          if racine(arbre) == i and noeud == [None, None , None, i] : 
              flag = True
  return flag

def estFeuilleVide(arbre,i):
  ''' retourne True si le noeud d'indice i est une feuille vide "de complétion" : [None,None,None,_] '''
  if i < 0 or i >= len(noeuds(arbre)) or noeuds(arbre)[i] == None : 
      print("indice <%d> incohérent" % i)
      return False
  if etiquette(arbre, i) == None and filsGauche(arbre, i) == None and filsDroit(arbre, i) == None : 
      return True
  if etiquette(arbre, i) == None or filsGauche(arbre, i) == None or filsDroit(arbre, i) == None : 
      print("contenu du noeud <%d> incohérent" % i)
      print(noeuds(arbre)[i])
  return False

def estRacine(arbre, i) :
  ''' retourne True si le noeud d'indice i est la racine de l'arbre '''
  return racine(arbre) == i

def estFilsGauche(arbre, i) :
  ''' retourne True si le noeud d'indice i est fils gauche de son pere '''
  p = pere(arbre, i)
  return False if p is None else filsGauche(arbre,p) == i

def estFilsDroit(arbre, i) :
  ''' retourne True si le noeud d'indice i est fils droit de son pere '''
  p = pere(arbre, i)
  return False if p is None else filsDroit(arbre,p) == i


def egalite(arbre1, arbre2, i1=None, i2=None) :
    ''' teste l'égalité (à encodage près) '''
    if i1 == None : i1 = racine(arbre1)
    if i2 == None : i2 = racine(arbre2)
    if estFeuilleVide(arbre1, i1) : return estFeuilleVide(arbre2, i2)
    if estFeuilleVide(arbre2, i2) : return False    
    if etiquette(arbre1, i1) != etiquette(arbre2, i2) : return False
    return egalite(arbre1, arbre2, filsGauche(arbre1, i1), filsGauche(arbre2, i2)) \
           and egalite(arbre1, arbre2, filsDroit(arbre1, i1), filsDroit(arbre2, i2))


# *******************************
# ** Setters 
# *******************************

def setRacine(arbre, i) :
  arbre[0] = i
  setPere(arbre, i, i)

def setEtiquette(arbre, i, eti) :
  if i is None or i < 0 or i >= len(noeuds(arbre)) or noeuds(arbre)[i] is None : return 
  noeuds(arbre)[i][0] = eti

def setFilsGauche(arbre, i, j) :
  if i is None or i < 0 or i >= len(noeuds(arbre)) or noeuds(arbre)[i] is None : return 
  noeuds(arbre)[i][1] = j

def setFilsDroit(arbre, i, j) :
  if i is None or i < 0 or i >= len(noeuds(arbre)) or noeuds(arbre)[i] is None : return 
  noeuds(arbre)[i][2] = j

def setPere(arbre, i, j) :
  if i is None or i < 0 or i >= len(noeuds(arbre)) or noeuds(arbre)[i] is None : return
  noeuds(arbre)[i][3] = j


# *******************************
# ** Autres utilitaires
# *******************************

# retourne une copie de l'arbre
def copier(arbre):
  copie = (arbre[0], [])
  for noeud in arbre[1] : (copie[1]).append(noeud[:])
  return copie

#echange les noeuds stockés en i et j en maintenant tous les liens
def swap(arbre, i, j):
  if i==j : return
  aChanger = [i,j]
  for x in i,j :
    for y in 1,2,3 :
      if arbre[1][x][y] != None and arbre[1][x][y] not in aChanger :
        aChanger.append(arbre[1][x][y])
        
  arbre[1][i],arbre[1][j] = arbre[1][j],arbre[1][i] #echange tout, étiquettes, etc
  for x in aChanger :
    for y in 1,2,3 :
      if arbre[1][x][y] == i :
        arbre[1][x][y] = j
      elif arbre[1][x][y] == j :
        arbre[1][x][y] = i

def nettoieTab(arbre, ids):
  ''' supprime du tableau chacun des noeuds de ids (sans perturber les
  liens des autres noeuds) '''
  toDel = len(ids)
  toMove = []
  lasts = [False]*toDel
  n = len(arbre[1])
  for i in ids :
    if i >= n - toDel :
      lasts[n - 1 - i] = True
    else :
      toMove.append(i)

  for k in range(toDel) :
    if not lasts[k] :
      swap(arbre,toMove.pop(),n-1-k)
      
  for i in range(toDel) :
    arbre[1].pop()

##########################################################################

##########################################################################
def arbreBinaireDeFichier(fichier) :
  ''' lit un fichier contenant la description d'un arbre avec une ligne
  par noeud, au format : num,etiquette,fg,fd
  et construit un tableau contenant en case d'indice num la liste
  [etiquette, fg, fd, pere] ''' 
  try:
    res = []
    with open(fichier) as f:
      for ligne in f :
        noeud = [None, None, None, None]
        num, etiquette, fg, fd = ligne.strip().split(',')
        if etiquette != '' : noeud[0] = int(etiquette)
        if fg : noeud[1] = int(fg)
        if fd : noeud[2] = int(fd)
        res.append(noeud)
  except IOError:
    print("Erreur d'ouverture du fichier <%s>" % fichier)
    return
  # ajout des peres  
  for i, noeud in enumerate(res) :
    etiquette, fg, fd, pere = noeud
    for fils in (fg, fd) : 
      if fils != None : res[fils][-1] = i
  # calcul de(s) élément(s) sans pere (normalement un seul : la racine)
  racines = []
  for i, noeud in enumerate(res) :
    if (noeud[-1] == None):
      racines.append(i)
  # erreur si trop ou pas assez de racines
  if len(racines) != 1:
    print('Mauvais nombre de racines (%d) : ' % len(racines) + str(racines))
    return None
  r = racines[0]
  res[r][-1] = r # la racine est son propre père
  return (r, res)
##########################################################################
  
##########################################################################
def arbreBinaireVersFichier(arbre, fichier) :
  ''' réciproque de la précédente '''
  try :
    with open(fichier, 'w') as f:
      for i, noeud in enumerate(arbre[1]) :
        etiquette, fg, fd, pere = noeud
        f.write(str(i) + ',')
        if etiquette != None : f.write(str(etiquette))
        f.write(',')
        if fg != None : f.write(str(fg))
        f.write(',')
        if fd != None : f.write(str(fd))
        f.write('\n')
  except IOError:
    print("Erreur d'ouverture du fichier <%s>" % fichier)
##########################################################################
  

##########################################################################
def etiquetteStr2Int(arbre) :
  ''' transforme les étiquettes de type str (représentant des entiers) en
  étiquettes de type int '''
  for i, noeud in enumerate(arbre[1]) :
    if noeud != None and noeud[0] != None and noeud[0]!='':
      arbre[1][i][0] = int(noeud[0])	# pas de vérification d'erreur... tant pis 
  return arbre
##########################################################################
  

##########################################################################
def completeArbreBinaire(arbre) :
  ''' ajoute des feuilles vides tout autour de l'arbre binaire ''' 
  rac, tab = arbre
  if len(tab) == 0 :
    tab.append([None, None, None, 0])
    return 0, tab
  for i in range(len(tab)) : 
    for j in (1, 2) :
      if tab[i][j] == None : 
        tab[i][j] = len(tab)
        tab.append([None, None, None, i])
  return (rac, tab)
##########################################################################


##########################################################################
def arbreBinaireEntierCompletDeFichier(fichier) :
  return completeArbreBinaire(etiquetteStr2Int(arbreBinaireDeFichier(fichier)))
##########################################################################

##########################################################################
def dessineArbreBinaire(arbre,fname = 'arbre') :
  ''' crée un fichier fname.dot et un fichier fname.pdf
  représentant l'arbre ''' 
  racine, tab = arbre
  if racine == None :
    if tab != [] :
      print("Erreur, il manque une racine")
    return

  # creation du fichier .dot
  etiquette, fg, fd, pere = tab[racine]
  if etiquette != None :
    fic = open(fname+".dot", 'w')
    fic.write("graph arbre {\n")
    fic.write("\t" + str(racine) + "[label=" + str(etiquette) + "];\n")
    todo = [fg, fd]
  else : 
    return
  while todo != [] :
    i = todo.pop(0)
    # cas non complete
    if i == None : continue
    # cas general
    etiquette, fg, fd, pere = tab[i]
    if etiquette != None :
      todo += [fg, fd]
      fic.write("\t" + str(i) +"[label=" + str(etiquette) + "];\n")
    else :
      fic.write("\t" + str(i) + '[shape="plaintext", label=""];\n')
    fic.write("\t" + str(pere) + " -- " + str(i) + ";\n")
  fic.write("}\n")
  fic.close()

  # transformation en .pdf
  system("dot -Tpdf -o " + fname + ".pdf " + fname + ".dot")
##########################################################################
  
##########################################################################
arbreVide = completeArbreBinaire((None,[]))

arbre3ABR1 = completeArbreBinaire((0,[[2, 1, 2, 0], [1, None, None, 0], [3, None, None, 0]]))
arbre3ABR2 = completeArbreBinaire((0,[[1, None, 1, 0], [2, None, 2, 0], [3, None, None, 1]]))
arbre3ABR3 = completeArbreBinaire((0,[[3, 1, None, 0], [2, 2, None , 0], [1, None, None, 1]]))

arbre10ABR1 = arbreBinaireEntierCompletDeFichier("abr10_1.txt")
arbre10ABR2 = arbreBinaireEntierCompletDeFichier("abr10_2.txt")

arbre100ABR1 = etiquetteStr2Int(arbreBinaireDeFichier("abr100_1.txt"))
arbre100ABR2 = etiquetteStr2Int(arbreBinaireDeFichier("abr100_2.txt"))

arbre100notABR = etiquetteStr2Int(arbreBinaireDeFichier("notAbr100.txt"))
##########################################################################
  
##########################################################################
if __name__ == '__main__':
  pass
