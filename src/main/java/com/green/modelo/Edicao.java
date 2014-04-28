/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "edicao")
public class Edicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEdicao")
    private Integer idEdicao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descricao")
    private String descricao;
    

    public Edicao() {
    }

    public Edicao(Integer idEdicao) {
        this.idEdicao = idEdicao;
    }

    public Edicao(Integer idEdicao, String descricao) {
        this.idEdicao = idEdicao;
        this.descricao = descricao;
    }

    public Integer getIdEdicao() {
        return idEdicao;
    }

    public void setIdEdicao(Integer idEdicao) {
        this.idEdicao = idEdicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEdicao != null ? idEdicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Edicao)) {
            return false;
        }
        Edicao other = (Edicao) object;
        if ((this.idEdicao == null && other.idEdicao != null) || (this.idEdicao != null && !this.idEdicao.equals(other.idEdicao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Edicao[ idEdicao=" + idEdicao + " ]";
    }
    
}
