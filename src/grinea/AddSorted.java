package grinea;/*
* AddSorted
* Author: Owen Frere 19520500
* Provides a method for inserting vertex and edges
*       into a linked list in order
*/

import java.util.Iterator;
public class AddSorted
{

    /*
    * addSorted
    * I: list (GraphList), vert (DSAVertex)
    * E: none
    * P: insert a vertex into a list of vertexs based on label alphabetical
            order. Will not insert duplicate label entries
    */
    public static void addSorted(GraphList list, DSAVertex vert)
    {
        int idx = 0;
        Iterator itr = list.iterator();
        boolean inserted = false;
        int comparison = 0;

        while (itr.hasNext() && inserted == false)
        {
            comparison = ((DSAVertex)itr.next()).getLabel().compareTo(
                vert.getLabel());
            if (comparison > 0)
            {
                list.insertIndex(idx, vert);
                inserted = true;
            }
            else if (comparison == 0)
            {
                inserted = true;
            }
            else
            {
                idx++;
            }
        }

        if (!(inserted))
        {
            list.insertLast(vert);
        }
    }

    /*
    * addSorted
    * I: list (GraphList), edge (DSAEdge)
    * E: none
    * P: insert a edge into a list of edges based on label alphabetical
            order. Will insert duplicate label entries
    */   
    public static void addSorted(GraphList list, DSAEdge edge)
    {
        int idx = 0;
        Iterator itr = list.iterator();
        boolean inserted = false;
        int comparison = 0;

        while (itr.hasNext() && inserted == false)
        {

            comparison = ((DSAEdge)itr.next()).getEnd().getLabel().compareTo(
                edge.getEnd().getLabel());
            if (comparison >= 0)
            {
                list.insertIndex(idx, edge);
                inserted = true;
            }
            else
            {
                idx++;
            }
        }

        if (!(inserted))
        {
            list.insertLast(edge);
        }

    }

}

