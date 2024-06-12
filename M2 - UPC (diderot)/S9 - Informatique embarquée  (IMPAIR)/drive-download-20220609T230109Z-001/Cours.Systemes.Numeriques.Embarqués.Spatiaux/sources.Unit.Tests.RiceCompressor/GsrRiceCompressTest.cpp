/*************************************************************************

 Copyright (c) 2013 by CNRS/LESIA

 This software is copyrighted by and is the sole property of CNRS/LESIA.
 All rights, title, ownership, or other interests in the software remain
 the property of CNRS/LESIA.
 This software may only be used in accordance with the corresponding
 license agreement. Any unauthorized use, duplication, transmission,
 distribution, or disclosure of this software is expressly forbidden.

 This Copyright notice may not be removed or modified without prior
 written consent of CNRS/LESIA.


 LESIA
 Observatoire de Meudon
 5 place Jules Janssen
 92195 Meudon
 http://www.lesia.obspm.fr/-Logiciels-embarques-.html
 *************************************************************************/

/************************************************************************

 RiceCompressor_LibCUnit Component

 rice_compressTest.cpp

 $Rev: 000 $

 ************************************************************************/

#include "Basic.h"
#include <stdio.h>
#include <GsrRiceCompress.h>
#include <GsrRiceUncompress.h>
#include <UnitTests/compress/GsrRiceCompressorTestDataSet.hpp>

const unsigned int sizeData_m = 96;
unsigned int preprocessedBuffer_m[sizeData_m * 2];
unsigned char compressedBuffer_m[sizeData_m * 4 * 2];
unsigned int uncompressBuffer_m[sizeData_m * 2];
unsigned short extractedBuffer_m[sizeData_m * 2];

/* The suite initialization function.
 * initializes the variables
 * Returns zero on success, non-zero otherwise.
 */
int initSuite_riceCompressTest(void) {
	return 0;
}

/* The suite cleanup function.
 * Closes the temporary file used by the tests.
 * Returns zero on success, non-zero otherwise.
 */
int cleanSuite_riceCompressTest(void) {
	return 0;
}

/**
 * Test the Rice compression and decompression full process on a data set of 16 samples.
 */
void riceCompressTest_fullProcess16() {
	const unsigned int sizeData = 16;
	unsigned short dataBuffer[sizeData] = { 10, 11, 12, 13, 11, 10, 30, 10, 10, 20, 10, 11, 12, 13, 14, 15 };
	// the buffers are double the size of the data in case the compressed packet is greater than the original one
	unsigned int preprocessedBuffer[sizeData * 2];
	unsigned int uncompressBuffer[sizeData * 2];
	unsigned char compressedBuffer[sizeData * 4];
	unsigned short extractedBuffer[sizeData];
	int compressResult = 0;
	int preprocessResult = 0;
	int uncompressResult = 0;
	int postprocessorResult = 0;

	preprocessResult = preprocess(dataBuffer, sizeData, preprocessedBuffer, ESTIMATE_1D_H);
	compressResult = compress(preprocessedBuffer, preprocessResult, compressedBuffer, sizeData * 2);
	// the number of elements of uncompressBuffer on which uncompress will run must be the same as the number of elements of dataBuffer
	uncompressResult = uncompress(compressedBuffer, compressResult, uncompressBuffer, sizeData);
	postprocessorResult = postprocessor(uncompressBuffer, uncompressResult, extractedBuffer, ESTIMATE_1D_H);

	// the compression works for this data set
	CU_ASSERT_TRUE(compressResult < sizeData);
	// the data before compression must be the same as the data after the decompression
	for (int i = 0; i < sizeData; i++) {
		CU_ASSERT_EQUAL(dataBuffer[i], extractedBuffer[i]);
	}

	CU_ASSERT_EQUAL(postprocessorResult, sizeData);
	CU_ASSERT_EQUAL(uncompressResult, sizeData);
	CU_ASSERT_EQUAL(postprocessorResult, uncompressResult);
}

/**
 * Test the Rice compression and decompression full process on a data set of 32 samples.
 */
