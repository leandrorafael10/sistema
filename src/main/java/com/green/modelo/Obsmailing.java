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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "obsmailing")
@XmlRootElement
public class Obsmailing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDObsMailing")
    private Integer iDObsMailing;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Obs")
    private String obs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTObs")
    @Temporal(TemporalType.DATE)
    private Date dTObs;
    @JoinColumn(name = "IDFuncionario", referencedColumnName = "IDFuncionario")
    @ManyToOne(optional = false)
    private Funcionario iDFuncionario;
    @JoinColumn(name = "IDMailing", referencedColumnName = "IDMailing")
    @ManyToOne
    private Mailing iDMailing;

    public Obsmailing() {
    }

    public Obsmailing(Integer iDObsMailing) {
        this.iDObsMailing = iDObsMailing;
    }

    public Obsmailing(Integer iDObsMailing, String obs, Date dTObs) {
        this.iDObsMailing = iDObsMailing;
        this.obs = obs;
        this.dTObs = dTObs;
    }

    public Integer getIDObsMailing() {
        return iDObsMailing;
    }

    public void setIDObsMailing(Integer iDObsMailing) {
        this.iDObsMailing = iDObsMailing;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getDTObs() {
        return dTObs;
    }

    public void setDTObs(Date dTObs) {
        this.dTObs = dTObs;
    }

    public Funcionario getIDFuncionario() {
        return iDFuncionario;
    }

    public void setIDFuncionario(Funcionario iDFuncionario) {
        this.iDFuncionario = iDFuncionario;
    }

    

    public Mailing getIDMailing() {
        return iDMailing;
    }

    public void setIDMailing(Mailing iDMailing) {
        this.iDMailing = iDMailing;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDObsMailing != null ? iDObsMailing.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obsmailing)) {
            return false;
        }
        Obsmailing other = (Obsmailing) object;
        if ((this.iDObsMailing == null && other.iDObsMailing != null) || (this.iDObsMailing != null && !this.iDObsMailing.equals(other.iDObsMailing))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Obsmailing[ iDObsMailing=" + iDObsMailing + " ]";
    }
    
}
