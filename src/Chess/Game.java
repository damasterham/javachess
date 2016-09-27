package Chess;

import Chess.Pieces.Piece;
import Chess.Pieces.Tower;

import java.util.Scanner;

//Created by DaMasterHam on 08-09-2016.
//
public class Game
{

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

        p1.setTurn(true);
        p2.setTurn(false);
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


    private static Point desiredMove(Player player, Scanner read, Point to, Piece piece)
    {
        System.out.print("Move to: ");
        if (read.hasNext())
        {
            String input = read.next();

            to = new Point(input);
        }
        return to;
    }


    private static Point desiredPick(Scanner read)
    {
        Point from = null;

        System.out.print("Take piece from: ");
        if (read.hasNext())
        {
            String input = read.next();

            from = new Point(input);
        }
        return from;
    }


    private static void playerTurn(Player player, Board board, Scanner read)
    {
        // Loops whilst it is the players turn
        while (player.isTurn())
        {
            Point from = null;
            Point to = null;
            Piece piece = null;

            // Select a point
            from = desiredPick(read);

            // Get piece from point
            piece = board.getPiece(from);

            // Check if there is piece
            if (piece != null)
            {
                // and if that piece is owned by the player
                if (player.owns(piece))
                {
                    // Get for desired move
                    to = desiredMove(player, read, to, piece);

                    // The desired moved is within the movement restrictions of the piece
                    if (piece.isValidMove(to)) {
                        // Checks each point between the piece and desired position
                        if (board.isObstructed(piece, to)) {
                            // If obstructed, Cannot move
                            System.out.println("There are pieces in the way,"
                                    + "you cannot move to this position");
                        } else {
                            // If there is a piece at the desired position
                            if (board.hasPiece(to)) {
                                Piece defender = board.getPiece(to);

                                // And that piece is owned by the player
                                if (player.owns(defender)) {

                                    System.out.print("Your " + defender.getName() + " is in that spot");
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
                            board.movePiece(piece, to);
                            // And it is no longer the players turn
                            player.setTurn(false);
                        }
                    }

                }
                else
                {
                    System.out.print("That is not your " + piece.getName());
                }
            }
            else
            {
                System.out.println("There is no piece at " + from.toChess());
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

        board.setPiece(new Tower("White", true), new Point("a1"));
        board.setPiece(new Tower("Black", false), new Point("h8"));

        int i = 0;
        while (i < 5)
        {
            p1.setTurn(true);
            System.out.println(p1.getName() + "'s turn");
            board.print();
            playerTurn(p1,board,read);
            p2.setTurn(true);
            System.out.println(p2.getName() + "'s turn");
            board.print();
            playerTurn(p2,board,read);

            i++;
        }





    }
}
