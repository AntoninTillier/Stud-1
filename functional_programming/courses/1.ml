2 + 2;;(*- : int = 4 , operation 2 + 2 makes 4*)

"il était " ^ "une fois";;(*- : string = "il \195\169tait une fois" , It's utf 8, so there are no accents.*)

29 / 4;;(*- : int = 7 , The result is rounded *)

29 /. 4;;(*Error: This expression has type int but an expression was expected of type float, We search for an integer but the console returns a decimal *)

29 mod 4;;(*int = 1, because 29 in modulo 4=1 (29=4*7+1)*)

0.5 + 20.2;;(* Error: This expression has type float but an expression was expected of type int *)

0.5 +. 20.2;;(* - : float = 20.7 *)

(42, 'A');;(*- : int * char = (42, 'A')*)

fst (42, 'A');;(* fst=first, returns the first element, -: int= 42 *)

snd (24, 'A');;(* snd= second, returns the 2nd element, -: char= 'A' *)

2 = 2;;(* -: bool = true, checks result *)

2 <> 2;;(* -: bool= false, checks result *)

'a' = 'A';;(* -: bool= false, checks result *)

false || true;;(* -: bool = true, checks result *)

true && true;;(* -: bool = true, checks result *)

(2 = 3) || ('a' <> 'A' && true);;(* -: bool = true, checks result *)

1;;(* - : int = 1 *)

[];;(* - : 'a list = [],this displays an empty list *)

[1; 2; 3];;(* - : int list = [1; 2; 3],this displays a list of three elements *)

function x -> x + 1;;(* - : int -> int = <fun>, an integer x is associated with the integer x+1, giving the function*)

(function x -> x + 1) 3;;(* - : int -> int = <fun>, an integer x is associated with the integer x+1, giving the function*)

function x -> (function y -> x + y);;(* - : int -> int -> int = <fun>, function x becomes function y where y is associated with the value x+y*)

(function x -> (function y -> x + y) 1) 3;;(* - : int = 4, we give y=1, and x=3 with a result of 4 *)

function (x, y) -> x;;(* - : 'a * 'b -> 'a = <fun>, returns the 1st element of the function *)

(function (x, y) -> x) (42, 'A');;(*- : int = 42, because x * is requested*)

let x = 2 + 5;;(* val x : int = 7, on additionne 2 et 5 *)

x;;(* - : int = 7 on demande le résultat de l'addition *)

let produit = fun x y -> x * y;;(*val produit : int -> int -> int = <fun>, on défini la fonction produit ayant pour paramètres x & y*)

produit 2 3;;(*- : int = 6,utilise la fonction produit précédemment écrit afin d'effectuer la multiplication de 2 et 3*)

let a = 1;;(*val a : int = 1 , defines the value of a *)
a;;(*- : int = 1, displays a*)

let translation = fun x -> x + a;;(*val translation : int -> int = <fun>, we define the translation function with parameters x & a*)

translation 3;;(*- : int = 4 , displays the result of the function*)

let a = 2;;(*val a : int = 2 , defines the value of a *)

translation 3;;(*- : int = 4 , displays the result of the function*)

let f = fun x1 -> (fun x2 -> (fun x3 -> x1 * x2 - x3));;(*- : int = 4 , display the result of the function*)(*val f : int -> int -> int = <fun>, define the function f with parameters x1 & x2 & x3*)

f 2 4 6;;

let f = fun x1 x2 x3 -> x1 * x2 - x3;;(*val f : int -> int -> int -> int = <fun>, we define the function f with parameters x1 & x2 & x3*)

f 2 4 6;;

let f x1 x2 x3 = x1 * x2 - x3;;(*val f : int -> int -> int -> int = <fun>, we define the function f with parameters x1 & x2 & x3*)

f 2 4 6;;

let a = 1 and b = 2 in a * b;;(* -: int=2, calculates 1*2 *)
a;;(* -: int=2, a takes the value of a*b which gives 2 *)

let a = 1
in let a = 2 and b = a
  in a + b;;(*-: int=3, we give the first a the value of 1, then the second the value of 2 and we give b the value of the first a, then we add a+b which gives 3*)
 
let f x = x + (if x < 0 then - 1 else 1);;(*val f : int -> int = <fun> *)
f (- 3);;(* -: int=-4, x is given the value -3, then the previous function is applied *)
f 50;;(* -: int=51, x is given the value 50, then the previous function is applied *)

if 2 < 3 then "etrange"
else "ok";;(*-: string="etrange", returns "etrange" if 2<3 otherwise returns "ok", but returns "etrange" in all cases in this example *)

let division a x =
  if x = 0
  then failwith "divide by zero"
  else a / x;;(* val division : int -> int -> int = <fun> *)

let rec factoriel n =
  if n >= 0 then
    if (n = 0 || n = 1) then
      1
    else
      n * (factoriel (n - 1))
  else failwith "Impossible";;(*  val factoriel : int -> int = <fun>*)

let maxi3 x y z =
  if (x >= y && x >= z)
  then x
  else if (y >= x && y >= z)
  then y
  else z;;

maxi3 5 8 2;;(*  val maxi3 : 'a -> 'a -> 'a -> 'a = <fun>
#   - : int = 8 *)

let maxi x y z =
  max (max x y) z;;

maxi 5 56 78;; (*val maxi : 'a -> 'a -> 'a -> 'a = <fun>
#   - : int = 78 *)


      
