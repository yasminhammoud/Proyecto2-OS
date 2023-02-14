
package osproject2;

/**
 * @author Valeria_Yasmin
 */
public class Robot {

    // Se decide que hacer con el Pana 2
    public void Check(Node Pana2) throws InterruptedException {
        
        Thread.sleep(100);
        Pana2.setCounter(0);
        Pana2.setNext(null);
        
        float probabilityForDestiny = (float) Math.random();
        
        // Sale al mercado
        if (probabilityForDestiny<=0.3) {
            
            System.out.println("Salió al mercado: "+ Pana2.getID());
            
            if (Pana2.getPriority() ==1) { Interface.firstPriority.setText(Administrator.QueuePriority1.showInfo()); }
            else if (Pana2.getPriority() == 2) { Interface.secondPriority.setText(Administrator.QueuePriority2.showInfo()); }
            else if (Pana2.getPriority() == 3) { Interface.thirdPriority.setText(Administrator.QueuePriority3.showInfo()); }
        }
        
        // Se vuelve a enconlar en su cola  - Necesita más tiempo para revisión
        else if (0.5<=probabilityForDestiny && probabilityForDestiny<=1) {
            
            System.out.println(Pana2.getID() + " requiere más tiempo para revisión");
            
            if (Pana2.getPriority() == 1) {
                Administrator.QueuePriority1.enQueue(Pana2);
                Interface.firstPriority.setText(Administrator.QueuePriority1.showInfo());
            }
            else if (Pana2.getPriority() == 2) {
                Administrator.QueuePriority2.enQueue(Pana2);
                Interface.secondPriority.setText(Administrator.QueuePriority2.showInfo());
            }
            else if (Pana2.getPriority() == 3) {
                Administrator.QueuePriority3.enQueue(Pana2);
                Interface.thirdPriority.setText(Administrator.QueuePriority3.showInfo());
            } 
        }
        
        // Va a la cola de reparación
        else{
            System.out.println(Pana2.getID() + " se va a la cola de reparación");
            Administrator.Repair.enQueue(Pana2);
            Interface.Repair.setText(Administrator.Repair.showInfo());
        }
    }
}
