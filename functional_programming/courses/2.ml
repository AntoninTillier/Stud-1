let divisiblePar nombre diviseur = (nombre mod diviseur) = 0 ;; 
 divisiblePar 10 2;; (* val divisiblePar : int -> int -> bool = <fun>
# - : bool = true, returns true because the remainder of 10 divided by 2 is 0 *)

divisiblePar 4 6 ;; (* where x is 4 and y is 6*)(* - : bool = false, because the remainder of 4 divided by 6 is not 0 *)

let bissextile annee =
 (( annee mod 4 =0 )&&( annee mod 100 <> 0) || ( annee mod 400 =0));;

bissextile 2016 ;;
bissextile 2018 ;; (*  val bissextile : int -> bool = <fun>
#   - : bool = true
# - : bool = false *)

let afficheTestDiv nombre diviseur=
  if( divisiblePar nombre diviseur) then
    "oui"
  else 
    "non";; (*  val afficheTestDiv : int -> int -> string = <fun> *)
afficheTestDiv 20 5 ;;(* - : string = "oui" *)
afficheTestDiv 33 5 ;;(* - : string = "non" *)

let secondeSuivante (heure, minute, seconde ) =
  if(seconde<59) then
    (heure, minute, seconde+1)
  else 
    if (minute<59) then
      (heure, minute+1, 0)
    else
      if(heure<23) then
        (heure+1, 0, 0)
      else (0, 0, 0);; (* val secondeSuivante : int * int * int -> int * int * int = <fun> *)

secondeSuivante (23, 59, 59);; (* - : int * int * int = (0, 0, 0) *)
secondeSuivante (20, 59, 59);; (*  - : int * int * int = (21, 0, 0) *)
secondeSuivante (20, 30, 59);; (*  - : int * int * int = (20, 31, 0) *)
secondeSuivante (20, 30, 40);; (* - : int * int * int = (20, 30, 41) *)

let secondeSuivante (heure, minute, seconde) =
  match (heure, minute, seconde)
  with (23, 59, 59) -> (0, 0, 0)
  |    (heure, 59, 59) -> (heure+1, 0, 0)
  |    (heure, minute, 59) -> (heure, minute+1, 0)
  |    (heure, minute, seconde) -> (heure, minute, seconde+1);;

secondeSuivante (23, 59, 59);; 
secondeSuivante (20, 59, 59);; 
secondeSuivante (20, 30, 59);; 
secondeSuivante (20, 30, 40);;

let secondeSuivante (heure, minute, seconde) =
  match (heure, minute, seconde)
  with (23, 59, 59) -> (0, 0, 0)
  |    (heure, minute, seconde) -> (heure, minute, seconde+1)
  |    (heure, minute, 59) -> (heure, minute+1, 0)
  |    (heure, 59, 59) -> (heure+1, 0, 0);;

secondeSuivante (23, 59, 59);; (* - : int * int * int = (0, 0, 0) *)
secondeSuivante (20, 59, 59);; (* - : int * int * int = (20, 59, 60)*) 
secondeSuivante (20, 30, 59);; (* - : int * int * int = (20, 30, 60)*)
secondeSuivante (20, 30, 40);; (* - : int * int * int = (20, 30, 41)*)

let num (n, d)=n;; (* val num : 'a * 'b -> 'a = <fun> *)
let denom (n, d)=d;; (*  val denom : 'a * 'b -> 'b = <fun> *)
let addfrac f1 f2 = (( num f1* denom f2)+(num f2* denom f1),(denom f1*denom f2));; (* val addfrac : int * int -> int * int -> int * int = <fun> *)

addfrac (1,2) (3,4);; (*  * *     - : int * int = (10, 8)*)

type fraction= {num: int ; denom: int };;
let addfrac2 f1 f2 = { num=( f1.num* f2.denom + f2.num*f1.denom); denom=f1.denom*f2.denom};; 

addfrac2 { num=1; denom=2} { num=3; denom=4};;

(*Example 1*)
 
let testfrac f1 f2 = ( num f1* denom f2= denom f1* num f2 );; (* val testfrac : int * int -> int * int -> bool = <fun> *)
testfrac (1,2) (3,4);; (* - : bool = false *)
testfrac(1,2) (40, 80);; (*  - : bool = true *)

(* Example 2 *)

type etudiant ={nom:string;note:int};;
(* type etudiant = { nom : string; note : int; } *)

let ordonne e1 e2 e3 = 
  if (e1.note>e2.note && e1.note>e3.note && e2.note>e3.note) then (e1.nom, e2.nom, e3.nom)
 else if (e1.note>e2.note && e1.note>e3.note && e3.note>e2.note) then (e1.nom, e3.nom, e2.nom)
 else if (e2.note>e1.note && e2.note>e3.note && e1.note>e3.note) then (e2.nom, e1.nom, e3.nom)
 else if (e2.note>e1.note && e2.note>e3.note && e3.note>e1.note) then (e2.nom, e3.nom, e1.nom)
 else if (e3.note>e1.note && e3.note>e2.note && e1.note>e2.note) then (e3.nom, e1.nom, e2.nom)
 else (e3.nom, e2.nom, e1.nom) ;;
(* val classe : etudiant -> etudiant -> etudiant -> string * string * string = <fun> *)

ordonne {nom="a";note=2} {nom="m";note=6} {nom="n";note=8};;
(* string * string * string = ("n", "m", "a") *)
ordonne {nom="a";note=18} {nom="m";note=11} {nom="n";note=5};;
(*string * string * string = ("a", "m", "n")  *)
ordonne {nom="a";note=5} {nom="m";note=5} {nom="n";note=6};;
(* string * string * string = ("n", "m", "a") *)