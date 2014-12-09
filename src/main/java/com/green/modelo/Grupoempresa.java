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
import javax.persistence.GenerationType;
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
@Table(name = "grupoempresa")
public class Grupoempresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idgrupoempresa")
    private Integer idgrupoempresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtinc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinc;
    @Column(name = "idusuarioalt")
    private Integer idusuarioalt;
    @Column(name = "dtalt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtalt;
    @Lob
    @Column(name = "imagem")
    private byte[] imagem;
    @JoinColumn(name = "idusuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;
   

    public Grupoempresa() {
    }

    public Grupoempresa(Integer idgrupoempresa) {
        this.idgrupoempresa = idgrupoempresa;
    }

    public Grupoempresa(Integer idgrupoempresa, String descricao, Date dtinc) {
        this.idgrupoempresa = idgrupoempresa;
        this.descricao = descricao;
        this.dtinc = dtinc;
    }

    public Integer getIdgrupoempresa() {
        return idgrupoempresa;
    }

    public void setIdgrupoempresa(Integer idgrupoempresa) {
        this.idgrupoempresa = idgrupoempresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtinc() {
        return dtinc;
    }

    public void setDtinc(Date dtinc) {
        this.dtinc = dtinc;
    }

    public Integer getIdusuarioalt() {
        return idusuarioalt;
    }

    public void setIdusuarioalt(Integer idusuarioalt) {
        this.idusuarioalt = idusuarioalt;
    }

    public Date getDtalt() {
        return dtalt;
    }

    public void setDtalt(Date dtalt) {
        this.dtalt = dtalt;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupoempresa != null ? idgrupoempresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupoempresa)) {
            return false;
        }
        Grupoempresa other = (Grupoempresa) object;
        if ((this.idgrupoempresa == null && other.idgrupoempresa != null) || (this.idgrupoempresa != null && !this.idgrupoempresa.equals(other.idgrupoempresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Grupoempresa[ idgrupoempresa=" + idgrupoempresa + " ]";
    }
    
}
