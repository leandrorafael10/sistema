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
@Table(name = "brindemovimentacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Brindemovimentacao.findAll", query = "SELECT b FROM Brindemovimentacao b"),
    @NamedQuery(name = "Brindemovimentacao.findByIDBrindemovimentacao", query = "SELECT b FROM Brindemovimentacao b WHERE b.iDBrindemovimentacao = :iDBrindemovimentacao"),
    @NamedQuery(name = "Brindemovimentacao.findByDTInc", query = "SELECT b FROM Brindemovimentacao b WHERE b.dTInc = :dTInc"),
    @NamedQuery(name = "Brindemovimentacao.findByIDUsuario", query = "SELECT b FROM Brindemovimentacao b WHERE b.iDUsuario = :iDUsuario")})
public class Brindemovimentacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDBrindemovimentacao")
    private Integer iDBrindemovimentacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @JoinColumn(name = "IDUsuario",referencedColumnName = "IDUsuario")
    @Basic(optional = false)
    @NotNull
    @ManyToOne
    private Usuario iDUsuario;
    @OneToMany(mappedBy = "iDBrindemovimentacao",cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<TermoResponsabilidade> termoResponsabilidadeList;

    public Brindemovimentacao() {
    }

    public Brindemovimentacao(Integer iDBrindemovimentacao) {
        this.iDBrindemovimentacao = iDBrindemovimentacao;
    }

    

    public Integer getIDBrindemovimentacao() {
        return iDBrindemovimentacao;
    }

    public void setIDBrindemovimentacao(Integer iDBrindemovimentacao) {
        this.iDBrindemovimentacao = iDBrindemovimentacao;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    

   
    public List<TermoResponsabilidade> getTermoResponsabilidadeList() {
        return termoResponsabilidadeList;
    }

    public void setTermoResponsabilidadeList(List<TermoResponsabilidade> termoResponsabilidadeList) {
        this.termoResponsabilidadeList = termoResponsabilidadeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDBrindemovimentacao != null ? iDBrindemovimentacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Brindemovimentacao)) {
            return false;
        }
        Brindemovimentacao other = (Brindemovimentacao) object;
        if ((this.iDBrindemovimentacao == null && other.iDBrindemovimentacao != null) || (this.iDBrindemovimentacao != null && !this.iDBrindemovimentacao.equals(other.iDBrindemovimentacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Brindemovimentacao[ iDBrindemovimentacao=" + iDBrindemovimentacao + " ]";
    }
    
}
