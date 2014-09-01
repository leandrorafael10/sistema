/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "brindeentrada")
public class BrindeEntrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDBrindeEntrada")
    private Integer iDBrindeEntrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantidade")
    private int quantidade;

    @JoinColumn(name = "IDBrinde", referencedColumnName = "IDBrinde")
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE})
    private Brinde iDBrinde;
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @Column(name = "DTEntrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTEntrada;

    public BrindeEntrada() {
    }

    public BrindeEntrada(Integer iDBrindeEntrada) {
        this.iDBrindeEntrada = iDBrindeEntrada;
    }

    public BrindeEntrada(Integer iDBrindeEntrada, int quantidade) {
        this.iDBrindeEntrada = iDBrindeEntrada;
        this.quantidade = quantidade;
    }

    public Integer getIDBrindeEntrada() {
        return iDBrindeEntrada;
    }

    public void setIDBrindeEntrada(Integer iDBrindeEntrada) {
        this.iDBrindeEntrada = iDBrindeEntrada;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Brinde getIDBrinde() {
        return iDBrinde;
    }

    public void setIDBrinde(Brinde iDBrinde) {
        this.iDBrinde = iDBrinde;
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

    public Date getDTEntrada() {
        return dTEntrada;
    }

    public void setDTEntrada(Date dTEntrada) {
        this.dTEntrada = dTEntrada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDBrindeEntrada != null ? iDBrindeEntrada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BrindeEntrada)) {
            return false;
        }
        BrindeEntrada other = (BrindeEntrada) object;
        if ((this.iDBrindeEntrada == null && other.iDBrindeEntrada != null) || (this.iDBrindeEntrada != null && !this.iDBrindeEntrada.equals(other.iDBrindeEntrada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.BrindeEntrada[ iDBrindeEntrada=" + iDBrindeEntrada + " ]";
    }

}
