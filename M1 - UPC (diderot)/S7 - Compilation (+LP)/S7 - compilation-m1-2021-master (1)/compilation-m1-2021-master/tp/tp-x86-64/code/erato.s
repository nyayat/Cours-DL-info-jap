#  Notice that 2^28 is equal to $268435456
        .file   "erato.s"
        .text
        .section        .rodata.str1.1,"aMS",@progbits,1
.LC0:
        .string         "%d\n"
        .text
        .globl  main
        .type   main, @function

main:
.LFB22:
        .cfi_startproc
        subq    $8, %rsp
        .cfi_def_cfa_offset 16
#
# WRITE YOUR ANSWER HERE
#
        movl    $0, %edi
        call    exit@PLT
        .cfi_endproc
.LFE22:
        .size   main, .-main
        .ident  "GCC: (Debian 8.2.0-13) 8.2.0"
        .section        .note.GNU-stack,"",@progbits
