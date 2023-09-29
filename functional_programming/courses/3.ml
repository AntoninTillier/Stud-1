let rec nbOccurences (o, l) =
  if l = [] then 0
  else if o = List.hd (l)
  then 1 + nbOccurences (o, List.tl (l))
  else nbOccurences (o, List.tl (l));;

nbOccurences (1, [1; 5; 1; 8; 66; 1]);;
(* -: int=3, the result is equal to 3 because we ask how many times the number 1 is present in the number sequence and it is present 3 times. *)


let rec retireOccurence1 (o, l) =
  if l = [] then l
  else if o = List.hd (l) then List.tl (l)
  else List.hd (l) :: retireOccurence1 (o, List.tl (l));;
retireOccurence1 (1, [1; 5; 1; 8; 66; 1]);;
(* - : int list = [5; 1; 8; 66; 1], removes the requested value the first time it appears in the list *)


let rec plusGrand1 l = match l with
    [] -> failwith "n'existe pas"
    | h :: [] -> h
    | h :: t -> max h (plusGrand1 t);;

plusGrand1 [2; 56; 89; 5];;
(* - : int = 89, returns the value 89, because this value is the highest in the list entered *)

let rec plusGrand2 l = match l with
    [] -> failwith "n'existe pas"
    | h :: [] -> h
    | h1 :: h2 :: [] -> max h1 h2
    | h1 :: h2 :: t -> if h1 > h2 then
          plusGrand2 (h1 :: t)
        else
          plusGrand2 (h2 :: t);;

plusGrand2 [2; 56; 89; 5];;(*  - : int = 89 *)
plusGrand2 [45; 87; 3; 5; 66; 411];;
(* - : int = 411, also returns the largest value in the list by including them 2 by 2. *)

(*Example 1*)

let rec somme l = if l = [] then 0
  else List.hd (l) + somme (List.tl (l));;
(* Takes the first value in the list and adds it to sum, the function stops when there are no more values in the list.*)

somme [22; 1; 3; 4];;
(* - : int = 30, returns the sum of 22+1+3+4. *)

(*Example 2*)

let rec somme2 l1 l2 =
  if (l1 = []||l2 = []) then []
    else List.hd (l1) + List.hd (l2) :: somme2 (List.tl (l1)) (List.tl (l2));;
somme2 [2; 1; 4; 5; 8; 79; 4; 56] [4; 2; 6; 7];;