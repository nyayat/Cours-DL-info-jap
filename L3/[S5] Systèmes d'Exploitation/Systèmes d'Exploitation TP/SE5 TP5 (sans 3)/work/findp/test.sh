#!/bin/bash

echo_red() {
  if [ -t 1 ] && command -v tput >/dev/null 2>&1 
  then
    echo -n "$(tput setaf 1)$(tput bold)" 2>/dev/null
    echo $@
    echo -n "$(tput sgr0)" 2>/dev/null
  else
    echo $@
  fi
}

echo -n "Running ./findp . key... "

if ./findp . key >/dev/null 2>&1; then
    echo "OK"
else
    echo_red -e "Failed\n"
    echo "File key not found"
fi

echo -n "Running ./findp . NONEXISTENT... "

if ./findp . NONEXISTENT >/dev/null 2>&1; then
    echo_red -e "Failed\n"
    echo "Should have exited with EXIT_FAILURE"
else
    echo "OK"
fi

if command -v valgrind >/dev/null 2>&1; then
    echo -n "Running \`valgrind --leak-check=full ./findp ~/ key\` ..."
    valgrind --leak-check=full --error-exitcode=33 \
	     ./findp ~/ key >/dev/null 2>&1
    RES=$?
    if [ "$RES" -eq 33 ]; then
	echo_red "Failed"
    else
	echo "OK"
    fi
else
    echo \
    "Could not  run memory leaks check: please install valgrind on this computer if you can, otherwise ignore this line."
fi
