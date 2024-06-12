#ifndef MYMATH_H
#define MYMATH_H

/* Avoid Heptagon's math.h. */
#ifndef __APPLE__
#include </usr/include/math.h>
#endif

#ifndef M_PI
#define M_PI 3.14159265358979323846
#endif

#endif  /* MYMATH_H */
