/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "tipobloqueio")
public class Tipobloqueio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDTipobloqueio")
    private Integer iDTipobloqueio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;

    public Tipobloqueio() {
    }

    public Tipobloqueio(Integer iDTipobloqueio) {
        this.iDTipobloqueio = iDTipobloqueio;
    }

    public Tipobloqueio(Integer iDTipobloqueio, String descricao, Date dTInc) {
        this.iDTipobloqueio = iDTipobloqueio;
        this.descricao = descricao;
        this.dTInc = dTInc;
    }

    public Integer getIDTipobloqueio() {
        return iDTipobloqueio;
    }

    public void setIDTipobloqueio(Integer iDTipobloqueio) {
        this.iDTipobloqueio = iDTipobloqueio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Integer getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Integer iDUsuarioAlt) {
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
        hash += (iDTipobloqueio != null ? iDTipobloqueio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipobloqueio)) {
            return false;
        }
        Tipobloqueio other = (Tipobloqueio) object;
        if ((this.iDTipobloqueio == null && other.iDTipobloqueio != null) || (this.iDTipobloqueio != null && !this.iDTipobloqueio.equals(other.iDTipobloqueio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Tipobloqueio[ iDTipobloqueio=" + iDTipobloqueio + " ]";
    }
    
}
