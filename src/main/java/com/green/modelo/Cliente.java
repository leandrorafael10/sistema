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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
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
    @Column(name = "IDCliente")
    private Integer iDCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ativo")
    private boolean ativo;
    @Lob
    @Size(max = 65535)
    @Column(name = "Obs")
    private String obs;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @Column(name = "Sexo")
    private Character sexo;
    @Size(max = 45)
    @Column(name = "Profissao")
    private String profissao;
    @Size(max = 45)
    @Column(name = "GrauInstrucao")
    private String grauInstrucao;
    //essas são as chaves estrangeiras o primeiro nome e nome da coluna com chave o segundo nome e uma 
    //referencia ao nome da chave primaria da tabela que ela e referencia.
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDTipocliente", referencedColumnName = "IDTipocliente")
    @ManyToOne
    private Tipocliente iDTipocliente;
    @JoinColumn(name = "IDRamoAtividade", referencedColumnName = "IDRamoAtividade")
    @ManyToOne
    private Ramoatividade iDRamoAtividade;
    @JoinColumn(name = "IDPessoa", referencedColumnName = "IDPessoa")
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REMOVE},optional = false)
    private Pessoa iDPessoa;
    @JoinColumn(name = "IDLimite", referencedColumnName = "IDLimite")
    @ManyToOne
    private Limite iDLimite;
    @JoinColumn(name = "IDDesconto", referencedColumnName = "IDDesconto")
    @ManyToOne
    private Desconto iDDesconto;
    @OneToMany(mappedBy = "iDcliente",targetEntity = ContratoMidia.class)
    private List<ContratoMidia> contratos;
    @OneToMany(mappedBy = "iDCliente")
    private  List<Receita> receitas;

    public Cliente() {
    }

    public Cliente(Integer iDCliente) {
        this.iDCliente = iDCliente;
    }

    public Cliente(Integer iDCliente, boolean ativo, Date dTInc) {
        this.iDCliente = iDCliente;
        this.ativo = ativo;
        this.dTInc = dTInc;
    }

    public Integer getIDCliente() {
        return iDCliente;
    }

    public void setIDCliente(Integer iDCliente) {
        this.iDCliente = iDCliente;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Integer iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getGrauInstrucao() {
        return grauInstrucao;
    }

    public void setGrauInstrucao(String grauInstrucao) {
        this.grauInstrucao = grauInstrucao;
    }

    

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Tipocliente getIDTipocliente() {
        return iDTipocliente;
    }

    public void setIDTipocliente(Tipocliente iDTipocliente) {
        this.iDTipocliente = iDTipocliente;
    }

    public Ramoatividade getIDRamoAtividade() {
        return iDRamoAtividade;
    }

    public void setIDRamoAtividade(Ramoatividade iDRamoAtividade) {
        this.iDRamoAtividade = iDRamoAtividade;
    }

    public Pessoa getIDPessoa() {
        return iDPessoa;
    }

    public void setIDPessoa(Pessoa iDPessoa) {
        this.iDPessoa = iDPessoa;
    }

    public Limite getIDLimite() {
        return iDLimite;
    }

    public void setIDLimite(Limite iDLimite) {
        this.iDLimite = iDLimite;
    }

    public Desconto getIDDesconto() {
        return iDDesconto;
    }

    public void setIDDesconto(Desconto iDDesconto) {
        this.iDDesconto = iDDesconto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCliente != null ? iDCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.iDCliente == null && other.iDCliente != null) || (this.iDCliente != null && !this.iDCliente.equals(other.iDCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Cliente[ iDCliente=" + iDCliente + " ]";
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

    public List<ContratoMidia> getContratos() {
        return contratos;
    }

    public void setContratos(List<ContratoMidia> contratos) {
        this.contratos = contratos;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }
    
}
