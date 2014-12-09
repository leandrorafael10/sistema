/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.green.modelo.Brinde;
import com.green.modelo.BrindeEntrada;
import com.green.rn.BrindeEntradaRN;
import com.green.rn.BrindeRN;
import com.green.util.ContadorPontoBrinde;
import com.green.util.ContextoUtil;
import com.green.util.RelatorioUtil;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "brindeBean")
@ViewScoped
public class BrindeBean implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{brindeRN}")
    private BrindeRN brindeRN;
    @ManagedProperty("#{brindeEntradaRN}")
    private BrindeEntradaRN brindeEntradaRN;
    private BrindeEntrada brindeEntrada;
    private Brinde brinde;
    private List<Brinde> brindes = new ArrayList<>();
    private List<ContadorPontoBrinde> contadorBrindes;
    private List<BrindeEntrada> entradas;
    private Date inicio;
    private Date fim;
    private int quantidade;
    
    @PostConstruct
    private void init() {
        this.brinde = new Brinde();
        this.brindes = getBrindeRN().listar();
        this.quantidade = 0;
        this.brindeEntrada = new BrindeEntrada();
    }
    
    public void atualizarSaldo(ActionEvent event) {
        getBrindeEntradaRN().entradaBrinde(getBrindeEntrada());
        
    }
    
    @SuppressWarnings("rawtypes")
	public void listarPeriodo(ActionEvent event) {
        List lista = getBrindeRN().listarPeriodo(inicio, fim);
        this.contadorBrindes = new ArrayList<>();
        for (Object object : lista) {
            Map row = (Map) object;
            this.contadorBrindes.add(new ContadorPontoBrinde(String.valueOf(row.get("0")), String.valueOf(row.get("1")),
                    (Long) row.get("2"), null));
        }
    }
    
    public void listarEntradasDeBrinde(Brinde b){
        setEntradas(getBrindeEntradaRN().listarPorBrinde(b));
        RequestContext.getCurrentInstance().update("formHistorico");
        RequestContext.getCurrentInstance().execute("PF('dialogHistorico').show()");
    }
    
    public void geraPDF() {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("data_inicio", inicio);
        parametros.put("data_fim", fim);
        parametros.put("usuario", ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario().getIDPessoa().getRazao());
        JRDataSource jrds = new JRBeanCollectionDataSource(contadorBrindes);
        RelatorioUtil.geraRelatorioBean("vendas_brinde", jrds, parametros);
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
    
    public Date getInicio() {
        return inicio;
    }
    
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }
    
    public Date getFim() {
        return fim;
    }
    
    public void setFim(Date fim) {
        this.fim = fim;
    }
    
    public List<ContadorPontoBrinde> getContadorBrindes() {
        return contadorBrindes;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public BrindeEntradaRN getBrindeEntradaRN() {
        return brindeEntradaRN;
    }
    
    public void setBrindeEntradaRN(BrindeEntradaRN brindeEntradaRN) {
        this.brindeEntradaRN = brindeEntradaRN;
    }
    
    public BrindeEntrada getBrindeEntrada() {
        return brindeEntrada;
    }
    
    public void setBrindeEntrada(BrindeEntrada brindeEntrada) {
        this.brindeEntrada = brindeEntrada;
    }

    public List<BrindeEntrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<BrindeEntrada> entradas) {
        this.entradas = entradas;
    }
    
}
