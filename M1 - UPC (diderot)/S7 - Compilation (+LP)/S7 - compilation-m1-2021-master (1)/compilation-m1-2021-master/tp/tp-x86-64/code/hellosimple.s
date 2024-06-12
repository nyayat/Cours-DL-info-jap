        .data
message: .string        "Hello World!"
         .text
        .globl  main
main:
        leaq     message(%rip), %rdi
        call    puts@PLT
        movq    $0, %rax
        ret
