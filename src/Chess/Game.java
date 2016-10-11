package Chess;

import Chess.Attributes.Point;
import Chess.Scene.Board;
import Chess.Scene.Pieces.Piece;
import Chess.Scene.Player;

import java.io.IOException;
import java.util.Scanner;

//Created by DaMasterHam on 08-09-2016.
//
public class Game
{

    // Perhaps singleton?

    private IChessEvents chessEvents;
    private Board board  = new Board();
    private Player p1 = new Player();
    private Player p2 = new Player();

    public Game(IChessEvents chessMethods)
    {
        this.chessEvents = chessMethods;
        board = new Board();
        p1 = new Player();
        p2 = new Player();
    }

    // Deprecated
    private static void promptEnterKey(){
        try
        {
            int read = System.in.read(new byte[2]);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Deprecated but not moved
    private static void printHelp()
    {
        System.out.println();
        System.out.println("The way you play is you select a piece and where you want to move it in one go");
        System.out.println("So \"from to eg\": \"a1 d5\"");
        System.out.println("In this example a1 is the position of one of your pieces");
        System.out.println("and d5 is where you want to move it");
        System.out.println();
        System.out.println("Full list of commmands:");
        System.out.println("\"board\" to show the board");
        System.out.println("\"turn\" to remind you whom's turn it is");
    }

    // Deprecated
    private static void printTurn(Player player)
    {
        System.out.println();
        System.out.println("It's " + player.getName() + "'s turn");
    }




    private static void setColors(Player p1, Player p2)
    {

        // Randomize later
//        String[] colors = new String[2];
//        colors[0] = "White";
//        colors[1] = "Black";
//
//        Random rand = new Random();

        p1.setColor("White");
        p2.setColor("Black");

        System.out.println(p1.getName() + " is " + p1.getColor() + " & " + p2.getName() + " is " + p2.getColor());
    }



    //Deprecated
    private static Point desiredMove(Player player, Scanner read, Point to, Piece piece)
    {
        System.out.print("Move to: ");
        if (read.hasNext())
        {
            String input = read.next();

            to = new Point(input);
            System.out.println(to.toChess() + " = " + to.toString());
        }
        return to;
    }

    //Deprecated
    private static Point desiredPick(Scanner read)
    {
        Point from = null;

        System.out.print("Take piece from: ");
        if (read.hasNext())
        {
            String input = read.next();

            from = new Point(input);
            System.out.println(from.toChess() + " = " + from.toString());
        }
        return from;
    }

    private static void initilizeBoard()
    {
        // set all pieces into board
    }

    private void playerTurn(Player player)
    {
        chessEvents.startTurn();

        while(player.isTurn())
        {
            chessEvents.turnLoopStart();

            chessEvents.requestPlayerMove();

            if (player.getMove() == null)
                break;

            player.getPiece(board);

            if (player.get)

        }
    }


    // Checkmate check should be included
    private static void playerTurn(Player player, Board board, IChessEvents chessMethods)
    {
        chessMethods.startTurn();

        // Loops whilst it is the players turn
        while (player.isTurn())
        {
            chessMethods.turnLoopStart();
            //Point from = null; // Replaced by player.move
            //Point to = null; // Replaced by player.move

            // Moved to player
            //Piece piece = null;

            //command(read,player, board);
            chessMethods.requestPlayerMove();

            // Get piece from point
            player.getPiece(board);
            //piece = board.getPiece(player.moveFrom());
            //chessMethods.setCurrentPiece(piece);

            // Check if there is piece
            if (player.getCurrentPiece() != null)
            {
                // and if that piece is owned by the player
                if (player.ownsCurrentPiece())
                {
                    // The desired moved is within the movement restrictions of the piece
                    if (player.getCurrentPiece().isValidMove(player.moveTo()))
                    {
                        // Checks each point between the piece and desired position
                        if (board.isObstructed(player.getCurrentPiece(), player.moveTo()))
                        {
                            // If obstructed, Cannot move
                            chessMethods.moveObstructed();
                        }
                        else
                        {
                            // If there is a piece at the desired position
                            if (board.hasPiece(player.moveTo())) {
                                Piece defender = board.getPiece(player.moveTo());

                                // And that piece is owned by the player
                                if (player.owns(defender)) {

                                    chessMethods.attackingOwnPiece();
                                }
                                // If owned by the Opponent
                                else
                                {
                                    // Remove the piece from play and moved
                                    board.remove(defender);
                                    board.movePiece(player.getCurrentPiece(), player.moveTo());
                                    chessMethods.attackSuccess();
                                }
                            }
                            else
                            {
                                // if its an empty space move
                                board.movePiece(player.getCurrentPiece(), player.moveTo());
                                chessMethods.pieceMoved();
                            }

                            // And it is no longer the players turn
                            player.endTurn();
                            chessMethods.endTurn();
                        }
                    }
                    else
                    {
                        // Invalid move by piece rules
                        chessMethods.invalidPieceMove();
                    }
                }
                else
                {
                    // if player is added as a reference in Piece
                    // you can reference the other player via the Piece
                    // it could also be used as comparison instead of comparing colors
                    chessMethods.notOwnedPiece();
                }
            }
            else
            {
                chessMethods.noPieceToSelect();
            }
        }
    }


    public void start()
    {
        chessEvents.injectGameElements(board, p1, p2);

        chessEvents.setPlayers();

//        initilizeBoard(); // should be a method in board
        chessEvents.initializeBoard(); // could possibly run renderboard

        // this is psuedo init
//        board.setPiece(new Tower("White"), new Point("a1"));
//        board.setPiece(new Tower("Black"), new Point("h8"));
//        board.setPiece(new Bishop("White"), new Point("h2"));

//        chessEvents.renderBoard(board);

        //System.out.println("-----------------------------------------\n");
//Some chessmethod? maybe initializeBoard();

        // Checks if player 1 is white
        /*if (p1.getColor().equals("white"))
        {
            // if he is it will display and start his turn
            p1.startTurn();
        }*/
//        else
//        {   //otherwise it is the other player that is white
//            // and his turn is displayed
//            // though we skip starting his turn as it will be when p1 turn is passed over
//
//        }

        int i = 0;
        while (i < 5) // the true test if is both kings are in play and not checkmate
        {
            playerTurn(p1); // is initially passed if p1 is not white
            p2.startTurn();
            playerTurn(p2);
            p1.startTurn();

            i++;
        }





    }
}
