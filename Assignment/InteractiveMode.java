/*
 * File: SimulationMode.java
 * File Created: Sunday, 27th October 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Tuesday, 29th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Contains interactions for the interactive mode of the simulation
 */
import java.util.*;
import java.io.*;

public class InteractiveMode
{
    private FollowNetwork network;

    /*
    * SUBMODULE: InteractiveMode
    * IMPORT:
    * EXPORT:
    * ASSERTION: default constructor for interactive mode class
    */
    public InteractiveMode()
    {
        network = new FollowNetwork();
    }

    /*
    * SUBMODULE: menu
    * IMPORT:
    * EXPORT: void
    * ASSERTION: Has all main steps for the menu system for interactive mode
    */
    public void menu()
    {
        int choice = -1;
        double temp = -1.0;
        double followProb = 0.5;
        double likeProb = 0.9999;
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        do //keep asking what to do unless user exits
        {
            System.out.println();
            System.out.println("Please choose an option");
            System.out.println("------------------------------");
            System.out.println("0. Exit");
            System.out.println("1. Load Network");
            System.out.println("2. Set Probabilites");
            System.out.println("3. Find, Insert, Delete User");
            System.out.println("4. Remove/Add a Follow");
            System.out.println("5. New Post");
            System.out.println("6. Display Network");
            System.out.println("7. Display Statistics");
            System.out.println("8. Update(Run a timestep)");
            System.out.println("9. Save Network");
            System.out.println("10. Show in Order");
            System.out.println("11. Prints posts");
            System.out.println("------------------------------");
            choice = sc.nextInt();

            switch (choice)
            {
                case 0 : exit=true; break; //user exits program
                case 1 : network = loadNetwork(); break; //load a network file
                case 2 :
                    while((temp < 0.0) || (temp > 1.0)) //keep asking until probability is correct format
                    {
                        System.out.println("Please enter probability for a like:");
                        temp = sc.nextDouble();
                        if((temp < 0.0) || (temp > 1.0))
                        {
                            System.out.println("Wrong bounds for probability");
                        }
                    }

                    likeProb = temp; //set the like probability
                    temp = -1.0; //set to invalid so loop will work

                    while((temp < 0.0) || (temp > 1.0))  //keep asking until probability is correct format
                    {
                        System.out.println("Please enter probability for a follow:");
                        temp = sc.nextDouble();
                        if((temp < 0.0) || (temp > 1.0))
                        {
                            System.out.println("Wrong bounds for probability");
                        }
                    }

                    followProb = temp; //set the follow Probability
                break;
                case 3 : nodeOperations(); break; //Node operations sub menu
                case 4 : followAdd(); break; //Add a follow sub menu
                case 5 : newPost(); break; //Add a post sub menu
                case 6 : network.displayNetworkList(); break; //Displays the network adjacency list
                case 7 : //print the probabilities
                    System.out.println("Probability of a like: " + likeProb);
                    System.out.println("Probability of a follow: " + followProb);
                break;
                case 8 : network.timeStep(likeProb, followProb); break; //Performs a timeStep
                case 9 : saveFile(); break; //saveFile sub menu
                case 10 : inOrder(); break; //ordered output sub menu
                case 11 : System.out.print(network.displayPosts()); break; //display the posts
                default : System.out.println("Invalid menu option"); //otherwise not a valid option
            }
        } while (exit != true);
    }

    /*
    * SUBMODULE: loadNetwork
    * IMPORT:
    * EXPORT: network(FollowNetwork)
    * ASSERTION: Loads a new network from a file
    */
    public FollowNetwork loadNetwork()
    {
        Scanner sc = new Scanner(System.in);
        String fileName;

        System.out.println("Please enter a file name to load");
        fileName = sc.next();

        FollowNetwork network = new FollowNetwork();
        readFile(network, fileName); //reads the file with filename into the network

        return network;
    }

