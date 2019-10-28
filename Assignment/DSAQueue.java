/*
 * File: DSAQueue.java
 * File Created: Thursday, 5th September 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Tuesday, 29th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Contains functions for a linked list data structure
  * Reference: A modified version of the queue used from Prac_02 made by Nicholas
  *            Klvana-Hooper(me) from 14/08/19. (Accessed on the 30th of September, 2019)
  *            based on Lecture 02 - queue lecture slides (Accessed on the 5th of September, 2019)
  */
import java.util.*;

public class DSAQueue implements Iterable
{
    protected DSALinkedList list;

    public Iterator iterator()
    {
        return list.iterator();
    }

    /*
    * SUBMODULE: DSAQueue
    * IMPORT:
    * EXPORT:
    * ASSERTION: default constructor for DSAQueue class
    */
    public DSAQueue()
    {
        list = new DSALinkedList();
    }

    public boolean isEmpty()
    {
        return (list.isEmpty());
    }

    /*
    * SUBMODULE: enqueue
    * IMPORT: value(Object)
    * EXPORT:
    * ASSERTION: adds value to the queue
    */
    public void enqueue(Object value)
    {
        list.insertLast(value);
    }

    /*
    * SUBMODULE: dequeue
    * IMPORT:
    * EXPORT: bottomVal(Object)
    * ASSERTION: returns value from bottom of queue
    */
    public Object dequeue()
    {
        Object bottomVal;
        bottomVal = list.peekFirst();
        list.removeFirst();
        return bottomVal;
    }

    /*
    * SUBMODULE: peek
    * IMPORT:
    * EXPORT: bottomVal(Object)
    * ASSERTION: return but don't remove bottom value from queue
    */
    public Object peek()
    {
        Object bottomVal;
        if (isEmpty())
        {
            throw new IllegalArgumentException("Queue is empty!");
        }
        else
        {
            bottomVal = list.peekFirst();
        }
        return bottomVal;
    }
}
