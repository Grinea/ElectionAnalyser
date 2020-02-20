package grinea; /*
 * Author: Owen Frere 19520500
 * File: Filtering
 * Purppose: Provides filtering functions to ElectionAnalyser
 * Date Modified: 27/10/2018
 */
import java.util.Iterator;
public class Filtering
{

    /*
    * addFilter
    * I: states  (DSALinkedList[])
    * E: objArr (Object[])
    * P: Gets a filter selection from user, and returns the list
    *   of state requested, returns it and a string representation 
    *   of the filter.
    */
    public static Object[] addFilter(DSALinkedList[] states)
    {
        int selection;
        String filter = "";
        DSALinkedList retList = null;
        Object[] objArr = new Object[2];

        selection = Menus.stateMenu();
        if (selection != 9)
        {
            filter = " State:" + Tools.idxToState(selection - 1);
            retList = states[selection - 1];
        }

        objArr[0] = retList;
        objArr[1] = filter;

        return objArr;
    }

    /*
    * addFilter
    * I: parties (DSALinkedList[]), partyHash (DSAHashTable)
    * E: objArr (Object[])
    * P: Gets a filter selection from user, and returns the list
    *   of party requested, returns it and a string representation 
    *   of the filter. Uses the hash table to check input is in table.
    */
    public static Object[] addFilter(DSALinkedList[] parties, 
        DSAHashTable partyHash)
    {
        String temp;
        String err = "";
        String filter = "";
        boolean noFilter = false;
        int retVal = 0;
        int idx = -1;
        DSALinkedList retList = null;
        Object[] objArr = new Object[2];
        
        do
        {
            temp = InputFunctions.getString(err + "\nPlease enter party "
                + "abbreviation to filter by.\nLeave blank for no filter:");
            err = "Could not find that party\n";
            if (temp.length()== 0)
            {
                noFilter = true;
            }
            else
            {
                idx = partyHash.getIdx(temp);
            }

        } while (idx == -1 && !noFilter);

        if (!noFilter)
        {
            filter = " Party:" + temp;
            retList = parties[idx];
        }

        objArr[0] = retList;
        objArr[1] = filter;

        return objArr;
    }

    /*
    * addFilter
    * I: divisions (Division[]), divisHash (DSAHashTable)
    * E: objArr (Object[])
    * P: Gets a filter selection from user, and returns the list
    *   of party requested, returns it and a string representation 
    *   of the filter. Uses the hash table to check input is in table.
    */
    public static Object[] addFilter(Division[] divisions, 
        DSAHashTable divisHash)
    {
        String temp; 
        String filter = "";
        String err = "";
        boolean noFilter = false;
        int idx = -1;
        DSALinkedList retList = null;
        Object[] objArr = new Object[2];       
 
        do
        {
            temp = InputFunctions.getString(err + "\nPlease enter division "
                + "to filter by.\nLeave blank for no filter:");
            err = "Could not find that division\n";
            if (temp.length()== 0)
            {
                noFilter = true;
            }
            else
            {
                idx = divisHash.getIdx(temp);
            }

        } while (idx == -1 && !noFilter);

        if (!noFilter)
        {
            filter = " Division:" + temp;
            retList = divisions[idx].getCandidates();
        }

        objArr[0] = retList;
        objArr[1] = filter;

        return objArr;
    }

    /*
    * addFilteredList
    * I: lsit1 (DSALinkedList), list2 (DSALinkedList)
    * E: filteredList (DSALinkedList)
    * P: Provides a wrapper for intersectionList that handles null lists
    *   (either no filter applied lists or the initial filter list) in the
    *    case of no filter applied, this means all items intersect with 
    *   non null list
    */
    public static DSALinkedList addFilteredList(DSALinkedList list1, 
        DSALinkedList list2)
    {
        DSALinkedList filteredList;

        if (list1 == null)
        {
            filteredList = list2;
        }
        else if (list2 == null)
        {
            filteredList = list1;
        }
        else
        {
            filteredList = intersectionList(list1, list2);
        }

        return filteredList; 
    }
 
    /*
    * intersectionList
    * I: lsit1 (DSALinkedList), list2 (DSALinkedList)
    * E: intersection (DSALinkedList)
    * P: Returns a list of the intersection of 2 lists, does it by hashing 
    *   the first list and then only appending items from the second list to 
    *   the new list, and only when it finds the key already in the hash table
    */
    public static DSALinkedList intersectionList(DSALinkedList list1, 
        DSALinkedList list2)
    {
        DSAHashTable hashTable = new DSAHashTable(1511);
        DSALinkedList intersection = new DSALinkedList();
        Iterator itr;
        Candidate candi;

        itr = list1.iterator();

        //hash first list
        while (itr.hasNext())
        {
            candi = (Candidate)(itr.next());
            hashTable.insertKey("" + candi.getID());
        }

        itr = list2.iterator();

        //append to intersection list where key is already in table
        while (itr.hasNext())
        {
            candi = (Candidate)(itr.next());
            if (!hashTable.insertKey("" + candi.getID()))
            {
                intersection.insertLast(candi);
            }
        }

        return intersection;
    }
}
