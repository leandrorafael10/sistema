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
@Table(name = "tipoconta")
public class Tipoconta implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idtipoconta")
    private Integer idtipoconta;
    @Basic(optional = false)
    @NotNull
    @Size( max = 45)
    @Column(name = "Descricao")
    private String descricao;
    @JoinColumn(name = "IDUsuarioAlt",referencedColumnName="IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioAlt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cxint")
    private boolean cxint;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    

    public Tipoconta() {
    }

    public Tipoconta(Integer idtipoconta) {
        this.idtipoconta = idtipoconta;
    }

    public Tipoconta(Integer idtipoconta, String descricao, Date dTInc, boolean cxint) {
        this.idtipoconta = idtipoconta;
        this.descricao = descricao;
        this.dTInc = dTInc;
        this.cxint = cxint;
    }

    public Integer getIdtipoconta() {
        return idtipoconta;
    }

    public void setIdtipoconta(Integer idtipoconta) {
        this.idtipoconta = idtipoconta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public boolean getCxint() {
        return cxint;
    }

    public void setCxint(boolean cxint) {
        this.cxint = cxint;
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
        hash += (idtipoconta != null ? idtipoconta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoconta)) {
            return false;
        }
        Tipoconta other = (Tipoconta) object;
        if ((this.idtipoconta == null && other.idtipoconta != null) || (this.idtipoconta != null && !this.idtipoconta.equals(other.idtipoconta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Tipoconta[ idtipoconta=" + idtipoconta + " ]";
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
