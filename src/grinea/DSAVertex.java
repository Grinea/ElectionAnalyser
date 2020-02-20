package grinea;/*
* DSAVertex
* Author Owen Frere 19520500
* Used for vertex in a graph theory graph
* Ref: Submitted for prac 6 in DSA
* Date: 28/10/18
*/

import java.util.Iterator;
public class DSAVertex 
{
    //class fields
    private String label;
    private Object value;
    private boolean visited;
    private GraphList sourceList;
    private GraphList sinkList;

    public DSAVertex(String inLabel, Object inValue) 
    {
        this.label = inLabel;
        this.value = inValue;
        this.visited = false;
        this.sourceList = new GraphList();
        this.sinkList = new GraphList();
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
    * getValue
    * I: none
    * P: value (object)
    */
    public Object getValue() 
    {
        return value;
    }

    /*
    * toString
    * I: none
    * E: (String)
    */
    public String toString() 
    {
        return "label:" + label + " visited: " + visited;
    }

    /*
    * getSources
    * I: none
    * E: adjacentList (GraphList)
    * P: gets a list of all the vertexs that the edges originating from this
        vertex terminate at
    */
    public GraphList getSources() 
    {
        GraphList adjacentList = new GraphList();
        Iterator itr = sourceList.iterator();
        DSAEdge edge;

        while (itr.hasNext()) 
        {
            edge = (DSAEdge)itr.next();
            AddSorted.addSorted(adjacentList, edge.getEnd());
        }

        return adjacentList;
    }

    /*
    * getSinks
    * I: none
    * E: adjacentList (GraphList)
    * P: gets a list of all the vertexs that the edges terminating at this
        vertex originate from
    */   
    public GraphList getSinks()
    {
        GraphList adjacentList = new GraphList();
        Iterator itr = sinkList.iterator();
        DSAEdge edge;

        while (itr.hasNext()) 
        {
            edge = (DSAEdge)itr.next();
            AddSorted.addSorted(adjacentList, edge.getStart());
        }

        return adjacentList;
    }

   /*
    * getSourceEs
    * I: none
    * E: (GraphList)
    * P: gets a list of all the edges originaling at this vertex
    */  
    public GraphList getSourceEs() 
    {
        return sourceList;
    }

   /*
    * getSinkEs
    * I: none
    * E: (GraphList)
    * P: gets a list of all the edges terminating at this vertex
    */  
    public GraphList getSinkEs() 
    {
        return sinkList;
    }

    /*
    * addSourceEdge
    * I: none
    * E: (GraphList)
    * P: adds to list of all the edges terminating at this
    */  
    public void addSourceEdge(DSAEdge inEdge) 
    {
        AddSorted.addSorted(sourceList, inEdge);
    }

    /*
    * addSinkEdge
    * I: none
    * E: (GraphList)
    * P: adds to list of all the edges terminating at this
    */  
    public void addSinkEdge(DSAEdge inEdge) 
    {
        AddSorted.addSorted(sinkList, inEdge);
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

}

