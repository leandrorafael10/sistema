/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "limite")
public class Limite implements Serializable {
    @OneToMany(mappedBy = "iDLimite")
    private List<Fornecedor> fornecedorList;
    
    @Basic(optional =     false)
    @NotNull
    @Column(name = "DTIni")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTIni;
    @Column(name =     "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDLimite")
    private Integer iDLimite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor")
    private long valor;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
   

    public Limite() {
    }

    public Limite(Integer iDLimite) {
        this.iDLimite = iDLimite;
    }

    public Limite(Integer iDLimite, String descricao, long valor, Date dTIni) {
        this.iDLimite = iDLimite;
        this.descricao = descricao;
        this.valor = valor;
        this.dTIni = dTIni;
    }

    public Integer getIDLimite() {
        return iDLimite;
    }

    public void setIDLimite(Integer iDLimite) {
        this.iDLimite = iDLimite;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
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
        hash += (iDLimite != null ? iDLimite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Limite)) {
            return false;
        }
        Limite other = (Limite) object;
        if ((this.iDLimite == null && other.iDLimite != null) || (this.iDLimite != null && !this.iDLimite.equals(other.iDLimite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Limite[ iDLimite=" + iDLimite + " ]";
    }

    public Date getDTIni() {
        return dTIni;
    }

    public void setDTIni(Date dTIni) {
        this.dTIni = dTIni;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    @XmlTransient
    @JsonIgnore
    public List<Fornecedor> getFornecedorList() {
        return fornecedorList;
    }

    public void setFornecedorList(List<Fornecedor> fornecedorList) {
        this.fornecedorList = fornecedorList;
    }

    
}
