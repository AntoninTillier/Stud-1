import java.util.Scanner;
import java.text.DecimalFormat;

public class strings_And_Characters {
    public static String saisie() {
        System.out.println("entrer une chaine de caracteres");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void affiche(String s) {
        System.out.println("chaine saisie : " + s + " ; longueur totale : " + s.length());
    }

    public static void afficheVertical(String s) {
        for (int i = 0; i < s.length(); i++)
            System.out.println(s.charAt(i));
    }

    public static int frequenceCarac(String s, char c) {
        int nb = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == c)
                nb++;
        return nb;
    }

    public static int nbVoyelles(String s) {
        int nb = 0;
        nb = frequenceCarac(s, 'a') + frequenceCarac(s, 'e') + frequenceCarac(s, 'i') + frequenceCarac(s, 'o') + frequenceCarac(s, 'u') + frequenceCarac(s, 'y');
        return nb;
    }

    public static String remplace(String s, char c1, char c2) {
        String ns = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c1)
                ns = ns + c2;
            else
                ns = ns + s.charAt(i);
        }
        return ns;
    }

    public static boolean contient(String ch, String sousCh) {
        if (ch.indexOf(sousCh) < 0)
            return false;
        else
            return true;
    }

    public static int nbVoyellesV2(String s) {
        int nb = 0;
        String voyelles = "aeiouy";
        for (int i = 0; i < s.length(); i++)
            if (voyelles.indexOf(s.charAt(i)) >= 0)
                nb++;
        return nb;
    }

    public static boolean estPalindrome(String ch) {
        boolean resultat = true;
        int longueur = ch.length();
        for (int i = 0; i < longueur / 2; i++)
            if (ch.charAt(i) != ch.charAt(longueur - 1 - i))
                resultat = false;
        System.out.println(resultat);
        return resultat;
    }

    public static String supprimeBlancs(String s) {
        String ns = "";
        int debut = 0;
        while (s.charAt(debut) == ' ')
            debut++;
        int fin = s.length() - 1;
        while (s.charAt(fin) == ' ')
            fin--;
        ns = ns + s.charAt(debut);
        for (int i = debut + 1; i <= fin; i++)
            if (s.charAt(i - 1) != ' ' || s.charAt(i) != ' ')
                ns = ns + s.charAt(i);
        affiche(ns);
        return ns;
    }

    public static int nbMots(String s) {
        int nbMots = 1;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == ' ' || s.charAt(i) == '\'')
                nbMots++;
        System.out.println(nbMots);
        return nbMots;
    }

    public static boolean memeNbParentheses(String s) {
        int nbOuvrantes = 0;
        int nbFermantes = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                nbOuvrantes++;
            if (s.charAt(i) == ')')
                nbFermantes++;
        }
        return nbOuvrantes == nbFermantes;
    }

    public static boolean verifieParentheses(String s) {
        if (memeNbParentheses(s) == true) {
            return true;
        } else {
            return false;
        }
    }

    public static String saisiePswd() {
        Scanner sc = new Scanner(System.in);
        String pswd;
        String caracSpec = "&@#$%^+=*";
        boolean present;
        do {
            System.out.println("Mot de passe, au moins 8 caractères et un caractère spécial:");
            pswd = sc.nextLine();
            present = false;
            for (int i = 0; i < pswd.length(); i++)
                if (caracSpec.indexOf(pswd.charAt(i)) >= 0) {
                    present = true;
                    break;
                }
        }
        while (pswd.length() < 8 || !present);
        return pswd;
    }

    public static void saisieTab(String t[]) {
        for (int i = 0; i < t.length; i++)
            t[i] = saisiePswd();
    }

    public static int taillePlusCourt(String t[]) {
        int min = t[0].length();
        for (int i = 1; i < t.length; i++)
            if (min > t[i].length())
                min = t[i].length();
        return min;
    }

    public static int taillePlusLong(String t[]) {
        for (int i = 0; i < t.length; i++)
            System.out.println(t[i].length());
        int max = t[0].length();
        for (int i = 1; i < t.length; i++)
            if (max < t[i].length())
                max = t[i].length();
        return max;
    }

    public static double tailleMoyenne(String t[]) {
        double moy = 0;
        for (int i = 0; i < t.length; i++)
            moy += t[i].length();
        return moy / t.length;
    }

    public static int nbCaracSpec(String mot) {
        int nb = 0;
        String caracSpec = "&@#$%^+=*";
        for (int i = 0; i < mot.length(); i++)
            if (caracSpec.indexOf(mot.charAt(i)) >= 0)
                nb++;
        return nb;
    }

    public static void lePlusDeCaracSpec(String t[]) {
        int max = nbCaracSpec(t[0]);
        int indice = 0;
        for (int i = 1; i < t.length; i++)
            if (max < nbCaracSpec(t[i])) {
                max = nbCaracSpec(t[i]);
                indice = i;
            }
        System.out.println("Max de caractères spéciaux: " + max + " password: " + t[indice]);
    }

    public static void afficheMinMax(String s) {
        String tab[] = s.split("cm ;");
        double min = Double.parseDouble(tab[0]);
        double max = min;
        for (int i = 1; i < tab.length; i++) {
            double elt = Double.
                    parseDouble(tab[i]);
            if (elt < min)
                min = elt;
            if (elt > max)
                max = elt;
        }
        System.out.println("minimum : " + min);
        System.out.println("maximum : " + max);
    }


    public static void main(String[] args) {
        // Exercise 1: String Manipulation
        char a = 'a';
        char c = 'c';
        String s = saisie();
        affiche(s);
        afficheVertical(s);
        frequenceCarac(s, a);
        nbVoyelles(s);
        remplace(s, a, c);
        System.out.println(contient(s, "ciel"));
        nbVoyellesV2(s);

        // Exercise 2: Palindrome
        String s = saisie();
        estPalindrome(s);
        inverse(s);

        // Exercise 3: Eliminating Extra Spaces
        String s = saisie();
        supprimeBlancs(s);
        nbMots(s);

        // Exercise 4: Parentheses
        String s = saisie();
        System.out.println(memeNbParentheses(s));
        System.out.println();
        System.out.println(verifieParentheses(s));


        // Exercise 5: Password Array
        String[] t = {""};
        saisieTab(t);
        saisiePswd();
        taillePlusCourt(t);
        taillePlusLong(t);
        tailleMoyenne(t);
        String s = "";
        nbCaracSpec(s);
        lePlusDeCaracSpec(t);

        // Exercise 6: Using Split
        afficheMinMax("1.93cm ; 2.2cm ; 1.65cm ; 1.76cm ; 2.07cm ; 1.8cm ;");
    }
}