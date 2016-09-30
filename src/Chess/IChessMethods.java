package Chess;//

import Chess.Pieces.Piece;

import java.util.List;

//Created by DaMasterHam on 29-09-2016.
//
public interface IChessMethods
{
    // initialization
    void setPlayers(Player p1, Player p2);
    void initializeBoard(Board board);

    //void renderBoard(Board board);
    //void renderOutOfPlay(Board board);

    void displayTurn(Player player);
    void requestPlayerMove(Player player);


    // Errors
    void moveObstructed();
    void ownPiecePresent(Piece piece);
    void invalidPieceMove(Piece piece);
    void notOwnedPiece(Piece piece);
    void noPieceToSelect(Player player);

    // Success
    void attackSuccess(Piece defender);
    void pieceMoved(Piece piece);

    void endTurn();


}
