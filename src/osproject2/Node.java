
package osproject2;

/**
 * @author Valeria_Yasmin
*/ 

public class Node {

    private String ID;
    private int priority, counter;
    private Node next;

    public Node(int priority, int counter) {
        this.ID = "ID" + Administrator.counter;
        this.priority = priority;
        this.counter = counter;
        this.next = null;
    }

    public String getID() { return ID; }

    public void setID(String ID) { this.ID = ID; }

    public int getPriority() { return priority; }

    public void setPriority(int priority) { this.priority = priority;  }

    public int getCounter() { return counter; }

    public void setCounter(int counter) { this.counter = counter; }

    public Node getNext() { return next; }

    public void setNext(Node next) { this.next = next; }

}
