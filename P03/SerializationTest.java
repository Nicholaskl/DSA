import java.util.*;
import java.io.*;

public class SerializationTest
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        boolean close = false;
        String fileName;
        fileName = "";
        DSALinkedList ll = new DSALinkedList();

        for(int i=50; i <= 100 ; i++)
        {
            ll.insertFirst(i);
        }

        while(close != true)
        {
            int choice = 0;
            System.out.println("1 - Read a Serialized file");
            System.out.println("2 - Display the list");
            System.out.println("3 - Write a serialized file");
            System.out.println("4 - Exit Program");
            System.out.println("-------------------------------------");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    System.out.println("Please enter a filename");
                    fileName = sc.next();
                    ll = load(fileName);
                    fileName = "";
                    break;
                case 2:
                    Iterator iter = ll.iterator();
                    while (iter.hasNext())
                    {
                        System.out.println(iter.next());
                    }
                    break;
                case 3:
                    System.out.println("Please enter a filename");
                    fileName = sc.next();
                    save(ll, fileName);
                    fileName = "";
                    break;
                default:
                    System.out.println("leaving!");
                    close = true;
            }
        }
    }

    private static void save(DSALinkedList objToSave, String filename)
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

    private static DSALinkedList load(String filename) throws IllegalArgumentException
    {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        DSALinkedList inObj;

        inObj = null;

        try {
            fileStrm = new FileInputStream(filename);
            objStrm = new ObjectInputStream(fileStrm);
            inObj = (DSALinkedList)objStrm.readObject();
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
}
