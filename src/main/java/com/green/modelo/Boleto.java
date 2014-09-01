/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "boleto")
public class Boleto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "idboleto")
    private Integer idboleto;
    @Column(name = "mora")
    private BigDecimal mora;
    @Column(name = "multa")
    private BigDecimal multa;

    @JoinColumn(name = "IDConta", referencedColumnName = "IDConta")
    @ManyToOne(fetch = FetchType.EAGER)
    private Conta iDConta;

    public Integer getIdboleto() {
        return idboleto;
    }

    public void setIdboleto(Integer idboleto) {
        this.idboleto = idboleto;
    }

    public BigDecimal getMora() {
        return mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public void setMulta(BigDecimal multa) {
        this.multa = multa;
    }

    public Conta getIDConta() {
        return iDConta;
    }

    public void setIDConta(Conta iDConta) {
        this.iDConta = iDConta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idboleto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Boleto other = (Boleto) obj;
        if (!Objects.equals(this.idboleto, other.idboleto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Boleto{" + "idboleto=" + idboleto + '}';
    }

}
