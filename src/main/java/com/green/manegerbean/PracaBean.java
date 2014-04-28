/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Praca;
import com.green.rn.PracaRN;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "pracaBean")
@RequestScoped
public class PracaBean implements Serializable{
    
    @ManagedProperty("#{pracaRN}")
    private PracaRN pracaRN;
   
    private Praca praca;
    private List<String> listaPracas;

    public List<String> getlistaPracas() {
        return listaPracas;
    }

    public void setlistaPracas(List<String> plistaPracas) {
        this.listaPracas = plistaPracas;
    }
    
    public Praca getPraca() {
        return praca;
    }

    public void setPraca(Praca praca) {
        this.praca = praca;
    }
    private List<Praca> pracas;
    
    @PostConstruct
    private void init(){
      setPracas(getPracaRN().listar());
      setlistaPracas(getPracaRN().listarPraca());
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
    public  List<SelectItem> getlistandoPracas(){
        List<SelectItem> itens = new LinkedList<SelectItem>();
        for(Praca teste : getPracas()){
           SelectItem iten  = new SelectItem(teste.getDescricao());
           itens.add(iten);
        }
        return itens;
    }
}
