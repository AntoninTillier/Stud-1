import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Backboard extends JPanel {
    public void paintComponent(Graphics g) {
        int numBeads = 20; // initial number of beads
        int i = 0; // bead counter
        int j; // counter for beads of the same color
        int maxCount = 3; // number of beads of the same color

        while (i < numBeads) {
            j = 0;
            while ((j < maxCount) && (i < numBeads)) {
                i++;

                if (j % 2 == 0) {
                    g.setColor(Color.yellow);
                    g.fillOval((i * 57) - 28, (int) (75 + (300 * Math.sin(Math.toRadians(((180 / 18.5) * i) - (180 / 18.5))))), 50, 50); // yellow circle because it's an even number
                } else {
                    g.setColor(Color.yellow);
                    g.fillRect((i * 57) - 28, (int) (75 + (300 * Math.sin(Math.toRadians(((180 / 18.5) * i) - (180 / 18.5))))), 50, 50); // yellow square because it's an odd number
                }
                j++;
            }
            j = 0;
            while ((j < maxCount) && (i < numBeads)) {
                i++;
                j++;
                if (j % 2 == 0) {
                    g.setColor(Color.blue);
                    g.fillOval((i * 57) - 28, (int) (75 + (300 * Math.sin(Math.toRadians(((180 / 18.5) * i) - (180 / 18.5))))), 50, 50); // blue circle because it's an even number
                } else {
                    g.setColor(Color.blue);
                    g.fillRect((i * 57) - 28, (int) (75 + (300 * Math.sin(Math.toRadians(((180 / 18.5) * i) - (180 / 18.5))))), 50, 50); // blue square because it's an odd number
                }
            }
        }
    }
}

public class Window extends JFrame {
    public Window() {
        this.setTitle("Beadwork");
        this.setSize(1200, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Backboard());
        this.setVisible(true);
    }
}

public class ap1 {
    public static void makeColorShapeNecklace(String shape1, String shape2, String color1, String color2, int numBeads) {
        int i = 0; // bead counter
        int j; // counter for beads of the same color
        int x = 3; // number of beads of the same color
        while (i < numBeads) {
            j = 0;
            while ((j < x) && (i < numBeads)) {
                i++;
                j++;
                if (i == 1) {
                    if (j % 2 == 0) {
                        System.out.print("o" + shape2 + color1 + "--");//o(square~yellow)-- because it's an even number
                    } else {
                        System.out.print("o" + shape1 + color1 + "--");//o(round~yellow)-- because it's an odd number
                    }
                } else {
                    if (i == 20) {
                        if (j % 2 == 0) {
                            System.out.print(shape2 + color1 + "↺");//(square~yellow)↺ because it's an even number
                        } else {
                            System.out.print(shape1 + color1 + "↺");//(round~yellow)↺ because it's an odd number
                        }
                    } else {
                        if (j % 2 == 0) {
                            System.out.print(shape2 + color1 + "--");//(square~yellow)-- because it's an even number
                        } else {
                            System.out.print(shape1 + color1 + "--");//(round~yellow)-- because it's an odd number
                        }
                    }
                }
            }
            j = 0;
            while ((j < x) && (i < numBeads)) {
                i++;
                j++;
                if (j % 2 == 0) {
                    System.out.print(shape1 + color2 + "--");//(round~blue)-- because it's an even number
                } else {
                    System.out.print(shape2 + color2 + "--");//(square~blue)-- because it's an odd number
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        makeColorShapeNecklace("(round~", "(square~", "yellow)", "blue)", 20);// this creates the necklace
        Window window = new Window();
    }
}