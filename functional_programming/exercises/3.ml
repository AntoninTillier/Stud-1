(*
Consider the "length" function you wrote and tested in a previous session.
We call an the number of tests (l=[]) performed to calculate the length of a list of n elements: to express an as a function of n, consider the case where n =0, then the general case. In concrete terms, we have :
an =1sin=0
an = 1 + an-1 otherwise
We therefore deduce that an =n+1
*)
(*Exercise 1*)
(*
You will code the following function in 2 different ways:
     [dernier l] returns the last element of list [l].
          @param l : 'a list, a non-empty list of objects of type 'a
          @return : 'a, the last element of the list
          @raise : Failure "empty list" if list [l] is empty.
In the first version (which you'll call dernier1) the stop test will be if there's only one element left in the list, by testing if the rest of the list is empty.
In the second version (which you'll call dernier2) the stop test will be if there's only one element left in the list, testing whether the rest of the list has a length equal to 0.
In both cases, the result will be the element at the top of the list if the list has only one element, otherwise it will be a call to the "dernier" function with the rest of the list as argument.
*)

let rec separe2 l p= match l with
  [] -> ([],[])
 |h::t -> let (l1,l2) = separe2 t p in 
          if ( h <= p ) then ( h::l1 , l2 )
            else ( l1 , h::l2);;

let rec quicksert l = match l with
  [] -> []
  | p::[] -> [p]
  | p::r -> let (l1, l2) = (separe2 r p) in quicksert (l1)@ quicksert (l2);;

let rec dernier1 l = match l with
  []-> failwith "echec"
  | h::[] -> h
  | h::t -> dernier1 t;;

dernier1 [1;2;3;4;5];; (*  - : int = 5 *)
dernier1 [4];; (* - : int = 4 *)
dernier1 [];; (* Exception: Failure "echec". *)

let rec longueur l =
if l=[] then 0  
    else 1 + longueur(List.tl(l)) ;;

let rec dernier2 l =
 if ( longueur l = 0 ) then failwith "echec"
    else  if ( longueur (List.tl(l)) = 0 ) then List.hd(l)
              else dernier2 (List.tl(l));;

dernier2 [5;7;8;99;2];; (*  - : int = 2 *)
dernier2 [10];; (*  - : int = 10 *)
dernier2 [];; (* Exception: Failure "echec". *)

(*Exercise 2*)
(*
Test these 2 functions for lists of 3 elements, then 10 elements: what do you find?
*)
let rec listealea n =
  if (n = 0) then []
  else (Random.int 10)::(listealea(n-1));; (* val listealea : int -> int list = <fun> *)

let l1 = listealea 3;; (*  val l1 : int list = [4; 7; 4] *)
let l2 = listealea 3;; (*  val l2 : int list = [5; 8; 8] *)

dernier1 l1;; (*  - : int = 4 *)
dernier1 l2;; (*  - : int = 8 *)

dernier2 l1;; (*  - : int = 4 *)
dernier2 l2;; (*  - : int = 8 *)

let l3 = listealea 10;; (* val l3 : int list = [2; 4; 8; 2; 3; 5; 0; 2; 4; 2] *)
let l4 = listealea 10;; (* val l4 : int list = [0; 0; 1; 4; 0; 0; 2; 6; 8; 4] *)

dernier1 l3;; (* - : int = 2*)
dernier1 l4;; (*  - : int = 4 *)

dernier2 l3;; (* - : int = 2 *)
dernier2 l4;; (*  - : int = 4*)

(*Note that "dernier2" seems slower than "dernier1".*)

(*Exercise 3*)
(*To generate "large" lists, use the function below:*)
let rec grandeListe n = 
if ( n = 0 ) then []
  else n::(grandeListe (n-1) );;
(*
Define variables l1, l100, l1000, l10000, l20000, l30000, where lk denotes the list of integers from k to 1.
Fill in the following commentary, measuring the time needed to obtain an answer (using a stopwatch).
*)
(* l1 -> similar times
   l10 -> similar times
   l100 -> similar times
   l1'000 -> dernier1 faster but almost equivalent
   l10'000 -> dernier1 faster
   l20'000 -> dernier1 is much faster
   l30'000 -> dernier1 is enormously faster than dernier2 *)

let chrono = Sys.time();;
let chrono2 = Sys.time()-.chrono;;

let liste1 = grandeListe 1;;

let chrono = Sys.time();;
dernier1 liste1;;
let chrono2 = Sys.time()-.chrono;; (*  float = 0.00100000000000000092 *)


let chrono = Sys.time();;
dernier2 liste1;;
let chrono2 = Sys.time()-.chrono;; (*  float = 0.00100000000000000089 *)


let liste2 = grandeListe 100;;

let chrono = Sys.time();;
dernier1 liste2;;
let chrono2 = Sys.time()-.chrono;; (* float = 0.00100000000000000089 *)


let chrono = Sys.time();;
dernier2 liste2;;
let chrono2 = Sys.time()-.chrono;; (* float = 0.00100000000000000089 *)

let liste3 = grandeListe 1000;;

let chrono = Sys.time();;
dernier1 liste3;;
let chrono2 = Sys.time()-.chrono;; (* float = 0.00100000000000000089 *)


let chrono = Sys.time();;
dernier2 liste3;;
let chrono2 = Sys.time()-.chrono;; (* float = 0.0269950000000000051 *)


let liste4 = grandeListe 10000;;

let chrono = Sys.time();;
dernier1 liste4;;
let chrono2 = Sys.time()-.chrono;; (* float = 0.000999999999999889866 *)


let chrono = Sys.time();;
dernier2 liste4;;
let chrono2 = Sys.time()-.chrono;; (* float = 2.641598 *)


let liste5 = grandeListe 20000;;

let chrono = Sys.time();;
dernier1 liste5;;
let chrono2 = Sys.time()-.chrono;; (* float = 0.00099999999999766942 *)


let chrono = Sys.time();;
dernier2 liste5;;
let chrono2 = Sys.time()-.chrono;; (*  float = 10.5403969999999987 *)


let liste6 = grandeListe 30000;;

let chrono = Sys.time();;
dernier1 liste6;;
let chrono2 = Sys.time()-.chrono;; (* float = 0.000999000000000194177 *)


let chrono = Sys.time();;
dernier2 liste6;;
let chrono2 = Sys.time()-.chrono;; (*  float = 23.696399 *)
