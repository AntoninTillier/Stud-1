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
  {bas = {tx = 20; ty = 200; tLargeur = 2; tHauteur = 100;
          tigeCol = Graphics.black};
   haut = {cx = 20; cy = 300; rayon = 6; teteCol = Graphics.red};
   choisi = true}


let dessiner allu =
  Graphics.set_color allu.bas.tigeCol;
  Graphics.fill_rect allu.bas.tx allu.bas.ty
    allu.bas.tLargeur allu.bas.tHauteur;
  Graphics.set_color allu.haut.teteCol;
  Graphics.fill_circle allu.haut.cx allu.haut.cy allu.haut.rayon

let () =

 Graphics.set_window_title "The stick game - Fort Boyard";

let v = Array.make nombrebaton allum in

for i = 0 to Array.length v - 1 do
    v.(i) <- {bas={tx=20 + i*(800/nombrebaton); ty=200; tLargeur=2;
                   tHauteur=100; tigeCol=Graphics.black};
              haut ={cx=(20 + 1) + i*(800/nombrebaton);cy=300; rayon=6;
                     teteCol=Graphics.red};
              choisi=true};
              dessiner v.(i);
  done;   
  ignore (Graphics.read_key ());;
  


Graphics.set_color Graphics.black;;

let rec zone (_ : unit) : unit =
let s = Graphics.wait_next_event [Graphics.Button_down]
in
Graphics.fill_rect s.Graphics.mouse_x s.Graphics.mouse_y 60 150;
 zone ();;
zone ();;

