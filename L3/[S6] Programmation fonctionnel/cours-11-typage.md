Le typage d'OCaml
=================

Il est temps de revenir plus en détail sur le typage d'OCaml, qui vous a déjà été présenté de façon informelle dans les premiers cours. En particulier:

- quels sont les types disponibles en OCaml et comment en définir de nouveaux ?
- comment déterminer le type d'une expression OCaml (algorithme d'*inférence*) ?

Dès maintenant, exprimons les points cruciaux de cet algorithme d'inférence à venir :

- Si cet algorithme échoue, l'expression OCaml en question est *mal typé*, un message d'erreur est indiqué à l'utilisateur et l'expression ne sera même pas évalué.
- Si cet algorithme réussit, l'expression est *bien typé*, et une propriété fondamentale du système est que l'évaluation de cette expression n'a alors que trois issues possibles: calcul infini, levée d'une exception, ou bien valeur résultat *dans le même type qu'au départ*. 

En particulier ce système de type permet d'exclure tout un ensemble de plantages à l'exécution:

- Si une expression est typée comme étant une fonction, elle le sera bien lors de l'évaluation, et recevra bien des arguments du bon type.
- Chaque structure en mémoire sera bien accédée de façon légale (jamais de `Segmentation Fault` en OCaml, ni de `NullPointerException`).

## Sources documentaires

