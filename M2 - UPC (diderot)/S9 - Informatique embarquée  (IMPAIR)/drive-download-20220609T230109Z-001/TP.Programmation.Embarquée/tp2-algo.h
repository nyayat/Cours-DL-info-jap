#ifndef ALGO_H_
#define ALGO_H_

typedef struct {
	float x;
	float y;
} Point;

/**
 * Calcule les coordonnées du barycentre du tableau 6x6 \a window, pondéré du tableau de même tailles \a mask.
 * @param window tableau de 6x6 où l'on cherche le barrycentre
 * @param mask tableau où les 0 indiquent un pixel à exclure et une autre valeur un pixel à inclure.
 * @return coordonnées du barycentre.
 */
Point barycentre(float window[], float mask[]);

/**
 * Calcul le flux binaire dans la fenetre \a window en entrée. \a Mask indique si le pixel doit être pris en compte ou non.
 * @param window tableau de 6x6 pixel où l'on calcul le flux d'une étoile
 * @param mask tableau où les 0 indiquent un pixel à exclure et une autre valeur un pixel à inclure.
 * @return somme des pixel inclus
 */
double calculFluxBinaire(float window[], float mask[]);

/**
 * Calcul le flux pondéré dans la fenetre \a window en entrée. \a Mask indique si le pixel doit être pris en compte ou non.
 * @param window tableau de 6x6 pixel où l'on calcul le flux d'une étoile
 * @param mask tableau où pour chaque pixel de \a window on trouve le facteur de pondération à appliquer
 * @return somme des pixels pondérés du facteur de \a mask
 */
double calculFluxPondere(float window[], float mask[]);

#endif /* ALGO_H_ */
