import java.util.*;
import java.io.*;

public class test
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int menu = 0;
        String output = "";

        System.out.println("1: Enter Custom Test Harness");
        System.out.println("2: Enter RandomNames File");
        menu = sc.nextInt();

        switch(menu)
        {
            case 1:
                testHarness();
                break;
            case 2:
                random();
                break;
            default:
                System.out.println("Error in choice given");
        }

    }

    public static void testHarness()
    {
        DSAHeap hp = new DSAHeap(10);

        int[] priority = {1, 2, 3, 4, 5, 10, 9, 8, 7, 6};

        for(int ii=0; ii < priority.length; ii++)
        {
            hp.add(priority[ii], Integer.toString(ii+1));
        }

        System.out.println("-----ADDED");
        hp.export();

        System.out.println("-----REMOVE");
        System.out.println("-----should be 6");
        hp.print(hp.remove());
        System.out.println("-----should be 7");
        hp.print(hp.remove());
        System.out.println("-----should be 8");
        hp.print(hp.remove());
        System.out.println("-----should be 9");
        hp.print(hp.remove());
        System.out.println("-----should be 10");
        hp.print(hp.remove());

        System.out.println("-----PRINT REMAINING"); //won't be in order cause not removed!
        hp.export();
    }

    public static void random()
    {
        DSAHeap hp = new DSAHeap(0);
        Random rd = new Random();
        Object[] importArr  = new Object[7000];
        int[] exportArr;

        for(int ii = 0; ii < importArr.length; ii++)
        {
            //puts random numbers in
            importArr[ii] = rd.nextInt();
        }

        exportArr = hp.heapSort(importArr);
        for (int ii=0;ii < exportArr.length ;ii++ )
        {
            System.out.println(exportArr[ii]);
        }

    }


}
