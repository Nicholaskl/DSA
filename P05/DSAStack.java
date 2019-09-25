import java.util.*;

public class DSAStack implements Iterable
{
    protected DSALinkedList list;

    // DEFAULT CONSTRUCTOR
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
