package Chess;//

import java.util.Scanner;

//Created by DaMasterHam on 29-09-2016.
//
public class ConsoleGame
{

    public static void main(String[] args)
    {
        Scanner read;
        IChessMethods chessMethods;
        Chess game;


        read = new Scanner(System.in);
        chessMethods = new ConsoleChessMethods(read);
        game = new Chess(chessMethods);

        game.start();
    }
}
