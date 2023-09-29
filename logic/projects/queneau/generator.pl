/**************************************************************************************/
/* FILE:     generator.pl                                                             */
/* PURPOSE:  Generate a poem                                                          */
/*                                                                                    */
/* VERSION:  12                                                                       */
/* AUTHOR:   Antonin Tillier                                                          */
/*                                                                                    */
/* poeme(poem_number, verse_number, verse)                                            */
/*                                                                                    */
/* PREDICATES:                                                                        */
/*   tout_poeme(Poeme)                                                                */
/*   tout_poeme_formatte()                                                            */
/*   poeme_page([,,,,,,,,,,,,,], Poeme)                                               */
/**************************************************************************************/


/************************************** Facts *****************************************/

poeme(1,1,[le,roi,de,la,pampa,retourne,sa,chemise]).
poeme(1,2,[pour,la,mettre,a,secher,aux,cornes,des,taureaux]).
poeme(1,3,[le,cornedbif,en,boite,empeste,la,remise]).
poeme(1,4,[et,fermentent,de,meme,et,les,cuirs,et,les,peaux]).
poeme(1,5,[je,me,souviens,encore,de,cette,heure,exquise]).
poeme(1,6,[les,gauchos,dans,la,plaine,agitaient,leurs,drapeaux]).
poeme(1,7,[nous,avions,aussi,froids,que,nus,sur,la,banquise]).
poeme(1,8,[lorsque,pur,nous,distraire,y,plantions,nos,treteaux]).
poeme(1,9,[du,pole,a,rosario,fait,une,belle,trotte]).
poeme(1,10,[aventures,on,eut,qui,s,y,pique,s,y,frotte]).
poeme(1,11,[lorsqu,on,boit,du,mate,l,on,devient,argentin]).
poeme(1,12,[l,amerique,du,sud,seduit,les,equivoques]).
poeme(1,13,[exaltent,l,espagnol,les,oreilles,baroques]).
poeme(1,14,[si,la,cloche,se,tait,et,son,terlintintin]).

poeme(2,1,[du,jeune,avantageux,la,nymphe,etait,eprise]).
poeme(2,2,[pour,de,fin,fond,du,nez,exciter,les,arceaux]).
poeme(2,3,[la,decouverte,alors,voila,qui,traumatise]).
poeme(2,4,[et,tout,vient,signifier,la,fin,des,haricots]).
poeme(2,5,[il,deplore,il,deplore,une,telle,mainmise]).
poeme(2,6,[le,vulgaire,s,entete,a,vouloir,des,vers,beau]).
poeme(2,7,[l,un,et,l,autre,ont,raison,non,la,foule,imprecise]).
poeme(2,8,[a,tous,n,est,pas,donne,d,aimer,les,chocs,verbaux]).
poeme(2,9,[le,poete,inspire,n,est,point,une,polyglotte]).
poeme(2,10,[le,lache,peut,arguer,de,sa,mine,palotte]).
poeme(2,11,[les,croque-morts,sont,la,pour,se,mettre,au,turbin]).
poeme(2,12,[ne,fallait,pas,si,loin,agiter,les,breloques]).
poeme(2,13,[grignoter,des,bretzels,distrait,bien,des,colloques]).
poeme(2,14,[le,mammifere,est,roi,nous,sommes,son,cousin]).

poeme(3,1,[le,marbre,pour,l,acide,est,une,friandise]).
poeme(3,2,[se,faire,il,pourrait,bien,que,ce,soit,des,jumeaux]).
poeme(3,3,[le,chauffeur,indigene,attendait,dans,la,brise]).
poeme(3,4,[il,donne,a,la,tribu,des,cris,aux,sens,nouveaux]).
poeme(3,5,[et,pourtant,c,etait,lui,le,frere,de,feintise]).
poeme(3,6,[que,n,a,pas,devore,la,horde,des,mulots]).
poeme(3,7,[nous,regrettions,un,peu,ce,tas,de,marchandise]).
poeme(3,8,[les,grecs,et,les,romains,en,vain,cherchent,leurs,mots]).
poeme(3,9,[le,loup,est,amateur,de,coq,et,de,cocotte]).
poeme(3,10,[on,comptait,les,esprits,aceres,a,la,hotte]).
poeme(3,11,[lorsqu,on,boit,du,mate,l,on,devient,argentin]).
poeme(3,12,[sa,sculpture,est,illustre,et,dans,le,fond,des,coques]).
poeme(3,13,[on,transporte,et,le, marbre,et,debris,et,defroques]).
poeme(3,14,[si,la,cloche,se,tait,et,son,terlintintin]).

