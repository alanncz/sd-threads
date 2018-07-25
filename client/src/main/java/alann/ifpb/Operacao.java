/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alann
 */
public class Operacao implements Runnable {

    private static int valor;
    private static boolean primeiraRequisicao = true;

    @Override
    public void run() {

        valor = 0;
        int requisicoes = 0;

        try {

            DatagramSocket clientSocket = new DatagramSocket();

            String servidor = "localhost";
            int porta = 9876;

            InetAddress IPAddress = InetAddress.getByName(servidor);
            
            int contRequisicoes = 0;

            while (true) {
                
                long inicioRequisicao = Monitor.tempoInicial();

                byte[] sendData = criaOperacao(valor);
                
                int resultadoEsperado = Monitor.resultadoEsperado(sendData);

                DatagramPacket sendPacket = new DatagramPacket(sendData,
                        sendData.length, IPAddress, porta);

                long tempoInicio = Monitor.tempoInicial();

                clientSocket.send(sendPacket);
                contRequisicoes++;
                requisicoes++;

                if (requisicoes == 10) {

                    long tempoFinal = Monitor.tempoFinal(tempoInicio);

                    long tempoSono = 1000 - tempoFinal;
                    Thread.sleep(tempoSono);

                    requisicoes = 0;
                    
                    System.out.println("");
                }

                byte[] receiveData = new byte[Integer.BYTES];

                DatagramPacket receivePacket = new DatagramPacket(receiveData,
                        receiveData.length);

                clientSocket.receive(receivePacket);

                int resultadoObtido = atualizarValor(receivePacket);
                String stringOperacao = Monitor.strigOperacao(sendData);
                
                long finalRequisicao = Monitor.tempoFinal(inicioRequisicao);
                
                System.out.println("Requisicao " + contRequisicoes + " Operacao:" + 
                        stringOperacao + "/ Resutado esperado: " + resultadoEsperado +
                        "/ Resultado Obtido: " + resultadoObtido + "/ Operação realizada em: " 
                        + finalRequisicao + " MLS");
            }

        } catch (IOException ex) {

        } catch (InterruptedException ex) {
            Logger.getLogger(Operacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static byte[] criaOperacao(int valor) {

        Random gerador = new Random();

        int valor1;
        int valor2;

        if (primeiraRequisicao == true) {

            valor1 = gerador.nextInt(10);
            valor2 = gerador.nextInt(10);
            primeiraRequisicao = false;
        } else {

            valor1 = valor;
            valor2 = gerador.nextInt(10);
        }

        char operacao = 'n';
        int operacaoEscolha = gerador.nextInt(2);

        if (operacaoEscolha == 0) {
            operacao = '+';
        } else if (operacaoEscolha == 1) {
            operacao = '-';
        }

        int tamanho = Integer.BYTES * 2 + Character.BYTES;

        ByteBuffer bff = ByteBuffer.allocate(tamanho);

        bff.putInt(valor1);
        bff.putInt(valor2);
        bff.putChar(operacao);

        byte[] sendData = bff.array();

        return sendData;

    }

    public static int atualizarValor(DatagramPacket receivePacket) {

        ByteBuffer bff = ByteBuffer.wrap(receivePacket.getData());
        int resultado = bff.getInt();
        valor = resultado;
        return resultado;
    }

}
