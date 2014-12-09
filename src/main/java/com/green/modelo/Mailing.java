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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "mailing")
public class Mailing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDMailing")
    private Integer iDMailing;
    @Size(min = 1, max = 50)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "razao")
    private String razao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "telefoneFixo")
    private String telefoneFixo;
    @Size(max = 15)
    @Column(name = "telefoneCel")
    private String telefoneCel;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTinc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @Column(name = "DTvalidade")
    @Temporal(TemporalType.DATE)
    private Date dTvalidade;
    @Size(max = 50)
    @Column(name = "http")
    private String http;
    @JoinColumn(name = "IDusuarioInc", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDusuarioInc;
    @JoinColumn(name = "IDFuncionario", referencedColumnName = "IDFuncionario")
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Funcionario iDFuncionario;
    @JoinColumn(name = "IDRamoAtividade", referencedColumnName = "IDRamoAtividade")
    @ManyToOne
    private Ramoatividade iDRamoatividade;
    @JoinColumn(name = "IDContrato",referencedColumnName = "idcontrato_midia")
    @ManyToOne(fetch = FetchType.EAGER)
    private ContratoMidia iDContratoMidia;
    @OneToMany(mappedBy = "iDMailing", fetch = FetchType.EAGER)
    private List<Obsmailing> iDobsMailing;

    public Mailing() {
    }

    public Mailing(Integer iDMailing) {
        this.iDMailing = iDMailing;
    }

    public Mailing(Integer iDMailing, String nome, String telefoneFixo, String telefoneCel, String email, int status) {
        this.iDMailing = iDMailing;
        this.nome = nome;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCel = telefoneCel;
        this.email = email;
        this.status = status;
    }

    public Integer getIDMailing() {
        return iDMailing;
    }

    public void setIDMailing(Integer iDMailing) {
        this.iDMailing = iDMailing;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefoneCel() {
        return telefoneCel;
    }

    public void setTelefoneCel(String telefoneCel) {
        this.telefoneCel = telefoneCel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Funcionario getIDFuncionario() {
        return iDFuncionario;
    }

    public void setIDFuncionario(Funcionario iDFuncionario) {
        this.iDFuncionario = iDFuncionario;
    }

    public Usuario getIDusuarioInc() {
        return iDusuarioInc;
    }

    public void setIDusuarioInc(Usuario iDusuarioInc) {
        this.iDusuarioInc = iDusuarioInc;
    }

    public List<Obsmailing> getIDobsMailing() {
        return iDobsMailing;
    }

    public void setIDobsMailing(List<Obsmailing> iDobsMailing) {
        this.iDobsMailing = iDobsMailing;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Date getDTvalidade() {
        return dTvalidade;
    }

    public void setDTvalidade(Date dTvalidade) {
        this.dTvalidade = dTvalidade;
    }

    public Ramoatividade getIDRamoatividade() {
        return iDRamoatividade;
    }

    public void setIDRamoatividade(Ramoatividade iDRamoatividade) {
        this.iDRamoatividade = iDRamoatividade;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public ContratoMidia getIDContratoMidia() {
        return iDContratoMidia;
    }

    public void setIDContratoMidia(ContratoMidia iDContratoMidia) {
        this.iDContratoMidia = iDContratoMidia;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDMailing != null ? iDMailing.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mailing)) {
            return false;
        }
        Mailing other = (Mailing) object;
        if ((this.iDMailing == null && other.iDMailing != null) || (this.iDMailing != null && !this.iDMailing.equals(other.iDMailing))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Mailing[ iDMailing=" + iDMailing + " ]";
    }
}
