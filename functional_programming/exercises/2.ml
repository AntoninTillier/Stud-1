(*Class*)
let l= ["is";"a";"tale";"told";"etc"];;
(* val l : string list = ["is"; "a"; "tale"; "told"; "etc"], returns the list *)

"Life" ::l;;
(* - : string list = ["Life"; "is"; "a"; "tale"; "told"; "etc"], adds "Life" to the top of the previously initialized list l *)

List.hd[1;2;3];;
(* - : int = 1, gives the value of the list header *)

List.tl["z";"d";"f"];;
(*- : string list = ["d"; "f"], returns the list without the first value (head) *)

[1;2;3;4;5;6]@[7;8;9;];;
(* - : int list = [1; 2; 3; 4; 5; 6; 7; 8; 9], adds the second list to the end of the first *)

List.rev[1;2;3;4;5;6;7];;
(* - : int list = [7; 6; 5; 4; 3; 2; 1], inverts the order of the values contained in the list, the first becomes the last, etc *)

0::[];;
(* - : int list = [0], create a list with unique value 0 *)

let maListe=1::2::3::[];;
(* val myList : int list = [1; 2; 3], create a list called myList with value 1;2;3 *)

0::maListe;;
(* - : int list = [0; 1; 2; 3], adds 0 to the first value of the previous list *)

let rec longueur l=
  if(l=[]) then 0
    else 1+longueur(List.tl(l));;
(* val length : 'a list -> int = <fun>, counts the number of elements in list l *)

longueur maListe;;
(* - : int = 3, indicates that the list myList contains 3 elements *)

(*Exercise 1*)
(*Write and test the "belongs" function for a list.*)

let rec appartient nb l=
if(l=[]) then false
  else if(nb=List.hd(l)) then true
       else appartient nb (List.tl(l));;
appartient 10 [1;2;3;55;10;147];; (* - : bool = true, returns true because 10 belongs to the list *)
appartient 5 [41;2;3;6;98;7];; (* - : bool = false, returns false because 5 does not belong to the list *)

(*Exercise 2*)
(*
Write and test the function that checks whether 2 lists are "identical".
Note that you need to write the specification first!
*)

(** [identiques l1 l2] is true if the lists [l1] and [l2] are identical in terms of structure and
    structure and values, and false otherwise.

    @param l1 : 'a list, the first list
    @param l2 : 'a list, the second list
    @return : bool, true if lists are identical, false otherwise
*)
let rec identiques l1 l2=
if (longueur l1<> longueur l2) then false
  else if(l1=[]&&l2=[]) then true
  else if (List.hd(l1)=List.hd(l2)) then identiques (List.tl(l1)) (List.tl(l2))
        else false;;

identiques [1;2;5] [1;2;5;4];; (* - : bool = false, returns false because they don'player_ruin.pl have the same length, so a list contains one or more extra values, in this case 4 *)
identiques [1;2;5] [1;2;5];; (* - : bool = true, lists identical *)
identiques [44;5;78;9] [];; (* - : bool = false, returns false because list is totally different *)
