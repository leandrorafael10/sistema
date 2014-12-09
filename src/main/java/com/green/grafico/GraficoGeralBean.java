/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.grafico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.green.modelo.Funcionario;
import com.green.rn.CapaLoteJornalRN;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "graficoGeralBean")
@ViewScoped
@SuppressWarnings("rawtypes")
public class GraficoGeralBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{capaLoteJornalRN}")
    private CapaLoteJornalRN capaLoteJornalRN;
    private CartesianChartModel modeloBarra;
    private LineChartModel modeloLinear;
    private PieChartModel modeloPizza;
    private PieChartModel modeloPizzaVendas;

    @PostConstruct
    private void init() {
        createPieModel();
        createLinearModel();
        createCategoriaBarra();
        createPizzaVendaModel();
    }

    
	private void createPieModel() {
        modeloPizza = new PieChartModel();
        List faturamentoPorEquipe = getCapaLoteJornalRN().faturamentoPorEquipe();
        for (Object object : faturamentoPorEquipe) {
            Map row = (Map)object;
            Funcionario nome  = (Funcionario)row.get("0");
            Long valor  = (Long)row.get("2");
            
            modeloPizza.set(primeiraPalavra(nome.getIDPessoa().getRazao()),valor);
        }
    }

    private void createPizzaVendaModel() {
        modeloPizzaVendas = new PieChartModel();
        List faturamentoPorEquipe = getCapaLoteJornalRN().situacaoVendas();
        for (Object object : faturamentoPorEquipe) {
            Map row = (Map)object;
            Long soma  = (Long)row.get("0");
            String status  = (String)row.get("1");
            modeloPizzaVendas.set(status,soma);
        }
    }

    private void createLinearModel() {
        
        List faturamentoMensal = getCapaLoteJornalRN().faturamentoGeral();
        modeloLinear = new LineChartModel();
        ChartSeries series1 = new ChartSeries();
        series1.setLabel("Faturado");
        for (Object object : faturamentoMensal) {
            Map row = (Map) object;
            BigDecimal soma = (BigDecimal) row.get("1");
            int ano = (int) row.get("2");
            int mes = (int) row.get("3");
            series1.set(String.valueOf(mes) + "/" + String.valueOf(ano), soma.intValue());
        }
        modeloLinear.setShowPointLabels(true);
        modeloLinear.addSeries(series1);
        
        

    }

    @SuppressWarnings("deprecation")
	private void createCategoriaBarra() {
        modeloBarra = new CartesianChartModel();
        List faturamentoPorEquipe = getCapaLoteJornalRN().faturamentoPorEquipe();
        Calendar calendar = Calendar.getInstance();
        for (Object object : faturamentoPorEquipe) {
            ChartSeries equipe = new ChartSeries();
            Map row = (Map)object;
            Funcionario nome  = (Funcionario)row.get("0");
            BigDecimal valor  = (BigDecimal)row.get("1");
            equipe.setLabel(primeiraPalavra(nome.getIDPessoa().getRazao()));
            equipe.set(calendar.getTime().getDate()+"/"+(calendar.getTime().getMonth()+1)+"/"+(calendar.getTime().getYear()+1900), valor);
            modeloBarra.addSeries(equipe);
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

    public PieChartModel getModeloPizza() {
        return modeloPizza;
    }

    public CartesianChartModel getModeloLinear() {
        return modeloLinear;
    }

    public CartesianChartModel getModeloBarra() {
        return modeloBarra;
    }

    public PieChartModel getModeloPizzaVendas() {
        return modeloPizzaVendas;
    }

    public CapaLoteJornalRN getCapaLoteJornalRN() {
        return capaLoteJornalRN;
    }

    public void setCapaLoteJornalRN(CapaLoteJornalRN capaLoteJornalRN) {
        this.capaLoteJornalRN = capaLoteJornalRN;
    }
}
