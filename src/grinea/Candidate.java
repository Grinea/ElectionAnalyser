package grinea;

/*
* Candidate
* Author: Owen Frere 19520500
* Date modified: 27/10/18
* Purpose: Contain information about a candidate
*/

public class Candidate
{
    //Class fields;
    private int ID;
    private String name;
    private String surname;
    private String party;
    private String division;
    private String state;
    private int votes;
    private boolean elected;
    private boolean histElected;
    private int swing;

    //Default constructor
    public Candidate(int inID, String inName, String inSurname, 
        String inParty, String inDivision, String inState, boolean inElec, 
        boolean inHist)
    {
        this.ID = inID;
        this.name = inName;
        this.surname = inSurname;
        this.party = inParty;
        this.division = inDivision;
        this.state = inState;
        this.votes = 0;
        this.elected = inElec;
        this.histElected = inHist;
        this.swing = 0;
    }

    //Accessor methods
    public int getID()
    {
        return ID;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getParty()
    {
        return party;   
    }

    public String getDivision()
    {
        return division;
    }

    public String getState()
    {
        return state;
    }

    public int getVotes()
    {
        return votes;
    }

    /*
    * getSwing
    * I: none
    * E: swing (double)
    * P: returns the percentage change in votes for the candidate
    */
    public double getSwing()
    {
        double swingVal = -100.0;

        if (votes != 0)
        {
            swingVal = ((double)votes / (double)(votes - swing) - 1) * 100;
        }

        return swingVal;
    }

    /*
    * toString
    * I: none
    * E: (string)
    * P: Returns a string representation of the state of the object
    */    
    public String toString()
    {
        double swing = getSwing();

        //convert to 2dp
        swing = (double)((int)(swing * 100.0)) / 100.0;
    
        return surname + ", " + name + " (ID: " + ID + "), Representing: " 
            + party + " in " + division + " in " + state + ". " + votes 
            + " votes(" + swing + "). E/H(" + elected + "," 
            + histElected + ")";
    }
    //End accessors

    /*
    * addVotes
    * I: inVotes (int)
    * E: none
    * P: adds votes to candidates total, used for incremental counting
    */
    public void addVotes(int inVotes)
    {
        votes += inVotes;
    }

    /*
    * voteDelta
    * I: voteDeta (int)
    * E: none
    * P: adds to the swing votes, used for incremental counting
    */  
    public void voteDelta(int inVotes)
    {
        swing += inVotes;
    }

}
