package Chess.Pieces;//

import Chess.Board;
import Chess.Point;

//Created by DaMasterHam on 20-09-2016.
//
public class Queen extends Piece
{
    private static final String NAME = "Queen";

    public Queen(String color, boolean direction)
    {
        super(NAME, NAME.substring(0,1), color, direction);
    }

    public boolean isValidMove(Point point)
    {
        Point diff = point.getDiff(point);

        return ((diff.getY() != 0 && diff.getX() == 0) || (diff.getY() == 0 &&  diff.getX() != 0));
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
