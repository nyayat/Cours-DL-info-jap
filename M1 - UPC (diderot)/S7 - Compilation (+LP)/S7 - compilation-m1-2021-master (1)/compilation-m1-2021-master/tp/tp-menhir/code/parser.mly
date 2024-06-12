%{ (* Emacs, open this with -*- tuareg -*- *)
open AST
%}

%token<int> INT
%token<string> ID
%token PLUS EOF

%start<AST.exp> phrase

%left PLUS

%%

phrase: e=exp EOF
{
  e
}

exp: x=INT
{
  LInt x
}
| x=ID
{
  Id x
}
| e1=exp PLUS e2=exp
{
  Add (e1, e2)
}
