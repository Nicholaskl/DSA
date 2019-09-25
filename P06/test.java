import java.util.*;
import java.io.*;

public class test
{
    public static void main(String[] args)
    {
        DSAHashTable ht = new DSAHashTable(3);
        Scanner sc = new Scanner(System.in);
        int menu = 0;
        String output = "";

        System.out.println("1: Enter Custom Test Harness");
        System.out.println("2: Enter RandomNames File");
        menu = sc.nextInt();

        switch(menu)
        {
            case 1:
                testHarness(ht);
                break;
            case 2:
                readFile(ht, "RandomNames7000.csv");
                output = ht.output();
                writeOneRow("output.csv", output);
                break;
            default:
                System.out.println("Error in choice given");
        }

        //Change test data here


    }

    public static void testHarness(DSAHashTable ht)
    {
        DSALinkedList export = new DSALinkedList();
        Object curr;

        String firstVal = "15";
        String secondVal = "46";
        String thirdVal = "27";
        String fourthVal = "13";
        String fifthVal = "37";
        String sixthVal = "8";
        String seventhVal = "26";

        ht.put(firstVal, 2);
        System.out.println("-----Add Test-----");
        ht.put(firstVal, 1);
        curr = ht.get(firstVal);
        System.out.println("Expecting 1: " + curr);
        ht.put(secondVal, 2);
        curr = ht.get(secondVal);
        System.out.println("Expecting 2: " + curr);
        ht.put(thirdVal, 3);
        curr = ht.get(thirdVal);
        System.out.println("Expecting 3: " + curr);
        ht.put(fourthVal, 4);
        curr = ht.get(fourthVal);
        System.out.println("Expecting 4: " + curr);
        ht.put(fifthVal, 5);
        curr = ht.get(fifthVal);
        System.out.println("Expecting 5: " + curr);
        ht.put(sixthVal, 6);
        curr = ht.get(sixthVal);
        System.out.println("Expecting 6: " + curr);
        ht.put(seventhVal, 7);
        curr = ht.get(seventhVal);
        System.out.println("Expecting 7: " + curr);

        System.out.println("-----Remove Test-----");
        ht.remove(fifthVal);
        ht.remove(secondVal);
        ht.remove(sixthVal);
        ht.remove(firstVal);
        ht.remove(fourthVal);

        System.out.println("-----Export Test-----");
        export = ht.export();
        printList(export);
    }

    public static void printList(DSALinkedList export)
    {
        Iterator iter = export.iterator();
        while (iter.hasNext())
        {
            System.out.println((iter.next()).toString());
        }
    }

    private static void readFile(DSAHashTable ht, String inFilename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line;
        int[] array2 = new int[7005];

        try
        {
            fileStrm = new FileInputStream(inFilename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            lineNum = 0;
            line = bufRdr.readLine();
            while (line != null && !(line.equals("")))
            {
                processLine(ht, line);
                lineNum++;
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch(IOException ex2)
                {
                }
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
    }

    private static void processLine(DSAHashTable ht, String csvRow) throws IllegalStateException
    {
        String key;
        Object value;

        String[] tokens = csvRow.split(",");
        try
        {
            key = (String)tokens[0];
            value = tokens[1];
            ht.put(key, value);
        }
        catch(Exception e)
        {
            throw new IllegalStateException("CSV row had invalid format");
        }
    }

    private static void writeOneRow(String filename, String output)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        try
        {
            fileStrm = new FileOutputStream(filename);
            pw = new PrintWriter(fileStrm);

            pw.println(output);

            pw.close();
        }
        catch (IOException e)
        {
            if(fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch(IOException ex2)
                {
                }
            }
            System.out.println("Error in writing to file: " + e.getMessage());
        }
    }
}