void riceCompressTest_fullProcess32() {
	const unsigned int sizeData32 = 32;
	unsigned short dataBuffer[sizeData32] = { 10, 11, 12, 13, 11, 10, 30, 10, 10, 20, 10, 11, 12, 13, 14, 15, 10, 11, 12, 13, 11, 10, 30,
			10, 10, 20, 10, 11, 12, 13, 14, 15 };
	// the buffers are double the size of the data in case the compressed packet is greater than the original one
	unsigned int preprocessedBuffer[sizeData32 * 2];
	unsigned char compressedBuffer[sizeData32 * 4 * 2];
	unsigned short extractedBuffer[sizeData32 * 2];
	unsigned int uncompressBuffer[sizeData32 * 2];
	int compressResult = 0;
	int preprocessResult = 0;
	int uncompressResult = 0;
	int postprocessorResult = 0;

	preprocessResult = preprocess(dataBuffer, sizeData32, preprocessedBuffer, ESTIMATE_1D_H);
	compressResult = compress(preprocessedBuffer, preprocessResult, compressedBuffer, sizeData32 * 2);
	// the number of elements of uncompressBuffer on which uncompress will run must be the same as the number of elements of dataBuffer
	uncompressResult = uncompress(compressedBuffer, compressResult, uncompressBuffer, sizeData32);
	postprocessorResult = postprocessor(uncompressBuffer, uncompressResult, extractedBuffer, ESTIMATE_1D_H);
	// the compression works for this data set
	CU_ASSERT_TRUE(compressResult < sizeData32);
	// the data before compression must be the same as the data after the decompression
	for (int i = 0; i < sizeData32; i++) {
		CU_ASSERT_EQUAL(dataBuffer[i], extractedBuffer[i]);
	}

	CU_ASSERT_EQUAL(postprocessorResult, sizeData32);
	CU_ASSERT_EQUAL(uncompressResult, sizeData32);
	CU_ASSERT_EQUAL(postprocessorResult, uncompressResult);
}

/**
 * Test the Rice compression and decompression full process on a data set of 320 samples.
 */
void riceCompressTest_fullProcess320() {
	const unsigned int sizeData320 = 320;
	unsigned short dataBuffer[sizeData320];
	// the buffers are double the size of the data in case the compressed packet is greater than the original one
	unsigned int preprocessedBuffer[sizeData320 * 2];
	unsigned char compressedBuffer[sizeData320 * 4 * 2];
	unsigned short extractedBuffer[sizeData320 * 2];
	unsigned int uncompressBuffer[sizeData320 * 2];
	int compressResult = 0;
	int preprocessResult = 0;
	int uncompressResult = 0;
	int postprocessorResult = 0;

	for (int i = 0; i < sizeData320; i++) {
		dataBuffer[i] = i;
	}

	preprocessResult = preprocess(dataBuffer, sizeData320, preprocessedBuffer, ESTIMATE_1D_H);
	compressResult = compress(preprocessedBuffer, preprocessResult, compressedBuffer, sizeData320 * 2);
	// the number of elements of uncompressBuffer on which uncompress will run must be the same as the number of elements of dataBuffer
	uncompressResult = uncompress(compressedBuffer, compressResult, uncompressBuffer, sizeData320);
	postprocessorResult = postprocessor(uncompressBuffer, uncompressResult, extractedBuffer, ESTIMATE_1D_H);

	printf("\n");
	printf("preprocessResult == %d\n", preprocessResult);
	printf("compressResult == %d\n", compressResult);

	printf("*****************************************\n");
	printf("Initial data:\n");
	for (int i = 0; i < sizeData320; i++) {
		printf("%x\t", dataBuffer[i]);
	}
	printf("\n");

	printf("*****************************************\n");
	printf("Preprocessed buffer:\n");
	for (int i = 0; i < preprocessResult; i++) {
		printf("%x\t", preprocessedBuffer[i]);
	}
	printf("\n");

	printf("*****************************************\n");
	printf("Compressed buffer:\n");
	for (int i = 0; i < compressResult; i++) {
		printf("%x\t", compressedBuffer[i]);
	}
	printf("\n");

	printf("Compression ratio = %f\n", (float) (sizeData320 * sizeof(unsigned short)) / (float) compressResult);
	printf("*****************************************\n");

	// the compression works for this data set
	CU_ASSERT_TRUE(compressResult < sizeData320);
	// the data before compression must be the same as the data after the decompression
	for (int i = 0; i < sizeData320; i++) {
		CU_ASSERT_EQUAL(dataBuffer[i], extractedBuffer[i]);
	}

	CU_ASSERT_EQUAL(postprocessorResult, sizeData320);
	CU_ASSERT_EQUAL(uncompressResult, sizeData320);
	CU_ASSERT_EQUAL(postprocessorResult, uncompressResult);
}

