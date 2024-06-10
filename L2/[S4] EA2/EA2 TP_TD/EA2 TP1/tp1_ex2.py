#!/usr/bin/env python3

#
# A REMPLIR
#
# Expression conditionnelle
# retourne True si x > 0, False si x <= 0 ou si x vaut None
def expression_5(x) :
    if(x==None or x<=0):
        return False
    else:
        return True


#
# AJOUTER D'AUTRES TESTS
#  [valeur_x, resultat_attendu]
def testData():
	return [[5, True], [None, False], [-2, False], [-3.4, False]]


#
# NE PAS MODIFIER
#
def testExpr(data) :
	score = 0
	ldata = len(data)
	for i, dt in enumerate(data) :
		print('  test %d/%d : ' % (i + 1, ldata), end='')
		x = dt[0]
		refr = dt[1]
		r = expression_5(x)
		if r == refr :
			score+=1
			print('ok')
		else :
			print('ECHEC')
			print('    entree  : %s' % x)
			print('    calcule : %s' % r)
			print('    attendu : %s' % refr)

	print('Score %d/%d' % (score, ldata))


if __name__ == '__main__':
	testExpr(testData())
