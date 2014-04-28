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
@Table(name = "produtofornecedorplanovendas")
public class Produtofornecedorplanovendas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ValorBase")
    private float valorBase;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdProdutoFornecedorPlanovendas")
    private Integer idProdutoFornecedorPlanovendas;
    @JoinColumn(name = "IDProdutoFornecedorEdicao", referencedColumnName = "IDProdutoFornecedorEdicao")
    @ManyToOne(optional = false)
    private Produtofornecedoredicao iDProdutoFornecedorEdicao;
    @JoinColumn(name = "IDPlanoVenda", referencedColumnName = "IDPlanoVenda")
    @ManyToOne(optional = false)
    private Planovenda iDPlanoVenda;

    public Produtofornecedorplanovendas() {
    }

    public Produtofornecedorplanovendas(Integer idProdutoFornecedorPlanovendas) {
        this.idProdutoFornecedorPlanovendas = idProdutoFornecedorPlanovendas;
    }

    public Produtofornecedorplanovendas(Integer idProdutoFornecedorPlanovendas, float valorBase) {
        this.idProdutoFornecedorPlanovendas = idProdutoFornecedorPlanovendas;
        this.valorBase = valorBase;
    }

    public float getValorBase() {
        return valorBase;
    }

    public void setValorBase(float valorBase) {
        this.valorBase = valorBase;
    }

    public Integer getIdProdutoFornecedorPlanovendas() {
        return idProdutoFornecedorPlanovendas;
    }

    public void setIdProdutoFornecedorPlanovendas(Integer idProdutoFornecedorPlanovendas) {
        this.idProdutoFornecedorPlanovendas = idProdutoFornecedorPlanovendas;
    }

    public Produtofornecedoredicao getIDProdutoFornecedorEdicao() {
        return iDProdutoFornecedorEdicao;
    }

    public void setIDProdutoFornecedorEdicao(Produtofornecedoredicao iDProdutoFornecedorEdicao) {
        this.iDProdutoFornecedorEdicao = iDProdutoFornecedorEdicao;
    }

    public Planovenda getIDPlanoVenda() {
        return iDPlanoVenda;
    }

    public void setIDPlanoVenda(Planovenda iDPlanoVenda) {
        this.iDPlanoVenda = iDPlanoVenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProdutoFornecedorPlanovendas != null ? idProdutoFornecedorPlanovendas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtofornecedorplanovendas)) {
            return false;
        }
        Produtofornecedorplanovendas other = (Produtofornecedorplanovendas) object;
        if ((this.idProdutoFornecedorPlanovendas == null && other.idProdutoFornecedorPlanovendas != null) || (this.idProdutoFornecedorPlanovendas != null && !this.idProdutoFornecedorPlanovendas.equals(other.idProdutoFornecedorPlanovendas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Produtofornecedorplanovendas[ idProdutoFornecedorPlanovendas=" + idProdutoFornecedorPlanovendas + " ]";
    }
    
}