/**
 * Test the Rice compression and decompression full process on a data set of 352 samples with padding.
 */
void riceCompressTest_fullProcess352Padding() {
	const unsigned int sizeData352 = 352;
	unsigned short dataBuffer[sizeData352];
	// the buffers are double the size of the data in case the compressed packet is greater than the original one
	unsigned int preprocessedBuffer[sizeData352 * 2];
	unsigned char compressedBuffer[sizeData352 * 4 * 2];
	unsigned short extractedBuffer[sizeData352 * 2];
	unsigned int uncompressBuffer[sizeData352 * 2];
	int compressResult = 0;
	int preprocessResult = 0;
	int uncompressResult = 0;
	int postprocessorResult = 0;

	// init 340 numbers
	for (int i = 0; i < sizeData352 - 12; i++) {
		dataBuffer[i] = i;
	}
	// copy the last valid number until a multiple of 16 is reached (352 == 16 * 22)
	for (int i = sizeData352 - 12; i < sizeData352; i++) {
		dataBuffer[i] = dataBuffer[i - 1];
	}

	preprocessResult = preprocess(dataBuffer, sizeData352, preprocessedBuffer, ESTIMATE_1D_H);
	compressResult = compress(preprocessedBuffer, preprocessResult, compressedBuffer, sizeData352 * 2);
	// the number of elements of uncompressBuffer on which uncompress will run must be the same as the number of elements of dataBuffer
	uncompressResult = uncompress(compressedBuffer, compressResult, uncompressBuffer, sizeData352);
	postprocessorResult = postprocessor(uncompressBuffer, uncompressResult, extractedBuffer, ESTIMATE_1D_H);

	printf("\n");
	printf("preprocessResult == %d\n", preprocessResult);
	printf("compressResult == %d\n", compressResult);

	printf("*****************************************\n");
	for (int i = 0; i < compressResult; i++) {
		printf("%x\t", compressedBuffer[i]);
	}
	printf("\n");

	printf("*****************************************\n");
	for (int i = 0; i < preprocessResult; i++) {
		printf("%x\t", preprocessedBuffer[i]);
	}
	printf("\n");
	printf("*****************************************\n");
	printf("Compression ratio = %f\n", (float) (sizeData352 * sizeof(unsigned short)) / (float) compressResult);
	printf("*****************************************\n");

	// the compression works for this data set
	CU_ASSERT_TRUE(compressResult < sizeData352);
	// the data before compression must be the same as the data after the decompression
	for (int i = 0; i < sizeData352; i++) {
		CU_ASSERT_EQUAL(dataBuffer[i], extractedBuffer[i]);
	}

	CU_ASSERT_EQUAL(postprocessorResult, sizeData352);
	CU_ASSERT_EQUAL(uncompressResult, sizeData352);
	CU_ASSERT_EQUAL(postprocessorResult, uncompressResult);
}

/// test from RiceCompressor_Exe - suite1.cpp
void riceCompressTest_fullProcessImage() {
	const int SIZE_35 = 120 * 120;
	unsigned short inputImage35[SIZE_35];
	unsigned short outputImage35[SIZE_35];
	unsigned int preprocessedImage35[SIZE_35];
	unsigned int postprocessedImage35[SIZE_35];
	unsigned char compressedImage35[SIZE_35 * 2];

	for (int i = 0; i < SIZE_35; i++) {
		inputImage35[i] = 'A';
		inputImage35[i] = inputImage35[i] << 8;
		inputImage35[i] += 'A' + i % 10;
		outputImage35[i] = 0;
		preprocessedImage35[i] = 0;
		postprocessedImage35[i] = 0;
		compressedImage35[i] = 0;
		compressedImage35[SIZE_35 + i] = 0;
	}

	int preProcessedNb35 = preprocess(inputImage35, SIZE_35, preprocessedImage35, ESTIMATE_1D_H);
	int compressedNb35 = compress(preprocessedImage35, preProcessedNb35, compressedImage35, SIZE_35 * 2);
	// the number of elements of uncompressBuffer on which uncompress will run must be the same as the number of elements of dataBuffer
	int uncompressedNb35 = uncompress(compressedImage35, compressedNb35, postprocessedImage35, SIZE_35);
	int postProcessedNb35 = postprocessor(postprocessedImage35, uncompressedNb35, outputImage35, ESTIMATE_1D_H);

	CU_ASSERT_EQUAL(postProcessedNb35, SIZE_35);
	CU_ASSERT_EQUAL(uncompressedNb35, SIZE_35);
	CU_ASSERT_EQUAL(postProcessedNb35, uncompressedNb35);
	// the compression works for this data set
	CU_ASSERT_TRUE(compressedNb35 < SIZE_35);
	// the data before compression must be the same as the data after the decompression
	for (int i = 0; i < SIZE_35; i++) {
		unsigned short currentValue = 'A';
		currentValue = currentValue << 8;
		currentValue += 'A' + i % 10;
		CU_ASSERT(inputImage35[i] == currentValue);
		CU_ASSERT(outputImage35[i] == currentValue);
	}
}

