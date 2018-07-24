/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb;

/**
 *
 * @author alann
 */
public class Main {
    
    public static void main(String[] args){
        
        Operacao operacao = new Operacao();
        Thread t1 = new Thread(operacao);
        
        t1.start();
        
    }
    
}
