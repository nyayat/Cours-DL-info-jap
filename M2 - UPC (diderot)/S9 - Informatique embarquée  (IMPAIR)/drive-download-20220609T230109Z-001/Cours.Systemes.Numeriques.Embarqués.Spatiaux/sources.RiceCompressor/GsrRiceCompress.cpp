#include "GsrRiceCompress.h"
#include "GsrNotExportedHeader.h"
#include "GsrRiceMacroAndConst.h"
#include <math.h>
#include <string.h>

void initCompressor(){
	zero_blocks=0;
	global_packed_bits = 32;
	packed_bits=global_packed_bits;
	global_packed_value = 0;
	packed_value = global_packed_value;
}

int preprocess(unsigned short* data,int nbInput,unsigned int* preprocessed,PreprocessMode mode){
	switch(mode){
		case ESTIMATE_1D_H	:
			{
				int lastValue=data[0];
				*preprocessed++=lastValue;
				int diff;
				for(int i=1;i<nbInput;i++){
					diff =data[i]-lastValue;
					if (diff >= 0)
					{
						if (diff <= lastValue)
							*preprocessed++= diff << 1;
						else
							*preprocessed++ = data[i];
					}
					else
					{
						if (diff >= (lastValue-XMAX))
							*preprocessed++ = ((-diff)<<1) - 1;
						else
							*preprocessed++ = XMAX-data[i];
					}
					lastValue=data[i];
				}
				return nbInput;
			}
			break;
		default :
			return -1;
	}
}

void encodeZeroBlock(bool maxOrEnd){
	if (zero_blocks <= 4) {
		newbits = zero_blocks;
	}
	else {
		if (maxOrEnd == true) {
			newbits = 5;
		}
		else {
			newbits = zero_blocks+1;
		}
	}

	packfs(newbits);
	zero_blocks = 0;
}

void encodeMSB(unsigned int* data,unsigned int* end,int lsbSize){
	unsigned int fsval;
	unsigned int fsval2;
	unsigned long value;
	while (data < end)
	{
		fsval  = (*data++ >> lsbSize) + 1;
		fsval2 = (*data++ >> lsbSize) + 1;
		if (fsval + fsval2 <= 16)
		{
			value = (1 << fsval2) | 1;
			pack1(value, fsval + fsval2);
		}
		else
		{
			packfs(fsval);
			packfs(fsval2);
		}
	}
}

void encodeLSB(unsigned int* data,unsigned int* end,int lsbSize){
	unsigned int size=8*lsbSize;
	unsigned int mask=masknot[lsbSize];
	int cptr=2*lsbSize;
	unsigned long value;

	value = *data++ & mask;
	while (data < end)
	{
		if(cptr>16){
			pack1(value, cptr-lsbSize);
			value = *data++ & mask;
			cptr=2*lsbSize;
		}
		else{
			value = (value << lsbSize) | *data++ & mask;
			cptr+=lsbSize;
		}
	}
	pack1(value, cptr-lsbSize);
}

void encodeLSBHO(unsigned int* data,unsigned int* end,int lsbSize){
	unsigned int mask=masknot[lsbSize];
	unsigned long value;
	while (data < end)
	{
		value = *data++ & mask;
		value = (value << lsbSize) | *data++ & mask;
		pack1(value, lsbSize*2);
	}
}

