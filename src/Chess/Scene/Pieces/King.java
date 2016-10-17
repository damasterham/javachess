package Chess.Scene.Pieces;//

import Chess.Attributes.Point;

//Created by DaMasterHam on 17-10-2016.
//
public class King extends Piece
{
    private static final String NAME = "King";

    public King(String color)
    {
        super(NAME,color);

        char symbol;

        if (color.toLowerCase().equals("white"))
            symbol = '♔';
        else
            symbol = '♚';
    }

    private boolean zeroOneRange(int a)
    {
        return a < 2 && a > -2;
    }

    private boolean onlyOneSpace(Point to)
    {
        int x = to.getX() - point.getX();
        int y = to.getY() - point.getY();

        return zeroOneRange(x) && zeroOneRange(y);
    }

    public boolean isValidMove(Point to)
    {
        return onlyOneSpace(to);
    }
}
