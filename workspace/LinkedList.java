/*
Problem:  Write a program that keeps and manipulates a linked list of
	    String data. The data will be provided by the user one item at a time.
      The user should be able to do the following operations:
                     -add "String"
                                adds an item to your list (maintaining alphabetical order)
                     -remove "String"
                                if the item exists removes the first instance of it
                     -show
                                should display all items in the linked list
                     -clear
                               should clear the list
	Input:  commands listed above
	Output: the results to the screen of each menu
	        choice, and error messages where appropriate.

 *Name: Joel A.
 *Date: Sep 24, 2025
 *PD: 5
 *You wrote a good description so I'm going to reuse it.
 *Description:  User will use this class to initialize and manipulate a linked list of
	              String data. The data will be provided by the user one item at a time.
                The user should be able to do the following operations:
                     -add "String"
                                adds an item to your list (maintaining alphabetical order)
                     -remove "String"
                                if the item exists removes the first instance of it
                     -show
                                should display all items in the linked list
                     -clear
                               should clear the list

*/

import java.util.List;

public class LinkedList{

  //instance varialbes go here (think about what you need to keep track of!)
  private ListNode head;

  //constructors go here
  public LinkedList() {
    head = null;
  }

  //precondition: the list has been initialized
  //postcondition: the ListNode containing the appropriate value has been added and returned
  public ListNode addAValue(String line) {

    ListNode prevNode = head;
    ListNode newNode = new ListNode(line, null);

    if (head == null) {
      head = newNode;
      return head;
    }
  
    while ( (prevNode.getNext() != null) && (line.compareTo(prevNode.getNext().getValue()) > 0) ) {
      prevNode = prevNode.getNext();
    }

    if ( line.compareTo(head.getValue()) > 0 ) {
      newNode.setNext(prevNode.getNext());
      prevNode.setNext(newNode);
    }
    
    else {
      newNode.setNext(prevNode);
      head = newNode;
      return head;
    }

    /*
    if (newNode.getNext() != null)
      System.out.println(line + " is next to: " + newNode.getNext().getValue() + ". It is after " + prevNode.getValue());
    else
      System.out.println(line + " is next to: null" + ". It is after " + prevNode.getValue());
    */
    
    return newNode;
  }
  
  //precondition: the list has been initialized
  //postcondition: the ListNode containing the appropriate value has been deleted and returned.
  //if the value is not in the list returns null
  public ListNode deleteAValue(String line)
  {
    ListNode tempNode = head;

    if (head != null) {

      if (line.equals(head.getValue())) {
        head = head.getNext();
        return tempNode;
      }

      while ( (tempNode.getNext() != null) && (!line.equals(tempNode.getNext().getValue())) ) {
        tempNode = tempNode.getNext();
      }

      if (tempNode.getNext() != null) {
        ListNode deletedNode = tempNode.getNext();
        tempNode.setNext(deletedNode.getNext());
        return deletedNode;
      }
      
    }

    return null;

  }

  //precondition: the list has been initialized
  //postconditions: returns a string containing all values appended together with spaces between.
  public String showValues()
  {
    ListNode tempNode = head;
    if (head != null) {
      String currentList = tempNode.getValue();
      
      while ( tempNode.getNext() != null ) {
        tempNode = tempNode.getNext();
        currentList += ", " + tempNode.getValue();
      }
      return currentList;
    }

    else {
      return "";
    }
  }

  //precondition: the list has been initialized
  //postconditions: clears the list.
  public void clear()
  {
    head = null;
  }

