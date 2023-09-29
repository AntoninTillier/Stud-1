**Logic : PROLOG project Experimental validation of the player's ruin law!**

1. **Introduction: competitive project** 

The aim of this project is to have you develop a Prolog program involving recursive programming, which we've seen in class can create looping problems if badly managed. This project will therefore involve applying the skills you've acquired on the subject over the last few weeks.

Each project is carried out individually. It will be graded on a scale of 20 and will count towards the EU average for ¼ of the final grade (if the student has composed in all the tests).

This project is also competitive in two ways:
- **Competition against the teacher** – It is requested that you create the most efficient program possible for an unknown test case. A part of the grade will depend on the system's efficiency compared to the teacher's
- **Student competition** (Bonus) – The first students to successfully submit a correct project will receive a bonus on their module grade

2. **Subject: Gambler's Ruin** 

If there is one viewpoint often shared among the French, it is the belief that they pay too much in taxes. However, when we analyze data from the Ministry of Finance, we observe that income tax, this supposedly confiscatory taxation, amounted to 59 billion in collections in 2012, for instance, while during the same year, these very same French citizens willingly spent 46.2 billion on gambling, whether it be on horse racing, online sports betting, or the lottery. From there, one might be tempted to think that by transforming income tax into the EuroMillions lottery, we could finally hope that public funds would flow abundantly to assist universities in providing students with decent conditions for education. The leap from one to the other is indeed a small one.

These statistics show that, on average, a French citizen spends 2,000 € annually on gambling but also wins 1,600 € annually. Thus, they lose 400 € with joy and merriment. Does this mean that it's impossible to win in gambling? Well, in fact, this has been mathematically proven in the case of pure chance games. The theorem of the gambler's ruin states the following:

« *If a player has an infinite initial fortune, there exists a martingale to always win. However, if this initial fortune is finite, there is no strategy to guarantee a win. On the contrary, if the player plays without a time limit, they are assured to end up ruined* ».  

In this project, we aim to experimentally verify this theorem using a Prolog program. Starting with a given initial amount of money, the program will simulate successive random games and observe that after a given time, the player always ends up ruined, regardless of their strategy. In this particular case, we will limit ourselves to the following situations:

- The game is limited to tossing a coin (heads or tails) with an associated bet, knowing that it can be demonstrated that all games of chance can be reduced to this strategy
- Among all the imaginable gaming strategies, we will limit ourselves to two types of systematic strategies: one where the player always bets the same fixed amount (fixed strategy) and one where they always bet the same percentage of the amount of money they still have (variable strategy)

In the context of this subject, two problems will be posed to you:

- Simulation of a Game – This problem is mandatory and will account for 15 points of the final grade. It involves solving the problem described below using the random generation capabilities provided natively by SWI-Prolog
- Random Coin Toss and Verification of the Law of Large Numbers – This problem is optional and will account for 5 points of the final grade. It involves creating a pseudo-random binary (heads or tails) draw generator and verifying that it satisfies at least the law of large numbers (an equal number of heads and tails on average as the number of draws increases)

3. **Simulation of a Game: Specifications**

The task is to create a program that will be called by the 'game/3' predicate defined as follows:

- PREDICAT :
  - jeu(Mise_init,Strategie,Valeur)       
- ROLE :
  - Global calling predicate that allows you to determine in how many games a player with a given initial bet goes bankrupt following a given strategy.
- ARGUMENTS :
  - Mise_init (int, +) : initial available amount
  - Strategie (int, +) : strategy type (fixe=2, variable=1)
  - Valeur (int, +) :percentage of the available bet or fixed amount


The progression of successive games must also follow the following rules:

- **Fixed Strategy** – The player bets a fixed amount (must be an integer) on each toss, which is defined at the beginning of the game when calling the 'jeu/3' predicate. If this bet exceeds the amount of money the player has remaining, the player bets the maximum amount they can afford. If the player has no money left, the game stops, and the player is bankrupt
- **Variable Strategy**  – In each toss, the player bets an amount that always corresponds to the same percentage of the money they have at their disposal. This percentage is strictly an integer (for example, if they are cautious, they bet 30% of their remaining wealth, if they go all-in like in poker, they bet 100%). However, it is required that the player only bets whole numbers (no cents): thus, the actual bet will be the largest integer less than or equal to their intended bet.

If the player runs out of money, the game stops, and they are bankrupt.

In all cases, the implemented system should be able to handle user input errors: it will notify the user if the parameters passed when invoking the predicate are incorrect.

4. **Random Drawing: Specifications**

It is possible that you may have used a predefined Prolog predicate for pseudo-random number generation in the previous problem. In this optional part, we aim to create a custom random generation predicate called gene_random/1 defined as follows:

- PREDICAT:
  - gen_random(N)
- ROLE :
  - Generate a pseudo-random binary number
- ARGUMENTS:
  - N (binary integer) : generated number (0 or 1)

There are many ways to implement pseudo-random number generation. Some are based on arithmetic properties (see the concept of a random sequence), others on unpredictable physical observations. Some combine both approaches.

In this problem, you have complete freedom to choose the generation method of your choice. The only requirement is not to use predefined SWI-Prolog predicates for generation.

It will be possible to precisely replace these predefined predicates in your initial game program with the one you created. However, this is not required, and if you do so for the final submission, make sure that you do not lose computation time.

On the contrary, what is requested here is to verify that your generator behaves according to the specifications. In particular, it should satisfy the law of large numbers, which states that as the number N of draws increases, the number of 'heads' and 'tails' draws becomes more balanced.

For this purpose, you are asked to create a test predicate 'verif_alea/1' that provides the distribution of draws after N successive tosses using the created generator. It is defined as follows:

- PREDICAT:
    - verif_ alea(N)
- ROLE :
    - For a given number N as input, perform N random tosses and provide the statistics of heads or tails outcomes.
- ARGUMENTS:
    - N (int) : number of tosses
