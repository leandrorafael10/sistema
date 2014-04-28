/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "autorizacao")
public class Autorizacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDAutorizacao")
    private Integer iDAutorizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTLimite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTLimite;
    @Size(max = 100)
    @Column(name = "Obs")
    private String obs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ativo")
    private short ativo;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDReceita", referencedColumnName = "IDReceita")
    @ManyToOne
    private Receita iDReceita;
    @JoinColumn(name = "IDDespesa", referencedColumnName = "IDDespesa")
    @ManyToOne
    private Despesa iDDespesa;

    public Autorizacao() {
    }

    public Autorizacao(Integer iDAutorizacao) {
        this.iDAutorizacao = iDAutorizacao;
    }

    public Autorizacao(Integer iDAutorizacao, Date dTInc, Date dTLimite, short ativo) {
        this.iDAutorizacao = iDAutorizacao;
        this.dTInc = dTInc;
        this.dTLimite = dTLimite;
        this.ativo = ativo;
    }

    public Integer getIDAutorizacao() {
        return iDAutorizacao;
    }

    public void setIDAutorizacao(Integer iDAutorizacao) {
        this.iDAutorizacao = iDAutorizacao;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Date getDTLimite() {
        return dTLimite;
    }

    public void setDTLimite(Date dTLimite) {
        this.dTLimite = dTLimite;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public short getAtivo() {
        return ativo;
    }

    public void setAtivo(short ativo) {
        this.ativo = ativo;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Receita getIDReceita() {
        return iDReceita;
    }

    public void setIDReceita(Receita iDReceita) {
        this.iDReceita = iDReceita;
    }

    public Despesa getIDDespesa() {
        return iDDespesa;
    }

    public void setIDDespesa(Despesa iDDespesa) {
        this.iDDespesa = iDDespesa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDAutorizacao != null ? iDAutorizacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autorizacao)) {
            return false;
        }
        Autorizacao other = (Autorizacao) object;
        if ((this.iDAutorizacao == null && other.iDAutorizacao != null) || (this.iDAutorizacao != null && !this.iDAutorizacao.equals(other.iDAutorizacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Autorizacao[ iDAutorizacao=" + iDAutorizacao + " ]";
    }
    
}
