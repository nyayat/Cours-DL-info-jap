#!/bin/env python3

import sys
bigrams = {}
wprec = '.'
for l in sys.stdin:
	l = l.strip()
	lparts = l.split("\t")
	if len(lparts) == 3:
		w = lparts[0]
		if not wprec in bigrams:
			bigrams[wprec] = {}
		if w in bigrams[wprec]:
			bigrams[wprec][w] += 1
		else:
			bigrams[wprec][w] = 1
		wprec = w

for w in bigrams:
	for wsuiv in bigrams[w]:
		print(w+'\t'+wsuiv+'\t'+str(int(bigrams[w][wsuiv])))
