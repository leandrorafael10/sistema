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
@Table(name = "credito")
public class Credito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDCredito")
    private Integer iDCredito;
    @Column(name = "Numero")
    private String numero;
    @NotNull
    @Column(name = "DTBaixa")
    @Temporal(TemporalType.DATE)
    private Date dTBaixa;
    @Column(name = "DTConciliacao")
    @Temporal(TemporalType.DATE)
    private Date dTConciliacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Lob
    @Size(max = 65535)
    @Column(name = "obs")
    private String obs;
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDUsuarioAlt", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioAlt;
    @JoinColumn(name = "IDTpDocumento", referencedColumnName = "IDDocumento")
    @ManyToOne(optional = false)
    private Documento iDTpDocumento;
    @JoinColumn(name = "IDConta", referencedColumnName = "IDConta")
    @ManyToOne(optional = false)
    private Conta iDConta;
    @JoinColumn(name = "IDCCusto", referencedColumnName = "IDCCusto")
    @ManyToOne(optional = false)
    private Ccusto iDCCusto;
    @JoinColumn(name = "IDClassificacao", referencedColumnName = "IDClassificacao")
    @ManyToOne(optional = false)
    private Classificacao iDClassificacao;
    @JoinColumn(name = "IDAtividade", referencedColumnName = "IDAtividade")
    @ManyToOne(optional = false)
    private Atividade iDAtividade;

    public Credito() {
    }

    public Credito(Integer iDCredito) {
        this.iDCredito = iDCredito;
    }

    public Credito(Integer iDCredito, String numero, Date dTBaixa) {
        this.iDCredito = iDCredito;
        this.numero = numero;
        this.dTBaixa = dTBaixa;
    }

    public Integer getIDCredito() {
        return iDCredito;
    }

    public void setIDCredito(Integer iDCredito) {
        this.iDCredito = iDCredito;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDTBaixa() {
        return dTBaixa;
    }

    public void setDTBaixa(Date dTBaixa) {
        this.dTBaixa = dTBaixa;
    }

    public Date getDTConciliacao() {
        return dTConciliacao;
    }

    public void setDTConciliacao(Date dTConciliacao) {
        this.dTConciliacao = dTConciliacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Usuario getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public Documento getIDTpDocumento() {
        return iDTpDocumento;
    }

    public void setIDTpDocumento(Documento iDTpDocumento) {
        this.iDTpDocumento = iDTpDocumento;
    }

    public Conta getIDConta() {
        return iDConta;
    }

    public void setIDConta(Conta iDConta) {
        this.iDConta = iDConta;
    }

    public Ccusto getIDCCusto() {
        return iDCCusto;
    }

    public void setIDCCusto(Ccusto iDCCusto) {
        this.iDCCusto = iDCCusto;
    }

    public Classificacao getIDClassificacao() {
        return iDClassificacao;
    }

    public void setIDClassificacao(Classificacao iDClassificacao) {
        this.iDClassificacao = iDClassificacao;
    }

    public Atividade getIDAtividade() {
        return iDAtividade;
    }

    public void setIDAtividade(Atividade iDAtividade) {
        this.iDAtividade = iDAtividade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCredito != null ? iDCredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credito)) {
            return false;
        }
        Credito other = (Credito) object;
        if ((this.iDCredito == null && other.iDCredito != null) || (this.iDCredito != null && !this.iDCredito.equals(other.iDCredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Credito[ iDCredito=" + iDCredito + " ]";
    }
    
}
