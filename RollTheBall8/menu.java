import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * RollTheBallPanel is the main frame for Roll The Ball. It is a JFrame which displays GlassPanes and the main game panel, RollTheBallPanel.
 * 
 * @author Keith Grable, Ethan De Bernardo, Martin McBride, Julia Dunbar, Tim Cronin
 * @version 2017-04-01
 */
public class menu extends JFrame implements ActionListener{
    private GlassPane glassPane;
    private GlassPaneWin glassPaneWin;
    private GlassPaneGameOver glassPaneGameOver;
    protected RollTheBallPanel rtbPane;
    private Container contentPane;
    private JButton button2;
    private JButton button1;
    private JLabel label1;
    protected boolean hasWon;
    protected int level;
    protected int score;
    /**
     * menu Constructor Initializes the start level, the components of the JFrame, and sets it visible.
     */
    public menu() {
        this.level = 1;
        initComponents();
        setVisible(true);
    }

    /**
     * Method initComponents Initializes all the components of the JFrame. 1 Label(For the background), 2 Buttons (Start, About) and
     * configures the JFrame settings.
     *
     */
    protected void initComponents() {
        //GLOBALS
        hasWon = false;   
        
        //BUTTONS
        button1 = new JButton();
        button2 = new JButton();   
        
        //LABELS
        label1 = new JLabel();  
        
        //PANES
        rtbPane = new RollTheBallPanel(this,level);
        glassPane = new GlassPane();
        glassPaneGameOver = new GlassPaneGameOver(this);
        glassPaneWin = new GlassPaneWin(this);
        
        //THIS
        setMinimumSize(new Dimension(550, 550));
        setMaximizedBounds(new Rectangle(0, 0, 550, 550));
        setTitle("Roll The Ball");
        contentPane = new Container();
        contentPane = getContentPane();
        contentPane.setLayout(null);
        setGlassPane(glassPane);
        setResizable(false);
        
        //BUTTON1
        button1.setIcon(new ImageIcon(this.getClass().getResource("/img/startbutton.png")));
        contentPane.add(button1);
        button1.setBounds(150, 260, 240, 75);
        button1.setBorder(null);
        button1.addActionListener(
            (ActionEvent e) -> {actionPerformed(e);}
        );
        //BUTTON2
        button2.setIcon(new ImageIcon(this.getClass().getResource("/img/aboutbutton.png")));
        contentPane.add(button2);
        button2.setBounds(150, 360, 240, 75);
        button2.setBorder(null);
        button2.addActionListener(
            (ActionEvent e) -> {actionPerformed2(e);}
        );
        //LABEL1
        label1.setIcon(new ImageIcon(this.getClass().getResource("/img/homescreen.jpg")));
        contentPane.add(label1);
        label1.setBounds(0, 0, 550, 520);
        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        
        pack();
        setLocationRelativeTo(getOwner());
    }   

    /**
     * Method actionPerformed is the action performed method for the Start button. Clicking this sets the contentpane to the main game panel.
     *
     * @param e A parameter
     */
    public void actionPerformed (ActionEvent e) {
        rtbPane.setVisible(true);
        this.setContentPane(rtbPane);
        pack();
    }

    /**
     * Method actionPerformed2 is the action performed method for the About button. This opens up a glasspane for instructions.
     *
     * @param e A parameter
     */
    public void actionPerformed2 (ActionEvent e) {
        glassPane.setVisible(true);
    } 

    /**
     * Method won is the method that's called when the (rtbPane.hasCompletePath() == True). It shows the GlassPaneWin panel and creates new levels.
     * 
     */
    public void won()
    {   score += rtbPane.getScore();
        if(level == 3)
        {
            this.setGlassPane(glassPaneGameOver);
            glassPaneGameOver.setVisible(true);
        }
        else
        {
            this.hasWon = hasWon;
            level++;
            this.setGlassPane(glassPaneWin);
            glassPaneWin.setVisible(true);
        }
    }

    /**
     * Method createNewLevel creates a new rtbPane with an incremented level. It resets the hasWon to false.
     *
     */
    public void createNewLevel()
    {
        hasWon = false;
        rtbPane = new RollTheBallPanel(this,level);
        rtbPane.setVisible(true);
        this.setContentPane(rtbPane);
        pack();
    }
}
