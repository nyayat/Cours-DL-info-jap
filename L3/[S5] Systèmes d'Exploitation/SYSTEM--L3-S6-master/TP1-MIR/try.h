#ifndef TRY_H
#define TRY_H

#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define try(...) TRY(, ##__VA_ARGS__, -1)

#define TRY(_, EXPR, ERRVAL, ...)
  ({
    typeof(EXPR) _retval_ = (EXPR);
    if (_retval_ == ERRVAL) {
      fprintf(stderr,
              "%s:%d: Call %s failed:\n%s\n",
              __FILE__, __LINE__, #EXPR, strerror(errno));
      _exit(EXIT_FAILURE);
    }
    _retval_;
  })

#endif /* TRYH */