package Chess;

import Chess.Pieces.Bishop;
import Chess.Pieces.Piece;
import Chess.Pieces.Tower;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.BooleanSupplier;

//Created by DaMasterHam on 08-09-2016.
//
public class Chess
{

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

    private static void printTurn(Player player)
    {
        System.out.println();
        System.out.println("It's " + player.getName() + "'s turn");
    }

    private static void setPlayers(Scanner read, Player p1, Player p2)
    {
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

    private static void command(Scanner read, Player player, Board board)
    {
        String[] commands;

        player.newMove();

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
                            case "help": printHelp(); break;
                            case "board": board.print(); break;
                            case "turn": printTurn(player);
                        }
                    }
                }
            }
        }
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


    // Checkmate check should be included
    private static void playerTurn(Player player, Board board, Scanner read)
    {
        // Shows the current board and tells whos turn it is
        System.out.println(player.getName() + "'s turn");

        // Loops whilst it is the players turn
        while (player.isTurn())
        {
            //Point from = null; // Replaced by player.move
            //Point to = null; // Replaced by player.move
            Piece piece = null;

// This can be shortcutted to one read command
            // as in: a1 a2
            // get move

            System.out.print("Your move (from to):");
            command(read,player, board);

            // Select a s
            //from = desiredPick(read);

            // Get piece from point
            piece = board.getPiece(player.moveFrom());

            // Check if there is piece
            if (piece != null)
            {
                // and if that piece is owned by the player
                if (player.owns(piece))
                {
                    // Get for desired move
                    //to = desiredMove(player, read, to, piece); //Replaced by command

                    // The desired moved is within the movement restrictions of the piece
                    if (piece.isValidMove(player.moveTo())) {
                        // Checks each point between the piece and desired position
                        if (board.isObstructed(piece, player.moveTo())) {
                            // If obstructed, Cannot move
                            System.out.println("There are pieces in the way,"
                                    + "you cannot move to this position");
                        }
                        else
                        {
                            // If there is a piece at the desired position
                            if (board.hasPiece(player.moveTo())) {
                                Piece defender = board.getPiece(player.moveTo());

                                // And that piece is owned by the player
                                if (player.owns(defender)) {

                                    System.out.println("Your " + defender.getName() + " is in that spot");
                                }
                                // If owned by the Opponent
                                else {
                                    // Remove the piece from play
                                    board.remove(defender);
                                    System.out.println("You took " + defender.getName()
                                            + " at " + defender.getPosition().toChess());
                                }
                            }

                            // Lastly the player piece is moved to the desired positon
                            board.movePiece(piece, player.moveTo());
                            board.print();

                            // Tells you which piece you took at which position
                            System.out.println("You took your " + piece.getName() + " at " + piece.getPosition().toChess());

                            System.out.println("You moved you " + piece.getName() + " to " + piece.getPosition().toChess());
                            // And it is no longer the players turn
                            player.endTurn();

                            System.out.println("Out of play: " + board.getOutOfPlay());

                            System.out.println("End turn...");


                            promptEnterKey();
                            System.out.println("-----------------------------------------");

                        }
                    }
                    else
                    {
                        System.out.println("You cannot move your " + piece.getName() + " there");
                    }
                }
                else
                {
                    // if player is added as a reference in Piece
                    // you can reference the other player via the Piece
                    // it could also be used as comparison instead of comparing colors
                    System.out.println("That is not your " + piece.getName());
                }
            }
            else
            {
                System.out.println("There is no piece at " + player.moveFrom().toChess()); //+ player.moveFrom().toChess());
            }
        }
    }


    public static void main(String[] args)
    {
        Board board  = new Board();
        Scanner read = new Scanner(System.in);
        Player p1 = new Player();
        Player p2 = new Player();
        boolean turn = true;

        setPlayers(read, p1, p2);
        setColors(p1,p2);

        board.setPiece(new Tower("White"), new Point("a1"));
        board.setPiece(new Tower("Black"), new Point("h8"));
        board.setPiece(new Bishop("White"), new Point("h2"));

        board.printIndex();
        board.print();
        System.out.println("-----------------------------------------\n");

        int i = 0;
        while (i < 5)
        {
            p1.startTurn();
            playerTurn(p1,board,read);
            p2.startTurn();
            playerTurn(p2,board,read);

            i++;
        }





    }
}