/**
 * Test the behavior of Rice compression and decompression full process on a data set of 768 samples
 */
void riceCompressTest_fullProcess768() {
	const unsigned int sizeData768 = 768;
	// init 768 numbers
	short dataBuffer[sizeData768] = { 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426,
			426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 427, 426, 426, 426, 426, 426, 426, 426,
			427, 427, 427, 427, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426,
			426, 426, 425, 425, 425, 425, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 427, 427, 426, 426, 426, 426, 426, 426,
			426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426, 426,
			426, 426, 426, 426, 426, 426, 425, 425, 425, 426, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179,
			179, 179, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 179, 179, 179,
			179, 180, 180, 181, 181, 181, 181, 180, 180, 179, 179, 179, 179, 179, 180, 180, 179, 179, 179, 179, 179, 179, 179, 179, 179,
			179, 179, 179, 179, 179, 179, 179, 178, 178, 178, 178, 178, 178, 179, 179, 180, 180, 179, 179, 179, 179, 179, 180, 179, 179,
			179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 180, 180, 180,
			180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 907, 907, 907, 907, 907, 907, 907, 907, 907, 907, 907, 906,
			906, 906, 906, 906, 906, 906, 906, 906, 907, 907, 907, 907, 907, 907, 907, 906, 906, 906, 906, 906, 907, 907, 907, 906, 906,
			906, 906, 906, 906, 906, 906, 906, 906, 907, 907, 907, 906, 906, 906, 906, 907, 907, 907, 907, 907, 907, 907, 907, 908, 907,
			907, 907, 906, 906, 906, 906, 906, 906, 907, 907, 907, 907, 907, 907, 907, 907, 907, 908, 908, 907, 907, 907, 907, 907, 907,
			907, 907, 907, 907, 907, 907, 907, 906, 906, 905, 905, 906, 906, 906, 906, 906, 906, 906, 906, 907, 907, 907, 907, 907, 907,
			907, 907, 907, 907, 907, 907, 906, 906, 906, 906, 907, 907, 907, 907, 907, 908, 422, 422, 422, 421, 421, 421, 422, 421, 421,
			421, 421, 421, 421, 421, 421, 421, 421, 421, 421, 421, 422, 421, 421, 421, 421, 422, 422, 422, 422, 422, 422, 422, 422, 422,
			421, 421, 421, 421, 421, 421, 422, 422, 422, 422, 422, 422, 422, 422, 422, 421, 421, 421, 421, 421, 421, 422, 422, 422, 422,
			422, 421, 421, 421, 421, 421, 422, 422, 422, 422, 421, 421, 421, 421, 421, 421, 421, 421, 421, 422, 422, 422, 422, 422, 422,
			421, 421, 421, 421, 422, 422, 422, 421, 421, 421, 421, 421, 421, 421, 421, 421, 422, 422, 422, 421, 421, 421, 421, 421, 421,
			422, 421, 421, 421, 421, 421, 421, 421, 422, 422, 422, 422, 421, 421, 421, 421, 421, 421, 421, 416, 416, 416, 416, 416, 417,
			417, 417, 416, 416, 416, 416, 416, 416, 416, 416, 416, 416, 416, 416, 416, 416, 416, 416, 416, 416, 416, 417, 417, 417, 417,
			417, 417, 417, 417, 416, 416, 416, 416, 416, 416, 416, 417, 417, 417, 417, 417, 417, 417, 417, 417, 416, 416, 416, 416, 416,
			416, 416, 416, 416, 416, 416, 417, 417, 417, 416, 416, 416, 416, 416, 416, 415, 415, 416, 416, 416, 416, 416, 417, 417, 417,
			417, 417, 417, 417, 417, 417, 417, 417, 417, 417, 417, 417, 417, 417, 417, 416, 416, 416, 416, 416, 416, 416, 417, 417, 416,
			416, 415, 415, 416, 416, 416, 416, 416, 416, 417, 417, 417, 417, 417, 416, 416, 416, 416, 416, 416, 417, 417, 392, 392, 392,
			391, 391, 391, 391, 391, 392, 392, 392, 392, 391, 391, 391, 391, 391, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392,
			392, 392, 392, 392, 391, 391, 391, 391, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 391, 391, 391, 391, 391,
			392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 391, 391, 391, 391, 391, 391, 391, 391, 391, 391,
			392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 392, 391, 391, 391, 391, 391, 391, 391, 392, 392, 392,
			392, 392, 392, 392, 392, 391, 391, 391, 391, 392, 392, 392, 392, 391, 391, 391, 391, 391, 391, 391, 391, 391, 391, 391, 391 };

	// the buffers are double the size of the data in case the compressed packet is greater than the original one
	unsigned int preprocessedBuffer[sizeData768 * 2];
	unsigned char compressedBuffer[sizeData768 * 4 * 2];
	unsigned short extractedBuffer[sizeData768 * 2];
	unsigned int uncompressBuffer[sizeData768 * 2];
	int compressResult = 0;
	int preprocessResult = 0;
	int uncompressResult = 0;
	int postprocessorResult = 0;

	//--- call the full process of Rice compression-decompression
	preprocessResult = preprocess(reinterpret_cast<unsigned short*>(dataBuffer), sizeData768, preprocessedBuffer, ESTIMATE_1D_H);
	compressResult = compress(preprocessedBuffer, preprocessResult, compressedBuffer, sizeData768 * 2);
	// the number of elements of uncompressBuffer on which uncompress will run must be the same as the number of elements of dataBuffer
	uncompressResult = uncompress(compressedBuffer, compressResult, uncompressBuffer, sizeData768);
	postprocessorResult = postprocessor(uncompressBuffer, uncompressResult, extractedBuffer, ESTIMATE_1D_H);

	//--- check the results
	printf("\n");
	printf("preprocessResult == %d\n", preprocessResult);
	printf("compressResult == %d\n", compressResult);
	printf("uncompressResult == %d\n", uncompressResult);
	printf("postprocessorResult == %d\n", postprocessorResult);

	printf("*****************************************\n");
	printf("Initial data:\n");
	for (int i = 0; i < sizeData768; i++) {
		printf("%X\t", dataBuffer[i]);
	}
	printf("\n");

	printf("*****************************************\n");
	printf("Preprocessed buffer:\n");
	for (int i = 0; i < preprocessResult; i++) {
		printf("%X\t", preprocessedBuffer[i]);
	}
	printf("\n");

	printf("*****************************************\n");
	printf("Compressed buffer:\n");
	for (int i = 0; i < compressResult; i++) {
		printf("%X\t", compressedBuffer[i]);
	}
	printf("\n");

	printf("*****************************************\n");
	printf("Uncompress buffer:\n");
	for (int i = 0; i < uncompressResult; i++) {
		printf("%X\t", uncompressBuffer[i]);
	}
	printf("\n");

	printf("*****************************************\n");
	printf("Extracted buffer:\n");
	for (int i = 0; i < postprocessorResult; i++) {
		printf("%X\t", reinterpret_cast<short*>(extractedBuffer)[i]);
	}
	printf("\n");

	printf("Compression ratio = %f\n", (float) (sizeData768 * sizeof(unsigned short)) / (float) compressResult);
	printf("*****************************************\n");

	// the compression works for this data set
	CU_ASSERT_TRUE(compressResult < sizeData768);
	// the data before compression must be the same as the data after the decompression
	bool bufferDifferent = false;
	int bytePosition = 0;
	short* extractedBufferSigned = reinterpret_cast<short*>(extractedBuffer);
	for (int i = 0; i < sizeData768; i++) {
		if (dataBuffer[i] != extractedBufferSigned[i]) {
			if (bufferDifferent == false) {
				bufferDifferent = true;
				bytePosition = i;
			}
		}
	}
	CU_ASSERT_EQUAL (bufferDifferent, false);
	CU_ASSERT_EQUAL (bytePosition, 0);
	CU_ASSERT_EQUAL(postprocessorResult, sizeData768);
	CU_ASSERT_EQUAL(uncompressResult, sizeData768);
	CU_ASSERT_EQUAL(postprocessorResult, uncompressResult);
}

