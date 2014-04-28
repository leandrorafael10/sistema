/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "operadora")
public class Operadora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDOperadora")
    private Integer iDOperadora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descricao")
    private String descricao;
    
    public Operadora() {
    }

    public Operadora(Integer iDOperadora) {
        this.iDOperadora = iDOperadora;
    }

    public Operadora(Integer iDOperadora, String descricao) {
        this.iDOperadora = iDOperadora;
        this.descricao = descricao;
    }

    public Integer getIDOperadora() {
        return iDOperadora;
    }

    public void setIDOperadora(Integer iDOperadora) {
        this.iDOperadora = iDOperadora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDOperadora != null ? iDOperadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operadora)) {
            return false;
        }
        Operadora other = (Operadora) object;
        if ((this.iDOperadora == null && other.iDOperadora != null) || (this.iDOperadora != null && !this.iDOperadora.equals(other.iDOperadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Operadora[ iDOperadora=" + iDOperadora + " ]";
    }
    
}
