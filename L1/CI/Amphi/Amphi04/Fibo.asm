;https://wchargin.github.io/lc3web/
.ORIG x0000
AND R0, R0, #0  ;R0 <- 0
AND R1, R1, #0  ;R1 <- 0
AND R2, R2, #0  ;R2 <- 0
ADD R0, R0, #12 ;R0 <-12
ADD R2, R2, #1  ;R2 <- 1
ADD R3, R1, R2  ;R3 <- R1 + R2
ADD R1, R2, #0  ;R1 <- R2
ADD R2, R3, #0  ;R2 <- R3
ADD R0, R0, #-1 ;R0--
BRp #-5         ;BRanchement conditionnel: si R0>0 alors on branche vers instruction -5
.END
