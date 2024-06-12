
#include "tp2-algo.h"
#include <stdint.h>

Point barycentre(float window[], float mask[]) {
	Point res;
	double total = 0;
	double tx = 0;
	double ty = 0;
	uint8_t xi = 0;
	uint8_t yi = 0;
	// --- parcoure window
	for (xi = 0; xi < 6; xi++) {
		for (yi = 0; yi < 6; yi++) {
			// -- si mask != 0
			if (mask[xi * 6 + yi] > 0) {
				// ajoute le pixel à la somme
				total += window[xi * 6 + yi];
				// somme les pixels, pondérés de leur coordonnée, dans chacun des dimensions.
				tx += window[xi * 6 + yi] * (xi + 1);
				ty += window[xi * 6 + yi] * (yi + 1);
			}
		}
	}
	// calcul le barrycentre dans chacun des dimensions
	res.x = (1.0 * tx / total) - 1;
	res.y = (1.0 * ty / total) - 1;

	return res;
}

double calculFluxBinaire(float window[], float mask[]) {
	double res = 0;
	uint8_t xi = 0;
	uint8_t yi = 0;
	// --- parcoure window
	for (xi = 0; xi < 6; xi++) {
		for (yi = 0; yi < 6; yi++) {
			// -- si mask != 0
			if (mask[xi * 6 + yi] > 0) {
				// somme les pixels
				res += window[xi * 6 + yi];
			}
		}
	}
	return res;
}

double calculFluxPondere(float window[], float mask[]) {
	double res = 0;
	uint8_t xi = 0;
	uint8_t yi = 0;
	// --- parcoure window
	for (xi = 0; xi < 6; xi++) {
		for (yi = 0; yi < 6; yi++) {
			// somme les pixels, pondérés du facteur de mask
			res += window[xi * 6 + yi] * mask[xi * 6 + yi];
		}
	}
	return res;
}
