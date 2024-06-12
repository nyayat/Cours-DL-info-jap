%module pendulum

%{
#define SWIG_FILE_WITH_INIT
#include "pendulum.h"
%}

// Important: makes SWIG aware of our dumb "string" type.
%include "mathext_types.h"

%rename("%(strip:[Pendulum__])s") "";
%include "pendulum_types.h"
%include "pendulum.h"

%pythoncode "node_wrapping.py"
