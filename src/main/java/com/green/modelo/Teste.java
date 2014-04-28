/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "teste")
public class Teste implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idteste")
    private Integer idteste;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Column(name = "sit")
    private Boolean sit;

    public Teste() {
    }

    public Teste(Integer idteste) {
        this.idteste = idteste;
    }

    public Integer getIdteste() {
        return idteste;
    }

    public void setIdteste(Integer idteste) {
        this.idteste = idteste;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getSit() {
        return sit;
    }

    public void setSit(Boolean sit) {
        this.sit = sit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idteste != null ? idteste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teste)) {
            return false;
        }
        Teste other = (Teste) object;
        if ((this.idteste == null && other.idteste != null) || (this.idteste != null && !this.idteste.equals(other.idteste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Teste[ idteste=" + idteste + " ]";
    }
    
}
