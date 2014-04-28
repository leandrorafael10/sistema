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
@Table(name = "documento")
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDDocumento")
    private Integer iDDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(max = 3)
    @Column(name = "Cod")
    private String cod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TipoAplicacao")
    private int tipoAplicacao;
    

    public Documento() {
    }

    public Documento(Integer iDDocumento) {
        this.iDDocumento = iDDocumento;
    }

    public Documento(Integer iDDocumento, String cod, String descricao, int tipoAplicacao) {
        this.iDDocumento = iDDocumento;
        this.cod = cod;
        this.descricao = descricao;
        this.tipoAplicacao = tipoAplicacao;
    }

    public Integer getIDDocumento() {
        return iDDocumento;
    }

    public void setIDDocumento(Integer iDDocumento) {
        this.iDDocumento = iDDocumento;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipoAplicacao() {
        return tipoAplicacao;
    }

    public void setTipoAplicacao(int tipoAplicacao) {
        this.tipoAplicacao = tipoAplicacao;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDDocumento != null ? iDDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.iDDocumento == null && other.iDDocumento != null) || (this.iDDocumento != null && !this.iDDocumento.equals(other.iDDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Documento[ iDDocumento=" + iDDocumento + " ]";
    }
}
