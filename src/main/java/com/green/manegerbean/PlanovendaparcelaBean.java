/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Planovendaparcela;
import com.green.rn.PlanovendaparcelaRN;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="planovendaparcelaBean")
@ViewScoped
public class PlanovendaparcelaBean implements Serializable{
    
    @ManagedProperty("#{planovendaparcelaRN}")
    private PlanovendaparcelaRN planovendaparcelaRN;
    private Planovendaparcela planovendaparcela = new Planovendaparcela();


    public void salvar(ActionEvent event){
        getPlanovendaparcelaRN().salvar(getPlanovendaparcela());
    }
    public void onEdit(RowEditEvent event) {
        Planovendaparcela pl = (Planovendaparcela) event.getObject();
        getPlanovendaparcelaRN().atualizar(pl);

    }

    public void onCancel(RowEditEvent event) {
        Planovendaparcela pl = (Planovendaparcela) event.getObject();
    }
    
    
    public Planovendaparcela getPlanovendaparcela() {
        return planovendaparcela;
    }

    public void setPlanovendaparcela(Planovendaparcela planovendaparcela) {
        this.planovendaparcela = planovendaparcela;
    }
    
    public PlanovendaparcelaRN getPlanovendaparcelaRN() {
        return planovendaparcelaRN;
    }

    public void setPlanovendaparcelaRN(PlanovendaparcelaRN planovendaparcelaRN) {
        this.planovendaparcelaRN = planovendaparcelaRN;
    }
    
    
}
