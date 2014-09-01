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
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "grupoacesso")
public class Grupoacesso implements Serializable {
    @OneToMany(mappedBy = "iDGrupoAcesso")
    private List<Usuario> usuarioList;
    
    @Basic(optional =     false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name =     "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @Column(name =     "IDUsuario")
    private Integer iDUsuario;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDGrupoAcesso")
    private Integer iDGrupoAcesso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    

    public Grupoacesso() {
    }

    public Grupoacesso(Integer iDGrupoAcesso) {
        this.iDGrupoAcesso = iDGrupoAcesso;
    }

    public Grupoacesso(Integer iDGrupoAcesso, String descricao, int iDUsuario, Date dTInc) {
        this.iDGrupoAcesso = iDGrupoAcesso;
        this.descricao = descricao;
        this.iDUsuario = iDUsuario;
        this.dTInc = dTInc;
    }

    public Integer getIDGrupoAcesso() {
        return iDGrupoAcesso;
    }

    public void setIDGrupoAcesso(Integer iDGrupoAcesso) {
        this.iDGrupoAcesso = iDGrupoAcesso;
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

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDGrupoAcesso != null ? iDGrupoAcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupoacesso)) {
            return false;
        }
        Grupoacesso other = (Grupoacesso) object;
        if ((this.iDGrupoAcesso == null && other.iDGrupoAcesso != null) || (this.iDGrupoAcesso != null && !this.iDGrupoAcesso.equals(other.iDGrupoAcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Grupoacesso[ iDGrupoAcesso=" + iDGrupoAcesso + " ]";
    }

    public Integer getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Integer iDUsuario) {
        this.iDUsuario = iDUsuario;
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

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    
    
}
