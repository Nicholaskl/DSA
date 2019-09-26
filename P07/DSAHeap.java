public class DSAHeap
{
    private DSAHeapEntry[] heap;
    private int count;

    public DSAHeap(int maxSize)
    {
        DSAHeapEntry blankEntry;

        heap = new DSAHeapEntry[maxSize];
        for (int ii=0; ii < maxSize; ii++)
        {
            blankEntry = new DSAHeapEntry();
            heap[ii] = blankEntry;
        }
        count = 0;
    }

    public void add(int priority, Object value)
    {
        DSAHeapEntry curr;

        for(int ii=0; ii < heap.length; ii++)
        {
            if((heap[ii].value == null) && (heap[ii].value == null))
            {
                curr = new DSAHeapEntry(priority, value);
                heap[ii+1] = curr;
                trickleUp(ii+1);
            }
        }
        count += 1;
    }

    public DSAHeapEntry remove()
    {
        DSAHeapEntry export, empty;

        if(count != 0)
        {
            empty = new DSAHeapEntry();

            export = heap[0];
            heap[0] = heap[count-1];
            heap[count-1] = empty;
            trickleDown(0);
        }
        else
        {
            throw new IllegalArgumentException("Heap empty. Nothing to remove");
        }
        return export;
    }

    private void trickleUp(int sortIdx)
    {
        trickleUpRec(sortIdx);
    }

    private void trickleUpRec(int curIdx) //probs shouldn't be void
    {
        int parentIdx;
        DSAHeapEntry temp;

        parentIdx = (curIdx-1)/2;
        if (curIdx > 0)
        {
            if(heap[curIdx].priority > heap[parentIdx].priority)
            {
                temp = heap[parentIdx];
                heap[parentIdx] = heap[curIdx];
                heap[curIdx] = temp;
                trickleUpRec(parentIdx);
            }
        }
    }

    private void trickleDown(int sortIdx)
    {
        trickleDownRec(sortIdx, count);
    }

    private void trickleDownRec(int curIdx, int numItems)
    {
        int lChildIdx, rChildIdx, largeIdx;
        DSAHeapEntry temp;
    
        lChildIdx = curIdx * 2 + 1;
        rChildIdx = lChildIdx + 1;

        if(lChildIdx < numItems)
        {
            largeIdx = lChildIdx;
            if (rChildIdx < numItems)
            {
                if (heap[lChildIdx].priority < heap[rChildIdx].priority)
                {
                    largeIdx = rChildIdx;
                }
            }
            if (heap[largeIdx].priority > heap[curIdx].priority)
            {
                temp = heap[curIdx];
                heap[curIdx] = heap[largeIdx];
                heap[largeIdx] = temp;
                trickleDownRec(largeIdx, numItems);
            }
        }
    }

    public void print(DSAHeapEntry toExport)
    {
        System.out.println("Priority: " + toExport.priority);
        System.out.println("Value: " + toExport.value);
    }

    public void export()
    {
        for(int ii=0; ii < count; ii++)
        {
            System.out.println("Priority: " + heap[ii].priority);
            System.out.println("Value: " + heap[ii].value);
        }
    }

    private class DSAHeapEntry
    {
        private int priority;
        private Object value;

        public DSAHeapEntry()
        {
            priority = -1;
            value = null;
        }

        public DSAHeapEntry(int inPriority, Object inValue)
        {
            priority = inPriority;
            value = inValue;
        }   
    }
}
