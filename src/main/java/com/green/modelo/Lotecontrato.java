/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "lotecontrato")
public class Lotecontrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLoteContrato")
    private Integer iDLoteContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sequencial")
    private boolean sequencial;
    @Column(name = "Objetivo")
    private Integer objetivo;
    @Size(max = 45)
    @Column(name = "Motivo")
    private String motivo;
    @Column(name = "NumeroIni")
    private Integer numeroIni;
    @Column(name = "NumeroFim")
    private Integer numeroFim;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDFuncionario", referencedColumnName = "IDFuncionario")
    @ManyToOne
    private Funcionario iDFuncionario;
    @JoinColumn(name = "IDFornecedor", referencedColumnName = "IDFornecedor")
    @ManyToOne(optional = false)
    private Fornecedor iDFornecedor;
    

    public Lotecontrato() {
    }

    public Lotecontrato(Integer iDLoteContrato) {
        this.iDLoteContrato = iDLoteContrato;
    }

    public Lotecontrato(Integer iDLoteContrato, int tipo, Date dTInc, boolean sequencial) {
        this.iDLoteContrato = iDLoteContrato;
        this.tipo = tipo;
        this.dTInc = dTInc;
        this.sequencial = sequencial;
    }

    public Integer getIDLoteContrato() {
        return iDLoteContrato;
    }

    public void setIDLoteContrato(Integer iDLoteContrato) {
        this.iDLoteContrato = iDLoteContrato;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public boolean getSequencial() {
        return sequencial;
    }

    public void setSequencial(boolean sequencial) {
        this.sequencial = sequencial;
    }

    public Integer getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Integer objetivo) {
        this.objetivo = objetivo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getNumeroIni() {
        return numeroIni;
    }

    public void setNumeroIni(Integer numeroIni) {
        this.numeroIni = numeroIni;
    }

    public Integer getNumeroFim() {
        return numeroFim;
    }

    public void setNumeroFim(Integer numeroFim) {
        this.numeroFim = numeroFim;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Funcionario getIDFuncionario() {
        return iDFuncionario;
    }

    public void setIDFuncionario(Funcionario iDFuncionario) {
        this.iDFuncionario = iDFuncionario;
    }

    public Fornecedor getIDFornecedor() {
        return iDFornecedor;
    }

    public void setIDFornecedor(Fornecedor iDFornecedor) {
        this.iDFornecedor = iDFornecedor;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDLoteContrato != null ? iDLoteContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lotecontrato)) {
            return false;
        }
        Lotecontrato other = (Lotecontrato) object;
        if ((this.iDLoteContrato == null && other.iDLoteContrato != null) || (this.iDLoteContrato != null && !this.iDLoteContrato.equals(other.iDLoteContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Lotecontrato[ iDLoteContrato=" + iDLoteContrato + " ]";
    }
    
}