/**
 * Test the behavior of Rice compression and compression full process on a data set of 7680 samples
 */
void riceCompressTest_fullProcess7680() {
	// the buffers are double the size of the data in case the compressed packet is greater than the original one
	unsigned int preprocessedBuffer[ricePacketBufferSize7680 * 2];
	unsigned char compressedBuffer[ricePacketBufferSize7680 * 4 * 2];
	unsigned short extractedBuffer[ricePacketBufferSize7680 * 2];
	unsigned int uncompressBuffer[ricePacketBufferSize7680 * 2];
	int compressResult = 0;
	int preprocessResult = 0;
	int uncompressResult = 0;
	int postprocessorResult = 0;

	//--- call the full process of Rice compression-decompression
	preprocessResult = preprocess(reinterpret_cast<unsigned short*>(ricePacketBuffer7680), ricePacketBufferSize7680, preprocessedBuffer, ESTIMATE_1D_H);
	compressResult = compress(preprocessedBuffer, preprocessResult, compressedBuffer, ricePacketBufferSize7680 * 2);
	// the number of elements of uncompressBuffer on which uncompress will run must be the same as the number of elements of dataBuffer
	uncompressResult = uncompress(compressedBuffer, compressResult, uncompressBuffer, ricePacketBufferSize7680);
	postprocessorResult = postprocessor(uncompressBuffer, uncompressResult, extractedBuffer, ESTIMATE_1D_H);

	// the compression works for this data set
	CU_ASSERT_TRUE(compressResult < ricePacketBufferSize7680);
	// the data before compression must be the same as the data after the decompression
	bool bufferDifferent = false;
	int bytePosition = 0;
	short* extractedBufferSigned = reinterpret_cast<short*>(extractedBuffer);
	for (int i = 0; i < ricePacketBufferSize7680; i++) {
		if (ricePacketBuffer7680[i] != extractedBufferSigned[i]) {
			if (bufferDifferent == false) {
				bufferDifferent = true;
				bytePosition = i;
			}
		}
	}
	CU_ASSERT_EQUAL (bufferDifferent, false);
	CU_ASSERT_EQUAL (bytePosition, 0);

	CU_ASSERT_EQUAL(postprocessorResult, ricePacketBufferSize7680);
	CU_ASSERT_EQUAL(uncompressResult, ricePacketBufferSize7680);
	CU_ASSERT_EQUAL(postprocessorResult, uncompressResult);
}

