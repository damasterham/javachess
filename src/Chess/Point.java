
package Chess;//

import java.util.*;

//Created by DaMasterHam on 09-09-2016.
//
public class Point
{
    private int x;
    private int y;

    public Point(){}

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

    public Point(int y, int x)
    {
        this.y = y;
        this.x = x;
    }


    private Point getDiff(Point other)
    {
        return new Point(other.getX() - x, other.getY() - y);
    }

    private int getSteps(Point other)
    {
        if (other.getX() > other.getY())
            return other.getX();
        else
            return other.getY();
    }

    private float getIncrement(int value, int steps)
    {
        return value / (float) steps;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setX(char letter)
    {
        this.x = CharIntMap.getIndex(letter);
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setY(char letter)
    {
        this.y = CharIntMap.getIndex(letter);
    }


    public List<Point> getPointsBetween(Point to)
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

        for (int i = 0; i < steps; i++)
        {
            x += xInc;
            y += yInc;

            list.add(new Point(x,y));
        }
        return list;
    }

    public String toChess()
    {
        return String.format("%s%d",CharIntMap.getChar(x), y+1);
    }




//    public double getSlope(Point other)
//    {
//        return  (double)(other.getY() - y) / (double)(other.getX() - x);
//    }

}
