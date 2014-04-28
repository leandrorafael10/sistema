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
@Table(name = "subcanal")
public class Subcanal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSubCanal")
    private Integer iDSubCanal;
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
    @Column(name = "CodigoSubCanal")
    private String codigoSubCanal;
    @JoinColumn(name = "IDusuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDusuario;
    
    public Subcanal() {
    }

    public Subcanal(Integer iDSubCanal) {
        this.iDSubCanal = iDSubCanal;
    }

    public Subcanal(Integer iDSubCanal, String descricao, Date dtinc, String codigoSubCanal) {
        this.iDSubCanal = iDSubCanal;
        this.descricao = descricao;
        this.dtinc = dtinc;
        this.codigoSubCanal = codigoSubCanal;
    }

    public Integer getIDSubCanal() {
        return iDSubCanal;
    }

    public void setIDSubCanal(Integer iDSubCanal) {
        this.iDSubCanal = iDSubCanal;
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

    public String getCodigoSubCanal() {
        return codigoSubCanal;
    }

    public void setCodigoSubCanal(String codigoSubCanal) {
        this.codigoSubCanal = codigoSubCanal;
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
        hash += (iDSubCanal != null ? iDSubCanal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subcanal)) {
            return false;
        }
        Subcanal other = (Subcanal) object;
        if ((this.iDSubCanal == null && other.iDSubCanal != null) || (this.iDSubCanal != null && !this.iDSubCanal.equals(other.iDSubCanal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Subcanal[ iDSubCanal=" + iDSubCanal + " ]";
    }
    
}
