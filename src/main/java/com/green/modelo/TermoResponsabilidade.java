/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "termo_responsabilidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TermoResponsabilidade.findAll", query = "SELECT t FROM TermoResponsabilidade t"),
    @NamedQuery(name = "TermoResponsabilidade.findByIDTermoResponsabilidade", query = "SELECT t FROM TermoResponsabilidade t WHERE t.iDTermoResponsabilidade = :iDTermoResponsabilidade"),
    @NamedQuery(name = "TermoResponsabilidade.findByEntradaSaida", query = "SELECT t FROM TermoResponsabilidade t WHERE t.entradaSaida = :entradaSaida"),
    @NamedQuery(name = "TermoResponsabilidade.findByDTInc", query = "SELECT t FROM TermoResponsabilidade t WHERE t.dTInc = :dTInc"),
    @NamedQuery(name = "TermoResponsabilidade.findByDataTermo", query = "SELECT t FROM TermoResponsabilidade t WHERE t.dataTermo = :dataTermo")})
public class TermoResponsabilidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTermo_Responsabilidade")
    private Integer iDTermoResponsabilidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entrada_saida")
    private boolean entradaSaida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataTermo")
    @Temporal(TemporalType.DATE)
    private Date dataTermo;
    @OneToMany(mappedBy = "iDTermoResponsabilidade",cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<BrindeTermo> brindeTermoList;
    @JoinColumn(name = "IDBrindemovimentacao", referencedColumnName = "IDBrindemovimentacao")
    @ManyToOne
    private Brindemovimentacao iDBrindemovimentacao;
    @JoinColumn(name = "IDFuncionario" ,referencedColumnName = "IDFuncionario")
    @ManyToOne
    private Funcionario iDFuncionario;
    @JoinColumn(name = "IDUsuario" ,referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuario;

    public TermoResponsabilidade() {
    }

    public TermoResponsabilidade(Integer iDTermoResponsabilidade) {
        this.iDTermoResponsabilidade = iDTermoResponsabilidade;
    }

    public TermoResponsabilidade(Integer iDTermoResponsabilidade, boolean entradaSaida, Date dTInc, Date dataTermo) {
        this.iDTermoResponsabilidade = iDTermoResponsabilidade;
        this.entradaSaida = entradaSaida;
        this.dTInc = dTInc;
        this.dataTermo = dataTermo;
    }

    public Integer getIDTermoResponsabilidade() {
        return iDTermoResponsabilidade;
    }

    public void setIDTermoResponsabilidade(Integer iDTermoResponsabilidade) {
        this.iDTermoResponsabilidade = iDTermoResponsabilidade;
    }

    public boolean getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(boolean entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Date getDataTermo() {
        return dataTermo;
    }

    public void setDataTermo(Date dataTermo) {
        this.dataTermo = dataTermo;
    }

    
    public List<BrindeTermo> getBrindeTermoList() {
        return brindeTermoList;
    }

    public void setBrindeTermoList(List<BrindeTermo> brindeTermoList) {
        this.brindeTermoList = brindeTermoList;
    }

    public Brindemovimentacao getIDBrindemovimentacao() {
        return iDBrindemovimentacao;
    }

    public void setIDBrindemovimentacao(Brindemovimentacao iDBrindemovimentacao) {
        this.iDBrindemovimentacao = iDBrindemovimentacao;
    }

    public Funcionario getIDFuncionario() {
        return iDFuncionario;
    }

    public void setIDFuncionario(Funcionario iDFuncionario) {
        this.iDFuncionario = iDFuncionario;
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
        hash += (iDTermoResponsabilidade != null ? iDTermoResponsabilidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TermoResponsabilidade)) {
            return false;
        }
        TermoResponsabilidade other = (TermoResponsabilidade) object;
        if ((this.iDTermoResponsabilidade == null && other.iDTermoResponsabilidade != null) || (this.iDTermoResponsabilidade != null && !this.iDTermoResponsabilidade.equals(other.iDTermoResponsabilidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.TermoResponsabilidade[ iDTermoResponsabilidade=" + iDTermoResponsabilidade + " ]";
    }
    
}
