#include "GsrRiceUncompress.h"
#include "GsrNotExportedHeader.h"
#include "GsrRiceMacroAndConst.h"
#include <math.h>
#include <string.h>

#ifdef  GAUSS
#include <exception>
#include <stdio.h>
#endif //  GAUSS



void initUncompressor(){
	int index;
	int i,k,j;
	for (i = 0; i <= MAX_EXT2; i++){
		for (int j = 0; j <= MAX_EXT2-i; j++)
		{
			index = (i+j)*(i+j+1)/2 + j;
			ext2_array1[index] = i;
			ext2_array2[index] = j;
		}
	}

	int *p;
	p = leading_zeros;
	*p++ = 8;
	for (i = 1, k = 7; i < 256; i += i, k--)
		for (j = 0; j < i; j++)
			*p++ = k;
}

int postprocessor(unsigned int* data,int nbInput,unsigned short* postProcessed,PreprocessMode mode){
	switch(mode){
		case ESTIMATE_1D_H	:
		{
			unsigned int sigma;
			unsigned int prediction;
			unsigned int *end= data + nbInput;

			//Récupération référence
			prediction=*data++;
			*postProcessed++=prediction;

			while (data < end)
			{
				//Récupération SIGMAi
				sigma = *data++;

				if (sigma >= (prediction << 1))
					prediction = sigma;
				else if (sigma > (XMAX - prediction) << 1)
					prediction = XMAX - sigma;
				else if (sigma & 1)
					prediction = prediction - ((sigma + 1) >> 1);
				else
					prediction = prediction + (sigma >> 1);

				*postProcessed++=prediction;
			}
			return nbInput;
		}
		break;
		default :
			return -1;
	}
}

int decodeFS(){
	int big_zero_count;
	int zero_count;

	big_zero_count = 0;
	while ((data_word & 0xff000000) == 0)
	{
		big_zero_count += 8;
		data_word <<= 8;
		data_bits -= 8;

		fillDataBuffer();
	}

	zero_count = leading_zeros[data_word >> 24];
	data_word <<= zero_count+1;
	data_bits -= zero_count+1;

	fillDataBuffer();
	return zero_count + big_zero_count;
}

int decodeZeroBlock(){
	int bits;

	bits = decodeFS() + 1;

	if (bits < 5)
		zero_blocks = bits;
	else if (bits == 5)
		zero_blocks = MAX_ZERO_BLOCKS;
	else
		zero_blocks = bits - 1;
	return zero_blocks;
}

