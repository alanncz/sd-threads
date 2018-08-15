/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import buffer.BufferBlocking;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alann
 */
public class Saida implements Runnable {

    private final BufferBlocking bufferSaida;
    private final DatagramSocket sendSocket;

    public Saida(BufferBlocking bufferSaida) throws SocketException {
        this.sendSocket = new DatagramSocket();
        this.bufferSaida = bufferSaida;
    }

    @Override
    public void run() {

        while (true) {

            DatagramPacket sendPacket = bufferSaida.get();
            try {
                sendSocket.send(sendPacket);
            } catch (IOException ex) {
                Logger.getLogger(Saida.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
