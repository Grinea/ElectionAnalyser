package grinea; /*
 * Author: Owen Frere 19520500
 * File: ElectionAnalyser
 * Purppose: Main file for the election analysing program
 * Date Modified: 27/10/2018
 */

import java.io.IOException;
import java.util.Iterator;
public class ElectionAnalyser
{  
    
    /*
    * main
    * Imports: args (String[])
    * Exports: none
    * Purpose: Main control flow for the election analysing program
    */
    public static void main(String[] args)
    {

        DSALinkedList candiList = new DSALinkedList();
        Candidate[] candidates;
        Division[] divisions;
        DSALinkedList[] parties;
        DSALinkedList[] states = new DSALinkedList[8];
         
        /*Defaults for the HashTables are 200% expected values,
            Hash Table signoff was originally before due date,
            so using cut down versions as I didn't have time to make
            resizing ones for the assignment*/
        DSAHashTable partyHash = new DSAHashTable(103);
        DSAHashTable divisHash = new DSAHashTable(307);
        int selection = 0;
        int counts[];

        try
        {
            candidates = FileIO.loadCandidateArray();

            counts = FileIO.countOccurences(partyHash, divisHash, candidates);

            for (int ii = 0; ii < 8; ii++)
            {
                states[ii] = new DSALinkedList();
            }

            parties = new DSALinkedList[counts[0]];
            for (int ii = 0; ii < counts[0]; ii++)
            {
                parties[ii] = new DSALinkedList(); 
            }

            divisions = new Division[counts[1]];

            processArray(candidates, parties, partyHash, divisions, 
                divisHash, states);

            FileIO.loadVoteData(divisions, divisHash);
    
            Sorts.mSSurname(candidates);
 
            do
            {
                selection = Menus.mainMenu();
                switch (selection)
                {
                    case 1:
                        listNominees(states, parties, partyHash, divisions, 
                            divisHash, candidates);
                        break;
                    case 2:
                        searchNominees(states, parties, partyHash, 
                            candidates);
                        break;
                    case 3:
                        listByMargin(divisions, partyHash); 
                        break;
                    case 4:
                        //itineraryByMargin(candidates);
                        System.out.println("Not implemented yet");
                        break;
                    case 5:
                        break;
                }
            } while (selection != 5);     
 
        }
        catch (IOException e)
        {
            System.out.println("Error reading candidate file");
        }

    }
    
    /*
    * processArray
    * I: candidates (Candidate[]), parties (DSALinkedList[]), 
    *       partyHash (DSAHashTable), divisions (Division[]), 
    *       divisHash (DSAHashTable), states (DSALinkedList[]))
    * E: none
    * P: Appends the candidates into the state/party/division lists they are 
    *   relevant to, creating the division object if necessary
    */
    private static void processArray(Candidate[] candidates, 
        DSALinkedList[] parties, DSAHashTable partyHash, 
        Division[] divisions, DSAHashTable divisHash, DSALinkedList[] states)
    {
        Candidate candi;
        int divIdx;

        for (int ii = 0; ii < candidates.length; ii++)
        {
            candi = (Candidate)candidates[ii];
            
            states[Tools.stateToIdx(candi.getState())].insertLast(candi);
            
            parties[partyHash.getIdx(candi.getParty())].insertLast(candi);

            divIdx = divisHash.getIdx(candi.getDivision());

            if (divisions[divIdx] == null)
            {
                divisions[divIdx] = new Division(candi.getDivision());
            }
            
            divisions[divIdx].addCandidate(candi);
        }

    }
 
    /*
    * listNominess()
    * I: states (DSALinkedList[]), parties (DSALinkedList[]), 
    *      partyHash (DSAHashTable), divisions (Division[]), 
    *      divisHash (DSAHashTable), candidates (Candidate[])
    * E: none
    * P: Begins the filtering process for the options of state/party/division,
    *      offers sorts, applies the sorts, displays results and offers to 
    *      log results
    */
    private static void listNominees(DSALinkedList[] states, 
        DSALinkedList[] parties, DSAHashTable partyHash, Division[] divisions,
        DSAHashTable divisHash, Candidate[] candidates)
    {
        //load objects
        Object[] objArr =  filtNominees(states, parties, partyHash, divisions,
            divisHash, candidates);
        Candidate[] candiArr = (Candidate[])objArr[0];
        String filter = (String)objArr[1];

        if (candiArr == null)
        {
            System.out.println("There are no matches to your criteria");
        }
        else
        {
            candiArr = listSort(candiArr);

            System.out.println("\nNominees matching " + filter);
            for (int ii = 0; ii < candiArr.length; ii++)
            {
                System.out.println(candiArr[ii].toString());
            }
        }

        if (Menus.yesNoMenu("Save a report") == 1)
        {
            FileIO.printReport(candiArr, "nomineeListReport", filter);
        }

    }

