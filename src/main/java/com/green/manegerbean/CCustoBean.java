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

import org.primefaces.event.RowEditEvent;

import com.green.modelo.Ccusto;
import com.green.rn.CCustoRN;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "ccustoBean")
@ViewScoped
public class CCustoBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{ccustoRN}")
    private CCustoRN cCustoRN;
    private Ccusto ccusto;

    @PostConstruct
    private void init() {
        this.ccusto = new Ccusto();
    }

    public void salvar(ActionEvent event) {
        getcCustoRN().salvar(getCcusto());
        setCcusto(new Ccusto());
    }

    public void atualizar(ActionEvent event) {
        getcCustoRN().atualizar(getCcusto());
    }
    public void atualizarLinha(RowEditEvent editEvent){
        getcCustoRN().atualizar((Ccusto)editEvent.getObject());
    }

    public void excluir() {
        getcCustoRN().excluir(getCcusto());
    }

    public Ccusto getCcusto() {
        return ccusto;
    }

    public void setCcusto(Ccusto ccusto) {
        this.ccusto = ccusto;
    }

    public CCustoRN getcCustoRN() {
        return cCustoRN;
    }

    public void setcCustoRN(CCustoRN cCustoRN) {
        this.cCustoRN = cCustoRN;
    }

}
