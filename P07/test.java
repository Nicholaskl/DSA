import java.util.*;
import java.io.*;

public class test
{
    public static void main(String[] args)
    {
        DSAHeap hp = new DSAHeap(12);

        int[] priority = {82, 70, 51, 63, 55, 37, 10, 42, 27, 30, 34, 95};


        String value1 = "1";
        String value2 = "2";
        String value3 = "3";

        for(int ii=0; ii < priority.length; ii++)
        {
            hp.add(priority[ii], Integer.toString(ii+1));
        }

        hp.export();
        System.out.println("-----ADDED");

        hp.print(hp.remove());
        System.out.println("-----should be 1");
        hp.print(hp.remove());
        System.out.println("-----should be 3");
        hp.print(hp.remove());
        System.out.println("-----should be 2");
        hp.export();
    }
}
