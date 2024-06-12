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


echo -n "Running ./untar test/test0.tar... "
./untar test/test0.tar
if diff -qr test0 test/test0 > output 2>&1; then
    echo "OK"
else
    echo_red -e "Failed\n"
    echo "Differences:"
    cat output
fi
rm output

if command -v valgrind >/dev/null 2>&1; then
    echo -n "Running \`valgrind --leak-check=full ./untar test/test0.tar\` ..."
    valgrind --leak-check=full --error-exitcode=33 \
	     ./untar test/test0.tar >/dev/null 2>&1
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
