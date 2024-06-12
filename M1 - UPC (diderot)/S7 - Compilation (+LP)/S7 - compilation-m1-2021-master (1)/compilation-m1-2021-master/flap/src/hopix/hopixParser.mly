%{ (* -*- tuareg -*- *)

  open HopixAST
  open Position


%}

%token EOF


%start<HopixAST.t> program

%%

program: EOF
{
   []
}

%inline located(X): x=X {
  Position.with_poss $startpos $endpos x
}
