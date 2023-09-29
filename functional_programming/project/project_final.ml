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
  Graphics.draw_string "Authors : Antonin Tillier";

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


print_endline "";

print_string "If you want to play in multiplayer mode, press 1, otherwise press 0 to play against the AI.";

let jeu = read_int() in

	if jeu = 1 then 
			begin
			(*Partie multijoueur*)
		while somme_tableau_entiers v > 1 do 
		
(*player 1*)
print_endline "";
print_string "Player 1 enter the number of sticks you wish to remove:";



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
      print_endline "Player 1: The stick has already been removed.";
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
		print_endline "Player 1, enter the first number of your sequence, which will be generated from left to right:";
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
			print_endline "Player 1, you've made a mistake. Enter a new number between 0 and (stick number -1).";
	end
done;	
	
if somme_tableau_entiers v = 1 then
	begin
	print_endline "Player 1, you win.";
	Graphics.fill_rect 0 70 800 440;
		 while 1>0 do
			 Graphics.set_color (Graphics.rgb (Random.int 256) (Random.int 256) (Random.int 256));
			 Graphics.moveto 80 220;
			 Graphics.set_text_size 35;
			 Graphics.draw_string "Well done Player 1, you've won!";
		 done;
	end
else
	print_endline "";

 
 (*player 2*)
print_endline "";
print_string "Player 2, enter the number of sticks you wish to remove:";

let verifchoisi m =
if v.(m).choisi =1 then
				print_string "Player 2, you remove the stick n° "
      else
      print_endline "Player 2. The stick has already been removed.";
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
	print_endline "Player 2, you're out of the picture.";
in



let affichebatonblanc nbre =
	let j = 
		print_endline "Player 2, enter the first number in your sequence, which will be generated from left to right:";
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
			print_endline "Player 2, you've made a mistake. Enter a new number between 0 and (stick number -1).";
	end
done;	

if somme_tableau_entiers v = 1 then
	begin
	print_endline "Player 2, you win";
	Graphics.fill_rect 0 70 800 440;
		 while 1>0 do
			 Graphics.set_color (Graphics.rgb (Random.int 256) (Random.int 256) (Random.int 256));
			 Graphics.moveto 80 220;
			 Graphics.set_text_size 35;
			 Graphics.draw_string "Well done Player 2, you've won!";
		 done;
	end
else
	print_endline "";

done;
print_endline "Finish";

end


	else 
	(* game against AI*)
	while somme_tableau_entiers v > 1 do 


let efface n = v.(n) <- {bas={tx=(800/nombrebaton)+n*(800/(nombrebaton+1)); ty=200; tLargeur=2;
                   tHauteur=100; tigeCol=Graphics.white};
              haut ={cx=(1+800/nombrebaton) + n*(800/(nombrebaton+1));cy=300; rayon=6;
                     teteCol=Graphics.white};
              choisi=0};
              dessiner v.(n);
in
 
(*player*)
print_endline "";
print_string "Player, enter the number of sticks you wish to remove:";

let verifchoisi m =
if v.(m).choisi =1 then
				print_string "Player, you remove the stick n° "
      else
      print_endline "Player. Error the stick has already been removed";
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
	print_endline "Player, you're out of the picture.";
in



let affichebatonblanc nbre =
	let j = 
		print_endline "Player, enter the first number of your sequence, which will be generated from left to right:";
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
			print_endline "Player, you've made a mistake. Enter a new number between 0 and (stick number -1).";
	end
done;	
	
if somme_tableau_entiers v = 1 then
	begin
	print_endline "Player, you've won";
	Graphics.fill_rect 0 70 800 440;
		 while 1>0 do
			 Graphics.set_color (Graphics.rgb (Random.int 256) (Random.int 256) (Random.int 256));
			 Graphics.moveto 150 220;
			 Graphics.set_text_size 35;
			 Graphics.draw_string "Congratulations, you've won!";
		 done;
	end
else
	print_endline "";
	

 
   (*AI*)
           
print_endline "";
print_string "AI :";

let verifchoisi m =
if v.(m).choisi =1 then
				print_string "AI removed the stick n° "
      else
      print_string "";  
in

let test n =
			verifchoisi (n);
	  	print_int (n);
	  	print_endline "";
		  efface (n);
		  
in

let boucle z nbre =
			for t=0 to nbre-1 do
				test (t+z);
		done;
in		

let affichebatonblanc nbre =
print_endline "AI has entered the first number of his sequence, which will be generated from left to right:";
	let y = ref 0 
	in while !y < nombrebaton do 
		if 	v.(!y).choisi =1 then
			begin
			boucle (!y) nbre;
			y:=nombrebaton;
			end
		else
			y:=!y+1;
	done;		
in
   
let boucledeux z nbre =
	let p = ref 0 in
		for t=0 to nbre-1 do 
		p:=	!p+(v.(z+t).choisi);
		done;
!p in

let affichebatonblancdeux nbre =
print_endline "AI has entered the last number in his sequence, which will be generated from left to right:";
	let y = ref 0 
	in while !y < (nombrebaton-nbre) do 
		if 	nbre = boucledeux (!y) nbre then
			begin
			boucle (!y) nbre;
			y:=nombrebaton;
			end
		else
			y:=!y+1;
	done;			
in
    
let win() =    
if somme_tableau_entiers v = 1 then
	begin
	print_endline "AI has won. Player, you lost";
	Graphics.fill_rect 0 70 800 440;
		 while 1>0 do
			 Graphics.set_color (Graphics.rgb (Random.int 256) (Random.int 256) (Random.int 256));
			 Graphics.moveto 150 220;
			 Graphics.set_text_size 35;
			 Graphics.draw_string "Too bad, you lost!";
		 done;	end
else
	print_endline "";
in
   
let n =ref 1 in
if nombreenlever < somme_tableau_entiers v-1 then
		begin
		let a =
		(Random.int 2)+1 in
			print_endline "";
			affichebatonblanc a;
		end
		
else
		begin
			print_endline "";
			affichebatonblancdeux (somme_tableau_entiers v-1);
			win();
		end

done;
print_endline "Finish";;
