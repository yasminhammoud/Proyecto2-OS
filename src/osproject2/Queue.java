
package osproject2;

/**
 * @author Valeria_Yasmin
 */
public class Queue {

    private Node front, rear;
    private int size;

    // Constructor
    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Verificar si la cola está vacía
    public boolean isEmpty() {
        return rear == null;
    }

    //Agrega Pana al final de la cola 
   void enQueue(Node newPana) {

        if (isEmpty()) {
            front = rear = newPana;
            return;
        } 
        else {
            rear.setNext(newPana);
            rear = newPana;
        }
        size++;
    }

    // Elimina y retorna el primer Pana de la cola 
     public Node deQueue() { 
        if (!isEmpty()) {
           
            Node temp = front;
            front = front.getNext();

            if (front == null) { rear = null; }
            size--;
            return temp;
        } 
        else { return null; }
    }

     // Imprimir la información de la Cola
    public String showInfo() {
        String txt = "" ;
        if (!isEmpty()) {
            Node temp = front;
            while (temp != null) {
               txt += "    " + temp.getID() + " está en la cola " + "\n";
               temp = temp.getNext();
            }
        } 
        else { txt = "       No hay juguetes en \n              esta cola"; }
        return txt;
    }
    
    public Node getFront() { return front; }

    public void setFront(Node front) { this.front = front; }

    public Node getRear() {  return rear; }

    public void setRear(Node rear) { this.rear = rear;  }

    public int getSize() { return size;  }

    public void setSize(int size) { this.size = size; }

}

