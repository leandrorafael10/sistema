/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.rn.BoletoRN;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author leandro.silva
 *
 */
@ManagedBean(name = "boletoBean")
@ViewScoped
public class BoletoBean {
    
    @ManagedProperty("#{boletoRN}")
    private BoletoRN boletoRN;

    public BoletoRN getBoletoRN() {
        return boletoRN;
    }

    public void setBoletoRN(BoletoRN boletoRN) {
        this.boletoRN = boletoRN;
    }
    
    

}
