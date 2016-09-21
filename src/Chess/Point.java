
package Chess;//

import java.util.HashMap;
import java.util.Map;

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
        this.x = LetterMap.get(letter);
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
        this.y = LetterMap.get(letter);
    }

    public Point getDiff(Point other)
    {
        return new Point(this.x - other.x, this.y -other.y);
    }


}
