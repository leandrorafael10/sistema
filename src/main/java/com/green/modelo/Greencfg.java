/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "greencfg")
public class Greencfg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDGreenCFG")
    private Integer iDGreenCFG;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Versao")
    private int versao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Versaos")
    private int versaos;

    public Greencfg() {
    }

    public Greencfg(Integer iDGreenCFG) {
        this.iDGreenCFG = iDGreenCFG;
    }

    public Greencfg(Integer iDGreenCFG, String tipo, int versao, int versaos) {
        this.iDGreenCFG = iDGreenCFG;
        this.tipo = tipo;
        this.versao = versao;
        this.versaos = versaos;
    }

    public Integer getIDGreenCFG() {
        return iDGreenCFG;
    }

    public void setIDGreenCFG(Integer iDGreenCFG) {
        this.iDGreenCFG = iDGreenCFG;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public int getVersaos() {
        return versaos;
    }

    public void setVersaos(int versaos) {
        this.versaos = versaos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDGreenCFG != null ? iDGreenCFG.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Greencfg)) {
            return false;
        }
        Greencfg other = (Greencfg) object;
        if ((this.iDGreenCFG == null && other.iDGreenCFG != null) || (this.iDGreenCFG != null && !this.iDGreenCFG.equals(other.iDGreenCFG))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Greencfg[ iDGreenCFG=" + iDGreenCFG + " ]";
    }
    
}