/**
 * Test the behavior of Rice algorithm with a data set of 398 TDS SCIENCE LFM CWF packets.
 * The data set can be found at : https://jira-lesia.obspm.fr/browse/RCL-7.
 * See https://jira-lesia.obspm.fr/secure/attachment/54496/TDS-031-01_Sweeping_Frequency_LF_R3_Log_110316_without_comp_LFM_CWF_398_packets_305664_samples.xlsx
 */
void riceCompressTest_fullProcess305664() {
	// the buffers are double the size of the data in case the compressed packet is greater than the original one
	unsigned int preprocessedBuffer[riceBufferSize305664PacketSize * 2];
	unsigned char compressedBuffer[riceBufferSize305664PacketSize * 4 * 2];
	unsigned short extractedBuffer[riceBufferSize305664PacketSize * 2];
	unsigned int uncompressBuffer[riceBufferSize305664PacketSize * 2];
	int compressResult = 0;
	int preprocessResult = 0;
	int uncompressResult = 0;
	int postprocessorResult = 0;

	//++++ TEST : full process of Rice compression and decompression on 398 TDS NORMAL CWF packets, all sized to 768 words

	for (unsigned int i = 0; i < riceBufferSize305664PacketNumber; i++) {
		//--- init the current packet index in the array
		unsigned int startIndex = i * riceBufferSize305664PacketSize;
		short* packetIndex = ricePacketBuffer305664 + startIndex;

		//--- call the full process of Rice compression and decompression
		preprocessResult = preprocess(reinterpret_cast<unsigned short*>(packetIndex), riceBufferSize305664PacketSize, preprocessedBuffer, ESTIMATE_1D_H);
		compressResult = compress(preprocessedBuffer, preprocessResult, compressedBuffer, riceBufferSize305664PacketSize * 2);
		// the number of elements of uncompressBuffer on which uncompress will run must be the same as the number of elements of dataBuffer
		uncompressResult = uncompress(compressedBuffer, compressResult, uncompressBuffer, riceBufferSize305664PacketSize);
		postprocessorResult = postprocessor(uncompressBuffer, uncompressResult, extractedBuffer, ESTIMATE_1D_H);

		// the compression works for this data set
		CU_ASSERT_TRUE(compressResult < riceBufferSize305664PacketSize);

		// the data before compression must be the same as the data after the decompression
		bool bufferDifferent = false;
		int bytePosition = 0;
		short* extractedBufferSigned = reinterpret_cast<short*>(extractedBuffer);
		for (int j = 0; j < riceBufferSize305664PacketSize; j++) {
			if (ricePacketBuffer305664[startIndex + j] != extractedBufferSigned[j]) {
				if (bufferDifferent == false) {
					bufferDifferent = true;
					bytePosition = j;
				}
			}
		}
		CU_ASSERT_EQUAL (bufferDifferent, false);
		CU_ASSERT_EQUAL (bytePosition, 0);
		if (bytePosition != 0) {
			//--- if differences are found between the initial buffer and the extracted buffer, the initial buffer is printed
			printf("\n error at byte position = %d \n", bytePosition);
			printf("Initial data:\n");
			for (int k = i *  riceBufferSize305664PacketSize; k < riceBufferSize305664PacketSize * (i+1); k++) {
				printf("%X\t", ricePacketBuffer305664[k]);
			}
			printf("\n");
		}
		CU_ASSERT_EQUAL(postprocessorResult, riceBufferSize305664PacketSize);
		CU_ASSERT_EQUAL(uncompressResult, riceBufferSize305664PacketSize);
		CU_ASSERT_EQUAL(postprocessorResult, uncompressResult);
	}
}

