/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "taxa_parceiro")
public class TaxaParceiro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idtaxa_parceiro")
    private Integer idtaxaParceiro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "bv")
    private BigDecimal bv;
    @Column(name = "comissao")
    private BigDecimal comissao;
    @Column(name = "iss")
    private BigDecimal iss;
    @JoinColumn(name = "IDContratoMidia", referencedColumnName = "idcontrato_midia")
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private ContratoMidia iDContratoMidia;

    public TaxaParceiro() {
    }

    public TaxaParceiro(Integer idtaxaParceiro) {
        this.idtaxaParceiro = idtaxaParceiro;
    }

    public Integer getIdtaxaParceiro() {
        return idtaxaParceiro;
    }

    public void setIdtaxaParceiro(Integer idtaxaParceiro) {
        this.idtaxaParceiro = idtaxaParceiro;
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

    public BigDecimal getIss() {
        return iss;
    }

    public void setIss(BigDecimal iss) {
        this.iss = iss;
    }

    public ContratoMidia getIDContratoMidia() {
        return iDContratoMidia;
    }

    public void setIDContratoMidia(ContratoMidia iDContratoMidia) {
        this.iDContratoMidia = iDContratoMidia;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtaxaParceiro != null ? idtaxaParceiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxaParceiro)) {
            return false;
        }
        TaxaParceiro other = (TaxaParceiro) object;
        if ((this.idtaxaParceiro == null && other.idtaxaParceiro != null) || (this.idtaxaParceiro != null && !this.idtaxaParceiro.equals(other.idtaxaParceiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.TaxaParceiro[ idtaxaParceiro=" + idtaxaParceiro + " ]";
    }
    
}
