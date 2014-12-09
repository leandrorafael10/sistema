/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "produtofornecedoredicao")
public class Produtofornecedoredicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDProdutoFornecedorEdicao")
    private Integer iDProdutoFornecedorEdicao;
    @JoinColumn(name = "IDProdutoFornecedor", referencedColumnName = "IDProdutoFornecedor")
    @ManyToOne(optional = false)
    private Produtofornecedor iDProdutoFornecedor;
    @JoinColumn(name = "IDEdicao", referencedColumnName = "idEdicao")
    @ManyToOne(optional = false)
    private Edicao iDEdicao;
    

    public Produtofornecedoredicao() {
    }

    public Produtofornecedoredicao(Integer iDProdutoFornecedorEdicao) {
        this.iDProdutoFornecedorEdicao = iDProdutoFornecedorEdicao;
    }

    public Integer getIDProdutoFornecedorEdicao() {
        return iDProdutoFornecedorEdicao;
    }

    public void setIDProdutoFornecedorEdicao(Integer iDProdutoFornecedorEdicao) {
        this.iDProdutoFornecedorEdicao = iDProdutoFornecedorEdicao;
    }

    public Produtofornecedor getIDProdutoFornecedor() {
        return iDProdutoFornecedor;
    }

    public void setIDProdutoFornecedor(Produtofornecedor iDProdutoFornecedor) {
        this.iDProdutoFornecedor = iDProdutoFornecedor;
    }

    public Edicao getIDEdicao() {
        return iDEdicao;
    }

    public void setIDEdicao(Edicao iDEdicao) {
        this.iDEdicao = iDEdicao;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDProdutoFornecedorEdicao != null ? iDProdutoFornecedorEdicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtofornecedoredicao)) {
            return false;
        }
        Produtofornecedoredicao other = (Produtofornecedoredicao) object;
        if ((this.iDProdutoFornecedorEdicao == null && other.iDProdutoFornecedorEdicao != null) || (this.iDProdutoFornecedorEdicao != null && !this.iDProdutoFornecedorEdicao.equals(other.iDProdutoFornecedorEdicao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Produtofornecedoredicao[ iDProdutoFornecedorEdicao=" + iDProdutoFornecedorEdicao + " ]";
    }
    
}
