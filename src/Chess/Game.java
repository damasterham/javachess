package Chess;

import Chess.Pieces.Piece;
import Chess.Pieces.Queen;

import java.util.Random;
import java.util.Scanner;

//Created by DaMasterHam on 08-09-2016.
//
public class Game
{

    private static void setPlayers(Scanner read, Player p1, Player p2)
    {
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

    private static void changeTurns(Player p1, Player p2)
    {
        p1.setTurn(!p1.isTurn());
        p2.setTurn(!p2.isTurn());
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

    private static void playerTurn(Player player, Board board, Scanner read)
    {
        while (player.isTurn())
        {
            Point from = null;
            Point to = null;
            Piece piece = null;

            if (read.hasNext())
            {
                System.out.print("Take piece: ");
                String input = read.next();

                from = new Point(input);
                piece = board.getPiece(from);
            }
            if (piece != null && player.owns(piece) && read.hasNext())
            {
                System.out.print("Move: ");
                String input = read.next();

                to = new Point(input);
            }
            if (board.hasPiece(to))
            {
                Piece defender = board.getPiece(to);

                if (player.owns(defender))
                {
                    System.out.print("Your " + defender.getName() + " is in that spot");
                }
                else
                {
                    board.remove(defender);
                    player.setTurn(false);
                }
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

        board.getLetterMap();

        board.setPiece(new Queen("White", true), new Point("a1"));
        board.setPiece(new Queen("Black", false), new Point("h8"));

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
