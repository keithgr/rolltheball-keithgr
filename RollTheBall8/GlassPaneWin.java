import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Write a description of class RollTheBallPanel here.
 * 
 * @author Keith Grable, Ethan De Bernardo, Martin McBride, Julia Dunbar, Tim Cronin
 * @version 2017-04-01
 */
public class GlassPaneWin extends JComponent implements MouseListener{
    protected BufferedImage image = null;
    protected menu menu1;
    public GlassPaneWin(menu menu1) {
        addMouseListener(this);
        setVisible(true);
        this.menu1 = menu1;
        repaint();
    }   

    protected void paintComponent(Graphics g) {
        int alpha = 190; // 50% transparent
        Color myColor = new Color(0, 0, 0, alpha);
        g.setColor(myColor);
        g.fillRect(0,0, 800, 800);
        if (image == null) {
            try {
                image = ImageIO.read(getClass().getResource("/img/youwin.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        g.drawImage(image, 120, 200, null);
        g.setColor(Color.white);
        Font myFont = new Font ("Courier New", 1, 17);
        g.setFont(myFont);
        g.drawString("Your Score: " + menu1.score, 240,400);
        g.drawString("Moves Taken This Round: " + menu1.rtbPane.moves, 200,415);
    }

    public void mouseClicked(MouseEvent e) {
        setVisible(false);
        menu1.createNewLevel();
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}