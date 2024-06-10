#3.1
"""
x = list(range(10))
x = list(range(2,11))
x = list(range(2,11,2))
x = list(range(10,1, -2))
print(x)
"""
#3.2
"""
x = range(10)
x = range(2,11)
x = range(2,11,2)
x = range(10,1, -2)



for n in x:
    print(n)
"""
#3.3
L1 = [i for i in range(0,13, 2)]
L2 = [i for i in 'abcdef']
#print(L1)
#print(L2)
L1.reverse()
#print(L1)
L3 = list(zip(L1,L2))
print(L3)
#print(L3[2:5])
#print(L3[1:len(L3):2])
L4 = L3
print(L4)