int decodeReferenceBlock(unsigned int* data,unsigned int* end){
	unsigned int* s = data;
	unsigned int* s1;
	s++;

#ifdef GAUSS
	if (input_ptr + 4 * sizeof(unsigned char) > input_ptr_end)
		return -1;
#endif
	//Remplissage initial dataBuffer
	data_word = *input_ptr++ << 24;
	data_word |= *input_ptr++ << 16;
	data_word |= *input_ptr++ << 8;
	data_word |= *input_ptr++;
	data_bits = 32;


	//lecture k
	unsigned int id = data_word >> 28;
	data_word <<= 4;
	data_bits -= 4;


	//Cas ID_LOW, FS est codé sur 5 bits au lieu de 4
	if (id == ID_LOW)
	{
		if(!(data_word & 0x80000000))//00001-->ID_LOW ou 00000-->ID_ZERO
			id=ID_ZERO;
		//Extraction du 5eme bit
		data_word <<= 1;
		data_bits--;
	}

	fillDataBuffer();

	unsigned int k_bits = id - 1;

	//Lecture de Delta0
	*data = data_word >> 16;
	data_word <<= 16;
	data_bits -= 16;

	fillDataBuffer();

	switch(id){
		case ID_ZERO:
			{
				int zero_block=decodeZeroBlock();
				memset(s, 0, (zero_block*PIXEL_PER_BLOCK-1)*sizeof(int));
				return zero_blocks;
			}
			break;
		case ID_FS:
			{
				while (s < end)
				{
					*s++ = decodeFS();
				}
				return 1;
			}
			break;
		case ID_LOW:
			{
				int m;
				unsigned *t;
				unsigned *tend;
				unsigned temp[HALF_PIXEL_PER_BLOCK];

				t = temp;
				tend = temp + HALF_PIXEL_PER_BLOCK;
				while(t<tend){
					*t++ = decodeFS();
				}

				t = temp;
				m = *t++;

				*s++ = ext2_array2[m];
				while (s < end)
				{
					m = *t++;
					if (m >= MAX_EXT2_SUM)
						m = 0;

					*s++ = ext2_array1[m];
					*s++ = ext2_array2[m];
				}
				return 1;
			}
			break;
		default:
			{
				if (id == ID_DEFAULT)
				{
					int bits;
					int shift;

					shift = 32 - BITS_PER_PIXELS;
					while (s < end)
					{
						bits = data_word >> shift;
						data_word <<= BITS_PER_PIXELS;
						data_bits -= BITS_PER_PIXELS;
						fillDataBuffer();

						*s++ = bits;
					}

				}
				else
				{
					int bits;
					int shift;

					//Decodage MSB (encodage FS)
					s1 = s;
					while (s < end)
					{
						*s++ = decodeFS();
					}

					s = s1;
					shift = 32 - k_bits;

					//Decodage LSB
					while (s < end)
					{
						bits = data_word >> shift;
						data_word <<= k_bits;
						data_bits -= k_bits;

						fillDataBuffer();

						*s = (*s << k_bits) | bits;
						s++;
					}
				}
				return 1;
			}
	}
}

