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


/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "pessoa")
@XmlRootElement
public class Pessoa implements Serializable {
    @Column(name =     "DTNascimento")
    @Temporal(TemporalType.DATE)
    private Date dTNascimento;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDPessoa")
    private Integer iDPessoa;
    @Basic(optional = false)
    @NotNull
    @Size(max = 70)
    @Column(name = "Razao")
    private String razao;
    @Size(max = 50)
    @Column(name = "Fantasia")
    private String fantasia;
    @Size(max = 20)
    @Column(name = "Rg")
    private String rg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CnpjCpf")
    private String cnpjCpf;
    @Size(max = 20)
    @Column(name = "InscricaoEstadual")
    private String inscricaoEstadual;
    @Size(max = 50)
    @Column(name = "Logradouro")
    private String logradouro;
    @Size(max = 20)
    @Column(name = "Numero")
    private String numero;
    @Size(max = 20)
    @Column(name = "Complemento")
    private String complemento;
    @Size(max = 30)
    @Column(name = "Bairro")
    private String bairro;
    @Size(max = 25)
    @Column(name = "Cidade")
    private String cidade;
    @Size(max = 2)
    @Column(name = "Estado")
    private String estado;
    @Size(max = 20)
    @Column(name = "Pais")
    private String pais;
    @Size(max = 50)
    @Column(name = "HTTP")
    private String http;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FisicaJuridica")
    private boolean fisicaJuridica;
    @Size(max = 11)
    @Column(name = "Cep")
    private String cep;
    @Size(max = 30)
    @Column(name = "Naturalidade")
    private String naturalidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "situacao")
    private boolean situacao;
    @Size(max = 20)
    @Column(name = "InscricaoMunicipal")
    private String inscricaoMunicipal;
    @Lob
    @Size(max = 65535)
    @Column(name = "Referencia")
    private String referencia;
    //@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Email")
    private String email;
    @OneToMany(mappedBy ="iDPessoa" ,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Contato> contatosList;
    
    public Pessoa() {
    }

    public Pessoa(Integer iDPessoa) {
        this.iDPessoa = iDPessoa;
    }

    public Pessoa(Integer iDPessoa, String razao, String cnpjCpf, boolean fisicaJuridica, boolean situacao) {
        this.iDPessoa = iDPessoa;
        this.razao = razao;
        this.cnpjCpf = cnpjCpf;
        this.fisicaJuridica = fisicaJuridica;
        this.situacao = situacao;
    }

    public Integer getIDPessoa() {
        return iDPessoa;
    }

    public void setIDPessoa(Integer iDPessoa) {
        this.iDPessoa = iDPessoa;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
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

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public boolean getFisicaJuridica() {
        return fisicaJuridica;
    }

    public void setFisicaJuridica(boolean fisicaJuridica) {
        this.fisicaJuridica = fisicaJuridica;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPessoa != null ? iDPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.iDPessoa == null && other.iDPessoa != null) || (this.iDPessoa != null && !this.iDPessoa.equals(other.iDPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Pessoa[ iDPessoa=" + iDPessoa + " ]";
    }

    public Date getDTNascimento() {
        return dTNascimento;
    }

    public void setDTNascimento(Date dTNascimento) {
        this.dTNascimento = dTNascimento;
    }

    public List<Contato> getContatosList() {
        return contatosList;
    }

    public void setContatosList(List<Contato> contatosList) {
        this.contatosList = contatosList;
    }

    
    
}
