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
  mutable  choisi : bool
}

let allum=
  {bas = {tx = 0; ty = 200; tLargeur = 2; tHauteur = 100;
          tigeCol = Graphics.black};
   haut = {cx = 1; cy = 300; rayon = 6; teteCol = Graphics.red};
   choisi = true}


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
              choisi=true};
              dessiner v.(i);
              Graphics.moveto ((800/nombrebaton)+i*(800/(nombrebaton+1))-5) 180;
 			 			 Graphics.draw_string "x";
                done; 
  
while nombrebaton > 1 do nombrebaton=nombrebaton-1;

let j = 


print_string "Player 1 enter your number :";
read_int () in   
v.(j) <- {bas={tx=(800/nombrebaton)+j*(800/(nombrebaton+1)); ty=200; tLargeur=2;
                   tHauteur=100; tigeCol=Graphics.white};
              haut ={cx=(1+800/nombrebaton) + j*(800/(nombrebaton+1));cy=300; rayon=6;
                     teteCol=Graphics.white};
              choisi=true};
              dessiner v.(j);
              
             
let jj = 

print_string "Player 2 enter your number:";
read_int () in   
v.(jj) <- {bas={tx=(800/nombrebaton)+jj*(800/(nombrebaton+1)); ty=200; tLargeur=2;
                   tHauteur=100; tigeCol=Graphics.white};
              haut ={cx=(1+800/nombrebaton) + jj*(800/(nombrebaton+1));cy=300; rayon=6;
                     teteCol=Graphics.white};
              choisi=true};
              dessiner v.(jj);              
   done;;
    
    