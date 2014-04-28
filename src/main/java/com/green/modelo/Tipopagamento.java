/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "tipopagamento")
@XmlRootElement
public class Tipopagamento implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDTipoPagamento")
    private Integer iDTipoPagamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo")
    private int tipo;
    

    public Tipopagamento() {
    }

    public Tipopagamento(Integer iDTipoPagamento) {
        this.iDTipoPagamento = iDTipoPagamento;
    }

    public Tipopagamento(Integer iDTipoPagamento, String descricao, int tipo) {
        this.iDTipoPagamento = iDTipoPagamento;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Integer getIDTipoPagamento() {
        return iDTipoPagamento;
    }

    public void setIDTipoPagamento(Integer iDTipoPagamento) {
        this.iDTipoPagamento = iDTipoPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDTipoPagamento != null ? iDTipoPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipopagamento)) {
            return false;
        }
        Tipopagamento other = (Tipopagamento) object;
        if ((this.iDTipoPagamento == null && other.iDTipoPagamento != null) || (this.iDTipoPagamento != null && !this.iDTipoPagamento.equals(other.iDTipoPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Tipopagamento[ iDTipoPagamento=" + iDTipoPagamento + " ]";
    }

   
    
}