int decodeNormalBlock(unsigned int* data,unsigned int* end, int alreadyDecodedBlock){
	unsigned int* s = data;
	//lecture k
	unsigned int id = data_word >> 28;
	data_word <<= 4;
	data_bits -= 4;

	//Cas ID_LOW, FS est codé sur 5 bits au lieu de 4
	if (id == ID_LOW)
	{
		if(!(data_word & 0x80000000))//00001-->ID_LOW ou 00000-->ID_ZERO
			id=ID_ZERO;
		//Extraction du 5eme bit
		data_word <<= 1;
		data_bits--;
	}

	fillDataBuffer();

	unsigned int k_bits = id - 1;
	zero_blocks = 0;

	switch(id){
		case ID_FS:
		#pragma region ID_FS
		{
			while (s < end)
			{
				*s++ = decodeFS();
			}
			return 1;
		}
		#pragma endregion
		break;
		case ID_LOW:
		#pragma region ID_LOW
		{
			int m;
			unsigned *t;
			unsigned *tend;
			unsigned temp[PIXEL_PER_BLOCK];
			/*** Read EXT2 FS values ***/
			t = temp;
			tend = temp + HALF_PIXEL_PER_BLOCK;
			while (t < tend)
			{
				*t++ = decodeFS();
			}

			t = temp;
			while (s < end)
			{
				m = *t++;
				if (m >= MAX_EXT2_SUM)
					m = 0;

				*s++ = ext2_array1[m];
				*s++ = ext2_array2[m];
			} // while (s < end)
			return 1;
		}
		#pragma endregion
		break;
		case ID_ZERO:
		#pragma region ID_ZERO
		{
			int bits;
			bits = decodeFS() + 1;

			if (bits < 5)
				zero_blocks = bits;
			else if (bits == 5)
			{
				zero_blocks = MAX_ZERO_BLOCKS - ((alreadyDecodedBlock-1) & (MAX_ZERO_BLOCKS-1));
				if (nbBlock - (alreadyDecodedBlock-1) < zero_blocks)
					zero_blocks = nbBlock - (alreadyDecodedBlock-1);
			}
			else
				zero_blocks = bits - 1;

			end += (zero_blocks-1) * PIXEL_PER_BLOCK;
			memset(s, 0, (end-s)*sizeof(int));
			return zero_blocks;
		}
		#pragma endregion
		break;
		case ID_DEFAULT:
		#pragma region ID_DEFAULT
		{
			int bits;
			int shift;
			shift = 32 - BITS_PER_PIXELS;
			while (s < end)
			{
				bits = data_word >> shift;
				data_word <<= BITS_PER_PIXELS;
				data_bits -= BITS_PER_PIXELS;
				fillDataBuffer();

				*s++ = bits;
			} // while (s < end)
			return 1;
		}
		#pragma endregion
		break;
		default:
		#pragma region defaultCase
		{
			int shift;

			unsigned int *s1 = s;
			while (s < end)
			{
				if ((data_word >> 30) == 3)
				{
					data_word <<= 2;
					data_bits -= 2;
					fillDataBuffer();

					*s++ = 0;
					*s++ = 0;
				}
				else if ((data_word >> 29) == 3)
				{
					data_word <<= 3;
					data_bits -= 3;
					fillDataBuffer();

					*s++ = 1;
					*s++ = 0;
				}
				else if ((data_word >> 29) == 5)
				{
					data_word <<= 3;
					data_bits -= 3;
					fillDataBuffer();

					*s++ = 0;
					*s++ = 1;
				}
				else if ((data_word >> 28) == 5)
				{
					data_word <<= 4;
					data_bits -= 4;
					fillDataBuffer();

					*s++ = 1;
					*s++ = 1;
				}
				else
				{
					*s++ = decodeFS();
					*s++ = decodeFS();
				}
			}

			s = s1;
			switch (k_bits)
			{
				case 1:
					while (s < end)
					{
						*(s+0) = (*(s+0) << 1) | (data_word >> 31);
						*(s+1) = (*(s+1) << 1) | (data_word >> 30) & 1;
						*(s+2) = (*(s+2) << 1) | (data_word >> 29) & 1;
						*(s+3) = (*(s+3) << 1) | (data_word >> 28) & 1;
						*(s+4) = (*(s+4) << 1) | (data_word >> 27) & 1;
						*(s+5) = (*(s+5) << 1) | (data_word >> 26) & 1;
						*(s+6) = (*(s+6) << 1) | (data_word >> 25) & 1;
						*(s+7) = (*(s+7) << 1) | (data_word >> 24) & 1;
						s += 8;
						data_word <<= 8;
						data_bits -= 8;
						fillDataBuffer();
					}
					break;

				case 2:
					while (s < end)
					{
						*(s+0) = (*(s+0) << 2) | (data_word >> 30) & 3;
						*(s+1) = (*(s+1) << 2) | (data_word >> 28) & 3;
						*(s+2) = (*(s+2) << 2) | (data_word >> 26) & 3;
						*(s+3) = (*(s+3) << 2) | (data_word >> 24) & 3;
						*(s+4) = (*(s+4) << 2) | (data_word >> 22) & 3;
						*(s+5) = (*(s+5) << 2) | (data_word >> 20) & 3;
						*(s+6) = (*(s+6) << 2) | (data_word >> 18) & 3;
						*(s+7) = (*(s+7) << 2) | (data_word >> 16) & 3;
						s += 8;
						data_word <<= 16;
						data_bits -= 16;
						fillDataBuffer();
					}
					break;

				case 3:
					while (s < end)
					{
						*(s+0) = (*(s+0) << 3) | (data_word >> 29) & 7;
						*(s+1) = (*(s+1) << 3) | (data_word >> 26) & 7;
						*(s+2) = (*(s+2) << 3) | (data_word >> 23) & 7;
						*(s+3) = (*(s+3) << 3) | (data_word >> 20) & 7;
						*(s+4) = (*(s+4) << 3) | (data_word >> 17) & 7;
						s += 5;
						data_word <<= 15;
						data_bits -= 15;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 3) | (data_word >> 29) & 7;
						*(s+1) = (*(s+1) << 3) | (data_word >> 26) & 7;
						*(s+2) = (*(s+2) << 3) | (data_word >> 23) & 7;
						s += 3;
						data_word <<= 9;
						data_bits -= 9;
						fillDataBuffer();
					}
					break;

				case 4:
					while (s < end)
					{
						*(s+0) = (*(s+0) << 4) | (data_word >> 28) & 0xf;
						*(s+1) = (*(s+1) << 4) | (data_word >> 24) & 0xf;
						*(s+2) = (*(s+2) << 4) | (data_word >> 20) & 0xf;
						*(s+3) = (*(s+3) << 4) | (data_word >> 16) & 0xf;
						s += 4;
						data_word <<= 16;
						data_bits -= 16;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 4) | (data_word >> 28) & 0xf;
						*(s+1) = (*(s+1) << 4) | (data_word >> 24) & 0xf;
						*(s+2) = (*(s+2) << 4) | (data_word >> 20) & 0xf;
						*(s+3) = (*(s+3) << 4) | (data_word >> 16) & 0xf;
						s += 4;
						data_word <<= 16;
						data_bits -= 16;
						fillDataBuffer();
					}
					break;

				case 5:
					while (s < end)
					{
						*(s+0) = (*(s+0) << 5) | (data_word >> 27) & 0x1f;
						*(s+1) = (*(s+1) << 5) | (data_word >> 22) & 0x1f;
						*(s+2) = (*(s+2) << 5) | (data_word >> 17) & 0x1f;
						s += 3;
						data_word <<= 15;
						data_bits -= 15;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 5) | (data_word >> 27) & 0x1f;
						*(s+1) = (*(s+1) << 5) | (data_word >> 22) & 0x1f;
						*(s+2) = (*(s+2) << 5) | (data_word >> 17) & 0x1f;
						s += 3;
						data_word <<= 15;
						data_bits -= 15;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 5) | (data_word >> 27) & 0x1f;
						*(s+1) = (*(s+1) << 5) | (data_word >> 22) & 0x1f;
						s += 2;
						data_word <<= 10;
						data_bits -= 10;
						fillDataBuffer();
					}
					break;

				case 6:
					while (s < end)
					{
						*(s+0) = (*(s+0) << 6) | (data_word >> 26) & 0x3f;
						*(s+1) = (*(s+1) << 6) | (data_word >> 20) & 0x3f;
						s += 2;
						data_word <<= 12;
						data_bits -= 12;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 6) | (data_word >> 26) & 0x3f;
						*(s+1) = (*(s+1) << 6) | (data_word >> 20) & 0x3f;
						s += 2;
						data_word <<= 12;
						data_bits -= 12;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 6) | (data_word >> 26) & 0x3f;
						*(s+1) = (*(s+1) << 6) | (data_word >> 20) & 0x3f;
						s += 2;
						data_word <<= 12;
						data_bits -= 12;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 6) | (data_word >> 26) & 0x3f;
						*(s+1) = (*(s+1) << 6) | (data_word >> 20) & 0x3f;
						s += 2;
						data_word <<= 12;
						data_bits -= 12;
						fillDataBuffer();
					}
					break;

				case 7:
					while (s < end)
					{
						*(s+0) = (*(s+0) << 7) | (data_word >> 25) & 0x7f;
						*(s+1) = (*(s+1) << 7) | (data_word >> 18) & 0x7f;
						s += 2;
						data_word <<= 14;
						data_bits -= 14;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 7) | (data_word >> 25) & 0x7f;
						*(s+1) = (*(s+1) << 7) | (data_word >> 18) & 0x7f;
						s += 2;
						data_word <<= 14;
						data_bits -= 14;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 7) | (data_word >> 25) & 0x7f;
						*(s+1) = (*(s+1) << 7) | (data_word >> 18) & 0x7f;
						s += 2;
						data_word <<= 14;
						data_bits -= 14;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 7) | (data_word >> 25) & 0x7f;
						*(s+1) = (*(s+1) << 7) | (data_word >> 18) & 0x7f;
						s += 2;
						data_word <<= 14;
						data_bits -= 14;
						fillDataBuffer();
					}
					break;

				case 8:
					while (s < end)
					{
						*(s+0) = (*(s+0) << 8) | (data_word >> 24) & 0xff;
						*(s+1) = (*(s+1) << 8) | (data_word >> 16) & 0xff;
						s += 2;
						data_word <<= 16;
						data_bits -= 16;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 8) | (data_word >> 24) & 0xff;
						*(s+1) = (*(s+1) << 8) | (data_word >> 16) & 0xff;
						s += 2;
						data_word <<= 16;
						data_bits -= 16;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 8) | (data_word >> 24) & 0xff;
						*(s+1) = (*(s+1) << 8) | (data_word >> 16) & 0xff;
						s += 2;
						data_word <<= 16;
						data_bits -= 16;
						fillDataBuffer();

						*(s+0) = (*(s+0) << 8) | (data_word >> 24) & 0xff;
						*(s+1) = (*(s+1) << 8) | (data_word >> 16) & 0xff;
						s += 2;
						data_word <<= 16;
						data_bits -= 16;
						fillDataBuffer();
					}
					break;

				default:
					shift = 32 - k_bits;
					while (s < end)
					{
						*s = (*s << k_bits) | (data_word >> shift);
						s++;
						data_word <<= k_bits;
						data_bits -= k_bits;
						fillDataBuffer();

						*s = (*s << k_bits) | (data_word >> shift);
						s++;
						data_word <<= k_bits;
						data_bits -= k_bits;
						fillDataBuffer();
					}

					break;
			} // switch (k_bits)
			return 1;
		}
		#pragma endregion
	} // switch(id)
}

