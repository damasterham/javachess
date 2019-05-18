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

        setSymbol(symbol);
    }

    public Knight(String color, int x, int y)
    {
        super(NAME,color,x,y);

        char symbol;
        ignoreObs = true;

        if (color.toLowerCase().equals("white"))
            symbol = '♘';
        else
            symbol = '♞';

        setSymbol(symbol);
    }

    private boolean isPosOrNeg(int a, int pn)
    {
        return a == pn || a == -pn;
    }
// dont work
    private boolean specificSpot(Point to)
    {
        int x = to.getX() - point.getX();
        int y = to.getY() - point.getY();

        return isPosOrNeg(x,2) && isPosOrNeg(y, 1) || isPosOrNeg(x, 1) && isPosOrNeg(y, 2);
    }

    public boolean isValidMove(Point to)
    {
        return specificSpot(to);
    }
}
