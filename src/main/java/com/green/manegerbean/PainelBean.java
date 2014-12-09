package com.green.manegerbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.green.modelo.Painel;
import com.green.modelo.Praca;
import com.green.rn.PainelRN;

@ManagedBean(name="painelBean")
@ViewScoped
public class PainelBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Painel painel;
	@ManagedProperty("#{painelRN}")
	private PainelRN painelRN;
	private List<Painel> listaPaineis;
	private List<Painel> listaPainelDisponivel;
	
	@PostConstruct
	private void init(){
		this.listaPaineis = new ArrayList<>();
		this.painel = new Painel();
		this.painel.setIDPraca(new Praca());
	}
	
	public void salvar(ActionEvent event){
		if(getPainelRN().salvar(getPainel())){
			init();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cadastrado com sucesso!","Cadastrado com sucesso!"));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Falha ao cadastrar!","Falha ao cadastrar!"));
		}
		
	}
	public void atualizar(ActionEvent event){
		if(getPainelRN().atualizar(getPainel())){
			init();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Atualizado com sucesso!","Atualizado com sucesso!"));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Falha ao atualizar!","Falha ao atualizar!"));
		}
	}

	public Painel getPainel() {
		return painel;
	}

	public void setPainel(Painel painel) {
		this.painel = painel;
	}

	public PainelRN getPainelRN() {
		return painelRN;
	}

	public void setPainelRN(PainelRN painelRN) {
		this.painelRN = painelRN;
	}

	public List<Painel> getListaPaineis() {
		this.listaPaineis = getPainelRN().listar();
		return listaPaineis;
	}

	public void setListaPaineis(List<Painel> listaPaineis) {
		this.listaPaineis = listaPaineis;
	}

	public List<Painel> getListaPainelDisponivel() {
		this.listaPainelDisponivel = getPainelRN().listaPainelDisponivel();
		return listaPainelDisponivel;
	}

	public void setListaPainelDisponivel(List<Painel> listaPainelDisponivel) {
		this.listaPainelDisponivel = listaPainelDisponivel;
	}
	
	

}
