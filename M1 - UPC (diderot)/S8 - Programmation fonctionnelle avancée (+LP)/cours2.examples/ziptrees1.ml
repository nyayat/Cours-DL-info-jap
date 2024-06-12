type 'a narbre =
| Feuille of 'a
| Noeud of 'a narbre list;;

type 'a listzipper = 'a list * 'a list

type 'a block = 'a narbre listzipper

type 'a pile = 'a block list

type 'a narbrezipper = 'a pile * 'a narbre;;
