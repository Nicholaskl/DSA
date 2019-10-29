/*
 * File: SimulationMode.java
 * File Created: Monday, 28th October 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Wednesday, 30th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Runs the simulation mode of the program that steps through at users request
 */

import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.*;

public class SimulationMode
{
    private FollowNetwork network;

     /*
     * SUBMODULE: SimulationMode
     * ASSERTION: Default Constructor for the social network graph.
     */
    public SimulationMode()
    {
        network = new FollowNetwork();
    }

    /*
    * SUBMODULE: menu
    * IMPORT: args(String[])
    * EXPORT: void
    * ASSERTION: Contains the main logic for the menu for the Simulation of program
    */
    public void menu(String[] args)
    {
        //Sets all of the data to what was inputted in command line
        String netFileName = args[1];
        String eventFileName = args[2];
        double likeProb = Double.valueOf(args[4]);
        double followProb = Double.valueOf(args[3]);

        int choice = -1;
        boolean exit = false;
        int time = 0;
        Scanner sc = new Scanner(System.in);


        readFile(network, netFileName); //reads the Network File
        readFile(network, eventFileName); //reads the Event File
        writeTimeStep(likeProb, followProb, time, eventFileName);
        do //Keeps simulation going until user asks to exit
        {
            System.out.println();
            System.out.println("Please choose an option");
            System.out.println("------------------------------");
            System.out.println("0. Exit");
            System.out.println("1. Do a timestep");
            System.out.println("------------------------------");
            choice = sc.nextInt();

            switch (choice)
            {
                case 0 : exit=true; break; // Exit simulation
                case 1 :
                {
                    time ++; // Time is increased after ever timestep
                    network.timeStep(likeProb, followProb); // timestep simulation
                    writeTimeStep(likeProb, followProb, time, eventFileName); // write to file
                }
                break;
                default : System.out.println("Invalid menu option");
            }
        } while (exit != true);
    }

    /*
    * SUBMODULE: writeTimeStep
    * IMPORT: likeProb(double), followProb(double), time(int), eventFileName(String)
    * EXPORT: void
    * ASSERTION: Writes the information about the social network to a unique file (every run)
    * REFERENCE: Time and date formatting based on https://www.w3schools.com/java/java_date.asp
    *            Accessed on the 28th of October 2019
    */
    public void writeTimeStep(double likeProb, double followProb, int time, String eventFileName)
    {
        String output = "";
        //Formatting for time and date
        LocalDateTime currTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH-mm-ss");
        // Filename formatted so is different every time its run
        String fileName = currTime.format(myFormatObj) + "-" + eventFileName + "time" + time;

        //Information printed per time run
        output += "Time: " + time + "\n";
        output += "Probability of like: " + likeProb + "\n";
        output += "Probability of follow: " + followProb + "\n";
        output += "\n" + network.outputFile(); // output network
        output += network.displayPosts(); // output posts

        writeFile(fileName, output); // write the string to file
    }

    /*
    * SUBMODULE: readFile
    * IMPORT: network(FollowNetwork), inFilename(String)
    * EXPORT: void
    * ASSERTION: Goes through the file line by line, reading its information and
    *            passing it to get processed into the network.
    * REFERENCE: Code based on Lecture01 - Sorting lecture slides.
    *            Accessed on the Sunday 27th of October
    */
    private static void readFile(FollowNetwork network, String inFilename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line;

        try
        {
            fileStrm = new FileInputStream(inFilename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            lineNum = 0; //start from line one
            line = bufRdr.readLine();
            while (line != null && !(line.equals(""))) //while the line is not empty read it
            {
                processLine(network, line); //processes the line into the network
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
    * IMPORT: network(FollowNetwork), csvRow(String)
    * EXPORT: void
    * ASSERTION: Takes in a line from the file and parses it into the network
    * REFERENCE: Code based on Lecture01 - Sorting lecture slides.
    *            Accessed on the Sunday 27th of October
    */
    private static void processLine(FollowNetwork network, String csvRow) throws IllegalStateException
    {
        String label1, label2;
        String[] tokens = csvRow.split(":");

        try
        {
            if(tokens[0].equals("F")) //If F second person will follow the first
            {
                label1 = tokens[1].trim(); //the person who will be followed
                label2 = tokens[2].trim(); //the follower
                network.addFollow(label1, label2);
            }
            else if(tokens[0].equals("A")) //If A then add the user to the network
            {
                label1 = tokens[1].trim();
                if(!network.hasUser(label1)) //If user isn't in network, adds them
                {
                    network.addUser(label1);
                }
            }
            else if(tokens[0].equals("P")) // If P then create a post with a user
            {                              // and also a post message.
                label1 = tokens[1].trim(); //User that posts
                label2 = tokens[2].trim(); //Post message
                network.addPost(label2, label1);
            }
            else // Otherwise its not an event file, and thus
            {    // regular syntax (ie with A,F P at beginning)

                label1 = tokens[0].trim();
                if(!network.hasUser(label1)) //Checks to see if the user is part
                {                            //of the Network, if not. they are added
                    network.addUser(label1);
                }

                if(tokens.length == 2) //If there are two arguments than its a follow
                {                      //statement with the second following the first
                    label2 = tokens[1].trim();
                    if(!network.hasUser(label2)) //checks if the second user is part
                    {                            //of the network. if not, they are added
                        network.addUser(label2);
                    }
                    network.addFollow(label1, label2); //follow added
                }
            }

        }
        catch(NumberFormatException e)
        {
            throw new IllegalArgumentException("Invalid range:", e);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        catch(NullPointerException e)
        {
            throw new IllegalArgumentException("Invalid range:", e);
        } //Catch file processing exceptions
    }

    /*
    * SUBMODULE: writeFile
    * IMPORT: filename(String), output(String)
    * EXPORT: void
    * ASSERTION: Writes the string to a file with the filename given
    * REFERENCE: Code based on Lecture01 - Sorting lecture slides.
    *            Accessed on the Sunday 27th of October
    */
    private static void writeFile(String filename, String output)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        try
        {
            fileStrm = new FileOutputStream(filename);
            pw = new PrintWriter(fileStrm);

            pw.println(output); //Prints the output text to the file

            pw.close();
        }
        catch (IOException e)
        { //Cathces file reading/writing exceptions
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
