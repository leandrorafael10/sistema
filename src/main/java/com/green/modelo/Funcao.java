/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "funcao")
@XmlRootElement
public class Funcao implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDFuncao")
    private List<Funcionario> funcionarioList;
    
    @Basic(optional =     false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name =     "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDFuncao")
    private Integer iDFuncao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @Column(name = "Hierarquia")
    private Integer hierarquia;
    
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;

    public Funcao() {
    }

    public Funcao(Integer iDFuncao) {
        this.iDFuncao = iDFuncao;
    }

    public Funcao(Integer iDFuncao, String descricao, Date dTInc) {
        this.iDFuncao = iDFuncao;
        this.descricao = descricao;
        this.dTInc = dTInc;
    }

    public Integer getIDFuncao() {
        return iDFuncao;
    }

    public void setIDFuncao(Integer iDFuncao) {
        this.iDFuncao = iDFuncao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Integer iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public Integer getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(Integer hierarquia) {
        this.hierarquia = hierarquia;
    }

    
    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDFuncao != null ? iDFuncao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcao)) {
            return false;
        }
        Funcao other = (Funcao) object;
        if ((this.iDFuncao == null && other.iDFuncao != null) || (this.iDFuncao != null && !this.iDFuncao.equals(other.iDFuncao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Funcao[ iDFuncao=" + iDFuncao + " ]";
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    
    public List<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    

    
    
}
