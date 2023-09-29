import java.util.*;
import java.io.*;
public class sequential_Access_Binary_Files {

    public static void writeTemp1(String nameFile) {
        DataOutputStream output = null;
        try {
            FileOutputStream fd = new FileOutputStream(nameFile);
            output = new DataOutputStream(fd);
            Random r = new Random();
            for (int i = 0; i < 5; i++) {
                output.writeInt(r.nextInt(71) - 20);
            }
        } catch (IOException e) {
            System.out.println("Write Error!");
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("File Closure Error!");
                }
            }
        }
    }

    public static void read(String nameFile) {
        System.out.println("File Contents: "+nameFile);
        DataInputStream input = null;
        try {
            FileInputStream fd = new FileInputStream(nameFile);
            input = new DataInputStream(fd);
            boolean eof = false;
            int n = 0;
            while (!eof) {
                try {
                    n = input.readInt();
                } catch (EOFException e) {
                    eof = true;
                }
                if (!eof)
                    System.out.println(n);
            }
        } catch (IOException e) {
            System.out.println("File Reading Error!");
        }
    }

    public static void minMaxMean(String nameFile) {
        DataInputStream input = null;
        boolean eof = false;
        int n = 0;
        int nb = 0;
        int min = 0, max = 0, mean = 0;
        try {
            FileInputStream fd = new FileInputStream(nameFile);
            input = new DataInputStream(fd);
        } catch (IOException e) {
            System.out.println("Error File!");
        }
        try {
            n = input.readInt();
            min = max = mean = n;
            nb++;
            while (!eof) {
                n = input.readInt();
                if (n < min) min = n;
                if (n > max) max = n;
                mean += n;
                nb++; }
        } catch (EOFException e) {
            eof = true;
        } catch (IOException e) {
            System.out.println("Empty File!");
        }
        if (eof) {
            System.out.println("Min = " + min);
            System.out.println("Max = " + max);
            System.out.println("mean = " + mean / nb);}
    }

    public static void NegativeValueCounter(String nameFile) {
        DataInputStream input = null;
        try {
            FileInputStream fd = new FileInputStream(nameFile);
            input = new DataInputStream(fd);
            boolean eof = false;
            int n = 0;
            int nbNeg = 0;
            while (!eof) {
                try {
                    n = input.readInt();
                    if (n < 0)
                        nbNeg++;
                } catch (EOFException e) {
                    eof = true;
                }
            }
            if (nbNeg != 0) {
                System.out.println("The file " + nameFile + " contains "+ nbNeg + " negative number(s)!");
            } else {
                System.out.println("The file " + nameFile + " does not contain any negative numbers!");
            }
        } catch (IOException e) {
            System.out.println("Error File !");
        }
    }

    public static void findNumber(String nameFile) {
        DataInputStream input = null;
        try {
            FileInputStream fd = new FileInputStream(nameFile);
            input = new DataInputStream(fd);
            boolean eof = false;
            boolean find = false;
            int n, v;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the search value:");
            v = sc.nextInt();
            while (!eof) {
                try {
                    n = input.readInt();
                    if (n == v) {
                        find = true;
                        break;
                    }
                } catch (EOFException e) {
                    eof = true;
                }
            }
            if (find) {
                System.out.println("The value " + v + " is present in the file " + nameFile + "!");
            } else {
                System.out.println("The value " + v + " is not present in the file " + nameFile + "!");
            }
        } catch (IOException e) {
            System.out.println("Error File!");
        }
    }

    public static void writeTemp2(String nameFile) {
        DataOutputStream output = null;
        try {
            FileOutputStream fd = new FileOutputStream(nameFile);
            output = new DataOutputStream(fd);
            Random r = new Random();
            for (int i = 0; i < 5; i++) {
                output.writeInt(r.nextInt(71) - 10);
            }
        } catch (IOException e) {
            System.out.println("Write Error!");
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("File Closure Error!");
                }
            }
        }
    }

    public static void displayFile() {
        String nomFichier;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the file to display:");
        nomFichier = sc.nextLine();
        DataInputStream entree = null;
        try {
            FileInputStream fd = new FileInputStream(nomFichier);
            entree = new DataInputStream(fd);
            boolean eof = false;
            int n = 0;
            while (!eof) {
                try {
                    n = entree.readInt();
                } catch (EOFException e) {
                    eof = true;
                }
                if (!eof)
                    System.out.println(n);
            }
        } catch (IOException e) {
            System.out.println("Error File!");
        }
    }

    public static void main(String[] args) {
        // Write a program that creates a file, which you will name "temp1" on the disk, containing 20 random integers between -20 and 50.
        writeTemp1("temp1");
        // Write a program that reads all the values contained in the "temp1" file and displays them on the screen.
        read("temp1");
        // Write a program that displays the maximum value, minimum value, and average value of the values contained in the "temp1" file.
        minMaxMean("temp1");
        // Write a program that indicates whether there are negative values in the "temp1" file and counts them if they exist.
        NegativeValueCounter("temp1");
        // Write a program that indicates whether the file "temp1" contains a value proposed by the user.
        findNumber("temp1");
        // Create a second file, which you will name "temp2," containing 30 random integers with values ranging from -10 to 60.
        writeTemp2("temp2");
        // Write a program that asks for the name of the file to read ("temp1", "temp2", etc.) and then displays the values from the requested file.
        displayFile();
    }
}