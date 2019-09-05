import java.util.*;
import java.io.*;

public class DSALinkedList implements Iterable, Serializable
{
    protected DSAListNode head;
    protected DSAListNode tail;

    public DSALinkedList()
    {
        head = null;
        tail = null;
    }

    public void insertFirst(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue);
        if(isEmpty())
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
    }

    public void insertLast(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue);
        if(isEmpty())
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
    }

    public boolean isEmpty()
    {
        return (head == null);
    }

    public Object peekFirst()
    {
        Object nodeValue = null;
        if(isEmpty())
        {
            throw new IllegalArgumentException("Nothing to peek.List is empty");
        }
        else
        {
            nodeValue = head.getValue();
        }
        return nodeValue;
    }

    public Object peekLast()
    {
        Object nodeValue = null;
        if(isEmpty())
        {
            throw new IllegalArgumentException("Nothing to peek.List isempty");
        }
        else
        {
            nodeValue = tail.getValue();
        }
        return nodeValue;
    }

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
        }
        else
        {
            nodeValue = head.getValue();
            head = head.getNext();
            head.setPrev(null);
        }
        return nodeValue;
    }

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
        }
        return nodeValue;
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

    private class DSALinkedListIterator implements Iterator, Serializable
    {
        private DSAListNode iterNext;

        public DSALinkedListIterator(DSALinkedList theList)
        {
            iterNext = theList.head;
        }

        public boolean hasNext()
        {
            return(iterNext != null);
        }

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
