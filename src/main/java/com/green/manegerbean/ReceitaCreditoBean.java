/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.green.modelo.Receitacredito;
import com.green.rn.ReceitaCreditoRN;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "receitaCreditoBean")
@ViewScoped
public class ReceitaCreditoBean implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Receitacredito receitacredito;
    @ManagedProperty("#{receitaCreditoRN}")
    private ReceitaCreditoRN receitaCreditoRN;
    private List<Receitacredito> receitacreditos;
    private Date inicio;
    private Date fim;

    @PostConstruct
    private void init() {
        this.receitacredito = new Receitacredito();
        this.receitacreditos = new ArrayList<>();
    }
    
    public void listarFiltroData(ActionEvent event){
        setReceitacreditos(getReceitaCreditoRN().listaCreditoPorPeriodo(getInicio(), getFim()));
    }
    
    public void estorno(ActionEvent event){
        getReceitaCreditoRN().excluir(getReceitacredito());
        listarFiltroData(event);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estornado com sucesso!", "Estornado com sucesso!"));
    }
    

    public Receitacredito getReceitacredito() {
        return receitacredito;
    }

    public void setReceitacredito(Receitacredito receitacredito) {
        this.receitacredito = receitacredito;
    }

    public ReceitaCreditoRN getReceitaCreditoRN() {
        return receitaCreditoRN;
    }

    public void setReceitaCreditoRN(ReceitaCreditoRN receitaCreditoRN) {
        this.receitaCreditoRN = receitaCreditoRN;
    }

    public List<Receitacredito> getReceitacreditos() {
        return receitacreditos;
    }

    public void setReceitacreditos(List<Receitacredito> receitacreditos) {
        this.receitacreditos = receitacreditos;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    
}
