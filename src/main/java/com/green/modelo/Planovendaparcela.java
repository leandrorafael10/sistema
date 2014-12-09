/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "planovendaparcela")
public class Planovendaparcela implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDPlanoVendaParcela")
    private Integer iDPlanoVendaParcela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QtdParcela")
    private int qtdParcela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor")
    private BigDecimal valor;
    @Column(name = "descricao")
    private String descricao;

    public Planovendaparcela() {
    }

    public Planovendaparcela(Integer iDPlanoVendaParcela) {
        this.iDPlanoVendaParcela = iDPlanoVendaParcela;
    }

    public Planovendaparcela(Integer iDPlanoVendaParcela, int qtdParcela, BigDecimal valor) {
        this.iDPlanoVendaParcela = iDPlanoVendaParcela;
        this.qtdParcela = qtdParcela;
        this.valor = valor;
    }

    public Integer getIDPlanoVendaParcela() {
        return iDPlanoVendaParcela;
    }

    public void setIDPlanoVendaParcela(Integer iDPlanoVendaParcela) {
        this.iDPlanoVendaParcela = iDPlanoVendaParcela;
    }

    public int getQtdParcela() {
        return qtdParcela;
    }

    public void setQtdParcela(int qtdParcela) {
        this.qtdParcela = qtdParcela;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPlanoVendaParcela != null ? iDPlanoVendaParcela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planovendaparcela)) {
            return false;
        }
        Planovendaparcela other = (Planovendaparcela) object;
        if ((this.iDPlanoVendaParcela == null && other.iDPlanoVendaParcela != null) || (this.iDPlanoVendaParcela != null && !this.iDPlanoVendaParcela.equals(other.iDPlanoVendaParcela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Planovendaparcela[ iDPlanoVendaParcela=" + iDPlanoVendaParcela + " ]";
    }
    
}
