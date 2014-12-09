/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.green.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "historico_demicao")
public class HistoricoDemicao implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idhistorico_demicao")
    private Integer iDHistoricoDemicao;
    
    @Column(name = "DTadmicao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dTadmicao;
    @Column(name = "DTdemicao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dTdemicao;

    public HistoricoDemicao() {
        this.iDFuncionario =new Funcionario();
    }

    public HistoricoDemicao(Integer iDHistoricoDemicao, Date dTadmicao, Date dTdemicao, Funcionario iDFuncionario, Usuario iDUsuario) {
        this.iDHistoricoDemicao = iDHistoricoDemicao;
        this.dTadmicao = dTadmicao;
        this.dTdemicao = dTdemicao;
        this.iDFuncionario = iDFuncionario;
        this.iDUsuario = iDUsuario;
    }
    
    

    public Date getDTadmicao() {
        return dTadmicao;
    }

    public void setDTadmicao(Date dTadmicao) {
        this.dTadmicao = dTadmicao;
    }

    public Date getDTdemicao() {
        return dTdemicao;
    }

    public void setDTdemicao(Date dTdemicao) {
        this.dTdemicao = dTdemicao;
    }

    public Funcionario getIDFuncionario() {
        return iDFuncionario;
    }

    public void setIDFuncionario(Funcionario iDFuncionario) {
        this.iDFuncionario = iDFuncionario;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }
    
    
    
    @JoinColumn(name = "IDFuncionario",referencedColumnName = "IDFuncionario")
    @ManyToOne(optional = false)
    private Funcionario iDFuncionario;
    
    @JoinColumn(name = "IDUsuario",referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    

    public Integer getiDHistoricoDemicao() {
        return iDHistoricoDemicao;
    }

    public void setiDHistoricoDemicao(Integer iDHistoricoDemicao) {
        this.iDHistoricoDemicao = iDHistoricoDemicao;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDHistoricoDemicao != null ? iDHistoricoDemicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the iDHistoricoDemicao fields are not set
        if (!(object instanceof HistoricoDemicao)) {
            return false;
        }
        HistoricoDemicao other = (HistoricoDemicao) object;
        if ((this.iDHistoricoDemicao == null && other.iDHistoricoDemicao != null) || (this.iDHistoricoDemicao != null && !this.iDHistoricoDemicao.equals(other.iDHistoricoDemicao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.HistoricoDemicao[ id=" + iDHistoricoDemicao + " ]";
    }
    
}
