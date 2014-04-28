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
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "ramoatividade")
public class Ramoatividade implements Serializable {
    @OneToMany(mappedBy = "idRamoAtividade")
    private List<Fornecedor> fornecedorList;
    
    
    @Basic(optional =     false)
    @NotNull
    @Column(name = "DTinc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTinc;
    @Column(name =     "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDRamoAtividade")
    private Integer iDRamoAtividade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "IDUsuarioAlt")
    private Integer iDUsuarioAlt;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;
    

    public Ramoatividade() {
    }

    public Ramoatividade(Integer iDRamoAtividade) {
        this.iDRamoAtividade = iDRamoAtividade;
    }

    public Ramoatividade(Integer iDRamoAtividade, String descricao, Date dTinc) {
        this.iDRamoAtividade = iDRamoAtividade;
        this.descricao = descricao;
        this.dTinc = dTinc;
    }

    public Integer getIDRamoAtividade() {
        return iDRamoAtividade;
    }

    public void setIDRamoAtividade(Integer iDRamoAtividade) {
        this.iDRamoAtividade = iDRamoAtividade;
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

    
    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDRamoAtividade != null ? iDRamoAtividade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ramoatividade)) {
            return false;
        }
        Ramoatividade other = (Ramoatividade) object;
        if ((this.iDRamoAtividade == null && other.iDRamoAtividade != null) || (this.iDRamoAtividade != null && !this.iDRamoAtividade.equals(other.iDRamoAtividade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Ramoatividade[ iDRamoAtividade=" + iDRamoAtividade + " ]";
    }

    public Date getDTinc() {
        return dTinc;
    }

    public void setDTinc(Date dTinc) {
        this.dTinc = dTinc;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
    }

    @XmlTransient
    @JsonIgnore
    public List<Fornecedor> getFornecedorList() {
        return fornecedorList;
    }

    public void setFornecedorList(List<Fornecedor> fornecedorList) {
        this.fornecedorList = fornecedorList;
    }

   
    
}
