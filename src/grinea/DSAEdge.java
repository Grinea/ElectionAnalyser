package grinea;/*
* DSAEdge
* Author Owen Frere 19520500
* Used for edges in a graph theory graph
* Ref: Submitted for prac 6 in DSA
* Date: 28/10/18
*/

import java.util.Iterator;
public class DSAEdge
{  
    private String label;
    private DSAVertex start;
    private DSAVertex end;
    private double weight;
    private boolean visited;
    
    public DSAEdge(DSAVertex inStart, DSAVertex inEnd, double inWeight,
                    String inLabel)
    {
        this.start = inStart;
        this.end = inEnd;
        this.weight = inWeight;
        this.label = inLabel;
    }
   
    /*
    * getLabel
    * I: none
    * E: label (String)
    */ 
    public String getLabel()
    {
        return label;
    }
   
    /*
    * getStart
    * I: none
    * E: start (DSAVertex)
    */
    public DSAVertex getStart()
    {
        return start;
    }
   
    /*
    * getEnd
    * I: none
    * E: end (DSAVertex)
    */
    public DSAVertex getEnd()
    {
        return end;
    }
   
    /*
    * getWeight
    * I: none
    * E: weight (double)
    */
    public double getWeight()
    {
        return weight;
    }
   
    /*
    * setVisited
    * I: none
    * E: none
    */
    public void setVisited()
    {
        visited = true;
    }
   
    /*
    * clearVisited
    * I: none
    * E: none
    */
    public void clearVisited() 
    {
        visited = false;
    }
   
    /*
    * getVisited
    * I: none
    * E: visited (boolean)
    */
    public boolean getVisited() 
    {
        return visited;
    }
   
    /*
    * toString
    * I: none
    * E: (String)
    */
    public String toString() 
    {
        return label + " from " + start.getLabel() + " to " + end.getLabel() 
            + ", weight: " + weight + ", visited: " + visited;
    }

}

