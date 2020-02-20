package grinea; /*
 * Author: Owen Frere 19520500
 * File: FileIO
 * Purppose: Provides file input and output functions to ElectionAnalyser
 * Date Modified: 27/10/2018
 */

import java.io.*;
import java.util.Iterator;
public class FileIO
{
    /*
    * loadCandidateArray
    * I: none
    * E: candiArr (candidate[])
    * P: takes a .csv of all the candidates in the election and
    *   creates objects for the candidates and reutrns an array of them
    */
    public static Candidate[] loadCandidateArray()
        throws IOException
    {
        FileReader fRdr;
        BufferedReader bRdr;
        String candidateData = "csvs/HouseCandidatesDownload-20499.csv";
        DSALinkedList candiList = new DSALinkedList();
        String line = null;
        String split[];
        Candidate tempCandi;
        Candidate candiArr[];

        fRdr = new FileReader(candidateData);
        bRdr = new BufferedReader(fRdr);
        
        //skip header
        for (int ii = 0; ii < 3; ii++)
        {
            line = bRdr.readLine();
        }
        
        while (line != null)
        {
            /*Cleans up the csv so the comma in the SFR party doesn't 
                break the file io*/
            line = line.replace("\"Shooters, Fishers and Farmers\"", 
                "Shooters Fishers and Farmers");
            split = line.split(",");

            //handles the candidates with no party abbreviation
            if (split[3].equals(""))
            {
                split[3] = "NIL";
            }

            try
            {
                tempCandi = new Candidate(Integer.parseInt(split[5]), 
                split[7], split[6], split[3], split[2], split[0], 
                toBoolean(split[8]), toBoolean(split[9]));

                candiList.insertLast(tempCandi);
            }
            catch (NumberFormatException e)
            {
                //ignore line
            }
            
            line = bRdr.readLine();
        }

        candiArr = makeArray(candiList);

        return candiArr;
    }

    /*
    * toBoolean
    * I: val (String)
    * E: retVal (boolean)
    * P: converts "Y" to true, otherwise false
    */
    public static boolean toBoolean(String val)
    {
        boolean retVal = false;

        if (val.equals("Y"))
        {
            retVal = true;
        }

        return retVal;
    }

    /*
    * loadVoteData
    * I: division (divisionp[), divisHash (DSAHashTable)
    * E: none
    * P: Wrapper for calling the load function on all the files
    */

    public static void loadVoteData(Division[] divisions, 
        DSAHashTable divisHash)
        throws IOException
    {
        String lead = "csvs/HouseStateFirstPrefsByPollingPlaceDownload"
            +"-20499-";
        String[] tail = {"ACT", "NSW", "NT", "QLD", "SA", "TAS", "VIC", "WA"};
    
        for (int ii = 0; ii < tail.length; ii++)
        {
            loadVotesFile(lead + tail[ii] + ".csv", divisions, divisHash);
        }
    }

    /*
    * loadVotesFile
    * I: fileName(string), divisions (Division[]), divisHash DSAHashTable)
    * E: none
    * P: Load the vote and swing data into the candidates and divisions
    */
    private static void loadVotesFile(String fileName, Division[] divisions,
        DSAHashTable divisHash)
        throws IOException
    {
        FileReader fRdr;
        BufferedReader bRdr;
        String line = null;
        String divName, party;
        String[] split;
        int votes, voteDelta, ID;
        Division div;
        Iterator itr;
        boolean found;
        Candidate candi;

        fRdr = new FileReader(fileName);
        bRdr = new BufferedReader(fRdr);
 
        //skip header
        for (int ii = 0; ii < 3; ii++)
        {
            line = bRdr.readLine();
        }
        
        
        while (line != null)
        {
            /*Cleans up the csv so the comma in the SFR party doesn't 
                break the file io*/
            line = line.replace("\"Shooters, Fishers and Farmers\"", 
                "Shooters Fishers and Farmers");
            split = line.split(",");       

            //extract data
            try
            {
                divName = split[2];
                votes = Integer.parseInt(split[13]);
                voteDelta = (int)Math.round((double)votes / (100.0 + 
                    Double.parseDouble(split[14])));
                party = split[11];
                ID = Integer.parseInt(split[5]);

                //find division
                div = divisions[divisHash.getIdx(divName)];
                found = false;         

                //skip informals
                if (!party.equals(""))
                {
                    div.addVotes(votes);
                    itr = div.getCandidates().iterator();
                    while (itr.hasNext() && found == false)
                    {
                        candi = (Candidate)itr.next();
                        if (candi.getID() == ID)
                        {
                            candi.addVotes(votes);
                            candi.voteDelta(voteDelta);
                            found = true;
                        }
                    }
                }
            }
            catch (NumberFormatException e)
            {
                //skip line
            }
            line = bRdr.readLine();
        }

    }

