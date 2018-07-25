/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

/**
 *
 * @author alann
 */
public class Server {

    public static void main(String args[]) throws Exception {

        int porta = 9876;

        DatagramSocket serverSocket = new DatagramSocket(porta);

        while (true) {

            int tamanho = Integer.BYTES * 2 + Character.BYTES;
            byte[] receiveData = new byte[tamanho];

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            serverSocket.receive(receivePacket);

            receiveData = receivePacket.getData();

            InetAddress IPAddress = receivePacket.getAddress();

            byte[] sendData = operacao(receiveData);

            int port = receivePacket.getPort();

            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, port);

            serverSocket.send(sendPacket);

        }
    }

    public static byte[] operacao(byte[] receiveData) {

        ByteBuffer bff = ByteBuffer.wrap(receiveData);

        int valor1 = bff.getInt();
        int valor2 = bff.getInt();
        char operacao = bff.getChar();

        int resultadoOperacao;

        if (operacao == '+') {
            resultadoOperacao = soma(valor1, valor2);
        } else {
            resultadoOperacao = subtracao(valor1, valor2);
        }

        int tamanho = Integer.BYTES;

        ByteBuffer bff2 = ByteBuffer.allocate(tamanho);

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
