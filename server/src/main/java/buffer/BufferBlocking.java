/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buffer;

import interfaces.Buffer;
import java.net.DatagramPacket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alann
 */
public class BufferBlocking implements Buffer {

    private final ArrayBlockingQueue<DatagramPacket> buffer;

    public BufferBlocking(int tamanhoBuffer) {
        
        buffer = new ArrayBlockingQueue<>(tamanhoBuffer);
    }

    @Override
    public DatagramPacket get() {
        
        DatagramPacket dadosLidos = null;
        try {

            dadosLidos = buffer.take();

        } catch (InterruptedException ex) {
            Logger.getLogger(BufferBlocking.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dadosLidos;
    }

    @Override
    public void set(DatagramPacket value) {

        try {

            buffer.put(value);

        } catch (InterruptedException exeption) {

        }
    }

}
