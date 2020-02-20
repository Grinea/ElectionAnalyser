package grinea;/*
 GraphFileIo
 Author Owen Frere 19520500
 Provides file io specific to the UnitTest for DSAGraph
 Ref: prac 06 for DSA
*/

import java.io.*;
import java.util.StringTokenizer;
import java.util.Iterator;
public class GraphFileIO
{   
    /*
    * loadFile
    * I: fileName (String)
    * E: retLists (GraphList[])
    * P: Takes a filename and returns a 2 element array of linked lists,
            comprising a list of verticies and a list of edges respectively
    * N: Throws an IOException if the file can't be opened
    */
    public static GraphList loadFile(String fileName)
        throws IOException
    {
        GraphList verticies = new GraphList();
        GraphList edges = new GraphList();
        GraphList[] retLists = {verticies, edges};
        
        FileReader fileRdr;
        BufferedReader bufRdr;

        fileRdr = new FileReader(fileName);
        bufRdr = new BufferedReader(fileRdr);

        loadLists(retLists, bufRdr);

        loadVertexLists(retLists[1]);

        //removed as unneccessary for this input format
        //checkStartEndPoints(retLists);
        return retLists[0];
    }

    /*
    * loadLists
    * I: retLists(GraphList), bufRdr (BufferedReader)
    * E: none
    * P: Extract lines from file and send them to processing
    */
    private static void loadLists(GraphList[] retLists, 
                                BufferedReader bufRdr)
        throws IOException
    {
        String line;

        line = bufRdr.readLine();
        while (line != null)
        {
            processLine(retLists, line);
            line = bufRdr.readLine();
        }
    }


    /*
    * processLine
    * I: retLists(GraphList), line (String)
    * E: none
    * P: Reads a line, calls appropriate processor and appends to relevant list
    */
    private static void processLine(GraphList[] retLists, String line) 
    {
        String[] strArr = line.split(" ");
        DSAVertex source = null,  sink = null;
        DSAEdge edge;
        double weight;
        Iterator itr;
        boolean matched = false;
        DSAVertex vert;

        itr = retLists[0].iterator();
        while (itr.hasNext() && !(matched))
        {
            vert = (DSAVertex)itr.next();
            if (vert.getLabel().equals(strArr[0]))
            {
                source = vert;
                matched = true;
            }
        }
        if (!(matched))
        {
            source = new DSAVertex(strArr[0], null);
        }

        itr = retLists[0].iterator();
        matched = false;
        while (itr.hasNext() && !(matched))
        {
            vert = (DSAVertex)itr.next();
            if (vert.getLabel().equals(strArr[1]))
            {
                sink = vert;
                matched = true;
            }
        }
        if (!(matched))
        {
            sink = new DSAVertex(strArr[1], null);
        }

        if (strArr.length > 2)
        {
            weight = Double.parseDouble(strArr[2]);
        }
        else
        {
            weight = 0.0;
        }
       
        edge = new DSAEdge(source, sink, weight, source.getLabel() + "-" 
            + sink.getLabel());

        //try catch for when its erroring in assignment
        AddSorted.addSorted(retLists[0], source);
        AddSorted.addSorted(retLists[0], sink);
        AddSorted.addSorted(retLists[1], edge);

    }

    /*
    * loadVertexLists
    * I: edgeList (GraphList)
    * E: none
    * P: adds the edges into the matching vertex object lists
    */
    private static void loadVertexLists(GraphList edgeList)
    {
        Iterator itr = edgeList.iterator();
        Iterator itr2;
        DSAVertex source, sink;
        DSAEdge edge, tEd;

        while (itr.hasNext())
        {
            edge = (DSAEdge)itr.next();
            source = edge.getStart();
            sink = edge.getEnd();

            source.getSourceEs().insertLast(edge);
            sink.getSinkEs().insertLast(edge);
        }

    }
}   

