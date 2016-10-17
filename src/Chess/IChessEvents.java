package Chess;//

import Chess.Scene.Board;
import Chess.Scene.Player;

//Created by DaMasterHam on 29-09-2016.
//
public interface IChessEvents
{
    // initialization
    void injectGameElements(Board board, Player p1, Player p2);
    void setPlayers();
    void initializeBoard();

    //void renderBoard(Board board);
    //void renderOutOfPlay(Board board);

    void displayTurn();

    // Input events
    void requestPlayerMove();

    // Errors events
    void moveObstructed();
    void attackingOwnPiece();
    void invalidPlayerMove();
    void invalidPieceMove();
    void notOwnedPiece();
    void noPieceToSelect();

    // Success events
    void attackSuccess();
    void pieceMoved();

    // Other
    void startTurn();
    void turnLoopStart();
    void endTurn();
}
