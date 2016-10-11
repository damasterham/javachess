package Chess;//

import Chess.Attributes.Point;
import Chess.Scene.Board;
import Chess.Scene.Pieces.Piece;
import Chess.Scene.Player;

import java.util.Random;
import java.util.Scanner;

//Created by DaMasterHam on 29-09-2016.
//
public class ConsoleChessEvents implements IChessEvents
{
    private Scanner read;

    private Board board;
    private Player p1;
    private Player p2;
    private Player currentPlayer;
    //private Piece currentPiece;

    public ConsoleChessEvents(Scanner read)
    {
        this.read = read;
    }


    // Internal rendering
    private void renderBoard()
    {

        System.out.println("┉┉┉┉Black┉┉┉┉");
        // Prints top border
        System.out.print('╔');
        for (int i = 0; i < board.getSIZE()*2; i++)
        {
            if ((i % 2) == 0)
                System.out.print('═');
            else
                System.out.print('╦');

        }
        System.out.println('╗');

        // Prints Letteres
        System.out.print("║\u2003");
        for (int i = 0; i < CharIntMap.getSize(); i++)
        {
            System.out.print(String.format("║%s\u2009\u2009\u200A\u200A",(""+CharIntMap.getChar(i))).toUpperCase());
        }
        System.out.println('║');

        // Prints Numbers and spaces
        // Y
        for (int i = 0; i < board.getSIZE()*2; i++)
        {
            if ((i % 2) == 0)
            {
                System.out.print("║"+((i/2)+1));
            }
            else
            {
                System.out.print("╠═");
            }

            // X
            for (int j = 0; j < board.getSIZE(); j++)
            {
                if ((i % 2) == 0)
                {
                    Piece p = board.getPiece(new Point(j,(i/2)));
                    if (p != null)
                        System.out.print(String.format("║%s",p.getSymbol()));
                    else
                        System.out.print("║╳");
                }
                else
                {
                    System.out.print("╬═");
                }
            }
            System.out.println('║');
        }
        System.out.println("┉┉┉┉White┉┉┉┉");

        renderOutOfPlay();
    }

    private void renderOutOfPlay()
    {
        if (board.hasOutOfPlay())
            System.out.println("Out of play: " + board.getOutOfPlay());
    }

    private void currentPlayerSet()
    {
        if (p1.isTurn())
            currentPlayer = p1;
        else if(p2.isTurn())
            currentPlayer = p2;
    }

    // possibly also made as internal rendering
    public void displayTurn()
    {
        System.out.println(currentPlayer.getColor() + "'s turn ┉ " + currentPlayer.getName());
    }

    // Initialization
    public void injectGameElements(Board board, Player p1, Player p2)
    {
        this.board = board;
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setPlayers()
    {
        Random random = new Random();
        int turn;

//        p1.setName("Rick");
//        p2.setName("Morty");

        while (p1.getName() == null || p2.getName() == null)
        {
            System.out.print("Player 1: ");
            if (read.hasNext())
            {
                p1.setName(read.next());
            }
            System.out.println();
            System.out.print("Player 2: ");
            if (read.hasNext())
            {
                p2.setName(read.next());
            }
            System.out.println();
        }

        turn = random.nextInt(1);

        // Randomizez who starts
        if (turn == 0)
        {
            p1.setColor("White");
            p2.setColor("Black");
            currentPlayer = p1;
            currentPlayer.startTurn();
        }
        else
        {
            p2.setColor("White");
            p1.setColor("Black");
            currentPlayer = p2;
            currentPlayer.startTurn();
        }

        System.out.println(p1.getName() + " is " + p1.getColor());
        System.out.println(p2.getName() + " is " + p2.getColor());
        System.out.println();



    }

    public void initializeBoard()
    {
        // Set all currentPieces via board initalize
        board.initialize();

        this.board = board;

        // render board
        //renderBoard();
    }


    // Input event
    public void requestPlayerMove()
    {
        String[] commands;

        currentPlayer.newMove();

        System.out.print("Your move (from to):");

        while (currentPlayer.moveFrom() == null  && currentPlayer.moveTo() == null)
        {
            if (read.hasNextLine()) {
                commands = read.nextLine().split(" ");

                if (commands.length == 2) {
                    Point from = new Point(commands[0]);// can be validated?
                    Point to = new Point(commands[1]);// can be validated?

                    currentPlayer.moveFrom(from);
                    currentPlayer.moveTo(to);
                }
                else
                {
                    if (commands.length == 1)
                    {
                        String command = commands[0].toLowerCase();

                        switch (command)
                        {
//                            case "help": printHelp(); break;
//                            case "board": board.print(); break;
//                            case "turn": printTurn(currentPlayer);
                            default:
                                System.out.println("No command by that name"); break;
                        }
                    }
                }
            }
        }
    }


    // Fail events
    public void moveObstructed()
    {
        System.out.println("There are pieces in the way,"
                + "you cannot move to this position");
    }

    public void attackingOwnPiece()
    {
        System.out.println("Your " + currentPlayer.getCurrentPiece().getName() + " is in that spot");
    }

    public void invalidPieceMove()
    {
        System.out.println("You cannot move your " + currentPlayer.getCurrentPiece().getName() + " there");
    }

    public void notOwnedPiece()
    {
        System.out.println("That is not your " + currentPlayer.getCurrentPiece().getName());
    }

    public void noPieceToSelect()
    {
        System.out.println("There is no piece at " + currentPlayer.moveFrom().toChess());
    }


    // Success events
    public void attackSuccess()
    {
        Piece attacked = board.getLastRemoved();

        System.out.println("You took " + currentPlayer.getCurrentPiece().getName()
                + " at " + currentPlayer.moveFrom().toChess()
                + " and took the oppentents " + attacked.getName()
                + " at " + currentPlayer.moveTo()
        );

    }

    public void pieceMoved()
    {
        renderBoard();

        // Tells you which currentPiece you took at which position
        System.out.println("You took your " + currentPlayer.getCurrentPiece().getName() + " at " + currentPlayer.moveFrom().toChess());

        System.out.println("You moved you " + currentPlayer.getCurrentPiece().getName() + " to " + currentPlayer.moveTo().toChess());

    }


    // Other
    public void startTurn() // Could also just take player as parameter
    {
        renderBoard();
    }

    public void turnLoopStart()
    {
        currentPlayerSet();
        displayTurn();
    }

    public void endTurn()
    {
        System.out.println("End turn...");
        System.out.println("-----------------------------------------");
    }


}
