/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import buffer.BufferBlocking;
import interfaces.Buffer;
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
public class Entrada implements Runnable {

    private final DatagramSocket serverSocket;
    private final Buffer bufferShared;

    public Entrada(BufferBlocking bufferShared) throws SocketException {
        this.serverSocket = new DatagramSocket(6677);
        this.bufferShared = bufferShared;
    }

    @Override
    public void run() {

        while (true) {

            int tamanho = Integer.BYTES * 3 + Character.BYTES;
            byte[] receiveData = new byte[tamanho];

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            
            try {
                serverSocket.receive(receivePacket);
                bufferShared.set(receivePacket);
            } catch (IOException ex) {
                Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
