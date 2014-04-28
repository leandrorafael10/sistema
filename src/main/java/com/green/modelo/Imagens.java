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
@Table(name = "imagens")
public class Imagens implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDImagens")
    private Integer iDImagens;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "Imagem")
    private byte[] imagem;

    public Imagens() {
    }

    public Imagens(Integer iDImagens) {
        this.iDImagens = iDImagens;
    }

    public Imagens(Integer iDImagens, byte[] imagem) {
        this.iDImagens = iDImagens;
        this.imagem = imagem;
    }

    public Integer getIDImagens() {
        return iDImagens;
    }

    public void setIDImagens(Integer iDImagens) {
        this.iDImagens = iDImagens;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDImagens != null ? iDImagens.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagens)) {
            return false;
        }
        Imagens other = (Imagens) object;
        if ((this.iDImagens == null && other.iDImagens != null) || (this.iDImagens != null && !this.iDImagens.equals(other.iDImagens))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Imagens[ iDImagens=" + iDImagens + " ]";
    }
    
}
