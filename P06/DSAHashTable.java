import java.util.*;

public class DSAHashTable
{
    public static final double LOWER_BOUND = 0.4;
    public static final double UPPER_BOUND = 0.65;
    private DSAHashEntry[] hashArray;
    private int count;

    public DSAHashTable(int tableSize)
    {
        int actualSize;
        DSAHashEntry entry;

        actualSize = findNextPrime(tableSize);
        hashArray = new DSAHashEntry[actualSize];
        for(int ii=0; ii < actualSize; ii++)
        {
            entry = new DSAHashEntry();
            hashArray[ii] = entry;
        }
        count = 0;
    }

    public void put(String inKey, Object inValue)
    {
        int index;
        DSAHashEntry currIdx;
        Boolean entered = false;

        index = hash(inKey);
        currIdx = new DSAHashEntry(inKey, inValue);

        if(getLoadFactor() > UPPER_BOUND)
        {
            reSize(findNextPrime(hashArray.length * 2));
            put(inKey, inValue);
        }
        else
        {
            if(count < hashArray.length)
            {
                while(!entered)
                {
                    if(hashArray[index].state == 1)
                    {
                        index = (index + stepHash(inKey)) % hashArray.length;
                    }
                    else
                    {
                        hashArray[index] = currIdx;
                        entered = true;
                        count += 1;
                    }
                }
            }
            else
            {
                throw new IllegalArgumentException("Not enough space!");
            }
        }
    }

    public Object get(String inKey)
    {
        Object retValue = null;
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        Boolean found = false;
        Boolean giveUp = false;

        while ((!found) && (!giveUp))
        {
            if(hashArray[hashIdx].state == 0)
            {
                giveUp = true;
            }
            else if ((hashArray[hashIdx].key).equals(inKey))
            {
                found = true;
            }
            else
            {
                hashIdx = (hashIdx + stepHash(inKey)) % hashArray.length;
                if (hashIdx == origIdx)
                {
                    giveUp = true;
                }
            }
        }

        if (!found)
        {
            throw new IllegalArgumentException("key not found");
        }
        retValue = hashArray[hashIdx].value;

        return retValue;
    }

    public void remove(String inKey)
    {
        DSAHashEntry empty;
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        Boolean found = false;
        Boolean giveUp = false;

        while ((!found) && (!giveUp))
        {
            if(hashArray[hashIdx].state == 0)
            {
                giveUp = true;
            }
            else if ((hashArray[hashIdx].key).equals(inKey))
            {
                empty = new DSAHashEntry();
                hashArray[hashIdx] = empty;
                hashArray[hashIdx].state = -1;
                count -= 1;
                found = true;
            }
            else
            {
                hashIdx = (hashIdx + stepHash(inKey)) % hashArray.length;
                if (hashIdx == origIdx)
                {
                    giveUp = true;
                }
            }
        }

        if (!found)
        {
            throw new IllegalArgumentException("key not found");
        }

        if(getLoadFactor() < LOWER_BOUND)
        {
            reSize(findNextPrime(hashArray.length / 2));
        }

    }

    public double getLoadFactor()
    {
        return (double)count/(double)hashArray.length;
    }

    public DSALinkedList export()
    {
        DSALinkedList exportLL = new DSALinkedList();

        for (int i=0; i < hashArray.length; i++)
        {
            exportLL.insertLast(hashArray[i]);
        }

        return exportLL;
    }

    private void reSize(int size)
    {
        DSALinkedList old;
        DSAHashEntry curr, entry;

        old = export();
        hashArray = new DSAHashEntry[size];
        count = 0;
        for(int ii=0; ii < size; ii++)
        {
            entry = new DSAHashEntry();
            hashArray[ii] = entry;
        }

        Iterator iter = old.iterator();
        while (iter.hasNext())
        {
            curr = (DSAHashEntry)iter.next();
            if (curr.state == 1)
            {
                put(curr.key, curr.value);
            }
        }
    }

    private int hash(String key)
    {
        int hashIdx = 0;

        for(int ii=0; ii < key.length(); ii++)
        {
            hashIdx = (31 * hashIdx) + key.charAt(ii);
        }
        return Math.abs(hashIdx) % hashArray.length;
    }

    private int stepHash(String key)
    {
        int num = Integer.parseInt(key);
        return 5 - (num % 5);
    }

    private int findNextPrime(int startVal)
    {
        int primeVal, ii, rootVal;
        Boolean isPrime;

        if (startVal % 2 == 0)
        {
            primeVal = startVal -1;
        }
        else
        {
            primeVal = startVal;
        }

        isPrime = false;
        do
        {
            primeVal = primeVal + 2;
            ii = 3;
            isPrime = true;
            rootVal = (int)Math.sqrt(primeVal);

            do
            {
                if (primeVal % ii == 0)
                {
                    isPrime = false;
                }
                else
                {
                    ii = ii + 2;
                }
            }
            while (( ii <= rootVal) && (isPrime));
        }
        while (!isPrime);

        return primeVal;
    }

    private class DSAHashEntry
    {
        private String key;
        private Object value;
        private int state;

        public DSAHashEntry()
        {
            key = "";
            value = null;
            state = 0;
        }

        public DSAHashEntry(String inKey, Object inValue)
        {
            key = inKey;
            value = inValue;
            state = 1;
        }

        public String toString()
        {
            String export = "";

            export = "key: " + key + "\n";
            export += "value: " + value;

            return export;
        }

        public String toWrite()
        {
            String export = "";

            export = key + value;

            return export;
        }
    }
}
