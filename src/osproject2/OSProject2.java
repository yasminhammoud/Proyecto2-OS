/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osproject2;

/**
 * @author Valeria_Yasmin
 */
public class OSProject2 {

    public static void main(String[] args ) throws InterruptedException {
        
        new Interface().setVisible(true);
        Administrator OS = new Administrator();
        OS.runSimulation();
    }    
}
