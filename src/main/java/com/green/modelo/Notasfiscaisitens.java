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
@Table(name = "notasfiscaisitens")
public class Notasfiscaisitens implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDNotasFiscaisItens")
    private Integer iDNotasFiscaisItens;
    @Size(max = 4)
    @Column(name = "CFOP")
    private String cfop;
    @Size(max = 4)
    @Column(name = "ST")
    private String st;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantidade")
    private int quantidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorUnitario")
    private float valorUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorTotal")
    private float valorTotal;
    @Size(max = 2)
    @Column(name = "AliICMS")
    private String aliICMS;
    @JoinColumn(name = "IDNotasFiscais", referencedColumnName = "IDNotasFiscais")
    @ManyToOne(optional = false)
    private Notasfiscais iDNotasFiscais;
    @JoinColumn(name = "IDBrinde", referencedColumnName = "IDBrinde")
    @ManyToOne
    private Brinde iDBrinde;

    public Notasfiscaisitens() {
    }

    public Notasfiscaisitens(Integer iDNotasFiscaisItens) {
        this.iDNotasFiscaisItens = iDNotasFiscaisItens;
    }

    public Notasfiscaisitens(Integer iDNotasFiscaisItens, int quantidade, float valorUnitario, float valorTotal) {
        this.iDNotasFiscaisItens = iDNotasFiscaisItens;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

    public Integer getIDNotasFiscaisItens() {
        return iDNotasFiscaisItens;
    }

    public void setIDNotasFiscaisItens(Integer iDNotasFiscaisItens) {
        this.iDNotasFiscaisItens = iDNotasFiscaisItens;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getAliICMS() {
        return aliICMS;
    }

    public void setAliICMS(String aliICMS) {
        this.aliICMS = aliICMS;
    }

    public Notasfiscais getIDNotasFiscais() {
        return iDNotasFiscais;
    }

    public void setIDNotasFiscais(Notasfiscais iDNotasFiscais) {
        this.iDNotasFiscais = iDNotasFiscais;
    }

    public Brinde getIDBrinde() {
        return iDBrinde;
    }

    public void setIDBrinde(Brinde iDBrinde) {
        this.iDBrinde = iDBrinde;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDNotasFiscaisItens != null ? iDNotasFiscaisItens.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notasfiscaisitens)) {
            return false;
        }
        Notasfiscaisitens other = (Notasfiscaisitens) object;
        if ((this.iDNotasFiscaisItens == null && other.iDNotasFiscaisItens != null) || (this.iDNotasFiscaisItens != null && !this.iDNotasFiscaisItens.equals(other.iDNotasFiscaisItens))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Notasfiscaisitens[ iDNotasFiscaisItens=" + iDNotasFiscaisItens + " ]";
    }
    
}
