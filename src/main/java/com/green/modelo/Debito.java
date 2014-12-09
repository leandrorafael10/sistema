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
@Table(name = "debito")
public class Debito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDDebito")
    private Integer iDDebito;
    @Basic(optional = false)
    @NotNull
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor")
    private BigDecimal valor;
    @Lob
    @Size(max = 65535)
    @Column(name = "Obs")
    private String obs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @JoinColumn(name = "IDUsuarioAlt", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioAlt;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDTpDocumento", referencedColumnName = "IDDocumento")
    @ManyToOne(optional = false)
    private Documento iDTpDocumento;
    

    public Debito() {
    }

    public Debito(Integer iDDebito) {
        this.iDDebito = iDDebito;
    }

    public Debito(Integer iDDebito, String numero, Date dTBaixa, BigDecimal valor, Date dTInc) {
        this.iDDebito = iDDebito;
        this.numero = numero;
        this.dTBaixa = dTBaixa;
        this.valor = valor;
        this.dTInc = dTInc;
    }

    public Integer getIDDebito() {
        return iDDebito;
    }

    public void setIDDebito(Integer iDDebito) {
        this.iDDebito = iDDebito;
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

    public Usuario getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Documento getIDTpDocumento() {
        return iDTpDocumento;
    }

    public void setIDTpDocumento(Documento iDTpDocumento) {
        this.iDTpDocumento = iDTpDocumento;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDDebito != null ? iDDebito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Debito)) {
            return false;
        }
        Debito other = (Debito) object;
        if ((this.iDDebito == null && other.iDDebito != null) || (this.iDDebito != null && !this.iDDebito.equals(other.iDDebito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Debito[ iDDebito=" + iDDebito + " ]";
    }
    
}
