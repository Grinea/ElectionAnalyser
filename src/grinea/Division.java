package grinea;/*
* Division
* Author: Owen Frere 19520500
* Date modified 27/10/18
* Purpose: Contain information about a division, including a linked list of 
*       references to the candidates in the division
*/

import java.util.Iterator;
public class Division
{
    //Class fields;
    private String name;
    private int voteTotal;
    private DSALinkedList candidates;

    //Default constructor
    public Division(String inName)
    {
        this.name = inName;
        this.candidates = new DSALinkedList();
        this.voteTotal = 0;
    }

    //Accessors
    public String getName()
    {
        return name;
    }

    public DSALinkedList getCandidates()
    {
        return candidates;
    }

    /*
    * getMargin
    * I: party (String)
    * E: (double)
    * P: Takes a party name and counts up the votes
    *   of all the representatives of that party in the division
    *   to work out if it is marginal
    */
    public double getMargin(String party)
    {
        Iterator itr = candidates.iterator();
        int partyVotes = 0;
        Candidate candi = null;

        //protection against div0
        if (voteTotal == 0)
        {
            throw new IllegalArgumentException("DIV0");
        }

        while (itr.hasNext())
        {
            candi = (Candidate)itr.next();
            if (candi.getParty().compareTo(party) == 0)
            {
                partyVotes += candi.getVotes();
            }
        }

        return ((double)partyVotes / (double)voteTotal)*100 - 50;
    }
    //End Accessors

    /*
    * addCandidate
    * I: inCadi (Candidate)
    * E: none
    * P: Adds a reference to the candidate in the list of candidates in the 
    *   division
    */
    public void addCandidate(Candidate inCandi)
    {
        candidates.insertLast(inCandi);
    }

    /*
    * addVotes
    * I: inVotes (int)
    * E: none
    * P: Adds votes to the total vote count for division
    */
    public void addVotes(int inVotes)
    {
        voteTotal += inVotes;
    }
}
