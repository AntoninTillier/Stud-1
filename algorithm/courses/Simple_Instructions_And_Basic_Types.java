import java.util.Scanner;

public class Simple_Instructions_And_Basic_Types {
    public static void main(String[] args) {
        // Exercice 1 : Permutations
        // Is it possible to swap the values of two variables without using an intermediate variable?
        int x=3, y=5;
        System.out.println("x ="+x+" y = "+y);
        x=x+y;
        y=x-y;
        x=x-y;
        // Perform a circular permutation of the integer values of three variables X, Y, and Z:
        // X takes the value of Y, Y takes the value of Z, and Z takes the value of X.
        // The initial values of X, Y, and Z should be requested from the user, and the new values (after circular permutation) should be displayed.
        Scanner sc = new Scanner(System.in);
        int x, y, z, tmp;
        System.out.print("Enter the value of x : ");
        x = sc.nextInt();
        System.out.print("Enter the value of y : ");
        y = sc.nextInt();
        System.out.print("Enter the value of z : ");
        z = sc.nextInt();
        System.out.println("Init x = " + x + ", Init y = " + y+ " et Init z = " + z);
        System.out.println("Circular permutation of x, y, and z");
        tmp = x;
        x = y;
        y = z;
        z = tmp;
        System.out.println("New x = " + x + ", New y = " + y + " et New z = "+ z);
        sc.close();
        // Modify the previous program so that for each variable, both the initial value and the new value are displayed.
        // Can we avoid duplicating the variables? If yes, modify the program accordingly.
        Scanner sc = new Scanner(System.in); int x, y, z, tmp;
        System.out.print("Enter the value of x : "); x = sc.nextInt();
        System.out.print("Enter the value of y : "); y = sc.nextInt();
        System.out.print("Enter the value of z : ");
        z = sc.nextInt();
        System.out.println("Circular permutation of x, y, and z"); tmp = x;
        System.out.print("Init x = " + x);
        x = y;
        System.out.println(" and New x = " + x);
        System.out.print("Init y = " + y);
        y = z;
        System.out.println(" and New y = " + y);
        System.out.print("Init z = " + z);
        z = tmp;
        System.out.println(" and New z = " + z);
        sc.close();

        // Exercice 2 : The sum of the digits of an integer
        // Using the / and % instructions, write a program that asks the user to enter a 4-digit integer and displays the sum of its digits.
        // If the entered number is 1234, the program should display a sum equal to 10 (1+2+3+4=10).
        Scanner sc = new Scanner(System.in);
        int n, somme;
        System.out.print("Enter a four-digit integer: ");
        n = sc.nextInt();
        somme = n % 10 + (n / 10) % 10 + (n / 100) % 10 + (n / 1000) % 10;
        System.out.println("Sum of the digits of \"" + n + "\"= " + somme);
        sc.close();

        // Exercice 3 : The quantity of bills representing an amount of money
        // Write a program that asks the user to enter a value (multiple of 5) representing an amount of money,
        // and calculates and displays the number of 100, 50, 20, 10, and 5 euro bills it represents
        // (minimizing the number of bills).
        Scanner sc = new Scanner(System.in);
        int somme, reste, b100, b50, b20, b10, b5;
        System.out.print("Enter a sum that is a multiple of 5: "); somme = sc.nextInt();
        b100 = somme / 100;
        reste = somme % 100;
        b50 = reste / 50;
        reste %= 50;
        b20 = reste / 20;
        reste %= 20;
        b10 = reste / 10;
        reste %= 10;
        b5 = reste / 5;
        reste %= 5;
        System.out.println("The money "+ somme +" represents:");
        System.out.println(b100 + " bill(s) of 100 €");
        System.out.println(b50 + " bill(s) of 50 €");
        System.out.println(b20 + " bill(s) of 20 €");
        System.out.println(b10 + " bill(s) of 10 €");
        System.out.println(b5 + " bill(s) of 5 €");
        sc.close();

        // Exercice 4 : Random generation of a number
        // Using the random() method of the Math class, generate the following numbers:
        // a real number within the interval [min, max[, where min and max are real numbers entered by the user.
        Scanner sc = new Scanner(System.in); double n, min, max;
        System.out.println("Generating a real number.");
        System.out.print("Enter the minimum bound (inclusive): "); min = sc.nextDouble();
        System.out.print("Enter the maximum bound (exclusive): "); max = sc.nextDouble();
        n = Math.random();
        System.out.print("The real number generated within the interval [ "+min+" , "+max+" [ is : "+ (n*(max-min)+min));
        sc.close();
        // Using the random() method of the Math class, generate the following numbers: an integer within the interval [10, 15].
        double n;
        int min, max;
        min = 10;
        max = 15;
        n = Math.random();
        System.out.print("Generated value: "+ (int)(n*(max-min+1)+min));
        // Using the random() method of the Math class, generate the following numbers: an integer within the interval [-10, 15].
        double n;
        int min, max; min = -10;
        max = 15;
        n = Math.random();
        System.out.print("Generated value: "+ (int)(n*(max-min+1)+min));
        // Using the random() method of the Math class, generate the following numbers: an integer within the interval [min, max],
        // where min and max are integers entered by the user.
        Scanner sc = new Scanner(System.in);
        double n;
        int min, max;
        System.out.println("Generating an integer number.");
        System.out.print("Enter the minimum bound:");
        min = sc.nextInt();
        System.out.print("Enter the maximum bound:");
        max = sc.nextInt(); n = Math.random();
        System.out.print("The integer number generated within the interval [ "+min+" , "+max+" ] is : "+ (int)(n*(max-min+1)+min));
        sc.close();

        // Exercice 5 : Declarations, types, and expressions
        // Among the following variable declarations, indicate which ones are valid:
        int i = 0; // Valid
        short j; // Valid
        long l1, l2 = 0, l3; // Valid
        //short j = 60000; Overflow error
        int i = 0x10; // Valid
        char c = 'a'; // Valid
        //char c = a; Valid if 'a' is of type char
        char c = 0x41; // Valid (character 'A')
        char c = '\u20AC'; // Valid (character '€')
        boolean b = true; // Valid
        //boolean b = 0; Incompatible types error
        //real r = 0.1; No real type error
        //float f = 0.1;"Incompatible types error (0.1 is of type double)
        double d = 0.1; // Valid
        double d = 0; // Valid
        float f = 0x10; // Valid
        double d = .1; // Valid
        int i = 'a'; // Valid
        // Based on the declarations below:
        int i=1, j=2, k=3;
        double x=0.1, y=0.2, z=0.3;
        char c = 'a';
        boolean b=true;
        // Specify the type of each of the following expressions (you can test them in a Java program,
        // using the declarations above and assigning an initial value to each of the variables):
        // x; double
        // 2; int
        // i=j; int
        // i==j; boolean
        // x+2.0; double
        // x+2; double
        // i+2; int
        // x+i; double
        // x/2; double
        // x/2.0; double
        // i/2; int
        // i/2.0; double
        // x<y; boolean
        // i%j+y; double
        // i/j+y; double
        // i>j>k; Syntax error
        // i&&b; Incompatible type error
        // i==j&&b; boolean
        // i>j&&k>j; boolean
        // x+y*i; double
        // i=c; int
        // x=(int)y; double
        // c=(char)((int) c + 1); char
        // i++; int
    }
}