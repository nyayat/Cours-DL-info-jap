#!/bin/env python3

import sys

"""
lstwords = {}
for line in open('freqs.txt'):
	fw = line.split()
	if len(fw) ==2:
		freq, word = line.split()
		wordnovoy = ''.join()
"""

with open('source.txt', mode="r", encoding="utf-8") as file :
  filedata = file.read()

# Replace the target string
filedata = filedata.replace('Gms', 'Gims')
filedata = filedata.replace('Mîtr', 'Maître')
filedata = filedata.replace('Knshs', 'Kinshasa')
filedata = filedata.replace('Alrs', 'Alors')
filedata = filedata.replace('frnçs', 'français')
filedata = filedata.replace('Frnc', 'France')


# Write the file out again
with open('hypothese.txt', mode="w", encoding="utf-8") as file:
  file.write(filedata)