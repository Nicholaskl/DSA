import java.util.*;
import java.io.*;

public class test
{
    public static void main(String[] args)
    {
        DSAHeap hp = new DSAHeap(3);

        int priority1 = 5;
        int priority2 = 2;
        int priority3 = 4;

        String value1 = "1";
        String value2 = "2";
        String value3 = "3";

        hp.add(priority1, value1);
        hp.add(priority2, value2);
        hp.add(priority3, value3);
        hp.export();

        //hp.print(hp.remove());
        //hp.print(hp.remove());
        //hp.print(hp.remove());
    }
}

