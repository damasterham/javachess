package Chess.Scene.Pieces;//

import Chess.Attributes.Point;

//Created by DaMasterHam on 17-10-2016.
//
public class Knight extends Piece
{
    private static final String NAME = "Knight";

    public Knight(String color)
    {
        super(NAME,color);

        char symbol;
        ignoreObs = true;

        if (color.toLowerCase().equals("white"))
            symbol = '♘';
        else
            symbol = '♞';
    }

    private boolean isPosOrNeg(int a, int pn)
    {
        return a == pn || a == -pn;
    }

    private boolean specificSpot(Point to)
    {
        int x = to.getX() - point.getX();
        int y = to.getX() - point.getY();

        return isPosOrNeg(x,2) && isPosOrNeg(y, 1) || isPosOrNeg(x, 1) && isPosOrNeg(y, 2);
    }

    public boolean isValidMove(Point to)
    {
        return specificSpot(to);
    }
}
