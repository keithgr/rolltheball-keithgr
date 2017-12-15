import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class RollTheBallPanel here.
 * 
 * @author Keith Grable, Ethan De Bernardo, Martin McBride, Julia Dunbar, Tim Cronin
 * @version 2017-04-01
 */
public class RollTheBallPanel extends JPanel implements ActionListener
{
    public static final int WINDOW_MIN_X = 550;
    public static final int WINDOW_MIN_Y = 550;
    public static final int COLS = 4;
    public static final int ROWS = 4;
    public static final int HGAP = 0; //Horizontal Gap
    public static final int VGAP = 0; //Vertical Gap
    public static final int NUMBER_OF_CELLS = ROWS * COLS;
    private Piece[][] buttons2;
    private int actionCheck = 0; //0 = select piece, 1 = move piece
    private Piece pieceToMove;
    private Piece moveTo;
    protected int starCount;
    protected boolean hasWon;
    protected boolean exitToMenu;
    protected menu menu1;
    protected int level;
    protected int moves;
    /**
     * Constructor for objects of class RollTheBallPanel
     */
    public RollTheBallPanel(menu menu1,int level)
    {
        this.menu1 = menu1;
        pieceToMove = null;//new Piece(new Point(-1,-1));
        moveTo = null;//new Piece(new Point(-1,-1));
        starCount = 0;
        moves = 0;
        hasWon = false;
        exitToMenu = false;
        this.level = level;

        buttons2 = new Piece[4][4];
        createLevel(this.level);

        this.setLayout(new GridLayout(ROWS,COLS,HGAP,VGAP));
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.createPiece();
        //this.pack();
    }

    public void createLevel(int level)
    {
        if(level == 1)
        {
            buttons2[0][0] = new Piece(new Point(0,0), Piece.EAST, Piece.NONE, true, false);
            buttons2[0][1] = new Piece(new Point(0,1));
            buttons2[0][2] = new Piece(new Point(0,2), Piece.WEST, Piece.SOUTH, false, false);
            buttons2[0][3] = new Piece(new Point(0,3));
            buttons2[1][0] = new Piece(new Point(1,0));
            buttons2[1][1] = new Piece(new Point(1,1), Piece.WEST, Piece.SOUTH, false, true);
            buttons2[1][2] = new Piece(new Point(1,2));
            buttons2[1][3] = new Piece(new Point(1,3));
            buttons2[2][0] = new Piece(new Point(2,0));
            buttons2[2][1] = new Piece(new Point(2,1), Piece.EAST, Piece.NORTH, false, true);
            buttons2[2][2] = new Piece(new Point(2,2), Piece.NORTH, Piece.SOUTH, true, false);
            buttons2[2][3] = new Piece(new Point(2,3));
            buttons2[3][0] = new Piece(new Point(3,0));
            buttons2[3][1] = new Piece(new Point(3,1), Piece.EAST, Piece.NORTH, false, true);
            buttons2[3][2] = new Piece(new Point(3,2));
            buttons2[3][3] = new Piece(new Point(3,3), Piece.WEST, Piece.NONE, true, false);
        }
        else if(level == 2)
        {
            buttons2[0][0] = new Piece(new Point(0,0), Piece.EAST, Piece.NONE, true, false);
            buttons2[0][1] = new Piece(new Point(0,1), Piece.EAST, Piece.SOUTH,false,false);
            buttons2[0][2] = new Piece(new Point(0,2), Piece.EAST, Piece.SOUTH,false,false);
            buttons2[0][3] = new Piece(new Point(0,3), Piece.WEST, Piece.SOUTH,false,false);
            buttons2[1][0] = new Piece(new Point(1,0), Piece.WEST, Piece.NORTH,false,false);
            buttons2[1][1] = new Piece(new Point(1,1), Piece.EAST, Piece.NORTH, false, true);
            buttons2[1][2] = new Piece(new Point(1,2), Piece.EAST, Piece.NORTH, false, true);
            buttons2[1][3] = new Piece(new Point(1,3), Piece.WEST, Piece.NORTH, false, true);
            buttons2[2][0] = new Piece(new Point(2,0), Piece.NORTH, Piece.SOUTH, true, false);
            buttons2[2][1] = new Piece(new Point(2,1));
            buttons2[2][2] = new Piece(new Point(2,2));
            buttons2[2][3] = new Piece(new Point(2,3));
            buttons2[3][0] = new Piece(new Point(3,0), Piece.SOUTH, Piece.WEST, false, true);
            buttons2[3][1] = new Piece(new Point(3,1));
            buttons2[3][2] = new Piece(new Point(3,2));
            buttons2[3][3] = new Piece(new Point(3,3), Piece.WEST, Piece.NONE, true, false);
        }
        else if(level == 3)
        {
            buttons2[0][0] = new Piece(new Point(0,0), Piece.EAST, Piece.NONE, true, false);
            buttons2[0][1] = new Piece(new Point(0,1), Piece.NORTH, Piece.WEST,false,false);
            buttons2[0][2] = new Piece(new Point(0,2), Piece.NORTH, Piece.SOUTH,false,false);
            buttons2[0][3] = new Piece(new Point(0,3), Piece.SOUTH, Piece.WEST, false, true);
            buttons2[1][0] = new Piece(new Point(1,0));
            buttons2[1][1] = new Piece(new Point(1,1));
            buttons2[1][2] = new Piece(new Point(1,2));
            buttons2[1][3] = new Piece(new Point(1,3), Piece.EAST, Piece.WEST,false,false);
            buttons2[2][0] = new Piece(new Point(2,0), Piece.NORTH, Piece.EAST, false,true);
            buttons2[2][1] = new Piece(new Point(2,1), Piece.NORTH, Piece.SOUTH, true,false);
            buttons2[2][2] = new Piece(new Point(2,2), Piece.EAST, Piece.WEST,false,false);
            buttons2[2][3] = new Piece(new Point(2,3), Piece.EAST, Piece.WEST,false,true);
            buttons2[3][0] = new Piece(new Point(3,0), Piece.EAST, Piece.WEST,false,false);
            buttons2[3][1] = new Piece(new Point(3,1), Piece.SOUTH, Piece.EAST,false,true);
            buttons2[3][2] = new Piece(new Point(3,2));
            buttons2[3][3] = new Piece(new Point(3,3), Piece.WEST, Piece.NONE, true, false);
        }
    }

