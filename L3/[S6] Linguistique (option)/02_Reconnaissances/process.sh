#!/bin/bash

echo "Génération du texte sans voyelles"
cat reference.txt | python3 removevoys.py > source.txt

# À activer si besoin de reconstruire les bigrames (ici générés avec 10M mots, 350M disponibles)
# ATTENTION : c'est LONG
# echo "Construction des ressources bigrames"
# tar -xvjf wikipedia.tag.txt.tar.bz2
# cat wikipedia.tag.txt | head -n 10000000 > wikipedia.tag.head.txt
# cat wikipedia.tag.head.txt | python3 getbigrams.py > bigrams.txt

echo "Reconstruction du texte avec voyelles"
cat source.txt | python3 addvoys.py > hypothese.txt

echo "Évaluation"
python3 evaluate.py reference.txt hypothese.txt
