package grinea;

/*
* Sorts
* Author: Owen Frere 19520500
* Date Modified: 27/10/18
* Reference: Adapted from the sorts practical
*   Note: A lot of repeated code here, but wasn't able
*       to work out how to get the comparison to change fields
*       in the time I had for the assignment
*/
class Sorts
{
   
    /*
    * mSSurname
    * I: A (candidate[])
    * E: none
    * P: Wrapper to start merge sort by surname
    */
    public static void mSSurname(Candidate[] A)
    {
        mSRSurname(A, 0, A.length - 1);
    }

    /*
    * mSRSurname
    * I: A (candidate[]), leftIdx (int), rightIdx (int)
    * E: none
    * P: Actual recursive merge sort by surname
    */
    private static void mSRSurname(Candidate[] A, int leftIdx, int rightIdx)
    {
        int midIdx;

        if (leftIdx < rightIdx)    
        { 
            midIdx = (leftIdx + rightIdx) / 2;

            mSRSurname(A, leftIdx, midIdx);
            mSRSurname(A, midIdx + 1, rightIdx);

            mergeSurame(A, leftIdx, midIdx, rightIdx);
        }   
    
    }

    /*
    * mergeSurname
    * I: A (candidate[]), leftIdx (int), midIdx (int), rightIdx (int)
    * E: none
    * P: merges subarrays based on surname
    */
    private static void mergeSurame(Candidate[] A, int leftIdx, int midIdx, 
        int rightIdx)
    {
        Candidate[] tempArr = new Candidate[rightIdx - leftIdx + 1];
        int ii, jj, kk;

        ii = leftIdx;
        jj = midIdx + 1;
        kk = 0;

        while (ii <= midIdx && jj <= rightIdx)
        {
            if (A[ii].getSurname().compareTo(A[jj].getSurname()) <= 0)
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

        for(ii = ii; ii <= midIdx; ii++)
        {
            tempArr[kk] = A[ii];
            kk++;
        }

        for(jj = jj; jj <= rightIdx; jj++)
        {
            tempArr[kk] = A[jj];
            kk++;
        }

        for(kk = leftIdx; kk <= rightIdx; kk++)
        {
            A[kk] = tempArr[kk-leftIdx];
        }

    }
    
    /*
    * mSState
    * I: A (candidate[])
    * E: none
    * P: Wrapper to start merge sort by state
    */
    public static void mSState(Candidate[] A)
    {
        mSRState(A, 0, A.length - 1);
    }

    /*
    * mSRState
    * I: A (candidate[]), leftIdx (int), rightIdx (int)
    * E: none
    * P: Actual recursive merge sort by state
    */
    private static void mSRState(Candidate[] A, int leftIdx, int rightIdx)
    {
        int midIdx;

        if (leftIdx < rightIdx)    
        { 
            midIdx = (leftIdx + rightIdx) / 2;

            mSRState(A, leftIdx, midIdx);
            mSRState(A, midIdx + 1, rightIdx);

            mergeState(A, leftIdx, midIdx, rightIdx);
        }   
    
    }
    
    /*
    * mergeState
    * I: A (candidate[]), leftIdx (int), midIdx (int), rightIdx (int)
    * E: none
    * P: merges subarrays based on state
    */
    private static void mergeState(Candidate[] A, int leftIdx, int midIdx, 
        int rightIdx)
    {
        Candidate[] tempArr = new Candidate[rightIdx - leftIdx + 1];
        int ii, jj, kk;

        ii = leftIdx;
        jj = midIdx + 1;
        kk = 0;

        while (ii <= midIdx && jj <= rightIdx)
        {
            if (A[ii].getState().compareTo(A[jj].getState()) <= 0)
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

        for(ii = ii; ii <= midIdx; ii++)
        {
            tempArr[kk] = A[ii];
            kk++;
        }

        for(jj = jj; jj <= rightIdx; jj++)
        {
            tempArr[kk] = A[jj];
            kk++;
        }

        for(kk = leftIdx; kk <= rightIdx; kk++)
        {
            A[kk] = tempArr[kk-leftIdx];
        }

    }
   
    /*
    * mSParty
    * I: A (candidate[])
    * E: none
    * P: Wrapper to start merge sort by party
    */
    public static void mSParty(Candidate[] A)
    {
        mSRParty(A, 0, A.length - 1);
    }

    /*
    * mSRParty
    * I: A (candidate[]), leftIdx (int), rightIdx (int)
    * E: none
    * P: Actual recursive merge sort by party
    */
    private static void mSRParty(Candidate[] A, int leftIdx, int rightIdx)
    {
        int midIdx;

        if (leftIdx < rightIdx)    
        { 
            midIdx = (leftIdx + rightIdx) / 2;

            mSRParty(A, leftIdx, midIdx);
            mSRParty(A, midIdx + 1, rightIdx);

            mergeParty(A, leftIdx, midIdx, rightIdx);
        }   
    
    }

    /*
    * mergeParty
    * I: A (candidate[]), leftIdx (int), midIdx (int), rightIdx (int)
    * E: none
    * P: merges subarrays based on party
    */
    private static void mergeParty(Candidate[] A, int leftIdx, int midIdx, 
        int rightIdx)
    {
        Candidate[] tempArr = new Candidate[rightIdx - leftIdx + 1];
        int ii, jj, kk;

        ii = leftIdx;
        jj = midIdx + 1;
        kk = 0;

        while (ii <= midIdx && jj <= rightIdx)
        {
            if (A[ii].getParty().compareTo(A[jj].getParty()) <= 0)
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

        for(ii = ii; ii <= midIdx; ii++)
        {
            tempArr[kk] = A[ii];
            kk++;
        }

        for(jj = jj; jj <= rightIdx; jj++)
        {
            tempArr[kk] = A[jj];
            kk++;
        }

        for(kk = leftIdx; kk <= rightIdx; kk++)
        {
            A[kk] = tempArr[kk-leftIdx];
        }

    }

    /*
    * mSSDivision
    * I: A (candidate[])
    * E: none
    * P: Wrapper to start merge sort by division
    */
    public static void mSDivision(Candidate[] A)
    {
        mSRDivision(A, 0, A.length - 1);
    }

    /*
    * mSRDivision
    * I: A (candidate[]), leftIdx (int), rightIdx (int)
    * E: none
    * P: Actual recursive merge sort by Division
    */
    private static void mSRDivision(Candidate[] A, int leftIdx, int rightIdx)
    {
        int midIdx;

        if (leftIdx < rightIdx)    
        { 
            midIdx = (leftIdx + rightIdx) / 2;

            mSRDivision(A, leftIdx, midIdx);
            mSRDivision(A, midIdx + 1, rightIdx);

            mergeDivision(A, leftIdx, midIdx, rightIdx);
        }   
    
    }

    
    /*
    * mergeDivision
    * I: A (candidate[]), leftIdx (int), midIdx (int), rightIdx (int)
    * E: none
    * P: merges subarrays based on division
    */
    private static void mergeDivision(Candidate[] A, int leftIdx, int midIdx, 
        int rightIdx)
    {
        Candidate[] tempArr = new Candidate[rightIdx - leftIdx + 1];
        int ii, jj, kk;

        ii = leftIdx;
        jj = midIdx + 1;
        kk = 0;

        while (ii <= midIdx && jj <= rightIdx)
        {
            if (A[ii].getDivision().compareTo(A[jj].getDivision()) <= 0)
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

        for(ii = ii; ii <= midIdx; ii++)
        {
            tempArr[kk] = A[ii];
            kk++;
        }

        for(jj = jj; jj <= rightIdx; jj++)
        {
            tempArr[kk] = A[jj];
            kk++;
        }

        for(kk = leftIdx; kk <= rightIdx; kk++)
        {
            A[kk] = tempArr[kk-leftIdx];
        }

    }
}
