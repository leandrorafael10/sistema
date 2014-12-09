/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "desconto")
public class Desconto implements Serializable {
    @OneToMany(mappedBy = "iDDesconto")
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDesconto")
    private Integer iDDesconto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Percentual")
    private String percentual;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;

    public Desconto() {
    }

    public Desconto(Integer iDDesconto) {
        this.iDDesconto = iDDesconto;
    }

    public Desconto(Integer iDDesconto, String descricao, String percentual, Date dTIni) {
        this.iDDesconto = iDDesconto;
        this.descricao = descricao;
        this.percentual = percentual;
        this.dTIni = dTIni;
    }

    public Integer getIDDesconto() {
        return iDDesconto;
    }

    public void setIDDesconto(Integer iDDesconto) {
        this.iDDesconto = iDDesconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPercentual() {
        return percentual;
    }

    public void setPercentual(String percentual) {
        this.percentual = percentual;
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
        hash += (iDDesconto != null ? iDDesconto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Desconto)) {
            return false;
        }
        Desconto other = (Desconto) object;
        if ((this.iDDesconto == null && other.iDDesconto != null) || (this.iDDesconto != null && !this.iDDesconto.equals(other.iDDesconto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Desconto[ iDDesconto=" + iDDesconto + " ]";
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

    public List<Fornecedor> getFornecedorList() {
        return fornecedorList;
    }

    public void setFornecedorList(List<Fornecedor> fornecedorList) {
        this.fornecedorList = fornecedorList;
    }

    
    
}
