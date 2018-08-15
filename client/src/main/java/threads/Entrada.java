/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import Monitor.Monitor;
import buffer.BufferBlocking;
import interfaces.Buffer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
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
        this.serverSocket = new DatagramSocket(6666);
        this.bufferShared = bufferShared;
    }

    @Override
    public void run() {

        while (true) {

            int tamanho = Integer.BYTES * 2;
            byte[] receiveData = new byte[tamanho];

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            try {
                serverSocket.receive(receivePacket);
                ByteBuffer bff = ByteBuffer.wrap(receiveData);
                int idOperacaoAnterior = bff.getInt();
                int resultadoOperacaoAnterior = bff.getInt();
                
                //monitorando resultado da operacao anterior
                Monitor.getListaDados().get(idOperacaoAnterior).setResultadoObtido(resultadoOperacaoAnterior);
                long tempoInicio = Monitor.getListaDados().get(idOperacaoAnterior).getTempoInicio();
                Monitor.getListaDados().get(idOperacaoAnterior).setTempoExecucao(Monitor.tempoFinal(tempoInicio));
                bufferShared.set(receivePacket);
                
                System.out.println(Monitor.getListaDados().get(idOperacaoAnterior).toString());
                
            } catch (IOException ex) {
                Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