    /*
    * SUBMODULE: nodeOperations
    * IMPORT:
    * EXPORT:
    * ASSERTION: Contains the node operations sub menu. find/add/remove
    */
    public void nodeOperations()
    {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        String name = "";
        boolean close = false;

        do //continues until user exits
        {
            System.out.println();
            System.out.println("Please choose an option");
            System.out.println("------------------------------");
            System.out.println("0. Exit");
            System.out.println("1. Find User");
            System.out.println("2. Insert User");
            System.out.println("3. Delete User");
            System.out.println("------------------------------");
            choice = sc.nextInt();

            if((choice == 1) || (choice == 2) || (choice == 3)) //ask for a name to add/remove/find
            {
                System.out.println();
                System.out.println("Please enter a name");
                name = sc.next();
            }

            switch (choice)
            {
                case 0 : close=true; break; //user exits
                case 1 : network.displayUser(name); break; //display user data
                case 2 : network.addUser(name); break; // add a user
                case 3 : network.removeUser(name); break; //remove a user
                default : System.out.println("Invalid menu option"); //wrong choice
            }

        } while(close != true);
    }

    /*
    * SUBMODULE: followAdd
    * IMPORT:
    * EXPORT:
    * ASSERTION: contains sub menu to add a follow
    */
    public void followAdd()
    {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        boolean close = false;
        String follower = "";
        String following = "";

        do //continues until user exits
        {
            System.out.println();
            System.out.println("Please choose an option");
            System.out.println("------------------------------");
            System.out.println("0. Exit");
            System.out.println("1. Add a Follow");
            System.out.println("2. Remove a Follow");
            System.out.println("------------------------------");
            choice = sc.nextInt();

            if((choice == 1) || (choice == 2)) //ask more information for add/remove if asked
            {
                System.out.println();
                System.out.println("Please enter the follower");
                follower = sc.next();
                System.out.println("Please enter who they are following or want to follow");
                following = sc.next();
            }

            switch (choice)
            {
                case 0 : close=true; break; //user exits
                case 1 : network.addFollow(following, follower); break; //add a follow
                case 2 : network.removeFollow(following, follower); break; //remove a fllow
                default : System.out.println("Invalid menu option"); //otherwise wrong choice
            }

        } while(close != true);
    }


    /*
    * SUBMODULE: newPost
    * IMPORT:
    * EXPORT:
    * ASSERTION: contains sub menu to add a post
    */
    public void newPost()
    {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        boolean close = false;
        String user = "";
        String post = "";

        do //continues until user exits
        {
            System.out.println("\nPlease choose an option");
            System.out.println("------------------------------");
            System.out.println("0. Exit");
            System.out.println("1. Make a post");
            System.out.println("------------------------------");
            choice = sc.nextInt();

            if(choice == 1) //print user directions
            {
                System.out.println();
                sc.nextLine();
                System.out.println("Please enter the User to make post");
                user = sc.nextLine();
                System.out.println("Please enter the post");
                post = sc.nextLine();
            }

            switch (choice)
            {
                case 0 : close=true; break; //user exits
                case 1 : network.addPost(post, user); break; // add a post
                default : System.out.println("Invalid menu option"); //invalid menu option
            }

        } while(close != true);
    }

    /*
    * SUBMODULE: inOrder
    * IMPORT:
    * EXPORT:
    * ASSERTION: contains in order sub menu
    */
    public void inOrder()
    {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        boolean close = false;

        do //continues until user exits
        {
            System.out.println();
            System.out.println("Please choose an option");
            System.out.println("------------------------------");
            System.out.println("0. Exit");
            System.out.println("1. Posts in order of popularity");
            System.out.println("2. People in order of popularity");
            System.out.println("------------------------------");
            choice = sc.nextInt();

            switch (choice)
            {
                case 0 : close=true; break; //user exits
                case 1 : network.orderByLike(); break; //print posts by number of likes
                case 2 : network.orderByFollower(); break; //prints users by number of followers
                default : System.out.println("Invalid menu option");
            }

        } while(close != true);
    }

    /*
    * SUBMODULE: saveFile
    * IMPORT:
    * EXPORT: void
    * ASSERTION: Contains the file saving sub menu
    */
    public void saveFile()
    {
        Scanner sc = new Scanner(System.in);
        String fileName;

        System.out.println("Please enter a file name to export to");
        fileName = sc.next();

        writeFile(fileName, network.outputFile()); //write the file network to file
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
        }
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
