package grinea;/*
 GraphList
 Author: Owen Frere 19520500
 Date Modified: 07/10/18
 Ref: DSA prac 06
*/
import java.util.*;
public class GraphList implements Iterable
{
    //Inner class for node
    private class GraphListNode
    {
        //Class fields
        public Object value;
        public GraphListNode nextN, prevN;

        //Alternate constructor
        public GraphListNode(Object inValue)
        {
            this.value = inValue;
            this.nextN = null;
            this.prevN = null;
        }
    
    }

    //Inner class for iterator
    private class GraphListIterator implements Iterator
    {
        //Class fields
        private GraphList.GraphListNode iterNext;

        //Alternate constructor
        public GraphListIterator(GraphList inList)
        {
            iterNext = inList.head;
        }

        /*
        * hasNext
        * I: none
        * E: (boolean)
        * P: Returns boolean representing emptiness
        */
        public boolean hasNext()
        {
            return (iterNext != null);
        }

        public GraphList.GraphListNode getCurrNode()
        {
            return iterNext.prevN;
        }

        /*
        * next
        * I: none
        * E: expValue 
        * P: returns next value
        */ 
        public Object next()
        {
            Object expValue;
            if (iterNext == null)
            {
                expValue = null;
            }
            else
            {
                expValue = iterNext.value;
                iterNext = iterNext.nextN;
            }

            return expValue;
        }

        //unsupported function warning 
        public void remove()
        {
            throw new UnsupportedOperationException("Not supported.");
        }

    }

    //Class fields
    private GraphListNode head, tail;

    //Default constructor
    public GraphList()
    {
        this.head = null;
        this.tail = null;
    }

    //Alternate constructor
    public GraphList(Object newValue)
    {
        this.head = new GraphListNode(newValue);
        this.tail = this.head;
    }

    /*
    * iterator()
    * I: none
    * E: (Iterator)
    * P: returns an iterator
    */
    public Iterator iterator()
    {
        return new GraphListIterator(this);
    }

    /*
    * insertFirst
    * I: newValue ()
    * E: none
    * P: inserts new item at front of linked list
    */  
    public void insertFirst(Object newValue)
    {

        GraphListNode newNode = new GraphListNode(newValue);
        if (head == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            newNode.nextN = head;
            head.prevN = newNode;
            head = newNode;
        }
    }

    /*
    * insertLast
    * I: newValue 
    * E: none
    * P: inserts new item at end of linked list
    */ 
    public void insertLast(Object newValue)
    {
        GraphListNode newNode = new GraphListNode(newValue);
        if (tail == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            newNode.prevN = tail;
            tail.nextN = newNode;
            tail = newNode;
        }
    }

    /*
    * insertBefore
    * I: newValue 
    * E: none
    * P: inserts new item before supplied node
    */ 
    public void insertIndex(int idx, Object newValue)
    {
        GraphListNode currNode = head;
        GraphListNode newNode;

        if (currNode == null || idx == 0)
        {
            insertFirst(newValue);
        }
        else
        {
            for (int ii = 0; ii < idx; ii++)
            {
                currNode = currNode.nextN;
            }

            if (currNode == null)
            {
                insertLast(newValue);
            }
            else
            {
                newNode = new GraphListNode(newValue);
                newNode.nextN = currNode;
                newNode.prevN = currNode.prevN;
                newNode.prevN.nextN = newNode;
                currNode.prevN = newNode;
            }
        }
    }

    /*
    * isEmpty
    * I: none
    * E: (boolean)
    * P: returns boolean representing emptiness
    */ 
    public boolean isEmpty()   
    {
        return (head == null);
    }

    /*
    * peekFirst
    * I: newValue 
    * E: none
    * P: returns value at front of linked list
    */  
    public Object peekFirst()
    {
        if (isEmpty())
        {
            throw new IllegalArgumentException("List is empty.");
        }
        
        return head.value;
    }
    
    /*
    * peekLast
    * I: none
    * E: ()
    * P: returns value at end of linked list
    */
    public Object peekLast()
    {
        if (isEmpty())
        {
            throw new IllegalArgumentException("List is empty.");
        }

        return tail.value;
    }

    /*
    * removeFirst
    * I: none
    * E: expValue
    * P: returns value at front of linked list and deletes the node
    */
    public Object removeFirst()
    {
        Object expValue;

        if (isEmpty())
        {
            throw new IllegalArgumentException("List is empty.");
        }
        else if (head == tail)
        {
            expValue = head.value;
            head = null;
            tail = null;
        }
        else
        {
            expValue = head.value;
            head = head.nextN;
            head.prevN = null;
        }

        return expValue;
    }

    /*
    * removeLast
    * I: none
    * E: expValue
    * P: returns value at end of linked list and deletes the node
    */
    public Object removeLast()
    {
        Object expValue;

        if (isEmpty())
        {
            throw new IllegalArgumentException("List is empty.");
        }
        else if (head == tail)
        {
            expValue = head.value;
            head = null;
            tail = null;
        }
        else
        {
            expValue = tail.value;
            tail = tail.prevN;
            tail.nextN = null;
        }

        return expValue;
    }

}

