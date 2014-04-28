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
@Table(name = "atividade")
public class Atividade implements Serializable {
    @Basic(optional =     false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name =     "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDAtividade")
    private Integer iDAtividade;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "Descricao")
    private String descricao;
    @Lob
    @Size(max = 65535)
    @Column(name = "Obs")
    private String obs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ativo")
    private boolean ativo;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDUsuarioAlt", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioAlt;

    public Atividade() {
    }

    public Atividade(Integer iDAtividade) {
        this.iDAtividade = iDAtividade;
    }

    public Atividade(Integer iDAtividade, String descricao, Date dTInc, boolean ativo) {
        this.iDAtividade = iDAtividade;
        this.descricao = descricao;
        this.dTInc = dTInc;
        this.ativo = ativo;
    }

    public Integer getIDAtividade() {
        return iDAtividade;
    }

    public void setIDAtividade(Integer iDAtividade) {
        this.iDAtividade = iDAtividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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

    public Usuario getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDAtividade != null ? iDAtividade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atividade)) {
            return false;
        }
        Atividade other = (Atividade) object;
        if ((this.iDAtividade == null && other.iDAtividade != null) || (this.iDAtividade != null && !this.iDAtividade.equals(other.iDAtividade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Atividade[ iDAtividade=" + iDAtividade + " ]";
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
    
}
