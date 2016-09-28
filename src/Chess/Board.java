package Chess;

import Chess.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

//Created by DaMasterHam on 08-09-2016.
//

public class Board
{
    private static final int SIZE = 8;

    private Piece[][] grid;
    private List<Piece> removed;

    public Board()
    {
        this.grid = new Piece[SIZE][SIZE];
        this.removed = new ArrayList<Piece>();
    }


    public List<Piece> getRemoved()
    {
        return removed;
    }

    public String getOutOfPlay()
    {
        String result = "";

        for (Piece p : removed)
        {
            result += p.getSymbol() + " ";
        }
        return result;
    }

    public void setPiece(Piece piece, Point position)
    {
        piece.setPosition(position);
        grid[position.getX()][position.getY()] = piece;
    }


    private void attack(Piece attacker, Piece defender)
    {
        removed.add(defender);
        setPiece(attacker, defender.getPosition());
    }

    public Piece getPiece(Point point)
    {
        if (point != null)
        return grid[point.getX()][point.getY()];
        return null;
    }

    private void remove(Point point)
    {
        grid[point.getX()][point.getY()] = null;
    }

    public Piece remove(Piece piece)
    {
        removed.add(piece);
        remove(piece.getPosition());
        return piece;
    }

    public boolean hasPiece(Point pos)
    {
        return (grid[pos.getX()][pos.getY()] != null);
    }

    public boolean isObstructed(Piece mover, Point to)
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

    public void movePiece(Piece piece, Point to)
    {
        remove(piece.getPosition());

        setPiece(piece, to);
    }
//    public void movePiece(Piece piece, Point to)
//    {
//        Piece spot;
//
//        spot = this.getPiece(to);
//
//        // Natural move
//        if (piece.isValidMove(to) && spot == null)
//        {
//            setPiece(piece, to);
//        }
//        // Attack
//        else if (piece.isValidMove(to) && spot != null)
//        {
//            attack(piece, spot);
//        }
//
//    }

    public void print()
    {
        // Prints top border
        System.out.print('╔');
        for (int i = 0; i < SIZE*2; i++)
        {
            if ((i % 2) == 0)
                System.out.print('═');
            else
                System.out.print('╦');

        }
        System.out.println('╗');

        // Prints Letteres
        System.out.print("║\u2003");
        for (int i = 0; i < CharIntMap.getSize(); i++)
        {
            System.out.print(String.format("║%s\u2009\u2009\u200A\u200A",(""+CharIntMap.getChar(i))).toUpperCase());
        }
        System.out.println('║');

        // Prints Numbers and spaces
        // Y
        for (int i = 0; i < SIZE*2; i++)
        {
            if ((i % 2) == 0)
            {
                System.out.print("║"+((i/2)+1));
            }
            else
            {
                System.out.print("╠═");
            }

            // X
            for (int j = 0; j < SIZE; j++)
            {
                if ((i % 2) == 0)
                {
                    Piece p = getPiece(new Point(j,(i/2)));
                    if (p != null)
                        System.out.print(String.format("║%s",p.getSymbol()));
                    else
                        System.out.print("║╳");
                }
                else
                {
                    System.out.print("╬═");
                }
            }
            System.out.println('║');
        }
    }
    
    public void printIndex()
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
}
