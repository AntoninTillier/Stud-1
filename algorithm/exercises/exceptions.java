// Exercise 1
// Write City Class with main() and functions: nbResidKnow, description, setResidents and handle exception 
public class City {
    String nameCity;
    int residentsCity;
    City() {
        nameCitye = "City unknown";
        residentsCity = 0;
        System.out.println("Builder Class City");
    }
    City(String name) {
        nameCitye = name;
        residentsCity = 0;
        System.out.println("Builder Class City with name");
    }
    City(String name, int number) {
        nameCitye = name;
        if (number > 0)
            residentsCity = number;
        else
            residentsCity = 0;
        System.out.println("Builder Class City with name and nb_residents");
    }
    public boolean nbResidKnow() {
        return residentsCity > 0;
    }
    public void description() {
        System.out.println("City name : \t" + nameCitye);
        if (nbResidKnow()) {
            System.out.println("\t\tNumber of residents" + residentsCity);
        }
        else {
            System.out.println("\t\tNumber of residents unknown");
        }
    }
    public String getName() {
        return nameCity
    }
    public int getResidents() {
        return residentsCity;
    }
    public void setName(String name) throws NameCityException {
        if (name.length() < 3) {
            throw new NameCityException();
        }  else {
            nameVille = name;
        }
    }
    public void setResidents(int number) throws residentsCityException {
        if (number >= 0) {
            residentsCity = number;
        } else {
            throw new residentsCityException();
        }
    }
}

public class NameCityException extends Exception {
    public void displayErreur() {
        System.out.println("Name City Incorrect!");
    }
}

public class ResidentsCityException extends Exception {
    public void displayErreur(){
        System.out.println("Number of residents in City Incorrect!");
    }
}

// Write City Class with main() and functions: nbResidKnow, description, setResidents and handle exception
public class Capitale extends City {
    Capitale() {
        System.out.println("Builder Classe Capitale");
    }
    Capitale(String name) {
        super(name);
        System.out.println("Builder Classe Capitale wiht name");
    }
    Capitale(String name, int number) {
        super(name, number);
        System.out.println("Builder Classe Capitale whith name and number");
    }
    Capitale(String name, int number, String country) {
        super(name, number);
        this.country = country; System.out.println("Builder Classe Capitale with name, number and country");
    }
    String country = null;
    public void description() {
        super.description();
        if (country != null) {
            System.out.println("\t\tCountry : " + country);
        }
        else {
            System.out.println("\t\tCountry unknow");
        }
    }
    public void setCountry(String country) {
        this.country = country;
    }
}

// Exercise 3
// Write the Point class that allows creating points with positive x and y coordinates.
public class Point {
    int x, y;
    Point(int n, int m) throws ErrConst {
        if ((n<0)||(m<0)) {
            throw new ErrConst("Constante négative !");
        }
        x = n;
        y = m;
    }
}

public class ErrConst extends Exception {
    public ErrConst(String s) {
        super(s);
        System.out.println("In the constructor body of ErrConst!");
    }
    public void display() {
        System.out.println("Error, negative constant!");
    }
}


public class exceptions {
    public static void main(String[] args) {
        // Exercise 1
        City v1 = new City();
        System.out.println("name City : " + v1.getName() + "; number habitans : " + v1.getResidents());
        v1.description();
        City v2 = new City("Blois");
        System.out.println("name City : " + v2.getName() + "; number habitans : " + v2.getResidents());
        v2.description();
        City v3 = new City("Tours", 30000);
        System.out.println("name City : " + v3.getName() + "; number habitans : " + v3.getResidents());
        v3.description();
        try {
            v3.setName("t");
        } catch (nameCityeException e1) {
            e1.displayErreur();
            try {
                v3.setName("City Inconnue");
            } catch (nameCityeException e2) {
            }
        }
        try {
            v3.setResidents(0);
        } catch (residentsCityException e3) {
            e3.displayErreur();
            try {
                v3.setResidents(0);
            } catch (residentsCityException e4) {
            }
        }
        v3.description();
        Capitale c1 = new Capitale("Paris", 500000); c1.description();
        c1.setcountry("France");
        c1.description();

        // Exercise 2
        // Manage the exception of the array loop
        int[] tab = new int[10];
        for (int i=0;i<=10;i++){
            try {
                System.out.println(tab[i]);
            } catch (ArrayIndexOutOfBoundsException e) { }
            System.out.println("Fin du programme !");
        }
        // Add to the previous code 'int x=1, y=0; System.out.println(x / y);' and provide all methods for handling the exception.
        int[] tab = new int[10];
        for (int i=0;i<=10;i++){
            try {
                System.out.println(tab[i]);
            } catch (ArrayIndexOutOfBoundsException e) { }
            int x=1,y=0;
            try {
                System.out.println(x / y);
            } catch (ArithmeticException e2) { }
            System.out.println("Fin du programme !");
        }
        // or
        int[] tab = new int[10];
        try {
            for (int i=0;i<=10;i++){
                System.out.println(tab[i]);
            }
            int x=1,y=0;
            System.out.println(x / y);
        }catch (ArrayIndexOutOfBoundsException e) { } catch (ArithmeticException e2) {}
        System.out.println("Fin du programme !");

        //Exercise 3
        System.out.println("Execution of the main method of Point!");
        Point a = null;
        try {
            a = new Point(3, -4);
        } catch (ErrConst e) { e.display();
            System.out.println("getMessage = " + e.getMessage());
        }
        finally {
            if (a != null)
                System.out.println("a.x = " + a.x + "; a.y = " + a.y + ";");
        }
    }
}
