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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "terceiros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Terceiros.findAll", query = "SELECT t FROM Terceiros t")})
public class Terceiros implements Serializable {
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "IDTerceiros")
    private Integer iDTerceiros;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.DATE)
    private Date dTInc;
    @Column(name = "ativo")
    private Boolean ativo;
    @Column(name = "DTCancel")
    @Temporal(TemporalType.DATE)
    private Date dTCancel;
    @Column(name = "uf")
    @NotNull
    @Size(max = 2)
    private String uf;
    @JoinColumn(name = "IDfuncao", referencedColumnName = "IDFuncao")
    @ManyToOne(optional = false)
    private Funcao iDfuncao;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDTerceirosGerente", referencedColumnName = "IDTerceiros")
    @ManyToOne
    private Terceiros iDTerceirosGerente;

    public Terceiros() {
    }

    public Terceiros(Integer iDTerceiros) {
        this.iDTerceiros = iDTerceiros;
    }

    public Terceiros(Integer iDTerceiros, String nome, String cpf, Date dTInc) {
        this.iDTerceiros = iDTerceiros;
        this.nome = nome;
        this.cpf = cpf;
        this.dTInc = dTInc;
    }

    public Integer getIDTerceiros() {
        return iDTerceiros;
    }

    public void setIDTerceiros(Integer iDTerceiros) {
        this.iDTerceiros = iDTerceiros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDTCancel() {
        return dTCancel;
    }

    public void setDTCancel(Date dTCancel) {
        this.dTCancel = dTCancel;
    }

    public Funcao getIDfuncao() {
        return iDfuncao;
    }

    public void setIDfuncao(Funcao iDfuncao) {
        this.iDfuncao = iDfuncao;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Terceiros getIDTerceirosGerente() {
        return iDTerceirosGerente;
    }

    public void setIDTerceirosGerente(Terceiros iDTerceirosGerente) {
        this.iDTerceirosGerente = iDTerceirosGerente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDTerceiros != null ? iDTerceiros.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terceiros)) {
            return false;
        }
        Terceiros other = (Terceiros) object;
        if ((this.iDTerceiros == null && other.iDTerceiros != null) || (this.iDTerceiros != null && !this.iDTerceiros.equals(other.iDTerceiros))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Terceiros[ iDTerceiros=" + iDTerceiros + " ]";
    }

    
    
}
