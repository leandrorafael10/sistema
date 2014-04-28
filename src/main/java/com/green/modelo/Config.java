/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "config")
public class Config implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDConfig")
    private Integer iDConfig;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDusuario")
    private int iDusuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NMaxContratos")
    private int nMaxContratos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUsuarioAlt")
    private int iDUsuarioAlt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;

    public Config() {
    }

    public Config(Integer iDConfig) {
        this.iDConfig = iDConfig;
    }

    public Config(Integer iDConfig, int iDusuario, int nMaxContratos, int iDUsuarioAlt, Date dTAlt) {
        this.iDConfig = iDConfig;
        this.iDusuario = iDusuario;
        this.nMaxContratos = nMaxContratos;
        this.iDUsuarioAlt = iDUsuarioAlt;
        this.dTAlt = dTAlt;
    }

    public Integer getIDConfig() {
        return iDConfig;
    }

    public void setIDConfig(Integer iDConfig) {
        this.iDConfig = iDConfig;
    }

    public int getIDusuario() {
        return iDusuario;
    }

    public void setIDusuario(int iDusuario) {
        this.iDusuario = iDusuario;
    }

    public int getNMaxContratos() {
        return nMaxContratos;
    }

    public void setNMaxContratos(int nMaxContratos) {
        this.nMaxContratos = nMaxContratos;
    }

    public int getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(int iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDConfig != null ? iDConfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Config)) {
            return false;
        }
        Config other = (Config) object;
        if ((this.iDConfig == null && other.iDConfig != null) || (this.iDConfig != null && !this.iDConfig.equals(other.iDConfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Config[ iDConfig=" + iDConfig + " ]";
    }
    
}
