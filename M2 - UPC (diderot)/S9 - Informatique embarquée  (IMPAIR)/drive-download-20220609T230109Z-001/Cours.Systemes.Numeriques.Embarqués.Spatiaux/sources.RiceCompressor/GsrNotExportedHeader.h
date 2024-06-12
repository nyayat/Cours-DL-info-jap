#pragma once
/**********************************************
 **************** Compression ***************** 
 **********************************************/
#pragma region Compressor functions
/**
* \ingroup NotExposedCompressor  Fonctions et variables liées à la compression
* @{
*/
//Compressor variables
static int zero_blocks; /**< Nombre de zero_block consécutifs*/
static int newbits;
static unsigned long packed_bits; 
static unsigned long global_packed_bits;
static unsigned long packed_value;
static unsigned long global_packed_value;
static unsigned char *global_bptr;
static unsigned char *bptr;
static int nbBlock;

void initCompressor();
/**
 * \brief encodage de un ou plusieurs blocs "zero_bloc"
 * \param maxOrEnd La valeur 'true' indique que l'encodage est déclenché en raison d'un nombre important de blocs (#MAX_ZERO_BLOCKS) ou de la fin de l'encoding
 */
void encodeZeroBlock(bool maxOrEnd);
/**
 * \brief encodage du MSB selon le principe des séquences fondamentales
 * \param data Borne inférieur du bloc à encoder
 * \param end Borne supérieure (exclue) du bloc à encoder
 * \param lsbSize 
 */
void encodeMSB(unsigned int* data,unsigned int* end,int lsbSize);
/**
 * \brief encodage du LSB, cas Ksplit inclus dans [1,5]
 * \param data Borne inférieur du bloc à encoder
 * \param end Borne supérieure (exclue) du bloc à encoder
 * \param lsbSize 
 */
void encodeLSB(unsigned int* data,unsigned int* end,int lsbSize);
/**
 * \brief encodage du LSB, cas Ksplit inclus dans [6,8]
 * \param data Borne inférieur du bloc à encoder
 * \param end Borne supérieure (exclue) du bloc à encoder
 * \param lsbSize 
 */
void encodeLSBHO(unsigned int* data,unsigned int* end,int lsbSize);
/**
 * \brief Encodage du bloc de référence. Appelée une seule fois pour le premier bloc
 * \param data Borne inférieur du bloc à encoder
 * \param end Borne supérieure (exclue) du bloc à encoder
 */
void encodeReferenceBlock(unsigned int* data,unsigned int* end);
/**
 * \brief Encodage d'un bloc quelconque.
 * \param data Borne inférieur du bloc à encoder
 * \param end Borne supérieure (exclue) du bloc à encoder
 * \param blockNro Indice du bloc (à partir de 1)
 */
void encodeNormalBlock(unsigned int* data, unsigned int* end,int blockNro);
/**
 * /brief vidage du tampon à la fin de la compression
 */
static void flush();  
/**
 * \brief recherche l'encodeur le plus performant pour un bloc de données
 * \param sigma Borne inférieur du bloc à analyser 
 * \param end Borne supérieure (exclue) du bloc à analyser
 * \return référence de l'encodeur choisi (ID_ZERO, ID_LOW, ID_FS, ID_Kn)
 */
static int find_winner16(unsigned int *sigma, unsigned int *end);
/**
 * \brief recherche l'encodeur le plus performant pour le bloc de données de référence
 * \param sigma Borne inférieur du bloc à analyser 
 * \param end Borne supérieure (exclue) du bloc à analyser
 * \return référence de l'encodeur choisi (ID_ZERO, ID_LOW, ID_FS, ID_Kn)
 */
static int find_ref_winner16(unsigned int *sigma, unsigned int *end);
/**
 * \brief Calcul le nombre de bits nécessaires pour un encodage étendu (ID_LOW)
 * \param sigma Borne inférieur du bloc à analyser 
 * \param end Borne supérieure (exclue) du bloc à analyser
 * \return 
 */
static unsigned int c_ext2(unsigned int* sigma, unsigned int* end);
/**@}*/ // End of group Compressor
#pragma endregion

/**********************************************
 **************** Decompression ****************
 **********************************************/
#pragma region Uncompressor functions
/**
* \ingroup NotExposedUncompressor 
* @{
*/
//Uncompressor variables
static int data_bits;
static unsigned int data_word;
static unsigned char *input_ptr;
#ifdef  GAUSS
static unsigned char *input_ptr_end;
#endif
static int leading_zeros[256];
void initUncompressor();
/**
* \brief fonction comptant le nombre de 0 consécutifs
* Cette fonction est utilisée pour décoder les encodages FS
* \return le nombre de 0 trouvés	
*/
/**
 * \brief Decodage d'une séquence fondamentale
 * \return Valeur décodée
 */
int decodeFS();
/**
 * \brief décodage de un ou plusieurs bloc de type 'Zero_Bloc'
 * \return Nombre de bloc de ce type décodés
 */
int decodeZeroBlock();
/**
 * \brief décodage du bloc de référence (la fonction n'est appelée qu'un fois en début de décompression)
 * \param data Borne inférieur du bloc destination à décoder
 * \param end Borne supérieure (exclue) du bloc destination à décoder
 * \return Nombre de bloc décodés (1 dans le cas général, plus dans le cas où le bloc de référence est de type 'Zero_Bloc')
 */
int decodeReferenceBlock(unsigned int* data,unsigned int* end);
/**
 * \brief décodage d'un bloc normal
 * \param data Borne inférieur du bloc destination à décoder
 * \param end Borne supérieure (exclue) du bloc destination à décoder
 * \param alreadyDecodedBlock Nombre de bloc déjà décodés
 * \return Nombre de nouveaux bloc s décodés
 */
int decodeNormalBlock(unsigned int* data,unsigned int* end, int alreadyDecodedBlock);
/**
 * /brief Rempli le buffer de travail avec la suite des données à décoder
 */
static void fillDataBuffer();
/**@}*/ // End of group Uncompressor
#pragma endregion




