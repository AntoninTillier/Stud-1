import java.util.Scanner;

public class Conditional_Statements_And_Loops {
    public static void main(String[] args) {
        // Exercise 1: Sorting
        // Write a program that asks the user for 3 integer values, assigns the largest value to a variable named
        // 'Grand', the intermediate value to a variable named 'Moyen', and the smallest of the three values to a variable named 'Petit',
        // then displays the values of Grand, Moyen, and Petit.
        Scanner sc = new Scanner(System.in); int a, b, c;
        int Grand, Moyen, Petit;
        System.out.print("Enter the first value:");
        a = sc.nextInt();
        System.out.print("Enter the second value:");
        b = sc.nextInt();
        System.out.print("Enter the third value:");
        c = sc.nextInt();
        if (a >= b && a >= c) {
            Grand = a;
            if (b >= c) {
                Moyen = b;
                Petit = c;
            } else {
                Moyen = c;
                Petit = b;
            }
        } else if (b >= a && b>= c) {
            Grand = b;
            if (a >= c) {
                Moyen = a;
                Petit = c;
            } else {
                Moyen = c;
                Petit = a;
            }
        } else {
            Grand = c;
            if (a >= b) {
                Moyen = a;
                Petit = b;
            } else {
                Moyen = b;
                Petit = a;
            }
        }
        System.out.println("Grand = " + Grand + ", Moyen = " + Moyen + " et Petit = " + Petit);
        sc.close();

        // Exercise 2: Simple Calculator
        // Write a program that asks for a first integer value, an operation (+ - * or /),
        // a second integer value, and displays the result of the operation on the two values.
        Scanner sc = new Scanner(System.in);
        int a, b;
        char op;
        System.out.print("Enter the first operand:");
        a = sc.nextInt();
        System.out.print("Enter the operator (+ - * /):");
        sc.nextLine();
        op = sc.nextLine().charAt(0);
        System.out.print("Enter the second operand:");
        b = sc.nextInt();
        if (op == '+') {
            System.out.println("\tThe result of "+a+" "+op+" "+b+" = "+(a+b));
        }
        if (op == '-'){
            System.out.println("\tThe result of "+a+" "+op+" "+b+" = "+(a-b));
        }
        if (op == '*') {
            System.out.println("\tThe result of "+a+" "+op+" "+b+" = "+(a*b));
        }
        if (op == '/') {
            System.out.println("\tThe result of "+a+" "+op+" "+b+" = "+(a/b));
        }
        sc.close();

        // Exercise 3: Solving a Second-Degree Equation
        // Write a program that inputs three real numbers a, b, and c, then determines and displays the set of solutions
        // of the equation ax^2 + bx + c = 0 in R (the real numbers).
        Scanner sc = new Scanner(System.in);
        double a, b, c;
        System.out.print("Introduire la valeur de a: ");
        a = sc.nextDouble();
        System.out.print("Introduire la valeur de b: ");
        b = sc.nextDouble();
        System.out.print("Introduire la valeur de c: "); c = sc.nextDouble();
        if (a == 0) {
            if (b == 0) {
                if (c == 0){
                    System.out.println("All real numbers are solutions.");
                } else {
                    System.out.println("No solution.");
                }
            } else {
                System.out.println("A unique real solution: " + (-c / b));
            }
        }
        double delta = (b * b - 4 * a * c);
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / 2 / a;
            double x2 = (-b - Math.sqrt(delta)) / 2 / a;
            System.out.println("The equation has 2 solutions " + x1 + " and "+ x2);
        } else if (delta == 0) {
            System.out.println("The equation has 1 double solution "+ (-b / 2 / a));
        } else {
            System.out.println("The equation has no solution in R (the set of real numbers).");
        }
        sc.close();

        // Exercise 4: Guessing a Number
        // Write a program that randomly selects an integer N between 1 and 100, then gives the user 6 chances to guess this number.
        // After each user's guess n, the program should respond with 'Won!' (if n = N), 'Greater' (if n < N), or 'Smaller' (if n > N).
        // In case the user hasn't guessed the number, the program should display the sentence:
        // 'The number to guess was N' (N being the value of the number to guess).
        Scanner sc = new Scanner(System.in);
        int i, tirage, choix;
        Random rd = new Random();
        tirage = rd.nextInt(100);
        System.out.println("Guess a number randomly chosen in [1, 100] ?");
        for (i=1;i<=6;i++) {
            System.out.print("\tEnter your number (attempt " + i + " ) : "); choix = sc.nextInt();
            if (choix == tirage) {
                System.out.println("You won!");
                break;
            } else if (choix < tirage)
                System.out.println("Greater"); else
        }
        if (i == 7)
            System.out.println("Smaller");
        System.out.println("The number to guess was " + tirage);
        sc.close();

        // Exercise 5: Sum Calculation
        // Write a program that calculates the sum from k=1 to n of k^4 for an integer n provided by the user.
        Scanner sc = new Scanner(System.in);
        int k, n;
        long S;
        System.out.println("Calculation of s(n) = sum(k^4), 1 <= k <= n");
        System.out.print("\tEnter the value of n:");
        n = sc.nextInt();
        S = 0;
        for (k=1;k<=n;k++) {
            S = S + k * k * k * k;
        }
        System.out.print("\tS(" + n + ") = " + S);
        sc.close();
        // Write a program that inputs an integer 𝑛 ≥ 1 and calculates and displays the results of the following double sums
        // sum(1/(i+j)), 1 <= i < j <= n and sum(1/(i+j)), 1 <= i <= j <= n
        Scanner sc = new Scanner(System.in);
        double S1, S2;
        int i, j, n;
        System.out.println("Calculation of\ts1(n) = sum(1/(i+j)), 1 <= i < j <= n et\n\t\ts2(n) = sum(1/(i+j)), 1 <= i <= j <= n");
        System.out.print("Introduire la valeur de n : ");
        n = sc.nextInt();
        S1 = 0;
        for (i=1;i<=n-1;i++) {
            for (j=i+1;j<=n;j++) {
                S1 = S1 + ((double) 1 / (i + j));
            }
        }
        System.out.println("\tS1(" + n + ") = " + S1);
        S2 = 0;
        for (i=1;i<=n;i++) {
            for (j=i;j<=n;j++) {
                S2 = S2 + ((double) 1 / (i + j));
            }
        }
        System.out.println("\tS2(" + n + ") = " + S2);
        sc.close();

        // Exercise 6: Interest Calculation
        // Write a program that calculates how many years you must wait at a minimum to have at least sommeFinale euros
        // in your account if you deposit sommeInitiale euros at the beginning of the first year and
        // earn pourcentageInteret euros in interest at the end of each year, with the interest being added to your account.
        Scanner sc = new Scanner(System.in);
        double sommeInitiale, sommeFinale, pourcentageInteret;
        int annees = 0;
        System.out.println("Interest Calculation!");
        System.out.print("\tEnter your initial amount:");
        sommeInitiale = sc.nextDouble();
        double somme = sommeInitiale;
        System.out.print("\tEnter your final amount:");
        sommeFinale = sc.nextDouble();
        System.out.print("\tEnter the percentage:");
        pourcentageInteret = sc.nextDouble();
        for (;;) {
            if (somme >= sommeFinale) {
                break;
            } else {
                somme += somme * pourcentageInteret / 100;
                annees++;
            }
        }
        System.out.println("You need to wait " + annees+ " year(s) to have at least  " + sommeFinale + " euros");
        System.out.println("with an initial deposit of " + sommeInitiale+ " euros and an annual interest of "+ pourcentageInteret + "%.");
    }
    sc.close();
}