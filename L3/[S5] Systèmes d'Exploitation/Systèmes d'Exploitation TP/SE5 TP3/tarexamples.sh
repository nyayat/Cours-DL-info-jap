#!/bin/bash


clean_tar_header() {
  FILE=$1
  OFFSET=$2
  dd if=/dev/zero "of=$FILE" conv=notrunc bs=1 seek=$(( $OFFSET + 108 )) count=8 2>/dev/null # uid
  dd if=/dev/zero "of=$FILE" conv=notrunc bs=1 seek=$(( $OFFSET + 116 )) count=8 2>/dev/null # gid
  dd if=/dev/zero "of=$FILE" conv=notrunc bs=1 seek=$(( $OFFSET + 265 )) count=32 2>/dev/null # uname
  dd if=/dev/zero "of=$FILE" conv=notrunc bs=1 seek=$(( $OFFSET + 297 )) count=32 2>/dev/null # gname
  echo "        " | dd "of=$FILE" conv=notrunc bs=1 seek=$(( $OFFSET + 148 )) count=8 2>/dev/null # checksum
}

fix_tar_header() {
  FILE=$1
  OFFSET=$2
  clean_tar_header "$FILE" "$OFFSET"
  printf "%06o " $(id -u $(whoami)) | dd "of=$FILE" conv=notrunc bs=1 seek=$(( $OFFSET + 108 )) count=7 2>/dev/null # uid
  printf "%06o " $(id -g $(whoami)) | dd "of=$FILE" conv=notrunc bs=1 seek=$(( $OFFSET + 116 )) count=7 2>/dev/null # gid
  echo -n $(whoami) | dd "of=$FILE" conv=notrunc bs=1 seek=$(( $OFFSET + 265 )) count=31 2>/dev/null # uname
  echo -n $(id -g -n $(whoami)) | dd "of=$FILE" conv=notrunc bs=1 seek=$(( $OFFSET + 297 )) count=31 2>/dev/null # gname
  printf "%06o\0 " $(od -t u1 -An -v -j $OFFSET -N 512 "$FILE" | awk '{for (i=1;i<=NF;i++) s+=$i} END {print s}') | dd "of=$FILE" conv=notrunc bs=1 seek=$(( $OFFSET + 148 )) count=8 2>/dev/null # checksum
}

TMP=/tmp/$(whoami)
if mkdir -p $TMP; then
  echo -e "\nFaites vos tests dans $(tput bold)$TMP$(tput sgr0)\n"
else
  echo -e "\nImpossible de créer le répertoire $TMP: abandon\n"
  exit 1
fi

cp templates/titi.tar.broken $TMP/titi.tar
cp templates/tarutils.tar.broken $TMP/tarutils.tar
fix_tar_header $TMP/titi.tar 0
fix_tar_header $TMP/titi.tar 1024
fix_tar_header $TMP/tarutils.tar 0
fix_tar_header $TMP/tarutils.tar 1536
head -c 2048 $TMP/titi.tar >| $TMP/sans_zeros.tar
head -c 1545 $TMP/titi.tar >| $TMP/tronque.tar

