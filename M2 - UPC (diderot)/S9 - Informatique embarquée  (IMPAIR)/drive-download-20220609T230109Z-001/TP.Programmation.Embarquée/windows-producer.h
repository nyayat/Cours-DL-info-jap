/*
 * windows-producer.h
 *
 *    release : 2.1
 *   published: 29 nov. 2018
 *      Author: LeeRoy MALAC-ALLAIN
 */

#ifndef WINDOWS_PRODUCER_H_
#define WINDOWS_PRODUCER_H_

#include <stdint.h>

/// Max number of windows produced for each call to produce_images()
#define MAX_WINDOWS_BY_STEP 1000
/// Number of mask of windows available
#define NUMBER_OF_MASK 10


typedef struct {
	float variation;
	float W;
} Etoile;

typedef struct Windows_producer {
	int g_windows_max_number;
	float* g_img;
	Etoile g_e[MAX_WINDOWS_BY_STEP];
	char g_irq_to_generate;
}Windows_producer;



/**
 * Initialize the windows producer.
 * Must be call before produce_images()
 * @param wp pointer on the structure with the internals variables of the component
 * @param img_buffer pointer on a memory zone when 36 * windows_max_number can be store
 * @param windows_max_number number of windows to produce. Can't be larger than MAX_WINDOWS_BY_STEP.
 * @return 0 if initialization is successful. Return -1 if value of windows_max_number is invalid.
 */
extern char init(Windows_producer* wp, float* img_buffer, unsigned int windows_max_number);


/**
 * Enable the trigger of an IRQ when produce_images() finish.
 * @param wp pointer on the structure with the internals variables of the component
 * @param irq IRQ to trigger
 */
extern void enable_irq(Windows_producer* wp, int irq);

/**
 * Disable the trigger of an irq when produce_images() finish.
 * @param wp pointer on the structure with the internals variables of the component
 */
extern void disable_irq(Windows_producer* wp );


/**
 * Produce new windows for the same star but with an evolved brightness.
 * Use the masks return by get_mask()
 * The windows are store in \a img_buffer set to init_data_producer().
 * The number of windows is \a windows_max_number set to init_data_producer().
 * Trigger the IRQ 10 when the \a img_buffer contains the new windows.
 * @param wp pointer on the structure with the internals variables of the component
 */
extern void produce_images(Windows_producer* wp );
//extern void produce_images(int irq);

/**
 * Get a pointer on the 36 float of a window's mask.
 * @param wp pointer on the structure with the internals variables of the component
 * @param i index in img_buffer of the requested windows
 */
extern float* get_mask(Windows_producer* wp, uint32_t i);

#endif /* WINDOWS_PRODUCER_H_ */
