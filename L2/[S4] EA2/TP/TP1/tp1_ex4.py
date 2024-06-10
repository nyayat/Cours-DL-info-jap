#!/usr/bin/env python3


#
# La valeur du tableau de chiffres 
#
def valeur(tab):
    nb = tab[:]
    nb.reverse()
    return int('0'+''.join([str(c) for c in nb]))


#
# Le tableau des chiffres (poids faible en premier)
#
def tableau(entier) : 
    return [int(c) for c in str(entier)]


#
# A REMPLIR
#
# L'addition de deux tableaux de chiffres decimaux de taille egale
#
def addition(nb1, nb2) :
  "addition de deux entiers representes pas des tableaux de chiffres"
  res = []
  retenue = 0
  op = 0
  for (chiffre1, chiffre2) in zip(nb1, nb2) :
    tmp = chiffre1 + chiffre2 + retenue
    op += 3 # A REMPLIR
    retenue = tmp//10 
    res.append(tmp%10)
    op += 2 # A REMPLIR
  return res + [retenue] if retenue > 0 else res, op

#
# A REMPLIR
#
# L'addition de deux tableaux de chiffres decimaux de taille differente
#
def additionV(nb1, nb2) :
  # A REMPLIR
  
  nb1, nb2 = nb1+[0]*(len(nb2)-len(nb1)), nb2+[0]*(len(nb1)-len(nb2))

  return addition(nb1, nb2)
  
#
# A COMPLETER
#
# La multiplication de deux tableaux de chiffres decimaux: methode 1
#
def multiplication1(nb1, nb2) :
  "multiplication de nb1, nb2 tableaux de chiffres par additions"
  res = nb1[:]  # copie du premier nombre
  vnb2 = valeur(nb2)
  op = 0
  for i in range(1, vnb2) :
    res, tmp = additionV(res, nb1)
    op += tmp # A REMPLIR
  return res, op

#
# A COMPLETER
#
# La multiplication de deux tableaux de chiffres decimaux: methode 2
#
def multiplication_par_un_chiffre(nb1, chiffre2) :
  "multiplication de nb1 par chiffre2"
  res = []
  retenue = 0
  op = 0
  for chiffre1 in nb1 :
    tmp = chiffre1 * chiffre2 + retenue
    op += max(chiffre1, chiffre2)+1 # A REMPLIR
    retenue = tmp//10
    res.append(tmp%10)
    op += 2 # A REMPLIR
  return res + [retenue], op

def multiplication2(nb1, nb2) :
  "multiplication de nb1, nb2 tableaux de chiffres par algo pose"
  res = []
  op = 0
  for (i, chiffre2) in enumerate(nb2) :
    tmp, opm = multiplication_par_un_chiffre(nb1, chiffre2)
    res, opa = additionV(res, [0]*i + tmp)
    # A COMPLETER
    op += opm+opa # A REMPLIR
  return res, op

#
# AJOUTER D'AUTRES TESTS
#  [parametres, [resultat_attendu, nb ops]]

def testDataAddEq() :
  return [ [ [[0, 1], [1, 1]], [[1, 2], 8] ],
	   [ [[9, 9], [1, 1]], [[0, 1, 1], 8] ],
	   [ [[8, 7, 2, 6], [2, 2, 7, 3]], [[0, 0, 0, 0, 1], 16] ]
	 ]

def testDataMul1() :
  return [ [ [[0, 1], [0, 1]], [[0, 0, 1], 72]],
	   [ [[9, 9], [1, 1]], [[9, 8, 0, 1], 116]],
	   [ [[8, 7, 2, 6], [2, 2, 7, 3]], [[6, 1, 7, 6, 6, 3, 3, 2], 112004] ]
	 ]

def testDataMul2() :
  return [ [ [[0, 1], [0, 1]], [[0, 0, 1, 0], 44]],
	   [ [[9, 9], [1, 1]], [[9, 8, 0, 1], 44]],
	   [ [[8, 7, 2, 6], [2, 2, 7, 3]], [[6, 1, 7, 6, 6, 3, 3, 2], 168] ]
	 ]


#
# NE PAS MODIFIER
#
def testOp(op, data) :
  print('\n\n* Test function %s:' % op.__name__)
  score = 0
  ldata = len(data)
  for i, dt in enumerate(data) :
    print('** test %d/%d : ' % (i + 1, ldata))
    x = dt[0][0]
    y = dt[0][1]
    refr = 0
    refc = 0
    if (len(dt[1]) > 1) :
      refr = dt[1][0]
      refc = dt[1][1]
    else :
      refr = dt[1]
    res = op(x, y)
    if (len(res) > 1) :
      r = res[0]
      c = res[1]
    else :
      r = res
      c = 0
    if (r == refr or valeur(r) == valeur(refr)) and c >= refc :
      score+=1
      print('ok (%s, %s ops)' % (r, c))
    else :
      print('ECHEC')
      print('    entrees : %s, %s' % (x, y))
      print('    calcule : %s' % r)
      print('    attendu : %s' % refr)
      if refc > 0 :
        print(' en minimum %d operations' % refc)
      else :
        print('')

  print('** Score %d/%d' % (score, ldata))


if __name__ == '__main__':
  testOp(addition, testDataAddEq())
  testOp(multiplication1, testDataMul1())
  testOp(multiplication2, testDataMul2())


