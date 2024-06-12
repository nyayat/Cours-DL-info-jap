# The Flap Compiler

## Prerequisites

Flap requires **OCaml 4.10+**, as well as the tools and libraries listed
below. They should be installed prior to attempting to build Flap.

- The **dune** build system.
- The **utop** enhanced interactive toplevel.
- The **pprint** library.
- The **menhir** parser generator and library.
- The **sexplib** library.
- The **ppx_sexp_conv** syntax extension.

The easiest way to install them is via OPAM.

``
opam install dune utop pprint menhir sexplib ppx_sexp_conv
``

In addition, running the test requires the [cram](https://bitheap.org/cram/)
tool. It is probably provided by your Linux distribution.

## Build instructions

To compile the compiler, run `dune build` from this directory.

To run the compiler, run `dune exec ./src/flap.exe -- OPTIONS file` from this
directory. Alternatively, `flap.exe` can be found in `_build/default/src/`.

The test suite can be found in the `tests` directory. See the README there.
