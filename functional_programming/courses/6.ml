type arbreBin =
    Vide
  |Noeud of int * arbreBin* arbreBin;;

let t1 = Vide;;
(* val t1 : arbreBin = Vide *)
let t2 = Noeud(3, Noeud(4, Vide, Vide), Noeud(5, Vide, Vide));;
(* val t2 : arbreBin = Noeud (3, Noeud (4, Vide, Vide), Noeud (5, Vide, Vide)) *)
let t3 = Noeud(1,Noeud( 2, Vide, Vide), t2);;
(*  val t3 : arbreBin =
  Noeud (1, Noeud (2, Vide, Vide), Noeud (3, Noeud (4, Vide, Vide), Noeud (5, Vide, Vide))) *)


let rec taille arbre = match arbre with
    Vide -> 0
  | Noeud( e, a1, a2 ) -> 1 + taille a1 + taille a2;;
(* val taille : arbreBin -> int = <fun> / 0 is returned if the node returns empty, otherwise 1 is added to the size of the nodes that follow thanks to recursivity *)
taille t3;;
(* - : int = 5 *)


let rec hauteur arbre = match arbre with
    Vide -> 0
  | Noeud( e, a1, a2 ) -> 1+ max ( hauteur a1 )( hauteur a2 );;
(* val hauteur : arbreBin -> int = <fun> / If the tree is empty, 0 is returned, otherwise 1 is added to the maximum height between the nodes that follow *)
hauteur t3 ;;
(*  - : int = 3 *)


let rec appartient e arbre = match arbre with
    Vide -> false
  | Noeud( x, g, d )-> ( x = e ) || appartient e g || appartient e d;;
(* val appartient: int -> arbreBin -> bool = <fun> / We check whether node number e belongs to the tree and return true or false. To verify this, we check from node to node using recursion *)
appartient 2 t3;;
(*   - : bool = true *)
appartient 12 t3;;
(*  - : bool = false *)


let rec arbreEquilibre arbre = match arbre with
    Vide -> false
  | Noeud ( e, a1, a2 ) -> if (abs ( (hauteur a1) - (hauteur a2)) <= 1) then true 
                              else false;;
(*  val arbreEquilibre : arbreBin -> bool = <fun> *)
let t4 = Noeud (1, Vide, Noeud( 2, Vide, Noeud( 3, Vide, Noeud(4, Vide, Noeud(5, Vide, Vide)))));; 
(*  val t4 : arbreBin = Noeud (1, Vide, Noeud (2, Vide, Noeud (3, Vide, Noeud (4, Vide, Noeud (5, Vide, Vide))))) *)
arbreEquilibre t4;;
(* - : bool = false *)


(*Example 1*)
let rec somme  arbre = match arbre with
   Vide -> 0
  | Noeud ( x, a1, a2 ) -> x + somme a1 + somme a2;;
(* val somme : arbreBin -> int = <fun> / x is added to the sum of the labels of the nodes linked to a1 and a2 *)
somme t4;;
(*  - : int = 15 *)

(*Example 2*)
type arbre =
  Vide
  | Noeud of arbre*int*arbre;;

let rec etiquetteInfixe arbre = match arbre with
    Vide -> []
  | Noeud( Vide, e, Vide ) -> [e]
  | Noeud ( a1, e, a2 ) -> (etiquetteInfixe a1) @ [e] @ (etiquetteInfixe a2) ;;
(*   val etiquetteInfixe : arbre -> int list = <fun> *)

let rec etiquettePrefixe arbre = match arbre with
    Vide -> []
  | Noeud( Vide, e, Vide ) -> [e]
  | Noeud ( e, a1, a2 ) -> [e] @ (etiquettePrefixe a1) @ (etiquettePrefixe a2) ;;
(*   val etiquetteInfixe : arbre -> int list = <fun> *)

let rec etiquettePostfixe arbre = match arbre with
    Vide -> []
  | Noeud( Vide, e, Vide ) -> [e]
  | Noeud ( a1, a2, e ) -> (etiquettePostfixe a1) @ (etiquettePostfixe a2) @ [e] ;;
(*   val etiquetteInfixe : arbre -> int list = <fun> *)
