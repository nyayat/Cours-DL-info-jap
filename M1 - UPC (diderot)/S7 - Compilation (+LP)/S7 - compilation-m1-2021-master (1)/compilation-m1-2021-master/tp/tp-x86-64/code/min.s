        .file   "min.c"
        .text
        .globl  min
        .type   min, @function
min:
.LFB22:
        .cfi_startproc
        movl    (%rdi), %eax
        cmpl    $1, %esi
        jbe     .L1
        leaq    4(%rdi), %rdx
        leal    -2(%rsi), %ecx
        leaq    8(%rdi,%rcx,4), %rsi
.L3:
        movl    (%rdx), %ecx
        cmpl    %ecx, %eax
        cmovl   %ecx, %eax
        addq    $4, %rdx
        cmpq    %rsi, %rdx
        jne     .L3
.L1:
        ret
        .cfi_endproc
.LFE22:
        .size   min, .-min
        .section        .rodata.str1.1,"aMS",@progbits,1
.LC0:
        .string         "%d\n"
        .text
        .globl  main
        .type   main, @function
main:
.LFB23:
        .cfi_startproc
        subq    $56, %rsp
        .cfi_def_cfa_offset 64
        movl    $1, (%rsp)
        movl    $5, 4(%rsp)
        movl    $5, 8(%rsp)
        movl    $6, 12(%rsp)
        movl    $3, 16(%rsp)
        movl    $-1, 20(%rsp)
        movl    $4, 24(%rsp)
        movl    $37, 28(%rsp)
        movl    $-73, 32(%rsp)
        movl    $0, 36(%rsp)
        movq    %rsp, %rdi
        movl    $10, %esi
        call    min
        movl    %eax, %esi
        leaq    .LC0(%rip), %rdi
        movl    $0, %eax
        call    printf@PLT
        movl    $0, %edi
        call    exit@PLT
        .cfi_endproc
.LFE23:
        .size   main, .-main
        .ident  "GCC: (Debian 8.2.0-13) 8.2.0"
        .section        .note.GNU-stack,"",@progbits
