/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Pontodevenda;
import com.green.rn.PontodevendaRN;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "pontodevendaBean")
@ViewScoped
public class PontodevendaBean implements Serializable {

    private Pontodevenda pontodevenda;
    @ManagedProperty("#{pontodevendaRN}")
    private PontodevendaRN pontodevendaRN;

    @PostConstruct
    private void init() {
        this.pontodevenda = new Pontodevenda();
    }

    public void salvar(ActionEvent event) {
        
        getPontodevendaRN().salvar(getPontodevenda());
        setPontodevenda(new Pontodevenda());
       
    }

    public void atualizar(ActionEvent event) {
        
        getPontodevendaRN().atualizar(getPontodevenda());
        setPontodevenda(new Pontodevenda());
    }

    public Pontodevenda getPontodevenda() {
        return pontodevenda;
    }

    public void setPontodevenda(Pontodevenda pontodevenda) {
        this.pontodevenda = pontodevenda;
    }

    public PontodevendaRN getPontodevendaRN() {
        return pontodevendaRN;
    }

    public void setPontodevendaRN(PontodevendaRN pontodevendaRN) {
        this.pontodevendaRN = pontodevendaRN;
    }
}
