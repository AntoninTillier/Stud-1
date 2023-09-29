public class Recursivite {
    static final int N = 20;
    static int i;
    static long tab[][] = new long[N][N];
    static long dejaCalcule[] = new long[N];
    static int nbAppels;

    public static long fib(int n) {
        nbAppels++;
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else return fib(n - 1) + fib(n - 2);
    }

    public static long fib2(int n) {
        nbAppels++;
        tab[i][n]++;
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else return fib(n - 1) + fib(n - 2);
    }

    public static long fib3(int n) {
        if (dejaCalcule[n] != -1)
            return dejaCalcule[n];
        return dejaCalcule[n] = fib(n - 1) + fib(n - 2);
    }

    public static void afficheAppels(int i) {
        int j;
        System.out.print("Nb appels à");
        for (j = 0; j < N; j++)
            System.out.print("\tFib(" + j + ")");
        System.out.print("\n\t");
        for (j = 0; j < N; j++)
            System.out.printf("\t%6d", tab[i][j]);
        System.out.print("\n\n");

    }

    public static double puis(double r, int n) {
        if (n == 0) return 1;
        else return r * puis(r, n - 1);
    }

    public static double puis2(double r, int n) {
        if (n < 0)
            if (r == 0) return 0;
            else return puis2(1 / r, -n);
        else if (n == 0) return 1;
        else if (((n / 2) % 2) == 1) return r * puis2(r, n - 1);
        else return puis2(r * r, n / 2);
    }

    static final int NBDISQUES = 4;
    static int nbDeplacements = 0;

    public static void tourHanoi(int D, char deb, char inter, char fin) {
        if (D > 0) {
            tourHanoi(D - 1, deb, fin, inter);
            System.out.println("Coup " + (++nbDeplacements) + " : " + deb + " -> " + fin);
            tourHanoi(D - 1, inter, deb, fin);
        }
    }

    public static boolean isPalindrome(String chaine) {
        if (chaine.length() < 2) return true;
        else
            return ((chaine.charAt(0) == chaine.charAt(chaine.length() - 1)) && estPalindrome(chaine.substring(1, chaine.length() - 1)));
    }

    static final int N = 10;
    static int Tab[] = new int[N];

    public static void initTab() {
        int i;
        Random aleaGen = new Random();
        for (i = 0; i < N; i++) {
            Tab[i] = aleaGen.nextInt(100);
        }
    }

    public static public void afficheTab() {
        int i;
        for (i = 0; i < N; i++)
            System.out.print(Tab[i] + " - ");
        System.out.println();
    }

    public static int maxSeq(int i, int j) {
        if (i == j) return Tab[i];
        else if (Tab[i] >= Tab[j]) return maxSeq(i, j - 1);
        else return maxSeq(i + 1, j);
    }

    public static int max(int i, int j) {
        if (i < j) return j;
        else return i;
    }

    public static int maxDicho(int i, int j) {
        int m;
        if (i == j) return Tab[i];
        else {
            m = (i + j) / 2;
            return max(maxDicho(i, m), maxDicho(m + 1, j));
        }
    }


    public static void main(String[] args) {
        // Consider the following sequence, called the Fibonacci sequence, defined as:
        //F0 = 0
        //F1 = 1
        //Fn = Fn-1 + Fn-2
        //Question: Write a method, named Fibonacci, that calculates F5 using two recursive calls.
        int i = 0;
        for (i = 0; i < N; i++) {
            nbAppels = 0;
            System.out.println("Fib( " + i + " ) = " + fib(i) + " et Nb Appels = " + nbAppels);
        }
        //Question: Rewrite the previous method to verify your previous results.
        // In other words, when Fn is calculated, count the total number of calls made and also count the number of
        // calls made to Fk for each 0 < k < n.
        int i = 0;
        for (i = 0; i < N; i++) {
            nbAppels = 0;
            System.out.println("Fib( " + i + " ) = " + fib(i) + " et Nb Appels = " + nbAppels);
            afficheAppels(i);
        }
        // Question: Rewrite the previous method in such a way that any calculated value (Fi, i <= n)
        // for the first time is stored in a one-dimensional array, and for subsequent times, it is simply retrieved
        // (read) from the array.
        int i = 0;
        for (i = 0; i < N; i++)
            dejaCalcule[i] = -1;
        dejaCalcule[0] = 0;
        dejaCalcule[1] = 1;
        for (i = 0; i < N; i++)
            System.out.println("Fibonacci( " + i + ") = " + fib(i));
        // Question: Write a program called Power that uses a recursive function to calculate the power to an integer
        // exponent N (positive or zero) of a real number R as defined previously.
        double R = 0.6;
        int N = 4;
        System.out.println(R + "**" + N + " = " + puis(R, N));
        // Question: Redo the previous exercise using the following recurrence relation:
        // R^0 = 1
        // R^N = R * R^(N-1) if N is odd
        // R^N = (R * R)^(N/2) if N is even
        System.out.println(r + "**" + n + " = " + puis2(r, n));
        // Question: Write a recursive method named tourHanoi that displays the moves to solve the problem.
        tourHanoi(NBDISQUES, 'A', 'B', 'C');
        // Question: Write a program called Palindrome that uses a recursive function called isPalindrome to determine
        // whether a given text is a palindrome or not based on the solution mentioned above.
        String str = "laval";
        System.out.println("Est un palindrome = " + isPalindrome(str));
        // Question: Write a program that:
        //Constructs an array (a global variable that should not be passed as a parameter to avoid increasing execution time)
        // filled with integer values (unordered).
        //Follows the principles of sequential and binary search to find the maximum value present in the array."
        initTab();
        afficheTab();
        System.out.println("Max par Recherche Sequentielle : " + maxSeq(0, N - 1));
        System.out.println("Max par Recherche Dichotomique : " + maxDicho(0, N - 1));
    }
}