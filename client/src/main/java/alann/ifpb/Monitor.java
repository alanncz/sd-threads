/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb;

import java.nio.ByteBuffer;

/**
 *
 * @author alann
 */
public class Monitor {
    
    public Monitor(){}
    
    public static int resultadoEsperado(byte[] dados){
        ByteBuffer bff = ByteBuffer.wrap(dados);
        int valor1 = bff.getInt();
        int valor2 = bff.getInt();
        char operacao = bff.getChar();
        if (operacao == '+') return  valor1 + valor2;
        return valor1 - valor2;
    }
    
    public static long tempoInicial(){
        return System.currentTimeMillis();
    }
    
    public static long tempoFinal(long tempoInicio){
        return System.currentTimeMillis() - tempoInicio;
    }
    
    public static String strigOperacao(byte[] dados){
        ByteBuffer bff = ByteBuffer.wrap(dados);
        int valor1 = bff.getInt();
        int valor2 = bff.getInt();
        char operacao = bff.getChar();
        return valor1 + " " + operacao + " " + valor2;
    }
    
}
