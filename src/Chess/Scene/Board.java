package Chess.Scene;

import Chess.Attributes.DIRECTION;
import Chess.Attributes.Point;
import Chess.Scene.Pieces.*;
import com.sun.glass.ui.Size;

import java.util.ArrayList;
import java.util.List;

//Created by DaMasterHam on 08-09-2016.
//

public class Board
{
    private static final int SIZE = 8;
    private Piece[][] grid; //[X][Y]
    private List<Piece> removed;
    private boolean checkMate;

    public Board()
    {
        this.grid = new Piece[SIZE][SIZE];
        this.removed = new ArrayList<Piece>();
        this.checkMate = false;
    }

    // Initializations
    private void        initTower(int row, String color)
    {
        grid[0][row] = new Tower(color, 0, row);
        grid[7][row] = new Tower(color, 7, row);
    }

    private void        initKnight(int row, String color)
    {
        grid[1][row] = new Knight(color, 1, row);
        grid[6][row] = new Knight(color, 6, row);
    }

    private void        initBishop(int row, String color)
    {
        grid[2][row] = new Bishop(color,2,row);
        grid[5][row] = new Bishop(color,5,row);
    }

    private void        initKingQueen(int row, String color, DIRECTION dir)
    {
        if (dir == DIRECTION.UP)
        {
            grid[3][row] = new King(color,3,row);
            grid[4][row] = new Queen(color,4,row);
        }
        else
        {
            grid[4][row] = new King(color,4,row);
            grid[3][row] = new Queen(color,3,row);
        }

    }



    private void        initPawns(int row, String color, DIRECTION direction)
    {
        for (int i = 0; i < SIZE; i++)
        {
            setPiece(new Pawn(color, direction), new Point(i,row));
        }
    }

    private void        initImportantPieces(int row, String coloe, DIRECTION kingQueensPlacements)
    {

    }

    public void         initialize()
    {
        String black = "Black";
        String white = "White";

        int whiteRow = 7;
        int blackRow = 0;

        initTower(blackRow,black);
        initTower(whiteRow,white);

        initKnight(blackRow,black);
        initKnight(whiteRow,white);

        initBishop(blackRow,black);
        initBishop(whiteRow,white);

        initKingQueen(blackRow, black, DIRECTION.UP);
        initKingQueen(whiteRow,white, DIRECTION.DOWN);

        initPawns(blackRow+1, black, DIRECTION.UP);
        initPawns(whiteRow-1, white, DIRECTION.DOWN);

    }

    public void         seeRowsIndex()
    {
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                System.out.print(" x:"+i+"y:"+j+" ");
            }
        }
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

    public Piece        getLastRemoved()
    {
        return removed.get(removed.size()-1);
    }

    public boolean      hasOutOfPlay()
    {
        return removed.size() > 0;
    }

   public boolean       kingJustDied()
   {
       return removed.get(removed.size()-1).getName().equals("King");
   }

    public boolean      checkMate()
    {
        return checkMate;
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



    public void         setPiece(Piece piece, Point position)
    {
        piece.setPosition(position);
        grid[position.getX()][position.getY()] = piece;
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
