(*Principle of problem decomposition*)
(*
We want to write a function to test whether a date is valid. The date is represented by 3 integers:
day, month and year
For example, for applications of this function to the following arguments, we should get the answers :
- dateValide 18 11 1999 : true
- dateValide 18 13 1999 : false (13 is not a valid month)
- dateValide 31 11 1999 : false (31 is not a valid day for the month of November)
- dateValide 29 2 1996 : true
- dateValide 29 2 1999 : false (February 29 does not exist for 1999)
*)

(*Exercise 1: Specification*)

(** [dateValide day month year] is true if the date represented by [day], [month]
    and [year] is a valid date and false otherwise.
    - day] must be an integer between 1 and 31, depending on the month and year.
    - month] must be an integer between 1 and 12.
    - year] must be a positive integer.

   @param day : int, the day of the date
   @param month : int, the month of the date
   @param annee : int, the year of the date
   @return : bool, true if the date is valid, false otherwise
*)

(*Exercise 2: Algorithm*)

(*1. Describe the conditions that determine whether a date is valid.*)
(*A date is valid if the number of days entered in the parameter is within the number of days in the month (0<days<30 or 31 or 29/28 depending on the year).
It is also valid if the number of months entered in the parameter is less than or equal to 12*)

(*2. Write the code for a function that tests whether a number represents a valid month.*)

let rec moisValide mois =
  ((mois > 0) && (mois < 13));;

moisValide 14;;(*bool = false*)
moisValide 10;;(*bool = true*)

(*3. Write the code for a function that returns the number of days in a month, starting from a given month and year.*)
let rec bissextile annee =
  ((annee mod 4 = 0) && (annee mod 100 <> 0) || (annee mod 400 = 0));;

bissextile 2016;;

let rec nbJoursDsMois jours mois =
  
  if ((mois = 4 || mois = 6 || mois = 9 || mois = 10) && (jours < 31) && (jours > 0))
  then true
  else if ((jours < 32) && (jours > 0) && (mois = 1 || mois = 3 || mois = 5 || mois = 7 || mois = 8 || mois = 10 || mois = 12))
  then true
  else false;;

nbJoursDsMois 50 32;;(*bool = false*)
nbJoursDsMois 2 5;;(*bool = true*)

(*4. Write the code for the dateValide function, which uses the previous functions.*)
let rec dateValide jours mois annee =
	if(((mois > 0) && (mois < 13))&&if((mois=4|| mois=6|| mois=9 || mois=10)&&(jours<31)&&(jours>0))
		then true
	else if ((jours<32)&&(jours>0)&&(mois=1|| mois=3|| mois=5|| mois=7|| mois=8|| mois=10||mois=12))
		then true
	else false)
		then true
	else if ((annee mod 4 = 0) && (annee mod 100 <> 0) || (annee mod 400 = 0)&&(mois=2)&&(jours<30)= true)
		then true
	else false;;
	
dateValide 18 11 1999;;(*true*)
dateValide 18 13 1999;;(*false*)
dateValide 31 11 1999;;(*false*)
dateValide 29 2 1996;;(*true*)
dateValide 29 2 1999;;(*false*)

(*Exercise 3: Structures*)
(*In the preceding functions, define and use a type called "date", composed of the 3 pieces of information day, month and year.*)

type date = {jours: int; mois: int; annee: int};;
let dateValide date =
  if ((moisValide date.mois = true) && (nbJoursDsMois date.jours date.mois = true))
 		then true
  else if ((date.annee mod 4 = 0) && (date.annee mod 100 <> 0) = true || ((date.annee mod 400 = 0) && (date.mois = 2) && (date.jours < 30)) = true)
  	then true
  else false;;

dateValide {jours = 18; mois = 11; annee = 1999};;(*true*)
dateValide {jours = 18; mois = 13; annee = 1999};;(*false*)
dateValide {jours = 31; mois = 11; annee = 1999};;(*false*)
dateValide {jours = 29; mois = 2; annee = 1996};;(*true*)
dateValide {jours = 29; mois = 2; annee = 1999};;(*false*)
