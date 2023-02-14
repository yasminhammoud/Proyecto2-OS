
package osproject2;

/**
 * @author Valeria_Yasmin
 */
public class Administrator {
    
    //Colas
    public static Queue QueuePriority1;
    public static Queue QueuePriority2;
    public static Queue QueuePriority3;
    public static Queue Repair;
    
    //Variables
    public static int counter;
    public Robot robot;
    
    //Constructor
    public Administrator(){

        this.QueuePriority1 = new Queue();
        this.QueuePriority2 = new Queue();
        this.QueuePriority3 = new Queue();
        this.Repair = new Queue();
        
        this.counter = 0;
        this.robot = new Robot();
    }
    
    public void runSimulation() throws InterruptedException{
       
        Interface.Revision.setText("Ninguno");
        showQueues();
        addPana();
        
        while(true) {
            
            //Robot revisa dos Panas 
            for (int i = 0; i < 2; i++) {

                // Se busca el siguiente Pana por chequear
                Node Pana2 = NextPanaToCheck();
                
                 //-------- Robot decide el destino del Pana2 ---------
                if (Pana2 != null) {
                    Interface.Revision.setText(Pana2.getID());
                    robot.Check(Pana2);
                }
                 //-----------------------------------------------------------------

                 // No se está revisando un Pana2 en este instante
                Interface.Revision.setText("Ninguno");
                
                // Actualizar los contadores de cada Pana2 de prioridad 2 y 3
                UpdateCounters();
                
                 //-------------------- Mejorar Pana 2 ---------------------- 
                 float repairProbability = (float) Math.random();
                 if (repairProbability <= 0.45 && !Repair.isEmpty()) {
                     Node PanaToFix =  Repair.deQueue();
                     Interface.Repair.setText(Repair.showInfo());
                     PanaToFix.setNext(null);
                     repairPana(PanaToFix);
                     }    
                 //-----------------------------------------------------------------
            }
            
           //-------------------------  Agregar Pana2 -------------------------
            float probabilityToAddPana = (float) Math.random();
            if (probabilityToAddPana <= 0.7) {
                addPana();
            } else { System.out.println("No se agrega un Pana2"); }
            //------------------------------------------------------------------------
        } 
        }
    
    // --------------------------------------------------------------------  Funciones del Administrador  --------------------------------------------------------------------
    
    // Agregar Pana 2 con prioridad escogida aleatoriamente
    public void addPana() {
        
        float probabilityToChoosePriority = (float) Math.random();
                
                if (probabilityToChoosePriority >= 0.66) {
                    QueuePriority1.enQueue(new Node(1,0));
                    Interface.firstPriority.setText(QueuePriority1.showInfo());
                }
                else if (probabilityToChoosePriority >= 0.33){
                    QueuePriority2.enQueue(new Node(2,0));
                    Interface.secondPriority.setText(QueuePriority2.showInfo());
                }
                else if (probabilityToChoosePriority >= 0) {
                    QueuePriority3.enQueue(new Node(3,0));
                    Interface.thirdPriority.setText(QueuePriority3.showInfo());
                }
                counter++;
                System.out.println("Se agrego un Pana2");
    }
    
    // Mejorar/Reparar Pana2 y encolarlo en su respectiva cola de prioridad
    public void repairPana(Node Pana2){
        
        System.out.println(Pana2.getID() +" se ha reparado");
        
        switch (Pana2.getPriority()) {
            case 1:
                QueuePriority1.enQueue(Pana2);
                Interface.firstPriority.setText(QueuePriority1.showInfo());
                break;
            case 2:
                QueuePriority2.enQueue(Pana2);
                Interface.secondPriority.setText(QueuePriority2.showInfo());
                break;
            case 3:
                QueuePriority3.enQueue(Pana2);
                Interface.thirdPriority.setText(QueuePriority3.showInfo());
                break;
            }
    }
    
    // Buscar el siguiente Pana2 disponible para chequear (Según el orden)
    public Node NextPanaToCheck() {
        
        Node tempNode = null;
        
        if (!QueuePriority1.isEmpty()) { tempNode = QueuePriority1.deQueue() ;}
        else if (!QueuePriority2.isEmpty()) { tempNode = QueuePriority2.deQueue() ;}
        else if (!QueuePriority3.isEmpty()) { tempNode = QueuePriority3.deQueue() ; }
        return tempNode;
    }
    
    // Actualizar contadores de cada Pana2 de prioridades 2 y 3
    public void UpdateCounters() {
        
        // Colas temporales
        Queue tempQueueP2 = new Queue();
        Queue tempQueueP3 = new Queue();
        
        if (!QueuePriority2.isEmpty()) { 

            while (!QueuePriority2.isEmpty()) {
                
                Node tempPana2 = QueuePriority2.deQueue();
                int tempPanaCounter = tempPana2.getCounter()+1;
                
                // Cambio de prioridad
                if (tempPanaCounter == 15) {
                    tempPana2.setCounter(0);
                    tempPana2.setPriority(1);
                    tempPana2.setNext(null);
                    QueuePriority1.enQueue(tempPana2);
                    System.out.println(tempPana2.getID() + " subió a prioridad 1");
                }
                else { 
                    tempPana2.setCounter(tempPanaCounter); 
                    tempQueueP2.enQueue(tempPana2); } 
            }
            QueuePriority2 = tempQueueP2;
            Interface.secondPriority.setText(QueuePriority2.showInfo());
            Interface.firstPriority.setText(QueuePriority1.showInfo());
            }            
        
        if (!QueuePriority3.isEmpty()) { 

            while (!QueuePriority3.isEmpty()) {

                Node tempPana3 = QueuePriority3.deQueue(); 
                int tempPanaCounter = tempPana3.getCounter()+1;

                // Cambio de prioridad
                if (tempPanaCounter == 15) {
                    tempPana3.setCounter(0);
                    tempPana3.setPriority(2);
                    tempPana3.setNext(null);
                    QueuePriority2.enQueue(tempPana3);
                    System.out.println(tempPana3.getID() + " subió a prioridad 2");
                    }
                else {
                    tempPana3.setCounter(tempPanaCounter); 
                    tempQueueP3.enQueue(tempPana3); 
                } 
                }
            QueuePriority3 = tempQueueP3;
            Interface.thirdPriority.setText(QueuePriority3.showInfo());
            Interface.secondPriority.setText(QueuePriority2.showInfo());
        }            
    }
    
    // Mostrar colas en la interfaz 
    public void showQueues(){
        
        Interface.firstPriority.setText(QueuePriority1.showInfo());
        Interface.secondPriority.setText(QueuePriority2.showInfo());
        Interface.thirdPriority.setText(QueuePriority3.showInfo());
        Interface.Repair.setText(Repair.showInfo());
    }
    }
        
        
    
    

