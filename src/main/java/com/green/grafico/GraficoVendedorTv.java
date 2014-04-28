/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.grafico;

import com.green.modelo.Capalotejornal;
import com.green.modelo.Equipevenda;
import com.green.modelo.Funcionario;
import com.green.modelo.Funcionariometa;
import com.green.rn.CapaLoteJornalRN;
import com.green.rn.ContratoMidiaRN;
import com.green.rn.EquipevendaRN;
import com.green.rn.FuncaoRN;
import com.green.rn.FuncionarioRN;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.spi.Contextual;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "graficoVendedorTv")
@ViewScoped
public class GraficoVendedorTv implements Serializable {

    @ManagedProperty("#{contratoMidiaRN}")
    private ContratoMidiaRN contratoMidiaRN;
    @ManagedProperty("#{funcaoRN}")
    private FuncaoRN funcaoRN;
    @ManagedProperty("#{funcionarioRN}")
    private FuncionarioRN funcionarioRN;
    private CartesianChartModel linearModel;
    private CartesianChartModel linearModelFat;
    @ManagedProperty("#{equipevendaRN}")
    private EquipevendaRN equipevendaRN;
    @ManagedProperty("#{capaLoteJornalRN}")
    private CapaLoteJornalRN capaLoteJornalRN;
    Double media = new Double("358.80");

    @PostConstruct
    public void init() {
        createLinearModel();
    }

    private void createLinearModel() {
        linearModel = new CartesianChartModel();
        linearModelFat = new CartesianChartModel();

        List obj = getContratoMidiaRN().listaVemd();

        List<Funcionario> vendedores = getFuncionarioRN().listarGerenteVendedor();
        for (Funcionario f : vendedores) {
            LineChartSeries series1 = new LineChartSeries();
            series1.setLabel(primeiraPalavra(f.getIDPessoa().getRazao()));

            for (Object object : obj) {
                Map row = (Map) object;
                Funcionario f1 = (Funcionario) row.get("0");
                BigDecimal valor = (BigDecimal) row.get("1");
                Date data = (Date) row.get("2");
                if (f.equals(f1)) {
                    series1.set((data.getMonth() + 1) + "/" + (1900 + data.getYear()), valor.intValue());
                }
            }
            linearModel.addSeries(series1);
            linearModelFat.addSeries(series1);
        }

    }

