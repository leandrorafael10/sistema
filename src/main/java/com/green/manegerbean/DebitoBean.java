/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Debito;
import com.green.rn.DebitoRN;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author leandro.silva
 */
@ManagedBean (name="debitoBean")
@ViewScoped
public class DebitoBean implements Serializable{
    
    @ManagedProperty("#{debitoRN}")
    private DebitoRN debitoRN;
    private Debito debito = new Debito();
    private List<Debito> debitos;
    
    @PostConstruct
    private void init(){
        setDebitos(getDebitoRN().listando());
    }
    public void salvar(ActionEvent event){
        getDebitoRN().salvar(getDebito());
        setDebito(new Debito());
    }
    public Debito getDebito() {
        return debito;
    }

    public void setDebito(Debito debito) {
        this.debito = debito;
    }

    public DebitoRN getDebitoRN() {
        return debitoRN;
    }

    public void setDebitoRN(DebitoRN debitoRN) {
        this.debitoRN = debitoRN;
    }

    public List<Debito> getDebitos() {
        return debitos;
    }

    public void setDebitos(List<Debito> debitos) {
        this.debitos = debitos;
    }
}
