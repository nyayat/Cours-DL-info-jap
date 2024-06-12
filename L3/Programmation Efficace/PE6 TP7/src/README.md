##### Explication de l'algorithme que j'ai implémenté

On ajoute des notes tant que le nombre de notes dans la séquence n'est pas supérieur ou égal à la somme des a<sub>i</sub>.
En effet, si c'est le cas, les a<sub>i</sub> dernières notes peuvent être répétées une infinité de fois et la solution est alors ```infini```. 

On choisit les notes de la façon suivante : parmi les notes qui peuvent être jouées (c'est-à-dire qui une fois jouée vérifient toujours les conditions), on choisit celle qui doit être jouée en priorité. Cette priorité est déterminée par la fonction `getLimit` qui calcule jusqu'à quelle position dans la séquence une note peut ne pas être jouée. Donc, une fois cette limite atteinte, la note doit être impérativement jouée. Puis, on vérifie que toutes les notes vérifient toujours les conditions.