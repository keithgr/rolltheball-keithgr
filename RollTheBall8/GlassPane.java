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
public class GlassPane extends JComponent implements MouseListener{
    protected BufferedImage image = null;
    public GlassPane() {
        addMouseListener(this);
        setVisible(true);
        repaint();
    }   
    
    protected void paintComponent(Graphics g) {
        int alpha = 190; // 50% transparent
        Color myColor = new Color(0, 0, 0, alpha);
        g.setColor(myColor);
        g.fillRect(0,0, 550, 550);
        if (image == null) {
            try {
                image = ImageIO.read(getClass().getResource("/img/instructions.jpg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        g.drawImage(image, 75, 75, null);
    }

    public void mouseClicked(MouseEvent e) {
        setVisible(false);
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}