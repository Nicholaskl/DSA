import java.util.*;

public class DSAQueue implements Iterable
{
    protected DSALinkedList list;

    public Iterator iterator()
    {
        return list.iterator();
    }

    public DSAQueue()
    {
        list = new DSALinkedList();
    }

    public boolean isEmpty()
    {
        return (list.peekFirst()==null);
    }

    public void enqueue(Object value)
    {
        list.insertLast(value);
    }

    public Object dequeue()
    {
        Object bottomVal;
        bottomVal = list.peekFirst();
        list.removeFirst();
        return bottomVal;
    }

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
