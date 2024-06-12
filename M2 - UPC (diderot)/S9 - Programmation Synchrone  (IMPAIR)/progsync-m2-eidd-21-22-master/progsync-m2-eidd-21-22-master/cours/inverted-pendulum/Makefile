HEPTC=heptc
PYGMENTS=python -m pygments -x -l ../../../notes/heptagon.py:HeptagonLexer

HEPT_NAME=pendulum

HEPT_SOURCES=$(HEPT_NAME).ept
HEPT_GENERATED=\
	$(HEPT_NAME)_types.h \
	$(HEPT_NAME).h \
	$(HEPT_NAME)_types.c \
	$(HEPT_NAME).c
C_SOURCES=mathext.c

SWIG_SOURCE=$(HEPT_NAME).i
SWIG_GENERATED=$(HEPT_NAME).py $(HEPT_NAME)_wrap.c

PY_SUFFIX=$(shell python -c \
	"import sysconfig as s; print(s.get_config_var('EXT_SUFFIX'))")
TARGET?=_$(HEPT_NAME)$(PY_SUFFIX)

.PHONY: all clean run runplot repl

all: $(TARGET)

clean:
	rm -f *.epci *.log *.mls *.obc *.epci *.epo
	rm -f $(SWIG_GENERATED) $(HEPT_GENERATED)
	rm -rf $(HEPT_NAME)_c build __pycache__ *.so

repl: all
	python -i -c 'import $(HEPT_NAME)'

run: all
	./main.py

runplot: all
	./main.py --plot

%.html: %.ept
	$(PYGMENTS) -O full -o $@ $^

$(TARGET): $(SWIG_GENERATED) $(HEPT_GENERATED) $(C_SOURCES)
	python setup.py build_ext --inplace
	@echo $@

$(SWIG_GENERATED): $(SWIG_SOURCE) $(HEPT_GENERATED) $(C_SOURCES)
	swig -python $<

%_types.h %_types.c %.c %.h %.epci : %.ept
	$(HEPTC) -c -target c $<
	cp $(foreach ext,c h,$(basename $<)_c/*.$(ext)) .

%.epci: %.epi
	$(HEPTC) $<

$(HEPT_SOURCES): mathext.epci debug.epci
