/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import com.green.modelo.Conta;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="contaConciliacao")
@ViewScoped
public class ContaConciliacao implements Serializable{
    
    private Conta conta ;
    private BigDecimal valorAconciliar;
    private BigDecimal valorConciliado;
    private BigDecimal valorPrevisto;

    public ContaConciliacao(BigDecimal valorAconciliar, BigDecimal valorConciliado, BigDecimal valorPrevisto) {
        this.valorAconciliar = BigDecimal.ZERO;
        this.valorConciliado = BigDecimal.ZERO;
        this.valorPrevisto = BigDecimal.ZERO;
    }
    

    public BigDecimal getValorAconciliar() {
        return valorAconciliar;
    }

    public void setValorAconciliar(BigDecimal valorAconciliar) {
        this.valorAconciliar = valorAconciliar;
    }

    public BigDecimal getValorConciliado() {
        return valorConciliado;
    }

    public void setValorConciliado(BigDecimal valorConciliado) {
        this.valorConciliado = valorConciliado;
    }

    public BigDecimal getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(BigDecimal valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    
    
}
