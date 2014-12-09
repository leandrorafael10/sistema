package com.green.manegerbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.green.modelo.Debito;
import com.green.modelo.Despesa;
import com.green.modelo.Despesadebito;
import com.green.rn.DespesaDebitoRN;

@ManagedBean(name = "despesaDebitoBean")
@ViewScoped
public class DespesaDebitoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{despesaDebitoRN}")
	private DespesaDebitoRN despesaDebitoRN;
	private Despesadebito despesadebito;
	private List<Despesadebito> despesadebitos;
	private Date dTInicio;
	private Date dTFim;

	@PostConstruct
	private void init() {
		this.despesadebito = new Despesadebito();
		this.despesadebito.setIDDespesa(new Despesa());
		this.despesadebito.setIDDebito(new Debito());
	}

	public void listarPeriodo(ActionEvent event) {
		setDespesadebitos(getDespesaDebitoRN().listaPeriodo(getdTInicio(), getdTFim()));
	}
	public void estornarDebito(ActionEvent event){
		getDespesaDebitoRN().estornarDebito(getDespesadebito());
		setDespesadebitos(getDespesaDebitoRN().listaPeriodo(getdTInicio(), getdTFim()));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Estornado com sucesso!","Estornado com sucesso!"));
	}

	public void salvar(ActionEvent event) {
		getDespesaDebitoRN().salvar(getDespesadebito());
	}

	public DespesaDebitoRN getDespesaDebitoRN() {
		return despesaDebitoRN;
	}

	public void setDespesaDebitoRN(DespesaDebitoRN despesaDebitoRN) {
		this.despesaDebitoRN = despesaDebitoRN;
	}

	public Despesadebito getDespesadebito() {
		return despesadebito;
	}

	public void setDespesadebito(Despesadebito despesadebito) {
		this.despesadebito = despesadebito;
	}

	public Date getdTInicio() {
		return dTInicio;
	}

	public void setdTInicio(Date dTInicio) {
		this.dTInicio = dTInicio;
	}

	public Date getdTFim() {
		return dTFim;
	}

	public void setdTFim(Date dTFim) {
		this.dTFim = dTFim;
	}

	public List<Despesadebito> getDespesadebitos() {
		return despesadebitos;
	}

	public void setDespesadebitos(List<Despesadebito> despesadebitos) {
		this.despesadebitos = despesadebitos;
	}

}
