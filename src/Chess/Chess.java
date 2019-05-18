//Created by DaMasterHam on 27-10-2016.
//
package Chess;

import java.util.Scanner;

public class Chess
{

    private static IChessEvents console()
    {
        Scanner read;
        read = new Scanner(System.in);
        return new ConsoleChessEvents(read);
    }

    private static IChessEvents fx()
    {
        return new JavaFXChess();
    }

    public static void main(String[] args)
    {
        IChessEvents events = null;
        Game game;

        if (args.length == 1)
        {
            if (args[0] == "console")
            {
                events = console();
            }
        }
        else
        {
            events = fx();
            JavaFXChess.launchApp();
        }

        if (events != null)
        {
            game = new Game(events);
            game.start();
        }

    }
}
