/*
    Marco Rodriguez
    September 17, 2022
    This is a generic sorted singly linked list class for CS 146
 */
/**
 * This class creates a Node for the linked list
 */
class Node <T extends Comparable<T>>{
    // Attributes of Node
    private final T data;
    private Node<T> next;

    /**
     * This is a constructor of Node that assigns a value to data
     * @param d - value assigned to data
     */
    public Node(T d){ this.data =  d; this.next = null; }

    /**
     * This method returns the value in data
     * @return - <T> value of data
     */
    public T getData(){ return data; }

    /**
     * This method returns a pointer to the next Node<T>
     * @return - Node<T> reference
     */
    public Node<T> getNext(){ return next; }

    /**
     * This method changes the pointer of the next Node<T>
     * @param n - value assigned to next
     */
    public void setNext(Node<T> n){ this.next = n;}

    /**
     * This method is an extension of the compareTo method of Comparable.
     * @param that - a Node<T> which we extract the data to compare to
     * @return - negative value means this < that, 0 means this = that, positive value means this > that
     */
    public int compareTo(Node<T> that){ return this.getData().compareTo(that.getData()); }
}

public class SortedSinglyLinkedList <T extends  Comparable<T>>{

    // Attributes of Linked list
    private Node<T> head;
    private int size;

    // This overrides the default constructor of the linked list
    public SortedSinglyLinkedList(){ head = null; size = 0;}

    /**
     * This method return the head of a linked list.
     * @return - Node<T> reference of linked list
     */
    public Node<T> getHead(){ return head; }

    /**
     * This method returns the size of the linked list
     * @return - int value of size
     */
    public int getSize(){ return size; }

    /**
     * This method deep copies a Linked list into a new linked list
     * @param that
     */
    private void copyLinkedList(SortedSinglyLinkedList<T> that){
        Node<T> thatCurrent = that.getHead();
        while(thatCurrent != null){
            this.addUniqueNode(thatCurrent.getData());
            thatCurrent = thatCurrent.getNext();
        }
    }

    /**
     * This method adds a new Node to the list in ascending order. If node already exists in the list it is ignored
     * @param data -
     */
    public void addNode(T data){
        Node<T> newNode = new Node<>(data);
        Node<T> previous = null;
        Node<T> current = head;

        // While not at end of linked list AND newNode's data is greater or equal than current's data
        while(current != null && newNode.compareTo(current) >= 0) {
            // increment both previous and current
            previous = current;
            current = current.getNext();
        }

        if(previous == null) // If previous was never changes insert at head
            head = newNode;
        else {
            if(previous.compareTo(newNode) == 0) // if data exists in list do nothing
                return;
            previous.setNext(newNode);// else add it to list
        }
        newNode.setNext(current); //
        size++;
    }

    /**
     * This method adds a new Node to the list in ascending order. Without checking for duplicates. Only used for intersection
     * @param data - value to be added to linked list
     */
    private void addUniqueNode(T data){
        Node<T> newNode = new Node<>(data);
        Node<T> previous = null;
        Node<T> current = head;

        // While not at end of linked list AND newNode's data is greater or equal than current's data
        while(current != null && newNode.compareTo(current) >= 0) {
            // increment both previous and current
            previous = current;
            current = current.getNext();
        }

        if(previous == null) // If previous was never changes insert at head
            head = newNode;
        else {
            previous.setNext(newNode);// else add it to list
        }
        newNode.setNext(current); //
        size++;
    }

    /**
     * This method removes data from a linked list if it occurs in the linked list
     * @param data - data that method attempts to remove
     */
    public void removeNode(T data){
        Node <T> temp = new Node<>(data);
        Node <T> previous = null;
        Node <T> current = head;

        while(current != null && temp.compareTo(current) > 0) {
            // increment both previous and current
            previous = current;
            current = current.getNext();
        }
        if(current == null){ // removing head from linked list move head pointer
            System.out.println(data + " not in linked list.");
        }
        else if(previous == null && temp.compareTo(current) == 0){
            head = current.getNext();
            size--;
        }
        else if(temp.compareTo(current) == 0){
            previous.setNext(current.getNext());
            size--;
        }
    }

    /**
     * This method returns a boolean value true if data is in the list
     * @param data - data to look for
     * @return - boolean value
     */
    public Boolean inSet(T data){

        Node <T> temp = new Node<>(data);
        Node <T> previous = null;
        Node <T> current = head;
        boolean inSet = false;
        while(current != null && temp.compareTo(current) >= 0) {
            // increment both previous and current
            previous = current;
            current = current.getNext();
        }
        if(previous == null){
            System.out.println(data + " not in set.");
        }
        else if(previous.getData().compareTo(temp.getData()) == 0){
            inSet = true;
        }
        return inSet;
    }

    /**
     * This method returns a reference Node from inputted data
     * @param data - data to be looked for
     * @return - Node<T> reference to data
     */
    public Node<T> returnReference(T data){
        Node <T> temp = new Node<>(data);
        Node <T> previous = null;
        Node <T> current = head;
        Node <T> returnNode = null;
        while(current != null && temp.compareTo(current) >= 0) {
            // increment both previous and current
            previous = current;
            current = current.getNext();
        }
        if(previous.compareTo(temp) == 0){
            returnNode = previous;
        }
        else if(previous == null && current.compareTo(temp) == 0){ // removing head from linked list move head pointer
            returnNode = current;
        }
        else{
            System.out.println(data + " not in linked list.");
        }
        return returnNode;
    }


    /**
     * This method returns a union of two linked lists
     * @param that - the other linked list
     * @return - SortedSinglyLinkedList<T> with union of both lists
     */
    public SortedSinglyLinkedList<T> union(SortedSinglyLinkedList<T> that){
        SortedSinglyLinkedList<T> returnList = new SortedSinglyLinkedList<>();
        returnList.copyLinkedList(this);
        Node<T> thatListCurrent = that.getHead();

        while(thatListCurrent != null ){
            returnList.addNode(thatListCurrent.getData());
            thatListCurrent = thatListCurrent.getNext();
        }
        return returnList;
    }

    /**
     * This method returns an intersection of two linked lists
     * @param that - the other linked list
     * @return - SortedSinglyLinkedList<T> with intersection of both lists
     */
    public SortedSinglyLinkedList<T> intersection(SortedSinglyLinkedList<T> that) {
        SortedSinglyLinkedList<T> returnList = new SortedSinglyLinkedList<>();
        Node<T> thisListCurrent = this.getHead();
        Node<T> thatListCurrent = that.getHead();

        // addNode without checking for duplicates
        while(thisListCurrent != null && thatListCurrent != null){
            if(thisListCurrent.compareTo(thatListCurrent) == 0){
                returnList.addUniqueNode(thisListCurrent.getData());
                thisListCurrent = thisListCurrent.getNext();
                thatListCurrent = thatListCurrent.getNext();
            }
            else if(thisListCurrent.compareTo(thatListCurrent) < 0){
                thisListCurrent = thisListCurrent.getNext();
            }
            else{
                thatListCurrent = thatListCurrent.getNext();
            }
        }
        return returnList;
    }


    /**
     * This is a helper method for debugging that prints all data value in a linked list to screen
     */
    public void display(){
        Node <T> current = head;
        if(head == null){
            System.out.println("Linked List is empty");
            return;
        }
        while(current != null){
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}