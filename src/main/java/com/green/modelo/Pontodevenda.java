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
@Table(name = "pontodevenda")
public class Pontodevenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "IDPontodeVenda")
    private Integer iDPontodeVenda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descricao")
    private String descricao;
    @Size(max = 45)
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
    @Size(max = 11)
    @Column(name = "Cep")
    private String cep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTInc;
    @JoinColumn(name = "IDUsuarioAlt", referencedColumnName = "IDUsuario")
    @ManyToOne
    private Usuario iDUsuarioAlt;
    @Column(name = "DTAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTAlt;
    @JoinColumn(name = "IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuario iDUsuario;

    public Pontodevenda() {
    }

    public Pontodevenda(Integer iDPontodeVenda) {
        this.iDPontodeVenda = iDPontodeVenda;
    }

    public Pontodevenda(Integer iDPontodeVenda, String descricao, Date dTInc) {
        this.iDPontodeVenda = iDPontodeVenda;
        this.descricao = descricao;
        this.dTInc = dTInc;
    }

    public Integer getIDPontodeVenda() {
        return iDPontodeVenda;
    }

    public void setIDPontodeVenda(Integer iDPontodeVenda) {
        this.iDPontodeVenda = iDPontodeVenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Date getDTInc() {
        return dTInc;
    }

    public void setDTInc(Date dTInc) {
        this.dTInc = dTInc;
    }

    public Usuario getIDUsuarioAlt() {
        return iDUsuarioAlt;
    }

    public void setIDUsuarioAlt(Usuario iDUsuarioAlt) {
        this.iDUsuarioAlt = iDUsuarioAlt;
    }

    public Date getDTAlt() {
        return dTAlt;
    }

    public void setDTAlt(Date dTAlt) {
        this.dTAlt = dTAlt;
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
        hash += (iDPontodeVenda != null ? iDPontodeVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pontodevenda)) {
            return false;
        }
        Pontodevenda other = (Pontodevenda) object;
        if ((this.iDPontodeVenda == null && other.iDPontodeVenda != null) || (this.iDPontodeVenda != null && !this.iDPontodeVenda.equals(other.iDPontodeVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Pontodevenda[ iDPontodeVenda=" + iDPontodeVenda + " ]";
    }
}
