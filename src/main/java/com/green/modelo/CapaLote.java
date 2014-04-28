/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "capa_lote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CapaLote.findAll", query = "SELECT c FROM CapaLote c")})
public class CapaLote implements Serializable {
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idcapa_lote")
    private Integer idcapaLote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_contrato")
    private int numeroContrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nome_cliente")
    private String nomeCliente;
    @Max(value = 9999999)
    @Min(value = 0)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "ativo")
    private Boolean ativo;
    @Column(name = "dtvenda")
    @Temporal(TemporalType.DATE)
    private Date dtvenda;
    @Column(name = "dtinc")
    @Temporal(TemporalType.DATE)
    private Date dtinc;
    @Column(name = "dtalt")
    @Temporal(TemporalType.DATE)
    private Date dtalt;
    @Column(name = "dtCancel")
    @Temporal(TemporalType.DATE)
    private Date dtCancel;
    @JoinColumn(name = "idusuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "idusuarioalt", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioAlt;
    @JoinColumn(name = "idbrinde", referencedColumnName = "IDBrinde")
    @ManyToOne
    private Brinde iDBrinde;
    @JoinColumn(name = "IDCalculo_comissao", referencedColumnName = "idcalculo_comissao")
    @ManyToOne(optional = false)
    private CalculoComissao iDCalculoComissao;
    @JoinColumn(name = "IDTerceiros", referencedColumnName = "IDTerceiros")
    @ManyToOne(optional = false)
    private Terceiros iDTerceiros;

    public CapaLote() {
    }

    public CapaLote(Integer idcapaLote) {
        this.idcapaLote = idcapaLote;
    }

    public CapaLote(Integer idcapaLote, int numeroContrato, String nomeCliente) {
        this.idcapaLote = idcapaLote;
        this.numeroContrato = numeroContrato;
        this.nomeCliente = nomeCliente;
    }

    public Integer getIdcapaLote() {
        return idcapaLote;
    }

    public void setIdcapaLote(Integer idcapaLote) {
        this.idcapaLote = idcapaLote;
    }

    public int getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(int numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDtvenda() {
        return dtvenda;
    }

    public void setDtvenda(Date dtvenda) {
        this.dtvenda = dtvenda;
    }

    public Date getDtinc() {
        return dtinc;
    }

    public void setDtinc(Date dtinc) {
        this.dtinc = dtinc;
    }

    public Date getDtalt() {
        return dtalt;
    }

    public void setDtalt(Date dtalt) {
        this.dtalt = dtalt;
    }

    public Date getDtCancel() {
        return dtCancel;
    }

    public void setDtCancel(Date dtCancel) {
        this.dtCancel = dtCancel;
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

    public Brinde getIDBrinde() {
        return iDBrinde;
    }

    public void setIDBrinde(Brinde iDBrinde) {
        this.iDBrinde = iDBrinde;
    }

    public CalculoComissao getiDCalculoComissao() {
        return iDCalculoComissao;
    }

    public void setiDCalculoComissao(CalculoComissao iDCalculoComissao) {
        this.iDCalculoComissao = iDCalculoComissao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcapaLote != null ? idcapaLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CapaLote)) {
            return false;
        }
        CapaLote other = (CapaLote) object;
        if ((this.idcapaLote == null && other.idcapaLote != null) || (this.idcapaLote != null && !this.idcapaLote.equals(other.idcapaLote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.CapaLote[ idcapaLote=" + idcapaLote + " ]";
    }

    public Terceiros getIDTerceiros() {
        return iDTerceiros;
    }

    public void setIDTerceiros(Terceiros iDTerceiros) {
        this.iDTerceiros = iDTerceiros;
    }
}
