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
@Table(name = "bandeira")
public class Bandeira implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDBandeira")
    private Integer iDBandeira;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CCredito")
    private int cCredito;

    public Bandeira() {
    }

    public Bandeira(Integer iDBandeira) {
        this.iDBandeira = iDBandeira;
    }

    public Bandeira(Integer iDBandeira, String descricao, int cCredito) {
        this.iDBandeira = iDBandeira;
        this.descricao = descricao;
        this.cCredito = cCredito;
    }

    public Integer getIDBandeira() {
        return iDBandeira;
    }

    public void setIDBandeira(Integer iDBandeira) {
        this.iDBandeira = iDBandeira;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCCredito() {
        return cCredito;
    }

    public void setCCredito(int cCredito) {
        this.cCredito = cCredito;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDBandeira != null ? iDBandeira.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bandeira)) {
            return false;
        }
        Bandeira other = (Bandeira) object;
        if ((this.iDBandeira == null && other.iDBandeira != null) || (this.iDBandeira != null && !this.iDBandeira.equals(other.iDBandeira))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Bandeira[ iDBandeira=" + iDBandeira + " ]";
    }
    
}
