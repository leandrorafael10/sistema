/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "acesso")
public class Acesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "IDAcesso")
    private Integer iDAcesso;
    @Size(max = 100)
    @Column(name = "IDTela")
    private String iDTela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TipoAcesso")
    private int tipoAcesso;
    @JoinColumn(name = "IDGrupoAcesso", referencedColumnName = "IDGrupoAcesso")
    @ManyToOne(optional = false)
    private Grupoacesso iDGrupoAcesso;

    public Acesso() {
    }

    public Acesso(Integer iDAcesso) {
        this.iDAcesso = iDAcesso;
    }

    public Acesso(Integer iDAcesso, int tipoAcesso) {
        this.iDAcesso = iDAcesso;
        this.tipoAcesso = tipoAcesso;
    }

    public Integer getIDAcesso() {
        return iDAcesso;
    }

    public void setIDAcesso(Integer iDAcesso) {
        this.iDAcesso = iDAcesso;
    }

    public String getIDTela() {
        return iDTela;
    }

    public void setIDTela(String iDTela) {
        this.iDTela = iDTela;
    }

    public int getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(int tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public Grupoacesso getIDGrupoAcesso() {
        return iDGrupoAcesso;
    }

    public void setIDGrupoAcesso(Grupoacesso iDGrupoAcesso) {
        this.iDGrupoAcesso = iDGrupoAcesso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDAcesso != null ? iDAcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acesso)) {
            return false;
        }
        Acesso other = (Acesso) object;
        if ((this.iDAcesso == null && other.iDAcesso != null) || (this.iDAcesso != null && !this.iDAcesso.equals(other.iDAcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Acesso[ iDAcesso=" + iDAcesso + " ]";
    }
    
}
