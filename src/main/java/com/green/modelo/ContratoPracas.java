/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "contrato_pracas")
@XmlRootElement
public class ContratoPracas implements Serializable {
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "idcontrato_pracas")
    private Integer idcontratoPracas;
    @Column(name =     "DTinc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTinc;
    @Column(name = "DTalt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTalt;
   
    @JoinColumn(name = "IDusuarioInc", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDusuarioInc;
    @JoinColumn(name = "IDusuarioalt", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDusuarioalt;
    
    private static final long serialVersionUID = 1L;
    
    @JoinColumn(name = "IDcontrato_midia", referencedColumnName = "idcontrato_midia")
    @ManyToOne(optional = false)
    private ContratoMidia iDcontratomidia;
    @JoinColumn(name = "IDpraca", referencedColumnName = "idpraca")
    @ManyToOne(optional = false ,fetch = FetchType.EAGER)
    private Praca iDpraca;

    public ContratoPracas() {
    }

   

    public ContratoMidia getIDcontratomidia() {
        return iDcontratomidia;
    }

    public void setIDcontratomidia(ContratoMidia iDcontratomidia) {
        this.iDcontratomidia = iDcontratomidia;
    }

    public Praca getIDpraca() {
        return iDpraca;
    }

    public void setIDpraca(Praca iDpraca) {
        this.iDpraca = iDpraca;
    }


    public Usuario getIDusuarioInc() {
        return iDusuarioInc;
    }

    public void setIDusuarioInc(Usuario iDusuarioInc) {
        this.iDusuarioInc = iDusuarioInc;
    }

    public Usuario getIDusuarioalt() {
        return iDusuarioalt;
    }

    public void setIDusuarioalt(Usuario iDusuarioalt) {
        this.iDusuarioalt = iDusuarioalt;
    }

    public ContratoPracas(Integer idcontratoPracas) {
        this.idcontratoPracas = idcontratoPracas;
    }

    public Integer getIdcontratoPracas() {
        return idcontratoPracas;
    }

    public void setIdcontratoPracas(Integer idcontratoPracas) {
        this.idcontratoPracas = idcontratoPracas;
    }

    public Date getDTinc() {
        return dTinc;
    }

    public void setDTinc(Date dTinc) {
        this.dTinc = dTinc;
    }

    public Date getDTalt() {
        return dTalt;
    }

    public void setDTalt(Date dTalt) {
        this.dTalt = dTalt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontratoPracas != null ? idcontratoPracas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoPracas)) {
            return false;
        }
        ContratoPracas other = (ContratoPracas) object;
        if ((this.idcontratoPracas == null && other.idcontratoPracas != null) || (this.idcontratoPracas != null && !this.idcontratoPracas.equals(other.idcontratoPracas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.ContratoPracas[ idcontratoPracas=" + idcontratoPracas + " ]";
    }
    
}
