package Chess;//

import Chess.Pieces.Piece;

//Created by DaMasterHam on 20-09-2016.
//
public class Player
{
    private String name;
    private String color;
    private boolean turn;

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }

    public String getName()
    {

        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isTurn()
    {
        return turn;
    }

    public void setTurn(boolean turn)
    {
        this.turn = turn;
    }

    public  boolean owns(Piece piece)
    {
        return (color.toLowerCase().equals(piece.getColor().toLowerCase()));
    }



    //Could from be a piece object reference?
//    public void movePiece(Board board, Point from, Point to)
//    {
//        Piece p;
//        boolean moveSucces;
//
//        p = board.getPiece(from);
//        board.movePiece(p, to);
//
//        //SPECIAL CASE FOR PAWN
////         = p.move(to, board);
//    }
}
