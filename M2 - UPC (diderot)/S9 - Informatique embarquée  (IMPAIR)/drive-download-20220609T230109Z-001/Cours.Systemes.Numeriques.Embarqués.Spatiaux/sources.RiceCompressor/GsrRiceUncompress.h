#pragma once
/**********************************************************
 *					Définition de la page de garde DOxygen
 **********************************************************/
/**
 * \mainpage Implementation de l'algorithme de compression de RICE du projet DESIR
 * \image html logo.jpg
 * \section Info Informations
 * \author Loïc Gueguen
 * \version 1.0
 * \date
 * \section Description Description de la bibliothèque
 * Bibliothèque de compression et de décompression basée sur l'agorithme de RICE du CCSDS (SZIP)
 * L'algorithme est adapté aux sépcéficités du projet DESIR, à savoir:
 *	- Les pixels sont codés sur 16 bits
 *	- Le nombre de pixels est un multiple de 16
 */

/**********************************************************
 *					Définition des sections DOxygen
 **********************************************************/
/**
 * \defgroup Exposed  Fonctions et types exposés dans la DLL
 * \defgroup NotExposed  Fonctions et types non exposés dans la DLL
 * \defgroup NotExposedCompressor  Fonctions et variables liées à la compression
			* \ingroup NotExposed
 * \defgroup NotExposedUncompressor Fonctions et variables liées à la decompression
			* \ingroup NotExposed
 * \defgroup Constantes  Constantes et macros prédéfinies
 */


/**
 * \ingroup Exposed
* @{
*/
#include "GsrPreprocessMode.h"

int postprocessor(unsigned int* data,int nbInput,unsigned short* postProcessed,PreprocessMode mode);
/**
 * \brief Fonction effectuant la decompression d'un bloc de données
 * \param compressed Tableau en entrée contenant les données compressées
 * \param compressedLen Taille du tableau en entrée
 * \param data Tableau de retour pour les données décompressées.
 * \param nbInput Taille du tableau
 * \return nombre d'octets décompressés ou -1 en cas d'erreur
 */
int uncompress(unsigned char* compressed,int compressedLen,unsigned int* data,int nbInput);
/**@}*/ // End of group Exposed
