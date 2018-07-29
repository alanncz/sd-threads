/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import threads.Saida;
import threads.Entrada;
import threads.Operacao;
import buffer.BufferBlocking;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author alann
 */
public class Server {

    public static void main(String[] args) throws SocketException {
        
        BufferBlocking bufferEntrada = new BufferBlocking(10);
        BufferBlocking bufferSaida = new BufferBlocking(10);
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new Entrada(bufferEntrada));
        executor.execute(new Operacao(bufferEntrada, bufferSaida));
        executor.execute(new Saida(bufferSaida));
        executor.shutdown();

    }
}
