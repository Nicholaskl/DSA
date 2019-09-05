public class ShufflingQueue extends DSAQueue
{
    public ShufflingQueue()
    {
        super();
    }

    public ShufflingQueue(int maxCapacity)
    {
        super(maxCapacity);
    }

    public void enqueue(Object value)
    {
        if (isFull())
        {
            throw new IllegalArgumentException("Queue is full");
        }
        else
        {
            queue[count] = value;
            count += 1;
        }
    }

    public Object dequeue()
    {
        Object bottomVal;
    
        bottomVal = peek();
        for(int ii=1; ii <= (count-1); ii++)
        {
            queue[ii-1] = queue[ii];
        }
        
        queue[count-1] = null;
        count += -1;

        return bottomVal;
    }

    public Object peek()
    {
        Object bottomVal;
        if(isEmpty())
        {
            throw new IllegalArgumentException("Queue is empty");
        }
        else
        {
            bottomVal = queue[0];
        }

        return bottomVal;
    }
}
