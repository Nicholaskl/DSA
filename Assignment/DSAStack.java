/*
 * File: DSAStack.java
 * File Created: Thursday, 5th September 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Tuesday, 29th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Contains functions for a linked list data structure
  * Reference: A modified version of the stac used from Prac_02 made by Nicholas
  *            Klvana-Hooper(me) from 14/08/19. (Accessed on the 30th of September, 2019)
  *            based on Lecture 02 - stack lecture slides (Accessed on the 5th of September, 2019)
  */
import java.util.*;

public class DSAStack implements Iterable
{
    protected DSALinkedList list;

    /*
    * SUBMODULE: DSAStack
    * IMPORT:
    * EXPORT:
    * ASSERTION: default constructor for DSAStack class
    */
    public DSAStack()
    {
        list = new DSALinkedList();
    }

    //ACCESSOR
    public boolean isEmpty()
    {
        return (list.peekFirst()==null);
    }


    //MUTATOR fix value??
    public void push(Object value)
    {
        list.insertFirst(value);
    }

    //MUTATOR fix topVAL return type!
    public Object pop()
    {
        Object topVal;
        topVal = list.peekFirst();
        list.removeFirst();
        return topVal;
    }

    //ACCESSOR fix topVAL return TYPE!
    public Object top()
    {
        Object topVal;
        if(isEmpty())
        {
            throw new IllegalArgumentException("Stack is Empty");
        }
        else
        {
            topVal = list.peekFirst();
        }
        return topVal;
    }

    public Iterator iterator()
    {
        return list.iterator();
    }
}
