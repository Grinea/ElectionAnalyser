package grinea;/*
* DSALinkedList
* Author: Owen Frere 19520500
* Date Modified: 27/10/18
* Purpose: provides a reduced selection of linked list functions and
*   removed double linking as it was increasing space complexity while
    not being used
* Reference: Submitted for linked list prac in an expanded form
*/

import java.util.*;
public class DSALinkedList implements Iterable
{
    //Inner class for node
    private class DSAListNode
    {
        //Class fields
        public Object value;
        public DSAListNode nextN;

        //Alternate constructor
        public DSAListNode(Object inValue)
        {
            this.value = inValue;
            this.nextN = null;
        }
    
    }

    //Inner class for iterator
    private class DSALinkedListIterator implements Iterator
    {
        //Class fields
        private DSALinkedList.DSAListNode iterNext;

        //Alternate constructor
        public DSALinkedListIterator(DSALinkedList inList)
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

        /*
        * next
        * I: none
        * E: expValue (Object)
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

    }

    //Class fields
    private DSAListNode head, tail;

    //Default constructor
    public DSALinkedList()
    {
        this.head = null;
        this.tail = null;
    }

    /*
    * iterator()
    * I: none
    * E: (Iterator)
    * P: returns an iterator
    */
    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }

    /*
    * insertLast
    * I: newValue ()
    * E: none
    * P: inserts new item at end of linked list
    */ 
    public void insertLast(Object newValue)
    {
        DSAListNode newNode = new DSAListNode(newValue);
        if (tail == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.nextN = newNode;
            tail = newNode;
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
   
}
