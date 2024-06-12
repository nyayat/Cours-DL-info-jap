#ifndef HEPT_FFI_H
#define HEPT_FFI_H

#define UNPAREN(...) __VA_ARGS__

#define DECLARE_HEPT_FUN(module, name, inputs, outputs)                 \
  typedef struct { outputs; } module ## __ ## name ## _out;             \
  void module ## __ ## name ##_step(UNPAREN inputs,                     \
                                    module ## __ ## name ## _out *)

#define DECLARE_HEPT_FUN_NULLARY(module, name, outputs)                 \
  typedef struct { outputs; } module ## __ ## name ## _out;             \
  void module ## __ ## name ##_step(module ## __ ## name ## _out *)

#define DEFINE_HEPT_FUN(module, name, inputs)                          \
  void module ## __ ## name ##_step(UNPAREN inputs,                    \
                                    module ## __ ## name ## _out *out)

#define DEFINE_HEPT_FUN_NULLARY(module, name, inputs)                   \
  void module ## __ ## name ##_step(module ## __ ## name ## _out *out)

#define DECLARE_HEPT_NODE(module, name, inputs, outputs, state)         \
  typedef struct { outputs; } module ## __ ## name ## _out;             \
  typedef struct { state; } module ## __ ## name ## _mem;               \
  void module ## __ ## name ##_step(UNPAREN inputs,                     \
                                    module ## __ ## name ## _out *,     \
                                    module ## __ ## name ## _mem *);    \
  void module ## __ ## name ##_reset(module ## __ ## name ## _mem *)

#define DECLARE_HEPT_NODE_NULLARY(module, name, outputs, state)         \
  typedef struct { outputs; } module ## __ ## name ## _out;             \
  typedef struct { state; } module ## __ ## name ## _mem;               \
  void module ## __ ## name ##_step(module ## __ ## name ## _out *,     \
                                    module ## __ ## name ## _mem *);    \
  void module ## __ ## name ##_reset(module ## __ ## name ## _mem *)

#define DEFINE_HEPT_NODE_RESET(module, name)                            \
  void module ## __ ## name ##_reset(module ## __ ## name ## _mem *mem)

#define DEFINE_HEPT_NODE_STEP(module, name, inputs)                     \
  void module ## __ ## name ##_step(UNPAREN inputs,                     \
                                    module ## __ ## name ## _out *out,  \
                                    module ## __ ## name ## _mem *mem)

#define DEFINE_HEPT_NODE_NULLARY_STEP(module, name, inputs)             \
  void module ## __ ## name ##_step(module ## __ ## name ## _out *out,  \
                                    module ## __ ## name ## _mem *mem)

/* FIXME remove when Heptagon's pervasives.h has been fixed. */
typedef char * string;

#endif  /* HEPT_FFI */
