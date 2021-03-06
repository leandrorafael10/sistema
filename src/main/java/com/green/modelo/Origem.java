/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author leandro.silva
 */
@Entity
@Table(name = "origem")
public class Origem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "idorigem")
    private Integer idorigem;
    @JoinColumn(name = "IDContrato_midia", referencedColumnName = "idcontrato_midia")
    @OneToOne(fetch = FetchType.EAGER)
    private ContratoMidia IDContratoMidia;
    @OneToMany(mappedBy ="idorigem" ,cascade={CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Receita> receitas;
    

    public Origem() {
    }

    public Origem(Integer idorigem) {
        this.idorigem = idorigem;
    }

    public Integer getIdorigem() {
        return idorigem;
    }

    public void setIdorigem(Integer idorigem) {
        this.idorigem = idorigem;
    }

    public ContratoMidia getIDContratoMidia() {
        return IDContratoMidia;
    }

    public void setIDContratoMidia(ContratoMidia IDContratoMidia) {
        this.IDContratoMidia = IDContratoMidia;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Origem)) {
            return false;
        }
        Origem other = (Origem) obj;
        if ((this.idorigem == null && other.idorigem != null) || (this.idorigem != null && !this.idorigem.equals(other.idorigem))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idorigem != null ? this.idorigem.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.green.modelo.Origem[" + "idorigem=" + idorigem + "]";
    }
    
}
