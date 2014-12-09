/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "despesadebito")
public class Despesadebito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDDespesaDebito")
    private Integer iDDespesaDebito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TipoPagamento")
    private boolean tipoPagamento;
    @JoinColumn(name = "IDDespesa", referencedColumnName = "IDDespesa")
    @ManyToOne(optional = false,cascade={CascadeType.MERGE})
    private Despesa iDDespesa;
    @JoinColumn(name = "IDDebito", referencedColumnName = "IDDebito")
    @ManyToOne(optional = false,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
    private Debito iDDebito;

    public Despesadebito() {
    }

    public Despesadebito(Integer iDDespesaDebito) {
        this.iDDespesaDebito = iDDespesaDebito;
    }

    public Despesadebito(Integer iDDespesaDebito, boolean tipoPagamento) {
        this.iDDespesaDebito = iDDespesaDebito;
        this.tipoPagamento = tipoPagamento;
    }

    public Integer getIDDespesaDebito() {
        return iDDespesaDebito;
    }

    public void setIDDespesaDebito(Integer iDDespesaDebito) {
        this.iDDespesaDebito = iDDespesaDebito;
    }

    public boolean getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(boolean tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Despesa getIDDespesa() {
        return iDDespesa;
    }

    public void setIDDespesa(Despesa iDDespesa) {
        this.iDDespesa = iDDespesa;
    }

    public Debito getIDDebito() {
        return iDDebito;
    }

    public void setIDDebito(Debito iDDebito) {
        this.iDDebito = iDDebito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDDespesaDebito != null ? iDDespesaDebito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despesadebito)) {
            return false;
        }
        Despesadebito other = (Despesadebito) object;
        if ((this.iDDespesaDebito == null && other.iDDespesaDebito != null) || (this.iDDespesaDebito != null && !this.iDDespesaDebito.equals(other.iDDespesaDebito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Despesadebito[ iDDespesaDebito=" + iDDespesaDebito + " ]";
    }
    
}
