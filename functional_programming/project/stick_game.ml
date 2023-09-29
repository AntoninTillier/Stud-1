#load "graphics.cma";;
open Graphics;;

type joueur = J | O;;
type baton = PrisJoueur | PrisOrdi | B;;
type plateau = baton list;;

let bas_baton = 200;;
let haut_baton = 200;;

open_graph " 1100x600";;
set_window_title "Stick Game";;

let rec creation_plat n i = if i < n then B :: (creation_plat n (i + 1)) else [];;
(* val creation_plat : int -> int -> baton list = <fun> *)

let rec affiche_plat plat i n = match plat with
		| [] -> ()
		| PrisJoueur :: t -> set_color (rgb 78 92 62); fill_rect ((1000 / n) * i + n) bas_baton (200 / n) haut_baton; set_color (rgb 82 71 62); fill_circle ((1000 / n) * i + n + (200 / (2 * n))) 400 (200 / n); affiche_plat t (i + 1) n
		| PrisOrdi :: t -> set_color (rgb 200 236 160); fill_rect ((1000 / n) * i + n) bas_baton (200 / n) haut_baton; set_color (rgb 82 71 62); fill_circle ((1000 / n) * i + n + (200 / (2 * n))) 400 (200 / n); affiche_plat t (i + 1) n
		| B :: t -> set_color (rgb 243 217 127); fill_rect ((1000 / n) * i + n) bas_baton (200 / n) haut_baton; set_color (rgb 218 19 37); fill_circle ((1000 / n) * i + n + (200 / (2 * n))) 400 (200 / n); affiche_plat t (i + 1) n;
				set_color black;
				moveto ((1000 / n) * i + n) 180;
				draw_char (char_of_int (48 + (i / 10)));
				draw_char (char_of_int (48 + i mod 10));;
(* val affiche_plat : baton list -> int -> int -> unit = <fun> *)

let rec efface plat joueur = match plat with
		| [] -> []
		| PrisJoueur :: t -> PrisJoueur :: (efface t joueur)
		| PrisOrdi :: t -> PrisOrdi :: (efface t joueur)
		| B :: t -> if joueur = J then PrisJoueur :: t else PrisOrdi :: t;;
(* val efface : baton list -> joueur -> baton list = <fun> *)

let affiche_joueur joueur = set_color black; match joueur with
		| J -> set_color white; draw_rect 590 80 185 60; set_color (rgb 78 92 62); draw_rect 360 80 145 60
		| O -> set_color white; draw_rect 360 80 145 60; set_color (rgb 200 236 160); draw_rect 590 80 185 60;;
(* val affiche_joueur : joueur -> unit = <fun> *)

(* win function for player *)
let win_J () =
	for i = 0 to 10000 do
		set_color (Graphics.rgb (Random.int 256) (Random.int 256) (Random.int 256));
		moveto 350 500;
		set_text_size 20;
		draw_string "	Congratulations to Player 1!";
	done;
	set_color black;
	moveto 240 450; set_text_size 15; draw_string "To return to the home screen, press a key and click on the screen.";
	set_text_size 12; Graphics.moveto 750 25; draw_string "Press q to exit the game.";
	if (read_key () == 'q') then close_graph ();;
(* val win_J : unit -> unit = <fun> *)

(* win function for player 2 *)
let win_J2 () =
	for i = 0 to 10000 do
		set_color (Graphics.rgb (Random.int 256) (Random.int 256) (Random.int 256));
		moveto 350 500;
		set_text_size 20;
		draw_string "	Congratulations to player 2!";
	done;
	set_color black;
	moveto 240 450; set_text_size 15; draw_string "To return to the home screen, press a key and click on the screen.";
	set_text_size 12; Graphics.moveto 750 25; draw_string "Press q to exit the game.";
	if (read_key () == 'q') then close_graph ();;
(* val win_J2 : unit -> unit = <fun> *)

