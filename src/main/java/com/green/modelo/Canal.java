/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "canal")
public class Canal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCanal")
    private Integer iDCanal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dtinc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinc;
    @Column(name = "IDusuarioalt")
    private Integer iDusuarioalt;
    @Column(name = "Dtalt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtalt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CodigoCanal")
    private String codigoCanal;
    
    @JoinColumn(name = "IDusuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDusuario;

    public Canal() {
    }

    public Canal(Integer iDCanal) {
        this.iDCanal = iDCanal;
    }

    public Canal(Integer iDCanal, String descricao, Date dtinc, String codigoCanal) {
        this.iDCanal = iDCanal;
        this.descricao = descricao;
        this.dtinc = dtinc;
        this.codigoCanal = codigoCanal;
    }

    public Integer getIDCanal() {
        return iDCanal;
    }

    public void setIDCanal(Integer iDCanal) {
        this.iDCanal = iDCanal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtinc() {
        return dtinc;
    }

    public void setDtinc(Date dtinc) {
        this.dtinc = dtinc;
    }

    public Integer getIDusuarioalt() {
        return iDusuarioalt;
    }

    public void setIDusuarioalt(Integer iDusuarioalt) {
        this.iDusuarioalt = iDusuarioalt;
    }

    public Date getDtalt() {
        return dtalt;
    }

    public void setDtalt(Date dtalt) {
        this.dtalt = dtalt;
    }

    public String getCodigoCanal() {
        return codigoCanal;
    }

    public void setCodigoCanal(String codigoCanal) {
        this.codigoCanal = codigoCanal;
    }


    public Usuario getIDusuario() {
        return iDusuario;
    }

    public void setIDusuario(Usuario iDusuario) {
        this.iDusuario = iDusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCanal != null ? iDCanal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Canal)) {
            return false;
        }
        Canal other = (Canal) object;
        if ((this.iDCanal == null && other.iDCanal != null) || (this.iDCanal != null && !this.iDCanal.equals(other.iDCanal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Canal[ iDCanal=" + iDCanal + " ]";
    }
    
}
