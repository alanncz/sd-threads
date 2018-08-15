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
            byte[] sendData = operacao(receivePacket.getData());
            InetAddress IPAddress = null;
            try {
                IPAddress = InetAddress.getByName(receivePacket.getAddress().getHostName());
            } catch (UnknownHostException ex) {
                Logger.getLogger(Operacao.class.getName()).log(Level.SEVERE, null, ex);
            }
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, 6666);
            bufferSaida.set(sendPacket);
        }

    }

    public static byte[] operacao(byte[] receiveData) {

        ByteBuffer bff = ByteBuffer.wrap(receiveData);

        int idOperacao = bff.getInt();
        int valor1 = bff.getInt();
        int valor2 = bff.getInt();
        char operacao = bff.getChar();
        
        System.out.println(valor1 + " " + operacao + " " + valor2);

        int resultadoOperacao;

        if (operacao == '+') {
            resultadoOperacao = soma(valor1, valor2);
        } else {
            resultadoOperacao = subtracao(valor1, valor2);
        }

        int tamanho = Integer.BYTES * 2;

        ByteBuffer bff2 = ByteBuffer.allocate(tamanho);

        bff2.putInt(idOperacao);
        bff2.putInt(resultadoOperacao);

        byte[] sendData = bff2.array();

        return sendData;
    }

    public static int soma(int valor1, int valor2) {

        return valor1 + valor2;

    }

    public static int subtracao(int valor1, int valor2) {

        return valor1 - valor2;

    }

}
