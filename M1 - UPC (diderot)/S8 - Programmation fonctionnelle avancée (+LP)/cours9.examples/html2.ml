open Html
let x = div [ pcdata "" ]

(* erreur, pas de div dans les span *)
let x' = span [ div [] ]

(* OK *)
let x'' = span [ span [] ]
let f s = div [ s; pcdata ""]
let f' s = div [ s; span []]
let f'' s = div [ s; span []; pcdata "" ]
