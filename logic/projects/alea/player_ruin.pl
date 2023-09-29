/*******************************************************************************/
/* FILE:    player_ruin.pl                                                     */
/* PURPOSE: Player Ruin                                                        */
/*                                                                             */
/* VERSION: 3.4                                                                */
/* AUTHOR:  Antonin Tillier                                                    */
/*                                                                             */
/* PREDICATES:                                                                 */
/*                                                                             */
/*   game(Initial_Bet, Strategy, Value)                                        */
/*   Global calling predicate that determines, in how many games, a player     */
/*   with a given initial bet becomes bankrupt by following a given strategy.  */
/*   Initial_Bet (integer, +): initial sum available at the start of the game  */
/*   Strategy (integer, +): type of strategy (fixed=2, variable=1)             */
/*   Value (integer, +): percentage of available bet or fixed sum              */
/*                                                                             */
/*******************************************************************************/


/****************/
/* Strategy 2    */
/****************/

/* Calculation of winnings or losses */
calcul2(0,Mise_init,Valeur,Res):-
	Res is Mise_init - Valeur.
calcul2(1,Mise_init,Valeur,Res):-
	Res is Mise_init + Valeur.

%/ Calling the defined strategy
strategie2(Mise_init,Valeur,Res,Compteurt,Compteurg,Ct,Cg,Cp):-
%/ Termination condition and display of requested information
	Mise_init=0,Cp is Ct - Cg,
        write('Bankruptcy after '),write(Ct),writeln(' games'),
	write('Winning games = '),writeln(Cg),
	write('Losing games = '),write(Cp),!;

%/Random generator N = 0 or 1
	N is random(2),%/write('    N='),writeln(N),
	Compteurt is Ct + 1,Compteurg is Cg + N,
%/Calling calcul2
	calcul2(N,Mise_init, Valeur,Res),%/ write('new bet '),writeln(Res),
%/ Loop until the initial bet reaches 0
	strategie2(Res,Valeur,_,_,_,Compteurt,Compteurg,_).




/****************/
/* Strategie 1  */
/****************/

/* Calculation of winnings/losses with the new bet rounded down (floor) */
calcul1(0,Mise_init,Valeur,Res):-
	 Res is floor(Mise_init*(1- Valeur/100)).
calcul1(1,Mise_init,Valeur,Res):-
	Res is floor(Mise_init*(1 + Valeur/100)).

%/ Call to the defined strategy
strategie1(Mise_init,Valeur,Res,Compteurt,Compteurg,Ct,Cg,Cp):-
% Termination condition and display of requested information
	Mise_init=0,Cp is Ct - Cg,
            write('Bankruptcy after '),write(Ct),writeln(' games'),
    	write('Winning games = '),writeln(Cg),
    	write('Losing games = '),write(Cp),!;

%/Random generator N = 0 or 1
	N is random(2),%/write('    N='),writeln(N),
	Compteurt is Ct + 1,Compteurg is Cg + N,
%/Calling calcul1
	calcul1(N,Mise_init,Valeur,Res),%/write('new bet '),writeln(Res),
%/ Loop until the initial bet reaches 0
	strategie1(Res,Valeur,_,_,_,Compteurt,Compteurg,_).


/*************************************/
/* Testing Startup Parameters        */
/*************************************/
test(Mise_init,1,Valeur,_,_,_,_,_):-
	write('Initial bet = ' ),writeln(Mise_init),
	write('Variable bet strategy: '), write(Valeur),writeln('%'),
	Valeur>100,
        writeln('Your betting percentage is too high.'),
	write('Choose a number between 1 and 100.'),!;

	Valeur=0,
	writeln('You cannot bet 0%.'),
	write('Choose a number between 1 and 100.'),!;

	strategie1(Mise_init,Valeur,_,_,_,0,0,_).


test(Mise_init,2,Valeur,_,_,_,_,_):-
	write('Initial bet = ' ),writeln(Mise_init),
	write('Variable bet strategy: '), writeln(Valeur),
	Mise_init < Valeur, writeln(Mise_init),writeln(Valeur),
        writeln('You cannot bet that much.'),
	write('Your initial bet is too low'),!;

	Valeur=0,
	writeln('You cannot bet 0.'),
	write('Choose a number > 0.'),!;

	strategie2(Mise_init,Valeur,_,_,_,0,0,_).




/*******************/
/* Game Launching   */
/*******************/
jeu(Mise_init,Strategie,Valeur):-
	test(Mise_init,Strategie,Valeur,_,_,_,_,_).


/******************************* explain prolog file *******************************/
% The gambler's ruin theorem is used to convey the fact that there is no strategy to win for sure.
% A player with limited resources will always end up ruined when playing without a time limit.
% With the 'jeu/3' predicate program, I experimented with and verified this theorem.
% Using strategy 2, I tested a fixed bet. I lose regardless of the scenario. Here are some examples with 'jeu(Mise_init,Strategie,Valeur)'
% The player must bet an amount between €1 and their initial betting amount; otherwise, the program loops.
% Regardless of the initial amount, as long as you play without a time limit, ruin is inevitable.
% With strategy 1, I tested a variable bet, and I also lose regardless of the scenario. Here are some examples with 'jeu(Mise_init,Strategie,Valeur)'
% The player must bet a percentage of their initial betting amount between 1% and 100%; otherwise, the program loops.
% Regardless of the initial amount, as long as you play without a time limit, ruin is also inevitable.
% Strategy 2 with a fixed bet allows you to play longer if the initial amount is substantial compared to strategy 1 with variable betting.
% This theorem is what makes casinos profitable because the player's hope of winning is always present. The desire to play is not rational.
/***********************************************************************************/