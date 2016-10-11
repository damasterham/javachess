package Chess.Scene.Pieces;//

import Chess.Attributes.Point;

//Created by DaMasterHam on 27-09-2016.
//
public class Bishop extends Piece
{
    private static final String NAME = "Bishop";
    
    public Bishop(String color)
    {
        super(NAME, color);

        char symbol;

        if (color.toLowerCase().equals("white"))
            symbol = '♗';
        else
            symbol = '♝';

        setSymbol(symbol);
        
    }

    //identical increment
    // 0,0 -> 2,2
    private boolean identicalIncrement(Point to)
    {
        int yDiff = to.getY() - getPosition().getY();
        int xDiff = to.getX() - getPosition().getX();

        return yDiff == xDiff || yDiff == (xDiff * -1);
    }

    public boolean isValidMove(Point to)
    {
        return identicalIncrement(to);
    }
}
