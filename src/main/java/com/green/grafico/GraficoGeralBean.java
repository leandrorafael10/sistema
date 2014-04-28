/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.grafico;

import com.green.modelo.Funcionario;
import com.green.rn.CapaLoteJornalRN;
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
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "graficoGeralBean")
@ViewScoped
public class GraficoGeralBean implements Serializable {

    @ManagedProperty("#{capaLoteJornalRN}")
    private CapaLoteJornalRN capaLoteJornalRN;
    private CartesianChartModel modeloBarra;
    private CartesianChartModel modeloLinear;
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
        modeloLinear = new CartesianChartModel();
        List faturamentoMensal = getCapaLoteJornalRN().faturamentoGeral();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Faturado");
        for (Object object : faturamentoMensal) {
            Map row = (Map) object;
            BigDecimal soma = (BigDecimal) row.get("1");
            int ano = (int) row.get("2");
            int mes = (int) row.get("3");
            series1.set(String.valueOf(mes) + "/" + String.valueOf(ano), soma.floatValue());
        }

        modeloLinear.addSeries(series1);

    }

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
