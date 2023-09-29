/*******************************************************************************/
/* FILE:    alea.pl                                                            */
/* PURPOSE: Random Drawing                                                     */
/*                                                                             */
/* VERSION: 1                                                                  */
/* AUTHOR:  Antonin Tillier                                                    */
/*                                                                             */
/* PREDICATES:                                                                 */
/*   verif_alea(N)                                                             */
/*                                                                             */
/* ROLE :                                                                      */
/*   For a given number N as input, performs N random drawings and provides    */
/*   statistics on the coin tosses (heads or tails).                           */
/*                                                                             */
/* ARGUMENT:                                                                   */
/*    N (integer): number of tosses                                            */
/*                                                                             */
/*******************************************************************************/


/******************************************************************************************************/
/*My method relies on the computers system clock, which provides a different number every second.     */
/*This number is multiplied by the sine of the counter that keeps track of the tosses,                */
/*and then it is taken modulo 2 to return 0s and 1s.                                                  */
/*My program is as efficient as the standard random generator (Random) and adheres to the law of      */
/*large numbers, meaning that the head (0) and tail (1) outcomes balance out over time.               */
/******************************************************************************************************/
calcul(N,Ct,Ct1,Cf,Cf1,Cp):-
	N=Ct1,Cp is Ct1-Cf1,
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
	calcul(N,_, Ct,_,Cf,_).


/****************************************************/
/* Launching Draws with Technique Selection          */
/****************************************************/
verif_alea(N):-
	calcul(N,_, 0, _, 0, _).