    /**
     * createPiece (String s)
     * 
     * Creates a Piece and adds an ActionListener
     * 
     * @param   String s
     */
    private void createPiece () {
        for(Piece[] i : buttons2)
        {
            for(Piece c : i)
            {
                c.addActionListener(
                    (ActionEvent e) -> {actionPerformed(e);}
                );
                this.add(c);
            }
        }
    }

    public void actionPerformed (ActionEvent e)
    {

        if(!((Piece)e.getSource()).isEmpty && !((Piece)e.getSource()).isFixed)//If we haven't selected a piece to move
        {
            pieceToMove = (Piece)e.getSource();
            actionCheck = 1;
        }
        else if(actionCheck == 1) //If we have selected a piece to move
        {
            Piece temp = (Piece)e.getSource();
            if(temp.loc.x - pieceToMove.loc.x == 1 && temp.loc.y == pieceToMove.loc.y){
                moveTo = temp;
                attemptMovePiece(Piece.SOUTH);
            }
            else if(temp.loc.x - pieceToMove.loc.x == -1 && temp.loc.y == pieceToMove.loc.y){ 
                moveTo = temp;
                attemptMovePiece(Piece.NORTH);
            }

            else if(temp.loc.x == pieceToMove.loc.x && temp.loc.y - pieceToMove.loc.y == 1)
            { 
                moveTo = temp; 
                attemptMovePiece(Piece.EAST);
            }

            else if(temp.loc.x == pieceToMove.loc.x && temp.loc.y - pieceToMove.loc.y == -1)
            { 
                moveTo = temp; 
                attemptMovePiece(Piece.WEST);

            }

            actionCheck = 0;
            pieceToMove = null;
            moveTo = null;

            //debug(false);
        }
    }

