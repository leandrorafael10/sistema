/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {
    @Lob
    @Column(name = "imagem")
    private byte[] imagem;
    @OneToMany(mappedBy = "idfornecedor")
    private List<Funcionario> funcionarioList;
   

    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDFornecedor")
    private Integer iDFornecedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ativo")
    private boolean ativo;
    @Lob
    @Size(max = 65535)
    @Column(name = "Obs")
    private String obs;
    @JoinColumn(name = "IDUsuarioAlt", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioAlt;
    @Size(max = 50)
    @Column(name = "Procurador")
    private String procurador;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IdRamoAtividade", referencedColumnName = "IDRamoAtividade")
    @ManyToOne
    private Ramoatividade idRamoAtividade;
    @JoinColumn(name = "IDPessoa", referencedColumnName = "IDPessoa")
    @ManyToOne(optional = false)
    private Pessoa iDPessoa;
    @JoinColumn(name = "IDLimite", referencedColumnName = "IDLimite")
    @ManyToOne
    private Limite iDLimite;
    @JoinColumn(name = "IDDesconto", referencedColumnName = "IDDesconto")
    @ManyToOne
    private Desconto iDDesconto;

    public Fornecedor() {
    }

    public Fornecedor(Integer iDFornecedor) {
        this.iDFornecedor = iDFornecedor;
    }

    public Fornecedor(Integer iDFornecedor, boolean ativo, Date dTInc) {
        this.iDFornecedor = iDFornecedor;
        this.ativo = ativo;
        this.dTInc = dTInc;
    }

    public Integer getIDFornecedor() {
        return iDFornecedor;
    }

    public void setIDFornecedor(Integer iDFornecedor) {
        this.iDFornecedor = iDFornecedor;
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

    public Usuario getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }


    public String getProcurador() {
        return procurador;
    }

    public void setProcurador(String procurador) {
        this.procurador = procurador;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Ramoatividade getIdRamoAtividade() {
        return idRamoAtividade;
    }

    public void setIdRamoAtividade(Ramoatividade idRamoAtividade) {
        this.idRamoAtividade = idRamoAtividade;
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
        hash += (iDFornecedor != null ? iDFornecedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.iDFornecedor == null && other.iDFornecedor != null) || (this.iDFornecedor != null && !this.iDFornecedor.equals(other.iDFornecedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Fornecedor[ iDFornecedor=" + iDFornecedor + " ]";
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

    @XmlTransient
    @JsonIgnore
    public List<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

}
