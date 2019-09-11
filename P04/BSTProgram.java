import java.util.*;
import java.io.*;

public class BSTProgram
{
    public static void main(String[] args)
    {
        DSABinarySearchTree bst = new DSABinarySearchTree();
        Scanner sc = new Scanner(System.in);
        boolean close = false;
        int typeChoice = 0;
        String fileName;

        //bst.insert("4", "4 key");
        //bst.insert("2", "2 key");
        //bst.insert("6", "6 key");
        //bst.insert("1", "1 key");
        //bst.insert("5", "5 key");
        //bst.insert("3", "3 key");
        //bst.insert("7", "7 key");


        //System.out.println("Testing find. Should print 3 key");
        //System.out.println(bst.find("3"));
        //System.out.println("-----Min-----");
        //System.out.println(bst.min());
        //System.out.println("-----Max-----");
        //System.out.println(bst.max());
        //System.out.println("-----height-----");
        //System.out.println(bst.height());
        //System.out.println("-----Balance-----");
        //System.out.println(bst.balance());


        while(close != true)
        {
            int menuChoice = 0;
            System.out.println("Please type in a menu choice");
            System.out.println("0. Exit Program");
            System.out.println("1. Read a CSV File");
            System.out.println("2. Read serialized file");
            System.out.println("3. Display the tree");
            System.out.println("4. Write CSV");
            System.out.println("5. Write a serialized File");
            System.out.println("6. Run Test");
            menuChoice = sc.nextInt();
            //sc.next();
            switch(menuChoice)
            {
                case 0:
                    System.out.println("\n" + "Bye.");
                    close = true;
                    break;
                case 1:
                    System.out.println("Please enter a filename");
                    fileName = sc.next();
                    readFile(bst, fileName);
                    break;
                case 2:
                    System.out.println("Please enter a filename");
                    fileName = sc.next();
                    bst = load(fileName);
                    break;
                case 3:
                    System.out.println("Please type in a display type");
                    System.out.println("1. Inorder");
                    System.out.println("2. Preorder");
                    System.out.println("3. Postorder");
                    typeChoice = sc.nextInt();

                    switch(typeChoice)
                    {
                        case 1:
                            System.out.print(bst.printInorder());
                            break;
                        case 2:
                            System.out.println(bst.printPreorder());
                            break;
                        case 3:
                            System.out.print(bst.printPostorder());
                            break;
                        default:
                            System.out.println("Wrong output");
                    }
                    break;
                case 4:
                    System.out.println("Please enter a filename");
                    fileName = sc.next();
                    String output = "";

                    System.out.println("Please type in a display type");
                    System.out.println("1. Inorder");
                    System.out.println("2. Preorder");
                    System.out.println("3. Postorder");
                    typeChoice = sc.nextInt();

                    switch(typeChoice)
                    {
                        case 1:
                            output = bst.printInorder();
                            break;
                        case 2:
                            output = bst.printPreorder();
                            break;
                        case 3:
                            output = bst.printPostorder();
                            break;
                        default:
                            System.out.println("Wrong output");
                    }
                    writeOneRow(fileName, output);
                    break;
                case 5:
                    System.out.println("Please enter a filename");
                    fileName = sc.next();
                    save(bst, fileName);
                    break;
                case 6:
                    System.out.println("Testing find. Should print 3 key");
                    System.out.println(bst.find("3"));
                    System.out.println("-----Min-----");
                    System.out.println(bst.min());
                    System.out.println("-----Max-----");
                    System.out.println(bst.max());
                    System.out.println("-----height-----");
                    System.out.println(bst.height());
                    System.out.println("-----Balance-----");
                    System.out.println(bst.balance());

                    break;
                default:
                    System.out.println("Wrong number. Please choose Another");
            }
        }
    }

    private static void save(DSABinarySearchTree objToSave, String filename)
    {
        FileOutputStream fileStrm;
        ObjectOutputStream objStrm;

        try {
            fileStrm = new FileOutputStream(filename);
            objStrm = new ObjectOutputStream(fileStrm);
            objStrm.writeObject(objToSave);

            objStrm.close();
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unable to save object to file");
        }
    }

    private static DSABinarySearchTree load(String filename) throws IllegalArgumentException
    {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        DSABinarySearchTree inObj;

        inObj = null;

        try {
            fileStrm = new FileInputStream(filename);
            objStrm = new ObjectInputStream(fileStrm);
            inObj = (DSABinarySearchTree)objStrm.readObject();
            objStrm.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class DSALinkedList not found" + e.getMessage());
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unable to load object from file");
        }
        return inObj;
    }

    private static void readFile(DSABinarySearchTree bst, String inFilename)
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
                processLine(bst, line);
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

    private static void processLine(DSABinarySearchTree bst, String csvRow) throws IllegalStateException
    {
        String id;
        Object value;

        String[] tokens = csvRow.split(",");
        try
        {
            id = tokens[0];
            value = tokens[1];
            bst.insert(id, value);
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
