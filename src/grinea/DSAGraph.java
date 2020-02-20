package grinea;/*
* DSAGraph
* Author: Owen Frere 19520500
* Ref previously submitted for DSA prac6
* Date: 28/10/18
*/

import java.util.Iterator;
import java.io.IOException;
public class DSAGraph
{
    //class fields
    private GraphList verticies;

    public DSAGraph()
    {
        this.verticies = new GraphList();
    }

    /*
    * addVertex
    * I: inLabel (String), inValue (Object)
    * E: none
    * P: Adds a vertex to list in sorted order
    */
    public void addVertex(String inLabel, Object inValue)
    {
        AddSorted.addSorted(verticies, new DSAVertex(inLabel, inValue));
    }

    /*
    * loadTestFile
    * I: fileName
    * E: none
    * P: loads graph from file
    */
    public void loadFile(String fileName)
    {
        GraphList[] lists;
        GraphList verts, edges;
        Iterator itr, itr2;
        DSAVertex source, sink;
        
        try
        {
            this.verticies = GraphFileIO.loadFile(fileName);
        }
        catch (IOException e)
        {
            System.out.println("File could not be loaded.");
        }

    }

    /*
    * addVertex
    * I: inVert (DSAVertex)
    * E: none
    * P: add a vertex to list in sorted order
    */
    public void addVertex(DSAVertex inVert)
    {
        AddSorted.addSorted(verticies, inVert);
    }

    /*
    * addEdge
    * I: vert1 vert2 (DSAEdge), inWeight(double), inLabel (String)
    * E: none
    * P: add an edge to its source and sink edge lists
    */
    public void addEdge(DSAVertex vert1, DSAVertex vert2, double inWeight, 
                        String inLabel)
    {
        DSAEdge newEdge;

        newEdge = new DSAEdge(vert1, vert2, inWeight, inLabel);

        vert1.addSourceEdge(newEdge);
        vert2.addSinkEdge(newEdge);
    }

    public void addEdge(DSAEdge edge)
    {
        addEdge(edge.getStart(), edge.getEnd(), edge.getWeight(), edge.getLabel());
    }

    /*
    * getVertexCount
    * I: none
    * E: count (int)
    * P: count number of vertexes in list
    */
    public int getVertexCount()
    {
        int count = 0;
        Iterator itr = verticies.iterator();

        while(itr.hasNext())
        {
            itr.next();
            count++;
        }

        return count;
    }

    /*
    * getEdgeCount
    * I: none
    * E: count (int)
    * P: count edges in vertex's lists
    */
    public int getEdgeCount()
    {
        int count = 0;
        Iterator itrV = verticies.iterator();
        Iterator itrE;
        DSAVertex vert;

        while(itrV.hasNext())
        {
            vert = (DSAVertex)itrV.next();
            itrE = vert.getSources().iterator();

            while(itrE.hasNext())
            {
                itrV.next();
                count++;
            }
        }

        return count;
    }

    /*
    * getVertex
    * I: inLabel (String)
    * E: retVert (DSAVertex)
    * P: returns first vertex found by name
    */
    public DSAVertex getVertex(String inLabel)
    {
        int comparison;
        DSAVertex retVert = null;
        boolean found = false;
        Iterator itr = verticies.iterator();

        while(itr.hasNext() && found == false)
        {
            retVert = (DSAVertex)itr.next();

            comparison = retVert.getLabel().compareTo(inLabel);

            if (comparison > 0)
            {
                throw new IllegalArgumentException("Not in list");
            }
            else if (comparison == 0)
            {
                found = true;
            }
        }

        if (!(found))
        {
            throw new IllegalArgumentException("Not in list");
        }

        return retVert;
    }

