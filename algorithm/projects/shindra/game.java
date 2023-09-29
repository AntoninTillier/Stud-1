import java.util.Scanner;

public class game {
    public static void main(String[] args) {
        int a = 0;
        while (a < 1) {
            System.out.println("Select the strategy: '1' - computer without AI or '2' - computer with AI!");
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) sc.next();
            int nb = sc.nextInt();
            if (nb == 1) {
                strategie(nb);
            } else if (nb == 2) {
                strategie(nb);
            } else {
                a = a - 1;
                System.out.println("Choose a number between 1 and 2!!");
                System.out.println();
            }
            a++;
        }
    }


    public static void strategie(int nb) {
        System.out.println();
        if (nb == 1) {// strategie 1
            System.out.println("You have chosen strategy 1.");
            System.out.println();
            int[] Deck_j = Deck_joueur();
            int[] Deck_ordi = Deck_ordinateur();
            System.out.println();
            int[][] plateau = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            String[][] plateau_T = {{" ", "0", " ", " ", "0", " ", " ", "0", " ", " ", "0", " "}, {" ", "0", " ", " ", "0", " ", " ", "0", " ", " ", "0", " "}, {" ", "0", " ", " ", "0", " ", " ", "0", " ", " ", "0", " "}, {" ", "0", " ", " ", "0", " ", " ", "0", " ", " ", "0", " "}};
            System.out.println("Here is the game board with 4 rows numbered from 1 to 4 and 4 columns numbered from 1 to 4: ");
            System.out.println();
            affichageString(plateau_T);
            System.out.println();
            int[][] plateau_J = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            int[][] plateau_O = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            int a = 1;
            while (a < 9) {
                plateau_J = place_du_joueurs_sur_plateau(0, 0, 0, Deck_j, plateau_J, plateau_O);
                System.out.println();
                plateau = remplissage_plateau(plateau_J, plateau_O);
                System.out.println("You played: ( )");
                System.out.println();
                affichage_plateau_de_jeu(plateau_T, plateau_J, plateau_O);
                System.out.println();
                plateau_O = place_du_ordinateur_sur_plateau(0, 0, Deck_ordi, a, plateau_O, plateau_J);
                plateau = remplissage_plateau(plateau_J, plateau_O);
                System.out.println("The AI played: [ ]");
                System.out.println();
                affichage_plateau_de_jeu(plateau_T, plateau_J, plateau_O);
                a++;
            }
            gagnant(plateau_J);
        } else {// strategie 2
            nb = 2;
            System.out.println("You have chosen strategy 2.");
            System.out.println();
            int[] Deck_j = Deck_joueur();
            int[] Deck_ordi = Deck_ordinateur();
            System.out.println();
            int[][] plateau = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            String[][] plateau_T = {{" ", "0", " ", " ", "0", " ", " ", "0", " ", " ", "0", " "}, {" ", "0", " ", " ", "0", " ", " ", "0", " ", " ", "0", " "}, {" ", "0", " ", " ", "0", " ", " ", "0", " ", " ", "0", " "}, {" ", "0", " ", " ", "0", " ", " ", "0", " ", " ", "0", " "}};
            System.out.println("Here is the game board with 4 rows numbered from 1 to 4 and 4 columns numbered from 1 to 4: ");
            System.out.println();
            affichageString(plateau_T);
            System.out.println();
            int[][] plateau_J = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            int[][] plateau_O = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            int a = 1;
            while (a < 9) {
                plateau_J = place_du_joueurs_sur_plateau(0, 0, 0, Deck_j, plateau_J, plateau_O);
                System.out.println();
                plateau = remplissage_plateau(plateau_J, plateau_O);
                System.out.println("You played: ( )");
                System.out.println();
                affichage_plateau_de_jeu(plateau_T, plateau_J, plateau_O);
                System.out.println();
                plateau_O = place_du_ordinateur_IA_sur_plateau(0, 0, Deck_ordi, a, plateau_O, plateau_J);
                plateau = remplissage_plateau(plateau_J, plateau_O);
                System.out.println("The AI played: [ ]");
                System.out.println();
                affichage_plateau_de_jeu(plateau_T, plateau_J, plateau_O);
                a++;
            }
            gagnant(plateau_J);
        }
    }

    /* In this method, I will generate the player's deck. The player will choose cards between 2 and 10. They can choose 9 cards out of 18. */
    public static int[] Deck_joueur() {
        int carte[] = {2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10};
        int deck[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};

        int a = 0;
        while (a < 9) {
            System.out.print("Choose 9 cards from this deck: ");
            for (int j = 0; j < 18; j++) {
                System.out.print(carte[j] + " ");
            }
            System.out.println();

            System.out.println("Choose a card from the deck between 2 and 10.");
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) sc.next();
            int carte_deck = sc.nextInt();
            System.out.println("Your card is : " + carte_deck);

            if (carte_deck == 0 || carte_deck != carte[0] && carte_deck != carte[1] && carte_deck != carte[2] && carte_deck != carte[3] && carte_deck != carte[4] && carte_deck != carte[5] && carte_deck != carte[6] && carte_deck != carte[7] && carte_deck != carte[8] && carte_deck != carte[9] && carte_deck != carte[10] && carte_deck != carte[11] && carte_deck != carte[12] && carte_deck != carte[13] && carte_deck != carte[14] && carte_deck != carte[15] && carte_deck != carte[16] && carte_deck != carte[17]) {
                a = a - 1;
                System.out.println("Please choose a card from your deck.");
                System.out.println();
            } else {
                for (int i = 0; i < carte.length; i++) {
                    if (carte[i] == carte_deck) {
                        carte[i] = 0;
                        for (i = 0; i < 18; i++) ;
                        System.out.println();
                    }
                }
                if (deck[a] == 0) {
                    deck[a] = carte_deck;
                }
            }
            a++;
        }
        System.out.print("your deck : ");
        for (int i = 0; i < deck.length; i++)
            System.out.print(deck[i] + "  ");
        System.out.println();
        return deck;
    }

    /* In this method, I will handle the player's placement on the game board.*/
    public static int[][] place_du_joueurs_sur_plateau(int l, int c, int p, int[] deck, int[][] plateau_j, int[][] plateau_o) {

        int[][] resu_J = plateau_j;
        int[][] resu_O = plateau_o;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose a location on the board:");

        System.out.println("line : ");
        while (!sc.hasNextInt()) sc.next();
        l = sc.nextInt();

        System.out.println("column : ");
        while (!sc.hasNextInt()) sc.next();
        c = sc.nextInt();

        System.out.println("Please choose your card:");
        while (!sc.hasNextInt()) sc.next();
        p = sc.nextInt();
        System.out.println();

        l = l - 1;
        c = c - 1;

        if (l < 0 || c < 0 || l > 3 || c > 3) {
            System.out.println("Please choose a valid location on the grid!");
            System.out.println();
            place_du_joueurs_sur_plateau(l, c, p, deck, plateau_j, plateau_o);
        } else if (resu_J[l][c] != 0) {
            System.out.println("Please choose an empty location!");
            System.out.println();
            place_du_joueurs_sur_plateau(l, c, p, deck, plateau_j, plateau_o);
        } else if (resu_O[l][c] != 0) {
            System.out.println("Please choose an empty location!");
            System.out.println();
            place_du_joueurs_sur_plateau(l, c, p, deck, plateau_j, plateau_o);
        } else if (p == 0) {
            System.out.println("You cannot choose 0.");
            System.out.println();
            place_du_joueurs_sur_plateau(l, c, p, deck, plateau_j, plateau_o);
        } else if (p != deck[0] && p != deck[1] && p != deck[2] && p != deck[3] && p != deck[4] && p != deck[5] && p != deck[6] && p != deck[7] && p != deck[8]) {
            System.out.println("Please choose a card from your deck.");
            System.out.println();
            place_du_joueurs_sur_plateau(l, c, p, deck, plateau_j, plateau_o);
        } else {
			// Printing the deck and setting the chosen card in the deck to 0
			for (int i = 0; i < deck.length; i++) {
				if (deck[i] == p) {
                    deck[i] = 0;
                    System.out.print("your deck : ");
                    for (i = 0; i < deck.length; i++)
                        System.out.print(deck[i] + "  ");
                    System.out.println();
                }
            }
            resu_J[l][c] = p;
            recherche_dans_tab(resu_J, resu_O, l, c);
            virus(resu_J, resu_O, l, c);
        }
        return resu_J;
    }

    public static void virus(int[][] resu_j, int[][] resu_o, int l, int c) {
        int[][] plateau1 = remplissage_plateau(resu_j, resu_o);
        int s = plateau1[l][c];
        if (c == 1 || c == 2) {
            int g = plateau1[l][c - 1];
            int d = plateau1[l][c + 1];
            if ((s == (g - d) && g != 0 && d != 0) || (s == (d - g) && g != 0 && d != 0)) {//gauche droite et droite gauche
                resu_j[l][c] = plateau1[l][c];
                resu_j[l][c - 1] = plateau1[l][c - 1];
                resu_j[l][c + 1] = plateau1[l][c + 1];
                resu_o[l][c - 1] = 0;
                resu_o[l][c + 1] = 0;
                resu_o[l][c] = 0;
                System.out.println();
                System.out.println("Virus 1 !");
            }
        }
        if (l == 1 || l == 2) {
            int h = plateau1[l - 1][c];
            int b = plateau1[l + 1][c];

            if ((s == (h - b) && h != 0 && b != 0) || (s == (b - h) && h != 0 && b != 0)) {//up down and down up
                resu_j[l][c] = plateau1[l][c];
                resu_j[l - 1][c] = plateau1[l - 1][c];
                resu_j[l + 1][c] = plateau1[l + 1][c];
                resu_o[l - 1][c] = 0;
                resu_o[l + 1][c] = 0;
                resu_o[l][c] = 0;
                System.out.println();
                System.out.println("Virus 2 !");
            }
        }

        if (c - 1 >= 0 && l - 1 >= 0) {
            int g = plateau1[l][c - 1];
            int h = plateau1[l - 1][c];
            if ((s == (g - h) && g != 0 && h != 0) || (s == (h - g) && h != 0 && g != 0)) {//Diagonal left right up
                resu_j[l][c] = plateau1[l][c];
                resu_j[l - 1][c] = plateau1[l - 1][c];
                resu_j[l][c - 1] = plateau1[l][c - 1];
                resu_o[l - 1][c] = 0;
                resu_o[l][c - 1] = 0;
                resu_o[l][c] = 0;
                System.out.println();
                System.out.println("Virus 3 !");
            }
        }
        if (c - 1 >= 0 && l + 1 < 4) {
            int g = plateau1[l][c - 1];
            int b = plateau1[l + 1][c];
            if ((s == (g - b) && g != 0 && b != 0) || (s == (b - g) && b != 0 && g != 0)) {//Diagonal left right down
                resu_j[l][c] = plateau1[l][c];
                resu_j[l + 1][c] = plateau1[l + 1][c];
                resu_j[l][c - 1] = plateau1[l][c - 1];
                resu_o[l + 1][c] = 0;
                resu_o[l][c - 1] = 0;
                resu_o[l][c] = 0;
                System.out.println();
                System.out.println("Virus 4 !");
            }
        }

        if (c + 1 < 4 && l - 1 >= 0) {
            int d = plateau1[l][c + 1];
            int h = plateau1[l - 1][c];
            if ((s == (d - h) && d != 0 && h != 0) || (s == (h - d) && h != 0 && d != 0)) {//Diagonal right left up
                resu_j[l][c] = plateau1[l][c];
                resu_j[l - 1][c] = plateau1[l - 1][c];
                resu_j[l][c + 1] = plateau1[l][c + 1];
                resu_o[l - 1][c] = 0;
                resu_o[l][c + 1] = 0;
                resu_o[l][c] = 0;
                System.out.println();
                System.out.println("Virus 5 !");
            }
        }
        if (c + 1 < 4 && l + 1 < 4) {
            int d = plateau1[l][c + 1];
            int b = plateau1[l + 1][c];
            if ((s == (d - b) && d != 0 && b != 0) || (s == (b - d) && b != 0 && d != 0)) {//Diagonal left right down
                resu_j[l][c] = plateau1[l][c];
                resu_j[l + 1][c] = plateau1[l + 1][c];
                resu_j[l][c + 1] = plateau1[l][c + 1];
                resu_o[l + 1][c] = 0;
                resu_o[l][c + 1] = 0;
                resu_o[l][c] = 0;
                System.out.println();
                System.out.println("Virus 6 !");
            }
        }
    }

	/* In this method, I will randomly generate the AI's deck. It will have cards ranging from 2 to 10, and it will choose 9 cards out of 18.*/
    public static int[] Deck_ordinateur() {
        int carte[] = {2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10};
        int deck_o[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int a = 0;
        while (a < 9) {
            int p = (int) (Math.random() * 18);
            if (carte[p] != 0) {
                deck_o[a] = carte[p];
                carte[p] = 0;
                a++;
            }
        }
        return deck_o;
    }

	// Displaying the game board in character mode.
    public static void affichageString(String[][] resu) {
        for (int i = 0; i < resu.length; i++) {
            for (int j = 0; j < resu[0].length; j++)
                System.out.print(resu[i][j] + "  ");
            System.out.println();
        }
        System.out.println();
    }

	/* Handling the computer's placement on the game board in strategy 1.*/
	public static int[][] place_du_ordinateur_sur_plateau(int l, int c, int[] p, int a, int[][] plateau_o, int[][] plateau_j) {
        int[][] resu_O = plateau_o;
        int[][] resu_J = plateau_j;
        l = ((int) (Math.random() * 4));
        c = ((int) (Math.random() * 4));
        if (resu_J[l][c] != 0) {
            place_du_ordinateur_sur_plateau(l, c, p, a, plateau_o, plateau_j);
        } else if (resu_O[l][c] == 0) {
            resu_O[l][c] = p[a];
            recherche_dans_tab(resu_O, resu_J, l, c);
            virus(resu_O, resu_J, l, c);
        } else {

            place_du_ordinateur_sur_plateau(l, c, p, a, plateau_o, plateau_j);
        }
        return resu_O;
    }

	/*Handling the computer's placement with intelligence on the game board in strategy 2.*/
	public static int[][] place_du_ordinateur_IA_sur_plateau(int l, int c, int[] p, int a, int[][] plateau_o, int[][] plateau_j) {
        int[][] resu_O = plateau_o;
        int[][] resu_J = plateau_j;
        int[] positionl = new int[16];
        int[] positionc = new int[16];
        int[] videl = new int[20];
        int[] videc = new int[20];
        int aa = a;
        int ba = 0;
        int ca = 0;
        int bb = 0;
        int cc = 0;
        int m = 0;
        int n = 0;
        int[][] plateau1 = remplissage_plateau(plateau_j, plateau_o);
        for (int i = 0; i < plateau_j.length; i++) {
            for (int j = 0; j < plateau_j[0].length; j++) {
                if (plateau_j[i][j] != 0) {
                    positionl[ba] = i;
                    positionc[ba] = j;
                    ba++;
                }
            }
        }
        for (int ii = 0; ii < plateau1.length; ii++) {
            for (int jj = 0; jj < plateau1[0].length; jj++) {
                if (plateau1[ii][jj] == 0) {
                    videl[ca] = ii;
                    videc[ca] = jj;
                    ca++;
                }
            }
        }
        while (cc <= ca) {
            m = videl[cc];
            n = videc[cc];
            tri_deck_par_ordre_croissant(p);
            if (n == 1 || n == 2) {
                int g = plateau1[m][n - 1];
                int d = plateau1[m][n + 1];
                for (int k = 1; k < p.length; k++) {
                    if ((p[k] != 0 && g != 0 && d != 0 && g != d) && (p[k] == (g - d) || p[k] == (d - g))) {
                        resu_O[m][n] = p[k];
                        p[k] = 0;
                        resu_O[m][n - 1] = plateau1[m][n - 1];
                        resu_O[m][n + 1] = plateau1[m][n + 1];
                        resu_J[m][n] = 0;
                        resu_J[m][n - 1] = 0;
                        resu_J[m][n + 1] = 0;
                        System.out.println();
                        System.out.println("Virus 1 AI !");
                        bb = ba + 1;
                    }
                }
            }
            if ((m == 1 || m == 2) && bb < ba) {
                int h = plateau1[m - 1][n];
                int b = plateau1[m + 1][n];
                for (int k = 1; k < p.length; k++) {
                    if ((p[k] != 0 && h != 0 && b != 0 && h != b) && (p[k] == (h - b) || p[k] == (b - h))) {
                        resu_O[m][n] = p[k];
                        p[k] = 0;
                        resu_O[m - 1][n] = plateau1[m - 1][n];
                        resu_O[m + 1][n] = plateau1[m + 1][n];
                        resu_J[m][n] = 0;
                        resu_J[m - 1][n] = 0;
                        resu_J[m + 1][n] = 0;
                        System.out.println();
                        System.out.println("Virus 2 AI !");
                        bb = ba + 1;
                    }
                }
            }
            if (n - 1 >= 0 && m - 1 >= 0 && bb < ba) {
                int g = plateau1[m][n - 1];
                int h = plateau1[m - 1][n];
                for (int k = 1; k < p.length; k++) {
                    if ((p[k] != 0 && g != 0 && h != 0 && g != h) && (p[k] == (g - h) || p[k] == (h - g))) {
                        resu_O[m][n] = p[k];
                        p[k] = 0;
                        resu_O[m][n - 1] = plateau1[m][n - 1];
                        resu_O[m - 1][n] = plateau1[m - 1][n];
                        resu_J[m][n] = 0;
                        resu_J[m][n - 1] = 0;
                        resu_J[m - 1][n] = 0;
                        System.out.println();
                        System.out.println("Virus 3 AI !");
                        bb = ba + 1;
                    }
                }
            }
            if (n - 1 >= 0 && m + 1 < 4 && bb < ba) {
                int gg = plateau1[m][n - 1];
                int b = plateau1[m + 1][n];
                for (int k = 1; k < p.length; k++) {
                    if ((p[k] != 0 && b != 0 && gg != 0 && gg != b) && (p[k] == (gg - b) || p[k] == (b - gg))) {
                        resu_O[m][n] = p[k];
                        p[k] = 0;
                        resu_O[m][n - 1] = plateau1[m][n - 1];
                        resu_O[m + 1][n] = plateau1[m + 1][n];
                        resu_J[m][n] = 0;
                        resu_J[m][n - 1] = 0;
                        resu_J[m + 1][n] = 0;
                        System.out.println();
                        System.out.println("Virus 4 AI !");
                        bb = ba + 1;
                    }
                }
            }
            if (n + 1 < 4 && m - 1 >= 0 && bb < ba) {
                int d = plateau1[m][n + 1];
                int hh = plateau1[m - 1][n];
                for (int k = 1; k < p.length; k++) {
                    if ((p[k] != 0 && hh != 0 && d != 0 && hh != d) && (p[k] == (d - hh) || p[k] == (hh - d))) {
                        resu_O[m][n] = p[k];
                        p[k] = 0;
                        resu_O[m][n + 1] = plateau1[m][n + 1];
                        resu_O[m - 1][n] = plateau1[m - 1][n];
                        resu_J[m][n] = 0;
                        resu_J[m][n + 1] = 0;
                        resu_J[m - 1][n] = 0;
                        System.out.println();
                        System.out.println("Virus 5 AI !");
                        bb = ba + 1;
                    }
                }
            }
            if (n + 1 < 4 && m + 1 < 4 && bb < ba) {
                int d = plateau1[m][n + 1];
                int b = plateau1[m + 1][n];
                for (int k = 1; k < p.length; k++) {
                    if ((p[k] != 0 && b != 0 && d != 0 && b != d) && (p[k] == (d - b) || p[k] == (b - d))) {
                        resu_O[m][n] = p[k];
                        p[k] = 0;
                        resu_O[m][n + 1] = plateau1[m][n + 1];
                        resu_O[m + 1][n] = plateau1[m + 1][n];
                        resu_J[m][n] = 0;
                        resu_J[m][n + 1] = 0;
                        resu_J[m + 1][n] = 0;
                        System.out.println();
                        System.out.println("Virus 6 AI !");
                        bb = ba + 1;
                    }
                }
            }
            cc++;
        }
        while (bb <= ba) {
            l = positionl[bb];
            c = positionc[bb];
            tri_deck_par_ordre_croissant(p);
            if (p[aa] == 0)
                aa++;

            if (aa == 9)
                aa = 0;

            if (c > 0 && plateau1[l][c - 1] == 0 && p[aa] != 0) {
                resu_O[l][c - 1] = p[aa];
                p[aa] = 0;
                c--;
                recherche_dans_tab(resu_O, resu_J, l, c);
                bb = ba + 1;
            } else if (c + 1 < 4 && plateau1[l][c + 1] == 0 && p[aa] != 0) {
                resu_O[l][c + 1] = p[aa];
                p[aa] = 0;
                c++;
                recherche_dans_tab(resu_O, resu_J, l, c);
                bb = ba + 1;
            } else if (l + 1 < 4 && plateau1[l + 1][c] == 0 && p[aa] != 0) {
                resu_O[l + 1][c] = p[aa];
                p[aa] = 0;
                l++;
                recherche_dans_tab(resu_O, resu_J, l, c);
                bb = ba + 1;
            } else if (l > 0 && plateau1[l - 1][c] == 0 && p[aa] != 0) {
                resu_O[l - 1][c] = p[aa];
                p[aa] = 0;
                l--;
                recherche_dans_tab(resu_O, resu_J, l, c);
                bb = ba + 1;
            } else {
                bb++;
            }
        }
        return resu_O;
    }

    public static void tri_deck_par_ordre_croissant(int tableau[]) {
        int longueur = tableau.length;
        int tampon = 0;
        boolean permutation;
        do {
            permutation = false;
            for (int i = 0; i < longueur - 1; i++) {
                if (tableau[i] > tableau[i + 1]) {
                    tampon = tableau[i];
                    tableau[i] = tableau[i + 1];
                    tableau[i + 1] = tampon;
                    permutation = true;
                }
            }
        }
        while (permutation);
    }

    public static void recherche_dans_tab(int[][] recherche, int[][] dans_tab, int l, int c) {
        int a = 0;
        int positionl[] = new int[1000];
        int positionc[] = new int[1000];
        positionl[0] = l;
        positionc[0] = c;
        while (a < 200) {
            l = positionl[a];
            c = positionc[a];
            if (c + 1 < 4 && recherche[l][c] > dans_tab[l][c + 1] && dans_tab[l][c + 1] != 0) {
                recherche[l][c + 1] = dans_tab[l][c + 1];
                dans_tab[l][c + 1] = 0;
                positionl[a] = l;
                positionc[a] = c + 1;
            }
            if (c - 1 >= 0 && recherche[l][c] > dans_tab[l][c - 1] && dans_tab[l][c - 1] != 0) {
                recherche[l][c - 1] = dans_tab[l][c - 1];
                dans_tab[l][c - 1] = 0;
                positionl[a + 16] = l;
                positionc[a + 16] = c - 1;
            }
            if (l + 1 < 4 && recherche[l][c] > dans_tab[l + 1][c] && dans_tab[l + 1][c] != 0) {
                recherche[l + 1][c] = dans_tab[l + 1][c];
                dans_tab[l + 1][c] = 0;
                positionl[a + 32] = l + 1;
                positionc[a + 32] = c;
            }
            if (l - 1 >= 0 && recherche[l][c] > dans_tab[l - 1][c] && dans_tab[l - 1][c] != 0) {
                recherche[l - 1][c] = dans_tab[l - 1][c];
                dans_tab[l - 1][c] = 0;
                positionl[a + 48] = l - 1;
                positionc[a + 48] = c;
            } else {
                a++;
            }
        }
    }

    public static int[][] remplissage_plateau(int[][] plateau_j, int[][] plateau_o) {
        int[][] somme = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                somme[i][j] = plateau_j[i][j] + plateau_o[i][j];
            }
        }
        return somme;
    }

    public static void affichage_plateau_de_jeu(String[][] plateau_total, int[][] plateau_j, int[][] plateau_o) {
        String[][] plateauj = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                plateauj[i][j] = "" + plateau_j[i][j];
        }
        for (int i = 0; i < 4; i++) {
            int a = 0;
            for (int j = 0; j < 4; j++) {
                if (plateau_j[i][j] != 0) {
                    plateau_total[i][a + j + 1] = plateauj[i][j];
                    plateau_total[i][a + j] = "(";
                    plateau_total[i][a + j + 2] = ")";
                    a = a + 2;
                } else {
                    a = a + 2;
                }
            }
        }
        String[][] plateauo = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                plateauo[i][j] = "" + plateau_o[i][j];
        }
        for (int i = 0; i < 4; i++) {
            int a = 0;
            for (int j = 0; j < 4; j++) {
                if (plateau_o[i][j] != 0) {
                    plateau_total[i][a + j + 1] = plateauo[i][j];
                    plateau_total[i][a + j] = "[";
                    plateau_total[i][a + j + 2] = "]";
                    a = a + 2;
                } else {
                    a = a + 2;
                }
            }
        }
        for (int i = 0; i < plateau_total.length; i++) {
            for (int j = 0; j < plateau_total[0].length; j++)
                System.out.print(plateau_total[i][j] + "\t");
            System.out.println();
        }
        System.out.println();
    }

    public static void gagnant(int[][] plateau) {
        int z = 0;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++)
                if (plateau[i][j] != 0) {
                    z++;
                }
        }
        if (z > 8) {
            System.out.println("You won with " + z + " cards, congratulations!");
        } else if (z == 8) {
            System.out.println("Tie game, you are even with the computer.");
        } else {
            System.out.println("AI won with " + (16 - z) + " cards !");
        }
    }
}