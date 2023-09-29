(*
Use a function that creates a sorted list from a binary search tree: check this.
In other words, check by testing on examples (but this can also be demonstrated) that one of your functions constructing a list from a tree necessarily produces a sorted list if the tree is an ordered binary tree.
This gives yet another algorithm for sorting a list of integers:
add the elements of the list one by one to a binary search tree, then retrieve the list by traversing the tree in the correct order.
Assuming that the add function has a complexity in O(log n), n being the size of the tree, as each element of the list is added,
we have a complexity in O(log 1 + log 2 + ... + log (k- 1)), with k the number of elements in the list,
which can be approximated (Sterling's formula) as O(k log(k) - k), where k is the size of the list;
then we need to go back from the tree to the list (with your function), which is done in O(k).
Overall, we have O(k log(k) - k + k), i.e. O(k log(k)), which is of the same order as quick sort.
*)

(*Exercise 1*)

type arbreBin =
    Vide
  |Noeud of int * arbreBin* arbreBin;;

let t1 = Noeud(3, Noeud(2, Vide, Vide), Noeud(4, Vide, Vide));;
let t21= Noeud (2, Noeud(1,Vide,Vide), Noeud(3, Vide, Vide));;
let t22 = Noeud (7, Noeud(6, Vide, Vide), Noeud(8, Vide, Vide));;
let t2 = Noeud(4,t21, t22);;

(** [etiquetteInfixe arbre] returns a list of the labels in the binary tree [arbre] in infixed order.
    in infixed order.

    @param arbre : 'a binary_tree, a binary tree of type 'a
    @return : 'a list, a list of labels in infixed order of the tree
*)
let rec etiquetteInfixe arbre = match arbre with
    Vide -> []
  | Noeud( e,  Vide, Vide ) -> [e]
  | Noeud ( e, a1, a2 ) -> (etiquetteInfixe a1) @ [e] @ (etiquetteInfixe a2) ;;

(** [etiquettePrefixe arbre] returns a list of the labels in the binary tree [arbre]
    in prefix order.

    @param arbre : 'a binary_tree, a binary tree of type 'a
    @return : 'a list, a list of labels in tree prefix order
*)
let rec etiquettePrefixe arbre = match arbre with
    Vide -> []
  | Noeud( e, Vide, Vide ) -> [e]
  | Noeud ( e, a1, a2 ) -> [e] @ (etiquettePrefixe a1) @ (etiquettePrefixe a2) ;;

(** [etiquettePostfixe arbre] returns a list of the labels in the binary tree [arbre]
    in postfix order.

    @param arbre : 'a binary_tree, a binary tree of type 'a
    @return : 'a list, a list of labels in postfix order of the tree
*)
let rec etiquettePostfixe arbre = match arbre with
    Vide -> []
  | Noeud( e, Vide, Vide ) -> [e]
  | Noeud ( e, a1, a2 ) -> (etiquettePostfixe a1) @ (etiquettePostfixe a2) @ [e] ;;

etiquetteInfixe t1;; (*  - : int list = [2; 3; 4] *)
etiquetteInfixe t2;; (* - : int list = [1; 2; 3; 4; 5; 6; 7] *)

etiquettePrefixe t1;; (*  - : int list = [3; 2; 4] *)
etiquettePrefixe t2;; (* - : int list = [4; 2; 1; 3; 6; 5; 7] *)

etiquettePostfixe t1;; (*   - : int list = [2; 4; 3] *)
etiquettePostfixe t2;; (*  - : int list = [1; 3; 2; 5; 7; 6; 4] *)


(*Exercise 2*)
(*
To add an element x to a binary search tree, proceed as follows.
Start from the root of the tree. At a node labeled y, we continue to the left if x <= y and to the right if x > y.
NB. The element is always inserted as a leaf. Write the specification and code for an "add" function that adds an element to a binary search tree in this way.
If the starting list is in an order, for example ascending, then what is the shape of the tree constructed with the addition you've just coded?
This addition does not produce a balanced tree...
*)

(** [add x arbreBin] adds an element [x] to the binary tree [arbreBin] while maintaining the binary search tree (BST) property.

    @param x : 'a, the element to be added to the tree
    @param arbreBin : 'a arbre_binaire, a binary search tree of type 'a
    @return : 'a binary_tree, a new binary BST tree containing [x]
*)

let rec add x arbreBin = match arbreBin with
    Vide -> Noeud(x, Vide, Vide)
   | Noeud ( y, d, g) -> if ( x <=  y ) then  Noeud( y, add x g, d)
                             else Noeud ( y, g, add x d);;

add 5 t1;; (*  - : arbreBin = Noeud (3, Noeud (4, Vide, Vide), Noeud (2, Vide, Noeud (5, Vide, Vide))) *)

(*Exercise 3*)
(*
Write the specification and code for a function that tests whether a binary tree is ordered.
*)
let rec triee l = match l with
   [] -> failwith "liste vide"
  |h::[] -> true
  |h::t -> if ( h < List.hd(t)) then triee t else false;;

triee [1;2;3;4];; (* - : bool = true *)
triee [7;5;314;0];; (* - : bool = false *)
triee [];; (* Exception: Failure "liste vide". *)

(** [order arbre] checks whether the binary tree [arbre] is sorted, i.e. whether
    the tree's labels are in ascending order according to the infix path.

    @param arbre : 'a binary_tree, a binary tree of type 'a
    @return : bool, true if the tree is sorted, false otherwise
    @raise : Failure "Empty tree" if tree [tree] is empty
*)
let rec ordonne arbre = match arbre with
   Vide -> failwith "Arbre Vide"
  | Noeud ( e, Vide, Vide) -> true
  | Noeud ( e, g, d) -> triee (etiquetteInfixe arbre);;

let t3 = Vide;;
let t4 = Noeud (9, Noeud (10, Vide, Vide), Noeud ( 1, Vide, Vide ));;

ordonne t1;; (* - : bool = true *)
ordonne t2;; (* - : bool = true *)
ordonne t3;; (* Exception: Failure "Arbre Vide". *)
ordonne t4;; (* - : bool = false *)