void encodeReferenceBlock(unsigned int* data,unsigned int* end){
	unsigned int *s,*s1;
	int id;
	int fsval;
	int ksplits;
	unsigned int mask;
	unsigned int num;

	s=data;

	s++;
	id = find_ref_winner16(s, end);

	switch(id){
		case ID_ZERO:
			{
				pack1(0, ID_BITS+1);						//Stockage FS
				pack1(*data, BITS_PER_PIXELS);	// Stockage Ref Value
				zero_blocks = 1;
				s = end;
			}
			break;
		case ID_FS:
			{
				pack1(id, ID_BITS);							//Stockage FS
				pack1(*data, BITS_PER_PIXELS);	// Stockage Ref Value
				//Stockage MSB
				while (s < end)
				{
					fsval = *s++ + 1;
					packfs(fsval);
				}
			}
			break;
		case ID_LOW:
			{
				pack1(1, ID_BITS+1);						//Stockage FS
				pack1(*data, BITS_PER_PIXELS);	// Stockage Ref Value
				fsval = ext2_array[0][*s++];
				packfs(fsval);
				while (s < end)
				{
					fsval = ext2_array[*s][*(s+1)];
					packfs(fsval);
					s += 2;
				}
			}
			break;
		case ID_DEFAULT:
		{
			pack1(id, ID_BITS);							//Stockage FS
			pack1(*data, BITS_PER_PIXELS);	// Stockage Ref Value
			while (s < end)
				pack1(*s++, BITS_PER_PIXELS);
		}
		break;
		default:
			{
				pack1(id, ID_BITS);							//Stockage FS
				pack1(*data, BITS_PER_PIXELS);	// Stockage Ref Value
				ksplits = id-1;
				//Stockage MSB
				s1 = s;
				while (s1 < end)
				{
					fsval = (*s1++ >> ksplits) + 1;
					packfs(fsval);
				}
				//Stockage LSB
				mask = masknot[ksplits];
				while (s < end)
				{
					num = *s++ & mask;
					pack1(num, ksplits);
				}
			}
			break;
	}
}

void encodeNormalBlock(unsigned int* data, unsigned int* end,int blockNro){
	int id;
	int fsval;
	int ksplits;
	unsigned int mask;
	unsigned int num;

	id = find_winner16(data, end);

	switch(id){
		case ID_ZERO:
			{
				if (zero_blocks == 0)
					pack1(0, ID_BITS+1);

				zero_blocks++;
				if ((blockNro & (MAX_ZERO_BLOCKS-1)) == 0 || blockNro == nbBlock)
				{
					encodeZeroBlock(true);
				}
			}
			break;
		case ID_FS:
			{
				if (zero_blocks) encodeZeroBlock(false);
				pack1(id, ID_BITS);
				while (data < end)
				{
					fsval = *data++ + 1;
					packfs(fsval);
				}
			}
			break;
		case ID_LOW:
			{
				if (zero_blocks) encodeZeroBlock(false);
				packfs(ID_BITS+1);
				while (data < end)
				{
					fsval = ext2_array[*data][*(data+1)];
					packfs(fsval);
					data += 2;
				}
			}
			break;
		default:
			{
				if (zero_blocks) encodeZeroBlock(false);
				pack1(id, ID_BITS);
				if (id == ID_DEFAULT)
				{
					while (data < end)
						pack1(*data++, BITS_PER_PIXELS);
				}
				else
				{
					ksplits = id-1;
					switch (ksplits)
					{
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
						{
							encodeMSB(data,end,ksplits);
							encodeLSB(data,end,ksplits);
						}
						break;
					case 6:
					case 7:
					case 8:
						{
							encodeMSB(data,end,ksplits);
							encodeLSBHO(data,end,ksplits);
						}
						break;
					case 9:
					case 10:
					case 11:
					case 12:
					case 13:
					case 14:
					case 15:
						encodeMSB(data,end,ksplits);
						mask = masknot[ksplits];
						while (data < end)
						{
							num = *data++ & mask;
							pack1(num, ksplits);
						}
						break;

					default:
						{
							encodeMSB(data,end,ksplits);
							mask = masknot[ksplits];
							while (data < end)
							{
								num = *data++ & mask;
								pack1(num, ksplits);
							}
						}
						break;
					}
				}
			}
			break;
	}
}

int compress(unsigned int* data,int nbInput,unsigned char* compressed,int compressedLen){
	unsigned int* s;
	unsigned int* end;
	nbBlock=(int)ceil((double)nbInput/PIXEL_PER_BLOCK);

	global_bptr = compressed;
	bptr = global_bptr;

	initCompressor();

	s=data;
	end=s+PIXEL_PER_BLOCK;

	//Encodage du premier bloc (bloc de référence)
	encodeReferenceBlock(s,end);

	s=end;
	end += PIXEL_PER_BLOCK;

	//Encodage des autres blocks
	for (int i=2; i <= nbBlock; i++)
	{
		encodeNormalBlock(s,end,i);
		s=end;
		end += PIXEL_PER_BLOCK;
	}
	if (zero_blocks) encodeZeroBlock(false);
	flush();
	return (int)(bptr-compressed);
}

