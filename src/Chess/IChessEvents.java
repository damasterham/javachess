package Chess;//

import Chess.Pieces.Piece;

import java.util.List;

//Created by DaMasterHam on 29-09-2016.
//
public interface IChessEvents
{
    // initialization
    void setPlayers();
    void initializeBoard();

    //void renderBoard(Board board);
    //void renderOutOfPlay(Board board);

    void displayTurn();

    // Input events
    void requestPlayerMove();

    // Errors events
    void moveObstructed();
    void ownPiecePresent();
    void invalidPieceMove();
    void notOwnedPiece();
    void noPieceToSelect();

    // Success events
    void attackSuccess();
    void pieceMoved();

    // Other
    void startTurn();
    void endTurn();
}
