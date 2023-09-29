public class graded1 {
    public static void main(String[] args) {
        // Write a program that asks the user for their name and the weight of their vehicle,
        // then displays the sentence 'Bonjour [name], votre véhicule pèse environ [weight] kilogramme(s)'
        // where [name] and [weight] should be replaced by the entered values.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Please enter the weight of your vehicle in kilograms: ");
        double weight = scanner.nextDouble();
        System.out.println("Hello " + name + ", your vehicle weighs approximately " + weight + " kilogram(s).");
        scanner.close();
        // Take the code written for Question 1 into a new class and modify this code to ensure that the entered
        // weight is realistic, i.e., at least 1 and less than 38000 kg.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        double weight;
        while (true) {
            System.out.print("Please enter the weight of your vehicle in kilograms (between 1 and 38000): ");
            weight = scanner.nextDouble();
            if (weight >= 1 && weight <= 38000) {
                break; // Exit the loop if the weight is valid
            } else {
                System.out.println("Invalid weight. Please enter a weight between 1 and 38000 kg.");
            }
        }
        System.out.println("Hello " + name + ", your vehicle weighs approximately " + weight + " kilogram(s).");
        scanner.close();
        // Take the code written for Question 2 into a new class and modify this code to ensure that
        // it's not possible to attempt more than 5 times for input. Beyond this number of attempts,
        // the program stops with an error message.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        double weight;
        int attempts = 0;
        while (attempts < 5) { // Limit to 5 attempts
            System.out.print("Please enter the weight of your vehicle in kilograms (between 1 and 38000): ");
            weight = scanner.nextDouble();
            if (weight >= 1 && weight <= 38000) {
                break;
            } else {
                System.out.println("Invalid weight. Please enter a weight between 1 and 38000 kg.");
                attempts++;
            }
        }
        if (attempts == 5) {
            System.out.println("You've exceeded the maximum number of attempts. Exiting...");
        } else {
            System.out.println("Hello " + name + ", your vehicle weighs approximately " + weight + " kilogram(s).");
        }
        scanner.close();
        // Take the code written for Question 3 into a new class and add instructions at the end of your program
        // to categorize the user's vehicle based on its weight:
        //• If it's less than 30 kg (exclusive), your program should display 'Bicycle'.
        //• If it's between 30 kg (inclusive) and 400 kg (exclusive), your program should display 'Motorcycle'.
        //• If the weight is between 400 kg (inclusive) and 1800 kg (exclusive), your program should display 'Car'.
        //• Finally, if the weight is greater than 1800 kg (inclusive), your program should display 'Truck'.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        double weight;
        int attempts = 0;
        while (attempts < 5) {
            System.out.print("Please enter the weight of your vehicle in kilograms (between 1 and 38000): ");
            weight = scanner.nextDouble();
            if (weight >= 1 && weight <= 38000) {
                break;
            } else {
                System.out.println("Invalid weight. Please enter a weight between 1 and 38000 kg.");
                attempts++;
            }
        }
        if (attempts == 5) {
            System.out.println("You've exceeded the maximum number of attempts. Exiting...");
        } else {
            System.out.println("Hello " + name + ", your vehicle weighs approximately " + weight + " kilogram(s).");
            if (weight < 30) {
                System.out.println("Your vehicle is a Bicycle.");
            } else if (weight >= 30 && weight < 400) {
                System.out.println("Your vehicle is a Motorcycle.");
            } else if (weight >= 400 && weight < 1800) {
                System.out.println("Your vehicle is a Car.");
            } else {
                System.out.println("Your vehicle is a Truck.");
            }
        }
        scanner.close();
        // Consider the following game of chance, which involves rolling a six-sided die multiple times and considering the last 2 rolls each time:
        //• If both rolls are either even or odd, the player wins a number of points equal to the sum of the two dice.
        //• If one is even and the other is odd, the player loses a number of points equal to the sum of the two dice.
        //Initially, the program asks the player how many dice rolls they want to make, knowing that this number must be at least two. The initial score is set to 0.
        //Then, starting from the second die roll, it's possible to update the score based on the last two dice rolls and following the rules mentioned earlier.
        //The final score is the one obtained by considering the sum of all scores on consecutive pairs of dice rolls.
        //Write a Java program that allows playing this game according to the rules outlined.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of dice rolls (must be at least 2): ");
        int numberOfRolls = scanner.nextInt();
        if (numberOfRolls < 2) {
            System.out.println("The number of rolls must be at least 2. Exiting...");
            scanner.close();
            return;
        }
        int[] rolls = new int[numberOfRolls];
        int score = 0;
        for (int i = 0; i < numberOfRolls; i++) {
            rolls[i] = (int) (Math.random() * 6) + 1;
            if (i >= 2) {
                int lastRoll1 = rolls[i - 1];
                int lastRoll2 = rolls[i - 2];
                if ((lastRoll1 % 2 == 0 && lastRoll2 % 2 == 0) || (lastRoll1 % 2 != 0 && lastRoll2 % 2 != 0)) {
                    score += lastRoll1 + lastRoll2;
                } else {
                    score -= lastRoll1 + lastRoll2;
                }
            }
        }
        System.out.println("Dice rolls:");
        for (int i = 0; i < numberOfRolls; i++) {
            System.out.println("Roll " + (i + 1) + ": " + rolls[i]);
        }
        System.out.println("Final Score: " + score);
        scanner.close();
        // The factorial of an integer number k is denoted as k! and is equal to k! = 1 * 2 * 3 * ... * k.
        // Write a main program that prompts the user to enter a positive integer k and displays the value of k!.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer k: ");
        int k = scanner.nextInt();
        if (k < 0) {
            System.out.println("Please enter a positive integer.");
        } else {
            long factorial = 1;
            for (int i = 2; i <= k; i++) {
                factorial *= i;
            }
            System.out.println(k + "! = " + factorial);
        }
        scanner.close();
        // The number 'e' was defined by the mathematician Euler as the limit of the following sum as k becomes very large:
        // e = 1 + 1/1 + 1/(12) + 1/(12*3) + ... + 1 / k!
        // Write a program that allows entering an integer value of k greater than 1 and displays the estimated value of the number 'e' using the formula mentioned.
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter an integer value of k greater than 1
        System.out.print("Enter an integer value of k (greater than 1): ");
        int k = scanner.nextInt();
        if (k <= 1) {
            System.out.println("Please enter a value of k greater than 1.");
        } else {
            double eEstimation = 1.0;
            double factorial = 1.0;
            for (int i = 1; i <= k; i++) {
                factorial *= i;
                eEstimation += 1.0 / factorial;
            }
            System.out.println("Estimated value of 'e' for k = " + k + ": " + eEstimation);
        }
        scanner.close();
    }
}