/**
 * Test the behavior of Rice algorithm with a data set of 8 snapshot of TDS NORMAL RSWF packets (72 packets).
 * The data set can be found at : https://jira-lesia.obspm.fr/browse/RCL-11.
 * See https://jira-lesia.obspm.fr/secure/attachment/54497/TDS-030-01_Sweeping_Frequency_HF_R3_Log09_without_comp_RSW_72_packets_131072_samples.xlsx
 */
void riceCompressTest_fullProcess131072() {
	// the buffers are double the size of the data in case the compressed packet is greater than the original one
	unsigned int preprocessedBuffer[riceBufferSize131072PacketSize1 * 2];
	unsigned char compressedBuffer[riceBufferSize131072PacketSize1 * 4 * 2];
	unsigned short extractedBuffer[riceBufferSize131072PacketSize1 * 2];
	unsigned int uncompressBuffer[riceBufferSize131072PacketSize1 * 2];
	int compressResult = 0;
	int preprocessResult = 0;
	int uncompressResult = 0;
	int postprocessorResult = 0;

	//++++ TEST : full process of Rice compression and decompression on 72 TDS SCIENCE NORMAL RSWF packets
	//++++ The data set is composed of 8 snapshots, each snapshot being composed of 9 packets (8 * 2032-word + 1 * 128-word sizes)

	unsigned int packetStartNumber = 1;
	unsigned int packetEndNumber = riceBufferSize131072PacketNumber + 1;

	for (unsigned int i = packetStartNumber; i < packetEndNumber; i++) {
		//--- determine in function of the packet index in the array the number of 2032 and 128-word sized packets brefore this packet
		//--- used to determine the index of the current packet in the array
		int numberOfPacketWithSize2 = (i / 9);
		int numberOfPacketWithSize1 = ((i / 9) * 8 + (i % 9));
		unsigned int packetSize = 0;
		if ((i % 9) == 0) {
			packetSize = riceBufferSize131072PacketSize2;
		}
		else {
			packetSize = riceBufferSize131072PacketSize1;
		}
		//--- determine for each packet the index in the array
		unsigned int startIndex = 0;
		if ((i != 0) && ((i % 9) == 0)) { // index of the last packet of each 9-packet TDS RSWF snapshot
			startIndex = numberOfPacketWithSize2 * 8 * riceBufferSize131072PacketSize1
					+ (numberOfPacketWithSize2 - 1) * riceBufferSize131072PacketSize2;
		}
		else { // index of the other packets of the 9-packet TDS RSWF snapshots
			startIndex = (i - numberOfPacketWithSize2 - 1) * riceBufferSize131072PacketSize1
					+ numberOfPacketWithSize2 * riceBufferSize131072PacketSize2;
		}

		//--- call the full process of Rice compression and decompression
		short* packetIndex = ricePacketBuffer131072 + startIndex;
		preprocessResult = preprocess(reinterpret_cast<unsigned short*>(packetIndex), packetSize, preprocessedBuffer, ESTIMATE_1D_H);
		compressResult = compress(preprocessedBuffer, preprocessResult, compressedBuffer, packetSize * 2);
		// the number of elements of uncompressBuffer on which uncompress will run must be the same as the number of elements of dataBuffer
		uncompressResult = uncompress(compressedBuffer, compressResult, uncompressBuffer, packetSize);
		postprocessorResult = postprocessor(uncompressBuffer, uncompressResult, extractedBuffer, ESTIMATE_1D_H);

		// the compression is not efficient on this data set, due the large variations in values on each 16-word data block
		CU_ASSERT_TRUE(compressResult > packetSize);
		// the data before compression must be the same as the data after the decompression
		bool bufferDifferent = false;
		int bytePosition = 0;
		short* extractedBufferSigned = reinterpret_cast<short*>(extractedBuffer);
		for (int j = 0; j < packetSize; j++) {
			if (ricePacketBuffer131072[startIndex+j] != extractedBufferSigned[j]) {
				if (bufferDifferent == false) {
					bufferDifferent = true;
					bytePosition = j;
				}
			}
		}
		CU_ASSERT_EQUAL (bufferDifferent, false);
		CU_ASSERT_EQUAL (bytePosition, 0);
		if (bytePosition != 0) {
			//--- if differences are found between the initial buffer and the extracted buffer, the initial buffer is printed
			printf("error at byte position = %d \n", bytePosition);
			printf("Initial data:\n");
			for (int k = startIndex; k < startIndex + packetSize; k++) {
				printf("%X\t", ricePacketBuffer131072[k]);
			}
			printf("\n");

		}
		CU_ASSERT_EQUAL(postprocessorResult, packetSize);
		CU_ASSERT_EQUAL(uncompressResult, packetSize);
		CU_ASSERT_EQUAL(postprocessorResult, uncompressResult);
	}
}

