**The stick game - Fort Boyard**


The sticks game in Fort Boyard is played by 2 players. The 2 players face each other, with 20 sticks lined up next to each other on a table placed between the 2 players. In turn, each player must remove a minimum of 1 stick and a maximum of 3 sticks from the game.
Le but du jeu est de laisser le dernier bâtonnet à son adversaire. Celui qui se retrouve avec le dernier bâtonnet a donc perdu.

The aim of the project is to program a modified version of the stick game described above, which takes into account the following modifications:

1. In Fort Boyard, the initial number of sticks is set to 20. In the project, this number should no longer be fixed at 20, but should be a game parameter (called n) that the user can define before starting a game.
1. In the game, the maximum number of sticks each player can remove each round is set at 3. In the project, this maximum number should no longer be set at 3, but should be a parameter (called p) that the user can define before starting a game.
1. In each round, the player can only remove sticks that are adjacent to each other (side by side). Selected sticks cannot be separated by sticks present in the game, or by sticks removed earlier in the game.

For example, for the following configuration,

- removing sticks 5 and 8 is forbidden as they are not adjacent.
- removing sticks 2 and 4 is prohibited, as they are separated by stick 3, which has been removed beforehand.

| b | b | b | x | b | b | b | b | b | b |
|---|---|---|---|---|---|---|---|---|--|
| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |


Project rendering :

1. program in Ocaml the adapted version of the sticks game described above.
1. propose an algorithm to simulate a player.
1. you must offer 2 game modes for your program:
   - a multiplayer mode, where two "real" players play against each other
   - a "single" mode where a single "real" player will play against your algorithm proposed in 2 
1. A graphical interface to visualize the game and its progress (display of sticks, sticks removed, etc.) or, if necessary, to monitor the game in console mode.

A tournament will be organized to play your programs against each other. Groups wishing to participate will be able to do so. More details about the tournament and technical details to come.