import java.util.Scanner;

public class Conditional_Statements_And_Loops_2 {
    public static void main(String[] args) {
        // Exercise 1: Finding GCD of Two Integers
        // Calculation of the GCD of two integers using subtraction.
        int a, b;
        Scanner sc = new Scanner(System.in);
        System.out.println("Calculation, by subtraction, of the GCD of 2 integers A and B.");
        System.out.print("\tEnter the value of A:");
        a = sc.nextInt();
        System.out.print("\tEnter the value of B:");
        b = sc.nextInt();
        int x=a;
        int y=b;
        while (x != y) {
            if (x>y)
                x -= y;
            else
                y -= x;
        }
        System.out.println("GCD(" + a + ", " + b + ") = " + x);
        sc.close();
        // Calculation of the GCD of two integers using the Euclidean algorithm.
        int a, b;
        Scanner sc = new Scanner(System.in);
        System.out.println("Calculation of the GCD of 2 integers A and B using the Euclidean algorithm.");
        System.out.print("\tEnter the value of A:");
        a = sc.nextInt();
        System.out.print("\tEnter the value of B:");
        b = sc.nextInt();
        int x=a;
        int y=b;
        int tmp;
        while ((x%y)!=0){
            tmp = y;
            y = x % y;
            x = tmp;
        }
        System.out.println("GCD(" + a + ", " + b + ") = " + y);
        sc.close();

        // Exercise 2: Egyptian Multiplication
        // XY = 0 si Y = 0
        // XY = X(Y – 1) + X if Y is odd
        // XY = 2X(Y/2) if Y is even
        // Write a program that calculates the result of multiplying two integers using the method above.
        Scanner sc = new Scanner(System.in);
        System.out.println("Egyptian multiplication of two integers X and Y.");
        System.out.print("\tEnter the value of X:");
        int x = sc.nextInt();
        System.out.print("\tEnter the value of Y :");
        int y = sc.nextInt();
        int a=x;
        int b=y;
        int S=0;
        while (b != 0) {
            if ((b%2)!=0) {
                b -= 1;
                S += a;
            } else {
                a *= 2;
                b /= 2;
            }
        }
        System.out.println(x + " * " + y + " = " + S);
        sc.close();
        // Exercise 3: Exponential Calculation
        // Write a program that calculates e^x (exponential of x)
        Scanner sc = new Scanner(System.in);
        double epsilon;
        System.out.println("Calculation of e^x = 1 + x/1! + x^2/2! + ... .");
        System.out.print("\tEnter the value of X:");
        double x = sc.nextDouble();
        System.out.print("\tEnter the value of epsilon:");
        epsilon = sc.nextDouble();
        double expox = 0;
        int i = 0;
        double termei = 1.0;
        while (Math.abs(termei) > epsilon) {
            expox += termei;
            i++;
            int j;
            long factoi = 1;
            for (j=1;j<=i;j++) {
                factoi *= j;
            }
            termei = Math.pow(x, i) / factoi;
        }
        System.out.println("e^"+x+" = " + expox+" neglecting the terms < "+epsilon);
        sc.close();
        // To avoid the costly calculation of factorial for each term i, you will notice that we can go from
        // term i to term i+1 by multiplication. Rewrite a new version of your previous program.
        Scanner sc = new Scanner(System.in);
        double epsilon;
        System.out.println("Optimized calculation of e^x = 1 + x/1! + x^2/2! + ... .");
        System.out.print("\tEnter the value of X:");
        double x = sc.nextDouble();
        System.out.print("\tEnter the value of epsilon:");
        epsilon = sc.nextDouble();
        double expox = 0;
        int i=0;
        double termei = 1.0;
        while (Math.abs(termei) > epsilon) {
            expox += termei; i++;
            termei *= (x / i);
        }
        System.out.println("e^" + x + " = " + expox+ " neglecting the terms < " + epsilon);
        sc.close();
    }
}