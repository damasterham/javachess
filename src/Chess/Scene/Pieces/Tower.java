package Chess.Scene.Pieces;//

import Chess.Attributes.Point;

//Created by DaMasterHam on 20-09-2016.
//
public class Tower extends Piece
{
    private static final String NAME = "Rook";

    public Tower(String color)
    {
        super(NAME, color);

        char symbol;

        if (color.toLowerCase().equals("white"))
            symbol = '♖';
        else
            symbol = '♜';

        setSymbol(symbol);

    }

    private boolean onlyOneDiffAxis(int x1, int x2, int y1, int y2)
    {
        return x1 == x2 && y1 != y2;
    }

    public boolean isValidMove(Point to)
    {
        return onlyOneDiffAxis(to.getX(), point.getX(), to.getY(), point.getY())
                || onlyOneDiffAxis(to.getY(), point.getY(), to.getX(), point.getX());
        //return ((to.getX() == point.getX() && to.getY() != point.getY()) || (to.getY() == point.getY() &&  to.getX() != point.getX()));
    }

//    public boolean move(Point to, Board board)
//    {
//        // Checks
//        if (isValidMove(to))
//        {
//            Piece spot = board.getPiece(to);
//
//            // Attack
//            if (spot != null && !spot.getColor().equals(this.getColor()))
//            {
//                board.removeFromPlay(spot);
//
//                return true;
//            }
//            // Empty move
//            else if (spot == null)
//            {
//                return true;
//            }
//
//            return false;
//        }
//    }
}
