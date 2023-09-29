import java.util.Scanner;

public class Conditional_Statements_And_Loops_3 {
    public static void main(String[] args) {
        // All the programs requested in exercises 1 and 2 must be implemented using two for() loops.
        // Exercise 1: Displaying Squares and Rectangles
        // Write a program that displays a matrix (square or rectangle) where each element
        // (intersection of a row and a column) is represented by the character 'X'.
        // The number of rows and the number of columns are entered by the user.
        Scanner sc = new Scanner(System.in);
        int l, c;
        System.out.println("Display squares and rectangles.");
        System.out.print("\tEnter the number of rows:");
        l = sc.nextInt();
        System.out.print("\tEnter the number of columns:");
        c = sc.nextInt();
        for (int i = l; i > 0; i--) {
            for (int j = c; j > 0; j--){
                System.out.print("X ");
            }
            System.out.println();
        }
        sc.close();

        // Exercise 2: Displaying Triangles
        // Write a program that displays a triangle represented by lines consisting of the character 'X' as shown right angle down.
        // The size of the triangle is entered by the user.
        Scanner sc = new Scanner(System.in);
        int l;
        System.out.println("Display triangles: right angle down.");
        System.out.print("\tEnter the size of the triangle (number of lines):");
        l = sc.nextInt();
        for (int i = 1; i <= l; i++) {
            for (int j = 1; j <= i; j++){
                System.out.print("X ");
            }
            System.out.println();
        }
        sc.close();
        // Write a program that displays a triangle represented by lines consisting of the character 'X' as shown right angle to the right.
        // The size of the triangle is entered by the user.
        Scanner sc = new Scanner(System.in);
        int l;
        System.out.println("Display triangles: right angle to the right.");
        System.out.print("\tEnter the size of the triangle (number of lines):"); l = sc.nextInt();
        for (int i=-l;i<=l;i++){
            for (int j = l - Math.abs(i); j > 0; j--) {
                System.out.print("X ");
            }
            System.out.println();
        }
        sc.close();

        // Exercise 3: Finding a Card
        // Write a program that, by asking the minimum number of questions to the user, finds the card the user has chosen from a deck of 32 cards.
        //Allowed questions concern: the color (red, black), the suit (hearts, diamonds, spades, clubs),
        // the rank (totally ordered set: ace, king, queen, jack: in descending order),
        // and the value (10, 9, 8, 7).
        // The answers must be yes or no.
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Card Guessing Game!");
        System.out.println("Think of a card from a deck of 32 cards, and I will guess it.");
        String[] colors = {"red", "black"};
        String[] suits = {"hearts", "diamonds", "spades", "clubs"};
        String[] ranks = {"ace", "king", "queen", "jack"};
        int[] values = {10, 9, 8, 7};
        String colorResponse, suitResponse, rankResponse, valueResponse;
        int minRankIndex = 0;
        int maxRankIndex = ranks.length - 1;
        int minValueIndex = 0;
        int maxValueIndex = values.length - 1;
        while (true) {
            System.out.println("Is the card red? (yes/no)");
            colorResponse = sc.nextLine().toLowerCase();
            System.out.println("Is the card from hearts or diamonds suit? (yes/no)");
            suitResponse = sc.nextLine().toLowerCase();
            System.out.println("Is the card an ace or a king? (yes/no)");
            rankResponse = sc.nextLine().toLowerCase();
            System.out.println("Is the card a 10 or a 9? (yes/no)");
            valueResponse = sc.nextLine().toLowerCase();
            if (colorResponse.equals("yes")) {
                // Keep only cards of the chosen color
                minRankIndex = 0;
                maxRankIndex = ranks.length - 1;
                minValueIndex = 0;
                maxValueIndex = values.length - 1;
            } else {
                // Exclude cards of the chosen color
                minRankIndex = ranks.length;
                maxRankIndex = ranks.length;
                minValueIndex = values.length;
                maxValueIndex = values.length;
            }

            if (suitResponse.equals("yes")) {
                // Keep only cards from hearts or diamonds suit
                minRankIndex = 0;
                maxRankIndex = ranks.length - 1;
                minValueIndex = 0;
                maxValueIndex = values.length - 1;
            } else {
                // Exclude cards from hearts or diamonds suit
                minRankIndex = ranks.length;
                maxRankIndex = ranks.length;
                minValueIndex = values.length;
                maxValueIndex = values.length;
            }
            if (rankResponse.equals("yes")) {
                // Keep only cards that are ace or king
                maxRankIndex = 1;
            } else {
                // Exclude cards that are ace or king
                minRankIndex = 2;
            }
            if (valueResponse.equals("yes")) {
                // Keep only cards that are 10 or 9
                maxValueIndex = 1;
            } else {
                // Exclude cards that are 10 or 9
                minValueIndex = 2;
            }

            // Check if there's only one possible card left
            if (minRankIndex == maxRankIndex && minValueIndex == maxValueIndex) {
                String guessedCard = colors[0] + " " + suits[0] + " " + ranks[minRankIndex] + " " + values[minValueIndex];
                System.out.println("Is your card " + guessedCard + "? (yes/no)");
                String finalResponse = scanner.nextLine().toLowerCase();
                if (finalResponse.equals("yes")) {
                    System.out.println("I guessed your card! Thanks for playing.");
                } else {
                    System.out.println("I couldn't guess your card. Let's try again.");
                }
                break;
            }
        }
        sc.close();
    }
}