/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Credito;
import com.green.rn.CreditoRN;
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
@ManagedBean(name="creditoBean")
@ViewScoped
public class CreditoBean implements Serializable{
    
    @ManagedProperty("#{creditoRN}")
    private CreditoRN creditoRN;
    private Credito credito  = new Credito();
    private List<Credito> creditos;

    @PostConstruct
    private void init(){
        setCreditos(getCreditoRN().listando());
    }
    
    
    public void salvar(ActionEvent actionEvent){
        getCreditoRN().salvar(getCredito());
        setCredito(new Credito());
    }
    
    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public CreditoRN getCreditoRN() {
        return creditoRN;
    }

    public void setCreditoRN(CreditoRN creditoRN) {
        this.creditoRN = creditoRN;
    }

    public List<Credito> getCreditos() {
        return creditos;
    }

    public void setCreditos(List<Credito> creditos) {
        this.creditos = creditos;
    }
    
    
}
