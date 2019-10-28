/*
 * File: DSALinkedList.java
 * File Created: Thursday, 5th September 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Tuesday, 29th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Contains functions for a linked list data structure
  * Reference: A modified version of the Linked list used from Prac_03 made by Nicholas
  *            Klvana-Hooper(me) from 21/08/19. (Accessed on the 30th of September, 2019)
  *            based on Lecture 03 - linked list lecture slides (Accessed on the 5th of September, 2019)
  */
import java.util.*;
import java.io.*;

public class DSALinkedList implements Iterable, Serializable
{
    protected DSAListNode head;
    protected DSAListNode tail;
    private int count;

    /*
    * SUBMODULE: DSALinkedList
    * IMPORT:
    * EXPORT:
    * ASSERTION: default constructor for DSALinkedList class
    */
    public DSALinkedList()
    {
        head = null;
        tail = null;
        count = 0;
    }

    /*
    * SUBMODULE: insertFirst
    * IMPORT: newValue(Object)
    * EXPORT:
    * ASSERTION: adds a node to the front of the list
    */
    public void insertFirst(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue);
        if(isEmpty()) //if the list is empty, tail and head is the new node
        {
            head = newNd;
            tail = newNd;
        }
        else
        {
            head.setPrev(newNd);
            newNd.setNext(head);
            head = newNd;
        }
        count++;
    }

    /*
    * SUBMODULE: insertLast
    * IMPORT: newValue(Object)
    * EXPORT:
    * ASSERTION: adds a ndoe to the end of the list
    */
    public void insertLast(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue);
        if(isEmpty()) //if the list is empty, tail and head is the new node
        {
            head = newNd;
            tail = newNd;
        }
        else
        {
            newNd.setPrev(tail);
            tail.setNext(newNd);
            tail = newNd;
        }
        count++;
    }

    public boolean isEmpty()
    {
        return (head == null);
    }

    /*
    * SUBMODULE: peekFirst
    * IMPORT:
    * EXPORT: nodeValue(Object)
    * ASSERTION: returns value of the first node
    */
    public Object peekFirst()
    {
        Object nodeValue = null;
        if(isEmpty())
        {
            throw new IllegalArgumentException("Nothing to peek. List is empty");
        }
        else
        {
            nodeValue = head.getValue();
        }
        return nodeValue;
    }

    /*
    * SUBMODULE: peekLast
    * IMPORT:
    * EXPORT: nodeValue(Object)
    * ASSERTION: returns value of the last node
    */
    public Object peekLast()
    {
        Object nodeValue = null;
        if(isEmpty())
        {
            throw new IllegalArgumentException("Nothing to peek. List is empty");
        }
        else
        {
            nodeValue = tail.getValue();
        }
        return nodeValue;
    }

    public int getCount()
    {
        return count;
    }

    /*
    * SUBMODULE: removeFirst
    * IMPORT:
    * EXPORT: nodeValue(Object)
    * ASSERTION: removes the first node from the list
    */
    public Object removeFirst()
    {
        Object nodeValue = null;
        if(isEmpty())
        {
            throw new IllegalArgumentException("Nothing to remove.List is empty");
        }
        else if (head.getNext() == null)
        {
            nodeValue = head.getValue();
            head = null;
            tail = null;
            count--; //one less node now
        }
        else
        {
            nodeValue = head.getValue();
            head = head.getNext();
            head.setPrev(null);
            count--;
        } //one less node now
        return nodeValue;
    }

    /*
    * SUBMODULE: removeLast
    * IMPORT:
    * EXPORT: nodeValue(Object)
    * ASSERTION: removes the last node from the list
    */
    public Object removeLast()
    {
        Object nodeValue = null;
        DSAListNode currNd, prevNd;
        if(isEmpty())
        {
            throw new IllegalArgumentException("Nothing to remove.List is empty");
        }
        else
        {
            nodeValue = tail.getValue();
            tail = tail.getPrev();
            tail.setNext(null);
            count--;
        }
        return nodeValue;
    }

    /*
    * SUBMODULE: remove
    * IMPORT: idx(int)
    * EXPORT: void
    * ASSERTION: removes the node at the index given
    */
    public void remove(int idx)
    {
        DSAListNode currNd;
        DSAListNode prevNd = null;
        DSAListNode nextNd = null;

        if(isEmpty())
        {
            throw new IllegalArgumentException("Can't remove from empty list");
        }
        else if(idx == 0) //if beginning of list, just remove first
        {
            removeFirst();
        }
        else if(idx == count-1) //if end of list, remove the last
        {
            removeLast();
        }
        else
        {
            currNd = head;

            for(int ii = 0; ii < idx; ii++) //continue traversing until node needed is gotten
            {
                currNd = currNd.next;
            }

            if(currNd.prev != null)
            {
                prevNd = currNd.prev;
            }

            if(currNd.next != null)
            {
                nextNd = currNd.next;
            }

            prevNd.setNext(nextNd);
            nextNd.setPrev(prevNd);
            currNd.next = null;
            currNd.prev = null;
            count--;
        }
    }


    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }


    private class DSAListNode implements Serializable
    {
        private Object value;
        private DSAListNode next;
        private DSAListNode prev;

        /*
        * SUBMODULE: DSAListNode
        * IMPORT: inValue(Object)
        * EXPORT:
        * ASSERTION: Alternate constructor for DSAListNode class
        */
        public DSAListNode(Object inValue)
        {
            value = inValue;
            next = null;
            prev = null;
        }

        public Object getValue()
        {
            return value;
        }

        public void setValue(Object inValue)
        {
            value = inValue;
        }

        public DSAListNode getNext()
        {
            return next;
        }

        public DSAListNode getPrev()
        {
            return prev;
        }

        public void setNext(DSAListNode newNext)
        {
            next = newNext;
        }

        public void setPrev(DSAListNode newPrev)
        {
            prev = newPrev;
        }
    }

    private class DSALinkedListIterator implements Iterator
    {
        private DSAListNode iterNext;

        /*
        * SUBMODULE: DSALinkedListIterator
        * IMPORT: theList(DSALinkedList)
        * EXPORT:
        * ASSERTION: Alternate constructor for the iterator class
        */
        public DSALinkedListIterator(DSALinkedList theList)
        {
            iterNext = theList.head;
        }

        public boolean hasNext()
        {
            return(iterNext != null);
        }

        /*
        * SUBMODULE: next
        * IMPORT:
        * EXPORT: value(Object)
        * ASSERTION: returns the next node in list
        */
        public Object next()
        {
            Object value;
            if (iterNext == null)
            {
                value = null;
            }
            else
            {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return value;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Not supported");
        }
    }
}
