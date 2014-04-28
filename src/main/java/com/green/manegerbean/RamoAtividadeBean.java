/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Ramoatividade;
import com.green.rn.RamoAtividadeRN;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="ramoAtividadeBean")
@RequestScoped
public class RamoAtividadeBean {
   
    @ManagedProperty("#{ramoAtividadeRN}")
    private RamoAtividadeRN ramoAtividadeRN;
    private Ramoatividade ramoatividadeNovo;
    private List<Ramoatividade> ramoatividades;

    @PostConstruct
    public void init(){
        setRamoatividades(getRamoAtividadeRN().listar());
    }
    public RamoAtividadeRN getRamoAtividadeRN() {
        return ramoAtividadeRN;
    }

    public void setRamoAtividadeRN(RamoAtividadeRN ramoAtividadeRN) {
        this.ramoAtividadeRN = ramoAtividadeRN;
    }

    public Ramoatividade getRamoatividadeNovo() {
        return ramoatividadeNovo;
    }

    public void setRamoatividadeNovo(Ramoatividade ramoatividadeNovo) {
        this.ramoatividadeNovo = ramoatividadeNovo;
    }

    public List<Ramoatividade> getRamoatividades() {
        return ramoatividades;
    }

    public void setRamoatividades(List<Ramoatividade> ramoatividades) {
        this.ramoatividades = ramoatividades;
    }
    
    
}