int executeUnitTests_riceCompressTest() {
	bool error = false;

	CU_pSuite pSuite = 0;

	/* initialize the CUnit test registry */
	if (CUE_SUCCESS != CU_initialize_registry())
		return CU_get_error();

	// add a suite to the registry
	pSuite = CU_add_suite("GsrRiceCompress", initSuite_riceCompressTest, cleanSuite_riceCompressTest);

	if (0 == pSuite) {
		CU_cleanup_registry();
		return CU_get_error();
	}

	// add the tests to the suite
	if ((CU_add_test(pSuite, " Test of GsrRiceCompress : full process 16 numbers ", riceCompressTest_fullProcess16) == 0)
			|| (CU_add_test(pSuite, " Test of GsrRiceCompress : full process 32 numbers ", riceCompressTest_fullProcess32) == 0)
			|| (CU_add_test(pSuite, " Test of GsrRiceCompress : full process image ", riceCompressTest_fullProcessImage) == 0)
			|| (CU_add_test(pSuite, " Test of GsrRiceCompress : full process 320 numbers ", riceCompressTest_fullProcess320) == 0)
			|| (CU_add_test(pSuite, " Test of GsrRiceCompress : full process 340 numbers, with padding ",
					riceCompressTest_fullProcess352Padding) == 0)
			|| (CU_add_test(pSuite, " Test of GsrRiceCompress : full process 768 samples ", riceCompressTest_fullProcess768) == 0)
			|| (CU_add_test(pSuite, " Test of GsrRiceCompress : full process 7680 samples ", riceCompressTest_fullProcess7680) == 0)
			|| (CU_add_test(pSuite, " Test of GsrRiceCompress : full process 305664 samples ", riceCompressTest_fullProcess305664) == 0)
			|| (CU_add_test(pSuite, " Test of GsrRiceCompress : full process 131072 samples ", riceCompressTest_fullProcess131072) == 0)) {
		error = true;
		printf("\n Essai 3\n\n");
	}

	if (error) {
		CU_cleanup_registry();
		return CU_get_error();
	}

	// Run all tests using the CUnit Basic interface
	CU_basic_set_mode (CU_BRM_VERBOSE);
	CU_basic_run_tests();
	CU_cleanup_registry();
	return CU_get_error();
}

