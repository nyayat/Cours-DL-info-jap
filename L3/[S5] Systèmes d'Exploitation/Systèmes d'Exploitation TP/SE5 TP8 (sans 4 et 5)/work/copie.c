#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <stdio.h>
#include <libgen.h>
#include "mppsh-exec.h"

/* copie ou concatène argv[1] dans argv[2] par redirections des flots
 * standard et recouvrement par cat */

int main(int argc, char **argv) {
  int flag = 0;

  char *cmd[2] = {"cat",  NULL};

  if (argc != 3) {
    dprintf(STDOUT_FILENO, "usage: %s ref1 ref2\n", argv[0]);
    exit(EXIT_FAILURE);
  }

  /* copie brute sauf si l'exécutable s'appelle concatene */
  if(strcmp(basename(argv[0]), "concatene") == 0) flag = O_APPEND;

  execute(cmd, argv[1], argv[2], flag);

  return EXIT_FAILURE;
}
