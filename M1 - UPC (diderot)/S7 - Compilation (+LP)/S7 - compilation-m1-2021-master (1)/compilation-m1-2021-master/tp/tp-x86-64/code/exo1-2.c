#include <stdio.h>
#include <stdlib.h>

int fact (int n) {
  if (n == 0)
    return 1;
  else
    return n * fact (n - 1);
}

int main (int argc, char** argv) {
  int input = atoi (argv[1]);
  printf ("The factorial of %d is %d.\n", input, fact (input));
  exit (EXIT_SUCCESS);
}
