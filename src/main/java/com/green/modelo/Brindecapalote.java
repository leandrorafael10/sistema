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

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "brindecapalote")
public class Brindecapalote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idbrinde_capalote")
    private Integer idbrindeCapalote;
    @JoinColumn(name = "IDBrinde" ,referencedColumnName = "IDBrinde")
    @ManyToOne
    private Brinde iDBrinde;
    @JoinColumn(name = "IDCapalotejornal", referencedColumnName = "IDCapalotejornal")
    @ManyToOne(optional = false)
    private Capalotejornal iDCapalotejornal;

    public Brindecapalote() {
    }

    public Brindecapalote(Integer idbrindeCapalote) {
        this.idbrindeCapalote = idbrindeCapalote;
    }

    public Integer getIdbrindeCapalote() {
        return idbrindeCapalote;
    }

    public void setIdbrindeCapalote(Integer idbrindeCapalote) {
        this.idbrindeCapalote = idbrindeCapalote;
    }

    public Capalotejornal getIDCapalotejornal() {
        return iDCapalotejornal;
    }

    public void setIDCapalotejornal(Capalotejornal iDCapalotejornal) {
        this.iDCapalotejornal = iDCapalotejornal;
    }

    public Brinde getIDBrinde() {
        return iDBrinde;
    }

    public void setIDBrinde(Brinde iDBrinde) {
        this.iDBrinde = iDBrinde;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbrindeCapalote != null ? idbrindeCapalote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Brindecapalote)) {
            return false;
        }
        Brindecapalote other = (Brindecapalote) object;
        if ((this.idbrindeCapalote == null && other.idbrindeCapalote != null) || (this.idbrindeCapalote != null && !this.idbrindeCapalote.equals(other.idbrindeCapalote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Brindecapalote[ idbrindeCapalote=" + idbrindeCapalote + " ]";
    }
    
}
