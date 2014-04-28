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
@Table(name = "brinde")
public class Brinde implements Serializable {
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDBrinde")
    private Integer iDBrinde;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SaldoInicial")
    private int saldoInicial;
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
    @Lob
    @Size(max = 65535)
    @Column(name = "DescricaoDet")
    private String descricaoDet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ativo")
    private boolean ativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SaldoAtual")
    private int saldoAtual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorCusto")
    private float valorCusto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorVenda")
    private float valorVenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SaldoVenda")
    private int saldoVenda;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    
    public Brinde() {
    }

    public Brinde(Integer iDBrinde) {
        this.iDBrinde = iDBrinde;
    }

    public Brinde(Integer iDBrinde, String descricao, int saldoInicial, Date dTInc, boolean ativo, int saldoAtual, float valorCusto, float valorVenda, int saldoVenda) {
        this.iDBrinde = iDBrinde;
        this.descricao = descricao;
        this.saldoInicial = saldoInicial;
        this.dTInc = dTInc;
        this.ativo = ativo;
        this.saldoAtual = saldoAtual;
        
        this.valorCusto = valorCusto;
        this.valorVenda = valorVenda;
        this.saldoVenda = saldoVenda;
    }

    public Integer getIDBrinde() {
        return iDBrinde;
    }

    public void setIDBrinde(Integer iDBrinde) {
        this.iDBrinde = iDBrinde;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(int saldoInicial) {
        this.saldoInicial = saldoInicial;
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

    public String getDescricaoDet() {
        return descricaoDet;
    }

    public void setDescricaoDet(String descricaoDet) {
        this.descricaoDet = descricaoDet;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(int saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public float getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(float valorCusto) {
        this.valorCusto = valorCusto;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getSaldoVenda() {
        return saldoVenda;
    }

    public void setSaldoVenda(int saldoVenda) {
        this.saldoVenda = saldoVenda;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDBrinde != null ? iDBrinde.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Brinde)) {
            return false;
        }
        Brinde other = (Brinde) object;
        if ((this.iDBrinde == null && other.iDBrinde != null) || (this.iDBrinde != null && !this.iDBrinde.equals(other.iDBrinde))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Brinde[ iDBrinde=" + iDBrinde + " ]";
    }

    
}
