/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "obscontrato")
public class ObsContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDObsContrato")
    private Integer iDObsContrato;
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
    @JoinColumn(name = "IDContratoMidia", referencedColumnName = "idcontrato_midia")
    @ManyToOne
    private ContratoMidia iDContratoMidia;

    public Integer getIDObsContrato() {
        return iDObsContrato;
    }

    public void setIDObsContrato(Integer iDObsContrato) {
        this.iDObsContrato = iDObsContrato;
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

    public ContratoMidia getIDContratoMidia() {
        return iDContratoMidia;
    }

    public void setIDContratoMidia(ContratoMidia iDContratoMidia) {
        this.iDContratoMidia = iDContratoMidia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.iDObsContrato!= null?this.iDObsContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObsContrato other = (ObsContrato) obj;
        if (!Objects.equals(this.iDObsContrato, other.iDObsContrato)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