    /*
    * filtNominees()
    * I: states (DSALinkedList[]), parties (DSALinkedList[]), 
    *       partyHash (DSAHashTable), divisions (Division[]), 
    *       divisHash (DSAHashTable), candidates (Candidate[])
    * E: Object[]
    * P: Gets user imput on filtering options, and applies them to the lists 
    *       to get the intersection, returns an array of the results
    */
    private static Object[] filtNominees(DSALinkedList[] states, 
        DSALinkedList[] parties, DSAHashTable partyHash, Division[] divisions,
        DSAHashTable divisHash, Candidate[] candidates)
    {
        DSALinkedList filtered = null;
        String filter = "filters:";
        Object[] objArr;
        Candidate[] candiArr = null;    

        /*if state filtering selected gets intersection with list
            and current filtered list*/
        objArr = Filtering.addFilter(states);
        if (((String)objArr[1]).length() != 0)
        {
            filter = filter + (String)objArr[1];
            filtered = Filtering.addFilteredList((DSALinkedList)objArr[0], 
                filtered);
        }
        
        /*if party filtering selected gets intersection with list
            and current filtered list*/
        objArr = Filtering.addFilter(parties, partyHash);
        if (((String)objArr[1]).length() != 0)
        {
            filter = filter + (String)objArr[1];
            filtered = Filtering.addFilteredList((DSALinkedList)objArr[0], 
                filtered);
        }  

        /*if division filtering selected gets intersection with list
            and current filtered list*/
        objArr = Filtering.addFilter(divisions, divisHash);
        if (((String)objArr[1]).length() != 0)
        {
            filter = filter + (String)objArr[1];
            filtered = Filtering.addFilteredList((DSALinkedList)objArr[0], 
                filtered);
        }       
      
        //returns entire array if no filters selected
        if (filter.length() == 8)
        {
            candiArr = candidates;    
        }
        else  if (!filtered.isEmpty())
        {   
            candiArr = FileIO.makeArray(filtered);        
        }

        objArr = new Object[2];
        objArr[0] = candiArr;
        objArr[1] = filter;

        return objArr;
    }
      
    /*
    * searchNominees()
    * I: states (DSALinkedList[]), parties (DSALinkedList[]) , 
    *   partyHash (DSAHashTable), candidates (Candidate[])
    * E: none
    * P: Begins the filtering process for the options of state/party, 
    *   displays results and offers to log results
    */
    private static void searchNominees(DSALinkedList[] states, 
        DSALinkedList[] parties, DSAHashTable partyHash, 
        Candidate[] candidates)
    {
        //load objects
        String startsWith = InputFunctions.getString("\nWhat start of a "
            + "surname are you looking for?");
        Object[] objArr = filtNominees(states, parties, partyHash, 
            candidates, startsWith.toUpperCase());
        Candidate[] candiArr = (Candidate[])objArr[0];
        String filter = (String)objArr[1];
        
        if (candiArr == null)
        {
            System.out.println("There are no matches to your criteria");
        }
        else
        {
            System.out.println("\nNominees matching " + filter);
            for (int ii = 0; ii < candiArr.length; ii++)
            {
                System.out.println(candiArr[ii].toString());
            }
        }

        if (Menus.yesNoMenu("Save a report") == 1)
        {
            FileIO.printReport(candiArr, "nomineeSearchReport", filter);
        }

    }