- Chapitre 18 du livre [Le langage Caml](http://caml.inria.fr/pub/distrib/books/llc.pdf) P. Weis, X. Leroy, 1993-1999. Oui ce livre concerne Caml Light, le précurseur d'OCaml, donc les exemples de code sont à adapter un peu. Mais l'essentiel du propos y reste d'actualité.

- Un ancien [cours de Master 2](http://pauillac.inria.fr/~xleroy/dea/typage/) par Xavier Leroy, et en particulier les chapitres 1,3,4.

## Les types

Dans les expressions de types OCaml, on peut trouver :

- Des types primitifs : `int`, `bool`, `float`, `char`, `string`, `unit` et quelques autres.
- Des constantes de type `nom`, avec peut-être un type en paramètre devant : `t1 nom` ou même plusieurs `(t1,...,tn) nom`.
  Par exemple `int list` ou `'a option` ou `(int->int,string) result`. Ces constantes de types correspondent à des définitions de types quelque-part auparavant. 
- Des variables de type : `'a`, `'b`, ... (permettant d'exprimer du *polymorphisme*)
- Une flèche entre deux types : `t1 -> t2` (désignant le type des fonctions à un argument de type `t1` et de résultat de type `t2`)
- Une étoile de deux types : `t1 * t2` (désignant les paires) et plus généralement `t1 * ... * tn` (pour les n-uplets)

On rappelle que `t1 -> t2 -> t3` est la même chose que `t1 -> (t2 -> t3)` : une fonction à deux arguments peut se voir comme une fonction à un argument répondant une fonction à un autre argument. Idem pour les fonctions à trois arguments et plus. Mais `(t1->t2)->t3` est tout autre chose (une fonction prenant une fonction en argument). 

## Les définitions de type

Le nom d'une constante de type correspond à une définition faite soit dans une bibliothèque d'OCaml, soit auparavant par l'utilisateur. On utilise parfois un nom *qualifié*, p.ex. `String.t` est la constante de type `t` définie dans le module `String` (et c'est un synonyme de `string`).

Il y a plusieurs variantes de définitions de type, qui commencent toutes par le mot-clé `type`. Notez que plusieurs définitions de types peuvent former un même *bloc mutuel* en utilisant le mot-clé `and` après un premier `type`, chaque définition dans ce bloc peut alors mentionner un autre des types défini dans ce bloc. Voir par exemple [slide/cours-10/AST.ml](slide/cours-10/AST.ml).

#### Primo : les abréviations de type

Une abréviation de type donne seulement un nom à une expression de type (qui existait donc déjà). Par exemple:

```ocaml
type truc = int (* crée un synonyme de int *)
type 'a truc_avec_parametre = ('a * 'a) list (* des listes de paires de quelque-chose *)
type 's rewrite_rules = 's -> 's word
```

Notez qu'après le `=` on n'a pas de *constructeurs* (nom en majuscule) comme pour la définition d'un type algébrique un peu plus bas.

Après la définition d'une abréviation de type, le nouveau nom et l'expression après le `=` sont librement interchangeables. Après `type truc = int`, les quatre types `truc->int` et `int->truc` et `truc->truc` et `int->int` sont quatre écritures possible du même type. OCaml affichera lui la version avec le moins d'abréviation possible (ici `int->int`).

Si l'abréviation a des paramètres, on les fait suivre quand on transforme le nom du type vers son contenu ou vice-versa. Par exemple écrire `int rewrite_rules` c'est exactement comme écrire `int -> int word`, ce qui désigne donc ici les fonctions des entiers vers les mots sur les entiers (`word` étant défini dans le projet).

#### Secondo : les définitions de types algébriques 

Parfois appelé aussi types énumérés ou types sommes. 

Cela commence comme une abréviation, mais après le `=` on liste les constructeurs possibles de ce nouveau type, avec les types des éventuels arguments de ces constructeurs après le mot-clé `of`. Je ne détaillerais pas plus, on en a déjà vu à de multiples reprises. Par exemple:

```ocaml
type answer = Yes | No | Maybe

type 'a option = Some of 'a | None

(* des arbres binaires avec des données aux noeuds *et* aux feuilles (pour changer) *)
type ('a,'b) tree =
| Node of 'a * ('a,'b) tree * ('a,'b) tree (* 3 arguments au constructeur Node *)
| Leaf of 'b 
```

Il est fréquent que ces types algébriques soient récursifs (mais ce n'est pas obligatoire, par exemple `option` ne l'est pas). 

Le nom des constructeurs commencent obligatoirement par une majuscule. Seule entorse à cette règle, les listes sont des types algébriques dont les deux constructeurs `[]` et `::` utilisent une syntaxe particulière au lieu d'un nom en majuscule.

#### Tertio : les définitions de types enregistrement

Déjà vu auparavant également.

```ocaml
type monrecord = { champ1 : type1; champ2 : type2 } (* et on peut aussi ajouter des paramètres de type *)
```

## Variables de types, instantiation, généralisation

La notation OCaml pour les variables de type : `'a`, `'b` ... et plus généralement une apostrophe suivi d'un nom.
Dans la littérature, certains auteurs les écrivent plutôt avec les premières lettres grecques `α`, `β`, ...

Revenons sur le rôle de ces variables de types. Une même expression OCaml peut parfois accepter plusieurs types. C'est le cas en particulier en situation de *polymorphisme*, quand certains morceaux d'une expression sont sous-contraints niveau typage. Par exemple `fun x -> x` peut être utilisé en tant que `int->int` ou bien en tant que `bool->bool` ou même comme `(int->int)->(int->int)`. Mais tous ces différents types possibles ont la même forme ici, une flèche avec la même chose à gauche et à droite. Utiliser une variable de type permet alors de résumer tous ces types en un seul : `'a -> 'a`. On parle alors de *type principal*, c'est-à-dire de type le plus général possible. Tous les autres types possibles de cet expression sont alors des cas particulier de ce type principal, obtenus par *instantiation* (a.k.a. *spécialisation*), c'est-à-dire en remplaçant certaines variables par d'autres types. Notons aussi que le nom de la variable de type importe peu, on aurait pu écrire par exemple `'foo -> 'foo`. 

Techniquement, cette instantiation correspond à une *substitution*, c'est-à-dire une fonction des variables de types vers les types. Si une variable `α` n'est pas concernée par une instantiation, on considère juste que `φ(α)=α`. Et une telle substitution `φ` s'étend à tout un type, en faisant agir `φ` sur toutes les variables de ce type. La substitution vide est la fonction identité `id`. Et on peut composer deux substitutions, je vous laisse imaginer comment : `φ∘ψ`.

L'instantiation correspond aussi à une notion d'ordre (partiel) sur les types, un type plus général étant vu comme "plus grand" qu'un type plus spécialisé. Le plus grand type possible est alors une simple variable `α` et à l'autre extrême les plus petits types possibles sont tous les types concrets n'ayant plus du tout de variables. Ainsi par exemple `(int->bool) < (β->γ) < α`. On parle parfois de sous-typage pour cette relation d'ordre, mais attention cela n'a qu'un lien très lointain avec le sous-typage des langages objet.

Cette opération d'instantiation est évidemment à faire de manière contrôlée. Quand on est encore en train de typer une expression OCaml, il ne faudrait pas changer sans raison une variable de type par n'importe quoi d'autre. Par contre, quant on a fini de typer une définition OCaml (un `let x = ...`, global ou local), les variables de types qui sont encore présents dans son type sont normalement considérés comme *universelles* : on pourra librement instantier ces variables de types par n'importe quoi quand ce nom `x` sera réutilisée ensuite. Cette étape du typage qui marquent les variables comme étant universelles est appelée la *généralisation*. Et et fait, cette généralisation n'est pas complètement systématique, il y a des variables qu'il ne faudra *pas* généraliser sous peine de soucis quand on considérera les opérations impératives (voir tout en bas de ce document).

Ici, on va noter `∀α1..αn, ...` devant un type pour indiquer quelles variables de ce type ont été généralisés, et sont donc universelles et librement substituables ensuite. Un type avec cette information `∀` est appelé un schéma de type. Dans les grandes lignes, la généralisation est l'étape faisant passer d'un type à un schéma de type, et l'instantiation va dans l'autre sens.

Enfin, on suppose ici qu'on peut toujours générer de "nouvelles" variables de types (on dit également "fraîches"), c'est-à-dire jamais utilisés auparavant lors du problème de typage qui nous occupe.

## L'unification

Une opération cruciale pour typer de l'OCaml est l'*unification* : est-ce que deux types sont suffisamment compatibles pour être rendu égaux, et que doit-on changer dans les variables de types de l'un et l'autre pour y arriver. Lors de l'inférence de type, un échec d'une étape d'unification signifie en pratique une erreur de type quelque part dans l'expression que l'on cherche à typer.

On utilise ici l'algorithme d'unification `mgu` (pour "most general unification") dû à Robinson.

**Entrée** : un ensemble `C` d'équations entre types, que l'on cherche à satisfaire

**Sortie** : soit un échec signifiant que `C` n'a pas de solution, soit une substitution `φ` qu'on appelle un unificateur principal de C. En effet `φ(C)` n'est plus formé que d'équations valides, et de plus toute autre substitution `ψ` ayant cette propriété est "plus particulière" que `φ`, au sens où il existe une substitution θ telle que ψ = θ ◦ φ.

**Calcul** :

- Si `C` est un ensemble vide d'équations : `mgu(∅) = id`
- Si `C` contient une équation triviale, on l'enlève et on recommence : `mgu({α = α} ∪ C) = mgu(C)`
- Si `C` contient une équation entre une variable de type `α` et un autre type `t`, il faut impérativement que `α` n'apparaisse pas dans `t` (pas de types cycliques, sinon `mgu(C)` est un échec). On considère ensuite la substitution `φ` telle que `φ(α)=t`, on l'applique partout, et on recommence : 
`mgu({α = t} ∪ C) = mgu(φ(C)) ◦ φ`.
- Si `C` contient une équation entre deux flèches de type, on la remplace par les deux sous-équations correspondantes : `mgu({t1 -> t2 = t1' -> t2'} ∪ C) = mgu({t1 = t1'; t2 = t2'} ∪ C)`
- Si `C` contient une équation entre deux types "étoile", on la remplace par les deux sous-équations correspondantes : `mgu({t1 * t2 = t1' * t2'} ∪ C) = mgu({t1 = t1'; t2 = t2'} ∪ C)`. Idem pour des équations entre types n-uplets.
- Si `C` contient une équation mentionnant une abréviation de type, on la remplace par le contenu de cette abréviation et on recommence.
- Si `C` contient une équation entre types algébriques `(t1,...tn) nom = (t1',...tn') nom`, avec le même nom des deux côtés, on continu avec les sous-équations correspondant aux paramètres `t1=t1'` ... `tn=tn'` s'il existent. Idem pour les types enregistrement.

Dans tous les autres cas, `mgu(C)` échoue et C n’a pas de solutions.


## Quelques notions et notations de plus

On utilise une notion d'environnement de typage `E`, c'est-à-dire une table des variables et autres définitions, avec leurs types respectifs.

Les variables libres d'un type (resp. d'un environnement), noté `VL(τ)` (resp. `VL(E)`) sont toutes les variables de types qui apparaissent dans un type `τ` (resp. un environnement E) de façon non-universelles.

On utilisera la notion suivante d’instance `Inst(σ)` d’un schéma de types, qui est le remplacement de toutes les variables universelles de types
par des nouvelles variables : `Inst(∀α1 ... αn. τ) = τ[α1 ← β1, ..., αn ← βn]` où `β1 ... βn` sont des variables "nouvelles".

On utilisera aussi la généralisation d'un type en schéma de type : `Gen(τ,E) = ∀α1 ... αn. τ` où `{α1 ... αn} = VL(τ) \ VL(E)` où `E` est un environnement de typage, Autrement dit `Gen(τ,E)` est `τ` dans lequel on a généralisé toutes les variables qui ne sont pas libres dans l’environnement `E`.

## L'algorithme d'inférence W de Damas-Milner-Tofte

L’algorithme W est alors le suivant:

**Entrée**: un environnement de typage `E` et une expression `e`.

**Sortie**: une erreur de typage ou bien un couple `(τ, φ)` où `τ` est le type inféré pour l’expression `e` et `φ` est la substitution à effectuer sur les variables libres de `E` afin que `e` soit typable dans `E`.

**Calcul**:

- Si dans ce qui suit, un usage de l'unification `mgu` échoue, ou un des sous-appels récursifs à W échoue, alors on répond `W (E,e) = err`.

- Si `e` est primitif (p.ex. constante numérique ou opérateur prédéfini):
  soit `σ` son schéma de type ;
  répondre `τ = Inst(σ)` et `φ = id`.

- Si `e` est une abréviation `x` avec `x ∈ E`:
  répondre `τ = Inst(E(x))` et `φ = id`.

- Si `e` est une fonction `fun x -> e1` :
  soit `α` une nouvelle variable de type ;
  soit `E1 = E+{x:α}` l'environnement enrichi avec `x`;
  soit `(τ1, φ1) = W (E1, e1)` le typage de `e1` ;
  répondre `τ = φ1(α) -> τ1` et `φ = φ1`.

- Si `e` est une application `e1 e2` :
  soit `(τ1, φ1) = W (E, e1)` le typage de `e1` ;
  soit `(τ2, φ2) = W (φ1(E), e2)` le typage de `e2` ;
  soit `α` une nouvelle variable de type ;
  soit `μ = mgu{φ2(τ1) = τ2 → α}` l'unification ;
  répondre `τ = μ(α)` et `φ = μ ◦ φ2 ◦ φ1`.

- Si `e` est un `let x = e1 in e2` :
  soit `(τ1, φ1) = W (E, e1)` le typage de `e1` ;
  soit `E1 = φ1(E)` l'environnement mis-à-jour ;
  soit `σ1 = Gen(τ1,E1)` le schéma de type généralisant `τ1` (ou bien `σ1 = τ1` quand la généralisation est exclue ici, cf tout en bas de ce document) ; 
  soit `E2 = E1+{x:σ1}` l'environnement enrichi avec `x` ;
  soit `(τ2, φ2) = W (E2, e2)` le typage de `e2` ;
  répondre `τ = τ2` et `φ = φ2 ◦ φ1`.

- Si `e` est un `let rec x = e1 in e2` :
  Dans les grandes lignes on procède comme au cas précédent, sauf que `e1` est typé dans un environnement contenant déjà `x` (avec un type quelconque au début). Plus précisément:
  soit `α` une nouvelle variable de type ;
  soit `E' = E+{x:α}` l'environnement enrichi avec `x` ;
  soit `(τ1, φ1) = W (E', e1)` le typage de `e1` ;
  soit `μ = mgu{φ1(α) = τ1}` l'unification des types de `x` et de son corps `e1`;
  soit `E1 = μ∘φ1(E)` l'environnement mis-à-jour ;
  soit `σ1 = Gen(τ1,E1)` le schéma de type généralisant `τ1` ;
  soit `E2 = E1+{x:σ1}` l'environnement enrichi avec `x` (type généralisé cette fois) ;
  soit `(τ2, φ2) = W (E2, e2)` le typage de `e2` ;
  répondre `τ = τ2` et `φ = φ2 ◦ μ ∘ φ1`.

- Si `e` est une paire `(e1,e2)` :
  soit `(τ1, φ1) = W (E, e1)`
  soit `(τ2, φ2) = W (φ1(E), e2)`
  répondre `τ = φ2(τ1) * τ2` et `φ = φ2 ◦ φ1`.

Ce n'est pas la fin de l'histoire, mais ces premiers cas permettent déjà de traiter les constructions formant le coeur d'OCaml. Les autres cas importants seront exposés plus bas, à un niveau plus informel pour que ce document reste digeste.

## Un premier exemple

Prenons `e = fun x -> x + 1`. On rappelle que `x+1` est en fait `(+) x 1`, qui est donc deux applications `((+) x) 1)`, avec `(+)` une opération primitive de type `int->int->int`.

```
Calcul de W (∅, fun x -> x + 1) ?
 Calcul de W({x:α}, (+) x 1) ?
   Calcul de W({x:α}, (+) x) ?
     Calcul de W({x:α}, (+)) ?
       Opérateur primitif, Inst ne change rien, φ=id
     Donc W({x:α}, (+)) = (int->int->int, id)
     Calcul de W({x:α}, x)
       Variable, on consulte l'environnement, Inst ne change rien, φ=id
     Donc W({x:α}, x) = (α, id)
     Calcul de μ = mgu(int->(int->int) = α -> β) avec β nouvelle variable
                 = [α ← int; β ← int->int]
   Donc W({x:α}, (+) x) = (int->int, μ)
   Calcul de W({x:int}, 1) ?
     Constante primitive de type int
   Donc W({x:int}, 1) = (int, id)
   Calcul de μ' = mgu(int->int = int -> γ) avec γ nouvelle variable
                = [γ ← int]
 Donc W({x:α}, (+) x 1) = (int, μ'∘μ)
Donc W(∅, fun x -> x + 1) = (int -> int, μ'∘μ)
```

Ici on remarque que la substitution obtenue n'est en fait pas utile,
car l'environnement initiale est vide de toute façon.

### Et pour les constructions non mentionnées auparavant ?

- Pour une définition globale `let x = e` ou globale récursive `let rec x = e`, on commence comme une définition locale, et on s'arrête après l'étape de généralisation. Le type mis dans l'environnement globale pour `x` est alors ce type généralisé.

- Si `e` est un n-uplet `(e1,e2,...,en)` on étend le cas précédent des pairs, en typant successivement chaque composant et en propageant les contraintes via des substitutions. Le type final est évidemment un type n-uplet `τ1*...*τn`.

- Si `e` est un constructeur `C (e1,...,ek)` d'un type algébrique, avec ses arguments, on regarde tout d'abord la définition du type algébrique
correspondant:
`type (α1,...αn) t = ... | C of τ1 * ... * τk | ...`.
Cela signifie donc que le constructeur `C` admet le schéma de type
`∀α1,...αn. (τ1 * ... τk -> (α1,...αn) t)`. Et on type ensuite `C (e1,...,en)` comme on traiterait une application à un n-uplet. C'est un léger abus, car `C` n'est pas une fonction, et ne pourrait être utilisé seul sans ses arguments, mais au niveau typage tout se passe ensuite de la même façon. En particulier, il y aura une instantiation du schéma de type de `C`, et une unification avec le n-uplet typant les arguments.

- Si `e` est un enregistrement (record) `{ label1 = e1; ...}` : grâce aux noms des champs, on peut retrouver le type enregistrement en question, et sa déclaration. On vérifie ensuite que tous les champs sont bien présents, et que les expressions `ei` sont bien de types compatibles avec les types des champs correspondants.

- Si `e` est une projection `e1.label` d'un record, là encore le nom de ce champ `label` permet de retrouver de quel type enregistrement il s'agit, puis on unifie le type inféré pour `e1` avec ce type enregistrement, et enfin le type de `e1.label` est alors le type de ce champ `label` spécialisé avec les contraintes découvertes jusqu'ici. 

- Pour un match, on va typer l'expression qu'on analyse, et vérifier que le type de chaque pattern s'unifie bien avec le type de cette expression. On fait ensuite une unification des types de toutes les expressions dans les branches pour trouver le type final de ce match.

## Typage et code impératif

Comme annoncé auparavant, il est parfois crucial de ne *pas* généraliser les variables de type issues de certains `let`. Cela est dû à la présence en OCaml de constructions impératives, permettant de changer en place certains contenus en mémoire. Si ces contenus avaient reçu initialement un type avec variables, rendre ces variables universelles mènerait à la possibilité d'ajouter des éléments incompatibles dans la même structure, et invaliderait les propriétés de sûreté à l'exécution des expressions bien typées d'OCaml.

Plus précisément, considérons une référence contenant initialement une liste vide :

```ocaml
let r = ref [];;
(* r : '_a list ref *)
```

On notera que OCaml utilise une syntaxe particulière `'_a` avec un souligné pour afficher la variable de type (dans sketch.sh c'est encore plus net comme affichage : `_weak1`). Cela indique que cette variable de type n'est pas universelle, mais plutôt *existentielle*, et ne pourra donc être instantié par la suite qu'une unique fois au plus.

Faisons un peu d'OCaml-fiction, et supposons que `r` ait le type `'a list ref` avec un `'a` universel. Alors `r` admet aussi le type `int list ref` (par instantiation), et donc la phrase suivante est bien typée:

```ocaml
r := 1 :: !r ;;
```

Mais une autre instantiation possible serait aussi `(int*int) list ref`, et on pourrait donc effectuer *ensuite* le calcul suivant par exemple :

```ocaml
fst (List.hd !r);; (* alors que r contient un entier en tête ?!? *)
```

Ce qui mènerait à considérer lors de l'exécution l'entier 1 comme une paire, c'est-à-dire un bloc mémoire à deux cases, et ce genre de confusion entre entier et structure mémoire mène droit à un `Segmentation Fault` et à un crash.

Le fait que `r` est un type avec une variable existentielle est donc une protection d'OCaml, la première utilisation de `r` figera le contenu de cette variable de type. Ici il s'agit de `r := 1 :: !r`, donc `r` sera alors définitivement un `int list ref`, et la phrase suivante `fst (List.hd !r)` est rejeté par OCaml comme mal typée.

Maintenant, quand est-il dangereux de généraliser ? Quand est-ce sûr ?
Dans un `let x = e`, la solution d'OCaml est de rejeter toute généralisation des variables de types quand l'expression `e` comporte en tête une application. C'est bien le cas de `let r = ref 1`. Cela peut avoir un impact sur du code qui est pourtant sans risque, par exemple lors d'une application partielle:

```ocaml
let idlist = List.map (fun x -> x) 
(* pas de généralisation, donc type '_a list -> '_a list *)
```

Solution si cela vous arrive, écrire ce genre de code avec tous les arguments :

```ocaml
let idlist l = List.map (fun x -> x) l
(* ou bien *)
let idlist = fun l -> List.map (fun x -> x) l
(* cette expression est une fonction, pas une application, donc
   généralisation, donc type 'a list -> 'a list
```

## Plus d'exemples

Voir le fichier [cours-11-typage.ml](cours-11-typage.ml)