(* function that allows both player and computer to play *)
let rec jeu plat i n p joueur ia coup = affiche_plat plat i n; affiche_joueur joueur;
	match plat with
		| [] ->
				if joueur = J then win_J2 ()
				else win_J ()
		| PrisJoueur :: t -> jeu t (i + 1) n p joueur ia coup
		| PrisOrdi :: t -> jeu t (i + 1) n p joueur ia coup
		| B :: t -> if ia && joueur = O && coup >= 1 && coup <= p then
					if (List.length plat) <= 4 && (List.length plat) > 1 then jeu (efface plat O) i n p O ia (coup + 1)
					else if (((List.length plat) - 1) mod (p + 1)) != 0 then jeu (efface plat O) i n p O ia (coup + 1)
					else if coup = 1 then jeu (efface plat O) i n p O ia 4
					else jeu plat i n p J ia 1

				else let s = Graphics.wait_next_event [Graphics.Button_down] in
						if (s.mouse_x >= ((1000 / n) * i + n)) && (s.mouse_x <= (((1000 / n) * i + n + (200 / n)))) && (s.mouse_y <= bas_baton + haut_baton) && (s.mouse_y >= bas_baton) && (coup <= p)
						then jeu (efface plat joueur) i n p joueur ia (coup + 1)
						else if joueur = J && coup != 1 then jeu plat i n p O ia 1
						else if coup != 1 then jeu plat i n p J ia 1
						else jeu plat i n p joueur ia 1;;
(* val jeu : baton list -> int -> int -> int -> joueur -> bool -> int -> unit =
  <fun> *)

(* starts the game *)
let game plat n p joueur ia = set_color white;
	fill_rect 0 0 1100 600; set_color (rgb 30 37 46);
	moveto 380 100;
	draw_string " Player 1            			Player 2/IA";
	jeu plat 1 n p joueur ia 1;;
(* val game : baton list -> int -> int -> joueur -> bool -> unit = <fun> *)

(* Permet de choisir le nombre b�ton � enlever par tour *)
let rec choix_nb_baton_enlever plat n joueur ia = clear_graph (); set_text_size 20;
	moveto 250 420; set_color blue; draw_string "How many sticks do you want to remove per round at most?"; set_color (rgb 191 196 201);
	fill_rect 300 300 150 50; fill_rect 500 300 150 50; fill_rect 700 300 150 50;
	fill_rect 400 200 150 50; fill_rect 600 200 150 50;
	set_text_size 13; set_color (rgb 30 37 46); moveto 330 315; draw_string " 3 sticks";
	moveto 530 315; draw_string " 6 sticks"; moveto 730 315; draw_string " 9 sticks";
	moveto 430 215; draw_string " 12 sticks"; moveto 630 215; draw_string " 15 sticks";
	let s = wait_next_event [Graphics.Button_down] in
		if s.mouse_x < 450 && s.mouse_x > 300 && s.mouse_y < 350 && s.mouse_y > 300
		then game plat n 3 joueur ia
		else if s.mouse_x < 650 && s.mouse_x > 500 && s.mouse_y < 350 && s.mouse_y > 300
		then game plat n 6 joueur ia
		else if s.mouse_x < 850 && s.mouse_x > 700 && s.mouse_y < 350 && s.mouse_y > 300
		then game plat n 9 joueur ia
		else if s.mouse_x < 550 && s.mouse_x > 400 && s.mouse_y < 250 && s.mouse_y > 200
		then game plat n 12 joueur ia
		else if s.mouse_x < 750 && s.mouse_x > 600 && s.mouse_y < 250 && s.mouse_y > 200
		then game plat n 15 joueur ia
		else choix_nb_baton_enlever plat n joueur ia;;
(* val choix_nb_baton_enlever : baton list -> int -> joueur -> bool -> unit =
  <fun> *)

