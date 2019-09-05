import java.util.*;
import java.io.*;
public class SortFile
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String fileName;
        int sortType;
        int[] array2 = new int[7005];

        System.out.println("Please enter file name");
        fileName = sc.nextLine();

        array2 = readFile(fileName);

        System.out.println("What sorting do you want?");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        sortType = sc.nextInt();

        switch (sortType)
        {
            case 1:
                array2 = Sorts.bubbleSort(array2);
            break;
            case 2:
                array2 = Sorts.selectionSort(array2);
            break;
            case 3:
                array2 = Sorts.insertionSort(array2);
            break;
            default:
                System.out.println("Invalid number, exiting...");
        }

        sc.nextLine();

        System.out.println("Please enter file name");
        fileName = sc.nextLine();
        writeOneRow(fileName, array2);
        System.out.println("Done");
    }

    private static int[] readFile(String inFilename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum, count;
        String line;
        int[] array2 = new int[7005];

        try
        {
            fileStrm = new FileInputStream(inFilename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            lineNum = 0;
            line = bufRdr.readLine();
            count = 0;
            while (line != null)
            {
                array2[count] = processLine(line);
                lineNum++;
                count++;
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
        return array2;
    }

    private static int processLine(String csvRow) throws IllegalStateException
    {

        String name;
        int id;


        String[] tokens = csvRow.split(",");
        try
        {
            id = Integer.parseInt(tokens[0]);
            name = tokens[1];
            return id;
        }
        catch(Exception e)
        {
            throw new IllegalStateException("CSV row had invalid format");
        }
    }

    private static void writeOneRow(String filename, int[] array2)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        try
        {
            fileStrm = new FileOutputStream(filename);
            pw = new PrintWriter(fileStrm);

            for(int ii=0;ii<=(array2.length-1);ii++)
            {
                if(array2[ii] != 0)
                {
                    pw.println(array2[ii]);
                }
            }
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
