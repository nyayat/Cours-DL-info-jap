all: serveur client1 client2

serveur:
	gcc -pthread -Wall -o serveur serveur.c

client1:
	gcc -Wall -o client1 client1.c

client2:
	gcc -Wall -o client2 client2.c

clean: 
	rm -f serveur client1 client2