(* Choose the number of sticks for the game *)
let rec choix_nb_baton joueur ia = clear_graph (); set_text_size 20;
	moveto 330 420; set_color blue; draw_string "How many sticks do you want to play with?"; set_color (rgb 191 196 201);
	fill_rect 300 300 150 50; fill_rect 300 200 150 50;
	fill_rect 500 300 150 50; fill_rect 500 200 150 50;
	fill_rect 700 300 150 50; fill_rect 700 200 150 50; set_text_size 13; set_color (rgb 30 37 46);
	moveto 330 315; draw_string " 10 sticks"; moveto 330 215; draw_string " 20 sticks";
	moveto 530 315; draw_string " 30 sticks"; moveto 530 215; draw_string " 40 sticks";
	moveto 730 315; draw_string " 50 sticks"; moveto 730 215; draw_string " 60 sticks";
	let s = wait_next_event [Graphics.Button_down] in
		if s.mouse_x < 450 && s.mouse_x > 300 && s.mouse_y < 350 && s.mouse_y > 300
		then choix_nb_baton_enlever (creation_plat 10 0) 10 joueur ia
		else if s.mouse_x < 450 && s.mouse_x > 300 && s.mouse_y < 250 && s.mouse_y > 200
		then choix_nb_baton_enlever (creation_plat 20 0) 20 joueur ia
		else if s.mouse_x < 650 && s.mouse_x > 500 && s.mouse_y < 350 && s.mouse_y > 300
		then choix_nb_baton_enlever (creation_plat 30 0) 30 joueur ia
		else if s.mouse_x < 650 && s.mouse_x > 500 && s.mouse_y < 250 && s.mouse_y > 200
		then choix_nb_baton_enlever (creation_plat 40 0) 40 joueur ia
		else if s.mouse_x < 850 && s.mouse_x > 700 && s.mouse_y < 350 && s.mouse_y > 300
		then choix_nb_baton_enlever (creation_plat 50 0) 50 joueur ia
		else if s.mouse_x < 850 && s.mouse_x > 700 && s.mouse_y < 250 && s.mouse_y > 200
		then choix_nb_baton_enlever (creation_plat 60 0) 60 joueur ia
		else choix_nb_baton joueur ia;;
(* val choix_nb_baton : joueur -> bool -> unit = <fun> *)

(* Choose your game mode *)
let rec debut_jeu joueur = set_text_size 15; set_color blue; moveto 380 550; draw_string "Welcome to stick game ";
	set_color black; moveto 25 25; set_text_size 12; draw_string "Authors : Antonin Tillier";
	set_text_size 13; set_color (rgb 245 243 239); fill_rect 290 165 550 355; set_color black;
	moveto 250 500; draw_string "		The aim of the game is to remove a definite number of sticks per turn.";
	moveto 250 470; draw_string "				The player who takes the last stick loses the game.";
	moveto 250 380; draw_string "					In this game, the player clicks on the stick";
	moveto 250 350; draw_string "				he wants to remove from left to right.";
	moveto 250 320; draw_string "				The stick will be colored in the player's color.";
	moveto 250 290; draw_string "				After your turn, click on the screen.";
	moveto 250 230; draw_string "				You can play with two players, or against the AI.";
	moveto 280 170; draw_string "		Make your choice by clicking on the chosen mode:"; set_color (rgb 191 196 201); fill_rect 280 70 205 65; fill_rect 560 70 285 65; set_color (rgb 30 37 46);
	moveto 300 90; draw_string "  Two-player mode           		  Single player vs. AI mode";
	let n = wait_next_event [Graphics.Button_down] in
		if (n.mouse_x >= 280) && (n.mouse_x <= 485) &&
			(n.mouse_y >= 70) && (n.mouse_y <= 135) then choix_nb_baton joueur false
		else if (n.mouse_x >= 560) && (n.mouse_x <= 845) &&
			(n.mouse_y >= 70) && (n.mouse_y <= 135) then choix_nb_baton joueur true
		else debut_jeu joueur;;
(* val debut_jeu : joueur -> unit = <fun> *)

(* Allows you to play indefinitely *)
let main () =
	while true do
		debut_jeu J;
		let n = wait_next_event [Graphics.Button_down] in
			if (n.mouse_x >= 0) && (n.mouse_x <= 1100) && (n.mouse_y >= 0) && (n.mouse_y <= 600) then
				clear_graph ();
	done;;

main ();;
(* val main : unit -> unit = <fun> *)