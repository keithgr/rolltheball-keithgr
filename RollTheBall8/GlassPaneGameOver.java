import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * This Panel displays the Game Over image after the last level is complete. It also displays the
 * current score and the number of moves taken to complete the previous level.
 * 
 * @author Keith Grable, Ethan De Bernardo, Martin McBride, Julia Dunbar, Tim Cronin
 * @version 2017-04-01
 */
public class GlassPaneGameOver extends JComponent implements MouseListener{
    protected BufferedImage image = null;
    protected menu menu1;
    /**
     * GlassPaneGameOver Constructor initializes the mouse listener and sets the panel to visible
     *
     * @param menu1 A parameter
     */
    public GlassPaneGameOver(menu menu1) {
        addMouseListener(this);
        setVisible(true);
        this.menu1 = menu1;
        repaint();
    }   

    /**
     * Method paintComponent paints the game over image along with the Score/Moves taken stats.
     *
     * @param g A parameter
     */
    protected void paintComponent(Graphics g) {
        int alpha = 190; // 50% transparent
        Color myColor = new Color(0, 0, 0, alpha);
        g.setColor(myColor);
        g.fillRect(0,0, 800, 800);
        if (image == null) {
            try {
                image = ImageIO.read(getClass().getResource("/img/gameover.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        g.drawImage(image, 120, 200, null);
        g.setColor(Color.white);
        Font myFont = new Font ("Courier New", 1, 17);
        g.setFont(myFont);
        g.drawString("Your Score: " + menu1.score, 250,420);
        g.drawString("Moves Taken This Round: " + menu1.rtbPane.moves, 200,435);
    }

    /**
     * Method mouseClicked is the mouse clicked listener
     *
     * @param e A parameter
     */
    public void mouseClicked(MouseEvent e) {
        setVisible(false);
        menu1.dispose();
        menu menu2 = new menu();
    }

    /**
     * Method mouseEntered
     *
     * @param e A parameter
     */
    public void mouseEntered(MouseEvent e) {}

    /**
     * Method mouseExited
     *
     * @param e A parameter
     */
    public void mouseExited(MouseEvent e) {}

    /**
     * Method mousePressed
     *
     * @param e A parameter
     */
    public void mousePressed(MouseEvent e) {}
    
    /**
     * Method mouseReleased
     *
     * @param e A parameter
     */
    public void mouseReleased(MouseEvent e) {}
}