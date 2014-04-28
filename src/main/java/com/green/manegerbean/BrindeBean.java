/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Brinde;
import com.green.rn.BrindeRN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "brindeBean")
@ViewScoped
public class BrindeBean implements Serializable {

    @ManagedProperty("#{brindeRN}")
    private BrindeRN brindeRN;
    private Brinde brinde;
    private List<Brinde> brindes = new ArrayList<>();

    @PostConstruct
    private void init() {
        this.brinde = new Brinde();
        this.brindes = getBrindeRN().listar();
    }

    public void salvar(ActionEvent actionEvent) {
        getBrindeRN().salvar(brinde);
        init();
    }

    public void onEdit(RowEditEvent event) {
        Brinde b = (Brinde) event.getObject();
        getBrindeRN().atualizar(b);

    }

    public void onCancel(RowEditEvent event) {
        Brinde f = (Brinde) event.getObject();
    }

    public BrindeRN getBrindeRN() {
        return brindeRN;
    }

    public void setBrindeRN(BrindeRN brindeRN) {
        this.brindeRN = brindeRN;
    }

    public Brinde getBrinde() {
        return brinde;
    }

    public void setBrinde(Brinde brinde) {
        this.brinde = brinde;
    }

    public List<Brinde> getBrindes() {
        return brindes;
    }

    public void setBrindes(List<Brinde> brindes) {
        this.brindes = brindes;
    }
}
