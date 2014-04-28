/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "log_movimentacao")
public class LogMovimentacao implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idlog_movimentacao")
    private Integer iDLogMovimentacao;
    @Column(name = "valor")
    @NotNull
    private BigDecimal valor;
    @Column(name = "movimentacao")
    @NotNull
    private Integer movimentacao;
    @Column(name = "DTInc")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dTInc;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne
    @NotNull
    private Usuario iDUsuario;
    @Column(name = "DTVenda")
    @Temporal(TemporalType.DATE)
    private Date dTVenda;
    
    @JoinColumn(name="IDcapalotejornal" ,referencedColumnName="IDCapalotejornal")
    @ManyToOne    
    private Capalotejornal iDCapalotejornal;

    public LogMovimentacao() {
    }

    public LogMovimentacao(Integer iDLogMovimentacao, BigDecimal valor, Integer movimentacao, Date dTInc, Usuario iDUsuario, Date dTVenda,Capalotejornal iDcapalotejornal) {
        this.iDLogMovimentacao = iDLogMovimentacao;
        this.valor = valor;
        this.movimentacao = movimentacao;
        this.dTInc = dTInc;
        this.iDUsuario = iDUsuario;
        this.dTVenda = dTVenda;
        this.iDCapalotejornal = iDcapalotejornal;
    }
    
    
    
    

    public Integer getIDLogMovimentacao() {
        return iDLogMovimentacao;
    }

    public void setIDLogMovimentacao(Integer iDLogMovimentacao) {
        this.iDLogMovimentacao = iDLogMovimentacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Integer movimentacao) {
        this.movimentacao = movimentacao;
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

    public Date getDTVenda() {
        return dTVenda;
    }

    public void setDTVenda(Date dTVenda) {
        this.dTVenda = dTVenda;
    }
    

    public Capalotejornal getIDCapalotejornal() {
		return iDCapalotejornal;
	}

	public void setIDCapalotejornal(Capalotejornal iDCapalotejornal) {
		this.iDCapalotejornal = iDCapalotejornal;
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.iDLogMovimentacao);
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
        final LogMovimentacao other = (LogMovimentacao) obj;
        if (!Objects.equals(this.iDLogMovimentacao, other.iDLogMovimentacao)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "LogMovimentacao{" + "iDLogMovimentacao=" + iDLogMovimentacao + '}';
    }
    
    
    
    
}
