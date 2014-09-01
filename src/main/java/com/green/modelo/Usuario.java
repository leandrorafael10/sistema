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
@Table(name = "usuario")
@XmlRootElement
public class Usuario implements Serializable {
    @OneToMany(mappedBy = "iDUsuarioAlt")
    private List<Fornecedor> fornecedorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDUsuario")
    private List<Fornecedor> fornecedorList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDUsuario")
    private List<Desconto> descontoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDUsuario")
    private List<Funcao> funcaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDUsuario")
    private List<Limite> limiteList;
    @OneToMany(mappedBy = "idusuarioAlt")
    private List<Funcionariometa> funcionariometaList;
    @OneToMany(mappedBy = "iDUsuarioInc")
    private List<Funcionariometa> funcionariometaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDUsuario")
    private List<Funcionario> funcionarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDUsuario")
    private List<Equipevenda> equipevendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDUsuario")
    private List<Ramoatividade> ramoatividadeList;
    
    
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
    @Column(name = "IDUsuario")
    private Integer iDUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Senha")
    private String senha;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ativo")
    private boolean ativo;
    @Column(name = "IDUsuarioInc")
    private Integer iDUsuarioInc;
    
    //cavas estrangeiras e suas relacoes com as tabelas.
    
    @JoinColumn(name = "IDGrupoAcesso", referencedColumnName = "IDGrupoAcesso")
    @ManyToOne
    private Grupoacesso iDGrupoAcesso;
    @JoinColumn(name = "IDFuncionario", referencedColumnName = "IDFuncionario")
    @ManyToOne
    private Funcionario iDFuncionario;
    

    public Usuario() {
    }

    public Usuario(Integer iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Usuario(Integer iDUsuario, String login, String senha, Date dTInc, boolean ativo, int iDUsuarioInc) {
        this.iDUsuario = iDUsuario;
        this.login = login;
        this.senha = senha;
        this.dTInc = dTInc;
        this.ativo = ativo;
        this.iDUsuarioInc = iDUsuarioInc;
    }

    public Integer getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Integer iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Integer iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    

    public Grupoacesso getIDGrupoAcesso() {
        return iDGrupoAcesso;
    }

    public void setIDGrupoAcesso(Grupoacesso iDGrupoAcesso) {
        this.iDGrupoAcesso = iDGrupoAcesso;
    }

    public Funcionario getIDFuncionario() {
        return iDFuncionario;
    }

    public void setIDFuncionario(Funcionario iDFuncionario) {
        this.iDFuncionario = iDFuncionario;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDUsuario != null ? iDUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.iDUsuario == null && other.iDUsuario != null) || (this.iDUsuario != null && !this.iDUsuario.equals(other.iDUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Usuario[ iDUsuario=" + iDUsuario + " ]";
    }

    public Integer getIDUsuarioInc() {
        return iDUsuarioInc;
    }

    public void setIDUsuarioInc(Integer iDUsuarioInc) {
        this.iDUsuarioInc = iDUsuarioInc;
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

   
    public List<Fornecedor> getFornecedorList() {
        return fornecedorList;
    }

    public void setFornecedorList(List<Fornecedor> fornecedorList) {
        this.fornecedorList = fornecedorList;
    }

   
    public List<Fornecedor> getFornecedorList1() {
        return fornecedorList1;
    }

    public void setFornecedorList1(List<Fornecedor> fornecedorList1) {
        this.fornecedorList1 = fornecedorList1;
    }

    
    public List<Desconto> getDescontoList() {
        return descontoList;
    }

    public void setDescontoList(List<Desconto> descontoList) {
        this.descontoList = descontoList;
    }

  
    public List<Funcao> getFuncaoList() {
        return funcaoList;
    }

    public void setFuncaoList(List<Funcao> funcaoList) {
        this.funcaoList = funcaoList;
    }

    
    public List<Limite> getLimiteList() {
        return limiteList;
    }

    public void setLimiteList(List<Limite> limiteList) {
        this.limiteList = limiteList;
    }

   
    public List<Funcionariometa> getFuncionariometaList() {
        return funcionariometaList;
    }

    public void setFuncionariometaList(List<Funcionariometa> funcionariometaList) {
        this.funcionariometaList = funcionariometaList;
    }

  
    public List<Funcionariometa> getFuncionariometaList1() {
        return funcionariometaList1;
    }

    public void setFuncionariometaList1(List<Funcionariometa> funcionariometaList1) {
        this.funcionariometaList1 = funcionariometaList1;
    }

   
    public List<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

   
    public List<Equipevenda> getEquipevendaList() {
        return equipevendaList;
    }

    public void setEquipevendaList(List<Equipevenda> equipevendaList) {
        this.equipevendaList = equipevendaList;
    }

   
    public List<Ramoatividade> getRamoatividadeList() {
        return ramoatividadeList;
    }

    public void setRamoatividadeList(List<Ramoatividade> ramoatividadeList) {
        this.ramoatividadeList = ramoatividadeList;
    }

   
    
}