    /*
    * adjacent
    * I: inVert (DSAVertex)
    * E: retList (GraphList)
    * P: Get a list of all the vertexs adjacent to provided vertex
    */
    public GraphList adjacent(DSAVertex inVert)
    {
        GraphList retList = new GraphList();
        Iterator itr = inVert.getSources().iterator();
        DSAEdge edge;
        
        while (itr.hasNext())
        {
            edge = (DSAEdge)itr.next();
            AddSorted.addSorted(retList, edge.getEnd());
        }

        return retList;
    }

    /*
    * adjacentE 
    * I: inVert (DSAVertex)
    * E: retList (GraphList)
    * P: returns a list of all edges with supplied vertex as source
    */
    public GraphList adjacentE(DSAVertex inVert)
    {
        GraphList retList = new GraphList();
        Iterator itr = inVert.getSources().iterator();

        while (itr.hasNext())
        {
            AddSorted.addSorted(retList, (DSAEdge)itr.next());
        }

        return retList;
    }

    /*
    * outDegree
    * I: inVert (DSAVertex)
    * E: count (int)
    * P: gets the count of edges originating at supplied vertex
    */
    public int outDegree(DSAVertex inVert)
    {
        Iterator itr = inVert.getSources().iterator();
        int count = 0;

        while (itr.hasNext())
        {
            itr.next();
            count++;
        }

        return count;
    }

    /*
    * inDegree 
    * I: inVert (DSAVertex)
    * E: count (int)
    * P: gets count of all edges ending at supplied vertex
    */
    public int inDegree(DSAVertex inVert)
    {
        Iterator itr = inVert.getSinks().iterator();
        int count = 0;

        while (itr.hasNext())
        {
            itr.next();
            count++;
        }

        return count;
    }

    /*
    * isAdjacent
    * I: vert1 vert2 (DSAVertex)
    * E: found (boolean)
    * P: returns a boolean of if you can move from vert1 to vert2 directly
    */
    public boolean isAdjacent(DSAVertex vert1, DSAVertex vert2)
    {
        Iterator itr = vert1.getSources().iterator();
        boolean found = false;
        DSAEdge edge = null;

        while (itr.hasNext() && found == false)
        {
            edge = (DSAEdge)itr.next();
            if(edge.getEnd() == vert2)
            {
                found = true;
            }
        }

        return found;
    }

    /*
    * displayList
    * I: none
    * E: none
    * P: prints graph's adjacency list
    */
    public void displayList()
    {
        DSAVertex vert;
        DSAEdge edge;
        Iterator itrV = verticies.iterator();
        Iterator itrE;

        if (itrV.hasNext())
        {
            while (itrV.hasNext())
            {
                vert = (DSAVertex)itrV.next();
                System.out.print(vert.getLabel() + ": ");
                itrE = vert.getSourceEs().iterator();
                while (itrE.hasNext())
                {
                    edge = (DSAEdge)itrE.next();
                    System.out.print(edge.getEnd().getLabel());
                }
                System.out.println();
            }
        }
        else
        {
            System.out.println("Nothing to display.");
        }

    }

    /*
    * displayMatrix
    * I: none
    * E: none
    * P: prints graph's adjacency matrix
    */
    public void displayMatrix()
    {
        int noVerts = 0;
        int[][] matrix;
        String[] lblMatrix;
        Iterator itrV = verticies.iterator();
        DSAVertex vert;

        noVerts = getVertexCount();

        if (noVerts == 0)
        {
            System.out.println("Nothing to display.");
        }
        else
        {
            matrix = new int[noVerts][noVerts];
            lblMatrix = new String[noVerts];
            System.out.println("\nVertex key:");
            for (int ii = 0; ii < noVerts; ii++)
            {
                vert = (DSAVertex)itrV.next();
                lblMatrix[ii] = vert.getLabel();
                System.out.println(vert.getLabel() + " = " + (char)(ii + 65));
            }
            System.out.println();

            fillMatrix(matrix, lblMatrix, noVerts);

            System.out.print(" ");
            for (int ii = 0; ii < noVerts; ii++)
            {
                System.out.print(" " + lblMatrix[ii]);
            }
            System.out.print("\n");

            for (int ii = 0; ii < noVerts; ii++)
            {
                System.out.print(lblMatrix[ii]);
                for (int jj = 0; jj < noVerts; jj++)
                {
                    System.out.print(" " + matrix[ii][jj]);
                }
                System.out.println();
            }
        }

    }
    
