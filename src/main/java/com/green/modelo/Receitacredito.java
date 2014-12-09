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
@Table(name = "receitacredito")
public class Receitacredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDReceitacredito")
    private Integer iDReceitacredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TipoPagamento")
    private boolean tipoPagamento;
    @JoinColumn(name = "IDReceita", referencedColumnName = "IDReceita")
    @OneToOne(optional = false)
    private Receita iDReceita;
    @JoinColumn(name = "IDCredito", referencedColumnName = "IDCredito")
    @ManyToOne(optional = false)
    private Credito iDCredito;

    public Receitacredito() {
    }

    public Receitacredito(Integer iDReceitacredito) {
        this.iDReceitacredito = iDReceitacredito;
    }

    public Receitacredito(Integer iDReceitacredito, boolean tipoPagamento) {
        this.iDReceitacredito = iDReceitacredito;
        this.tipoPagamento = tipoPagamento;
    }

    public Integer getIDReceitacredito() {
        return iDReceitacredito;
    }

    public void setIDReceitacredito(Integer iDReceitacredito) {
        this.iDReceitacredito = iDReceitacredito;
    }

    public boolean getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(boolean tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Receita getIDReceita() {
        return iDReceita;
    }

    public void setIDReceita(Receita iDReceita) {
        this.iDReceita = iDReceita;
    }

    public Credito getIDCredito() {
        return iDCredito;
    }

    public void setIDCredito(Credito iDCredito) {
        this.iDCredito = iDCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDReceitacredito != null ? iDReceitacredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receitacredito)) {
            return false;
        }
        Receitacredito other = (Receitacredito) object;
        if ((this.iDReceitacredito == null && other.iDReceitacredito != null) || (this.iDReceitacredito != null && !this.iDReceitacredito.equals(other.iDReceitacredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Receitacredito[ iDReceitacredito=" + iDReceitacredito + " ]";
    }
    
}
