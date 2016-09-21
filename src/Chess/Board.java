package Chess;

import Chess.Pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Created by DaMasterHam on 08-09-2016.
//

public class Board
{
    private static final int SIZE = 8;

    private Piece[][] grid;
    private List<Piece> outOfPlay;

    public Board()
    {
        LetterMap.init();
        this.grid = new Piece[SIZE][SIZE];
        this.outOfPlay = new ArrayList<Piece>();
    }

    public void getLetterMap()
    {
        for (char key : LetterMap.getMap().keySet())
        {
            for (int i = 1; i <= SIZE; i++)
            {
                System.out.print(" " + key + i +" ");
            }
            System.out.println();
        }
    }




    public void setPiece(Piece piece, Point position)
    {
        piece.setPosition(position);
        grid[position.getX()][position.getY()] = piece;
    }
    private void attack(Piece attacker, Piece defender)
    {
        outOfPlay.add(defender);
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
        outOfPlay.add(piece);
        remove(piece.getPosition());
        return piece;
    }

    public boolean hasPiece(Point pos)
    {
        return (grid[pos.getX()][pos.getY()] != null);
    }


    public void movePiece(Piece piece, Point to)
    {
        Piece spot;

        spot = this.getPiece(to);

        // Natural move
        if (piece.isValidMove(to) && spot == null)
        {
            setPiece(piece, to);
        }
        // Attack
        else if (piece.isValidMove(to) && spot != null)
        {
            attack(piece, spot);
        }

    }

    public void print()
    {
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                Piece p = getPiece(new Point(i,j));
                if (p != null)
                    System.out.print(p.getSymbol());
                else
                    System.out.print('O');
            }
            System.out.println();
        }
    }
}