/////////////////////////////////////////////////////////////////////////////////////////

static void flush()
{
	unsigned v16;

	if (packed_bits < 32)
	{
		v16 = packed_value >> 16;
		*bptr++ = v16 >> 8;
		if (packed_bits < 24)
			*bptr++ = v16;
	}
}

static unsigned int c_ext2(unsigned int* sigma, unsigned int* end)
{
	unsigned int total;
	unsigned int*s;

	s = sigma;
	if ((end - s) & 1)
	{
		if (*s > MAX_EXT2)
			return 9999;

		total = ext2_array[0][*s++];
	}
	else
		total = 0;

	while (s < end)
	{
		if (*s + *(s+1) > MAX_EXT2)
			return 9999;

		total += ext2_array[*s][*(s+1)];
		s += 2;
	}

	return total+1;
}

static int find_winner16(unsigned int *sigma, unsigned int *end)
{
	unsigned int bits;
	unsigned int sum;
	unsigned int *s;

	/*** Find best coding for 16 pixels per block. ***/
	/*** The compression may be slightly sub optimum ***/
	s = sigma;
	sum = 0;
	while (s < end)
	{
		sum += *s++;
		sum += *s++;
	}

	/*** J = 16; V(k) = J * 2^k - J/2; V(k) = 2*V(k-1) + 8 ***/
	if (sum == 0)
		return ID_ZERO;
	else if (sum <= 3)
		return ID_LOW;
	else if (sum <= 24)
	{
		bits = c_ext2(sigma, end);
		if (bits < sum + 16)
			return ID_LOW;
		else
			return ID_FS;
	}
	else if (sum <= 56)
		return ID_K1;
	else if (sum <= 120)
		return ID_K2;
	else if (sum <= 248)
		return ID_K3;
	else if (sum <= 504)
		return ID_K4;
	else if (sum <= 1016)
		return ID_K5;
	else if (sum <= 2040)
		return ID_K6;
	else if (sum <= 4088)
		return ID_K7;
	else if (sum <= 8184)
		return ID_K8;
	else if (sum <= 16376)
		return ID_K9;
	else if (sum <= 32760)
		return ID_K10;
	else if (sum <= 65528)
		return ID_K11;
	else if (sum <= 131064)
		return ID_K12;
	else if (sum <= 262136)
		return ID_K13;
	else if (sum <= 524280)
		return ID_K14;
	else
		return ID_DEFAULT;
}

static int find_ref_winner16(unsigned int *sigma, unsigned int *end)
{
	unsigned int bits;
	unsigned int sum;
	unsigned int *s;

	/*** Find best coding for 16 pixels per block. ***/
	/*** The compression may be slightly sub optimum ***/
	s = sigma;
	sum = 0;
	while (s < end)
		sum += *s++;

	/*** J = 15; V(k) = J * 2^k - J/2; V(k) = 2*V(k-1) + 7 ***/
	if (sum == 0)
		return ID_ZERO;
	else if (sum <= 3)
		return ID_LOW;
	else if (sum <= 23)
	{
		bits = c_ext2(sigma, end);
		if (bits < sum + 15)
			return ID_LOW;
		else
			return ID_FS;
	}
	else if (sum <= 53)
		return ID_K1;
	else if (sum <= 113)
		return ID_K2;
	else if (sum <= 233)
		return ID_K3;
	else if (sum <= 473)
		return ID_K4;
	else if (sum <= 953)
		return ID_K5;
	else if (sum <= 1913)
		return ID_K6;
	else if (sum <= 3833)
		return ID_K7;
	else if (sum <= 7673)
		return ID_K8;
	else if (sum <= 15353)
		return ID_K9;
	else if (sum <= 30713)
		return ID_K10;
	else if (sum <= 61433)
		return ID_K11;
	else if (sum <= 122873)
		return ID_K12;
	else if (sum <= 245753)
		return ID_K13;
	else if (sum <= 491513)
		return ID_K14;
	else
		return ID_DEFAULT;
}
