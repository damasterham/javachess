package Chess;

import java.util.HashMap;
import java.util.Map;

//Created by DaMasterHam on 09-09-2016.
//
public class CharIntMap
{
    private static final int SIZE = 8;

    public static char getChar(int i)
    {
        if (i >= 0 && i < SIZE)
            return (char)(97 + i);
        return 0;
    }

    public static int getIndex(char ch)
    {
        if (ch <= 'h' && ch >='a')
            return ch - 'a';
        else if (ch <= 'H' && ch>='A')
            return ch - 'A';
        return -1;
    }

    public static int getSize()
    {
        return SIZE;
    }


//    private static Map<Character,Integer> letterMap = new HashMap<>();
//
//    public static void init()
//    {
//        letterMap.put('a',0);
//        letterMap.put('b',1);
//        letterMap.put('c',2);
//        letterMap.put('d',3);
//        letterMap.put('e',4);
//        letterMap.put('f',5);
//        letterMap.put('g',6);
//        letterMap.put('h',7);
//    }
//
//    public static int get(char letter)
//    {
//        return letterMap.get(letter);
//    }
//
//    public static Map<Character,Integer> getMap()
//    {
//        return letterMap;
//    } private static Map<Character,Integer> letterMap = new HashMap<>();
//
//    public static void init()
//    {
//        letterMap.put('a',0);
//        letterMap.put('b',1);
//        letterMap.put('c',2);
//        letterMap.put('d',3);
//        letterMap.put('e',4);
//        letterMap.put('f',5);
//        letterMap.put('g',6);
//        letterMap.put('h',7);
//    }
//
//    public static int get(char letter)
//    {
//        return letterMap.get(letter);
//    }
//
//    public static Map<Character,Integer> getMap()
//    {
//        return letterMap;
//    }
}