  //precondition: the list has been initialized
  //postcondition: list will be cleared
  public void test() {
    System.out.println("--------------------------------------------------------------------\n");
    System.out.println("RUNNING TEST\n\n");

    System.out.println("Adding to list\n");

    System.out.println("Adding apple: " + addAValue("apple").getValue());
    System.out.println("Adding banana: " + addAValue("banana").getValue());
    System.out.println("Adding canteloupe: " + addAValue("canteloupe").getValue());
    System.out.println("Adding dog: " + addAValue("dog").getValue());
    System.out.println("Adding edamame: " + addAValue("edamame").getValue());
    System.out.println("Adding egg: " + addAValue("egg").getValue());
    System.out.println("Adding flan: " + addAValue("flan").getValue());
    System.out.println("Adding french fries: " + addAValue("french fries").getValue());
    System.out.println("Adding grapefruit: " + addAValue("grapefruit").getValue());
      
    System.out.println(showValues());

    System.out.println("--------------------------------------------------------------------\n");

    System.out.println("Ordering list\n");

    System.out.println("Correct Series: apple, banana, canteloupe, dog, edamame, egg, flan, french fries, grapefruit");
    System.out.println("LIST MATCHES: " + showValues().equals("apple, banana, canteloupe, dog, edamame, egg, flan, french fries, grapefruit"));

    System.out.println("--------------------------------------------------------------------\n");

    System.out.println("Removing from list\n");
    System.out.println("Remove 'edamame': " + deleteAValue("edamame").getValue());
    System.out.println("Remove 'dog': " + deleteAValue("dog").getValue());
    System.out.println("Attempt to remove 'edamame': " + deleteAValue("edamame"));
    System.out.println("Attempt to remove 'dog': " + deleteAValue("dog"));

    System.out.println("--------------------------------------------------------------------\n");

    System.out.println("Reversing List");
    System.out.println("Before Reverse: " + showValues());
    reverse();
    System.out.println("After Reverse: " + showValues());
    System.out.println("Reversing list again for testing purposes: ");
    reverse();

    testNReverse(3);

    System.out.println("Clearing List\n");
    System.out.println("Before Clear: " + showValues());
    clear();
    System.out.println("After Clear: " + showValues());
    System.out.println("List Cleared: " + showValues().equals(""));

    
    
  }
  
  public void testNReverse(int n) {
    System.out.println("--------------------------------------------------------------------\n");

    System.out.println("Reversing List");
    System.out.println("Before nReverse: " + showValues());
    nReverse(n);
    System.out.println("After nReverse: " + showValues());

    System.out.println("--------------------------------------------------------------------\n");
  }

  //precondition: Linkedlist has more than one value
  //postconditon: Reverses the order of each node in the linked list
  public void reverse() {
    ListNode prevNode = head;
    ListNode currNode = prevNode.getNext();
    ListNode nextNode = currNode.getNext();
    
    prevNode.setNext(null);
    currNode.setNext(prevNode);
    head = currNode;

    while (nextNode != null) { 
      head = nextNode;
      prevNode = currNode;
      currNode = nextNode;
      nextNode = nextNode.getNext();
      currNode.setNext(prevNode);
    }
  }

  //precondition: n <= number of items in the linkedlist an linked list needs at least one value in it
  //postcondition: Each group of n nodes in the linkedlist are reversed starting from the head
  public void nReverse(int n) {
    boolean headDeclared = false;
    
    ListNode prevNode= null; 
    ListNode currNode = head; 
    ListNode nextNode = currNode.getNext(); 
    ListNode unsortedTailNode = head; 
    ListNode sortedTailNode = null; 

    
    while (currNode != null) {
      
      for (int itemsReversed = 0; itemsReversed < n && currNode != null; itemsReversed++) {
        currNode.setNext(prevNode);
        prevNode = currNode;
        currNode = nextNode;
        if (nextNode != null) {
          nextNode = nextNode.getNext();
        }
      }
      
      if (!headDeclared) {
        head = prevNode;
        headDeclared = true;
      }
      
      if (sortedTailNode != null) {
        sortedTailNode.setNext(prevNode);
      }
      
      unsortedTailNode.setNext(nextNode);
      sortedTailNode = unsortedTailNode;
      unsortedTailNode = currNode;

    }
  }
}
