/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "contato")
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic
    @Column(name = "IDContato")
    private Integer iDContato;
    @Size(max = 45)
    @Column(name = "Profissao")
    private String profissao;
    @Size(max = 3)
    @Column(name = "DDDR")
    private String dddr;
    @Size(max = 8)
    @Column(name = "TelefoneR")
    private String telefoneR;
    @Size(max = 30)
    @Column(name = "Descricao")
    private String descricao;
    @Size(max = 8)
    @Column(name = "RamalC")
    private String ramalC;
    @Size(max = 3)
    @Column(name = "DDDC")
    private String dddc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Size(max = 8)
    @Column(name = "TelefoneC")
    private String telefoneC;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Email")
    private String email;
    @Column(name = "Origem")
    private Character origem;
    @Size(max = 3)
    @Column(name = "DDDCel")
    private String dDDCel;
    @Size(max = 8)
    @Column(name = "TelefoneCel")
    private String telefoneCel;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @Lob
    @Size(max = 65535)
    @Column(name = "Obs")
    private String obs;
    @Size(max = 3)
    @Column(name = "DDDF")
    private String dddf;
    @Size(max = 8)
    @Column(name = "TelefoneF")
    private String telefoneF;
    @Size(max = 50)
    @Column(name = "Http")
    private String http;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    @JoinColumn(name = "IDPessoa", referencedColumnName = "IDPessoa")
    @ManyToOne
    private Pessoa iDPessoa;
    @JoinColumn(name = "IDOperadora", referencedColumnName = "IDOperadora")
    @ManyToOne
    private Operadora iDOperadora;
    @JoinColumn(name = "idfilial", referencedColumnName = "IDFilial")
    @ManyToOne
    private Filial idfilial;
    @JoinColumn(name = "idempresa", referencedColumnName = "IDEmpresa")
    @ManyToOne
    private Empresa idempresa;

    public Contato() {
    }

    public Contato(Integer iDContato) {
        this.iDContato = iDContato;
    }

    public Contato(Integer iDContato, Date dTInc) {
        this.iDContato = iDContato;
        this.dTInc = dTInc;
    }

    public Integer getIDContato() {
        return iDContato;
    }

    public void setIDContato(Integer iDContato) {
        this.iDContato = iDContato;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getDddr() {
        return dddr;
    }

    public void setDddr(String dddr) {
        this.dddr = dddr;
    }

    public String getTelefoneR() {
        return telefoneR;
    }

    public void setTelefoneR(String telefoneR) {
        this.telefoneR = telefoneR;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRamalC() {
        return ramalC;
    }

    public void setRamalC(String ramalC) {
        this.ramalC = ramalC;
    }

    public String getDddc() {
        return dddc;
    }

    public void setDddc(String dddc) {
        this.dddc = dddc;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public String getTelefoneC() {
        return telefoneC;
    }

    public void setTelefoneC(String telefoneC) {
        this.telefoneC = telefoneC;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getOrigem() {
        return origem;
    }

    public void setOrigem(Character origem) {
        this.origem = origem;
    }

    public String getDDDCel() {
        return dDDCel;
    }

    public void setDDDCel(String dDDCel) {
        this.dDDCel = dDDCel;
    }

    public String getTelefoneCel() {
        return telefoneCel;
    }

    public void setTelefoneCel(String telefoneCel) {
        this.telefoneCel = telefoneCel;
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

    public String getDddf() {
        return dddf;
    }

    public void setDddf(String dddf) {
        this.dddf = dddf;
    }

    public String getTelefoneF() {
        return telefoneF;
    }

    public void setTelefoneF(String telefoneF) {
        this.telefoneF = telefoneF;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Pessoa getIDPessoa() {
        return iDPessoa;
    }

    public void setIDPessoa(Pessoa iDPessoa) {
        this.iDPessoa = iDPessoa;
    }

    public Operadora getIDOperadora() {
        return iDOperadora;
    }

    public void setIDOperadora(Operadora iDOperadora) {
        this.iDOperadora = iDOperadora;
    }

    public Filial getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(Filial idfilial) {
        this.idfilial = idfilial;
    }

    public Empresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresa idempresa) {
        this.idempresa = idempresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDContato != null ? iDContato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contato)) {
            return false;
        }
        Contato other = (Contato) object;
        if ((this.iDContato == null && other.iDContato != null) || (this.iDContato != null && !this.iDContato.equals(other.iDContato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Contato[ iDContato=" + iDContato + " ]";
    }
    
}
