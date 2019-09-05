

public class DSAStack
{
    public static final int DEFAULT_CAPACITY = 100;

    private Object[] stack;
    private int count;

    // DEFAULT CONSTRUCTOR
    public DSAStack()
    {
        stack = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    // ALTERNATE CONSTRUCTOR
    public DSAStack(int maxCapacity)
    {
        stack = new Object[maxCapacity];
        count = 0;
    }

    // ACCESSOR
    public int getCount()
    {
        return count;
    }

    //ACCESSOR
    public boolean isEmpty()
    {
        return (count==0);
    }

    //ACCESSOR
    public boolean isFull()
    {
        return (count==stack.length);
    }

    //MUTATOR fix value??
    public void push(Object value)
    {
        if (isFull())
        {
            throw new IllegalArgumentException("Stack is full!"); //double check
        }
        else
        {
            stack[count] = value;
            count += 1;
        }
    }

    //MUTATOR fix topVAL return type!
    public Object pop()
    {
        Object topVal;
        topVal = top();
        count += -1;
        return topVal;
    }

    //ACCESSOR fix topVAL return TYPE!
    public Object top()
    {
        Object topVal;
        if (isEmpty())
        {
            throw new IllegalArgumentException("Stack is Empty!"); //double check
        }
        else
        {
            topVal = stack[count-1];
        }
        return topVal;
    }
}
