package Chess.Scene.Pieces;
import Chess.Attributes.DIRECTION;
import Chess.Attributes.Point;

//Created by DaMasterHam on 09-09-2016.
//
public class Pawn extends Piece
{
    private static final String NAME = "Pawn";

    private DIRECTION direction;
    private boolean isAttack;


    public Pawn(String color, DIRECTION direction)
    {
        super(NAME, color);
        this.direction = direction;

        char symbol;

        if (color.toLowerCase().equals("white"))
            symbol = '♙';
        else
            symbol = '♟';
        setSymbol(symbol);
    }

    public Pawn(String color, DIRECTION direction, int x, int y)
    {
        super(NAME, color, x, y);
        this.direction = direction;

        char symbol;

        if (color.toLowerCase().equals("white"))
            symbol = '♙';
        else
            symbol = '♟';
        setSymbol(symbol);
    }



    private boolean validAttackX(Point to)
    {
        int x = to.getX() - point.getX();

        return x == 1 || x == -1;
    }

    private boolean validMoveY(Point to)
    {
        int y = to.getY() - point.getY();
        return y == direction.getValue();
    }

    private boolean isAttack(Point to)
    {
        return to.getX() != point.getX();
    }

    public boolean isValidMove(Point to)
    {
        isAttack = false;
        // The direction and movement is correct
        if (validMoveY(to))
        {
            // If the move is also an attack
            if (isAttack(to))
            {
                isAttack = true;
                // Is it a valid attack
                return validAttackX(to);
            }
            // Otherwise just a valid move
            return true;
        }
        // Invalid direction and movement
        else
        {
            return false;
        }
    }


    public boolean isValidAttack()
    {
        return isAttack;
    }



}