int uncompress(unsigned char* compressed,int compressedLen,unsigned int* data,int nbInput){
	nbBlock=(int)ceil((double)nbInput/PIXEL_PER_BLOCK);

	input_ptr=compressed;
#ifdef GAUSS
	input_ptr_end = compressed + compressedLen *  sizeof(unsigned char) + 2;
#endif
	initUncompressor();

	unsigned int *s=data;
	unsigned int *end=s+PIXEL_PER_BLOCK;

	//Decodage du premier bloc (bloc de référence)
	int nbDecodedBlock=decodeReferenceBlock(s,end);
	if (nbDecodedBlock == -1)
		return -1;

	s=end+(nbDecodedBlock-1)*PIXEL_PER_BLOCK;
	end = s + PIXEL_PER_BLOCK;
	int i = nbDecodedBlock;
#ifdef GAUSS
	try {
#endif
		//Decodage des autres blocks
		while(i < nbBlock)
		{
			nbDecodedBlock = decodeNormalBlock(s, end, i + 1);
			i += nbDecodedBlock;
			s = end + (nbDecodedBlock - 1)*PIXEL_PER_BLOCK;
			end = s + PIXEL_PER_BLOCK;
		}
#ifdef GAUSS
	}
	catch(std::exception e){
		//return i*PIXEL_PER_BLOCK;
		char buffer[128];
		sprintf_s(buffer, 128, "%s : block %i/%i (size: %i/%i)", e.what(), i, nbBlock, ((size_t)s-(size_t)data)/2,nbInput*2);
		throw std::exception(buffer);
	}
#endif
	return nbBlock*PIXEL_PER_BLOCK;
}

static void fillDataBuffer(){
	if (data_bits <= 16)
	{
#ifdef GAUSS
		if ((size_t)input_ptr > (size_t)input_ptr_end) {
			char buffer[36];
			sprintf_s(buffer,36, "Illegal access to %X > %X", (size_t)input_ptr, (size_t)input_ptr_end);
			throw std::exception(buffer);
		}
#endif
		data_word |= *input_ptr++ << (24 - data_bits);
		data_word |= *input_ptr++ << (16 - data_bits);
		data_bits += 16;
	}
}
