#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "mymath.h"
#include "mathext.h"

void Mathext__float_step(int x, Mathext__float_out *o) {
  o->o = (float)x;
}

void Mathext__int_step(float x, Mathext__int_out *o) {
  o->o = (int)x;
}

void Mathext__floor_step(float x, Mathext__floor_out *o) {
  o->o = floorf(x);
}

void Mathext__sin_step(float x, Mathext__sin_out *o) {
  o->o = sinf(x);
}

void Mathext__cos_step(float x, Mathext__cos_out *o) {
  o->o = cosf(x);
}

void Mathext__atan2_step(float y, float x, Mathext__atan2_out *o) {
  o->o = atan2f(y, x);
}

void Mathext__hypot_step(float x, float y, Mathext__hypot_out *o) {
  o->o = hypotf(x, y);
}

void Mathext__sqrt_step(float x2, Mathext__sqrt_out *o) {
  o->o = sqrtf(x2);
}

void Mathext__modulo_step(int x, int y, Mathext__modulo_out *o) {
  o->o = x % y;
}
