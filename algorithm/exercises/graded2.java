public class graded2 {
    public static int[] generateRandomArray(int size, int min, int max) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt((max - min) + 1) + min;
        }
        return array;
    }

    public static void displayArray(int[] array) {
        System.out.print("Generated Array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static int base10(int[] byteArr) {
        int decimalValue = 0;
        for (int i = 0; i < byteArr.length; i++) {
            int power = 7 - i;
            int bitValue = byteArr[i];
            decimalValue += bitValue * Math.pow(2, power);
        }
        return decimalValue;
    }

    public static int[] salesBySalesperson(int[][] sales) {
        int numSalespeople = sales.length;
        int numItems = sales[0].length;
        int[] salesBySalesperson = new int[numSalespeople];
        for (int i = 0; i < numSalespeople; i++) {
            int totalSales = 0;
            for (int j = 0; j < numItems; j++) {
                totalSales += sales[i][j];
            }
            salesBySalesperson[i] = totalSales;
        }
        return salesBySalesperson;
    }

    public static int[] totalItems(int[][] sales) {
        int numItems = sales[0].length;
        int[] totalItemsSold = new int[numItems];
        for (int i = 0; i < numItems; i++) {
            int totalSoldForItem = 0;
            for (int j = 0; j < sales.length; j++) {
                totalSoldForItem += sales[j][i];
            }
            totalItemsSold[i] = totalSoldForItem;
        }
        return totalItemsSold;
    }

    public static double[] totalRevenue(int[] totalItemsSold, double[] unitPrices) {
        int numItems = totalItemsSold.length;
        double[] totalRevenuePerItem = new double[numItems];
        for (int i = 0; i < numItems; i++) {
            totalRevenuePerItem[i] = totalItemsSold[i] * unitPrices[i];
        }
        return totalRevenuePerItem;
    }

    public static int minRevenueIndex(double[] totalRevenuePerItem) {
        if (totalRevenuePerItem.length == 0) {
            return -1;
        }
        double minRevenue = totalRevenuePerItem[0];
        int minIndex = 0;
        for (int i = 1; i < totalRevenuePerItem.length; i++) {
            if (totalRevenuePerItem[i] < minRevenue) {
                minRevenue = totalRevenuePerItem[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static int minSalesIndex(int[][] sales, double[] unitPrices) {
        if (sales.length == 0 || unitPrices.length == 0) {
            return -1;
        }
        double minRevenuePerItem = Double.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < sales[0].length; i++) {
            double revenuePerItem = 0.0;
            for (int j = 0; j < sales.length; j++) {
                revenuePerItem += sales[j][i] * unitPrices[i];
            }
            if (revenuePerItem < minRevenuePerItem) {
                minRevenuePerItem = revenuePerItem;
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        // Exercise 1
        // Write a main program that constructs and displays an array containing 50 random integers generated
        // between 0 and 100 (inclusive). The display and creation of the array can be done in external methods.
        int[] randomArray = generateRandomArray(50, 0, 100);
        displayArray(randomArray);

        // Exercise 2
        // We want to represent a byte (consisting of 8 bits) in Java using an array of 8 integers containing only 0s and 1s.
        // The goal of the exercise is to write a Java method that takes as input an array representing a byte and returns the corresponding positive decimal integer value as output.
        // The order of the indices in the array representing the byte and the power of 2 to be used in the calculation are reversed:
        //• Index 0 of the array corresponds to power 7: called the most significant bit, i.e., 2^7.
        //• Index 7 of the array corresponds to power 0: called the least significant bit, i.e., 2^0.
        // Write a method base10 that takes as input an array of integers representing a byte and returns its decimal value.
        // Create a main program that calculates the value corresponding to the following byte: int[] byte = {0,0,0,1,0,0,1,1};
        int[] byteArr = {0, 0, 0, 1, 0, 0, 1, 1};
        int decimalValue = base10(byteArr);
        System.out.println("Decimal value: " + decimalValue);

        // Exercise 3
        // In this last exercise, we are interested in a simplified management of a store's sales.
        // For this purpose, we want to set up a 2-dimensional array that indicates,
        // for each salesperson and for each item, the number (positive integer) that has been sold.
        // The Java representation is:
        int[][] sales = {{0, 3, 2, 0}, {2, 3, 0, 1}, {1, 1, 1, 1}, {5, 1, 0, 0}, {1, 1, 2, 0}};`
        // Write a method salesBySalesperson that takes the sales array as input and returns a 1-dimensional array indicating,
        // for each salesperson, the number of sales they have made across all items.
        // For the given array, your method should return the array [5, 6, 4, 6, 4].
        int[] salesBySalesperson = salesBySalesperson(sales);
        for (int i = 0; i < salesBySalesperson.length; i++) {
            System.out.println("Salesperson " + (i + 1) + ": " + salesBySalesperson[i]);
        }
        // Write a method totalItems that takes the sales array as input and returns a 1-dimensional array indicating,
        // for each item, the number of times it has been sold across all salespeople.
        // For the given array, your method should return the array [9, 9, 5, 2].
        int[] totalItemsSold = totalItems(sales);
        for (int i = 0; i < totalItemsSold.length; i++) {
            System.out.println("Item " + (i + 1) + ": " + totalItemsSold[i]);
        }
        // Write a method totalRevenue that takes as input the array of the total number of sales per item (obtained in the previous question)
        // and an array indicating, for each item, its unit price, and returns an array indicating,
        // for each item, its revenue, which is the product of the total quantity sold and its unit price.
        // If you use the following array of unit selling prices:
        double[] unit_prices = {21.5, 25, 12.7, 33.3};
        //You should obtain the following result with your method: [193.5, 225.0, 63.5, 66.6].
        double[] totalRevenuePerItem = totalRevenue(totalItemsSold, unitPrices);
        for (int i = 0; i < totalRevenuePerItem.length; i++) {
            System.out.println("Item " + (i + 1) + " Revenue: " + totalRevenuePerItem[i]);
        }
        // Write a method minRevenueIndex that takes as input the array containing the revenue per item (obtained in question 3)
        // and returns the index (not the amount) of the item that has sold the least well (in terms of revenue, not quantity).
        // In case of a tie between multiple items, your method should return one of the correct indices without any further constraints.
        // For example, your function should return 2 for the revenue array [193.5, 225.0, 63.5, 66.6] because 63.5 is the smallest value and it has index 2.
        int minRevenueIndex = minRevenueIndex(totalRevenuePerItem);
        System.out.println("Index of the item with the least revenue: " + minRevenueIndex);
        // Produce a program that, given the sales array and the unit prices per item, displays the index of the item that has sold the least.
        int minSalesIndex = minSalesIndex(sales, unitPrices);
        System.out.println("Index of the item with the least sales quantity: " + minSalesIndex);
    }
}