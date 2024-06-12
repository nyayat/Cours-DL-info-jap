#include <stdio.h>
#include <string.h>
#include "tarutils.h"

/* Compute and write the checksum of a header, by adding all
   (unsigned) bytes in it (while hd->chksum is initially all ' ').
   Then hd->chksum is set to contain the octal encoding of this
   sum (on 6 bytes), followed by '\0' and ' '.
*/

void set_checksum(struct posix_header *hd) {
  memset(hd->chksum,' ',8);
  unsigned int sum = 0;
  unsigned char *p = (unsigned char *)hd;
  for (int i=0; i < BLOCKSIZE; i++) { 
    sum += p[i]; 
  }
  sprintf(hd->chksum,"%06o",sum);
}

/* Check that the checksum of a header is correct */

int check_checksum(struct posix_header *hd) {
  unsigned int checksum;
  sscanf(hd->chksum,"%o ", &checksum);
  unsigned int sum = 0;
  unsigned char *p = (unsigned char *)hd;
  for (int i=0; i < BLOCKSIZE; i++) { 
    sum += p[i]; 
  }
  for (int i=0;i<8;i++) { 
    sum += ' ';
    sum -= ((unsigned char *)hd->chksum)[i]; 
  }
  return (checksum == sum);
}
