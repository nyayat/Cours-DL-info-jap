        .file   "hello.c"
        .text
        .section        .rodata.str1.1,"aMS",@progbits,1
.LC0:
        .string         "Hello world"
        .section        .text.startup,"ax",@progbits
        .p2align 4,,15
        .globl  main
        .type   main, @function
main:
        leaq    .LC0(%rip), %rdi
        subq    $8, %rsp
        call    puts@PLT
        xorl    %edi, %edi
        call    exit@PLT
        .size   main, .-main
        .ident  "GCC: (Debian 8.2.0-13) 8.2.0"
        .section        .note.GNU-stack,"",@progbits
