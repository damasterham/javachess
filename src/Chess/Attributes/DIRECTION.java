package Chess.Attributes;//

//Created by DaMasterHam on 20-10-2016.
//
public enum DIRECTION
{
    UP(1), DOWN(-1);

    private final int direction;
    DIRECTION(int direction)
    {
        this.direction = direction;
    }
    public int getValue()
    {
        return direction;
    }
}
