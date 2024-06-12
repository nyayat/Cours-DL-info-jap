/*
 * buffer_circulaire.c
 *
 *  Created on: 20 nov. 2018
 *      Author: SGSE
 */
#include "buffer_circulaire.h"

void init_buffer_circulaire(buffer_circulaire* b, uint8_t* buffer,
		uint16_t taille_donnee, uint32_t nombre_donnees) {
	b->buffer = buffer;
	b->taille_donnee = taille_donnee;
	b->nombre_donnees = nombre_donnees;
	b->i_ancienne_donee = nombre_donnees;
	b->i_donnee_recente = nombre_donnees - 1;
	b->occupation = 0;
	b->occupation_max = 0;
}

int push(buffer_circulaire* fifo, uint8_t* source, uint16_t taille) {
	int res = -3;
	if (fifo != 0 && fifo->buffer != 0) {
		if (taille > fifo->taille_donnee) {
			res = -1;
		} else {
			// calcul le prochain emplacement a utiliser
			uint32_t i_prochaine_donnee = (fifo->i_donnee_recente + 1)
					% fifo->nombre_donnees;
			if (i_prochaine_donnee == fifo->i_ancienne_donee) {
				// plus de place
				res = -2;
			} else {
				uint8_t* dest = &(fifo->buffer[i_prochaine_donnee
						* fifo->taille_donnee]);
				uint16_t i;
				// copie de la donnée
				for (i = 0; i < taille; i++) {
					dest[i] = source[i];
				}
				// mise a jour des compteurs
				fifo->occupation++;
				if (fifo->occupation > fifo->occupation_max) {
					fifo->occupation_max = fifo->occupation;
				}
				// mise à jour des pointeurs
				fifo->i_donnee_recente = i_prochaine_donnee;
				if (fifo->i_ancienne_donee == fifo->nombre_donnees) {
					fifo->i_ancienne_donee = fifo->i_donnee_recente;
				}
				res = 0;
			}
		}
	}
	return res;
}

int pop(buffer_circulaire* fifo, uint8_t* destination, uint16_t taille_max) {
	int res = -3;
	if (fifo != 0 && fifo->buffer != 0) {
		if (taille_max < fifo->taille_donnee) {
			//
			res = -1;
		} else {
			if (fifo->i_ancienne_donee == fifo->nombre_donnees) {
				// fifo empty
				res = -2;
			} else {
				uint8_t* source = &(fifo->buffer[fifo->i_ancienne_donee
						* fifo->taille_donnee]);
				// copie de la donnee
				uint16_t i;
				for (i = 0; i < fifo->taille_donnee; i++) {
					destination[i] = source[i];
				}
				//mise a jour du compteurs
				fifo->occupation--;
				// mise a jour des pointeurs
				if (fifo->i_ancienne_donee == fifo->i_donnee_recente) {
					fifo->i_ancienne_donee = fifo->nombre_donnees;
				} else {
					fifo->i_ancienne_donee = (fifo->i_ancienne_donee + 1)
							% fifo->nombre_donnees;
				}
				res = fifo->taille_donnee;
			}
		}
	}
	return res;
}

unsigned int test_buffer_circulaire() {
	unsigned int res = 0;
	int tmp;
	unsigned int i = 0;
	static const char TAILLE = 2;
	static const char NB_DONNEES = 3;
	uint8_t buffer_fifo[NB_DONNEES][TAILLE];
	uint8_t buffer[TAILLE * 2];

	buffer_circulaire bc;
	init_buffer_circulaire(&bc, buffer_fifo, TAILLE, NB_DONNEES);

	for (i = 0; i < TAILLE; i++) {
		for (tmp = 0; tmp < NB_DONNEES; tmp++) {
			buffer_fifo[i][tmp] = 0;
		}
	}
	for (i = 0; i < TAILLE * 2; i++) {
		buffer[i] = 0;
	}

	i = 0;

// pop sur fifo vide
	if (res == 0) {
		i++;
		if (-2 != pop(&bc, buffer, TAILLE) || bc.occupation != 0
				|| bc.occupation_max != 0) {
			res = i;
		}
	}

// donnee trop grande
	if (res == 0) {
		i++;
		buffer[0] = 0x11;
		buffer[1] = 0x22;
		buffer[2] = 0x33;
		tmp = push(&bc, buffer, TAILLE + 1);
		if (-1 != tmp || buffer_fifo[0][0] != 0 || bc.occupation != 0
				|| bc.occupation_max != 0) {
			res = i;
		}
	}

// push OK
	if (res == 0) {
		i++;
		buffer[0] = 0x11;
		buffer[1] = 0x22;
		tmp = push(&bc, buffer, TAILLE);
		if (0 != tmp || buffer_fifo[0][0] != 0x11 || buffer_fifo[0][1] != 0x22
				|| bc.occupation != 1 || bc.occupation_max != 1) {
			res = i;
		}
	}

// pop avec buffer trop petit
	if (res == 0) {
		i++;
		buffer[0] = 0;
		buffer[1] = 0;
		buffer[2] = 0;
		if (-1 != pop(&bc, buffer, TAILLE - 1) || buffer[0] != 0
				|| buffer[0] != 0 || bc.occupation != 1
				|| bc.occupation_max != 1) {
			res = i;
		}
	}

// pop OK
	if (res == 0) {
		i++;
		buffer[0] = 0;
		buffer[1] = 0;
		buffer[2] = 0;
		tmp = pop(&bc, buffer, TAILLE);
		if (2 != tmp || buffer[0] != 0x11 || buffer[1] != 0x22
				|| bc.occupation != 0 || bc.occupation_max != 1) {
			res = i;
		}
	}

// pop une fifo vide
	if (res == 0) {
		i++;
		buffer[0] = 0;
		buffer[1] = 0;
		buffer[2] = 0;
		tmp = pop(&bc, buffer, TAILLE);
		if (-2 != tmp || buffer[0] != 0 || buffer[1] != 0 || bc.occupation != 0
				|| bc.occupation_max != 1) {
			res = i;
		}
	}

//push 3 fois avec succès
	if (res == 0) {
		i++;
		buffer[0] = 0x11;
		buffer[1] = 0x12;
		tmp = push(&bc, buffer, TAILLE);
		res = buffer_fifo[1][0];
		res = buffer_fifo[1][1];
		res = 0;
		if (0 != tmp || buffer_fifo[1][0] != 0x11 || buffer_fifo[1][1] != 0x12
				|| bc.occupation != 1 || bc.occupation_max != 1) {
			res = i;
		}
	}
	if (res == 0) {
		i++;
		buffer[0] = 0x13;
		buffer[1] = 0x14;
		if (0 != push(&bc, buffer, TAILLE) || buffer_fifo[2][0] != 0x13
				|| buffer_fifo[2][1] != 0x14|| bc.occupation != 2 || bc.occupation_max != 2) {
			res = i;
		}
	}
	if (res == 0) {
		i++;
		buffer[0] = 0x17;
		buffer[1] = 0x18;
		if (0 != push(&bc, buffer, TAILLE) || buffer_fifo[0][0] != 0x17
				|| buffer_fifo[0][1] != 0x18|| bc.occupation != 3 || bc.occupation_max != 3) {
			res = i;
		}
	}

// push sur une fifo pleine
	if (res == 0) {
		i++;
		buffer[0] = 0x19;
		buffer[1] = 0x1a;
		if (-2 != push(&bc, buffer, TAILLE)|| bc.occupation != 3 || bc.occupation_max != 3) {
			res = i;
		}
	}

	return res;
}

