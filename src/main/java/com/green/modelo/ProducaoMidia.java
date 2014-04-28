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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "producao_midia")
public class ProducaoMidia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic
    @Column(name = "IDProducaoMidia")
    private Integer iDProducaoMidia;
    @Column(name = "status_material")
    private Integer statusMaterial;
    @Column(name = "status_producao")
    private Integer statusProducao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DTFim_material")
    private Date dTFimMaterial;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DTFim_producao")
    private Date dTFimProducao;
    @JoinColumn(name = "IDContrato", referencedColumnName = "idcontrato_midia")
    @ManyToOne(fetch = FetchType.EAGER)
    private ContratoMidia iDContratoMidia;
    @JoinColumn(name = "IDusuario_alt_material", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario IDUsuarioAltMaterial;
    @JoinColumn(name = "IDusuario_alt_producao", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario IDUsuarioAltProducao;

    public ProducaoMidia() {
    }

    public ProducaoMidia(Integer iDProducaoMidia) {
        this.iDProducaoMidia = iDProducaoMidia;
    }

    public ProducaoMidia(Integer iDProducaoMidia, Integer statusMaterial, Integer statusProducao, ContratoMidia iDContratoMidia) {
        this.iDProducaoMidia = iDProducaoMidia;
        this.statusMaterial = statusMaterial;
        this.statusProducao = statusProducao;
        this.iDContratoMidia = iDContratoMidia;
    }

    public Integer getIDProducaoMidia() {
        return iDProducaoMidia;
    }

    public void setIDProducaoMidia(Integer iDProducaoMidia) {
        this.iDProducaoMidia = iDProducaoMidia;
    }

    public Integer getStatusMaterial() {
        return statusMaterial;
    }

    public void setStatusMaterial(Integer statusMaterial) {
        this.statusMaterial = statusMaterial;
    }

    public Integer getStatusProducao() {
        return statusProducao;
    }

    public void setStatusProducao(Integer statusProducao) {
        this.statusProducao = statusProducao;
    }

    public Date getDTFimMaterial() {
        return dTFimMaterial;
    }

    public void setDTFimMaterial(Date dTFimMaterial) {
        this.dTFimMaterial = dTFimMaterial;
    }

    public Date getDTFimProducao() {
        return dTFimProducao;
    }

    public void setDTFimProducao(Date dTFimProducao) {
        this.dTFimProducao = dTFimProducao;
    }

    public ContratoMidia getIDContratoMidia() {
        return iDContratoMidia;
    }

    public void setIDContratoMidia(ContratoMidia iDContratoMidia) {
        this.iDContratoMidia = iDContratoMidia;
    }

    public Usuario getIDUsuarioAltMaterial() {
        return IDUsuarioAltMaterial;
    }

    public void setIDUsuarioAltMaterial(Usuario IDUsuarioAltMaterial) {
        this.IDUsuarioAltMaterial = IDUsuarioAltMaterial;
    }

    public Usuario getIDUsuarioAltProducao() {
        return IDUsuarioAltProducao;
    }

    public void setIDUsuarioAltProducao(Usuario IDUsuarioAltProducao) {
        this.IDUsuarioAltProducao = IDUsuarioAltProducao;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDProducaoMidia != null ? iDProducaoMidia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProducaoMidia)) {
            return false;
        }
        ProducaoMidia other = (ProducaoMidia) object;
        if ((this.iDProducaoMidia == null && other.iDProducaoMidia != null) || (this.iDProducaoMidia != null && !this.iDProducaoMidia.equals(other.iDProducaoMidia))) {
            return false;
        }
        return true;
    }
}
