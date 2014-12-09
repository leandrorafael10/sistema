/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "conta")
public class Conta implements Serializable {

    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @NotNull
    @Column(name = "DTAbertura")
    @Temporal(TemporalType.DATE)
    private Date dTAbertura;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDConta")
    private Integer iDConta;
    @Size(max = 45)
    @Column(name = "Numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "Titular")
    private String titular;
    @Size(max = 15)
    @Column(name = "Agencia")
    private String agencia;
    @Size(max = 3)
    @Column(name = "AgenciaDigito")
    private String agenciaDigito;
    @NotNull
    @Column(name = "Ativa")
    private boolean ativa;
    @JoinColumn(name = "IDUsuarioAlt", referencedColumnName = "IDUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario iDUsuarioAlt;
    @Size(max = 3)
    @Column(name = "NumeroDigito")
    private String numeroDigito;
    @Column(name = "SaldoIni")
    private BigDecimal saldoIni;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Del")
    private boolean del;
    @NotNull
    @Size(max = 45)
    @Column(name = "nomeAgencia")
    private String nomeAgencia;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDTipoConta", referencedColumnName = "idtipoconta")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tipoconta iDTipoConta;
    @JoinColumn(name = "IDEmpresa", referencedColumnName = "IDEmpresa")
    @ManyToOne(optional = false)
    private Empresa iDEmpresa;
    @JoinColumn(name = "IDBanco", referencedColumnName = "IDBanco")
    @ManyToOne(fetch = FetchType.LAZY)
    private Banco iDBanco;
    @OneToOne(mappedBy = "iDConta", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private Boleto iDBoleto;

    public Conta() {
    }

    public Conta(Integer iDConta) {
        this.iDConta = iDConta;
    }

    public Conta(Integer iDConta, String titular, boolean ativa, Date dTInc, Date dTAbertura, BigDecimal saldoIni, boolean del) {
        this.iDConta = iDConta;
        this.titular = titular;
        this.ativa = ativa;
        this.dTInc = dTInc;
        this.dTAbertura = dTAbertura;
        this.saldoIni = saldoIni;
        this.del = del;
    }

    public Integer getIDConta() {
        return iDConta;
    }

    public void setIDConta(Integer iDConta) {
        this.iDConta = iDConta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getAgenciaDigito() {
        return agenciaDigito;
    }

    public void setAgenciaDigito(String agenciaDigito) {
        this.agenciaDigito = agenciaDigito;
    }

    public boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public Usuario getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public String getNumeroDigito() {
        return numeroDigito;
    }

    public void setNumeroDigito(String numeroDigito) {
        this.numeroDigito = numeroDigito;
    }

    public BigDecimal getSaldoIni() {
        return saldoIni;
    }

    public void setSaldoIni(BigDecimal saldoIni) {
        this.saldoIni = saldoIni;
    }

    public boolean getDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Tipoconta getIDTipoConta() {
        return iDTipoConta;
    }

    public void setIDTipoConta(Tipoconta iDTipoConta) {
        this.iDTipoConta = iDTipoConta;
    }

    public Empresa getIDEmpresa() {
        return iDEmpresa;
    }

    public void setIDEmpresa(Empresa iDEmpresa) {
        this.iDEmpresa = iDEmpresa;
    }

    public Banco getIDBanco() {
        return iDBanco;
    }

    public void setIDBanco(Banco iDBanco) {
        this.iDBanco = iDBanco;
    }

    public Boleto getIDBoleto() {
        return iDBoleto;
    }

    public void setIDBoleto(Boleto iDBoleto) {
        this.iDBoleto = iDBoleto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDConta != null ? iDConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.iDConta == null && other.iDConta != null) || (this.iDConta != null && !this.iDConta.equals(other.iDConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Conta[ iDConta=" + iDConta + " ]";
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    public Date getDTAbertura() {
        return dTAbertura;
    }

    public void setDTAbertura(Date dTAbertura) {
        this.dTAbertura = dTAbertura;
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

}
