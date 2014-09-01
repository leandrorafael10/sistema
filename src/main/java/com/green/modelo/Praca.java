/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "praca")
public class Praca implements Serializable {

    @Column(name = "DTinc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTinc;
    @Column(name = "DTalt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTalt;
    @Column(name = "IDusuarioInc")
    private Integer iDusuarioInc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDpraca", fetch = FetchType.LAZY)
    private Set<ContratoPracas> contratoPracasSet;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idpraca")
    private Integer idpraca;
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "IDusuarioalt")
    private Integer iDusuarioalt;
    @Column(name = "peso")
    private Integer peso;
    @JoinColumn(name = "IDPessoa", referencedColumnName = "IDPessoa")
    @ManyToOne
    private Pessoa iDPessoa;
    @ManyToMany(mappedBy = "pracas")
    private List<ContratoMidia> contratosPraca;

    public Praca() {
    }

    public Praca(Integer idpraca) {
        this.idpraca = idpraca;
    }

    public Integer getIdpraca() {
        return idpraca;
    }

    public void setIdpraca(Integer idpraca) {
        this.idpraca = idpraca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIDusuarioalt() {
        return iDusuarioalt;
    }

    public void setIDusuarioalt(Integer iDusuarioalt) {
        this.iDusuarioalt = iDusuarioalt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpraca != null ? idpraca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Praca)) {
            return false;
        }
        Praca other = (Praca) object;
        if ((this.idpraca == null && other.idpraca != null) || (this.idpraca != null && !this.idpraca.equals(other.idpraca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Praca[ idpraca=" + idpraca + " ]";
    }

    public Set<ContratoPracas> getContratoPracasSet() {
        return contratoPracasSet;
    }

    public void setContratoPracasSet(Set<ContratoPracas> contratoPracasSet) {
        this.contratoPracasSet = contratoPracasSet;
    }

    public Integer getIDusuarioInc() {
        return iDusuarioInc;
    }

    public void setIDusuarioInc(Integer iDusuarioInc) {
        this.iDusuarioInc = iDusuarioInc;
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

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Pessoa getIDPessoa() {
        return iDPessoa;
    }

    public void setIDPessoa(Pessoa iDPessoa) {
        this.iDPessoa = iDPessoa;
    }

    public List<ContratoMidia> getContratosPraca() {
        return contratosPraca;
    }

    public void setContratosPraca(List<ContratoMidia> contratosPraca) {
        this.contratosPraca = contratosPraca;
    }
    
    

}
