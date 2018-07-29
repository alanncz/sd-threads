/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import threads.Operacao;
import threads.Saida;
import threads.Entrada;
import buffer.BufferBlocking;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author alann
 */
public class Client {
    
    public static void main(String[] args) throws SocketException, UnknownHostException {

        BufferBlocking bufferEntrada = new BufferBlocking(1);
        BufferBlocking bufferSaida = new BufferBlocking(1);

        Random gerador = new Random();

        int primeiroValor = gerador.nextInt(10);

        byte[] sendData = Operacao.criaOperacao(primeiroValor);

        InetAddress IPAddress = InetAddress.getByName("localhost");

        DatagramPacket sendPacket = new DatagramPacket(sendData,
                sendData.length, IPAddress, 9876);

        bufferSaida.set(sendPacket);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new Entrada(bufferEntrada));
        executor.execute(new Saida(bufferSaida));
        executor.execute(new Operacao(bufferEntrada, bufferSaida));

        executor.shutdown();

    }

}