    public void attemptMovePiece(int dir)
    {
        //the coordinates of the attempted space to move to
        int newRow = pieceToMove.loc.x + Piece.ROW_DELTAS[dir];
        int newCol = pieceToMove.loc.y + Piece.COL_DELTAS[dir];
        int oldRow = pieceToMove.loc.x;
        int oldCol = pieceToMove.loc.y;
        //checks if the attempted space is out-of-bounds or occupied
        if(pieceToMove == null || moveTo == null || newRow < 0 || newRow >= buttons2.length || newCol < 0 || newCol >= buttons2[0].length
        || pieceToMove.isEmpty || pieceToMove.isFixed || !moveTo.isEmpty)
        {
            return;
        }
        else
        {
            //playSound();
            buttons2[oldRow][oldCol] = moveTo;
            buttons2[newRow][newCol] = pieceToMove;
            buttons2[oldRow][oldCol].loc.x = oldRow;
            buttons2[oldRow][oldCol].loc.y = oldCol;
            buttons2[newRow][newCol].loc.x = newRow;
            buttons2[newRow][newCol].loc.y = newCol;
            this.removeAll();
            //this.dispose();
            moves++;
            refreshPanel();
        }
        if(hasCompletePath())
        {
            setWon(true);
        }
    }

    public void setWon(boolean hasWon)
    {
        this.hasWon = hasWon;
        this.menu1.won();
    }

    /**
     * Finds the piece that is adjacent to a given piece in the specified direction
     *
     * @param p The given piece
     * @param heading The specified direction
     * @return The piece that is adjacent to the given piece, or null if the piece is non-existent or out-of-bounds
     */
    private Piece getAdj(int heading)
    {
        int newRow = pieceToMove.loc.x + Piece.ROW_DELTAS[heading];
        int newCol = pieceToMove.loc.y + Piece.COL_DELTAS[heading];
        //check for out-of-bounds
        if(newRow < 0 || newRow >= buttons2.length || newCol < 0 || newCol >= buttons2[0].length)
            return null;
        return buttons2[newRow][newCol];
    }

    private Piece getAdjForCompletePath(Piece ptr, int heading)
    {
        int newRow = ptr.loc.x + Piece.ROW_DELTAS[heading];
        int newCol = ptr.loc.y + Piece.COL_DELTAS[heading];
        //check for out-of-bounds
        if(newRow < 0 || newRow >= buttons2.length || newCol < 0 || newCol >= buttons2[0].length)
            return null;
        return buttons2[newRow][newCol];
    }

    public void refreshPanel()
    {
        this.removeAll();
        for(Piece[] i : buttons2)
        {
            for(Piece c : i)
            {
                this.add(c);
            }
        }
        this.revalidate();
        this.repaint();
    }

    public boolean hasCompletePath()
    {
        
        
        //the root of our path
        Piece startPiece = buttons2[0][0];
        Piece endPiece = buttons2[3][3];
        Piece ptr = startPiece;

        //set the direction that the ball is heading to the non-null entry/exit of startPiece
        int heading = startPiece.entry;
        if(heading == Piece.NONE) { heading = startPiece.exit; }

        //attempt to traverse path to the endPiece
        //ptr is never null
        while (ptr != endPiece) {
            //collects a star if it is there
            if(ptr.hasStar)
                starCount++;
            //get the next piece to traverse to
            Piece next = getAdjForCompletePath(ptr, heading);
            //if ball is heading to nothing, then path is not complete
            if (next == null) { 
                starCount = 0;
                return false;
            }
            //if ball is heading toward next’s entry
            else if (next.entry != Piece.NONE && next.entry == (heading+2)%4 ) {
                ptr = next;
                heading = ptr.exit;
            }
            //if ball is heading toward next’s exit
            else if (next.exit != Piece.NONE && next.exit == (heading+2)%4 ) {
                ptr = next;
                heading = ptr.entry;
            }
            else {
                starCount = 0;
                return false;
            }
        }
        return true;
    }

    public int getScore()
    {
        return starCount * 1000;
    }   

    //     public void playSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    //     {
    //         String soundName = "/sounds/click.wav";    
    //         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
    //         Clip clip = AudioSystem.getClip();
    //         clip.open(audioInputStream);
    //         clip.start();
    //     }

}
