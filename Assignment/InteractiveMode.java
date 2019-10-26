import java.util.*;
import java.io.*;

public class InteractiveMode
{
    private FollowNetwork network;

    public InteractiveMode()
    {
        network = new FollowNetwork();
    }

    public void menu()
    {
        int choice = -1;
        double temp = -1.0;
        double followProb = 0.0;
        double likeProb = 0.0;
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        do
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
            System.out.println("------------------------------");
            choice = sc.nextInt();

            switch (choice)
            {
                case 0 : exit=true; break;
                case 1 : network = loadNetwork(); break;
                case 2 :
                    do
                    {
                        System.out.println("Please enter probability for a like:");
                        temp = sc.nextDouble();
                    } while((temp < 0.0) && (temp > 1.0));

                    likeProb = temp;
                    temp = -1.0;

                    do
                    {
                        System.out.println("Please enter probability for a follow:");
                        temp = sc.nextDouble();
                    } while((temp < 0.0) && (temp > 1.0));

                    followProb = temp;
                break;
                case 3 : nodeOperations(); break;
                case 4 : followAdd(); break;
                case 6 : network.displayNetworkList(); break;
                case 7 :
                    System.out.println("Probability of a like: " + likeProb);
                    System.out.println("Probability of a follow: " + followProb);
                break;
                default : System.out.println("Invalid menu option");
            }
        } while (exit != true);
    }

    public void nodeOperations()
    {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        String name = "";
        boolean close = false;

        System.out.println("Please enter a name");
        name = sc.next();

        do
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

            switch (choice)
            {
                case 0 : close=true; break;
                case 1 : network.hasUser(name); break;
                case 2 : network.addUser(name); break;
                case 3 : network.removeUser(name); break;
                default : System.out.println("Invalid menu option");
            }

        } while(close != true);
    }

    public void followAdd()
    {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        boolean close = false;
        String follower = "";
        String following = "";

        System.out.println("Please enter the follower");
        follower = sc.next();
        System.out.println("Please enter who they are following or want to follow");
        following = sc.next();

        do
        {
            System.out.println();
            System.out.println("Please choose an option");
            System.out.println("------------------------------");
            System.out.println("0. Exit");
            System.out.println("1. Add a Follow");
            System.out.println("2. Remove a Follow");
            System.out.println("------------------------------");
            choice = sc.nextInt();

            switch (choice)
            {
                case 0 : close=true; break;
                case 1 : network.addFollow(following, follower); break;
                case 2 : network.removeFollow(following, follower); break;
                default : System.out.println("Invalid menu option");
            }

        } while(close != true);
    }

    public FollowNetwork loadNetwork()
    {
        Scanner sc = new Scanner(System.in);
        String fileName;

        System.out.println("Please enter a file name to load");
        fileName = sc.next();

        FollowNetwork network = new FollowNetwork();
        readFile(network, fileName);

        return network;
    }

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

            lineNum = 0;
            line = bufRdr.readLine();
            while (line != null && !(line.equals("")))
            {
                processLine(network, line);
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

    private static void processLine(FollowNetwork network, String csvRow) throws IllegalStateException
    {
        String label1, label2;

        String[] tokens = csvRow.split(":");
        try
        {
            label1 = tokens[0];
            if(!network.hasUser(label1))
            {
                network.addUser(label1);
            }

            if(tokens.length == 2)
            {
                label2 = tokens[1];
                if(!network.hasUser(label2))
                {
                    network.addUser(label2);
                }
                network.addFollow(label1, label2);
            }
        }
        catch(Exception e)
        {
            throw new IllegalStateException("CSV row had invalid format");
        }
    }
}
