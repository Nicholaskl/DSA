/*
 * File: GraphTest.java
 * File Created: Monday, 30th September 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Tuesday, 29th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Test file for the Graph data structure
 * Reference: Modified version of the test harness made by Nicholas Klvana-Hooper (me)
 *            for Prac_05 from 18/09/19. (Accessed on the 30th of September, 2019)
 */
import java.util.*;
import java.io.*;

public class GraphTestHarness
{
    public static void main(String[] args)
    {
        DSAGraph gr = new DSAGraph();
        String fileName = "";
        Scanner sc = new Scanner(System.in);
        DSALinkedList ll;

        //Reading test data from prac6_2.al
        readFile(gr, "prac6_2.al");

        System.out.println("------------------------------");
        System.out.println("Printing graph as adjacency list");
        gr.displayAsList();
        System.out.println("------------------------------");
        System.out.println("Printing graph as adjacency matrix");
        gr.displayAsMatrix();
        System.out.println("------------------------------");
        System.out.println("Do a depth first search traversal");
        gr.depthFirstSearch();
        System.out.println("------------------------------");
        System.out.println("Do a breadth first search traversal");
        gr.breadthFirstSearch();
        System.out.println("------------------------------");
        System.out.println("Delete A from the graph");
        gr.deleteNode("A");
        System.out.println("------------------------------");
        System.out.println("A should now be missing");
        gr.displayAsList();
    }

    /*
    * SUBMODULE: readFile
    * IMPORT: gr(DSAGraph), inFilename(String)
    * EXPORT: void
    * ASSERTION: Goes through the file line by line, reading its information and
    *            passing it to get processed into the network.
    * REFERENCE: Code based on Lecture01 - Sorting lecture slides.
    *            Accessed on the Sunday 27th of October
    */
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

            lineNum = 0; //start from line one
            line = bufRdr.readLine();
            while (line != null && !(line.equals(""))) //while the line is not empty read it
            {
                processLine(gr, line); //processes the line into the network
                lineNum++; //goes to next line and reads it
                line = bufRdr.readLine();
            }
            fileStrm.close(); //closes the reading process
        }
        catch(IOException e)
        { // Catches file reading/writing errors
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

    /*
    * SUBMODULE: processLine
    * IMPORT: gr(DSAGraph), csvRow(String)
    * EXPORT: void
    * ASSERTION: Takes in a line from the file and parses it into the network
    * REFERENCE: Code based on Lecture01 - Sorting lecture slides.
    *            Accessed on the Sunday 27th of October
    */
    private static void processLine(DSAGraph gr, String csvRow) throws IllegalStateException
    {
        String label1, label2;

        String[] tokens = csvRow.split(" ");
        try
        {
            label1 = tokens[0];
            label2 = tokens[1];
            if(!gr.hasVertex(label1)) //if first vertex doesn't exist - add it
            {
                System.out.println("Adding Vertex: " + label1);
                gr.addVertex(label1, null);
            }
            if(!gr.hasVertex(label2)) //if second vertex doesn't exist - add it
            {
                System.out.println("Adding Vertex: " + label2);
                gr.addVertex(label2, null);
            }
            System.out.println("Adding Edge from " + label2 + "to " + label1);
            gr.addEdge(label1, label2); //add edge between the two vertices
        }
        catch(Exception e)
        {
            throw new IllegalStateException("CSV row had invalid format");
        }
    }
}
