/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "brindeplanodevenda")
public class Brindeplanodevenda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDBrindePlanodeVenda")
    private Integer iDBrindePlanodeVenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantidade")
    private int quantidade;
    @JoinColumn(name = "IDPlanoVenda", referencedColumnName = "IDPlanoVenda")
    @ManyToOne(optional = false)
    private Planovenda iDPlanoVenda;
    @JoinColumn(name = "IDBrinde", referencedColumnName = "IDBrinde")
    @ManyToOne(optional = false)
    private Brinde iDBrinde;

    public Brindeplanodevenda() {
    }

    public Brindeplanodevenda(Integer iDBrindePlanodeVenda) {
        this.iDBrindePlanodeVenda = iDBrindePlanodeVenda;
    }

    public Brindeplanodevenda(Integer iDBrindePlanodeVenda, int quantidade) {
        this.iDBrindePlanodeVenda = iDBrindePlanodeVenda;
        this.quantidade = quantidade;
    }

    public Integer getIDBrindePlanodeVenda() {
        return iDBrindePlanodeVenda;
    }

    public void setIDBrindePlanodeVenda(Integer iDBrindePlanodeVenda) {
        this.iDBrindePlanodeVenda = iDBrindePlanodeVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Planovenda getIDPlanoVenda() {
        return iDPlanoVenda;
    }

    public void setIDPlanoVenda(Planovenda iDPlanoVenda) {
        this.iDPlanoVenda = iDPlanoVenda;
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
        hash += (iDBrindePlanodeVenda != null ? iDBrindePlanodeVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Brindeplanodevenda)) {
            return false;
        }
        Brindeplanodevenda other = (Brindeplanodevenda) object;
        if ((this.iDBrindePlanodeVenda == null && other.iDBrindePlanodeVenda != null) || (this.iDBrindePlanodeVenda != null && !this.iDBrindePlanodeVenda.equals(other.iDBrindePlanodeVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Brindeplanodevenda[ iDBrindePlanodeVenda=" + iDBrindePlanodeVenda + " ]";
    }
    
}
