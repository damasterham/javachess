package Chess.Pieces;

//Created by DaMasterHam on 08-09-2016.
//

import Chess.Board;
import Chess.IMovable;
import Chess.Point;

// P, T, B, H, K ,Q
public abstract class Piece implements IMovable
{
    private static final String WHITE = "white";
    private static final String BLACK = "black";

    //private Board board;
    private String name;
    private String symbol;
    private boolean direction; // only for pawns
    private String color;

    protected Point point;

    public Piece(String name, String symbol, String color, boolean direction)
    {
        this.name = name;
        this.symbol = symbol;
        this.color = color;
        this.direction = direction;


    }
//
//    public int findA(String text)
//    {
//        char a = 'A';
//        int i = 0;
//        while (i < text.length())
//        {
//            char letter = text.charAt(i);
//            i++;
//            if (letter == a)
//                return i;
//        }
//
//        return -1;
//    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public boolean isDirection()
    {
        return direction;
    }

    public void setDirection(boolean direction)
    {
        this.direction = direction;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public void setPosition(int x, int y)
    {
        this.point.setX(x);
        this.point.setY(y);
    }

    public void setPosition(Point point)
    {
        this.point = point;
    }

    public Point getPosition()
    {
        return point;
    }
}
