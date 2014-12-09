/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "brinde_termo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BrindeTermo.findAll", query = "SELECT b FROM BrindeTermo b"),
    @NamedQuery(name = "BrindeTermo.findByIDBrindetermo", query = "SELECT b FROM BrindeTermo b WHERE b.iDBrindetermo = :iDBrindetermo")})
public class BrindeTermo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDBrinde_termo")
    private Integer iDBrindetermo;
    @JoinColumn(name = "IDTermoResponsabilidade", referencedColumnName = "IDTermo_Responsabilidade")
    @ManyToOne
    private TermoResponsabilidade iDTermoResponsabilidade;
    @JoinColumn(name = "IDBrinde", referencedColumnName = "IDBrinde")
    @ManyToOne
    private Brinde iDBrinde;
    @Column(name = "Qtd")
    @NotNull
    private int qtd;
    
    


    public BrindeTermo() {
    }
    

    public BrindeTermo(Integer iDBrindetermo) {
        this.iDBrindetermo = iDBrindetermo;
    }

    public BrindeTermo(TermoResponsabilidade iDTermoResponsabilidade, Brinde iDBrinde) {
        this.iDTermoResponsabilidade = iDTermoResponsabilidade;
        this.iDBrinde = iDBrinde;
    }

    public BrindeTermo(TermoResponsabilidade iDTermoResponsabilidade, Brinde iDBrinde, int qtd) {
        this.iDTermoResponsabilidade = iDTermoResponsabilidade;
        this.iDBrinde = iDBrinde;
        this.qtd = qtd;
    }
    
    

    public Integer getIDBrindetermo() {
        return iDBrindetermo;
    }

    public void setIDBrindetermo(Integer iDBrindetermo) {
        this.iDBrindetermo = iDBrindetermo;
    }

    public TermoResponsabilidade getIDTermoResponsabilidade() {
        return iDTermoResponsabilidade;
    }

    public void setIDTermoResponsabilidade(TermoResponsabilidade iDTermoResponsabilidade) {
        this.iDTermoResponsabilidade = iDTermoResponsabilidade;
    }

    public Brinde getIDBrinde() {
        return iDBrinde;
    }

    public void setIDBrinde(Brinde iDBrinde) {
        this.iDBrinde = iDBrinde;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDBrindetermo != null ? iDBrindetermo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BrindeTermo)) {
            return false;
        }
        BrindeTermo other = (BrindeTermo) object;
        if ((this.iDBrindetermo == null && other.iDBrindetermo != null) || (this.iDBrindetermo != null && !this.iDBrindetermo.equals(other.iDBrindetermo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.BrindeTermo[ iDBrindetermo=" + iDBrindetermo + " ]";
    }
    
}
