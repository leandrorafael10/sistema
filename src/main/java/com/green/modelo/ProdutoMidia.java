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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "produto_midia")
@XmlRootElement
public class ProdutoMidia implements Serializable {
    @Column(name =     "DTalt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTalt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idproduto_midia")
    private Integer idprodutoMidia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tempo_video")
    private String tempoVideo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade_insercao_diaria")
    private int quantidadeInsercaoDiaria;
    @Column(name = "DTinc")
    private Integer dTinc;
    @Column(name = "IDusuarioalt")
    private Integer iDusuarioalt;
    

    public ProdutoMidia() {
    }

    public ProdutoMidia(Integer idprodutoMidia) {
        this.idprodutoMidia = idprodutoMidia;
    }

    public ProdutoMidia(Integer idprodutoMidia, String tempoVideo, String descricao, int quantidadeInsercaoDiaria) {
        this.idprodutoMidia = idprodutoMidia;
        this.tempoVideo = tempoVideo;
        this.descricao = descricao;
        this.quantidadeInsercaoDiaria = quantidadeInsercaoDiaria;
    }

    public Integer getIdprodutoMidia() {
        return idprodutoMidia;
    }

    public void setIdprodutoMidia(Integer idprodutoMidia) {
        this.idprodutoMidia = idprodutoMidia;
    }

    public String getTempoVideo() {
        return tempoVideo;
    }

    public void setTempoVideo(String tempoVideo) {
        this.tempoVideo = tempoVideo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeInsercaoDiaria() {
        return quantidadeInsercaoDiaria;
    }

    public void setQuantidadeInsercaoDiaria(int quantidadeInsercaoDiaria) {
        this.quantidadeInsercaoDiaria = quantidadeInsercaoDiaria;
    }

    public Integer getDTinc() {
        return dTinc;
    }

    public void setDTinc(Integer dTinc) {
        this.dTinc = dTinc;
    }

    public Integer getIDusuarioalt() {
        return iDusuarioalt;
    }

    public void setIDusuarioalt(Integer iDusuarioalt) {
        this.iDusuarioalt = iDusuarioalt;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprodutoMidia != null ? idprodutoMidia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoMidia)) {
            return false;
        }
        ProdutoMidia other = (ProdutoMidia) object;
        if ((this.idprodutoMidia == null && other.idprodutoMidia != null) || (this.idprodutoMidia != null && !this.idprodutoMidia.equals(other.idprodutoMidia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.ProdutoMidia[ idprodutoMidia=" + idprodutoMidia + " ]";
    }

    public Date getDTalt() {
        return dTalt;
    }

    public void setDTalt(Date dTalt) {
        this.dTalt = dTalt;
    }
    
}
