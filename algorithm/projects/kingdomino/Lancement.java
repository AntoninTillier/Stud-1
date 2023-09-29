import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

@SuppressWarnings("serial")
public class Lancement extends JPanel {
    BufferedImage Lancement;

    public Lancement(BufferedImage image) {
        this.Lancement = image;
    }

    public void paintComponent(Graphics g) {
        int x = (getWidth() - Lancement.getWidth()) / 2;
        int y = (getHeight() - Lancement.getHeight()) / 2;
        g.drawImage(Lancement, x, y, this);
    }
}