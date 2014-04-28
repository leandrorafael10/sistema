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
@Table(name = "planovenda")
public class Planovenda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDPlanoVenda")
    private Integer iDPlanoVenda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ativo")
    private boolean ativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUsuario")
    private int iDUsuario;
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
    @Column(name = "Obs")
    private String obs;
    @Size(max = 45)
    @Column(name = "CodigoFornecedor")
    private String codigoFornecedor;
    
    @JoinColumn(name = "IDFornecedor", referencedColumnName = "IDFornecedor")
    @ManyToOne(optional = false)
    private Fornecedor iDFornecedor;
    

    public Planovenda() {
    }

    public Planovenda(Integer iDPlanoVenda) {
        this.iDPlanoVenda = iDPlanoVenda;
    }

    public Planovenda(Integer iDPlanoVenda, String descricao, boolean ativo, int iDUsuario, Date dTInc) {
        this.iDPlanoVenda = iDPlanoVenda;
        this.descricao = descricao;
        this.ativo = ativo;
        this.iDUsuario = iDUsuario;
        this.dTInc = dTInc;
    }

    public Integer getIDPlanoVenda() {
        return iDPlanoVenda;
    }

    public void setIDPlanoVenda(Integer iDPlanoVenda) {
        this.iDPlanoVenda = iDPlanoVenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(int iDUsuario) {
        this.iDUsuario = iDUsuario;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getCodigoFornecedor() {
        return codigoFornecedor;
    }

    public void setCodigoFornecedor(String codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
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
        hash += (iDPlanoVenda != null ? iDPlanoVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planovenda)) {
            return false;
        }
        Planovenda other = (Planovenda) object;
        if ((this.iDPlanoVenda == null && other.iDPlanoVenda != null) || (this.iDPlanoVenda != null && !this.iDPlanoVenda.equals(other.iDPlanoVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Planovenda[ iDPlanoVenda=" + iDPlanoVenda + " ]";
    }
    
}
