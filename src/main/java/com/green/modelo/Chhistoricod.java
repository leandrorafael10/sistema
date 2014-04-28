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
@Table(name = "chhistoricod")
public class Chhistoricod implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCHHistoricoD")
    private Integer iDCHHistoricoD;
    @JoinColumn(name = "IDDespesa", referencedColumnName = "IDDespesa")
    @ManyToOne(optional = false)
    private Despesa iDDespesa;
    @JoinColumn(name = "IDCheque", referencedColumnName = "IDCheque")
    @ManyToOne(optional = false)
    private Cheque iDCheque;

    public Chhistoricod() {
    }

    public Chhistoricod(Integer iDCHHistoricoD) {
        this.iDCHHistoricoD = iDCHHistoricoD;
    }

    public Integer getIDCHHistoricoD() {
        return iDCHHistoricoD;
    }

    public void setIDCHHistoricoD(Integer iDCHHistoricoD) {
        this.iDCHHistoricoD = iDCHHistoricoD;
    }

    public Despesa getIDDespesa() {
        return iDDespesa;
    }

    public void setIDDespesa(Despesa iDDespesa) {
        this.iDDespesa = iDDespesa;
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
        hash += (iDCHHistoricoD != null ? iDCHHistoricoD.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chhistoricod)) {
            return false;
        }
        Chhistoricod other = (Chhistoricod) object;
        if ((this.iDCHHistoricoD == null && other.iDCHHistoricoD != null) || (this.iDCHHistoricoD != null && !this.iDCHHistoricoD.equals(other.iDCHHistoricoD))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Chhistoricod[ iDCHHistoricoD=" + iDCHHistoricoD + " ]";
    }
    
}
