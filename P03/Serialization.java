import java.util.*;
import java.io.*;

public class Serialization
{
    public static void main(String [] args)
    {
        
    }

    public class ContainerClass implements Serializable
    {
    }

    private void save(ContainerClass objToSave, String filename)
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

    private ContainerClass load(String filename) throws IllegalArgumentException
    {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        ContainerClass inObj;
        
        inObj = null;

        try {
            fileStrm = new FileInputStream(filename);
            objStrm = new ObjectInputStream(fileStrm);
            inObj = (ContainerClass)objStrm.readObject();
            objStrm.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class containerClass not found" + e.getMessage());
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unable to load object from file");
        }
        return inObj;
    }
}
