import javax.swing.*;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Objects;
/**
 * A Piece occupies one tile on a slidable puzzle board<br>
 * If it is not fixed, it may be moved to any vacant laterally adjacent space
 *
 * @author Keith Grable, Ethan De Bernardo, Martin McBride, Julia Dunbar, Tim Cronin
 * @version 2017-04-01
 */
public class Piece extends JButton
{
    /** constants to represent cardinal directions */
    protected static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3, NONE = 4;

    /** the change in row from moving one space in the given cardinal direction */
    protected static final int[] ROW_DELTAS = {-1, 0, 1, 0};

    /** the change in column from moving one space in the given cardinal direction */
    protected static final int[] COL_DELTAS = {0, 1, 0, -1};

    /** location of the piece */
    protected Point loc;

    /** cardinal directions from which the ball can enter/exit the piece */
    protected int entry, exit;

    /** true if and only if the piece is fixed to the board */
    protected boolean isFixed;

    /** true if and only if the piece has a collectible star */
    protected boolean hasStar;

    protected int row;
    protected int col;
    protected boolean isEmpty;
    public Piece(Point l)
    {
        loc = l;
        isEmpty = true;
        setImage();
        row = getRow();
        col = getCol();        
        entry = exit = Piece.NONE;       
    }

    /**
     * @param   l   The location of the piece in 2D space
     * @param   entr    The cardinal direction of the entry opening
     * @param   ex  The cardinal direction of the exit opening
     * @param   isF True if and only if the piece is fixed to the board
     * @param   hasS    True if and only if the piece contains a collectible star
     */
    public Piece(Point l, int entr, int ex, boolean isF, boolean hasS)
    {
        loc = l;
        entry = entr;
        exit = ex;
        isFixed = isF;
        hasStar = hasS;
        isEmpty = false;
        //setText(entry + (hasStar ? "*" : "") + exit);
        setImage();
        row = getRow();
        col = getCol();
    }    

    public int getRow()
    {
        return loc.x;
    }

    public int getCol()
    {
        return loc.y;
    }

    public void setImage()
    {
        ImageIcon img = new ImageIcon(); 
        //NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3, NONE = 4;
        if(this.getRow() == 0 && this.getCol() == 0)
        {
            img = new ImageIcon(this.getClass().getResource("/img/start.png"));
        }
        else if(this.getRow() == 3 && this.getCol() == 3)
        {
            img = new ImageIcon(this.getClass().getResource("/img/end.png"));
        }
        else if((entry == 1 && exit == 3) || (entry == 3 && exit == 1))
        {
            if(this.hasStar)
                img = new ImageIcon(this.getClass().getResource("/img/EWSTAR.png"));
            else
                img = new ImageIcon(this.getClass().getResource("/img/EW.png"));
        }
        else if((entry == 0 && exit == 1) || (entry == 1 && exit == 0))
        {
            if(this.hasStar)
                img = new ImageIcon(this.getClass().getResource("/img/NESTAR.png"));
            else
                img = new ImageIcon(this.getClass().getResource("/img/NE.png"));
        }
        else if((entry == 0 && exit == 2) || (entry == 2 && exit == 0))
        {
            if(isFixed)
                img = new ImageIcon(this.getClass().getResource("/img/NSlocked.png"));
            else
                img = new ImageIcon(this.getClass().getResource("/img/NS.png"));
        }
        else if((entry == 0 && exit == 3) || (entry == 3 && exit == 0))
        {
            if(this.hasStar)
                img = new ImageIcon(this.getClass().getResource("/img/NWSTAR.png"));
            else
                img = new ImageIcon(this.getClass().getResource("/img/NW.png"));   
        }
        else if((entry == 2 && exit == 1) || (entry == 1 && exit == 2))
        {
            if(this.hasStar)
                img = new ImageIcon(this.getClass().getResource("/img/SESTAR.png"));
            else
                img = new ImageIcon(this.getClass().getResource("/img/SE.png"));
        }
        else if((entry == 2 && exit == 3) || (entry == 3 && exit == 2))
        {
            if(this.hasStar)
                img = new ImageIcon(this.getClass().getResource("/img/SWSTAR.png"));
            else
                img = new ImageIcon(this.getClass().getResource("/img/SW.png"));  
        }
        else
        {
            img = new ImageIcon(this.getClass().getResource("/img/empty.png"));  
        }
        this.setIcon(img);
        this.setBorder(null);
    }

    public String toString()
    {
        char[] temp = {'N', 'E', 'S', 'W', '.'};
        char f1 = (isFixed ? '{' : '[' );
        char f2 = (isFixed ? '}' : ']' );
        return "" + f1 + temp[entry] + (hasStar?'*':' ') + temp[exit] + f2 + "("+getRow()+","+getCol()+")";
    }

    public boolean equals(Object o)
    {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Piece)) {
            return false;
        }
        Piece p = (Piece) o;    
        return p.loc.equals(loc) && p.loc.x == loc.x && p.loc.y == loc.y;
    }

    public int hashCode()
    {
        return Objects.hash(loc, loc.x, loc.y);
    }
}//end class

