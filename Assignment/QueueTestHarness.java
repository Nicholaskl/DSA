/*
 * File: QueueTestHarness.java
 * File Created: Thursday, 5th September 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Tuesday, 29th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Test file for the Queue data structure
 * Reference: Modified version of the test harness made by Nicholas Klvana-Hooper (me)
 *            for Prac_03 from 21/08/19. (Accessed on the 30th of September, 2019)
 */
import java.util.*;

public class QueueTestHarness
{
    public static void main( String[] args)
    {
        try
        {
            DSAQueue mySQ = new DSAQueue();

            System.out.println("................Shuffling Queue Tests................");
            System.out.println("Testing Enqueue and peek. 200 expected");
            mySQ.enqueue(200);
            mySQ.enqueue(210);
            System.out.println(mySQ.peek());

            mySQ.enqueue(220); //Adds test data to the queue
            mySQ.enqueue(240);
            mySQ.enqueue(260);
            mySQ.enqueue(280);
            mySQ.enqueue(300);
            mySQ.enqueue(320);
            mySQ.enqueue(340);
            mySQ.enqueue(360);

            System.out.println("Testing Dequeue and peek. 210 expected.");
            mySQ.dequeue();
            System.out.println(mySQ.peek());

            System.out.println("Testing Dequeue and peek. 220 expected.");
            mySQ.dequeue();
            System.out.println(mySQ.peek());

            System.out.println("Testing Dequeue and peek. 240 expected.");
            mySQ.dequeue();
            System.out.println(mySQ.peek());

            System.out.println("Testing Dequeue and peek. 260 expected.");
            mySQ.dequeue();
            System.out.println(mySQ.peek());

            System.out.println("Testing Dequeue and peek. 280 expected.");
            mySQ.dequeue();
            System.out.println(mySQ.peek());

            System.out.println("Testing Dequeue and peek. 300 expected.");
            mySQ.dequeue();
            System.out.println(mySQ.peek());

            System.out.println("Testing Dequeue and peek. 320 expected.");
            mySQ.dequeue();
            System.out.println(mySQ.peek());

            System.out.println("Testing Dequeue and peek. 340 expected.");
            mySQ.dequeue();
            System.out.println(mySQ.peek());

            System.out.println("Testing Dequeue and peek. 360 expected.");
            mySQ.dequeue();
            System.out.println(mySQ.peek());
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
