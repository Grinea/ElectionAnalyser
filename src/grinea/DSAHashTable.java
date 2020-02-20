package grinea;/*
* DSAHashTable
* Author: Owen Frere 19520500
* Date Modified: 27/10/18
* Purpose: provides the function of a hash table using double hashing on 
*       string keys
*/

public class DSAHashTable
{
    //Inner class for the tuple
    private class DSAHashTuple
    {
        //class fields
        public String key;
        public int idx;

        //Default constructor
        public DSAHashTuple(String inKey, int inIdx)
        {
            this.idx = inIdx;
            this.key = inKey;
        }
    }

    //Class fields
    private DSAHashTuple[] tuples;
    private int noEntries;

    //Default constructor
    public DSAHashTable(int numElems)
    {
        this.noEntries = 0;
        this.tuples = new DSAHashTuple[numElems];
    }

    //Accessors
    public boolean isEmpty()
    {
        return (noEntries == 0);
    }

    public int getNoEntries()
    {
        return noEntries;
    }

    /*
    * getIdx
    * I: key (String)
    * E: retVal (int)
    * P: Retrieves the index value stored in the tuple with the given key
    *   Returns -1 if the key is not in the table.
    */
    public int getIdx(String key)
    {
        int primHash = primaryHash(key);
        int secHash;
        int retVal = -1;

        if (tuples[primHash] != null && !tuples[primHash].key.equals(key))
        {
            secHash = secondaryHash(key);
            while (tuples[primHash] != null && 
                !tuples[primHash].key.equals(key))
            {
                primHash += secHash;
                primHash = primHash % tuples.length;
            }
            //Not null check to short circuit the .key call in case of null
            if (tuples[primHash] != null && tuples[primHash].key.equals(key))
            {
                retVal = tuples[primHash].idx;      
            }

        }
        else if (tuples[primHash] != null)
        {
            retVal = tuples[primHash].idx;
        }

        return retVal;
    }
    //End accessors

    /*
    * insertKey
    * I: key (String)
    * E: inserted (boolean)
    * P: Hashes a string key and inserts a tuple into the hash table
    *   collisions are handled by double hashing. Returns a boolean
    *   indicating if the key was inserted, as it will not be if already
    *   existing in the table
    */
    public boolean insertKey(String key)
    {
        int primHash = primaryHash(key);
        int secHash;
        boolean inserted = false;

        if (tuples[primHash] != null && !tuples[primHash].key.equals(key))
        {
            secHash = secondaryHash(key);
            while (tuples[primHash] != null && 
                !tuples[primHash].key.equals(key))
            {
                primHash += secHash;
                primHash = primHash % tuples.length;
            }
        }

        //only inserts if key not existing
        if (tuples[primHash] == null)
        {
            tuples[primHash] = new DSAHashTuple(key, noEntries);
            noEntries++;
            inserted = true;
        }

        return inserted;
    }

    /*
    * primaryHash
    * I: key (String)
    * E: hashIdx (int)
    * P: Hashes a string key
    * Reference: DSA Lecture slide 71, added negative handling
    */
    public int primaryHash(String key)
    {
        int hashIdx = 0;

        for (int ii = 0; ii < key.length(); ii++)
        {
            hashIdx = (33 * hashIdx) + key.charAt(ii);
        }

        hashIdx = hashIdx % tuples.length;

        /*This handles negative hash results. Rather than taking absolute
        values I add the table length. I figure that -x is x items less than
        a full rotation instead of x into the next rotation. Not sur if this
        will affect he prime properties, but I think absolute would*/
        if (hashIdx < 0)
        {
            hashIdx += tuples.length;
        }
        return hashIdx;
    }

    /*
    * primaryHash
    * I: key (String)
    * E: hashIdx (int)
    * P: Hashes a string key
    * Ref: DSA Lecture slide 72, modified to string and negative handling
    */
    public int secondaryHash(String key)
    {
        int a = 63689;
        int b = 378551;
        int hashIdx = 0;

        for (int ii = 0; ii < key.length(); ii++)
        {
            hashIdx = (hashIdx * a) + key.charAt(ii);
            a *= b;
        }

        hashIdx = hashIdx % tuples.length;
        
        /*This handles negative hash results. Rather than taking absolute
        values I add the table length. I figure that -x is x items less than
        a full rotation instead of x into the next rotation. Not sur if this
        will affect he prime properties, but I think absolute would*/
        if (hashIdx < 0)
        {
            hashIdx += tuples.length;
        }
        return hashIdx;
    }

}
