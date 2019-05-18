package Chess;//

import java.util.Scanner;

//Created by DaMasterHam on 29-09-2016.
//
public class ConsoleGame
{



    public static void main(String[] args)
    {
        Scanner read;
        IChessEvents chessMethods;
        Game game;

        read = new Scanner(System.in);
        chessMethods = new ConsoleChessEvents(read);
        game = new Game(chessMethods);

        game.start();
    }
}
