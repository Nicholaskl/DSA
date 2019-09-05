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
    public static void mergeSort(int[] A)
    {
    }//mergeSort()
    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//mergeSortRecurse()
    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
    }//merge()


    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(int[] A)
    {
    }//quickSort()
    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//quickSortRecurse()
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
		return 0;	// TEMP - Replace this when you implement QuickSort
    }//doPartitioning


}//end Sorts calss
