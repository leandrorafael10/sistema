/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "contrato_parceiro")
public class ContratoParceiro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDcontratoParceiro")
    private Integer iDContratoParceiro;
    @Column(name = "cliente")
    private String cliente;
    @JoinColumn(name = "idcontrato_midia", referencedColumnName = "idcontrato_midia")
    @ManyToOne
    private ContratoMidia iDContratoMidia;
    @Column(name = "bv")
    private BigDecimal bv;
    @Column(name = "comissao")
    private BigDecimal comissao;
    @Column(name = "imposto")
    private BigDecimal imposto;

    public ContratoParceiro() {
        this.bv = new BigDecimal("0.00");
        this.comissao = new BigDecimal("0.00");
        this.imposto = new BigDecimal("0.00");
    }

    public ContratoParceiro(Integer iDContratoParceiro, String cliente, ContratoMidia iDContratoMidia, BigDecimal bv, BigDecimal comissao, BigDecimal imposto) {
        this.iDContratoParceiro = iDContratoParceiro;
        this.cliente = cliente;
        this.iDContratoMidia = iDContratoMidia;
        this.bv = bv;
        this.comissao = comissao;
        this.imposto = imposto;
    }
    
    

    public Integer getIDContratoParceiro() {
        return iDContratoParceiro;
    }

    public void setIDContratoParceiro(Integer iDContratoParceiro) {
        this.iDContratoParceiro = iDContratoParceiro;
    }

    public ContratoMidia getIDContratoMidia() {
        return iDContratoMidia;
    }

    public void setIDContratoMidia(ContratoMidia iDContratoMidia) {
        this.iDContratoMidia = iDContratoMidia;
    }
    

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getBv() {
        return bv;
    }

    public void setBv(BigDecimal bv) {
        this.bv = bv;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public BigDecimal getImposto() {
        return imposto;
    }

    public void setImposto(BigDecimal imposto) {
        this.imposto = imposto;
    }
    
    

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDContratoParceiro != null ? iDContratoParceiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the iDContratoParceiro fields are not set
        if (!(object instanceof ContratoParceiro)) {
            return false;
        }
        ContratoParceiro other = (ContratoParceiro) object;
        if ((this.iDContratoParceiro == null && other.iDContratoParceiro != null) || (this.iDContratoParceiro != null && !this.iDContratoParceiro.equals(other.iDContratoParceiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.ContratoParceiro[ id=" + iDContratoParceiro + " ]";
    }

    
    
}
