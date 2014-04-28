/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Equipevenda;
import com.green.modelo.Funcionario;
import com.green.modelo.Funcionariometa;
import com.green.rn.CapaLoteJornalRN;
import com.green.rn.EquipevendaRN;
import java.io.Serializable;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "equipevendaBean")
@ViewScoped
public class EquipevendaBean implements Serializable {

    @ManagedProperty("#{equipevendaRN}")
    private EquipevendaRN equipevendaRN;
    @ManagedProperty("#{capaLoteJornalRN}")
    private CapaLoteJornalRN capaLoteJornalRN;
    private Equipevenda equipevenda;

    @PostConstruct
    private void init() {
        this.equipevenda = new Equipevenda();
        this.equipevenda.setIDGerente(new Funcionario());
        this.equipevenda.setIDPromotor(new Funcionario());


    }

    public boolean ultimaMeta(Equipevenda e) {
        Calendar c = Calendar.getInstance();
        if (e.getIdequipevenda() != null) {
            for (Funcionariometa f : e.getFuncionariometaList()) {
                if (f.getMes() == (c.get(Calendar.MONTH) + 1) && f.getAno() == (c.get(Calendar.YEAR))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void salvar(ActionEvent event) {
        getEquipevendaRN().salvar(getEquipevenda());
    }

    public void atualizar(ActionEvent event) {
        getEquipevendaRN().atualizar(getEquipevenda());
    }

    public CapaLoteJornalRN getCapaLoteJornalRN() {
        return capaLoteJornalRN;
    }

    public void setCapaLoteJornalRN(CapaLoteJornalRN capaLoteJornalRN) {
        this.capaLoteJornalRN = capaLoteJornalRN;
    }

    public EquipevendaRN getEquipevendaRN() {
        return equipevendaRN;
    }

    public void setEquipevendaRN(EquipevendaRN equipevendaRN) {
        this.equipevendaRN = equipevendaRN;
    }

    public Equipevenda getEquipevenda() {
        return equipevenda;
    }

    public void setEquipevenda(Equipevenda equipevenda) {
        this.equipevenda = equipevenda;
    }
}
