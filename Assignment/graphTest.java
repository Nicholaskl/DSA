import java.util.*;
import java.io.*;

public class graphTest
{
    public static void main(String[] args)
    {
        DSAGraph gr = new DSAGraph();
        String fileName = "";
        Scanner sc = new Scanner(System.in);
        DSALinkedList ll;

        readFile(gr, "prac6_2.al");

        gr.displayAsList();
        gr.displayAsMatrix();
        gr.depthFirstSearch();
        gr.breadthFirstSearch();
    }

    private static void readFile(DSAGraph gr, String inFilename)
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
                processLine(gr, line);
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

    private static void processLine(DSAGraph gr, String csvRow) throws IllegalStateException
    {
        String label1, label2;

        String[] tokens = csvRow.split(" ");
        try
        {
            label1 = tokens[0];
            label2 = tokens[1];
            if(!gr.hasVertex(label1))
            {
                gr.addVertex(label1, null);
            }
            if(!gr.hasVertex(label2))
            {
                gr.addVertex(label2, null);
            }
            gr.addEdge(label1, label2);
        }
        catch(Exception e)
        {
            throw new IllegalStateException("CSV row had invalid format");
        }
    }
}
