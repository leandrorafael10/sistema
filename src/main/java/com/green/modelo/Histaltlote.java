/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "histAltLote")
@XmlRootElement
public class Histaltlote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idhistAltLote")
    private Integer idhistAltLote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTOriginal")
    @Temporal(TemporalType.DATE)
    private Date dTOriginal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTNova")
    @Temporal(TemporalType.DATE)
    private Date dTNova;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne
    @NotNull
    private Usuario iDUsuario;
    @JoinColumn(name = "IDCapaLoteJornal" ,referencedColumnName = "IDCapalotejornal")
    @ManyToOne
    private Capalotejornal iDCapaLoteJornal;

    public Histaltlote() {
    }

    public Histaltlote(Integer idhistAltLote) {
        this.idhistAltLote = idhistAltLote;
    }

    public Histaltlote(Integer idhistAltLote, Date dTOriginal, Date dTNova, Date dTAlt, Usuario iDUsuario,Capalotejornal iDCapaLoteJornal) {
        this.idhistAltLote = idhistAltLote;
        this.dTOriginal = dTOriginal;
        this.dTNova = dTNova;
        this.dTAlt = dTAlt;
        this.iDUsuario = iDUsuario;
        this.iDCapaLoteJornal = iDCapaLoteJornal;
    }

    public Integer getIdhistAltLote() {
        return idhistAltLote;
    }

    public void setIdhistAltLote(Integer idhistAltLote) {
        this.idhistAltLote = idhistAltLote;
    }

    public Date getDTOriginal() {
        return dTOriginal;
    }

    public void setDTOriginal(Date dTOriginal) {
        this.dTOriginal = dTOriginal;
    }

    public Date getDTNova() {
        return dTNova;
    }

    public void setDTNova(Date dTNova) {
        this.dTNova = dTNova;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Capalotejornal getiDCapaLoteJornal() {
        return iDCapaLoteJornal;
    }

    public void setiDCapaLoteJornal(Capalotejornal iDCapaLoteJornal) {
        this.iDCapaLoteJornal = iDCapaLoteJornal;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhistAltLote != null ? idhistAltLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Histaltlote)) {
            return false;
        }
        Histaltlote other = (Histaltlote) object;
        if ((this.idhistAltLote == null && other.idhistAltLote != null) || (this.idhistAltLote != null && !this.idhistAltLote.equals(other.idhistAltLote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Histaltlote[ idhistAltLote=" + idhistAltLote + " ]";
    }
}
