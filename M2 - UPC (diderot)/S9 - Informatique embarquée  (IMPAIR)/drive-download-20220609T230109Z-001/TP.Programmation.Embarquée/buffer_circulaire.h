/*
 * buffer_circulaire.h
 *
 *  Created on: 20 nov. 2018
 *      Author: SGSE
 */

#ifndef BUFFER_CIRCULAIRE_H_
#define BUFFER_CIRCULAIRE_H_

#include <stdint.h>

typedef struct s_buffer_circulaire {
	uint16_t taille_donnee;
	uint32_t nombre_donnees;
	uint32_t i_ancienne_donee;
	uint32_t i_donnee_recente;
	uint32_t occupation;
	uint32_t occupation_max;
	uint8_t* buffer;
} buffer_circulaire;

/**
 * Initialise un objet buffer_circulaire
 * @param buffer espace memoire de \a taille_donnee * \a nombre_donnees octets
 * @param taille_donnee taille maximal d'une donnee enregitree dans la FIFO
 * @param nombre de donnees que peut contenir la FIFO
 */
void init_buffer_circulaire (buffer_circulaire* buffer_circulaire, uint8_t* buffer, uint16_t taille_donnee, uint32_t nombre_donnees);


/**
 * Ajoute a la FIFO une donnee
 * @param source adresse de la donnee a ajouter a la FIFO
 * @param taille nombre d'octets a ajouter a la FIFO
 * @return 0 si tout s'est bien passe
 * @return -1 si la donnee est plus grande que ce que n'accepte la FIFO
 * @return -2 s'il n'y a plus de place pour ajouter la donnee dans la FIFO
 * @return -3 si fifo ou le pointer \a buffer de init_buffer_circulaire() est null
 */
int push(buffer_circulaire* fifo, uint8_t* source, uint16_t taille);

/**
 * Retire de la FIFO la plus ancienne donnee et la copie a l'adresse \a destination
 * @param destination adresse memoire ou doivent etre enregistre les donnees
 * @param aille_max nombre maximal d'octets qui peuvent etre enregistrees a l'adresse \a destination
 * @return nombre d'octets enregistres a l'adresse \a destination
 * @return -1 si la donnee a retirer est plus grande que \a taille_max
 * @return -2 si aucune donnee n'est disponible dans la FIFO
 * @return -3 si fifo est null ou le pointer \a buffer de init_buffer_circulaire() est null
 */
int pop(buffer_circulaire* fifo, uint8_t* destination, uint16_t taille_max);


/**
 * test unitaires des fonctions de buffer_circulaire
 * @return 0 si tout s'est bien passe
 * @return le numero du premier test en erreur
 */
unsigned int test_buffer_circulaire() ;

#endif /* BUFFER_CIRCULAIRE_H_ */
