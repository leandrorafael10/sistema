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
@Table(name = "classificacao")
public class Classificacao implements Serializable {

    @Basic(optional =     false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name =     "dtalt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtalt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDClassificacao")
    private Integer iDClassificacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Descricao")
    private String descricao;
    @Size(max = 13)
    @Column(name = "Codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "natureza")
    private boolean natureza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private boolean tipo;
    @JoinColumn(name = "idusuarioAlt", referencedColumnName = "IDUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idusuarioAlt;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario iDUsuario;

    public Classificacao() {
    }

    public Classificacao(Integer iDClassificacao) {
        this.iDClassificacao = iDClassificacao;
    }

    public Classificacao(Integer iDClassificacao, String descricao, Date dTInc, boolean ativo, boolean natureza, boolean tipo) {
        this.iDClassificacao = iDClassificacao;
        this.descricao = descricao;
        this.dTInc = dTInc;
        this.ativo = ativo;
        this.natureza = natureza;
        this.tipo = tipo;
    }

    public Integer getIDClassificacao() {
        return iDClassificacao;
    }

    public void setIDClassificacao(Integer iDClassificacao) {
        this.iDClassificacao = iDClassificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean getNatureza() {
        return natureza;
    }

    public void setNatureza(boolean natureza) {
        this.natureza = natureza;
    }

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Usuario getIdusuarioAlt() {
        return idusuarioAlt;
    }

    public void setIdusuarioAlt(Usuario idusuarioAlt) {
        this.idusuarioAlt = idusuarioAlt;
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
        hash += (iDClassificacao != null ? iDClassificacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classificacao)) {
            return false;
        }
        Classificacao other = (Classificacao) object;
        if ((this.iDClassificacao == null && other.iDClassificacao != null) || (this.iDClassificacao != null && !this.iDClassificacao.equals(other.iDClassificacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Classificacao[ iDClassificacao=" + iDClassificacao + " ]";
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Date getDtalt() {
        return dtalt;
    }

    public void setDtalt(Date dtalt) {
        this.dtalt = dtalt;
    }
}