poeme(4,1,[quand,l,un,avecque,l,autre,aussitot,sympathise]).
poeme(4,2,[depuis,que,lord,elgin,negligea,ses,naseaux]).
poeme(4,3,[la,critique,lucide,apercoit,ce,qu,il,vise]).
poeme(4,4,[il,n,avait,droit,qu,a,une,et,le,jour,des,rameaux]).
poeme(4,5,[l,un,et,l,autre,a,raison,non,la,foule,insoumise]).
poeme(4,6,[que,n,a,pas,devore,la,horde,des,mulots]).
poeme(4,7,[nous,avions,aussi,froids,que,nus,sur,la,banquise]).
poeme(4,8,[les,grecs,et,les,romains,en,vain,cherchent,leurs,mots]).
poeme(4,9,[l,esprit,souffle,et,resouffle,au-dessus,de,la,botte,comme]).
poeme(4,10,[a,chandernagor,le,manant,sent,la,crotte]).
poeme(4,11,[il,voudra,retrouver,le,germe,adulterin]).
poeme(4,12,[ne,fallait,pas,si,loin,agiter,ses,breloques]).
poeme(4,13,[on,mettait,sans,facon,ses,plus,infectes,loques]).
poeme(4,14,[le,beaune,et,le,chianti,sont-ils,le,meme,vin]).

/**************************************************************************************/

/***************************** random generator *********************************/

creation_du_poeme_aleatoire(0,Poeme,Liste1):- set_prolog_flag(answer_write_options,[max_depth(0)]), Poeme=Liste1, writeln(" ").
/*termination condition to allow implementing the list in the poem*/

creation_du_poeme_aleatoire(0,Poeme,_):- tout_poeme(Poeme).
/*condition to allow the program to be restarted indefinitely by backtracking*/

creation_du_poeme_aleatoire(Compt,Poeme,Liste):- Numero_vers is (15-Compt),random_between(1,4,Numero_poeme),poeme(Numero_poeme,Numero_vers,Vers), append(Liste,[Vers],Liste1), Compt1 is Compt-1, creation_du_poeme_aleatoire(Compt1,Poeme, Liste1).
/* Predicate that creates a 14-line poem using Compt. We use randomization for generating random poems and Compt for the lines. These 14 lines are added to a list called Liste1 using the append predicate, which combines two lists into one.*/
/*We stop doing this when we reach 0 in Compt because we decrease Compt by 1 to reach the termination condition. */


tout_poeme(Poeme):- creation_du_poeme_aleatoire(14,Poeme,[]).
/* tout_poeme/1 creates all possible poems by backtracking */

/**************************************************************************************/

/******************************* formatted generator *******************************/
reecrire([]).
reecrire([Poeme | Vers]) :- write(Poeme), write(" "),reecrire(Vers).
/* This predicate writes the verses in a readable manner using [Head|Tail], which allows retrieving elements from a list. */

ecrire([]). 
ecrire([Poeme | Vers]) :- reecrire(Poeme), writeln(" "), ecrire(Vers).
/* This predicate allows unwrapping the double list using [Head | Tail], which retrieves the elements of the list, skipping a line for each verse. */

tout_poeme_formatte():- tout_poeme(Poeme), ecrire(Poeme).
/* tout_poeme_formatte/0 creates all possible poems by backtracking in a readable manner */

/**************************************************************************************/

/******************************* directed generator **********************************/

test_interval(Z):- [A|AA]=Z,borne(A), test_interval(AA).
test_interval([]).
borne(X):-X<1,write('Please retry with numbers between 1 and 4.'),!,fail.
borne(X):-X>4,write('Please retry with numbers between 1 and 4.'),!,fail.
borne(X):- X>=1,X=<4.
/* predicate for managing user errors */


creation_du_poeme_dirigee(15,_,Poeme,Liste_temp1):- set_prolog_flag(answer_write_options,[max_depth(0)]), Poeme=Liste_temp1, writeln(" ").
/* termination condition to allow implementing the list in the poem */


creation_du_poeme_dirigee(Compt,Liste,Poeme,Liste_temp):- [Tete|Queue]=Liste, poeme(Tete,Compt,Vers), append(Liste_temp,[Vers],Liste_temp1), Compt1 is Compt+1,creation_du_poeme_dirigee(Compt1,Queue,Poeme,Liste_temp1).
creation_du_poeme_dirigee(15,_,_,_).
/* Predicate that creates a 14-line poem using Compt. These 14 lines are added to a temporary list called Liste_temp1 using the append predicate, which combines two lists into one. We stop doing this when we reach 15 in Compt because we increment Compt by 1 to reach the termination condition. */

poeme_page([A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14],Poeme):- test_interval([A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14]),creation_du_poeme_dirigee(1,[A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14],Poeme,[]).
/*poeme_page/2 allows the user to create a desired poem and handles input errors*/

/**************************************************************************************/

/******************************* explain prolog file *******************************/
% With tout_poeme/1, I generate a new poem by pressing the 'r' key randomly and infinitely. The program stops when I press the 'enter' key.
% Each poem is created by randomly selecting 14 lines from the 4 poems. For example, the first line is chosen from the first lines of the 4 poems, and so on until the 14th line.
% With the tout_poeme_formatte/0 predicate, I use the tout_poeme/1 predicate to create a poem, and then I format it using a write predicate and a rewrite predicate to remove the square brackets and commas from the double list.
% The ecrire/1 predicate extracts the line from the list. This line then passes through a reecrire/1 predicate that formats it by removing the commas and square brackets.
% The poeme_page/2 predicate allows me to create a single poem with the lines chosen from the desired poems.
% The program checks if the values entered in the list are between 1 and 4.
/**************************************************************************************/