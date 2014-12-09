/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Tipoconta;
import com.green.rn.TipoContaRN;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="tipoContaBean")
@ViewScoped
public class TipoContaBean implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8288095050439553578L;

	@ManagedProperty("#{tipoContaRN}")
    private TipoContaRN tipoContaRN;
    
    private Tipoconta tipoconta = new Tipoconta();
    private List<Tipoconta> tipocontas;
    
    @PostConstruct
    private void init (){
        setTipocontas(getTipoContaRN().listar());
    }
    
    public void listando(){
        setTipocontas(getTipoContaRN().listar());
    }
    public List<Tipoconta> getTipocontas() {
        return tipocontas;
    }
    
    public void salvar(ActionEvent actionEvent){
        getTipoContaRN().salvar(getTipoconta());
        setTipoconta(new Tipoconta());
    }
    public void excluir(ActionEvent actionEvent){
        getTipoContaRN().excluir(getTipoconta());
        setTipoconta(new Tipoconta());
    }
    public  void atualizar(RowEditEvent editEvent){
        Tipoconta t = (Tipoconta)editEvent.getObject();
        getTipoContaRN().atualizar(t);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Atualizado com sucesso!"));
    }
    public void setTipocontas(List<Tipoconta> tipocontas) {
        this.tipocontas = tipocontas;
    }

    public Tipoconta getTipoconta() {
        return tipoconta;
    }

    public void setTipoconta(Tipoconta tipoconta) {
        this.tipoconta = tipoconta;
    }
    
    public TipoContaRN getTipoContaRN() {
        return tipoContaRN;
    }

    public void setTipoContaRN(TipoContaRN tipoContaRN) {
        this.tipoContaRN = tipoContaRN;
    }
    
    
}
