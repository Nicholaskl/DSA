/*
 * File: LinkedListTestHarness.java
 * File Created: Monday, 30th September 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Tuesday, 29th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Test file for the Graph data structure
 */
import java.util.*;

public class LinkedListTestHarness
{
    public static void main(String[] args)
    {
        try
        {
            DSALinkedList ll = new DSALinkedList();

            System.out.println("----------Test Harness----------");
            System.out.println("Testing insertfirst and peekfirst. Expecting 5");
            ll.insertFirst(5);
            System.out.println(ll.peekFirst());

            System.out.println("Testing insertfirst and peekfirst. Expecting 10");
            ll.insertFirst(10);
            System.out.println(ll.peekFirst());

            System.out.println("Testing insertlast and peekfirst. Expecting 10");
            ll.insertLast(15);
            System.out.println(ll.peekFirst());

            System.out.println("Testing insertfirst and peeklast. Expecting 15");
            ll.insertFirst(20);
            System.out.println(ll.peekLast());

            System.out.println("Tesing iterator. Expecting 20,10,5,15");
            Iterator iter = ll.iterator();
            while (iter.hasNext())
            {
                System.out.println(iter.next());
            }

            System.out.println("Testing removefirst and peekfirst. Expecting 10");
            ll.removeFirst();
            System.out.println(ll.peekFirst());

            System.out.println("Testing removelast and peekfirst. Expecting 10");
            ll.removeLast();
            System.out.println(ll.peekFirst());

            System.out.println("Testing removefirst and peeklast. Expecting 5");
            ll.removeFirst();
            System.out.println(ll.peekLast());

            System.out.println("Testing removing node and peeking nothing");
            ll.removeFirst();
            System.out.println(ll.peekFirst());
        }
        catch(Exception e)
        {
            System.out.println("Error should be nothing to remove");
            System.out.println("Error is: " + e.getMessage());
        }
    }
}
