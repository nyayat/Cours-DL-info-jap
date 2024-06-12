        .text
        .globl  main
main:
        mov     $message, %rdi
        call    puts
        movq    $0, %rax
        ret
        .data

message:.string         "Hello World!"
