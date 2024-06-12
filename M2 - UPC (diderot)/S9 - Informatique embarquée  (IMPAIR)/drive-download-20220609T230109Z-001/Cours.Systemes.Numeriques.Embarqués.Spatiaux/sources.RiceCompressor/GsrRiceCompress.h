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

/**
 * \brief fonction effectuant le pre-processing des données
 * \param data Tableau de données à transformer. Doit être de type UI16
 * \param nbInput Taille du tableau
 * \param preprocessed Tableau contenant les données preprocessées (doit être alloué)
 * \param mode Mode de transformation
 * \return Nombre de données utiles dans le tableau transformé
 */
int preprocess(unsigned short* data,int nbInput,unsigned int* preprocessed,PreprocessMode mode);
/**
 * \brief Fonction effectuant la compression d'un bloc de données
 * \param data Tableau de données à compresser
 * \param nbInput Taille du tableau
 * \param compressed Tableau de retour pour les données compressées
 * \param compressedLen Taille du tableau de retour
 * \return la taille en octet du tableau compressé ou -1 en cas d'erreur
 */
int compress(unsigned int* data,int nbInput,unsigned char* compressed,int compressedLen);
/**@}*/ // End of group Exposed