    /*
    * breadthFirst
    * I: none
    * E: none
    * P: prints a breadth first search
    */
    public void breadthFirst()
    {
        GraphList queue = new GraphList();
        DSAVertex vert1 = null, vert2 = null;
        Iterator itr;

        if (verticies.isEmpty())
        {
            System.out.println("Empty Graph");
        }
        else
        {
            clearVertVisited();
            System.out.println();           
 
            queue.insertLast(verticies.peekFirst());
            ((DSAVertex)verticies.peekFirst()).setVisited();

            while (!(queue.isEmpty()))
            {
                vert1 = (DSAVertex)queue.removeFirst();
                System.out.print(vert1.getLabel() + " ");
             
                if (!(vert1.getSources().isEmpty()))
                {
                    itr = vert1.getSources().iterator();
                    while (itr.hasNext())
                    {
                        vert2 = (DSAVertex)itr.next();
                        if (!(vert2.getVisited()))
                        {
                            queue.insertLast(vert2);
                            vert2.setVisited();
                        } 
                    }
                }
            }
        }        
        System.out.println();
    }

    /*
    * depthFirst
    * I: none
    * E: none
    * P: prints a depth first search
    */
    public void depthFirst()
    {
        GraphList stack = new GraphList();
        DSAVertex vert1 = null, vert2 = null;
        Iterator itr;

        if (verticies.isEmpty())
        {
            System.out.println("Empty Graph");
        }
        else
        {
            clearVertVisited();
            System.out.println();

            stack.insertLast(verticies.peekFirst());
            ((DSAVertex)verticies.peekFirst()).setVisited();

            while (!(stack.isEmpty()))
            {
                vert1 = (DSAVertex)stack.removeLast();
                System.out.print(vert1.getLabel() + " ");

                if (!(vert1.getSources().isEmpty()))
                {
                    itr = vert1.getSources().iterator();
                    while (itr.hasNext())
                    {
                        vert2 = (DSAVertex)itr.next();
                        if (!(vert2.getVisited()))
                        {
                            stack.insertLast(vert2);
                            vert2.setVisited();
                        }
                    }
                }
            }
        }
        System.out.println();
    } 

    /*
    * fillMatrix
    * I: matrix (int[][]), lblMatrix (String[]), noVerts (int)
    * E: none
    * P: fills adjacency matrix for printing
    */
    private void fillMatrix(int[][] matrix, String[] lblMatrix, int noVerts)
    {
        Iterator itrV = verticies.iterator();
        Iterator itrE;
        String lbl;
        int jj;
        boolean found;
        DSAVertex vert;

        itrV = verticies.iterator();
        for (int ii = 0; ii < noVerts; ii++)
        {
            vert = (DSAVertex)itrV.next();
            itrE = vert.getSourceEs().iterator();
            while (itrE.hasNext())
            {
                lbl = ((DSAEdge)itrE.next()).getEnd().getLabel();
                jj = 0;
                found = false;
                while (!(found))
                {
                    if (lbl.equals(lblMatrix[jj]))
                    {
                        found = true;
                    }
                    else
                    {
                        jj++;
                    }
                }
                matrix[ii][jj]++;
            }
        }
    }
   
    /*
    * clearVertVisited
    * I: none
    * E: none
    * P: clears the visited status of all verticies in graph
    */
    private void clearVertVisited()
    {
        Iterator itr = verticies.iterator();
        while(itr.hasNext())
        {
            ((DSAVertex)itr.next()).clearVisited();
        }
    }
}

