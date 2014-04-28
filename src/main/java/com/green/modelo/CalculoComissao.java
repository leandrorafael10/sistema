/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "calculo_comissao")
public class CalculoComissao implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idcalculo_comissao")
    private Integer idcalculoComissao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porc_promotor_1")
    private BigDecimal porcPromotor1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porc_promotor_2")
    private BigDecimal porcPromotor2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porc_promotor_3")
    private BigDecimal porcPromotor3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porc_gerente_1")
    private BigDecimal porcGerente1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porc_gerente_2")
    private BigDecimal porcGerente2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porc_gerente_3")
    private BigDecimal porcGerente3;
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.DATE)
    private Date dTInc;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.DATE)
    private Date dTAlt;
    @JoinColumn(name = "IDUsuarioAlt", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioAlt;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;

    public CalculoComissao() {
    }

    public CalculoComissao(Integer idcalculoComissao) {
        this.idcalculoComissao = idcalculoComissao;
    }

    public CalculoComissao(Integer idcalculoComissao, BigDecimal porcPromotor1, BigDecimal porcPromotor2, BigDecimal porcPromotor3, BigDecimal porcGerente1, BigDecimal porcGerente2, BigDecimal porcGerente3, Date dTInc) {
        this.idcalculoComissao = idcalculoComissao;
        this.porcPromotor1 = porcPromotor1;
        this.porcPromotor2 = porcPromotor2;
        this.porcPromotor3 = porcPromotor3;
        this.porcGerente1 = porcGerente1;
        this.porcGerente2 = porcGerente2;
        this.porcGerente3 = porcGerente3;
        this.dTInc = dTInc;
    }

    public Integer getIdcalculoComissao() {
        return idcalculoComissao;
    }

    public void setIdcalculoComissao(Integer idcalculoComissao) {
        this.idcalculoComissao = idcalculoComissao;
    }

    public BigDecimal getPorcPromotor1() {
        return porcPromotor1;
    }

    public void setPorcPromotor1(BigDecimal porcPromotor1) {
        this.porcPromotor1 = porcPromotor1;
    }

    public BigDecimal getPorcPromotor2() {
        return porcPromotor2;
    }

    public void setPorcPromotor2(BigDecimal porcPromotor2) {
        this.porcPromotor2 = porcPromotor2;
    }

    public BigDecimal getPorcPromotor3() {
        return porcPromotor3;
    }

    public void setPorcPromotor3(BigDecimal porcPromotor3) {
        this.porcPromotor3 = porcPromotor3;
    }

    public BigDecimal getPorcGerente1() {
        return porcGerente1;
    }

    public void setPorcGerente1(BigDecimal porcGerente1) {
        this.porcGerente1 = porcGerente1;
    }

    public BigDecimal getPorcGerente2() {
        return porcGerente2;
    }

    public void setPorcGerente2(BigDecimal porcGerente2) {
        this.porcGerente2 = porcGerente2;
    }

    public BigDecimal getPorcGerente3() {
        return porcGerente3;
    }

    public void setPorcGerente3(BigDecimal porcGerente3) {
        this.porcGerente3 = porcGerente3;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    public Usuario getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcalculoComissao != null ? idcalculoComissao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalculoComissao)) {
            return false;
        }
        CalculoComissao other = (CalculoComissao) object;
        if ((this.idcalculoComissao == null && other.idcalculoComissao != null) || (this.idcalculoComissao != null && !this.idcalculoComissao.equals(other.idcalculoComissao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.CalculoComissao[ idcalculoComissao=" + idcalculoComissao + " ]";
    }

   
    
}
