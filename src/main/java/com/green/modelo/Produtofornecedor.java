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
@Table(name = "produtofornecedor")
public class Produtofornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDProdutoFornecedor")
    private Integer iDProdutoFornecedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Descricao")
    private String descricao;
    @Lob
    @Size(max = 65535)
    @Column(name = "Descricaodet")
    private String descricaodet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dtinc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinc;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @Column(name = "Dtalt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtalt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ativo")
    private boolean ativo;
    
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDFornecedor", referencedColumnName = "IDFornecedor")
    @ManyToOne(optional = false)
    private Fornecedor iDFornecedor;

    public Produtofornecedor() {
    }

    public Produtofornecedor(Integer iDProdutoFornecedor) {
        this.iDProdutoFornecedor = iDProdutoFornecedor;
    }

    public Produtofornecedor(Integer iDProdutoFornecedor, String codigo, String descricao, Date dtinc, boolean ativo) {
        this.iDProdutoFornecedor = iDProdutoFornecedor;
        this.codigo = codigo;
        this.descricao = descricao;
        this.dtinc = dtinc;
        this.ativo = ativo;
    }

    public Integer getIDProdutoFornecedor() {
        return iDProdutoFornecedor;
    }

    public void setIDProdutoFornecedor(Integer iDProdutoFornecedor) {
        this.iDProdutoFornecedor = iDProdutoFornecedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaodet() {
        return descricaodet;
    }

    public void setDescricaodet(String descricaodet) {
        this.descricaodet = descricaodet;
    }

    public Date getDtinc() {
        return dtinc;
    }

    public void setDtinc(Date dtinc) {
        this.dtinc = dtinc;
    }

    public Integer getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Integer iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public Date getDtalt() {
        return dtalt;
    }

    public void setDtalt(Date dtalt) {
        this.dtalt = dtalt;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Fornecedor getIDFornecedor() {
        return iDFornecedor;
    }

    public void setIDFornecedor(Fornecedor iDFornecedor) {
        this.iDFornecedor = iDFornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDProdutoFornecedor != null ? iDProdutoFornecedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtofornecedor)) {
            return false;
        }
        Produtofornecedor other = (Produtofornecedor) object;
        if ((this.iDProdutoFornecedor == null && other.iDProdutoFornecedor != null) || (this.iDProdutoFornecedor != null && !this.iDProdutoFornecedor.equals(other.iDProdutoFornecedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Produtofornecedor[ iDProdutoFornecedor=" + iDProdutoFornecedor + " ]";
    }
    
}
