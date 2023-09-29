/*******************************************************************************/
/* FILE:    alea_vs_random.pl                                                  */
/* PURPOSE: Random drawing                                                     */
/*                                                                             */
/* VERSION: 2                                                                  */
/* AUTHOR:  Antonin Tillier                                                    */
/*                                                                             */
/* PREDICATES:                                                                 */
/*   verif_alea(N)                                                             */
/*                                                                             */
/* ROLE:                                                                       */
/*   For a given number N as input, performs N random drawings and provides    */
/*   statistics on the coin tosses (heads or tails).                           */
/*                                                                             */
/* ARGUMENT:                                                                   */
/*    N (integer): number of tosses                                            */
/*                                                                             */
/*******************************************************************************/


/*******************************/
/* 1 Random Method             */
/*******************************/
calcul(1,N,Ct,Ct1,Cf,Cf1,Cp):-
	N=Ct1,Cp is Ct1-Cf1,
	writeln('My Method'),
	write('Head tosses = '),writeln(Cp),
	write('Tail tosses = '), writeln(Cf1),!;
% /Using the system clock to generate a random number and
% the sine function.
	get_time(B),
	A is integer(sin(Ct1)*B),%/writeln(A),
	M is A mod(2),
	Ct is Ct1 + 1,
	Cf is Cf1 + M,
	Cp is Ct - Cf,
	calcul(1,N,_, Ct,_,Cf,_).


/*******************************/
/* 2 Prolog Random Method      */
/*******************************/
calcul(2,N,Ct,Ct1,Cf,Cf1,Cp):-
	N=Ct1,Cp is Ct1-Cf1,
	writeln('Vs random'),
	write('Head tosses = '),writeln(Cp),
	write('Tail tosses = '), write(Cf1),!;
	M is random(2),	Ct is Ct1 + 1,
	Cf is Cf1 + M,
	Cp is Ct - Cf,
	calcul(2,N,_, Ct,_,Cf,_).


/****************************************************/
/*  Launching Draws with Technique Selection        */
/****************************************************/
verif_alea(N):-
	calcul(1,N,_, 0, _, 0, _),
	calcul(2,N,_, 0, _, 0, _).