    /*
    * count
    * I: list (DSALinkedList)
    * E: int
    * P: counts the number of items in a linked list
    */
    public static int count(DSALinkedList list)
    {
        int count = 0;
        Iterator Itr = list.iterator();
        while (Itr.hasNext())
        {
            count++;
            Itr.next();
        }

        return count;
    }

    /*
    * makeArray
    * I: candiList (DSALinkedList)
    * E: candiArr (candidate[])
    * P: turns a linked list of candidates into an array
    */
    public static Candidate[] makeArray(DSALinkedList candiList)
    {
        Candidate[] candiArr;
        int candiCount = count(candiList);
        Iterator itr = candiList.iterator();
        int ii;

        candiArr = new Candidate[candiCount];

        ii = 0;
        while (itr.hasNext())
        {
            candiArr[ii] = (Candidate)itr.next();
            ii++;
        }

        return candiArr;
    }

    /*
    * makeStringArray
    * I: list (DSALinkedList)
    * E: stringArr (String[])
    * P: converts a string linked list to an array
    */
    public static String[] makeStringArray(DSALinkedList list)
    {
        String[] stringArr;
        int strCount = count(list);
        Iterator itr = list.iterator();
        int ii;

        stringArr = new String[strCount];

        ii = 0;
        while (itr.hasNext())
        {
            stringArr[ii] = (String)itr.next();
            ii++;
        }

        return stringArr;
    }

    /*
    * countOccurences
    * I: partyHash (DSAHashTable), divisHash(DSAHashTable), 
    *   candidates (candidate[])
    * E: counts (int[])
    * P: counts the unique occurences of each party and division, by hashing 
    *   their keys
    */
    public static int[] countOccurences(DSAHashTable partyHash, 
        DSAHashTable divisHash, Candidate[] candidates)
    {
        int[] counts = new int[2];
        for (int ii = 0; ii < candidates.length; ii++)
        {
            partyHash.insertKey(candidates[ii].getParty());
            divisHash.insertKey(candidates[ii].getDivision());
        }

        counts[0] = partyHash.getNoEntries();
        counts[1] = divisHash.getNoEntries();

        return counts;
    }

    /*
    * printReport
    * I: objArr (Object[]), title (string), filer (string)
    * E: none
    * P: Prints the filter and all toString's of a supplied array to a file in
    *   current directory (adds -x to the title to avoid collisions)
    */
    public static void printReport(Object[] objArr, String title, 
        String filter)
    {
        boolean fileExists;
        String fileName, userTitle;
        File report;
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        int ii = 1; 
       
        userTitle = InputFunctions.getString("\nEnter title for report:");
        
        if (userTitle.matches("[a-zA-Z0-9_ -]*"))
        {
            title = userTitle;
        }
        else
        {
            System.out.println("\nInvalid characters in filename.\n"
                + "Using default.");
        }
        
        fileName = "reports/" + title + ".txt";   
 
        report = new File("./" + fileName);
        

        //find a file name that is free
        while (report.exists())
        { 
            fileName = title + "-" + ii + ".txt";
            report = new File("./" + fileName);
            ii++;
        }

        try
        {
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);

            pw.println(filter);
            for (int jj = 0; jj < objArr.length; jj++)
            {
                pw.println(objArr[jj].toString());
            }
            
            pw.close();
            fileStrm.close();
            System.out.println("\nSaved as " + fileName);
        }
        catch (IOException e)
        {
            System.out.println("Error saving report");
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException e2)
                {
                }
            }
        }
    }
}


