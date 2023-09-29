let permute_element tab n p =
  let aux = tab.(n) in tab.(p) ; tab.(p) <- aux;;
(* val permute_element : 'a array -> int -> int -> unit = <fun> *)

let choisir_pivot tab debut fin = debut ;;
(* val choisir_pivot : 'a -> 'b -> 'c -> 'b = <fun> *)

let partionner tab debut fin ipivot = 
    permute_element tab debut ipivot ;
        let p = ref (debut) in
     for i = debut+1 to fin do 
       if(tab.(i) < tab.(ipivot))then begin
         incr p;
         permute_element tab i !p
       end
     done;
        permute_element tab ipivot !p ;
         !p;;
(* val partionner : 'a array -> int -> int -> int -> int = <fun>*)

let rec tri_rapide tab debut fin = 
if debut < fin 
    then let pivot = choisir_pivot tab debut fin in
         let place_pivot = partionner tab debut fin pivot in
         tri_rapide (tri_rapide tab debut (place_pivot-1)) (place_pivot+1)fin
    else
          tab;;
(*  val tri_rapide : 'a array -> int -> int -> 'a array = <fun> *)



(*Example 1*)
let tab1 = [|1;2;9;7;5;4;6|];;
tri_rapide tab1 0 6;;
(* -: int array = [|1;2;4;5;6;7;9|] *)

let rec qsort l = match l with
    [] -> []
  | pivot::reste -> let petits, grands = List.partition((>)pivot) reste in 
                         qsort petits@[pivot]@qsort grands ;;
(* val qsort : 'a list -> 'a list = <fun> *)

(*Course question*)

(*
In programming, genericity means defining identical algorithms that operate on data of different types. Genericity is therefore linked to polymorphism.
*)

(*
Type polymorphism also means type parameterization: the general (abstract) data type appears as a parameter of the defined algorithms, with the special feature that this parameter is a type.
*)


(*Example 2*)
let rec filtreI nb tab =
if ( tab = [] ) then false
  else if ( List.hd(tab) = nb ) then true
    else (filtreI nb (List.tl(tab)));;
(* val filtreI : 'a -> 'a list -> bool = <fun> / compares element by element if nb belongs to the list *)

let rec filtre1 nb tab=
if ( filtreI nb tab = false ) then []
  else if ( List.hd(tab) = nb ) then [List.hd(tab)]@(filtre1 nb (List.tl(tab)))
           else filtre1 nb (List.tl(tab));;
(* val filter1 : 'a -> 'a list -> 'a list = <fun> / if false returns empty list, if true searches for value equal to nb elements by elements *)
filtre1 4 [1;4;5;6;4;2;4;9];; (* - : int list = [4; 4; 4] *)
(**********************)
let rec filtreIn nb tab = 
if ( tab = [] ) then false
  else if ( List.hd(tab) = nb*((List.hd(tab))/nb)) then true
          else ( filtreIn nb (List.tl(tab)));;
(* val filtreIn : int -> int list -> bool = <fun> / compares element by element if a multiple of nb belongs to the list *)

let rec filtre2 nb tab = 
if ( filtreIn nb tab = false ) then []
  else if ( List.hd(tab) = nb*((List.hd(tab))/nb)) then [List.hd(tab)]@(filtre2 nb (List.tl(tab)))
         else ( filtre2 nb (List.tl(tab)));;
(* val filter2 : int -> int list -> int list = <fun> / if filterIn returns false then empty list, otherwise search one by one for elements that happen to be a multile of nb *)
filtre2 5 [0;2;5;7;10;15;17];;
(*  - : int list = [0; 5; 10; 15] *)


(*Example 3*)
let rec map tab = match tab with
  [] -> []
  | h::[] -> [h+1]
  | h::t-> t@map t;;

map [1;2;3];;
List.map succ [1;2;3];;
(* - : int list = [2; 3; 4]*)

let increment x = x+1;;
(* val increment : int -> int = <fun> *) 
List.map increment [1;2;3];;
(*  - : int list = [2; 3; 4] *)

let enleve x = x-1;;
(* val enleve : int -> int = <fun> *)
List.map enleve [1;2;3];;
(* - : int list = [0; 1; 2] *) 


(*Example 4*)
let plus x y = x+y;;
(* val plus : int -> int -> int = <fun> *)

let rec apply plus x list = match list with
  [] -> x
  | h::[] -> x+h
  | h::t -> let temp = x+h in apply plus temp t;;
(* val apply : 'a -> int -> int list -> int = <fun> / We add x with each array value one by one *)
apply plus 0 [1;2;3;4];;
(*   - : int = 10 *)


let rec apply max x list = match list with
  [] -> x
  | h::[] -> max x h
  | h::t -> let temp = max x h in apply max temp t;;
(* val apply : ('a -> 'b -> 'a) -> 'a -> 'b list -> 'a = <fun> / we first search for the maximum between x and the list head, this maximum then becomes a temporary variable which we compare with the rest of the list following the same principle *)
apply max 0 [4;8;5;2;6;4];;
(* - : int = 8 *)
