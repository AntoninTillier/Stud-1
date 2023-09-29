let rec insertion nb l=
  if(l=[]) then [nb]
    else 
       if (List.hd(l)>nb) then nb::l
         else ((List.hd(l))::(insertion nb (List.tl(l))));;
(* val insertion : 'a -> 'a list -> 'a list = <fun> // the insertion function checks if the list is empty, in this case nb is inserted in the list, if the list contains values nb is compared with the value at the top of the list, if nb is less then it becomes the value at the top of the list, otherwise the value is left at the top of the list and nb is compared with the sequence of elements *)

let l1=[3;45;78;90];;
(*  val l1 : int list = [3; 45; 78; 90] *)
insertion 1 l1;;
(* - : int list = [1; 3; 45; 78; 90]// 1 is less than 3 so it goes to the top of the list *)
insertion 52 l1;;
(* - : int list = [3; 45; 52; 78; 90]// 52 is greater than 3 and 45, so by recursion 52 is placed in third position before the value in the list which is greater than it, in this case 78 *)

let rec tri_insertion l=
  if (l=[]) then []
    else (insertion(List.hd(l))(tri_insertion(List.tl(l))));;
(* l tri_insertion : 'a list -> 'a list = <fun> // the tri_insertion function calls the insertion function to sort the elements of the list in ascending order by taking the top of the list and comparing it with the following values *)

tri_insertion [87;7;567;5;1;666;10];;
(* - : int list = [1; 5; 7; 10; 87; 567; 666]// all elements are sorted in ascending order *)


let rec separe l=
  if ( l=[])then (l,[])
    else if ((List.tl(l))=[]) then (l,[])
       else( let (l1, l2) = separe (List.tl(List.tl(l))) in (((List.hd(l))::l1),((List.hd(List.hd(l)))::l2)));;
(* val separe : 'a list list -> 'a list list * 'a list = <fun> // this function separates a list into two distinct lists *)

let l3=[4;1;3;5;1;2];;
(*  val l3 : int list = [4; 1; 3; 5; 1; 2] *)
separe l3;;
(* ([4;3;1],[1;5;2]) *)

let rec fusionne l1 l2=
  if(l1=[]) then l2
    else if (l2=[]) then l1
      else if ((List.hd(l1)) <= (List.hd(l2))) then (List.hd(l1))::(fusionne(List.tl(l1))l2)
          else (List.hd (l2))::(fusionne l1(List.tl(l2)));;
(* val merge: 'a list -> 'a list -> 'a list = <fun> // this function merges two lists by arranging the values in ascending order *)
fusionne [1;3;4] [1;2;5];;
(* - : int list = [1; 1; 2; 3; 4; 5] *)

let rec tri_fusion l=
  if(l=[]||(List.tl(l))=[]) then []
    else ( let temp = (separe l) in fusionne (tri_fusion(fst(temp)))(tri_fusion(snd(temp))));;
(* val tri_fusion : 'a list list -> 'a list list * 'a list -> 'a list = <fun> // this function takes a list as parameter, this list will be separated according to the separe function written previously, then the two lists will be merged according to the merge method, the elements of the list will then be sorted in ascending order *)
tri_fusion [4;1;3;5;1;2];;
(* - : int list = [1;1;2;3;4;5] *)
tri_fusion [5;4;7;1;2;9];;
(* - : int list = [1;2;4;5;7;9] *)

(*Example 1*)
let rec sep nb l=
 if (l=[]) then ([],[])
   else if (List.tl(l)=[]) then (l,[])
     else  let (l1, l2) = sep (List.tl(l)) in
            if ( nb > (List.hd(l))) then ((List.hd(l)::l1),(List.hd(List.tl(l))::l2))
              else ( List.hd(List.tl(l)::l1, List.hd(l))::l2);;
sep 3 [1;5;4;2;0;7];;


(*Example 2*)
let rec min l=
  if (l=[]) then []
    else if ( ( List.hd(l)) < (List.hd(List.tl(l))) ) then List.hd(l)
           else min (List.tl(l));;

(* val min : 'a list list -> 'a list = <fun> // compare the values 2 by 2 to find the minimum value of the array *)
min [4;5;2;11;78;1;9];;
(* -: int = 1 *)

let rec listeMin l=
  if (l = [] ) then (0, [] )
    else let (min, l1) = listeMin l  in
      if ( (List.hd(l)) = min l ) then ( min l, listeMin (List.tl(l))::[] )
        else ( min l, (List.hd(l)) :: ( listeMin (List.tl(l))) :: []) ;;
