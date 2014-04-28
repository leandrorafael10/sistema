/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Atividade;
import com.green.rn.AtividadeRN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author leandro.silva
 */
@SuppressWarnings("serial")
@ManagedBean(name = "atividadeBean")
@ViewScoped
public class AtividadeBean implements Serializable {

    @ManagedProperty("#{atividadeRN}")
    private AtividadeRN atividadeRN;
    
    private Atividade atividade = new Atividade();
    private List<Atividade> atividades = new ArrayList<Atividade>();
    private Atividade atividadeNovo = new Atividade();
    private Atividade atividadeAtualizado = new Atividade();
    
    @PostConstruct
    private void init(){
        setAtividades(getAtividadeRN().listar());
    }
   

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public Atividade getAtividadeAtualizado() {
        return atividadeAtualizado;
    }

    public void setAtividadeAtualizado(Atividade atividadeAtualizado) {
        this.atividadeAtualizado = atividadeAtualizado;
    }

    public Atividade getAtividadeNovo() {
        return atividadeNovo;
    }

    public void setAtividadeNovo(Atividade atividadeNovo) {
        this.atividadeNovo = atividadeNovo;
    }
    
    
    public void salvar() {
        getAtividadeRN().salvar(getAtividadeNovo());
        this.atividadeNovo = new Atividade();
    }
    public void excluir(){
        getAtividadeRN().excluir(getAtividade());
        this.atividade = new Atividade();
    }
    public void atualizar(){
        getAtividadeAtualizado().setIDAtividade(getAtividade().getIDAtividade());
        getAtividadeAtualizado().setIDUsuario(getAtividade().getIDUsuario());
        getAtividadeAtualizado().setDTInc(getAtividade().getDTInc());
        if(getAtividadeAtualizado().getObs() ==null || getAtividadeAtualizado().getObs().equals("")){
            getAtividadeAtualizado().setObs(getAtividade().getObs());
        }
        getAtividadeRN().atualizar(getAtividadeAtualizado());
        this.atividadeAtualizado = new Atividade();
    }
    public void listando(){
         setAtividades(getAtividadeRN().listar());
    }
    public Atividade getAtividade() {
        return atividade;
    }
    
    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
    
    public AtividadeRN getAtividadeRN() {
        return atividadeRN;
    }
    
    public void setAtividadeRN(AtividadeRN atividadeRN) {
        this.atividadeRN = atividadeRN;
    }
}
