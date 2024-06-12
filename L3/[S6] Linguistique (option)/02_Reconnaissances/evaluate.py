#!/bin/env python3

import sys

ref = ''
for s in open(sys.argv[1]):
	ref += s
hyp = ''
for s in open(sys.argv[2]):
	hyp += s
# Installer: pip3 install editdistance
import editdistance
print('Score (min is better):'+str(editdistance.eval(ref, hyp)))
