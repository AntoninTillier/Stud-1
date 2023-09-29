#load "graphics.cma" ;;

Graphics.open_graph "800x440";;

let nombrebaton = 10;;



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

print_endline "Player 1 enter the number of sticks you wish to remove:";
let nbre = read_int () in
for t=1 to nbre do

	let j = 
		print_endline "Player 1 enter your number :";
		read_int () in   
		v.(j) <- {bas={tx=(800/nombrebaton)+j*(800/(nombrebaton+1)); ty=200; tLargeur=2;
                   tHauteur=100; tigeCol=Graphics.white};
              haut ={cx=(1+800/nombrebaton) + j*(800/(nombrebaton+1));cy=300; rayon=6;
                     teteCol=Graphics.white};
              choisi=0};
              dessiner v.(j);
done;             

print_endline "Player 2 enter the number of sticks you wish to remove:";
let nbree = read_int () in
for tt=1 to nbree do           
	let jj = 
		print_endline "Player 2 enter your number :";
		read_int () in   
		v.(jj) <- {bas={tx=(800/nombrebaton)+jj*(800/(nombrebaton+1)); ty=200; tLargeur=2;
                   tHauteur=100; tigeCol=Graphics.white};
              haut ={cx=(1+800/nombrebaton) + jj*(800/(nombrebaton+1));cy=300; rayon=6;
                     teteCol=Graphics.white};
              choisi=0};
              dessiner v.(jj);                          
done;

done;
print_endline "finish";;
