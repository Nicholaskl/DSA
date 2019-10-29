/*
 * File: StackTestHarness.java
 * File Created: Thursday, 5th September 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Tuesday, 29th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Test file for the Stack data structure
 * Reference: Modified version of the test harness made by Nicholas Klvana-Hooper (me)
 *            for Prac_03 from 21/08/19. (Accessed on the 30th of September, 2019)
 */
import java.util.*;

public class StackTestHarness
{
    public static void main( String[] args)
    {
        try
        {
            DSAStack newStack = new DSAStack();

            System.out.println("................Stack Tests................");
            System.out.println("Testing push and top. 210 expected");
            newStack.push(200);
            newStack.push(210);
            System.out.println(newStack.top());

            newStack.push(220); //Add test data to the stack
            newStack.push(240);
            newStack.push(260);
            newStack.push(280);
            newStack.push(300);
            newStack.push(320);
            newStack.push(340);
            newStack.push(360);

            System.out.println("Testing top. 360 expected.");
            System.out.println(newStack.top());

            System.out.println("Testing pop and top. 340 expected.");
            newStack.pop();
            System.out.println(newStack.top());

            System.out.println("Testing pop and top. 320 expected.");
            newStack.pop();
            System.out.println(newStack.top());

            System.out.println("Testing pop and top. 300 expected.");
            newStack.pop();
            System.out.println(newStack.top());

            System.out.println("Testing pop and top. 280 expected.");
            newStack.pop();
            System.out.println(newStack.top());

            System.out.println("Testing pop and top. 260 expected.");
            newStack.pop();
            System.out.println(newStack.top());

            System.out.println("Testing pop and top. 240 expected.");
            newStack.pop();
            System.out.println(newStack.top());

            System.out.println("Testing pop and top. 220 expected.");
            newStack.pop();
            System.out.println(newStack.top());

            System.out.println("Testing pop and top. 210 expected.");
            newStack.pop();
            System.out.println(newStack.top());

            System.out.println("Testing pop and top. 200 expected.");
            newStack.pop();
            System.out.println(newStack.top());
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
