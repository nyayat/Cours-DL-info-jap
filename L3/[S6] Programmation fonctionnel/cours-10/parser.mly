%{ (* -*- tuareg -*- *)

open AST

%}

(** Keywords. *)
%token LET REC IN FUN MATCH WITH END

(** Punctuation. *)
%token EQUAL RARROW COMMA SEMICOLON DOT UNDERSCORE PIPE

(** Structural symbols. *)
%token LBRACE RBRACE LPAREN RPAREN EOF

(** Identifiers *)
%token<string> ID UID

(** Primitive operations *)
%token<AST.operation> OP

(** Literals. *)
%token<int> INT

%start<AST.program> program

%nonassoc IN
%right RARROW
%left ID INT
%left LPAREN
%nonassoc OP
%%

program: ds=toplevel_definition* EOF
{
  ds
}
| error
{
  failwith "Syntax error."
}

toplevel_definition:
vd=value_definition
{
  ToplevelValue vd
}

value_definition:
LET x=identifier
EQUAL t=term
{
  SimpleValue (x, t)
}
| LET REC f=identifier xs=identifier+ EQUAL t=term
{
  RecFunction (f, xs, t)
}

term:
vd=value_definition IN t=term
{
  Let (vd, t)
}
| t=term u=simple_term
{
  App (t, u)
}
| k=constructor LPAREN ts=separated_list(COMMA, term) RPAREN
{
  KApp (k, ts)
}
| FUN x=identifier RARROW t=term
{
  Lam (x, t)
}
| LBRACE
  fs=separated_nonempty_list(SEMICOLON, field_definition)
  RBRACE
{
  Record fs
}
| MATCH x=term WITH PIPE? bs=separated_list(PIPE, branch) END
{
  Match (x, bs)
}
| s=simple_term
{
  s
}

simple_term:
x=identifier
{
  Var x
}
| x=INT
{
  Lit (LInt x)
}
| o=OP
{
  Op o
}
| s=simple_term DOT l=label
{
  Proj (s, l)
}
| LPAREN t=term RPAREN
{
  t
}

field_definition: l=label EQUAL t=term
{
  (l, t)
}

branch: p=pattern RARROW t=term
{
  (p, t)
}

pattern:
x=identifier
{
  PVar x
}
| n=INT
{
  PLit (LInt n)
}
| k=constructor LPAREN ps=separated_list(COMMA, pattern) RPAREN
{
  PKApp (k, ps)
}
| UNDERSCORE
{
  PWildcard
}

identifier: x=ID
{
  Id x
}

constructor: x=UID
{
  CId x
}


label: x=ID
{
  LId x
}
