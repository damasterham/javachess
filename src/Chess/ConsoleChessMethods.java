package Chess;//

import Chess.Pieces.Piece;
import java.util.Random;
import java.util.Scanner;

//Created by DaMasterHam on 29-09-2016.
//
public class ConsoleChessMethods implements IChessMethods
{
    private Scanner read;
    private Board board;
/*    private Player p1;
    private Player p2;
    private Player currentPlayer;
    private Piece currentPiece;*/
    // Current player?


    public ConsoleChessMethods(Scanner read)
    {
        this.read = read;
    }

    public ConsoleChessMethods(Scanner read, Board board)
    {
        this.read = read;
        this.board = board;
    }

    // Initialization
    public void setPlayers(Player p1, Player p2)
    {
        Random random = new Random();
        int turn;

        p1.setName("Rick");
        p2.setName("Morty");

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
        }
        else
        {
            p2.setColor("White");
            p1.setColor("Black");
        }

        System.out.println(p1.getName() + " is " + p1.getColor());
        System.out.println(p2.getName() + " is " + p2.getColor());
        System.out.println();
    }

    public void initializeBoard(Board board)
    {
        // Set all pieces via board initalize
        board.initialize();

        this.board = board;

        // render board
        renderBoard(this.board);
    }

    // All messages could be extracted as fields and properties


    public void renderBoard(Board board)
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

        renderOutOfPlay(board);
    }

    public void renderOutOfPlay(Board board)
    {
        if (board.hasOutOfPlay())
            System.out.println("Out of play: " + board.getOutOfPlay());
    }

    public void displayTurn(Player player)
    {
        System.out.println(player.getColor() + "'s turn ┉ " + player.getName());
    }

    public void requestPlayerMove(Player player)
    {
        String[] commands;

        player.newMove();

        System.out.print("Your move (from to):");

        while (player.moveFrom() == null  && player.moveTo() == null)
        {
            if (read.hasNextLine()) {
                commands = read.nextLine().split(" ");

                if (commands.length == 2) {
                    Point from = new Point(commands[0]);// can be validated?
                    Point to = new Point(commands[1]);// can be validated?

                    player.moveFrom(from);
                    player.moveTo(to);
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
//                            case "turn": printTurn(player);
                            default: break;
                        }
                    }
                }
            }
        }
    }

    public void moveObstructed()
    {
        System.out.println("There are pieces in the way,"
                + "you cannot move to this position");
    }

    public void invalidPieceMove(Piece piece)
    {
        System.out.println("You cannot move your " + piece.getName() + " there");
    }

    public void notOwnedPiece(Piece piece)
    {
        System.out.println("That is not your " + piece.getName());
    }

    public void noPieceToSelect(Player player)
    {
        System.out.println("There is no piece at " + player.moveFrom().toChess());
    }

    public void ownPiecePresent(Piece piece)
    {
        System.out.println("Your " + piece.getName() + " is in that spot");
    }

    public void attackSuccess(Piece piece)
    {
        System.out.println("You took " + piece.getName()
                + " at " + piece.getPosition().toChess());

    }

    public void pieceMoved(Piece piece) // or just have player fields?
    {
        renderBoard(this.board);

        // Tells you which piece you took at which position
        System.out.println("You took your " + piece.getName() + " at ");

        System.out.println("You moved you " + piece.getName() + " to " + piece.getPosition().toChess());

    }

    public void endTurn()
    {
        System.out.println("End turn...");
        System.out.println("-----------------------------------------");
    }
}
