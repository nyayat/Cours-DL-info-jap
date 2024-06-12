        .file   "segfault.c"
        .text
        .section        .rodata.str1.1,"aMS",@progbits,1
.LC0:
        .string         "rtbbdrr"
        .text
        .globl  main
        .type   main, @function
main:
.LFB22:
        .cfi_startproc
        subq    $8, %rsp
        .cfi_def_cfa_offset 16
        movl    $9, %edi
        call    malloc@PLT
        movl    $0, %esi
        movl    $114, %edx
        movl    $0, %ecx
        leaq    .LC0(%rip), %rdi
.L2:
        addl    $1, %edx
        movb    %dl, (%rax,%rcx)
        addl    $1, %esi
        movl    %esi, %ecx
        movzbl  (%rdi,%rcx), %edx
        testb   %dl, %dl
        jne     .L2
        movl    $0, %edi
        call    puts@PLT
        movl    $0, %edi
        call    exit@PLT
        .cfi_endproc
.LFE22:
        .size   main, .-main
        .ident  "GCC: (Debian 8.2.0-13) 8.2.0"
        .section        .note.GNU-stack,"",@progbits