    /*
    * filtNominees()
    * I: states (DSALinkedList[]), parties (DSALinkedList[]), 
        partyHash (DSAHashTable), candidates (Candidate[], startsWith (string)
    * E: objArr (Object[])
    * P: Gets user imput on filtering options, and applies them to the lists 
        to get the intersection, returns an array of the results
    */
    private static Object[] filtNominees(DSALinkedList[] states, 
        DSALinkedList[] parties, DSAHashTable partyHash, 
        Candidate[] candidates, String startsWith)
    {
        DSALinkedList filtered = null;
        String filter = "filters:";
        Object[] objArr;
        Candidate[] candiArr = null;
        DSALinkedList list = new DSALinkedList();

        objArr = Filtering.addFilter(states);
        if (((String)objArr[1]).length() != 0)
        {
            filter = filter + (String)objArr[1];
            filtered = Filtering.addFilteredList((DSALinkedList)objArr[0], 
                filtered);
        }
        
        objArr = Filtering.addFilter(parties, partyHash);
        if (((String)objArr[1]).length() != 0)
        {
            filter = filter + (String)objArr[1];
            filtered = Filtering.addFilteredList((DSALinkedList)objArr[0], 
                filtered);
        }       
     
        if (filter.length() == 8)
        {
            candiArr = candidates;    
        }
        else  if (!filtered.isEmpty())
        {   
            candiArr = FileIO.makeArray(filtered);        
        }

        for (int ii = 0; ii < candiArr.length; ii++)
        {
            if (candiArr[ii].getSurname().matches("^" + startsWith + ".*"))
            {
               list.insertLast(candiArr[ii]);
            }
        }

        candiArr = FileIO.makeArray(list);

        objArr = new Object[2];
        objArr[0] = candiArr;
        objArr[1] = filter;

        return objArr;

    }

    /*
    * listByMargin
    * I: divisions (Division[]);
    * E: none
    * P: Lists the seats a selected party is within a selected margin of
    */
    public static void listByMargin(Division[] divisions, 
        DSAHashTable partyHash)
    {
        String party;
        String err = "";
        String divMargin;
        int idx = -1;
        int partyTotal = 0;
        double margin, partyMargin;
        Iterator itr;
        DSALinkedList divList = new DSALinkedList();
        String[] stringArr;
        int useDefault = 0;

        do
        {
            party = InputFunctions.getString(err + "\nPlease enter party "
                + "abbreviation to filter by:");
            err = "Could not find that party\n";
            idx = partyHash.getIdx(party);
            
        } while (idx == -1);        

        useDefault = Menus.yesNoMenu("Use default margin of 6%");

        if (useDefault == 1)
        {
            margin = 6.0;
        }
        else
        {
            margin = InputFunctions.getBoundDouble(0.0, 100.0, 
            "\nWhat margin %?");
        }

        for (int ii = 0; ii < divisions.length; ii++)
        {
            partyMargin = divisions[ii].getMargin(party);

            if (Math.abs(partyMargin) < margin)
            {
                partyMargin = (double)(Math.round(partyMargin * 100.0)) 
                    / 100.0;
                divMargin = divisions[ii].getName() + ", " + partyMargin;
                divList.insertLast(divMargin);
            }
        }

        itr = divList.iterator();
        while (itr.hasNext())
        {
            System.out.println((String)itr.next());
        }

        if (Menus.yesNoMenu("Save a report") == 1)
        {
            stringArr = FileIO.makeStringArray(divList);
            FileIO.printReport(stringArr, "listByMargin", "Party:" + party
                + "Margin:" + margin);
        }

    }

    /*
    * listSort()
    * I: candidates (Candidate[]
    * E: candiArr (Candidate[])
    * P: gets user input on the multi level sorting options, and applies 
    *   them in reverse priority order with a stable sort
    */
    private static Candidate[] listSort(Candidate[] candiArr)
    {
        int[] sorts = new int[4];

        sorts[0] = Menus.yesNoMenu("Sort by Surname");
        sorts[1] = Menus.yesNoMenu("Sort by State");
        sorts[2] = Menus.yesNoMenu("Sort by Party");
        sorts[3] = Menus.yesNoMenu("Sort by Division");

        if (sorts[3] == 1)
        {
            Sorts.mSDivision(candiArr);
        }

        if (sorts[2] == 1)
        {
            Sorts.mSParty(candiArr);
        }

        if (sorts[1] == 1)
        {
            Sorts.mSState(candiArr);
        }

        if (sorts[0] == 1)
        {
            Sorts.mSSurname(candiArr);
        }
        
        return candiArr;
    }
}
