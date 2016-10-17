package Chess.Scene.Pieces;//

import Chess.Attributes.Point;

//Created by DaMasterHam on 17-10-2016.
//
public class Queen extends Piece
{
    private static final String NAME = "Queen";

    public Queen(String color)
    {
        super(NAME,color);

        char symbol;

        if (color.toLowerCase().equals("white"))
            symbol = '♕';
        else
            symbol = '♛';
    }

    private boolean onlyOneDiffAxis(int x1, int x2, int y1, int y2)
    {
        return x1 == x2 && y1 != y2;
    }

    private boolean identicalIncrement(Point to)
    {
        int yDiff = to.getY() - getPosition().getY();
        int xDiff = to.getX() - getPosition().getX();

        return yDiff == xDiff || yDiff == (xDiff * -1);
    }


    public boolean isValidMove(Point to)
    {
        return identicalIncrement(to) ||
                (onlyOneDiffAxis(to.getX(), point.getX(), to.getY(), point.getY()) ||
                        onlyOneDiffAxis(to.getY(), point.getY(), to.getX(), point.getX()));
    }
}
