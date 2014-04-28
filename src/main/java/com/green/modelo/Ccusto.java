/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "ccusto")
public class Ccusto implements Serializable {
    @Basic(optional =     false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name =     "DTalt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTalt;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDCCusto")
    private Integer iDCCusto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "Codigo")
    private String codigo;
    @JoinColumn(name = "IDUsuarioAlt", referencedColumnName = "IDUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario iDUsuarioAlt;
    @Column(name = "ativo" )
    private Boolean ativo;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Usuario iDUsuario;

    public Ccusto() {
    }

    public Ccusto(Integer iDCCusto) {
        this.iDCCusto = iDCCusto;
    }

    public Ccusto(Integer iDCCusto, String descricao, String codigo, Date dTInc) {
        this.iDCCusto = iDCCusto;
        this.descricao = descricao;
        this.codigo = codigo;
        this.dTInc = dTInc;
    }

    public Integer getIDCCusto() {
        return iDCCusto;
    }

    public void setIDCCusto(Integer iDCCusto) {
        this.iDCCusto = iDCCusto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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
        hash += (iDCCusto != null ? iDCCusto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ccusto)) {
            return false;
        }
        Ccusto other = (Ccusto) object;
        if ((this.iDCCusto == null && other.iDCCusto != null) || (this.iDCCusto != null && !this.iDCCusto.equals(other.iDCCusto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Ccusto[ iDCCusto=" + iDCCusto + " ]";
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Date getDTalt() {
        return dTalt;
    }

    public void setDTalt(Date dTalt) {
        this.dTalt = dTalt;
    }
    
}
