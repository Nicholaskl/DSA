/*
 * File: SocialSim.java
 * File Created: Thursday, 24th October 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Tuesday, 29th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Contains the main method for the Social Simulation program
 */

import java.util.*;

public class SocialSim
{
    public static void main(String[] args)
    {
        if(args.length < 1) //Has to contain at least one argument... which would be -i
        {
            System.out.println("Wrong Usuage! Please use -i for interactive Mode and -s for Simulation mode");
        }
        else
        {
            if(args[0].equals("-i")) //If -i, run interactive mode
            {
                InteractiveMode intMode = new InteractiveMode();
                intMode.menu(); //run interactive mode
            }
            if((args[0].equals("-s")) && (args.length == 5))  //If -is, and contains necessary arguments
            {                                                 //run interactive mode
                SimulationMode simMode = new SimulationMode();
                simMode.menu(args); //run simulation mode
            }
        }
    }
}
