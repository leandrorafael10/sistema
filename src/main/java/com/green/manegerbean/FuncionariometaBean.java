/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.green.modelo.Equipevenda;
import com.green.modelo.Funcionariometa;
import com.green.rn.FuncionariometaRN;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "funcionariometaBean")
@ViewScoped
public class FuncionariometaBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{funcionariometaRN}")
    private FuncionariometaRN funcionariometaRN;
    private Funcionariometa funcionariometa;
    private CartesianChartModel linearModel;

    @PostConstruct
    private void init() {
        this.funcionariometa = new Funcionariometa();
        this.funcionariometa.setIDEquipevenda(new Equipevenda());
        linearModel = new CartesianChartModel();

    }

    public void atualizar(Funcionariometa f) {
        getFuncionariometaRN().atualizar(f);
    }

    public void salvar(Equipevenda e) {
        getFuncionariometaRN().salvarNova(getFuncionariometa(), e);
        setFuncionariometa(new Funcionariometa());
    }

    public void createLinearModel() {
        linearModel = new CartesianChartModel();
        String nome = "";
        LineChartSeries series1 = new LineChartSeries();
        if (getFuncionariometa().getIdFuncionarioMeta() != null) {
            for (Funcionariometa f : getFuncionariometa().getIDEquipevenda().getFuncionariometaList()) {
                nome =primeiraPalavra(f.getIDEquipevenda().getIDPromotor().getIDPessoa().getRazao()); 
                series1.set(String.valueOf(f.getMes()), (int) f.getMeta().intValue());
            }
            series1.setLabel(nome);
            linearModel.addSeries(series1);
        }
    }

    public String primeiraPalavra(String frase) {
        String palavra = "";
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) != ' ') {
                palavra = palavra + frase.charAt(i);
            } else {
                break;
            }
        }
        return palavra;
    }

    public void grafico() {
        linearModel = new CartesianChartModel();
        if (getFuncionariometa().getIDEquipevenda() != null) {
            LineChartSeries meta = new LineChartSeries();
            String nome = "";

            for (Funcionariometa f : getFuncionariometa().getIDEquipevenda().getFuncionariometaList()) {
                nome = f.getIDEquipevenda().getIDPromotor().getIDPessoa().getRazao();

                meta.set(String.valueOf(f.getMes()), (int) f.getMeta().intValue());
            }
            meta.setLabel(nome);
            linearModel.addSeries(meta);
        }
    }

    public void onEdit(RowEditEvent event) {
        Funcionariometa f = (Funcionariometa) event.getObject();
        atualizar(f);

    }

    public void onCancel(RowEditEvent event) {
       
    }

    public CartesianChartModel getLinearModel() {
        return linearModel;
    }

    public void setLinearModel(CartesianChartModel linearModel) {
        this.linearModel = linearModel;
    }

    public FuncionariometaRN getFuncionariometaRN() {
        return funcionariometaRN;
    }

    public void setFuncionariometaRN(FuncionariometaRN funcionariometaRN) {
        this.funcionariometaRN = funcionariometaRN;
    }

    public Funcionariometa getFuncionariometa() {
        return funcionariometa;
    }

    public void setFuncionariometa(Funcionariometa funcionariometa) {
        this.funcionariometa = funcionariometa;
    }
}
