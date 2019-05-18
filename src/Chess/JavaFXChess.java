package Chess;//

import Chess.Scene.Board;
import Chess.Scene.Player;
import javafx.application.Application;
import javafx.print.PageLayout;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Created by DaMasterHam on 27-10-2016.
//
public class JavaFXChess extends Application implements IChessEvents
{
    private static final int X = 1280;
    private static final int Y = 720;

    private Board board;
    private Player p1;
    private Player p2;
    private Player currentPlayer;

    private Stage window;
    private Scene startup;
    private Pane startupLayout;
    private Scene playerNames;
    private Pane playerNamesLayout;
    private Scene theGame;
    private Pane theGameLayout;

    public static void launchApp()
    {
        launch();
    }

    private Pane startLayout(int x, int y)
    {
        Pane layout = new StackPane();
        Button start = new Button("Player Vs Player");
        start.setOnAction(e -> changeScene(window, playerNames));
        start.setLayoutX(x);
        start.setLayoutY(y);
        layout.getChildren().add(start);
        return layout;
    }

    private Pane playerNamesLayout(int x, int y)
    {
        Pane layout = new VBox();
        Button start = new Button("Start Game");
        start.setOnAction(e -> changeScene(window, theGame));
        start.setLayoutX(x);
        start.setLayoutY(y);

        TextField p1 = new TextField();
        p1.setPromptText("Player 1");

        TextField p2 = new TextField();
        p2.setPromptText("Player 2");

        layout.getChildren().addAll(p1, p2, start);
        return layout;
    }

    private Pane theGameLayout()
    {
        return new StackPane();
    }

    public void start(Stage window) throws Exception
    {
        this.window = window;
        // Layout
        startupLayout = startLayout(50, 100);
        startup = new Scene(startupLayout,X, Y);

        playerNamesLayout = playerNamesLayout(100, 50);
        playerNames = new Scene(playerNamesLayout,X,Y);

        theGameLayout = theGameLayout();
        theGame = new Scene(theGameLayout, X, Y);

        // Window
        window.setTitle("Chess");
        window.setScene(startup);
        window.show();
    }

    public void changeScene(Stage stage, Scene scene)
    {
        stage.setScene(scene);
    }




    public void injectGameElements(Board board, Player p1, Player p2)
    {
        this.board = board;
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setPlayers()
    {
        // Fxset players
    }

    @Override
    public void initializeBoard() {

    }

    @Override
    public void displayTurn() {

    }

    @Override
    public void requestPlayerMove() {

    }

    @Override
    public void moveObstructed() {

    }

    @Override
    public void attackingOwnPiece() {

    }

    @Override
    public void invalidPlayerMove() {

    }

    @Override
    public void invalidPieceMove() {

    }

    @Override
    public void notOwnedPiece() {

    }

    @Override
    public void noPieceToSelect() {

    }

    @Override
    public void attackSuccess() {

    }

    @Override
    public void pieceMoved() {

    }

    @Override
    public void startTurn() {

    }

    @Override
    public void turnLoopStart() {

    }

    @Override
    public void endTurn() {

    }

    @Override
    public void gameOver() {

    }
}
