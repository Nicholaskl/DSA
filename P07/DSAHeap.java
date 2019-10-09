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
        curr = new DSAHeapEntry(priority, value);
        Boolean stop = false;
        int ii = 0;

        if(heap[0].value == null)
        {
            heap[0] = curr;
        }
        else
        {
            do
            {
                if(heap[ii].value == null)
                {
                    heap[ii] = curr;
                    trickleUp(ii);
                    stop = true;
                }
                else
                {
                    ii += 1;
                }
            }
            while(stop != true);
        }
        count += 1;
    }

    public DSAHeapEntry remove()
    {
        DSAHeapEntry export, empty;

        if(count > 0)
        {
            empty = new DSAHeapEntry();

            export = heap[0];
            heap[0] = heap[count-1];
            heap[count-1] = empty;
            trickleDown(0, heap.length);
        }
        else
        {
            throw new IllegalArgumentException("Heap empty. Nothing to remove");
        }
        count += -1;
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

    private void trickleDown(int sortIdx, int numItems)
    {
        trickleDownRec(sortIdx, numItems);
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

    private void heapify()
    {
        for(int ii = (heap.length/2)-1; ii >= 0; ii--)
        {
            trickleDown(ii, heap.length);
        }
    }

    public int[] heapSort(Object[] array)
    {
        int[] returnArr = new int[array.length];
        DSAHeapEntry curr = null;

        heap = new DSAHeapEntry[array.length];
        for (int ii = 0; ii < heap.length; ii++)
        {
            curr = new DSAHeapEntry((int)array[ii], " ");
            heap[ii] = curr;
        }

        heapify();

        for(int ii = array.length-1; ii >= 1; ii--)
        {
            curr = heap[ii];
            heap[ii] = heap[0];
            heap[0] = curr;
            trickleDown(0, ii);
        }


        for (int ii = 0;ii < array.length ; ii++)
        {
            returnArr[ii] = heap[ii].priority;
        }

        return returnArr;
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
