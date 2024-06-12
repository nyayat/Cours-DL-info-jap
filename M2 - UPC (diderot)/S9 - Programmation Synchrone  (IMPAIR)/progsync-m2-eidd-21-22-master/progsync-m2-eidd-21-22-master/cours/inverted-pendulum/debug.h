#ifndef DEBUG_H
#define DEBUG_H

#include "stdbool.h"
#include "assert.h"
#include "pervasives.h"

#include "hept_ffi.h"

DECLARE_HEPT_FUN(Debug, dbg, (char *),);
DECLARE_HEPT_FUN(Debug, dbg_bool, (char *, bool),);
DECLARE_HEPT_FUN(Debug, dbg_int, (char *, int),);
DECLARE_HEPT_FUN(Debug, dbg_float, (char *, float),);

typedef struct { } Debug__world;

DECLARE_HEPT_FUN_NULLARY(Debug, d_init, Debug__world n);
DECLARE_HEPT_FUN(Debug, d_string, (Debug__world, char *), Debug__world n);
DECLARE_HEPT_FUN(Debug, d_bool, (Debug__world, bool), Debug__world n);
DECLARE_HEPT_FUN(Debug, d_int, (Debug__world, int), Debug__world n);
DECLARE_HEPT_FUN(Debug, d_float, (Debug__world, float), Debug__world n);

#endif  /* DEBUG_H */