    /*
     * Funçao par a criar grafico com vendas1  e meta de vendas1 ,vendas1 faturadas e vendas de faturamento por promotor
     * o linearModel e referente as vendas1
     * o linearModelFat e referente ao faturamento
     */
    public void graficoVendedorMeta(Equipevenda equipevenda) {
        RequestContext context = RequestContext.getCurrentInstance();
        linearModel = new CartesianChartModel();
        linearModelFat = new CartesianChartModel();

        List<Vendas> vend = new ArrayList<>();
        List<Vendas> vendaFat = new ArrayList<>();
        List<Vendas> meta = new ArrayList<>();
        List<Vendas> metaFat = new ArrayList<>();
        if (equipevenda.getIdequipevenda() != null) {

            List listaVendas = getCapaLoteJornalRN().vendasEquipe(equipevenda);

            ChartSeries vendas = new ChartSeries();
            vendas.setLabel("Venda");
            ChartSeries vendasFat = new ChartSeries();
            vendasFat.setLabel("Venda Faturada");
            if (equipevenda.getIdequipevenda() != null) {
                
                for (Funcionariometa f : equipevenda.getFuncionariometaList()) {
                    String periodo = String.valueOf(f.getMes()) + "/" + String.valueOf(f.getAno());
                    metaFat.add(new Vendas(periodo, new BigDecimal(media).multiply(new BigDecimal(f.getMeta())).setScale(2, RoundingMode.UP)));
                    meta.add(new Vendas(periodo, (int) f.getMeta().intValue()));
                    boolean i = false;
                    for (Object object : listaVendas) {
                        Map row = (Map) object;
                        Long venda = (Long) row.get("0");
                        int mes = (int) row.get("1");
                        int ano = (int) row.get("2");
                        BigDecimal soma = (BigDecimal) row.get("3");
                        Double mediaResp = (Double) row.get("4");
                        if(mediaResp.compareTo(new Double("358.0"))>=0){
                            media =mediaResp;
                        }else{
                            media = new Double("358.0");
                        }
                         
                        if (mes == f.getMes() && ano == f.getAno()) {
                            vend.add(new Vendas(periodo, venda.intValue()));
                            vendaFat.add(new Vendas(periodo, soma));
                            i = true;
                        }
                    }
                    if (!i) {
                        vend.add(new Vendas(periodo, 0));
                        vendaFat.add(new Vendas(periodo, BigDecimal.ZERO));
                    }


                }
            }
            ChartSeries metas = new ChartSeries();
            metas.setLabel("Meta");
            ChartSeries metasFat = new ChartSeries();
            metasFat.setLabel("Meta Faturamento");
            if (vend.isEmpty() && meta.isEmpty()) {
                vendas.set("sem", 0);
                metas.set("sem", 0);
            } else {
                for (Vendas v : vend) {
                    Number number = new Integer(v.getTotal());
                    vendas.set(v.getPeriodo(), number);
                }

                for (Vendas v : meta) {
                    Number number = new Integer(v.getTotal());
                    metas.set(v.getPeriodo(), number);
                }
            }

            if (vendaFat.isEmpty() && metaFat.isEmpty()) {
                vendasFat.set("sem", 0);
                metasFat.set("sem", 0);
            } else {
                for (Vendas v : vendaFat) {
                    vendasFat.set(v.getPeriodo(), v.getValor());
                }
                for (Vendas v : metaFat) {
                    metasFat.set(v.getPeriodo(), v.getValor());
                }
            }

            linearModelFat.addSeries(metasFat);
            linearModelFat.addSeries(vendasFat);
            linearModel.addSeries(vendas);
            linearModel.addSeries(metas);
        }

        context.execute("dialogNovaMeta.show()");
        context.update("formNovaMeta");

    }

    private class Vendas {

        private String periodo;
        private int total;
        private BigDecimal valor;

        public Vendas(String per, int tot) {
            periodo = per;
            total = tot;
        }

        public Vendas(String per, BigDecimal val) {
            periodo = per;
            valor = val;
        }

        public String getPeriodo() {
            return periodo;
        }

        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
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

    public ContratoMidiaRN getContratoMidiaRN() {
        return contratoMidiaRN;
    }

    public EquipevendaRN getEquipevendaRN() {
        return equipevendaRN;
    }

    public void setEquipevendaRN(EquipevendaRN equipevendaRN) {
        this.equipevendaRN = equipevendaRN;
    }

    public CapaLoteJornalRN getCapaLoteJornalRN() {
        return capaLoteJornalRN;
    }

    public void setCapaLoteJornalRN(CapaLoteJornalRN capaLoteJornalRN) {
        this.capaLoteJornalRN = capaLoteJornalRN;
    }

    public void setContratoMidiaRN(ContratoMidiaRN contratoMidiaRN) {
        this.contratoMidiaRN = contratoMidiaRN;
    }

    public CartesianChartModel getLinearModel() {
        return linearModel;
    }

    public void setLinearModel(CartesianChartModel linearModel) {
        this.linearModel = linearModel;
    }

    public CartesianChartModel getLinearModelFat() {
        return linearModelFat;
    }

    public void setLinearModelFat(CartesianChartModel linearModelFat) {
        this.linearModelFat = linearModelFat;
    }

    public FuncaoRN getFuncaoRN() {
        return funcaoRN;
    }

    public void setFuncaoRN(FuncaoRN funcaoRN) {
        this.funcaoRN = funcaoRN;
    }

    public FuncionarioRN getFuncionarioRN() {
        return funcionarioRN;
    }

    public void setFuncionarioRN(FuncionarioRN funcionarioRN) {
        this.funcionarioRN = funcionarioRN;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }
    
}
