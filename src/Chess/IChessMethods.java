package Chess;//

//Created by DaMasterHam on 29-09-2016.
//
public interface IChessMethods
{
    void renderBoard();

    void displayTurn();
    void requestPlayerMove();


    // Errors
    void moveObstructed();
    void ownPiecePresent();
    void invalidPieceMove();
    void notOwnedPiece();
    void noPieceToSelect();

    // Success
    void attackSuccess();

    void endTurn();


}
