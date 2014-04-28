/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "funcionariometa")
public class Funcionariometa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idFuncionarioMeta")
    private Integer idFuncionarioMeta;
    @Column(name = "meta")
    private Integer meta;
    @Column(name = "Dtalt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtalt;
    @Column(name = "tipo")
    private Integer tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mes")
    private int mes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano")
    private int ano;
    @Column(name="diasTrab")
    private Integer diasTrab;
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @JoinColumn(name = "IdusuarioAlt", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario idusuarioAlt;
    @JoinColumn(name = "IDUsuarioInc", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioInc;
    @JoinColumn(name = "IDEquipevenda", referencedColumnName = "idequipevenda")
    @ManyToOne
    private Equipevenda iDEquipevenda;

    public Funcionariometa() {
    }

    public Funcionariometa(Integer idFuncionarioMeta) {
        this.idFuncionarioMeta = idFuncionarioMeta;
    }

    public Integer getIdFuncionarioMeta() {
        return idFuncionarioMeta;
    }

    public void setIdFuncionarioMeta(Integer idFuncionarioMeta) {
        this.idFuncionarioMeta = idFuncionarioMeta;
    }

    public Integer getMeta() {
        return meta;
    }

    public void setMeta(Integer meta) {
        this.meta = meta;
    }

    public Date getDtalt() {
        return dtalt;
    }

    public void setDtalt(Date dtalt) {
        this.dtalt = dtalt;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Equipevenda getIDEquipevenda() {
        return iDEquipevenda;
    }

    public void setIDEquipevenda(Equipevenda iDEquipevenda) {
        this.iDEquipevenda = iDEquipevenda;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuncionarioMeta != null ? idFuncionarioMeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionariometa)) {
            return false;
        }
        Funcionariometa other = (Funcionariometa) object;
        if ((this.idFuncionarioMeta == null && other.idFuncionarioMeta != null) || (this.idFuncionarioMeta != null && !this.idFuncionarioMeta.equals(other.idFuncionarioMeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Funcionariometa[ idFuncionarioMeta=" + idFuncionarioMeta + " ]";
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Integer getDiasTrab() {
        return diasTrab;
    }

    public void setDiasTrab(Integer diasTrab) {
        this.diasTrab = diasTrab;
    }
    

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Usuario getIdusuarioAlt() {
        return idusuarioAlt;
    }

    public void setIdusuarioAlt(Usuario idusuarioAlt) {
        this.idusuarioAlt = idusuarioAlt;
    }

    public Usuario getIDUsuarioInc() {
        return iDUsuarioInc;
    }

    public void setIDUsuarioInc(Usuario iDUsuarioInc) {
        this.iDUsuarioInc = iDUsuarioInc;
    }
}
