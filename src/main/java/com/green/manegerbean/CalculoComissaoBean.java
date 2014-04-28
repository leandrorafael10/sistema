/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.CalculoComissao;
import com.green.rn.CalculoComissaoRN;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "calculoComissaoBean")
@ViewScoped
public class CalculoComissaoBean implements Serializable{
    
    private CalculoComissao calculoComissao =new CalculoComissao();
    @ManagedProperty("#{calculoComissaoRN}")
    private CalculoComissaoRN calculoComissaoRN;

    public void salvar(ActionEvent event){
        getCalculoComissaoRN().salvar(getCalculoComissao());
        
    }
    public CalculoComissao getCalculoComissao() {
        return calculoComissao;
    }

    public void setCalculoComissao(CalculoComissao calculoComissao) {
        this.calculoComissao = calculoComissao;
    }

    public CalculoComissaoRN getCalculoComissaoRN() {
        return calculoComissaoRN;
    }

    public void setCalculoComissaoRN(CalculoComissaoRN calculoComissaoRN) {
        this.calculoComissaoRN = calculoComissaoRN;
    }
    
    
    
}
