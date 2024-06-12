#pragma once
/**
* \ingroup Constantes
* @{
*/

/**
 * \brief Nombre de pixels par bloc
 */
#define PIXEL_PER_BLOCK 16				
/**
 * \brief Nombre de pixels par demi bloc
 */
#define HALF_PIXEL_PER_BLOCK	8		
/**
 * \brief Nombre de bits par pixels
 */
#define BITS_PER_PIXELS	16				
/**
 * \brief Nombre de bits utilisés pour coder FS
 */
#define ID_BITS					4					
/**
 * \brief Nombre max de 'Zero_bloc' consécutifs, doit être une puissance de 2
 */
#define MAX_ZERO_BLOCKS	64				
/**
 * \brief Valeur max d'un pixel
 */
#define XMAX						0xFFFF		

#define ID_ZERO     -1
#define ID_LOW       0
#define ID_FS        1
#define ID_K1				 2
#define ID_K2				 3
#define ID_K3				 4
#define ID_K4				 5
#define ID_K5				 6
#define ID_K6				 7
#define ID_K7				 8
#define ID_K8				 9
#define ID_K9				10
#define ID_K10			11
#define ID_K11			12
#define ID_K12			13
#define ID_K13			14
#define ID_K14			15
#define ID_DEFAULT  15

/**
 * \brief Masque permettant de récupérer les i derniers bits
 */
static unsigned int masknot[]={	
	0x00000000,
	0x00000001,0x00000003,0x00000007,0x0000000F,
	0x0000001F,0x0000003F,0x0000007F,0x000000FF,
	0x000001FF,0x000003FF,0x000007FF,0x00000FFF,
	0x00001FFF,0x00003FFF,0x00007FFF,0x0000FFFF,
	0x0001FFFF,0x0003FFFF,0x0007FFFF,0x000FFFFF,
	0x001FFFFF,0x003FFFFF,0x007FFFFF,0x00FFFFFF,
	0x01FFFFFF,0x03FFFFFF,0x07FFFFFF,0x0FFFFFFF,
	0x1FFFFFFF,0x3FFFFFFF,0x7FFFFFFF,0xFFFFFFFF
}; 

#define MAX_EXT2     				  7 
#define MAX_EXT2_SUM 				 (MAX_EXT2*(MAX_EXT2+1)/2 + MAX_EXT2)
/**
 * \brief tableau permettant l'encodage du format étendu (#ID_LOW)\n
 * \f$ext2\_array[i][j] = (i+j)*(i+j+1)/2 + j + 1\f$
*/
static unsigned char ext2_array[MAX_EXT2+1][MAX_EXT2+1]={
	1,3,6,10,15,21,28,36,
	2,5,9,14,20,27,35,44,
	4,8,13,19,26,34,43,53,
	7,12,18,25,33,42,52,63,
	11,17,24,32,41,51,62,74,
	16,23,31,40,50,61,73,86,
	22,30,39,49,60,72,85,99,
	29,38,48,59,71,84,98,113
};

/**
 * \brief Tableau permettant le décodage du format étendu (#ID_LOW) en conjonction avec #ext2_array2
 */
static unsigned char ext2_array1[MAX_EXT2_SUM+1]; 
/**
 * \brief Tableau permettant le décodage du format étendu (#ID_LOW) en conjonction avec #ext2_array1
 */
static unsigned char ext2_array2[MAX_EXT2_SUM+1]; 


/**
 * /brief Macro permettant l'encodage d'une valeur sur n bits
*/
#define pack1(value, pbits) \
{ \
	packed_bits -= pbits; \
	packed_value |= value << packed_bits; \
	if (packed_bits <= 16) \
{ \
	unsigned long v16; \
	v16 = packed_value >> 16; \
	*bptr++ =(unsigned char)(v16 >> 8); \
	*bptr++ = (unsigned char)(v16 & 0xff); \
	packed_value <<= 16; \
	packed_bits += 16; \
} \
}
/**
 * \brief Macro permettant l'encodage d'une valeur au format 'sequence fondamentale'
 */
#define packfs(xbits) \
{ \
	long pbits; \
	\
	pbits = xbits; \
	while (pbits > 16) \
{ \
	pack1(0, 16); \
	pbits -= 16; \
} \
	pack1(1, pbits); \
}

/**@}*/ // End of group Constantes

