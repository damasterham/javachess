package Chess.Scene.Pieces;

//Created by DaMasterHam on 08-09-2016.
//

import Chess.Attributes.Point;

// P, T, B, H, K ,Q
public abstract class Piece
{
    private static final String WHITE = "white";
    private static final String BLACK = "black";

    //private Board board;
    private String name;
    private char symbol;
    private String color;
    protected Point point;
    protected boolean ignoreObs = false;

    public Piece(String name, String color)
    {
        this.name = name;
        this.color = color;
    }

    // Accessors
    public String   getName()
    {
        return name;
    }

    public char     getSymbol()
    {
        return symbol;
    }

    public String   getColor()
    {
        return color;
    }

    public Point    getPosition()
    {
        return point;
    }

    public boolean  ignoreObstacles()
    {
        return ignoreObs;
    }

    // Mutators
    public void     setName(String name)
    {
        this.name = name;
    }

    public void     setSymbol(char symbol)
    {
        this.symbol = symbol;
    }

    public void     setColor(String color)
    {
        this.color = color;
    }

    public void     setPosition(Point point)
    {
        this.point = point;
    }

    public void     setPosition(int x, int y)
    {
        this.point.setX(x);
        this.point.setY(y);
    }

    // Abstract Accessors
    public abstract boolean isValidMove(Point to);

}
