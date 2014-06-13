/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.util;

import java.math.BigDecimal;

/**
 *
 * @author leandro.silva
 */

public class ContadorPontoBrinde {
    
    private String descricao;
    private String local;
    private Long quantidade;
    private BigDecimal total; 

    public ContadorPontoBrinde() {
    }
    
    

    public ContadorPontoBrinde(String descricao, String local, Long quantidade, BigDecimal total) {
        this.descricao = descricao;
        this.local = local;
        this.quantidade = quantidade;
        this.total = total;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    
}
