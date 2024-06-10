#!/usr/bin/env python3

#    Small library for EA4
#     v1.0 2019 nolin@irif.fr
##### Configuration part #####

# You can disable colors with this
# In particular if you get nonsense
disable_colors = False

# If colors do not work put True
#   note : you need to install
#          the colorama module
# This should make colors work
#   on Windows
usecolorama = False

#### End of configuration ####

if usecolorama:
  import colorama
  colorama.init()

#ANSI escape codes (CSI)
aecbegin="\033["
aecend="m"

end    = aecbegin +    "0" + aecend

bold   = aecbegin +    "1" + aecend
underl = aecbegin +    "4" + aecend
#all colors are also bold
red    = aecbegin + "1;31" + aecend
green  = aecbegin + "1;32" + aecend
yellow = aecbegin + "1;33" + aecend
blue   = aecbegin + "1;34" + aecend
pink   = aecbegin + "1;35" + aecend
cyan   = aecbegin + "1;36" + aecend
#fondrouge = "\033[1;41m"

coldict = {
  "bold":bold,"red":red,"green":green,"yellow":yellow,"blue":blue,"pink":pink,"cyan":cyan,"underl":underl
}

# Enables coloring text with ANSI escape codes
# /!\ printcol("Test",4,"test") ne marche pas pour l'instant
def printcol(*arg,**kwargs):
  s  = arg[0]
  ss = s.split('}')
  nr = len(ss)
  s  = end.join(ss)
  ss = s.split('{')
  nl = len(ss)
  if (nl != nr): # just to check
    print("Bad parentheses in printcol"); return
  for i in range(1,nl):
    ss[i-1] += coldict[arg[i]]
  s="".join(ss)
  print(s,**kwargs)

# WORK IN PROGRESS
#   useful features would be:
#     - a function to replace all plotting functions
#         - it would take a list of functions and an iterator as parameters
#         - it would then run the functions on all values
#           received from the iterator and plot that
#         - as optional parameter, it could receive a list of colours
#     - a function coded once and for all (and not copypasted from
#       a TP to the next) to get lists of colours
#     - a generic 'test' function
#         - just like the universal plotting function, it could replace
#           all the adhoc test functions
#         - would take test_data and algorithms as parameters
#
#   Also: it would be better if the tests were not in the students'
#         skeleton files. This would make those files cleaner and
#         also we could just run their code with the original version
#         of the tests without looking at their files to see if they
#         changed the test functions and data
#
# def colors(tous=True) :
#   d = 0 if tous else 1
#   res = ['ro', 'co', 'go', 'bo']
#   return res[d:]
#
# def colors(n) :
#   res = ['ro', 'co', 'go', 'bo']
#   return res[:n]
#
# Affiche les opérations faites par les algos contenus dans le tableau
# 'algos' sur les points obtenus via l'itérateur 'it'
# def courbes_ops(algos, it, colors=None) :
#   if type(it).__name=='int':
#     it = range(it)
#   elif type(it).__name=='tuple':
#     if len(it)==3:
#       deb,fin,pas = it
#     else:
#       deb,fin,pas = 0,it[0],i[1]
#     it = range(deb,fin,pas)
#   # now iterator 'it' is correctly initialized

if disable_colors :
  coldict = {"bold":"","red":"","green":"","yellow":"","blue":"","pink":"","cyan":"","underl":""}

