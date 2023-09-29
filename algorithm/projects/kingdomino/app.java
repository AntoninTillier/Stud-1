import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;


@SuppressWarnings("serial")
public class app extends JFrame {
    private Thread traitement;
    private JProgressBar progression;
    private JLabel nom = new JLabel("Launching Kingdomino");
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        /* To play with more comfort, you should open the console in full screen. */
        String chemin = new File("src/Kingdomino.jpg").getAbsolutePath();
        BufferedImage image = ImageIO.read(new File(chemin));
        Lancement contentPane = new Lancement(image);
        JWindow Lancement = new JWindow();
        Lancement.setContentPane(contentPane);
        Lancement.setSize(854, 481);
        Lancement.setLocationRelativeTo(null);
        Lancement.setVisible(true);
        app Chargement = new app();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Chargement.dispose();
        Lancement.dispose();
        menu();
    }

    public app() {
        this.setSize(320, 80);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        Font POLICE = new Font("Helvetica Neue", Font.BOLD, 25);
        nom.setFont(POLICE);
        traitement = new Thread(new Traitement());
        progression = new JProgressBar();
        progression.setMaximum(500);
        progression.setMinimum(0);
        progression.setStringPainted(true);
        this.getContentPane().add(progression, BorderLayout.SOUTH);
        this.getContentPane().add(nom, BorderLayout.CENTER);
        traitement.start();
        this.setVisible(true);
    }

    class Traitement implements Runnable {
        public void run() {
            for (int valeur = 0; valeur <= 500; valeur++) {
                progression.setValue(valeur);
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* The "menu" method allows the user to choose their game mode and read the rules.*/
    public static void menu() {
        System.out.println("WELCOME TO KINGDOMINO" + "\n");
        System.out.println("To get the rules of the game, press 'h,' or press 'n' if you don't want them.");
        String aide = "a";
        while (verrifS(aide) != true) {
            aide = sc.nextLine();
        }
        if (aide.equalsIgnoreCase("h")) {
            Desktop help = Desktop.getDesktop();
            String chemin = new File("src/regle_du_jeu.pdf").getAbsolutePath();
            try {
                help.open(new File(chemin));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("To play with one or more players, type '1.' Otherwise, to play against the computer, type '2.'" + "\n");

        while (!sc.hasNextInt())
            sc.next();
        int choix = 100;
        while (verrifI(choix, 1, 2) != true) {
            choix = sc.nextInt();
        }
        if (choix == 1) {
            System.out.println("Enter the number of players: 2, 3, or 4:" + "\n");
            int nbj = 100;
            while (verrifI(nbj, 2, 4) != true) {
                nbj = sc.nextInt();
            }
            if (nbj == 2) {
                System.out.println("Do you want to play with a 5x5 board? Enter 1. For a 7x7 board, enter 2.");
                while (!sc.hasNextInt())
                    sc.next();
                int tab = 100;
                while (verrifI(tab, 1, 2) != true) {
                    tab = sc.nextInt();
                }
                if (tab == 1) {
                    int[][] deckC = new int[48][2];
                    String[][] deck = domino_Deck(deckC);
                    int[][] plateauC1 = new int[5][5];
                    String[][] plateau1 = kingdom(plateauC1);
                    int[][] plateauC2 = new int[5][5];
                    String[][] plateau2 = kingdom(plateauC2);
                    nbj = 2;
                    int compt = 0;
                    while (compt < 12) {
                        System.out.println("Round N°" + (compt + 1) + "\n");
                        int[][] dominoC_par_nbj = new int[nbj][2];
                        String[][] domino_par_nbj = regle(deck, deckC, dominoC_par_nbj, nbj);
                        System.out.println("Player 1 : " + "\n");
                        afficheS(plateau1);
                        System.out.println("\n");
                        plateau_de_jeu(plateau1, plateauC1, nbj, dominoC_par_nbj, domino_par_nbj, 0);
                        domino_par_nbj = changement_deck(domino_par_nbj, 1);
                        dominoC_par_nbj = changement_deckC(dominoC_par_nbj, 1);
                        deck = changement_deck(deck, nbj);
                        deckC = changement_deckC(deckC, nbj);
                        System.out.println();
                        afficheS(plateau1);
                        System.out.println("\n" + "\n");
                        System.out.println("Round N°" + (compt + 1) + "\n");
                        System.out.println("Player 2 : " + "\n");
                        afficheS(plateau2);
                        System.out.println("\n");
                        plateau_de_jeu(plateau2, plateauC2, nbj, dominoC_par_nbj, domino_par_nbj, 1);
                        System.out.println();
                        afficheS(plateau2);
                        System.out.println("\n");
                        compt++;
                    }
                    System.out.println();
                    System.out.println("Score Player 1 : ");
                    int score1 = winner(plateau1, plateauC1);
                    score1 += empire_du_milieu(plateau1);
                    System.out.println();
                    System.out.println("Your total score is " + score1 + "\n");
                    System.out.println("Score Player 2 : ");
                    int score2 = winner(plateau2, plateauC2);
                    score2 += empire_du_milieu(plateau2);
                    System.out.println();
                    System.out.println("Your total score is " + score2 + "\n");
                    if (score1 > score2) {
                        System.out.println("Player 1 you win.");
                    } else {
                        System.out.println("Player 2 you win.");
                    }
                }
                if (tab == 2) {
                    int[][] deckC = new int[48][2];
                    String[][] deck = domino_Deck(deckC);
                    int[][] plateauC1 = new int[7][7];
                    String[][] plateau1 = kingdom7(plateauC1);
                    int[][] plateauC2 = new int[7][7];
                    String[][] plateau2 = kingdom7(plateauC2);
                    nbj = 2;
                    int compt = 0;
                    while (compt < 24) {
                        System.out.println("Round N°" + (compt + 1) + "\n");
                        int[][] dominoC_par_nbj = new int[nbj][2];
                        String[][] domino_par_nbj = regle(deck, deckC, dominoC_par_nbj, nbj);
                        System.out.println("Player 1 : " + "\n");
                        afficheS(plateau1);
                        System.out.println("\n");
                        plateau_de_jeu7(plateau1, plateauC1, nbj, dominoC_par_nbj, domino_par_nbj, 0);
                        domino_par_nbj = changement_deck(domino_par_nbj, 1);
                        dominoC_par_nbj = changement_deckC(dominoC_par_nbj, 1);
                        deck = changement_deck(deck, nbj);
                        deckC = changement_deckC(deckC, nbj);
                        System.out.println();
                        afficheS(plateau1);
                        System.out.println("\n" + "\n");
                        System.out.println("Round N°" + (compt + 1) + "\n");
                        System.out.println("Player 2 : " + "\n");
                        afficheS(plateau2);
                        System.out.println("\n");
                        plateau_de_jeu7(plateau2, plateauC2, nbj, dominoC_par_nbj, domino_par_nbj, 1);
                        System.out.println();
                        afficheS(plateau2);
                        System.out.println();
                        compt++;
                    }
                    int score1 = winner7(plateau1, plateauC1);
                    score1 += empire_du_milieu(plateau1);
                    System.out.println("Your total score is " + score1 + "\n");
                    int score2 = winner7(plateau2, plateauC2);
                    score2 += empire_du_milieu(plateau2);
                    System.out.println("Your total score is " + score2 + "\n");
                    if (score1 > score2) {
                        System.out.println("Player 1 you win.");
                    } else {
                        System.out.println("Player 2 you win.");
                    }
                }
            }
            if (nbj == 3) {
                int[][] deckC = new int[48][2];
                String[][] deck = domino_Deck(deckC);
                int[][] plateauC1 = new int[5][5];
                String[][] plateau1 = kingdom(plateauC1);
                int[][] plateauC2 = new int[5][5];
                String[][] plateau2 = kingdom(plateauC2);
                int[][] plateauC3 = new int[5][5];
                String[][] plateau3 = kingdom(plateauC3);
                nbj = 3;
                int compt = 0;
                while (compt < 12) {
                    System.out.println("Round N°" + (compt + 1) + "\n");
                    int[][] dominoC_par_nbj = new int[nbj][2];
                    String[][] domino_par_nbj = regle(deck, deckC, dominoC_par_nbj, nbj);
                    System.out.println("Player 1 : " + "\n");
                    afficheS(plateau1);
                    System.out.println("\n");
                    plateau_de_jeu(plateau1, plateauC1, nbj, dominoC_par_nbj, domino_par_nbj, 0);
                    domino_par_nbj = changement_deck(domino_par_nbj, 1);
                    dominoC_par_nbj = changement_deckC(dominoC_par_nbj, 1);
                    deck = changement_deck(deck, nbj);
                    deckC = changement_deckC(deckC, nbj);
                    System.out.println();
                    afficheS(plateau1);
                    System.out.println("\n" + "\n");
                    System.out.println("Round N°" + (compt + 1) + "\n");
                    System.out.println("Player 2 : " + "\n");
                    afficheS(plateau2);
                    System.out.println("\n");
                    plateau_de_jeu(plateau2, plateauC2, nbj, dominoC_par_nbj, domino_par_nbj, 1);
                    domino_par_nbj = changement_deck(domino_par_nbj, 1);
                    dominoC_par_nbj = changement_deckC(dominoC_par_nbj, 1);
                    System.out.println();
                    afficheS(plateau2);
                    System.out.println("\n" + "\n");
                    System.out.println("Round N°" + (compt + 1) + "\n");
                    System.out.println("Player 3 : " + "\n");
                    afficheS(plateau3);
                    System.out.println("\n");
                    plateau_de_jeu(plateau3, plateauC3, nbj, dominoC_par_nbj, domino_par_nbj, 2);
                    System.out.println();
                    afficheS(plateau3);
                    System.out.println("\n");
                    compt++;
                }
                System.out.println();
                System.out.println("Score Player 1 : ");
                int score1 = winner(plateau1, plateauC1);
                score1 += empire_du_milieu(plateau1);
                System.out.println("Your total score is " + score1 + "\n");
                System.out.println("Score Player 2 : ");
                int score2 = winner(plateau2, plateauC2);
                score2 += empire_du_milieu(plateau2);
                System.out.println();
                System.out.println("Your total score is " + score2 + "\n");
                System.out.println("Score Player 3 : ");
                int score3 = winner(plateau3, plateauC3);
                score3 += empire_du_milieu(plateau3);
                System.out.println();
                System.out.println("Your total score is " + score3 + "\n");
                if (score1 > score2 && score1 > score3) {
                    System.out.println("Player 1 you win.");
                }
                if (score2 > score1 && score2 > score3) {
                    System.out.println("Player 2 you win.");
                }
                if (score3 > score1 && score3 > score2) {
                    System.out.println("Player 3 you win.");
                }
            }
            if (nbj == 4) {
                int[][] deckC = new int[48][2];
                String[][] deck = domino_Deck(deckC);
                int[][] plateauC1 = new int[5][5];
                String[][] plateau1 = kingdom(plateauC1);
                int[][] plateauC2 = new int[5][5];
                String[][] plateau2 = kingdom(plateauC2);
                int[][] plateauC3 = new int[5][5];
                String[][] plateau3 = kingdom(plateauC3);
                int[][] plateauC4 = new int[5][5];
                String[][] plateau4 = kingdom(plateauC4);
                nbj = 4;
                int compt = 0;
                while (compt < 12) {
                    System.out.println("Round N°" + (compt + 1));
                    int[][] dominoC_par_nbj = new int[nbj][2];
                    String[][] domino_par_nbj = regle(deck, deckC, dominoC_par_nbj, nbj);
                    System.out.println("Player 1 : " + "\n");
                    afficheS(plateau1);
                    System.out.println("\n");
                    plateau_de_jeu(plateau1, plateauC1, nbj, dominoC_par_nbj, domino_par_nbj, 0);
                    domino_par_nbj = changement_deck(domino_par_nbj, 1);
                    dominoC_par_nbj = changement_deckC(dominoC_par_nbj, 1);
                    deck = changement_deck(deck, nbj);
                    deckC = changement_deckC(deckC, nbj);
                    System.out.println();
                    afficheS(plateau1);
                    System.out.println("\n" + "\n");
                    System.out.println("Round N°" + (compt + 1) + "\n");
                    System.out.println("Player 2 : " + "\n");
                    afficheS(plateau2);
                    System.out.println("\n");
                    plateau_de_jeu(plateau2, plateauC2, nbj, dominoC_par_nbj, domino_par_nbj, 1);
                    domino_par_nbj = changement_deck(domino_par_nbj, 1);
                    dominoC_par_nbj = changement_deckC(dominoC_par_nbj, 1);
                    System.out.println();
                    afficheS(plateau2);
                    System.out.println("\n" + "\n");
                    System.out.println("Round N°" + (compt + 1) + "\n");
                    System.out.println("Player 3 : " + "\n");
                    afficheS(plateau3);
                    System.out.println("\n");
                    plateau_de_jeu(plateau3, plateauC3, nbj, dominoC_par_nbj, domino_par_nbj, 2);
                    domino_par_nbj = changement_deck(domino_par_nbj, 1);
                    dominoC_par_nbj = changement_deckC(dominoC_par_nbj, 1);
                    System.out.println();
                    afficheS(plateau3);
                    System.out.println("\n" + "\n");
                    System.out.println("Round N°" + (compt + 1) + "\n");
                    System.out.println("Player 4 : " + "\n");
                    afficheS(plateau4);
                    System.out.println("\n");
                    plateau_de_jeu(plateau4, plateauC4, nbj, dominoC_par_nbj, domino_par_nbj, 3);
                    System.out.println();
                    afficheS(plateau4);
                    System.out.println("\n");
                    compt++;
                }
                System.out.println();
                System.out.println("Score Player 1 : ");
                int score1 = winner(plateau1, plateauC1);
                score1 += empire_du_milieu(plateau1);
                System.out.println();
                System.out.println("Your total score is " + score1 + "\n");
                System.out.println("Score Player 2 : ");
                int score2 = winner(plateau2, plateauC2);
                score2 += empire_du_milieu(plateau2);
                System.out.println();
                System.out.println("Your total score is " + score2 + "\n");
                System.out.println("Score Player 3 : ");
                int score3 = winner(plateau3, plateauC3);
                score3 += empire_du_milieu(plateau3);
                System.out.println();
                System.out.println("Your total score is " + score3 + "\n");
                System.out.println("Score Player 4 : ");
                int score4 = winner(plateau4, plateauC4);
                score4 += empire_du_milieu(plateau4);
                System.out.println();
                System.out.println("Your total score is " + score4 + "\n");
                if (score1 > score2 && score1 > score3 && score1 > score4) {
                    System.out.println("Player 1 you win.");
                }
                if (score2 > score1 && score2 > score3 && score2 > score4) {
                    System.out.println("Player 2 you win.");
                }
                if (score3 > score1 && score3 > score2 && score3 > score4) {
                    System.out.println("Player 3 you win.");
                }
                if (score4 > score1 && score4 > score2 && score4 > score3) {
                    System.out.println("Player 4 you win.");
                }
            }
        }
        if (choix == 2) {
            System.out.println("\n" +
					"Do you want to play against the computer in easy mode? Type 1. If you want to play against the challenging AI, type 2." + "\n");
            int choixIA = 100;
            while (verrifI(choixIA, 1, 2) != true) {
                choixIA = sc.nextInt();
            }
            if (choixIA == 1) {
                int[][] deckC = new int[48][2];
                String[][] deck = domino_Deck(deckC);
                int[][] plateauC1 = new int[5][5];
                String[][] plateau1 = kingdom(plateauC1);
                int[][] plateauCIA = new int[5][5];
                String[][] plateauIA = kingdom(plateauCIA);
                int nbIA = 2;
                int compt = 0;
                while (compt < 12) {
                    System.out.println("Round N°" + (compt + 1) + "\n");
                    int[][] dominoC_par_nbj = new int[nbIA][2];
                    String[][] domino_par_nbj = regle(deck, deckC, dominoC_par_nbj, nbIA);
                    System.out.println("Player 1 : " + "\n");
                    afficheS(plateau1);
                    System.out.println("\n");
                    plateau_de_jeu(plateau1, plateauC1, nbIA, dominoC_par_nbj, domino_par_nbj, 0);
                    domino_par_nbj = changement_deck(domino_par_nbj, 1);
                    dominoC_par_nbj = changement_deckC(dominoC_par_nbj, 1);
                    deck = changement_deck(deck, nbIA);
                    deckC = changement_deckC(deckC, nbIA);
                    System.out.println();
                    afficheS(plateau1);
                    System.out.println("\n" + "\n");
                    System.out.println("Round N°" + (compt + 1) + "\n");
                    System.out.println("AI : " + "\n");
                    plateau_de_jeuIA(plateauIA, plateauCIA, nbIA, dominoC_par_nbj, domino_par_nbj, 1);
                    afficheS(plateauIA);
                    System.out.println("\n");
                    compt++;
                }
                System.out.println();
                System.out.println("Score Player 1 : ");
                int score1 = winner(plateau1, plateauC1);
                score1 += empire_du_milieu(plateau1);
                System.out.println();
                System.out.println("Your total score is " + score1 + "\n");
                System.out.println("Score AI : ");
                int score2 = winner(plateauIA, plateauCIA);
                score2 += empire_du_milieu(plateauIA);
                System.out.println();
                System.out.println("Your total score is " + score2 + "\n");
                if (score1 > score2) {
                    System.out.println("Player 1 you win.");
                } else {
                    System.out.println("AI you win.");
                }
            }
            if (choixIA == 2) {
                int[][] deckC = new int[48][2];
                String[][] deck = domino_Deck(deckC);
                int[][] plateauC1 = new int[5][5];
                String[][] plateau1 = kingdom(plateauC1);
                int[][] plateauCIAF = new int[5][5];
                String[][] plateauIAF = kingdom(plateauCIAF);
                int nbIA = 2;
                int compt = 0;
                while (compt < 12) {
                    System.out.println("Round N°" + (compt + 1) + "\n");
                    int[][] dominoC_par_nbj = new int[nbIA][2];
                    String[][] domino_par_nbj = regle(deck, deckC, dominoC_par_nbj, nbIA);
                    System.out.println("Player 1 : " + "\n");
                    afficheS(plateau1);
                    System.out.println("\n");
                    plateau_de_jeu(plateau1, plateauC1, nbIA, dominoC_par_nbj, domino_par_nbj, 0);
                    domino_par_nbj = changement_deck(domino_par_nbj, 1);
                    dominoC_par_nbj = changement_deckC(dominoC_par_nbj, 1);
                    deck = changement_deck(deck, nbIA);
                    deckC = changement_deckC(deckC, nbIA);
                    System.out.println();
                    afficheS(plateau1);
                    System.out.println("\n" + "\n");
                    System.out.println("Round N°" + (compt + 1) + "\n");
                    System.out.println("AI : " + "\n");
                    plateau_de_jeuIAF(plateauIAF, plateauCIAF, nbIA, dominoC_par_nbj, domino_par_nbj, 1);
                    afficheS(plateauIAF);
                    System.out.println("\n");
                    compt++;
                }
                System.out.println();
                System.out.println("Score Player 1 : ");
                int score1 = winner(plateau1, plateauC1);
                score1 += empire_du_milieu(plateau1);
                System.out.println();
                System.out.println("Your total score is " + score1 + "\n");
                System.out.println("Score AI : ");
                int score2 = winner(plateauIAF, plateauCIAF);
                score2 += empire_du_milieu(plateauIAF);
                System.out.println();
                System.out.println("Your total score is " + score2 + "\n");
                if (score1 > score2) {
                    System.out.println("Player 1 you win.");
                } else {
                    System.out.println("AI you win.");
                }
            }
        }
    }

    public static void afficheS(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++)
                System.out.print(tab[i][j] + "\t");
            System.out.println();
        }
    }

    public static void afficheI(int[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++)
                System.out.print(tab[i][j] + "\t");
            System.out.println();
        }
    }

    /* Cette méthode permet de créer les dominos aléatoirement*/
    public static String[][] domino_Deck(int[][] dominoC) {
        String[][] domino = new String[48][2];
        String[] piece = {"champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ", "champ",
                "prairie", "prairie", "prairie", "prairie", "prairie", "prairie", "prairie", "prairie", "prairie", "prairie", "prairie", "prairie", "prairie", "prairie",
                "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer", "mer",
                "maree", "maree", "maree", "maree", "maree", "maree", "maree", "maree", "maree", "maree",
                "mine", "mine", "mine", "mine", "mine", "mine",
                "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret", "foret",};
        int[] pieceC = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0, 1, 1, 2, 2,
                0, 1, 2, 2, 2, 3,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};

        for (int i = 0; i < 48; i++) {
            for (int j = 0; j < 2; j++) {
                boolean egal = false;
                int proba = ((int) (Math.random() * 96));
                while (egal != true) {
                    if (piece[proba] != null) {
                        domino[i][j] = piece[proba];
                        dominoC[i][j] = pieceC[proba];
                        egal = true;
                    } else {
                        proba = ((int) (Math.random() * 96));
                    }
                }
                piece[proba] = null;
            }
        }
        return domino;
    }

    /* This method creates the game board*/
    public static String[][] kingdom(int[][] tab) {
        String[][] kingdom = new String[5][5];
        for (int i = 0; i < kingdom.length; i++) {
            for (int j = 0; j < kingdom[0].length; j++) {
                if (i == (5 / 2) && j == (5 / 2)) {
                    kingdom[i][j] = "Castle";
                    tab[i][j] = 10;
                } else {
                    kingdom[i][j] = " _X_ ";
                    tab[i][j] = 0;
                }
            }
        }
        return kingdom;
    }

    public static String[][] kingdom7(int[][] tab) {
        String[][] kingdom = new String[7][7];
        for (int i = 0; i < kingdom.length; i++) {
            for (int j = 0; j < kingdom[0].length; j++) {
                if (i == (7 / 2) && j == (7 / 2)) {
                    kingdom[i][j] = "Castle";
                    tab[i][j] = 10;
                } else {
                    kingdom[i][j] = " _X_ ";
                    tab[i][j] = 0;
                }
            }
        }
        return kingdom;
    }

    /* Input by the user of the value i*/
    public static int i() {
        System.out.println("emplacement i : ");
        while (!sc.hasNextInt())
            sc.next();
        int i = sc.nextInt();
        return i;
    }

    /* Input by the user of the value j*/
    public static int j() {
        System.out.println("emplacement j : ");
        while (!sc.hasNextInt())
            sc.next();
        int j = sc.nextInt();
        return j;
    }

    /* The plateau_de_jeu method allows the user to place their dominoes on their board.
    	If the user makes a mistake, the domino is discarded.
    	This method also allows moving the board up, down, left, or right
	*/
    public static String[][] plateau_de_jeu(String[][] tab, int[][] tab2, int nbj, int[][] dominoC_par_nbj, String[][] domino_par_nbj, int n) {
        System.out.println("Here is your domino and its number of crowns");
        System.out.println();
        System.out.println(domino_par_nbj[0][0] + "\t" + domino_par_nbj[0][1]);
        System.out.println();
        System.out.println(dominoC_par_nbj[0][0] + "\t" + dominoC_par_nbj[0][1]);
        System.out.println();
        int castel = comptage_position_plateau(tab, "Castle");
        int part1domino = comptage_position_plateau(tab, domino_par_nbj[0][0]);
        int part2domino = comptage_position_plateau(tab, domino_par_nbj[0][1]);
        if (castel == 0 && part1domino == 0 && part2domino == 0) {
            System.out.println("There is no solution. The domino is not used, you pass your turn.");
            return tab;
        }
        System.out.println();
        int[][] dominoC_jouable = new int[1][2];
        String[][] domino_jouable = choix_part_domino(domino_par_nbj[0][0], domino_par_nbj[0][1], dominoC_par_nbj[0][0], dominoC_par_nbj[0][1], dominoC_jouable);
        int i = 100;
        while (verrifI(i, -1, 5) != true) {
            i = i();
        }
        int j = 100;
        while (verrifI(j, -1, 5) != true) {
            j = j();
        }
        if (i == -1)//up
        {
            if (regle_playC(tab) == true) {
                if (tab[i + 1][j] == " _X_ ") {
                    return tab;
                }
                if (comptageC(tab) == true) {
                    if (regle_depassementC(tab) == false) {
                        return tab;
                    }
                    i = 1 + i;
                    for (int ii = 3; ii > -1; ii--) {
                        for (int jj = 0; jj < tab[0].length; jj++) {
                            if (tab[ii][jj] != " _X_ ") {
                                tab[ii + 1][jj] = tab[ii][jj];
                                tab[ii][jj] = " _X_ ";
                                tab2[ii + 1][jj] = tab2[ii][jj];
                                tab2[ii][jj] = 0;
                            }
                        }
                    }
                } else {
                    System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                    return tab;
                }
            } else {
                System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                return tab;
            }
        }
        if (i == 5)//down
        {
            if (regle_playC5(tab) == true) {
                if (tab[i - 1][j] == " _X_ ") {
                    return tab;
                }
                if (comptageC(tab) == true) {
                    if (regle_depassementC5(tab) == false) {
                        return tab;
                    }
                    i = i - 1;
                    for (int ii = 0; ii < tab.length; ii++) {
                        for (int jj = 0; jj < tab[0].length; jj++) {
                            if (tab[ii][jj] != " _X_ ") {
                                tab[ii - 1][jj] = tab[ii][jj];
                                tab[ii][jj] = " _X_ ";
                                tab2[ii - 1][jj] = tab2[ii][jj];
                                tab2[ii][jj] = 0;
                            }
                        }
                    }
                } else {
                    System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                    return tab;
                }
            } else {
                System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                return tab;
            }
        }
        if (j == -1)//left
        {
            if (regle_playL(tab)) {
                if (tab[i][j + 1] == " _X_ ") {
                    return tab;
                }
                if (comptageL(tab) == true) {
                    if (regle_depassementL(tab) == false) {
                        return tab;
                    }
                    j = 1 + j;
                    for (int ii = 0; ii < tab.length; ii++) {
                        for (int jj = 4; jj > -1; jj--) {
                            if (tab[ii][jj] != " _X_ ") {
                                tab[ii][jj + 1] = tab[ii][jj];
                                tab[ii][jj] = " _X_ ";
                                tab2[ii][jj + 1] = tab2[ii][jj];
                                tab2[ii][jj] = 0;
                            }
                        }
                    }
                } else {
                    System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                    return tab;
                }
            } else {
                System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                return tab;
            }
        }
        if (j == 5)//right
        {
            if (regle_playL5(tab) == true) {
                if (tab[i][j - 1] == " _X_ ") {
                    return tab;
                }
                if (comptageL(tab) == true) {
                    if (regle_depassementL5(tab) == false) {
                        return tab;
                    }
                    j = j - 1;
                    for (int ii = 0; ii < tab.length; ii++) {
                        for (int jj = 0; jj < tab.length; jj++) {
                            if (tab[ii][jj] != " _X_ ") {
                                tab[ii][jj - 1] = tab[ii][jj];
                                tab[ii][jj] = " _X_ ";
                                tab2[ii][jj - 1] = tab2[ii][jj];
                                tab2[ii][jj] = 0;
                            }
                        }
                    }
                } else {
                    System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                    return tab;
                }
            } else {
                System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                return tab;
            }
        }
        if (defausserVide(tab, i, j) == false) {
            return tab;
        }
        if (defausserDomino(tab, i, j, domino_jouable[0][0]) == false) {
            return tab;
        }
        if (positionVide(tab, i, j) == true) {

            tab = placement_plateau(tab, tab2, domino_jouable, dominoC_jouable, i, j);
            return tab;
        } else {
            System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
            return tab;
        }
    }

    public static String[][] plateau_de_jeu7(String[][] tab, int[][] tab2, int nbj, int[][] dominoC_par_nbj, String[][] domino_par_nbj, int n) {
        System.out.println("Here is your domino and its number of crowns:");
        System.out.println();
        System.out.println(domino_par_nbj[0][0] + "\t" + domino_par_nbj[0][1]);
        System.out.println();
        System.out.println(dominoC_par_nbj[0][0] + "\t" + dominoC_par_nbj[0][1]);
        System.out.println();
        int castel = comptage_position_plateau7(tab, "Castle");
        int part1domino = comptage_position_plateau7(tab, domino_par_nbj[0][0]);
        int part2domino = comptage_position_plateau7(tab, domino_par_nbj[0][1]);

        if (castel == 0 && part1domino == 0 && part2domino == 0) {
            System.out.println("There is no solution. The domino is not used, you pass your turn.");
            return tab;
        }
        System.out.println();
        int[][] dominoC_jouable = new int[1][2];
        String[][] domino_jouable = choix_part_domino(domino_par_nbj[0][0], domino_par_nbj[0][1], dominoC_par_nbj[0][0], dominoC_par_nbj[0][1], dominoC_jouable);
        int i = 100;
        while (verrifI(i, -1, 7) != true) {
            i = i();
        }
        int j = 100;
        while (verrifI(j, -1, 7) != true) {
            j = j();
        }
        if (i == -1)//up
        {
            if (comptageC7(tab) == true) {
                if (regle_depassementC_7(tab) == false) {
                    return tab;
                }
                i = 1 + i;
                for (int ii = 5; ii > -1; ii--) {
                    for (int jj = 0; jj < tab[0].length; jj++) {
                        if (tab[ii][jj] != " _X_ ") {
                            tab[ii + 1][jj] = tab[ii][jj];
                            tab[ii][jj] = " _X_ ";
                            tab2[ii + 1][jj] = tab2[ii][jj];
                            tab2[ii][jj] = 0;
                        }
                    }
                }
            } else {
                System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                return tab;
            }
        }
        if (i == 7)//down
        {
            if (comptageC7(tab) == true) {
                if (regle_depassementC5_7(tab) == false) {
                    return tab;
                }
                i = i - 1;
                for (int ii = 0; ii < tab.length; ii++) {
                    for (int jj = 0; jj < tab[0].length; jj++) {
                        if (tab[ii][jj] != " _X_ ") {
                            tab[ii - 1][jj] = tab[ii][jj];
                            tab[ii][jj] = " _X_ ";
                            tab2[ii - 1][jj] = tab2[ii][jj];
                            tab2[ii][jj] = 0;
                        }
                    }
                }
            } else {
                System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                return tab;
            }
        }
        if (j == -1)//left
        {
            if (comptageL7(tab) == true) {
                if (regle_depassementL_7(tab) == false) {
                    return tab;
                }
                j = 1 + j;
                for (int ii = 0; ii < tab.length; ii++) {
                    for (int jj = 6; jj > -1; jj--) {
                        if (tab[ii][jj] != " _X_ ") {
                            tab[ii][jj + 1] = tab[ii][jj];
                            tab[ii][jj] = " _X_ ";
                            tab2[ii][jj + 1] = tab2[ii][jj];
                            tab2[ii][jj] = 0;
                        }
                    }
                }
            } else {
                System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                return tab;
            }
        }
        if (j == 7)//right
        {
            if (comptageL7(tab) == true) {
                if (regle_depassementL5_7(tab) == false) {
                    return tab;
                }
                j = j - 1;
                for (int ii = 0; ii < tab.length; ii++) {

                    for (int jj = 0; jj < tab.length; jj++) {
                        if (tab[ii][jj] != " _X_ ") {
                            tab[ii][jj - 1] = tab[ii][jj];
                            tab[ii][jj] = " _X_ ";
                            tab2[ii][jj - 1] = tab2[ii][jj];
                            tab2[ii][jj] = 0;
                        }
                    }
                }
            } else {
                System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                return tab;
            }
        }
        if (defausserVide7(tab, i, j) == false) {
            return tab;
        }
        if (defausserDomino7(tab, i, j, domino_jouable[0][0]) == false) {
            return tab;
        }
        if (positionVide(tab, i, j) == true) {

            tab = placement_plateau7(tab, tab2, domino_jouable, dominoC_jouable, i, j);
            return tab;
        } else {
            System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
            return tab;
        }
    }

    /* Like for the user, this method allows the computer to place its dominoes. */
    public static String[][] plateau_de_jeuIA(String[][] tab, int[][] tab2, int nbj, int[][] dominoC_par_nbj, String[][] domino_par_nbj, int n) {
        int nb = 0;
        int castel = comptage_position_plateauIA(tab, "Castle");
        int part1domino = comptage_position_plateauIA(tab, domino_par_nbj[nb][0]);
        int part2domino = comptage_position_plateauIA(tab, domino_par_nbj[nb][1]);
        if (castel == 0 && part1domino == 0 && part2domino == 0) {
            return tab;
        }
        int[][] dominoC_jouable = new int[1][2];
        String[][] domino_jouable = choix_part_dominoIA(domino_par_nbj[nb][0], domino_par_nbj[nb][1], dominoC_par_nbj[nb][0], dominoC_par_nbj[nb][1], dominoC_jouable);
        int i = ((int) (Math.random() * (5 - (-1) + 1) + (-1)));
        int j = ((int) (Math.random() * (5 - (-1) + 1) + (-1)));
        if (i == -1)//up
        {
            if (regle_playC(tab) == true) {
                if (tab[i + 1][j] == " _X_ ") {
                    return tab;
                }
                if (comptageC(tab) == true) {
                    if (regle_depassementC(tab) == false) {
                        return tab;
                    }
                    i = 1 + i;
                    for (int ii = 3; ii > -1; ii--) {
                        for (int jj = 0; jj < tab[0].length; jj++) {
                            if (tab[ii][jj] != " _X_ ") {
                                tab[ii + 1][jj] = tab[ii][jj];
                                tab[ii][jj] = " _X_ ";
                                tab2[ii + 1][jj] = tab2[ii][jj];
                                tab2[ii][jj] = 0;
                            }
                        }
                    }
                } else {
                    return plateau_de_jeuIA(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
                }
            } else {
                return plateau_de_jeuIA(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
            }
        }
        if (i == 5)//down
        {
            if (regle_playC5(tab) == true) {
                if (tab[i - 1][j] == " _X_ ") {
                    return tab;
                }
                if (comptageC(tab) == true) {
                    if (regle_depassementC5(tab) == false) {
                        return tab;
                    }
                    i = i - 1;
                    for (int ii = 0; ii < tab.length; ii++) {
                        for (int jj = 0; jj < tab[0].length; jj++) {
                            if (tab[ii][jj] != " _X_ ") {
                                tab[ii - 1][jj] = tab[ii][jj];
                                tab[ii][jj] = " _X_ ";
                                tab2[ii - 1][jj] = tab2[ii][jj];
                                tab2[ii][jj] = 0;
                            }
                        }
                    }
                } else {
                    return plateau_de_jeuIA(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
                }
            } else {
                return plateau_de_jeuIA(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
            }
        }
        if (j == -1)//left
        {
            if (regle_playL(tab)) {
                if (tab[i][j + 1] == " _X_ ") {
                    return tab;
                }
                if (comptageL(tab) == true) {
                    if (regle_depassementL(tab) == false) {
                        return tab;
                    }
                    j = 1 + j;
                    for (int ii = 0; ii < tab.length; ii++) {
                        for (int jj = 4; jj > -1; jj--) {
                            if (tab[ii][jj] != " _X_ ") {
                                tab[ii][jj + 1] = tab[ii][jj];
                                tab[ii][jj] = " _X_ ";
                                tab2[ii][jj + 1] = tab2[ii][jj];
                                tab2[ii][jj] = 0;
                            }
                        }
                    }
                } else {
                    return plateau_de_jeuIA(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
                }
            } else {
                return plateau_de_jeuIA(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
            }
        }
        if (j == 5)//right
        {
            if (regle_playL5(tab) == true) {
                if (tab[i][j - 1] == " _X_ ") {
                    return tab;
                }
                if (comptageL(tab) == true) {
                    if (regle_depassementL5(tab) == false) {
                        return tab;
                    }
                    j = j - 1;
                    for (int ii = 0; ii < tab.length; ii++) {

                        for (int jj = 0; jj < tab.length; jj++) {
                            if (tab[ii][jj] != " _X_ ") {
                                tab[ii][jj - 1] = tab[ii][jj];
                                tab[ii][jj] = " _X_ ";
                                tab2[ii][jj - 1] = tab2[ii][jj];
                                tab2[ii][jj] = 0;
                            }
                        }
                    }
                } else {
                    return plateau_de_jeuIA(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
                }
            } else {
                return plateau_de_jeuIA(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
            }
        }
        if (defausserVide(tab, i, j) == false) {
            return plateau_de_jeuIA(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
        }
        if (defausserDomino(tab, i, j, domino_jouable[0][0]) == false) {
            return tab;
        }
        if (positionVide(tab, i, j) == true) {

            tab = placement_plateauIA(tab, tab2, domino_jouable, dominoC_jouable, i, j);
            return tab;
        } else {
            return plateau_de_jeuIA(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
        }
    }

    public static String[][] plateau_de_jeuIAF(String[][] tab, int[][] tab2, int nbj, int[][] dominoC_par_nbj, String[][] domino_par_nbj, int n) {
        int nb = 0;
        int[][] dominoC_jouable = new int[1][2];
        String[][] domino_jouable = choix_part_dominoIA(domino_par_nbj[nb][0], domino_par_nbj[nb][1], dominoC_par_nbj[nb][0], dominoC_par_nbj[nb][1], dominoC_jouable);
        int castel = comptage_position_plateauIA(tab, "Castle");
        int part1domino = comptage_position_plateauIA(tab, domino_jouable[0][0]);
        int part2domino = comptage_position_plateauIA(tab, domino_jouable[0][1]);
        if (castel == 0 && part1domino == 0 && part2domino == 0) {
            return tab;
        }
        int i = ((int) (Math.random() * (4 - 0 + 1) + 0));
        int j = ((int) (Math.random() * (4 - 0 + 1) + 0));
        ;
        if (defausserVide(tab, i, j) == false) {
            return plateau_de_jeuIAF(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
        }
        if (defausserDomino(tab, i, j, domino_jouable[0][0]) == false) {
            return plateau_de_jeuIAF(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
        }
        if (positionVide(tab, i, j) == true) {

            tab = placement_plateauIAF(tab, tab2, domino_jouable, dominoC_jouable, i, j);
            return tab;
        } else {
            plateau_de_jeuIAF(tab, tab2, nbj, dominoC_par_nbj, domino_par_nbj, n);
        }
        return tab;
    }

    public static boolean defausserVide7(String[][] tab, int i, int j) {
        int T = 0;
        if (i > 0 && tab[i - 1][j] != " _X_ ") {
            T++;
        }
        if (i < 6 && tab[i + 1][j] != " _X_ ") {
            T++;
        }
        if (j > 0 && tab[i][j - 1] != " _X_ ") {
            T++;
        }
        if (j < 6 && tab[i][j + 1] != " _X_ ") {
            T++;
        }
        if (T >= 1) {
            return true;
        } else {
            System.out.println();
            System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
            return false;
        }
    }

    public static boolean defausserDomino7(String[][] tab, int i, int j, String part1_domino) {
        int T = 0;
        if ((i > 0 && tab[i - 1][j] == part1_domino) || (i > 0 && tab[i - 1][j] == "Castle")) {
            T++;
        }
        if ((i < 6 && tab[i + 1][j] == part1_domino) || (i < 6 && tab[i + 1][j] == "Castle")) {
            T++;
        }
        if ((j > 0 && tab[i][j - 1] == part1_domino) || (j > 0 && tab[i][j - 1] == "Castle")) {
            T++;
        }
        if ((j < 6 && tab[i][j + 1] == part1_domino) || (j < 6 && tab[i][j + 1] == "Castle")) {
            T++;
        }
        if (T >= 1) {
            return true;
        } else {
            System.out.println();
            System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
            return false;
        }
    }

    public static boolean regle_depassementC_7(String[][] tab) {
        for (int j = 0; j < tab[0].length; j++) {
            if (tab[6][j] != " _X_ ") {
                return false;
            }
        }
        return true;
    }

    public static boolean regle_depassementC5_7(String[][] tab) {
        for (int j = 0; j < tab[0].length; j++) {
            if (tab[0][j] != " _X_ ") {
                return false;
            }
        }
        return true;
    }

    public static boolean regle_depassementL_7(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i][6] != " _X_ ") {
                return false;
            }
        }
        return true;
    }

    public static boolean regle_depassementL5_7(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i][0] != " _X_ ") {
                return false;
            }
        }
        return true;
    }

    public static boolean comptageC7(String[][] tab) {
        for (int j = 0; j < tab[0].length; j++) {
            int compteur = 0;
            for (int i = 0; i < tab.length; i++) {
                if (tab[i][j] != " _X_ ") {
                    compteur++;
                }
                if (tab[6][j] == "Castle") {
                    return false;
                }
                if (tab[0][j] == "Castle") {
                    return false;
                }
                if (compteur == 7) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean comptageL7(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            int compteur = 0;
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j] != " _X_ ") {
                    compteur++;
                }
                if (tab[i][6] == "Castle") {
                    return false;
                }
                if (tab[i][0] == "Castle") {
                    return false;
                }
                if (compteur == 7) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int comptage_position_plateau7(String[][] tab, String domino_part) {
        int position = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j] == domino_part) {
                    System.out.println();
                    System.out.println("The positions where you can play around " + "[ " + (tab[i][j]) + " ]" + " are: ");
                    if (i > 0 && tab[i - 1][j] == " _X_ ") {
                        System.out.print("( " + (i - 1) + ";" + j + " )" + "\t");
                        position++;
                    }
                    if (i < 6 && tab[i + 1][j] == " _X_ ") {
                        System.out.print("( " + (i + 1) + ";" + j + " )" + "\t");
                        position++;
                    }
                    if (j > 0 && tab[i][j - 1] == " _X_ ") {
                        System.out.print("( " + i + ";" + (j - 1) + " )" + "\t");
                        position++;
                    }
                    if (j < 6 && tab[i][j + 1] == " _X_ ") {
                        System.out.print("( " + i + ";" + (j + 1) + " )" + "\t");
                        position++;
                    }
                    if (i == 0) {
                        if (comptageC7(tab) == true) {
                            if (regle_depassementC_7(tab) == true) {
                                System.out.print("( " + (i - 1) + ";" + j + " )" + "\t");
                                position++;
                            }
                        }
                    }
                    if (i == 6) {
                        if (comptageC7(tab) == true) {
                            if (regle_depassementC5_7(tab) == true) {
                                System.out.print("( " + (i + 1) + ";" + j + " )" + "\t");
                                position++;
                            }
                        }
                    }
                    if (j == 0) {
                        if (comptageL7(tab) == true) {
                            if (regle_depassementL_7(tab) == true) {
                                System.out.print("( " + i + ";" + (j - 1) + " )" + "\t");
                                position++;
                            }
                        }
                    }
                    if (j == 6) {
                        if (comptageL7(tab) == true) {
                            if (regle_depassementL5_7(tab) == true) {
                                System.out.print("( " + i + ";" + (j + 1) + " )" + "\t");
                                position++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
        return position;
    }

    public static boolean regle_playC(String[][] tab) {
        for (int j = 0; j < tab[0].length; j++) {
            if (tab[0][j] != " _X_ ") {
                return true;
            }
        }
        return false;
    }

    public static boolean regle_playC5(String[][] tab) {
        for (int j = 0; j < tab[0].length; j++) {
            if (tab[4][j] != " _X_ ") {
                return true;
            }
        }
        return false;
    }

    public static boolean regle_playL(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i][0] != " _X_ ") {
                return true;
            }
        }
        return false;
    }

    public static boolean regle_playL5(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i][4] != " _X_ ") {
                return true;
            }
        }
        return false;
    }

    public static boolean verrifS(String valeur) {
        if (valeur.equalsIgnoreCase("h") || valeur.equalsIgnoreCase("n")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean domino_par_nbj(int nb, int nbj) {
        if (nb < 0 || nb > nbj) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean positionVide(String[][] tab, int i, int j) {
        if (tab[i][j] != " _X_ ")
            return false;
        else
            return true;
    }

    public static boolean verrifI(int valeur, int min, int max) {
        if (valeur < min || valeur > max) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean defausserVide(String[][] tab, int i, int j) {
        int T = 0;
        if (i > 0 && tab[i - 1][j] != " _X_ ") {
            T++;
        }
        if (i < 4 && tab[i + 1][j] != " _X_ ") {
            T++;
        }
        if (j > 0 && tab[i][j - 1] != " _X_ ") {
            T++;
        }
        if (j < 4 && tab[i][j + 1] != " _X_ ") {
            T++;
        }
        if (T >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean defausserDomino(String[][] tab, int i, int j, String part1_domino) {
        int T = 0;
        if ((i > 0 && tab[i - 1][j] == part1_domino) || (i > 0 && tab[i - 1][j] == "Castle")) {
            T++;
        }
        if ((i < 4 && tab[i + 1][j] == part1_domino) || (i < 4 && tab[i + 1][j] == "Castle")) {
            T++;
        }
        if ((j > 0 && tab[i][j - 1] == part1_domino) || (j > 0 && tab[i][j - 1] == "Castle")) {
            T++;
        }
        if ((j < 4 && tab[i][j + 1] == part1_domino) || (j < 4 && tab[i][j + 1] == "Castle")) {
            T++;
        }
        if (T >= 1) {
            return true;
        } else {
            return false;
        }
    }

    /* Handles errors in enlarging the board on line 4*/
    public static boolean regle_depassementC(String[][] tab) {
        for (int j = 0; j < tab[0].length; j++) {
            if (tab[4][j] != " _X_ ") {
                return false;
            }
        }
        return true;
    }

    /* Handles errors in enlarging the board on line 0*/
    public static boolean regle_depassementC5(String[][] tab) {
        for (int j = 0; j < tab[0].length; j++) {
            if (tab[0][j] != " _X_ ") {
                return false;
            }
        }
        return true;
    }

    /* Handles errors in enlarging the board on the column 4*/
    public static boolean regle_depassementL(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i][4] != " _X_ ") {
                return false;
            }
        }
        return true;
    }

    /* Handles errors in enlarging the board on the column 0*/
    public static boolean regle_depassementL5(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i][0] != " _X_ ") {
                return false;
            }
        }
        return true;
    }

    /* Handles errors in enlarging the board on the columns by checking if there is a possibility to play*/
    public static boolean comptageC(String[][] tab) {
        for (int j = 0; j < tab[0].length; j++) {
            int compteur = 0;
            for (int i = 0; i < tab.length; i++) {
                if (tab[i][j] != " _X_ ") {
                    compteur++;
                }
                if (tab[4][j] == "Castle") {
                    return false;
                }
                if (tab[0][j] == "Castle") {
                    return false;
                }
                if (compteur == 5) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Handles errors in enlarging the board on the lines by checking if there is a possibility to play*/
    public static boolean comptageL(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            int compteur = 0;
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j] != " _X_ ") {
                    compteur++;
                }
                if (tab[i][4] == "Castle") {
                    return false;
                }
                if (tab[i][0] == "Castle") {
                    return false;
                }
                if (compteur == 5) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Provides the positions where the user can play their first domino*/
    public static int comptage_position_plateau(String[][] tab, String domino_part) {
        int position = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j] == domino_part) {
                    System.out.println();
                    System.out.println("The positions where you can play around " + "[ " + (tab[i][j]) + " ]" + " are: ");
                    if (i > 0 && tab[i - 1][j] == " _X_ ") {
                        System.out.print("( " + (i - 1) + ";" + j + " )" + "\t");
                        position++;
                    }
                    if (i < 4 && tab[i + 1][j] == " _X_ ") {
                        System.out.print("( " + (i + 1) + ";" + j + " )" + "\t");
                        position++;
                    }
                    if (j > 0 && tab[i][j - 1] == " _X_ ") {
                        System.out.print("( " + i + ";" + (j - 1) + " )" + "\t");
                        position++;
                    }
                    if (j < 4 && tab[i][j + 1] == " _X_ ") {
                        System.out.print("( " + i + ";" + (j + 1) + " )" + "\t");
                        position++;
                    }
                    if (i == 0) {
                        if (comptageC(tab) == true) {
                            if (regle_depassementC(tab) == true) {
                                System.out.print("( " + (i - 1) + ";" + j + " )" + "\t");
                                position++;
                            }
                        }
                    }
                    if (i == 4) {
                        if (comptageC(tab) == true) {
                            if (regle_depassementC5(tab) == true) {
                                System.out.print("( " + (i + 1) + ";" + j + " )" + "\t");
                                position++;
                            }
                        }
                    }
                    if (j == 0) {
                        if (comptageL(tab) == true) {
                            if (regle_depassementL(tab) == true) {
                                System.out.print("( " + i + ";" + (j - 1) + " )" + "\t");
                                position++;
                            }
                        }
                    }
                    if (j == 4) {
                        if (comptageL(tab) == true) {
                            if (regle_depassementL5(tab) == true) {
                                System.out.print("( " + i + ";" + (j + 1) + " )" + "\t");
                                position++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
        return position;
    }

    public static int comptage_position_plateauIA(String[][] tab, String domino_part) {
        int position = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j] == domino_part) {
                    if (i > 0 && tab[i - 1][j] == " _X_ ") {
                        position++;
                    }
                    if (i < 4 && tab[i + 1][j] == " _X_ ") {
                        position++;
                    }
                    if (j > 0 && tab[i][j - 1] == " _X_ ") {
                        position++;
                    }
                    if (j < 4 && tab[i][j + 1] == " _X_ ") {
                        position++;
                    }
                    if (i == 0) {
                        if (comptageC(tab) == true) {
                            if (regle_depassementC(tab) == true) {
                                position++;
                            }
                        }
                    }
                    if (i == 4) {
                        if (comptageC(tab) == true) {
                            if (regle_depassementC5(tab) == true) {
                                position++;
                            }
                        }
                    }
                    if (j == 0) {
                        if (comptageL(tab) == true) {
                            if (regle_depassementL(tab) == true) {
                                position++;
                            }
                        }
                    }
                    if (j == 4) {
                        if (comptageL(tab) == true) {
                            if (regle_depassementL5(tab) == true) {
                                position++;
                            }
                        }
                    }
                }
            }
        }
        return position;
    }

    /* Retrieve nbj (which is the number of players) dominos from the domino deck to play them with the players.*/
    public static String[][] regle(String[][] deck, int[][] deckC, int[][] resuC, int nbj) {
        String[][] resu = new String[nbj][2];
        for (int i = 0; i < nbj; i++) {
            resu[i][0] = deck[i][0];
            resuC[i][0] = deckC[i][0];
            resu[i][1] = deck[i][1];
            resuC[i][1] = deckC[i][1];
        }
        return resu;
    }

    /*Allow the user to choose their first domino to place on their board.*/
    public static String[][] choix_part_domino(String part1_domino, String part2_domino, int part1C_domino, int part2C_domino, int[][] dominoC) {
        String[][] domino = new String[1][2];
        System.out.println("To start placing " + "[ " + part1_domino + " ]" + " to place the first one, press 1, otherwise press 2 to " + "[ " + part2_domino + " ]");
        while (!sc.hasNextInt())
            sc.next();
        int choix = 100;
        while (verrifI(choix, 1, 2) != true) {
            choix = sc.nextInt();
            if (choix != 1 && choix != 2) {
                System.out.println("To start placing " + "[ " + part1_domino + " ]" + " to place the first one, press 1, otherwise press 2 to " + "[ " + part2_domino + " ]");
            }
        }
        if (choix == 1) {
            domino[0][0] = part1_domino;
            domino[0][1] = part2_domino;
            dominoC[0][0] = part1C_domino;
            dominoC[0][1] = part2C_domino;
            System.out.println(part1_domino);
            System.out.println();
        }
        if (choix == 2) {
            domino[0][0] = part2_domino;
            domino[0][1] = part1_domino;
            dominoC[0][0] = part2C_domino;
            dominoC[0][1] = part1C_domino;
            System.out.println(part2_domino);
            System.out.println();
        }
        return domino;
    }

    /* Just like for the user, but this time for the computer*/
    public static String[][] choix_part_dominoIA(String part1_domino, String part2_domino, int part1C_domino, int part2C_domino, int[][] dominoC) {
        String[][] domino = new String[1][2];
        int choix = ((int) (Math.random() * (2 - 1 + 1) + 1));
        if (choix != 1 && choix != 2) {
            choix_part_dominoIA(part1_domino, part2_domino, part1C_domino, part2C_domino, dominoC);
        }
        if (choix == 1) {
            domino[0][0] = part1_domino;
            domino[0][1] = part2_domino;
            dominoC[0][0] = part1C_domino;
            dominoC[0][1] = part2C_domino;
        }
        if (choix == 2) {
            domino[0][0] = part2_domino;
            domino[0][1] = part1_domino;
            dominoC[0][0] = part2C_domino;
            dominoC[0][1] = part1C_domino;
        }
        return domino;
    }

    /* This allows removing the used dominoes from the deck*/
    public static String[][] changement_deck(String[][] domino, int nbj) {
        String[][] resu = new String[domino.length - nbj][2];
        for (int i = 0; i < domino.length - nbj; i++) {
            for (int j = 0; j < 2; j++) {
                resu[i][j] = domino[i + nbj][j];
            }
        }
        return resu;
    }

    public static int[][] changement_deckC(int[][] dominoC, int nbj) {
        int[][] resuC = new int[dominoC.length - nbj][2];
        for (int i = 0; i < dominoC.length - nbj; i++) {
            for (int j = 0; j < 2; j++) {
                resuC[i][j] = dominoC[i + nbj][j];
            }
        }
        return resuC;
    }

    /* This allows the player to place the dominos*/
    public static String[][] placement_plateau(String[][] tab, int[][] tab2, String[][] domino_jouable, int[][] dominoC_jouable, int i, int j) {
        placement_I(tab, domino_jouable, i, j);
        placementC_I(tab2, dominoC_jouable, i, j);
        System.out.println();
        afficheS(tab);
        System.out.println();
        placement_J(tab, tab2, domino_jouable, dominoC_jouable, i, j);
        return tab;
    }

    public static String[][] placement_plateau7(String[][] tab, int[][] tab2, String[][] domino_jouable, int[][] dominoC_jouable, int i, int j) {
        placement_I(tab, domino_jouable, i, j);
        placementC_I(tab2, dominoC_jouable, i, j);
        System.out.println();
        afficheS(tab);
        System.out.println();
        placement_J7(tab, tab2, domino_jouable, dominoC_jouable, i, j);
        return tab;
    }

    public static String[][] placement_J7(String[][] tab, int[][] tab2, String[][] domino_jouable, int[][] dominoC_jouable, int i, int j) {
        int[][] position = new int[8][2];
        System.out.println("You can place the other part on these positions: " + "\n");
        if (i > 0 && tab[i - 1][j] == " _X_ ") {
            System.out.print("( " + (i - 1) + ";" + j + " )" + "\t");
            position[0][0] = (i - 1);
            position[0][1] = j;
        } else {
            position[0][0] = 10;
            position[0][1] = 10;
        }
        if (i < 6 && tab[i + 1][j] == " _X_ ") {
            System.out.print("( " + (i + 1) + ";" + j + " )" + "\t");
            position[1][0] = (i + 1);
            position[1][1] = j;
        } else {
            position[1][0] = 10;
            position[1][1] = 10;
        }
        if (j > 0 && tab[i][j - 1] == " _X_ ") {
            System.out.print("( " + i + ";" + (j - 1) + " )" + "\t");
            position[2][0] = i;
            position[2][1] = (j - 1);
        } else {
            position[2][0] = 10;
            position[2][1] = 10;
        }
        if (j < 6 && tab[i][j + 1] == " _X_ ") {
            System.out.print("( " + i + ";" + (j + 1) + " )" + "\t");
            position[3][0] = i;
            position[3][1] = (j + 1);
        } else {
            position[3][0] = 10;
            position[3][1] = 10;
        }
        if (i == 0) {
            if (comptageC7(tab) == true) {
                if (regle_depassementC_7(tab) == true) {
                    System.out.print("( " + (i - 1) + ";" + j + " )" + "\t");
                    position[4][0] = (i - 1);
                    position[4][1] = j;
                } else {
                    position[4][0] = 10;
                    position[4][1] = 10;
                }
            } else {
                position[4][0] = 10;
                position[4][1] = 10;
            }
        } else {
            position[4][0] = 10;
            position[4][1] = 10;
        }
        if (i == 6) {
            if (comptageC7(tab) == true) {
                if (regle_depassementC5_7(tab) == true) {
                    System.out.print("( " + (i + 1) + ";" + j + " )" + "\t");
                    position[5][0] = (i + 1);
                    position[5][1] = j;
                } else {
                    position[5][0] = 10;
                    position[5][1] = 10;
                }
            } else {
                position[5][0] = 10;
                position[5][1] = 10;
            }
        } else {
            position[5][0] = 10;
            position[5][1] = 10;
        }
        if (j == 0) {
            if (comptageL7(tab) == true) {
                if (regle_depassementL_7(tab) == true) {
                    System.out.print("( " + i + ";" + (j - 1) + " )" + "\t");
                    position[6][0] = i;
                    position[6][1] = (j - 1);
                } else {
                    position[6][0] = 10;
                    position[6][1] = 10;
                }
            } else {
                position[6][0] = 10;
                position[6][1] = 10;
            }
        } else {
            position[6][0] = 10;
            position[6][1] = 10;
        }
        if (j == 6) {
            if (comptageL7(tab) == true) {
                if (regle_depassementL5_7(tab) == true) {
                    System.out.print("( " + i + ";" + (j + 1) + " )" + "\t");
                    position[7][0] = i;
                    position[7][1] = (j + 1);
                } else {
                    position[7][0] = 10;
                    position[7][1] = 10;
                }
            } else {
                position[7][0] = 10;
                position[7][1] = 10;
            }
        } else {
            position[7][0] = 10;
            position[7][1] = 10;
        }
        if ((position[0][0] == 10 && position[0][1] == 10) && (position[1][0] == 10 && position[1][1] == 10) && (position[2][0] == 10 && position[2][1] == 10) && (position[3][0] == 10 && position[3][1] == 10) && (position[4][0] == 10 && position[4][1] == 10) && (position[5][0] == 10 && position[5][1] == 10) && (position[6][0] == 10 && position[6][1] == 10) && (position[7][0] == 10 && position[7][1] == 10)) {
            tab[i][j] = " _X_ ";
            System.out.println("There is no solution left to place your part of the domino. The domino is not considered, and you pass your turn.");
            return tab;
        }
        System.out.println("\n");
        int iii = i;
        int jjj = j;
        i = i();
        j = j();
        if ((i < -1 || i > 7) || (j < -1 || j > 7)) {
            System.out.println("You have entered the wrong values. Please try again.");
            System.out.println();
            placement_J7(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
        } else {
            if ((i == position[0][0] && j == position[0][1]) || (i == position[1][0] && j == position[1][1]) || (i == position[2][0] && j == position[2][1]) || (i == position[3][0] && j == position[3][1]) || (i == position[4][0] && j == position[4][1]) || (i == position[5][0] && j == position[5][1]) || (i == position[6][0] && j == position[6][1]) || (i == position[7][0] && j == position[7][1])) {
                if (i == -1)//up
                {
                    if (comptageC7(tab) == true) {
                        if (regle_depassementC_7(tab) == false) {
                            return tab;
                        }
                        i = 1 + i;
                        for (int ii = 5; ii > -1; ii--) {
                            for (int jj = 0; jj < tab[0].length; jj++) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii + 1][jj] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii + 1][jj] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                        return tab;
                    }
                }
                if (i == 7)//down
                {
                    if (comptageC7(tab) == true) {
                        if (regle_depassementC5_7(tab) == false) {
                            return tab;
                        }
                        i = i - 1;
                        for (int ii = 0; ii < tab.length; ii++) {
                            for (int jj = 0; jj < tab[0].length; jj++) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii - 1][jj] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii - 1][jj] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                        return tab;
                    }
                }
                if (j == -1)//left
                {
                    if (comptageL7(tab) == true) {
                        if (regle_depassementL_7(tab) == false) {
                            return tab;
                        }
                        j = 1 + j;
                        for (int ii = 0; ii < tab.length; ii++) {
                            for (int jj = 6; jj > -1; jj--) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii][jj + 1] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii][jj + 1] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                        return tab;
                    }
                }
                if (j == 7)//right
                {
                    if (comptageL7(tab) == true) {
                        if (regle_depassementL5_7(tab) == false) {
                            return tab;
                        }
                        j = j - 1;
                        for (int ii = 0; ii < tab.length; ii++) {

                            for (int jj = 0; jj < tab.length; jj++) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii][jj - 1] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii][jj - 1] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        System.out.println("The chosen position is not correct. The domino is not used, you pass your turn.");
                        return tab;
                    }
                }

                tab[i][j] = domino_jouable[0][1];
                placementC_J(tab2, dominoC_jouable, i, j);
            } else {
                System.out.println("You have entered the wrong values. Please try again.");
                System.out.println();
                placement_J7(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
            }

        }
        return tab;
    }

    /* This allows the AI to place the dominos.*/
    public static String[][] placement_plateauIA(String[][] tab, int[][] tab2, String[][] domino_jouable, int[][] dominoC_jouable, int i, int j) {
        placement_I(tab, domino_jouable, i, j);
        placementC_I(tab2, dominoC_jouable, i, j);
        placement_JIA(tab, tab2, domino_jouable, dominoC_jouable, i, j);
        return tab;
    }

    public static String[][] placement_plateauIAF(String[][] tab, int[][] tab2, String[][] domino_jouable, int[][] dominoC_jouable, int i, int j) {
        placement_I(tab, domino_jouable, i, j);
        placementC_I(tab2, dominoC_jouable, i, j);
        placement_JIAF(tab, tab2, domino_jouable, dominoC_jouable, i, j);
        return tab;
    }

    /* Place the first part of the domino.*/
    public static String[][] placement_I(String[][] tab, String[][] domino_jouable, int i, int j) {
        tab[i][j] = domino_jouable[0][0];
        return tab;
    }

    public static int[][] placementC_I(int[][] tab2, int[][] dominoC_jouable, int i, int j) {
        tab2[i][j] = dominoC_jouable[0][0];
        return tab2;
    }

    /* Place the second part of the domino without discarding*/
    public static String[][] placement_J(String[][] tab, int[][] tab2, String[][] domino_jouable, int[][] dominoC_jouable, int i, int j) {
        int[][] position = new int[8][2];
        System.out.println("You can place the other part in these positions: " + "\n");
        if (i > 0 && tab[i - 1][j] == " _X_ ") {
            System.out.print("( " + (i - 1) + ";" + j + " )" + "\t");
            position[0][0] = (i - 1);
            position[0][1] = j;
        } else {
            position[0][0] = 10;
            position[0][1] = 10;
        }
        if (i < 4 && tab[i + 1][j] == " _X_ ") {
            System.out.print("( " + (i + 1) + ";" + j + " )" + "\t");
            position[1][0] = (i + 1);
            position[1][1] = j;
        } else {
            position[1][0] = 10;
            position[1][1] = 10;
        }
        if (j > 0 && tab[i][j - 1] == " _X_ ") {
            System.out.print("( " + i + ";" + (j - 1) + " )" + "\t");
            position[2][0] = i;
            position[2][1] = (j - 1);
        } else {
            position[2][0] = 10;
            position[2][1] = 10;
        }
        if (j < 4 && tab[i][j + 1] == " _X_ ") {
            System.out.print("( " + i + ";" + (j + 1) + " )" + "\t");
            position[3][0] = i;
            position[3][1] = (j + 1);
        } else {
            position[3][0] = 10;
            position[3][1] = 10;
        }
        if (i == 0) {
            if (comptageC(tab) == true) {
                if (regle_depassementC(tab) == true) {
                    System.out.print("( " + (i - 1) + ";" + j + " )" + "\t");
                    position[4][0] = (i - 1);
                    position[4][1] = j;
                } else {
                    position[4][0] = 10;
                    position[4][1] = 10;
                }
            } else {
                position[4][0] = 10;
                position[4][1] = 10;
            }
        } else {
            position[4][0] = 10;
            position[4][1] = 10;
        }
        if (i == 4) {
            if (comptageC(tab) == true) {
                if (regle_depassementC5(tab) == true) {
                    System.out.print("( " + (i + 1) + ";" + j + " )" + "\t");
                    position[5][0] = (i + 1);
                    position[5][1] = j;
                } else {
                    position[5][0] = 10;
                    position[5][1] = 10;
                }
            } else {
                position[5][0] = 10;
                position[5][1] = 10;
            }
        } else {
            position[5][0] = 10;
            position[5][1] = 10;
        }
        if (j == 0) {
            if (comptageL(tab) == true) {
                if (regle_depassementL(tab) == true) {
                    System.out.print("( " + i + ";" + (j - 1) + " )" + "\t");
                    position[6][0] = i;
                    position[6][1] = (j - 1);
                } else {
                    position[6][0] = 10;
                    position[6][1] = 10;
                }
            } else {
                position[6][0] = 10;
                position[6][1] = 10;
            }
        } else {
            position[6][0] = 10;
            position[6][1] = 10;
        }
        if (j == 4) {
            if (comptageL(tab) == true) {
                if (regle_depassementL5(tab) == true) {
                    System.out.print("( " + i + ";" + (j + 1) + " )" + "\t");
                    position[7][0] = i;
                    position[7][1] = (j + 1);
                } else {
                    position[7][0] = 10;
                    position[7][1] = 10;
                }
            } else {
                position[7][0] = 10;
                position[7][1] = 10;
            }
        } else {
            position[7][0] = 10;
            position[7][1] = 10;
        }
        if ((position[0][0] == 10 && position[0][1] == 10) && (position[1][0] == 10 && position[1][1] == 10) && (position[2][0] == 10 && position[2][1] == 10) && (position[3][0] == 10 && position[3][1] == 10) && (position[4][0] == 10 && position[4][1] == 10) && (position[5][0] == 10 && position[5][1] == 10) && (position[6][0] == 10 && position[6][1] == 10) && (position[7][0] == 10 && position[7][1] == 10)) {
            tab[i][j] = " _X_ ";
            System.out.println("There is no solution left to place your part of the domino. The domino is not considered, and you pass your turn.");
            return tab;
        }
        System.out.println("\n");
        int iii = i;
        int jjj = j;
        i = i();
        j = j();
        if ((i < -1 || i > 5) || (j < -1 || j > 5)) {
            System.out.println("You have entered incorrect values. Please try again.");
            System.out.println();
            placement_J(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
        } else {
            if ((i == position[0][0] && j == position[0][1]) || (i == position[1][0] && j == position[1][1]) || (i == position[2][0] && j == position[2][1]) || (i == position[3][0] && j == position[3][1]) || (i == position[4][0] && j == position[4][1]) || (i == position[5][0] && j == position[5][1]) || (i == position[6][0] && j == position[6][1]) || (i == position[7][0] && j == position[7][1])) {
                if (i == -1)//up
                {
                    if (comptageC(tab) == true) {
                        if (regle_depassementC(tab) == false) {
                            return tab;
                        }
                        i = 1 + i;
                        for (int ii = 3; ii > -1; ii--) {
                            for (int jj = 0; jj < tab[0].length; jj++) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii + 1][jj] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii + 1][jj] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        System.out.println("You have entered incorrect values. Please try again.");
                        System.out.println();
                        placement_J(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
                    }
                }
                if (i == 5)//down
                {
                    if (comptageC(tab) == true) {
                        if (regle_depassementC5(tab) == false) {
                            return tab;
                        }
                        i = i - 1;
                        for (int ii = 0; ii < tab.length; ii++) {
                            for (int jj = 0; jj < tab[0].length; jj++) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii - 1][jj] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii - 1][jj] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        System.out.println("You have entered incorrect values. Please try again.");
                        System.out.println();
                        placement_J(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
                    }
                }
                if (j == -1)//left
                {
                    if (comptageL(tab) == true) {
                        if (regle_depassementL(tab) == false) {
                            return tab;
                        }
                        j = 1 + j;
                        for (int ii = 0; ii < tab.length; ii++) {
                            for (int jj = 4; jj > -1; jj--) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii][jj + 1] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii][jj + 1] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        System.out.println("You have entered incorrect values. Please try again.");
                        System.out.println();
                        placement_J(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
                    }
                }
                if (j == 5)//right
                {
                    if (comptageL(tab) == true) {
                        if (regle_depassementL5(tab) == false) {
                            return tab;
                        }
                        j = j - 1;
                        for (int ii = 0; ii < tab.length; ii++) {

                            for (int jj = 0; jj < tab.length; jj++) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii][jj - 1] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii][jj - 1] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        System.out.println("You have entered incorrect values. Please try again.");
                        System.out.println();
                        placement_J(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
                    }
                }

                tab[i][j] = domino_jouable[0][1];
                placementC_J(tab2, dominoC_jouable, i, j);
            } else {
                System.out.println("You have entered incorrect values. Please try again.");
                System.out.println();
                placement_J(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
            }

        }
        return tab;
    }

    public static String[][] placement_JIA(String[][] tab, int[][] tab2, String[][] domino_jouable, int[][] dominoC_jouable, int i, int j) {
        int[][] position = new int[8][2];
        if (i > 0 && tab[i - 1][j] == " _X_ ") {
            position[0][0] = (i - 1);
            position[0][1] = j;
        } else {
            position[0][0] = 10;
            position[0][1] = 10;
        }
        if (i < 4 && tab[i + 1][j] == " _X_ ") {
            position[1][0] = (i + 1);
            position[1][1] = j;
        } else {
            position[1][0] = 10;
            position[1][1] = 10;
        }
        if (j > 0 && tab[i][j - 1] == " _X_ ") {
            position[2][0] = i;
            position[2][1] = (j - 1);
        } else {
            position[2][0] = 10;
            position[2][1] = 10;
        }
        if (j < 4 && tab[i][j + 1] == " _X_ ") {
            position[3][0] = i;
            position[3][1] = (j + 1);
        } else {
            position[3][0] = 10;
            position[3][1] = 10;
        }
        if (i == 0) {
            if (comptageC(tab) == true) {
                if (regle_depassementC(tab) == true) {
                    position[4][0] = (i - 1);
                    position[4][1] = j;
                } else {
                    position[4][0] = 10;
                    position[4][1] = 10;
                }
            } else {
                position[4][0] = 10;
                position[4][1] = 10;
            }
        } else {
            position[4][0] = 10;
            position[4][1] = 10;
        }
        if (i == 4) {
            if (comptageC(tab) == true) {
                if (regle_depassementC5(tab) == true) {
                    position[5][0] = (i + 1);
                    position[5][1] = j;
                } else {
                    position[5][0] = 10;
                    position[5][1] = 10;
                }
            } else {
                position[5][0] = 10;
                position[5][1] = 10;
            }
        } else {
            position[5][0] = 10;
            position[5][1] = 10;
        }
        if (j == 0) {
            if (comptageL(tab) == true) {
                if (regle_depassementL(tab) == true) {
                    position[6][0] = i;
                    position[6][1] = (j - 1);
                } else {
                    position[6][0] = 10;
                    position[6][1] = 10;
                }
            } else {
                position[6][0] = 10;
                position[6][1] = 10;
            }
        } else {
            position[6][0] = 10;
            position[6][1] = 10;
        }
        if (j == 4) {
            if (comptageL(tab) == true) {
                if (regle_depassementL5(tab) == true) {
                    position[7][0] = i;
                    position[7][1] = (j + 1);
                } else {
                    position[7][0] = 10;
                    position[7][1] = 10;
                }
            } else {
                position[7][0] = 10;
                position[7][1] = 10;
            }
        } else {
            position[7][0] = 10;
            position[7][1] = 10;
        }
        if ((position[0][0] == 10 && position[0][1] == 10) && (position[1][0] == 10 && position[1][1] == 10) && (position[2][0] == 10 && position[2][1] == 10) && (position[3][0] == 10 && position[3][1] == 10) && (position[4][0] == 10 && position[4][1] == 10) && (position[5][0] == 10 && position[5][1] == 10) && (position[6][0] == 10 && position[6][1] == 10) && (position[7][0] == 10 && position[7][1] == 10)) {
            tab[i][j] = " _X_ ";
            return tab;
        }
        int iii = i;
        int jjj = j;
        i = ((int) (Math.random() * (5 - (-1) + 1) + (-1)));
        j = ((int) (Math.random() * (5 - (-1) + 1) + (-1)));
        if ((i < -1 || i > 5) || (j < -1 || j > 5)) {
            placement_JIA(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
        } else {
            if ((i == position[0][0] && j == position[0][1]) || (i == position[1][0] && j == position[1][1]) || (i == position[2][0] && j == position[2][1]) || (i == position[3][0] && j == position[3][1]) || (i == position[4][0] && j == position[4][1]) || (i == position[5][0] && j == position[5][1]) || (i == position[6][0] && j == position[6][1]) || (i == position[7][0] && j == position[7][1])) {
                if (i == -1)//up
                {
                    if (comptageC(tab) == true) {
                        if (regle_depassementC(tab) == false) {
                            return tab;
                        }
                        i = 1 + i;
                        for (int ii = 3; ii > -1; ii--) {
                            for (int jj = 0; jj < tab[0].length; jj++) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii + 1][jj] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii + 1][jj] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        return placement_JIA(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
                    }
                }
                if (i == 5)//down
                {
                    if (comptageC(tab) == true) {
                        if (regle_depassementC5(tab) == false) {
                            return tab;
                        }
                        i = i - 1;
                        for (int ii = 0; ii < tab.length; ii++) {
                            for (int jj = 0; jj < tab[0].length; jj++) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii - 1][jj] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii - 1][jj] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        return placement_JIA(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
                    }
                }
                if (j == -1)//left
                {
                    if (comptageL(tab) == true) {
                        if (regle_depassementL(tab) == false) {
                            return tab;
                        }
                        j = 1 + j;
                        for (int ii = 0; ii < tab.length; ii++) {
                            for (int jj = 4; jj > -1; jj--) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii][jj + 1] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii][jj + 1] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        return placement_JIA(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
                    }
                }
                if (j == 5)//right
                {
                    if (comptageL(tab) == true) {
                        if (regle_depassementL5(tab) == false) {
                            return tab;
                        }
                        j = j - 1;
                        for (int ii = 0; ii < tab.length; ii++) {

                            for (int jj = 0; jj < tab.length; jj++) {
                                if (tab[ii][jj] != " _X_ ") {
                                    tab[ii][jj - 1] = tab[ii][jj];
                                    tab[ii][jj] = " _X_ ";
                                    tab2[ii][jj - 1] = tab2[ii][jj];
                                    tab2[ii][jj] = 0;
                                }
                            }
                        }
                    } else {
                        return placement_JIA(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
                    }
                }

                tab[i][j] = domino_jouable[0][1];
                placementC_J(tab2, dominoC_jouable, i, j);
            } else {
                placement_JIA(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
            }

        }
        return tab;
    }

    public static String[][] placement_JIAF(String[][] tab, int[][] tab2, String[][] domino_jouable, int[][] dominoC_jouable, int i, int j) {
        int[][] position = new int[4][2];
        if (i > 0 && tab[i - 1][j] == " _X_ ") {
            position[0][0] = (i - 1);
            position[0][1] = j;
        } else {
            position[0][0] = 10;
            position[0][1] = 10;
        }
        if (i < 4 && tab[i + 1][j] == " _X_ ") {
            position[1][0] = (i + 1);
            position[1][1] = j;
        } else {
            position[1][0] = 10;
            position[1][1] = 10;
        }
        if (j > 0 && tab[i][j - 1] == " _X_ ") {
            position[2][0] = i;
            position[2][1] = (j - 1);
        } else {
            position[2][0] = 10;
            position[2][1] = 10;
        }
        if (j < 4 && tab[i][j + 1] == " _X_ ") {
            position[3][0] = i;
            position[3][1] = (j + 1);
        } else {
            position[3][0] = 10;
            position[3][1] = 10;
        }

        if ((position[0][0] == 10 && position[0][1] == 10) && (position[1][0] == 10 && position[1][1] == 10) && (position[2][0] == 10 && position[2][1] == 10) && (position[3][0] == 10 && position[3][1] == 10)) {
            tab[i][j] = " _X_ ";
            return tab;
        }
        int iii = i;
        int jjj = j;
        i = ((int) (Math.random() * (4 - 0 + 1) + 0));
        j = ((int) (Math.random() * (4 - 0 + 1) + 0));
        if ((i == position[0][0] && j == position[0][1]) || (i == position[1][0] && j == position[1][1]) || (i == position[2][0] && j == position[2][1]) || (i == position[3][0] && j == position[3][1])) {
            tab[i][j] = domino_jouable[0][1];
            placementC_J(tab2, dominoC_jouable, i, j);
        } else {
            placement_JIAF(tab, tab2, domino_jouable, dominoC_jouable, iii, jjj);
        }
        return tab;
    }

    public static int[][] placementC_J(int[][] tab2, int[][] dominoC_jouable, int i, int j) {
        tab2[i][j] = dominoC_jouable[0][1];
        return tab2;
    }

    /* Method to return the score.*/
    public static int winner(String[][] plateau, int[][] plateauC) {
        int c = score(plateau, plateauC, "champ");
        int p = score(plateau, plateauC, "prairie");
        int m = score(plateau, plateauC, "mer");
        int ma = score(plateau, plateauC, "maree");
        int mi = score(plateau, plateauC, "mine");
        int f = score(plateau, plateauC, "foret");
        int score = c + p + m + ma + mi + f;
        return score;
    }

    public static int winner7(String[][] plateau, int[][] plateauC) {
        int c = score7(plateau, plateauC, "champ");
        int p = score7(plateau, plateauC, "prairie");
        int m = score7(plateau, plateauC, "mer");
        int ma = score7(plateau, plateauC, "maree");
        int mi = score7(plateau, plateauC, "mine");
        int f = score7(plateau, plateauC, "foret");
        int score = c + p + m + ma + mi + f;
        return score;
    }

    /* Method to calculate the score of each part of dominos.*/
    public static int score(String[][] tab, int[][] tab2, String part_domino) {
        String[][] tabpoint = tab;
        int[][] tab2point = tab2;
        boolean[][] tabb = new boolean[5][5];
        int compteur = 0;
        int couronne = 0;
        int score = 0;
        int addscore = 0;
        int[] compteurC = new int[25];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                compteur = expansion(tab2point, tabpoint, tabb, part_domino, i, j, 0, compteurC);
                for (int ii = 0; ii < compteurC.length; ii++) {
                    couronne += compteurC[ii];
                    compteurC[ii] = 0;
                }
                score = compteur * couronne;
                addscore += score;
                couronne = 0;
            }
        }
        System.out.println("Score of " + part_domino + " is " + addscore);
        return addscore;
    }

    /* This recursive method allows finding all the adjacent domino parts.*/
    public static int expansion(int[][] tab2point, String[][] tab, boolean[][] tabb, String part_domino, int i, int j, int compteur, int[] compteurC) {
        if ((i < 0 || i > 4) || (j < 0 || j > 4)) {
            return compteur;
        }
        if (tabb[i][j] == true) {
            return compteur;
        }
        if (tab[i][j] != part_domino) {
            return compteur;
        }
        if (tab[i][j] == part_domino) {
            compteurC[compteur] = tab2point[i][j];
            compteur++;
            tabb[i][j] = true;

            compteur = expansion(tab2point, tab, tabb, part_domino, i + 1, j, compteur, compteurC);
            compteur = expansion(tab2point, tab, tabb, part_domino, i - 1, j, compteur, compteurC);
            compteur = expansion(tab2point, tab, tabb, part_domino, i, j + 1, compteur, compteurC);
            compteur = expansion(tab2point, tab, tabb, part_domino, i, j - 1, compteur, compteurC);
        }

        return compteur;
    }

    public static int score7(String[][] tab, int[][] tab2, String part_domino) {
        String[][] tabpoint = tab;
        int[][] tab2point = tab2;
        boolean[][] tabb = new boolean[7][7];
        int compteur = 0;
        int couronne = 0;
        int score = 0;
        int addscore = 0;
        int[] compteurC = new int[25];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                compteur = expension7(tab2point, tabpoint, tabb, part_domino, i, j, 0, compteurC);
                for (int ii = 0; ii < compteurC.length; ii++) {
                    couronne += compteurC[ii];
                    compteurC[ii] = 0;
                }
                score = compteur * couronne;
                addscore += score;
                couronne = 0;
            }
        }
        System.out.println("Score of " + part_domino + " is " + addscore);
        System.out.println();
        return addscore;
    }

    public static int expension7(int[][] tab2point, String[][] tab, boolean[][] tabb, String part_domino, int i, int j, int compteur, int[] compteurC) {
        if ((i < 0 || i > 6) || (j < 0 || j > 6)) {
            return compteur;
        }
        if (tabb[i][j] == true) {
            return compteur;
        }
        if (tab[i][j] != part_domino) {
            return compteur;
        }
        if (tab[i][j] == part_domino) {
            compteurC[compteur] = tab2point[i][j];
            compteur++;
            tabb[i][j] = true;

            compteur = expension7(tab2point, tab, tabb, part_domino, i + 1, j, compteur, compteurC);
            compteur = expension7(tab2point, tab, tabb, part_domino, i - 1, j, compteur, compteurC);
            compteur = expension7(tab2point, tab, tabb, part_domino, i, j + 1, compteur, compteurC);
            compteur = expension7(tab2point, tab, tabb, part_domino, i, j - 1, compteur, compteurC);
        }

        return compteur;
    }

    /* Additional rule*/
    public static int empire_du_milieu(String[][] plateau) {
        if (plateau[plateau.length / 2][plateau[0].length / 2] == "Castle") {
            System.out.println("You have successfully implemented the additional rule \"Empire du milieu.\"" + "\n");
            return 10;
        } else {
            return 0;
        }
    }

}