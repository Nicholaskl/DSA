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

    /*
    * SUBMODULE: isEmpty
    * IMPORT:
    * EXPORT: isEmpty(boolean)
    * ASSERTION: returns whether stack is Empty
    */
    public boolean isEmpty()
    {
        return (list.peekFirst()==null);
    }

    /*
    * SUBMODULE: push
    * IMPORT: value(Object)
    * EXPORT:
    * ASSERTION: Adds value to the top of the stack
    */
    public void push(Object value)
    {
        list.insertFirst(value);
    }

    /*
    * SUBMODULE: pop
    * IMPORT:
    * EXPORT: topVal(Object)
    * ASSERTION: returns and removes the value at the top of the stack
    */
    public Object pop()
    {
        Object topVal;
        topVal = list.peekFirst();
        list.removeFirst();
        return topVal;
    }

    /*
    * SUBMODULE: top
    * IMPORT:
    * EXPORT: topVal(Object)
    * ASSERTION: returns but doesn't remove the value at the top of the stack
    */
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
