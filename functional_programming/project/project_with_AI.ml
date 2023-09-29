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
  Graphics.draw_string "Authors :";

let v = Array.make nombrebaton allum in

for i = 0 to Array.length v-1 do
    v.(i) <- {bas={tx=(800/nombrebaton)+i*(800/(nombrebaton+1)); ty=200; tLargeur=2;
                   tHauteur=100; tigeCol=Graphics.black};
              haut ={cx=(1+800/nombrebaton) + i*(800/(nombrebaton+1));cy=300; rayon=6;
                     teteCol=Graphics.red};
              choisi=1};
              dessiner v.(i);
              Graphics.moveto ((800/nombrebaton)+i*(800/(nombrebaton+1))-5) 180;
 			 			  Graphics.draw_char (char_of_int (48 + (i /10)));
 			 						Graphics.draw_char (char_of_int (48 + i mod 10));
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

let verifchoisi m =
if v.(m).choisi =1 then
				print_string "Player 1 you remove the stick n° "
      else
      print_endline "Player 1 Error stick has already been removed";
in

let test n =
	if n < nombrebaton then
			begin
			verifchoisi (n);
	  	print_int (n);
	  	print_endline "";
		  efface (n);
		  end
	else 
	print_endline "Player 1, you're out of the picture.";
in



let affichebatonblanc nbre =
	let j = 
		print_endline "Player 1 enter the first number in your sequence, which will be generated from left to right:";
		read_int () in 
				for t=0 to nbre-1 do
					test (j+t)
					
				
				done;
in

let x = ref 0 
in while !x=0 do    
let nbre = read_int() in
	print_int nbre;
	print_endline "";
	if nbre >0 && nbre < (nombreenlever+1) then
	begin
	x:=1;
	affichebatonblanc nbre 
	end
	else
	begin
			print_endline "Player 1 you've made a mistake. Enter a new number between 0 and (stick number -1).";
	end
done;	
	
if somme_tableau_entiers v = 1 then
	begin
	print_endline "Player 1 you have won";
	exit 0;
	end
else
	print_endline "";

	
 
    
           
print_endline "";
print_string "AI enter the number of sticks you wish to remove:";

let verifchoisi m =
if v.(m).choisi =1 then
				print_string "AI you remove the stick n° "
      else
      print_endline "AI RJ";
in

let test n =
	if n < nombrebaton then
			begin
			verifchoisi (n);
	  	print_int (n);
	  	print_endline "";
		  efface (n);
		  end
	else 
	print_endline "AI leaves the picture.";
in



let affichebatonblanc nbre =
	let j = 
		print_endline "AI enter the first number in your sequence, which will be generated from left to right:";
		Random.self_init();
		(Random.int nombrebaton) in
				for t=0 to nbre-1 do
					test (j+t)
					
				
				done;
in
    
let x = ref 0 
in while !x=0 do    
let nbre = Random.self_init();
(Random.int nombreenlever)+1 in
	print_int nbre;
	print_endline "";
	if nbre >0 && nbre < (nombreenlever+1) then
	begin
	x:=1;
	affichebatonblanc nbre 
	end
	else
	begin
			print_endline "AI you were wrong.";
	end
done;	

if somme_tableau_entiers v = 1 then
	begin
	print_endline "AI you've won";
	exit 0;
	end
else
	print_endline "";

done;
print_endline "Finish";;



