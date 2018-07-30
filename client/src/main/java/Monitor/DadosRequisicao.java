/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

/**
 *
 * @author alann
 */
public class DadosRequisicao {
    
    private int id;
    private int resultadoEsperado;
    private int resultadoObtido;
    private String operacao;
    private long tempoInicio;
    private long tempoExecucao;

    public long getTempoInicio() {
        return tempoInicio;
    }

    public void setTempoInicio(long tempoInicio) {
        this.tempoInicio = tempoInicio;
    }

    public long getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(long tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResultadoEsperado() {
        return resultadoEsperado;
    }

    public void setResultadoEsperado(int resultadoEsperado) {
        this.resultadoEsperado = resultadoEsperado;
    }

    public int getResultadoObtido() {
        return resultadoObtido;
    }

    public void setResultadoObtido(int resultadoObtido) {
        this.resultadoObtido = resultadoObtido;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    @Override
    public String toString() {
        return "DadosRequisicao{" + "id=" + id + ", operacao=" + operacao + ", resultadoEsperado=" + resultadoEsperado + 
                ", resultadoObtido=" + resultadoObtido + ", tempoExecucao=" + tempoExecucao + "ns" + '}';
    }


    
    
    
}
