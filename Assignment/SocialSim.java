import java.util.*;

public class SocialSim
{
    public static void main(String[] args)
    {
        if(args.length < 1)
        {
            System.out.println("Wrong Usuage! Please use -i for interactive Mode and -s for Simulation mode");
        }
        else
        {
            if(args[0].equals("-i"))
            {
                InteractiveMode intMode = new InteractiveMode();
                intMode.menu();
            }
        }
    }
}
