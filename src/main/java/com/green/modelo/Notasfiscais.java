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
@Table(name = "notasfiscais")
public class Notasfiscais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDNotasFiscais")
    private Integer iDNotasFiscais;
    @Column(name = "Tipo")
    private Integer tipo;
    @Size(max = 45)
    @Column(name = "NaturezaOP")
    private String naturezaOP;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Serie")
    private String serie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "Numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataNF")
    @Temporal(TemporalType.DATE)
    private Date dataNF;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataMov")
    @Temporal(TemporalType.DATE)
    private Date dataMov;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BaseICMS")
    private float baseICMS;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorICMS")
    private float valorICMS;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BaseST")
    private float baseST;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorST")
    private float valorST;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorFrete")
    private float valorFrete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TipoFrete")
    private boolean tipoFrete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorIPI")
    private float valorIPI;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OutrasDesp")
    private float outrasDesp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorSeguro")
    private float valorSeguro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @Lob
    @Size(max = 65535)
    @Column(name = "DadosAdicionais")
    private String dadosAdicionais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorTotalProd")
    private float valorTotalProd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorTotalNF")
    private float valorTotalNF;
    @Column(name = "Cancelada")
    private Boolean cancelada;
    @Column(name = "DTCancelada")
    @Temporal(TemporalType.DATE)
    private Date dTCancelada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Descontos")
    private float descontos;
    
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDPessoa", referencedColumnName = "IDPessoa")
    @ManyToOne(optional = false)
    private Pessoa iDPessoa;

    public Notasfiscais() {
    }

    public Notasfiscais(Integer iDNotasFiscais) {
        this.iDNotasFiscais = iDNotasFiscais;
    }

    public Notasfiscais(Integer iDNotasFiscais, String serie, String numero, Date dataNF, Date dataMov, float baseICMS, float valorICMS, float baseST, float valorST, float valorFrete, boolean tipoFrete, float valorIPI, float outrasDesp, float valorSeguro, Date dTInc, float valorTotalProd, float valorTotalNF, float descontos) {
        this.iDNotasFiscais = iDNotasFiscais;
        this.serie = serie;
        this.numero = numero;
        this.dataNF = dataNF;
        this.dataMov = dataMov;
        this.baseICMS = baseICMS;
        this.valorICMS = valorICMS;
        this.baseST = baseST;
        this.valorST = valorST;
        this.valorFrete = valorFrete;
        this.tipoFrete = tipoFrete;
        this.valorIPI = valorIPI;
        this.outrasDesp = outrasDesp;
        this.valorSeguro = valorSeguro;
        this.dTInc = dTInc;
        this.valorTotalProd = valorTotalProd;
        this.valorTotalNF = valorTotalNF;
        this.descontos = descontos;
    }

    public Integer getIDNotasFiscais() {
        return iDNotasFiscais;
    }

    public void setIDNotasFiscais(Integer iDNotasFiscais) {
        this.iDNotasFiscais = iDNotasFiscais;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNaturezaOP() {
        return naturezaOP;
    }

    public void setNaturezaOP(String naturezaOP) {
        this.naturezaOP = naturezaOP;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataNF() {
        return dataNF;
    }

    public void setDataNF(Date dataNF) {
        this.dataNF = dataNF;
    }

    public Date getDataMov() {
        return dataMov;
    }

    public void setDataMov(Date dataMov) {
        this.dataMov = dataMov;
    }

    public float getBaseICMS() {
        return baseICMS;
    }

    public void setBaseICMS(float baseICMS) {
        this.baseICMS = baseICMS;
    }

    public float getValorICMS() {
        return valorICMS;
    }

    public void setValorICMS(float valorICMS) {
        this.valorICMS = valorICMS;
    }

    public float getBaseST() {
        return baseST;
    }

    public void setBaseST(float baseST) {
        this.baseST = baseST;
    }

    public float getValorST() {
        return valorST;
    }

    public void setValorST(float valorST) {
        this.valorST = valorST;
    }

    public float getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(float valorFrete) {
        this.valorFrete = valorFrete;
    }

    public boolean getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(boolean tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public float getValorIPI() {
        return valorIPI;
    }

    public void setValorIPI(float valorIPI) {
        this.valorIPI = valorIPI;
    }

    public float getOutrasDesp() {
        return outrasDesp;
    }

    public void setOutrasDesp(float outrasDesp) {
        this.outrasDesp = outrasDesp;
    }

    public float getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(float valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Integer getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Integer iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    public String getDadosAdicionais() {
        return dadosAdicionais;
    }

    public void setDadosAdicionais(String dadosAdicionais) {
        this.dadosAdicionais = dadosAdicionais;
    }

    public float getValorTotalProd() {
        return valorTotalProd;
    }

    public void setValorTotalProd(float valorTotalProd) {
        this.valorTotalProd = valorTotalProd;
    }

    public float getValorTotalNF() {
        return valorTotalNF;
    }

    public void setValorTotalNF(float valorTotalNF) {
        this.valorTotalNF = valorTotalNF;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Date getDTCancelada() {
        return dTCancelada;
    }

    public void setDTCancelada(Date dTCancelada) {
        this.dTCancelada = dTCancelada;
    }

    public float getDescontos() {
        return descontos;
    }

    public void setDescontos(float descontos) {
        this.descontos = descontos;
    }

    
    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Pessoa getIDPessoa() {
        return iDPessoa;
    }

    public void setIDPessoa(Pessoa iDPessoa) {
        this.iDPessoa = iDPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDNotasFiscais != null ? iDNotasFiscais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notasfiscais)) {
            return false;
        }
        Notasfiscais other = (Notasfiscais) object;
        if ((this.iDNotasFiscais == null && other.iDNotasFiscais != null) || (this.iDNotasFiscais != null && !this.iDNotasFiscais.equals(other.iDNotasFiscais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Notasfiscais[ iDNotasFiscais=" + iDNotasFiscais + " ]";
    }
    
}
