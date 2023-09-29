#load "graphics.cma" ;;

Graphics.open_graph "800x440";;

let nombrebaton = 10;;
let nombreenlever = 3;;


type tete =
{
mutable cx : int;
mutable cy : int;
mutable rayon : int;
mutable teteCol : Graphics.color
}

type tige = {
  mutable tx       : int;
  mutable ty       : int;
  mutable tLargeur : int;
  mutable tHauteur : int;
  mutable tigeCol  : Graphics.color
}
 
type allumette = {
  mutable haut   : tete;
  mutable bas    : tige;
  mutable  choisi : int
}

let allum=
  {bas = {tx = 0; ty = 200; tLargeur = 2; tHauteur = 100;
          tigeCol = Graphics.black};
   haut = {cx = 1; cy = 300; rayon = 6; teteCol = Graphics.red};
   choisi = 1}


let dessiner allu =
  Graphics.set_color allu.bas.tigeCol;
  Graphics.fill_rect allu.bas.tx allu.bas.ty
    allu.bas.tLargeur allu.bas.tHauteur;
  Graphics.set_color allu.haut.teteCol;
  Graphics.fill_circle allu.haut.cx allu.haut.cy allu.haut.rayon

let () =

 Graphics.set_window_title "The stick game - Fort Boyard";
  Graphics.rgb (150) (0) (150);
  Graphics.moveto 50 50;
  Graphics.draw_string "Choose a number between 0 and your stick number -1.";

let v = Array.make nombrebaton allum in

for i = 0 to Array.length v-1 do
    v.(i) <- {bas={tx=(800/nombrebaton)+i*(800/(nombrebaton+1)); ty=200; tLargeur=2;
                   tHauteur=100; tigeCol=Graphics.black};
              haut ={cx=(1+800/nombrebaton) + i*(800/(nombrebaton+1));cy=300; rayon=6;
                     teteCol=Graphics.red};
              choisi=1};
              dessiner v.(i);
              Graphics.moveto ((800/nombrebaton)+i*(800/(nombrebaton+1))-5) 180;
 			 			 Graphics.draw_string "x";
                done; 
  
  
let somme_tableau_entiers tab =
let somme = ref 0 in
let taille = Array.length tab in 
for i = 0 to pred taille do
somme := !somme + tab.(i).choisi; 
done;
!somme in



while somme_tableau_entiers v > 1 do 


print_endline "";
print_string "Player 1 enters the number of sticks you wish to remove:";



let efface n = v.(n) <- {bas={tx=(800/nombrebaton)+n*(800/(nombrebaton+1)); ty=200; tLargeur=2;
                   tHauteur=100; tigeCol=Graphics.white};
              haut ={cx=(1+800/nombrebaton) + n*(800/(nombrebaton+1));cy=300; rayon=6;
                     teteCol=Graphics.white};
              choisi=0};
              dessiner v.(n);
in

let verifchoisi n =
if v.(n).choisi =1 then
				print_string "Player 1 you removed stick n° "
      else
      print_endline "Player 1 Error stick already removed";
in

let affichebatonblanc nbre =
let tab =	Array.make nbre nombrebaton in
	let j = 
		print_endline "Player 1 enter the first number of your sequence which will be generated from left to right:";
		read_int () in 
	for t=0 to nbre-1 do
			tab.(t) <- j+t;
			verifchoisi (j+t);
			print_int tab.(t);
			print_endline "";
			efface tab.(t);
			done;
in
    
let nbre = read_int() in
	print_int nbre;
	print_endline "";
	if nbre >0 && nbre <4 then
		affichebatonblanc nbre 
	else
			print_endline "Player 1 you have made a mistake. Skip your turn";

if somme_tableau_entiers v = 1 then
	begin
	print_endline "Player 1 you have won";
	exit 0;
	end
else
	print_endline "";

	
 
    
           
print_endline "";
print_string "Player 2 enters the number of sticks you wish to remove:";

let verifchoisi n =
if v.(n).choisi =1 then
				print_string "Player 2 you removed the stick n° "
      else
      print_endline "Player 2 Error stick already removed";
in

let affichebatonblanc nbre =
let tab =	Array.make nbre nombrebaton in
	let j = 
		print_endline "Player 2 enter the first number of your sequence which will be generated from left to right:";
		read_int () in 
	for t=0 to nbre-1 do
			tab.(t) <- j+t;
			verifchoisi (j+t);
			print_int tab.(t);
			print_endline "";
			efface tab.(t);
			done;
in
    
let nbre = read_int() in
	print_int nbre;
	print_endline "";
	if nbre >0 && nbre <4 then
		affichebatonblanc nbre 
	else
			print_endline "Player 2 you have made a mistake. Skip your turn";

done;
print_endline "finish";;