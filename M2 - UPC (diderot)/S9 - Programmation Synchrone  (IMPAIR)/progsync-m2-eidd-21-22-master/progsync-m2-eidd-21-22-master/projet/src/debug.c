#include "debug.h"

#include "cutils.h"

void Debug__dbg_step(char *msg, Debug__dbg_out *o) {
  log_info("%s\n", msg);
}

void Debug__dbg_bool_step(char *msg, bool x, Debug__dbg_bool_out *o) {
  log_info("%s %d\n", msg, x);
}

void Debug__dbg_int_step(char *msg, int x, Debug__dbg_int_out *o) {
  log_info("%s %d\n", msg, x);
}

void Debug__dbg_float_step(char *msg, float x, Debug__dbg_float_out *o) {
  log_info("%s %f\n", msg, x);
}

void Debug__d_init_step(Debug__d_init_out *o) {
  /* Empty by design */
}

void Debug__d_string_step(Debug__world _w, char *s, Debug__d_string_out *o) {
  log_info("%s", s);
}

void Debug__d_bool_step(Debug__world _w, bool b, Debug__d_bool_out *o) {
  log_info("%d", b);
}

void Debug__d_int_step(Debug__world _w, int i, Debug__d_int_out *o) {
  log_info("%d", i);
}

void Debug__d_float_step(Debug__world _w, float f, Debug__d_float_out *o) {
  log_info("%f", f);
}
