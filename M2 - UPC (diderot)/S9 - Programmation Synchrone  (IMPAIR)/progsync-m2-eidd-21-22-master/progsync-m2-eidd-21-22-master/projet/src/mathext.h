#ifndef MATHEXT_H
#define MATHEXT_H

#include "stdbool.h"
#include "assert.h"
#include "pervasives.h"

#include "hept_ffi.h"

DECLARE_HEPT_FUN(Mathext, float, (int), float o);
DECLARE_HEPT_FUN(Mathext, int, (float), int o);
DECLARE_HEPT_FUN(Mathext, floor, (float), float o);

DECLARE_HEPT_FUN(Mathext, sin, (float), float o);
DECLARE_HEPT_FUN(Mathext, cos, (float), float o);
DECLARE_HEPT_FUN(Mathext, atan2, (float, float), float o);
DECLARE_HEPT_FUN(Mathext, hypot, (float, float), float o);
DECLARE_HEPT_FUN(Mathext, sqrt, (float), float o);

DECLARE_HEPT_FUN(Mathext, modulo, (int, int), int o);

#endif  /* MATHEXT_H */
