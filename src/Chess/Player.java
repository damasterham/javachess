package Chess;//

import Chess.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

//Created by DaMasterHam on 20-09-2016.
//
public class Player
{
    private String name;
    private String color;
    private boolean turn;
    private Move move;
    private Piece currentPiece;
    private List<Move> pastMoves;

    public Player()
    {
        pastMoves = new ArrayList<Move>();
    }


    // Accessors
    public String       getName()
    {
        return name;
    }

    public String       getColor()
    {
        return color;
    }

    public boolean      isTurn()
    {
        return turn;
    }

    public Move         getMove()
    {
        return move;
    }

    public Piece        getCurrentPiece()
    {
        return currentPiece;
    }

    public Point        moveFrom()
    {
        return move.getFrom();
    }

    public Point        moveTo()
    {
        return move.getTo();
    }

    public boolean      owns(Piece piece)
    {
        return (color.toLowerCase().equals(piece.getColor().toLowerCase()));
    }

    public List<Move>   getPastMoves()
    {
        return pastMoves;
    }


    // Mutators
    public void         setName(String name)
    {
        this.name = name;
    }

    public void         setColor(String color)
    {
        this.color = color;
    }

    public void         startTurn()
    {
        turn = true;
    }

    public void         endTurn()
    {
        pastMoves.add(move);
        move = null;
        turn = false;
    }

    public void         setCurrentPiece(Piece piece)
    {
        this.currentPiece = piece;
    }

    public void         setMove(Move move)
    {
        this.move = move;
    }

    public void         newMove()
    {
        move = new Move();
    }

    public void         clearMove()
    {
        move = null;
    }

    public void         moveFrom(Point from)
    {
        move.setFrom(from);
    }

    public void         moveTo(Point to)
    {
        move.setTo(to);
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
