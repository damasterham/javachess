
package Chess.Attributes;//

import Chess.CharIntMap;

import java.util.*;

//Created by DaMasterHam on 09-09-2016.
//
public class Point
{
    private static final int LIMIT = 8;

    private int x;
    private int y;

    public Point(){}

    public Point(int x, int y)
    {
        this.y = y;
        this.x = x;
    }

    public Point(String input)
    {
        if (input.length() == 2)
        {
            this.setX(input.charAt(0));
            try
            {
                // DO number map
                int a = Integer.parseInt(""+input.charAt(1));
                this.setY(a -1);

            }
            catch (NumberFormatException e)
            {

            }
        }
    }


    // Accessors
    public int          getX()
    {
        return x;
    }

    public int          getY()
    {
        return y;
    }

    private Point       getDiff(Point other)
    {
        return new Point(other.getX() - x, other.getY() - y);
    }

    private float       getIncrement(int value, int steps)
    {
        return value / (float) steps;
    }

    private int         getSteps(Point other)
    {
        if (other.getX() > other.getY())
            return other.getX();
        else
            return other.getY();
    }

    public List<Point>  getPointsBetween(Point to)
    {
        List<Point> list = new ArrayList<Point>();
        int steps;

        Point diff = getDiff(to);

        int x = this.x;
        int y = this.y;
        int xInc;
        int yInc;

        steps = getSteps(diff);
        xInc = (int)getIncrement(diff.getX(),steps);
        yInc = (int)getIncrement(diff.getY(),steps);

        for (int i = 0; i < steps - 1; i++) // -1 to remove last point check
        {
            x += xInc;
            y += yInc;

            list.add(new Point(x,y));
        }
        return list;
    }


    // Mutators
    public void         setX(int x)
    {
        if (x >= 0 && x < LIMIT)
            this.x = x;
    }

    public void         setX(char letter)
    {
        int a = CharIntMap.getIndex(letter);
        if (a > 0)
        {
            this.x = a;
        }
    }

    public void         setY(int y)
    {
        if (y >= 0 && y < LIMIT)
            this.y = y;
    }

    public void         setY(char letter)
    {
        int a = CharIntMap.getIndex(letter);
        if (a > 0)
        {
            this.y = a;
        }
    }


    // Console accessors
    public String       toChess()
    {
        return String.format("%s%d",CharIntMap.getChar(x), y+1);
    }

    public String       toString()
    {
        return String.format("%d,%d",x,y);
    }



//    public double getSlope(Point other)
//    {
//        return  (double)(other.getY() - y) / (double)(other.getX() - x);
//    }

}
