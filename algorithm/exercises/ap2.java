public class ap2 {

    public static boolean isSet(int[][] set) {
        int count = 0; // counter
        for (int i = 1; i < set.length; i++) {
            if (set[i].length == set[i - 1].length) {
                count++;
            }
        }
        return set.length == (count + 1);
    }

    public static boolean countPositive(int[][] ensemble) {
        int[] f = new int[ensemble.length];
        for (int j = 0; j < ensemble.length; j++) {
            int c1 = 0; // counter
            for (int i = 0; i < ensemble[j].length; i++) {
                if (ensemble[j][i] > 0) {
                    c1++;
                }
            }
            for (int i = 0; i < ensemble[j].length; i++) {
                f[j] = c1; // fill array f by counting positive numbers
            }
        }
        int d = 0; // counter
        int k = 1; // counter for comparing data in array f
        while (d < ensemble.length && f[k] == f[k - 1])
            d++;
        k++;
        boolean test = true; // result flag
        if (d == ensemble.length) {
            test = true;
            System.out.println("The sets are equivalent in positive numbers.");
        } else {
            test = false;
            System.out.println("The sets are not equivalent in positive numbers.");
        }
        return test;
    }


    public static void main(String[] args) {
        int e1[][] = {{1, 2, 3}, {7, -2, 3}, {7, 5, 45}};
        int e2[][] = {{1, 2, 3}, {7, -2}, {7, 5, 45}};
        boolean test = isSet(e1); // calls the method and tests it with e1
        if (test == true) { // checks if isSet is true or false and prints the result
            System.out.println("The sets are equivalent."); // display
        } else {
            System.out.println("The sets are not equivalent.");
        }
        System.out.println("");
        System.out.println("{1, 2, 3}, {7, -2}, {7, 5, 45}");
        test = isSet(e2); // calls the method and tests it with e2
        if (test == true) {
            System.out.println("The sets are equivalent.");
        } else {
            System.out.println("The sets are not equivalent.");
        }
        //
        countPositive(e1);
        countPositive(e2);
    }
}