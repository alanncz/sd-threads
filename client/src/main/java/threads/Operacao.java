/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import buffer.BufferBlocking;
import interfaces.Buffer;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alann
 */
public class Operacao implements Runnable {

    private final Buffer bufferEntrada;
    private final Buffer bufferSaida;

    public Operacao(BufferBlocking bufferEntrada, BufferBlocking bufferSaida) {
        this.bufferEntrada = bufferEntrada;
        this.bufferSaida = bufferSaida;
    }

    @Override
    public void run() {

        while (true) {
            DatagramPacket receivePacket = bufferEntrada.get();
            ByteBuffer bff = ByteBuffer.wrap(receivePacket.getData());
            int resultadoOperacaoAnterior = bff.getInt();
            byte[] sendData = criaOperacao(resultadoOperacaoAnterior);
            System.out.println(resultadoOperacaoAnterior);
            
            InetAddress IPAddress = null;
            try {
                IPAddress = InetAddress.getByName("localhost");
            } catch (UnknownHostException ex) {
                Logger.getLogger(Operacao.class.getName()).log(Level.SEVERE, null, ex);
            }
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, 9876);
            bufferSaida.set(sendPacket);                      
        }
    }

    public static byte[] criaOperacao(int resultadoOperacaoAnterior) {

        Random gerador = new Random();
        int novoValor = gerador.nextInt(10);
        char operacao = 'n';
        int operacaoEscolha = gerador.nextInt(2);
        if (operacaoEscolha == 0) operacao = '+';
        else if (operacaoEscolha == 1)  operacao = '-';
       
        int tamanho = Integer.BYTES * 2 + Character.BYTES;
        ByteBuffer bff = ByteBuffer.allocate(tamanho);
        bff.putInt(resultadoOperacaoAnterior);
        bff.putInt(novoValor);
        bff.putChar(operacao);
        byte[] sendData = bff.array();

        return sendData;

    }

}
