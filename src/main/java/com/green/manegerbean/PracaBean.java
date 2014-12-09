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
import javax.faces.bean.RequestScoped;

import com.green.modelo.Praca;
import com.green.rn.PracaRN;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "pracaBean")
@RequestScoped
public class PracaBean implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{pracaRN}")
    private PracaRN pracaRN;
   
    private Praca praca;
    private List<Praca> pracas;
    

   
    
    public Praca getPraca() {
        return praca;
    }

    public void setPraca(Praca praca) {
        this.praca = praca;
    }
   
    
    @PostConstruct
    private void init(){
      setPracas(getPracaRN().listar());
     
    }

    public List<Praca> getPracas() {
        return pracas;
    }

    public void setPracas(List<Praca> pracas) {
        this.pracas = pracas;
    }
    
    public PracaRN getPracaRN() {
        return pracaRN;
    }

    public void setPracaRN(PracaRN pracaRN) {
        this.pracaRN = pracaRN;
    }
   
}
