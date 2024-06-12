# coding: utf-8
import os
import io

# Récupérez un texte sur Internet (par ex. article Wikipedia)
s = ''
with open('reference.txt', mode="r", encoding="utf-8") as file:
    text = file.read()
    # print(text)
    # Faire un programme qui enlève les voyelles de ces textes
    s = ''
    for i in text:
        if i not in 'aeiouàéêèê':
            s = s + i
    print(s)

with open('source.txt', mode="w", encoding="utf-8") as file:
        file.write(s)

# Faire un programme pour rétablir les voyelles correctes
# À l’aide d’un dictionnaire (arbitraire / heuristique)
# Par méthode probabiliste (mots les plus fréquents)
# Par méthode contextuelle (bigrames)
# Par méthode générative (HMM)
