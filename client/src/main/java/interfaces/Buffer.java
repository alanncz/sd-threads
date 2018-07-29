/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.net.DatagramPacket;

/**
 *
 * @author alann
 */
public interface Buffer {
    
    public DatagramPacket get();
    public void set(DatagramPacket data);
    
}
