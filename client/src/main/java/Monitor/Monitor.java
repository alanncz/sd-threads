/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 *
 * @author alann
 */
public class Monitor {
    
    private static ArrayList<DadosRequisicao> listaDados = new ArrayList();

    public static ArrayList<DadosRequisicao> getListaDados() {
        return listaDados;
    }
    
    public static void put(DadosRequisicao dados){
        listaDados.add(dados.getId(), dados);
    }
    
    public static long tempoInicio(){
        return System.nanoTime();
    }
    
    public static long tempoFinal(long tempoInicio){
        long tempoFinal = System.nanoTime();
        return tempoFinal - tempoInicio;
    }
    
    public static int resultadoEsperado(byte[] dados){
        ByteBuffer bff = ByteBuffer.wrap(dados);
        int idOperacao = bff.getInt();
        int valor1 = bff.getInt();
        int valor2 = bff.getInt();
        char operacao = bff.getChar();
        if (operacao == '+') return valor1 + valor2;
        return valor1 - valor2;
    }
    
    public static String stringOperacao(byte[] dados){
        ByteBuffer bff = ByteBuffer.wrap(dados);
        int idOperacao = bff.getInt();
        int valor1 = bff.getInt();
        int valor2 = bff.getInt();
        char operacao = bff.getChar();
        return valor1 + " " + operacao + " " + valor2;
    }
    
}
