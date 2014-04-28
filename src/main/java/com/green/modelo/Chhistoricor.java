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
@Table(name = "chhistoricor")
public class Chhistoricor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCHHistoricoR")
    private Integer iDCHHistoricoR;
    @JoinColumn(name = "IDReceita", referencedColumnName = "IDReceita")
    @ManyToOne(optional = false)
    private Receita iDReceita;
    @JoinColumn(name = "IDCheque", referencedColumnName = "IDCheque")
    @ManyToOne(optional = false)
    private Cheque iDCheque;

    public Chhistoricor() {
    }

    public Chhistoricor(Integer iDCHHistoricoR) {
        this.iDCHHistoricoR = iDCHHistoricoR;
    }

    public Integer getIDCHHistoricoR() {
        return iDCHHistoricoR;
    }

    public void setIDCHHistoricoR(Integer iDCHHistoricoR) {
        this.iDCHHistoricoR = iDCHHistoricoR;
    }

    public Receita getIDReceita() {
        return iDReceita;
    }

    public void setIDReceita(Receita iDReceita) {
        this.iDReceita = iDReceita;
    }

    public Cheque getIDCheque() {
        return iDCheque;
    }

    public void setIDCheque(Cheque iDCheque) {
        this.iDCheque = iDCheque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCHHistoricoR != null ? iDCHHistoricoR.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chhistoricor)) {
            return false;
        }
        Chhistoricor other = (Chhistoricor) object;
        if ((this.iDCHHistoricoR == null && other.iDCHHistoricoR != null) || (this.iDCHHistoricoR != null && !this.iDCHHistoricoR.equals(other.iDCHHistoricoR))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Chhistoricor[ iDCHHistoricoR=" + iDCHHistoricoR + " ]";
    }
    
}
