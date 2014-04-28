/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Pessoa;
import com.green.rn.PessoaRN;
import com.green.util.Endereco;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable {

    @ManagedProperty("#{pessoaRN}")
    private PessoaRN pessoaRN;
    
    @ManagedProperty("#{endereco}")
    private Endereco endereco;
    
     private Pessoa pessoa =new Pessoa();
    private List<Pessoa> pessoas = null;
    
   

   
    
   

    public PessoaBean() {
    }

    @PostConstruct
    private void init() {
        setPessoas(pessoaRN.listar());
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }



    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaRN getPessoaRN() {
        return pessoaRN;
    }

    public void setPessoaRN(PessoaRN pessoaRN) {
        this.pessoaRN = pessoaRN;
    }
    
}
