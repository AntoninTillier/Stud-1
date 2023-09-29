let rec intersection l1 l2 =
  if (l1 = [] || l2 = []) then []
  else if (List.hd (l1) = List.hd (l2))
  then List.hd (l1) :: intersection (List.tl (l1)) (List.tl (l2))
  else intersection (List.tl (l1)) (List.tl (l2));;
(*  val intersection : 'a list -> 'a list -> 'a list = <fun>  *)
intersection [1; 3; 5; 7; 9] [2; 3; 6; 8; 9];;
(*  - : int list = [3; 9] *)


let rec union l1 l2 =
  if (l1 = [] || l2 = []) then []
  else if (List.hd (l1) = List.hd (l2)) then List.hd (l1) :: union (List.tl (l1)) (List.tl (l2))
  else List.hd (l1) :: List.hd (l2) :: union (List.tl (l1)) (List.tl (l2));;
(* val union : 'a list -> 'a list -> 'a list = <fun> *)
union [1; 3; 5; 7; 9] [2; 3; 6; 8; 9];;
(* - : int list = [1; 2; 3; 5; 6; 7; 8; 9] *)


let rec difference l1 l2 =
  if (l1 = [] || l2 = []) then []
  else if (List.hd (l1) = List.hd (intersection l1 l2)) then difference (List.tl (l1)) (List.tl (l2))
  else List.hd (l1) :: difference (List.tl (l1)) (List.tl (l2));;
(*  val difference : 'a list -> 'a list -> 'a list = <fun> *)
difference [1; 3; 5; 7; 9] [2; 3; 6; 8; 9];;
(*  - : int list = [1; 5; 7] *)


let rec difference_symetrique l1 l2 =
  if (l1 = [] || l2 = []) then []
  else if (List.hd (l1) = List.hd (intersection l1 l2)) then difference_symetrique (List.tl (l1)) (List.tl (l2))
  else if (List.hd (l1) > List.hd (l2)) then List.hd (l2) :: List.hd (l1) :: difference_symetrique (List.tl (l1)) (List.tl (l2))
  else List.hd (l1) :: List.hd (l2) :: difference_symetrique (List.tl (l1)) (List.tl (l2));;
(*    val difference_symetrique : 'a list -> 'a list -> 'a list = <fun> *)
difference_symetrique [1; 3; 5; 7; 9] [2; 3; 6; 8; 9];;
(* - : int list = [1; 2; 5; 6; 7; 8] *)


let rec cartesien l1 l2 = match (l1,l2)with
([],[]) -> []
|(h::t,[]) -> []
|([],r::y) -> []
|(h::t,r::y) -> (h,r)::cartesien [h] y @ cartesien t l2;;
(*  val cartesien : 'a list -> 'b list -> ('a * 'b) list = <fun> *)
cartesien [1; 3; 5; 7; 9] [2; 3; 6; 8; 9] ;;
(*[(1, 2); (1, 3); (1, 6); (1, 8); (1, 9); (3, 2); (3, 3); (3, 6); (3, 8);
 (3, 9); (5, 2); (5, 3); (5, 6); (5, 8); (5, 9); (7, 2); (7, 3); (7, 6);
 (7, 8); (7, 9); (9, 2); (9, 3); (9, 6); (9, 8); (9, 9)] *)
