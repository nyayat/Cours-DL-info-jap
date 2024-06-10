import math
#1.6
# print(math.sqrt(3)+56/9.0*math.fabs(-1/4)+63**2)

#2
# Z=None #variable à manipuler dans l'exercice 2

#2.1
## Une variable avec la valeur None est définie

#2.2
# print(True if Z==None else False)
## erreur si Z not defined donc réponse que dans le cas contraire
   
#2.3
# print("sans valeur" if Z==None else "chaine vide" if Z=="" else "autre")

#2.4
# print(True if x>0 else False)
## erreur avec x=None ou x not defined

# print(None if x==None else True if x>0 else False)
## erreur avec x not defined

#3.1
# l1=list(range(0, 10))
# l2=list(range(2, 11))
# l3=[i for i in range (2, 11) if i%2==0]
# l4=[i for i in range (10, 1, -1) if i%2==0]

#3.2
# for i in range (0, 10):
#     print(i)
    
#3.3.a
l1=[i for i in range (0, 13) if i%2==0]
l2=[i for i in "abcdef"]

#3.3.b
l1.reverse()

#3.3.c
l3=[(l,c) for (c,l) in zip (l1, l2)]

#3.4.a
sl1=l3[2:5:2]

#3.4.b
sl2=l3[1:len(l3)+1:2]

#3.4.c
l4=l3[0:len(l3)+1:1]