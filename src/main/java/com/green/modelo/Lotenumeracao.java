/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "lotenumeracao")
public class Lotenumeracao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLoteNumeracao")
    private Integer iDLoteNumeracao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Numero")
    private int numero;
    @JoinColumn(name = "IDLoteContrato", referencedColumnName = "IDLoteContrato")
    @ManyToOne(optional = false)
    private Lotecontrato iDLoteContrato;

    public Lotenumeracao() {
    }

    public Lotenumeracao(Integer iDLoteNumeracao) {
        this.iDLoteNumeracao = iDLoteNumeracao;
    }

    public Lotenumeracao(Integer iDLoteNumeracao, int numero) {
        this.iDLoteNumeracao = iDLoteNumeracao;
        this.numero = numero;
    }

    public Integer getIDLoteNumeracao() {
        return iDLoteNumeracao;
    }

    public void setIDLoteNumeracao(Integer iDLoteNumeracao) {
        this.iDLoteNumeracao = iDLoteNumeracao;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Lotecontrato getIDLoteContrato() {
        return iDLoteContrato;
    }

    public void setIDLoteContrato(Lotecontrato iDLoteContrato) {
        this.iDLoteContrato = iDLoteContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDLoteNumeracao != null ? iDLoteNumeracao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lotenumeracao)) {
            return false;
        }
        Lotenumeracao other = (Lotenumeracao) object;
        if ((this.iDLoteNumeracao == null && other.iDLoteNumeracao != null) || (this.iDLoteNumeracao != null && !this.iDLoteNumeracao.equals(other.iDLoteNumeracao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Lotenumeracao[ iDLoteNumeracao=" + iDLoteNumeracao + " ]";
    }
    
}
