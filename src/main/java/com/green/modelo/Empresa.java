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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "empresa")
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDEmpresa")
    private Integer iDEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Razao")
    private String razao;
    @Size(max = 50)
    @Column(name = "Fantasia")
    private String fantasia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Cnpj")
    private String cnpj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "InscricaoEstadual")
    private String inscricaoEstadual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Logradouro")
    private String logradouro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Numero")
    private String numero;
    @Size(max = 20)
    @Column(name = "Complemento")
    private String complemento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Bairro")
    private String bairro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Cidade")
    private String cidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Pais")
    private String pais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Dtinc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinc;
    @Size(max = 50)
    @Column(name = "HTTP")
    private String http;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @Lob
    @Size(max = 65535)
    @Column(name = "Obs")
    private String obs;
    @Lob
    @Column(name = "Imagem")
    private byte[] imagem;
    @Size(max = 45)
    @Column(name = "Procurador")
    private String procurador;
    @Column(name = "DTComemora")
    @Temporal(TemporalType.DATE)
    private Date dTComemora;
    @Size(max = 10)
    @Column(name = "CEP")
    private String cep;
    @Size(max = 20)
    @Column(name = "InscricaoMunicipal")
    private String inscricaoMunicipal;
    
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDRamoAtividade", referencedColumnName = "IDRamoAtividade")
    @ManyToOne(optional = false)
    private Ramoatividade iDRamoAtividade;
    @JoinColumn(name = "idgrupoempresa", referencedColumnName = "idgrupoempresa")
    @ManyToOne
    private Grupoempresa idgrupoempresa;

    public Empresa() {
    }

    public Empresa(Integer iDEmpresa) {
        this.iDEmpresa = iDEmpresa;
    }

    public Empresa(Integer iDEmpresa, String razao, String cnpj, String inscricaoEstadual, String logradouro, String numero, String bairro, String cidade, String estado, String pais, Date dtinc) {
        this.iDEmpresa = iDEmpresa;
        this.razao = razao;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.dtinc = dtinc;
    }

    public Integer getIDEmpresa() {
        return iDEmpresa;
    }

    public void setIDEmpresa(Integer iDEmpresa) {
        this.iDEmpresa = iDEmpresa;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getDtinc() {
        return dtinc;
    }

    public void setDtinc(Date dtinc) {
        this.dtinc = dtinc;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    public Integer getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Integer iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getProcurador() {
        return procurador;
    }

    public void setProcurador(String procurador) {
        this.procurador = procurador;
    }

    public Date getDTComemora() {
        return dTComemora;
    }

    public void setDTComemora(Date dTComemora) {
        this.dTComemora = dTComemora;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    
    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Ramoatividade getIDRamoAtividade() {
        return iDRamoAtividade;
    }

    public void setIDRamoAtividade(Ramoatividade iDRamoAtividade) {
        this.iDRamoAtividade = iDRamoAtividade;
    }

    public Grupoempresa getIdgrupoempresa() {
        return idgrupoempresa;
    }

    public void setIdgrupoempresa(Grupoempresa idgrupoempresa) {
        this.idgrupoempresa = idgrupoempresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDEmpresa != null ? iDEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.iDEmpresa == null && other.iDEmpresa != null) || (this.iDEmpresa != null && !this.iDEmpresa.equals(other.iDEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Empresa[ iDEmpresa=" + iDEmpresa + " ]";
    }
    
}
