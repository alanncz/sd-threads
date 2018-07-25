/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author alann
 */
public class Main {
    
    public static void main(String[] args){
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Operacao());
        executorService.shutdown();
        
    }
    
}
