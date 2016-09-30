package Chess;

import Chess.Pieces.Piece;
import Chess.Pieces.Tower;

import java.util.ArrayList;
import java.util.List;

//Created by DaMasterHam on 08-09-2016.
//

public class Board
{
    private static final int SIZE = 8;
    private Piece[][] grid; //[X][Y]
    private List<Piece> removed;

    public Board()
    {
        this.grid = new Piece[SIZE][SIZE];
        this.removed = new ArrayList<Piece>();
    }

    // Accessors
    public Piece        getPiece(Point point)
    {
        if (point != null)
            return grid[point.getX()][point.getY()];
        return null;
    }

    public List<Piece>  getRemoved()
    {
        return removed;
    }

    public boolean      hasOutOfPlay()
    {
        return removed.size() > 0;
    }

    public boolean      hasPiece(Point pos)
    {
        return (grid[pos.getX()][pos.getY()] != null);
    }

    public boolean      isObstructed(Piece mover, Point to)
    {
        List<Point> movement;

        movement = mover.getPosition().getPointsBetween(to);

        for (Point p : movement)
        {
            if (hasPiece(p))
                return true;
        }

        return false;
    }

    public static int   getSIZE()
    {
        return SIZE;
    }

// Mutators

    private void        remove(Point point)
    {
        grid[point.getX()][point.getY()] = null;
    }

    private void        initPawns(int row, String color)
    {
        for (int i = 0; i < SIZE; i++)
        {
            setPiece(new Tower(color), new Point(i,row));
        }
    }

    public void         setPiece(Piece piece, Point position)
    {
        piece.setPosition(position);
        grid[position.getX()][position.getY()] = piece;
    }

    public void         initialize()
    {
        initPawns(1, "Black");
        initPawns(6, "White");
    }

    public void         movePiece(Piece piece, Point to)
    {
        remove(piece.getPosition());

        setPiece(piece, to);
    }

    public Piece        remove(Piece piece)
    {
        removed.add(piece);
        remove(piece.getPosition());
        return piece;
    }


    // Console specific accessors

    public void         printIndex()
    {
        // Y
        for (int i = 0; i < SIZE; i++) 
        {
            // X
            for (int j = 0; j < SIZE; j++)
            {
                System.out.print(i + "," + j +";");
            }
            System.out.println();
        }
    }

    // Helper accessors
    public String       getOutOfPlay()    // as String
    {
        String result = "";

        for (Piece p : removed)
        {
            result += p.getSymbol() + " ";
        }
        return result;
    }
}
