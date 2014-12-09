/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.green.modelo.Tipopagamento;
import com.green.rn.TipoPagamentoRN;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="tipoPagamentoBean")
@ViewScoped
public class TipoPagamentoBean implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{tipoPagamentoRN}")
    private TipoPagamentoRN tipoPagamentoRN;
    private Tipopagamento tipopagamento ;
    private List<Tipopagamento> tipoPagamentos;
    
    @PostConstruct
    private void init(){
        setTipoPagamentos(getTipoPagamentoRN().listar());
    }

    public TipoPagamentoRN getTipoPagamentoRN() {
        return tipoPagamentoRN;
    }

    public void setTipoPagamentoRN(TipoPagamentoRN tipoPagamentoRN) {
        this.tipoPagamentoRN = tipoPagamentoRN;
    }

    public List<Tipopagamento> getTipoPagamentos() {
        return tipoPagamentos;
    }

    public void setTipoPagamentos(List<Tipopagamento> tipoPagamentos) {
        this.tipoPagamentos = tipoPagamentos;
    }

    public Tipopagamento getTipopagamento() {
        return tipopagamento;
    }

    public void setTipopagamento(Tipopagamento tipopagamento) {
        this.tipopagamento = tipopagamento;
    }
    
}
