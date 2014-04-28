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
@Table(name = "planovendapagamento")
public class Planovendapagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NMaxParcela")
    private int nMaxParcela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorBaseTotal")
    private float valorBaseTotal;
    @Id
    @GeneratedValue
    @Column(name = "IDPlanovendaPagamento")
    private Integer iDPlanovendaPagamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ativo")
    private boolean ativo;
    @JoinColumn(name = "IDTipoPagamento", referencedColumnName = "IDTipoPagamento")
    @ManyToOne(optional = false)
    private Tipopagamento iDTipoPagamento;
    @JoinColumn(name = "IDPlanoVenda", referencedColumnName = "IDPlanoVenda")
    @ManyToOne(optional = false)
    private Planovenda iDPlanoVenda;
    
    public Planovendapagamento() {
    }

    public Planovendapagamento(Integer iDPlanovendaPagamento) {
        this.iDPlanovendaPagamento = iDPlanovendaPagamento;
    }

    public Planovendapagamento(Integer iDPlanovendaPagamento, int nMaxParcela, float valorBaseTotal, boolean ativo) {
        this.iDPlanovendaPagamento = iDPlanovendaPagamento;
        this.nMaxParcela = nMaxParcela;
        this.valorBaseTotal = valorBaseTotal;
        this.ativo = ativo;
    }

    public int getNMaxParcela() {
        return nMaxParcela;
    }

    public void setNMaxParcela(int nMaxParcela) {
        this.nMaxParcela = nMaxParcela;
    }

    public float getValorBaseTotal() {
        return valorBaseTotal;
    }

    public void setValorBaseTotal(float valorBaseTotal) {
        this.valorBaseTotal = valorBaseTotal;
    }

    public Integer getIDPlanovendaPagamento() {
        return iDPlanovendaPagamento;
    }

    public void setIDPlanovendaPagamento(Integer iDPlanovendaPagamento) {
        this.iDPlanovendaPagamento = iDPlanovendaPagamento;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Tipopagamento getIDTipoPagamento() {
        return iDTipoPagamento;
    }

    public void setIDTipoPagamento(Tipopagamento iDTipoPagamento) {
        this.iDTipoPagamento = iDTipoPagamento;
    }

    public Planovenda getIDPlanoVenda() {
        return iDPlanoVenda;
    }

    public void setIDPlanoVenda(Planovenda iDPlanoVenda) {
        this.iDPlanoVenda = iDPlanoVenda;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPlanovendaPagamento != null ? iDPlanovendaPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planovendapagamento)) {
            return false;
        }
        Planovendapagamento other = (Planovendapagamento) object;
        if ((this.iDPlanovendaPagamento == null && other.iDPlanovendaPagamento != null) || (this.iDPlanovendaPagamento != null && !this.iDPlanovendaPagamento.equals(other.iDPlanovendaPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Planovendapagamento[ iDPlanovendaPagamento=" + iDPlanovendaPagamento + " ]";
    }
    
}
