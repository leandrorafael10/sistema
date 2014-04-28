/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "cheque")
@NamedQueries({
    @NamedQuery(name = "Cheque.findAll", query = "SELECT c FROM Cheque c")})
public class Cheque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCheque")
    private Integer iDCheque;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Banco")
    private String banco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Agencia")
    private String agencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Conta")
    private String conta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Codigo")
    private float codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor")
    private float valor;
    

    public Cheque() {
    }

    public Cheque(Integer iDCheque) {
        this.iDCheque = iDCheque;
    }

    public Cheque(Integer iDCheque, String banco, String agencia, String conta, float codigo, float valor) {
        this.iDCheque = iDCheque;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.codigo = codigo;
        this.valor = valor;
    }

    public Integer getIDCheque() {
        return iDCheque;
    }

    public void setIDCheque(Integer iDCheque) {
        this.iDCheque = iDCheque;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public float getCodigo() {
        return codigo;
    }

    public void setCodigo(float codigo) {
        this.codigo = codigo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCheque != null ? iDCheque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cheque)) {
            return false;
        }
        Cheque other = (Cheque) object;
        if ((this.iDCheque == null && other.iDCheque != null) || (this.iDCheque != null && !this.iDCheque.equals(other.iDCheque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Cheque[ iDCheque=" + iDCheque + " ]";
    }
    
}
