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

            while (true) {

                byte[] sendData = criaOperacao(valor);

                DatagramPacket sendPacket = new DatagramPacket(sendData,
                        sendData.length, IPAddress, porta);

                long tempoInicio = System.currentTimeMillis();

                clientSocket.send(sendPacket);
                requisicoes++;

                if (requisicoes == 10) {

                    long tempoFinal = System.currentTimeMillis();
                    long dif = (tempoFinal - tempoInicio);

                    long tempoSono = 1000 - dif;
                    Thread.sleep(tempoSono);

                    System.out.println("Requisicoes enviadas :" + requisicoes);
                    requisicoes = 0;
                    
                }

                byte[] receiveData = new byte[Integer.BYTES];

                DatagramPacket receivePacket = new DatagramPacket(receiveData,
                        receiveData.length);

                clientSocket.receive(receivePacket);

                atualizarValor(receivePacket);
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

    public static void atualizarValor(DatagramPacket receivePacket) {

        ByteBuffer bff = ByteBuffer.wrap(receivePacket.getData());
        int resultado = bff.getInt();
        valor = resultado;
        //System.out.println("O valor atual é " + resultado);

    }

}
