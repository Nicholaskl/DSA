/**
** Software Technology 152
** Class to hold various static sort methods.
*/
class Sorts
{
    // bubble sort
    public static int[] bubbleSort(int[] A)
    {
        int pass, temp;
        boolean sorted;
        pass = 0;

        do
        {
            sorted = true;

            for(int ii=0;ii<=(A.length-1-pass-1);ii++)
            {
                if(A[ii] > A[ii+1])
                {
                    temp = A[ii];
                    A[ii] = A[ii+1];
                    A[ii+1] = temp;
                    sorted = false;
                }
            }
            pass = pass+1;
        } while (sorted != true);
        return A;
    }//bubbleSort()

    // selection sort
    public static int[] selectionSort(int[] A)
    {
        int temp, minIdx;

        for(int nn=0;nn<=(A.length-1);nn++)
        {
            minIdx = nn;
            for(int jj=nn+1;jj<=(A.length-1);jj++)
            {
                if(A[jj] < A[minIdx])
                {
                    minIdx = jj;
                }
            }
            temp = A[minIdx];
            A[minIdx] = A[nn];
            A[nn] = temp;
        }
        return A;
    }// selectionSort()

    // insertion sort
    public static int[] insertionSort(int[] A)
    {
        int temp, ii;
        for(int nn=1; nn<=(A.length-1);nn++)
        {
            ii = nn;
            temp = A[ii];
            while((ii > 0) && (A[ii-1] > temp))
            {
                A[ii] = A[ii-1];
                ii = ii-1;
            }
            A[ii] = temp;
        }
        return A;
    }// insertionSort()

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static int[] mergeSort(int[] A)
    {
        A = mergeSortRecurse(A, 0, A.length-1);
        return A; 
    }//mergeSort()
    private static int[] mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
        int midIdx;

        if(leftIdx < rightIdx)
        {
            midIdx = (leftIdx + rightIdx)/2;

            mergeSortRecurse(A, leftIdx, midIdx);
            mergeSortRecurse(A, midIdx+1, rightIdx);

            merge(A, leftIdx, midIdx, rightIdx);            
        }
        return A;
    }//mergeSortRecurse()
    private static int[] merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
        int[] tempArr = new int[rightIdx-leftIdx+1];
        int ii = leftIdx;
        int jj = midIdx +1;
        int kk = 0;

        while((ii <= midIdx) && (jj <= rightIdx))
        {
            if(A[ii] <= A[jj])
            {
                tempArr[kk] = A[ii];
                ii++;
            }
            else
            {
                tempArr[kk] = A[jj];
                jj++;
            }
            kk++;
        }
        for(ii = ii; ii < midIdx; ii++)
        {
            tempArr[kk] = A[ii];
            kk++;
        }
        for(jj = jj; jj < rightIdx; jj++)
        {
            tempArr[kk] = A[jj];
            kk++;
        }
    
        for(kk = leftIdx; kk < rightIdx; kk++)
        {
            A[kk] = tempArr[kk-leftIdx];
        }
        return A;
    }//merge()

    //quicksortMedian3
    public static int[] quickSortMedian3(int[] A)
    {
        A = quickSortM3Recurse(A, 0, A.length-1);
        return A;
    }
    private static int[] quickSortM3Recurse(int[] A, int leftIdx, int rightIdx)
    {
        int pivotIdx, newPivotIdx;
        
        if(rightIdx > leftIdx)
        {
            pivotIdx = (leftIdx+rightIdx)/2;
            newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortRecurse(A, leftIdx, newPivotIdx-1);
            quickSortRecurse(A, newPivotIdx+1, rightIdx);
        }
        return A;
    }
    // quickSort - front-end for kick-starting the recursive algorithm
    public static int[] quickSort(int[] A)
    {
        A = quickSortRecurse(A, 0, A.length-1);
        return A;
    }//quickSort()
    private static int[] quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
        int pivotIdx, newPivotIdx;
        
        if(rightIdx > leftIdx)
        {
            pivotIdx = leftIdx;
            newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortRecurse(A, leftIdx, newPivotIdx-1);
            quickSortRecurse(A, newPivotIdx+1, rightIdx);
        }
        return A;
    }//quickSortRecurse()
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
        int newPivotIdx, pivotVal, currIdx, temp;
        
        pivotVal = A[pivotIdx];
        A[pivotIdx] = A[rightIdx];
        A[rightIdx] = pivotVal;

        currIdx = leftIdx;
        for(int ii = leftIdx; ii <= rightIdx-1; ii++)
        {
            if(A[ii] < pivotVal)
            {
                temp = A[ii];
                A[ii] = A[currIdx];
                A[currIdx] = temp;
                currIdx++;
            }
        }
        newPivotIdx = currIdx;
        A[rightIdx] = A[newPivotIdx];
        A[newPivotIdx] = pivotVal;

		return newPivotIdx;	// TEMP - Replace this when you implement QuickSort
    }//doPartitioning


}//end Sorts calss
