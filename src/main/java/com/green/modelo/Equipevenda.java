/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Where;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "equipevenda")
public class Equipevenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idequipevenda")
    private Integer idequipevenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @JoinColumn(name = "IDGerente", referencedColumnName = "IDFuncionario")
    @ManyToOne
    private Funcionario iDGerente;
    @JoinColumn(name = "IDPromotor", referencedColumnName = "IDFuncionario")
    @ManyToOne
    private Funcionario iDPromotor;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDEquipevenda",fetch = FetchType.EAGER)
    private List<Funcionariometa> funcionariometaList;

    public Equipevenda() {
    }

    public Equipevenda(Integer idequipevenda) {
        this.idequipevenda = idequipevenda;
    }

    public Equipevenda(Integer idequipevenda, Date dTInc) {
        this.idequipevenda = idequipevenda;
        this.dTInc = dTInc;
    }

    public Integer getIdequipevenda() {
        return idequipevenda;
    }

    public void setIdequipevenda(Integer idequipevenda) {
        this.idequipevenda = idequipevenda;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Funcionario getIDGerente() {
        return iDGerente;
    }

    public void setIDGerente(Funcionario iDGerente) {
        this.iDGerente = iDGerente;
    }

    public Funcionario getIDPromotor() {
        return iDPromotor;
    }

    public void setIDPromotor(Funcionario iDPromotor) {
        this.iDPromotor = iDPromotor;
    }

    public Usuario getiDUsuario() {
        return iDUsuario;
    }

    public void setiDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public List<Funcionariometa> getFuncionariometaList() {
        return funcionariometaList;
    }

    public void setFuncionariometaList(List<Funcionariometa> funcionariometaList) {
        this.funcionariometaList = funcionariometaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idequipevenda != null ? idequipevenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipevenda)) {
            return false;
        }
        Equipevenda other = (Equipevenda) object;
        if ((this.idequipevenda == null && other.idequipevenda != null) || (this.idequipevenda != null && !this.idequipevenda.equals(other.idequipevenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Equipevenda[ idequipevenda=" + idequipevenda + " ]";
    }
}
