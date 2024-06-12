#ifndef TRY_H
#define TRY_H

#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define try(...) TRY(, ##__VA_ARGS__, -1)

#define TRY(_, EXPR, ERRVAL, ...)                                     \
  ({                                                                  \
    typeof(EXPR) _retval_ = (EXPR);                                   \
    if (_retval_ == ERRVAL) {                                         \
      fprintf(stderr,                                                 \
              "\033[1m%s:%d:\033[0m "                                 \
              "Call \033[1;36m%s\033[0m failed:\n"                    \
              "\033[1;31m%s\033[0m\n",                                \
              __FILE__, __LINE__, #EXPR, strerror(errno));            \
      _exit(EXIT_FAILURE);                                            \
    }                                                                 \
    _retval_;                                                         \
  })                                                                  \

#endif /* TRY_H */
