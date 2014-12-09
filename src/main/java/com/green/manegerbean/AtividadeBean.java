/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.green.rn.AtividadeRN;
import com.green.modelo.Atividade;



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
    private Atividade atividadeNovo = new Atividade();

    @PostConstruct
    private void init() {
        this.atividade = new Atividade();
        this.atividadeNovo = new Atividade();

    }

    public Atividade getAtividadeNovo() {
        return atividadeNovo;
    }

    public void setAtividadeNovo(Atividade atividadeNovo) {
        this.atividadeNovo = atividadeNovo;
    }

    public void salvar(ActionEvent event) {
        getAtividadeRN().salvar(getAtividadeNovo());
        this.atividadeNovo = new Atividade();
    }

    public void excluir(ActionEvent event) {
        getAtividadeRN().excluir(getAtividade());
        this.atividade = new Atividade();
    }

    public void atualizar(ActionEvent event) {
        getAtividadeRN().atualizar(getAtividade());
        this.atividade = new Atividade();
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
