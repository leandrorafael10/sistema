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
@Table(name = "tipocliente")
public class Tipocliente implements Serializable {
    @Basic(optional =     false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name =     "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDTipocliente")
    private Integer iDTipocliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @Column(name = "IDTipobloqueio")
    private Integer iDTipobloqueio;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;

    public Tipocliente() {
    }

    public Tipocliente(Integer iDTipocliente) {
        this.iDTipocliente = iDTipocliente;
    }

    public Tipocliente(Integer iDTipocliente, String descricao, Date dTInc) {
        this.iDTipocliente = iDTipocliente;
        this.descricao = descricao;
        this.dTInc = dTInc;
    }

    public Integer getIDTipocliente() {
        return iDTipocliente;
    }

    public void setIDTipocliente(Integer iDTipocliente) {
        this.iDTipocliente = iDTipocliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Integer iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public Integer getIDTipobloqueio() {
        return iDTipobloqueio;
    }

    public void setIDTipobloqueio(Integer iDTipobloqueio) {
        this.iDTipobloqueio = iDTipobloqueio;
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
        hash += (iDTipocliente != null ? iDTipocliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocliente)) {
            return false;
        }
        Tipocliente other = (Tipocliente) object;
        if ((this.iDTipocliente == null && other.iDTipocliente != null) || (this.iDTipocliente != null && !this.iDTipocliente.equals(other.iDTipocliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Tipocliente[ iDTipocliente=" + iDTipocliente + " ]";
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
    
}
