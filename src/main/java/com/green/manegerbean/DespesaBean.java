/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.green.modelo.Despesa;
import com.green.modelo.Despesadebito;
import com.green.rn.DespesaRN;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "despesaBean")
@ViewScoped
public class DespesaBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{despesaRN}")
    private DespesaRN despesaRN;
    private Despesa despesaNovo;
    private Despesadebito despesadebito;
    private List<Despesa> despesas;
    private List<Despesa> despesaVencidas;
    private boolean tipoMulta;
    private boolean tipoDesconto;
    private BigDecimal valorPago = BigDecimal.ZERO;
    private int parcelas = 0;
    private int vencimento = 0;
    private Date dTInicio;
    private Date dTFim;
    
   
    @PostConstruct
    private void init() {
        this.despesaNovo = new Despesa();
        this.despesadebito = new Despesadebito();
     
        getDespesaNovo().setValorMulta(BigDecimal.ZERO);
        getDespesaNovo().setValorJuros(BigDecimal.ZERO);
        getDespesaNovo().setValorDesconto(BigDecimal.ZERO);
        getDespesaNovo().setQuantidadeParcela(1);
    
    
    }

    

    public void salvar(ActionEvent actionEvent) {
        getDespesaRN().salvar(getDespesaNovo());
        init();
    }

    public void confirmacaoDespesa(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formConfirmaDespesa");
        context.execute("PF('dialogDespesa').show()");
    }


    public void filtroPeriodoSituacao(ActionEvent event){
    	setDespesas(getDespesaRN().filtroPeriodoSituacao(getdTInicio(), getdTFim(),false));
    }
    
    
    


    
    public List<Despesa> getDespesas() {
    	
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    public boolean isTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(boolean tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public boolean isTipoMulta() {
        return tipoMulta;
    }

    public void setTipoMulta(boolean tipoMulta) {
        this.tipoMulta = tipoMulta;
    }

    public Despesa getDespesaNovo() {
        return despesaNovo;
    }

    public void setDespesaNovo(Despesa despesaNovo) {
        this.despesaNovo = despesaNovo;
    }

    public Despesadebito getDespesadebito() {
        return despesadebito;
    }

    public void setDespesadebito(Despesadebito despesadebito) {
        this.despesadebito = despesadebito;
    }

    public DespesaRN getDespesaRN() {
        return despesaRN;
    }

    public void setDespesaRN(DespesaRN despesaRN) {
        this.despesaRN = despesaRN;
    }

    
    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public int getVencimento() {
        return vencimento;
    }

    public void setVencimento(int vencimento) {
        this.vencimento = vencimento;
    }



	public Date getdTInicio() {
		return dTInicio;
	}



	public void setdTInicio(Date dTInicio) {
		this.dTInicio = dTInicio;
	}



	public Date getdTFim() {
		return dTFim;
	}



	public void setdTFim(Date dTFim) {
		this.dTFim = dTFim;
	}



	public List<Despesa> getDespesaVencidas() {
		this.despesaVencidas = getDespesaRN().despesaVencidas();
		return despesaVencidas;
	}



	public void setDespesaVencidas(List<Despesa> despesaVencidas) {
		this.despesaVencidas = despesaVencidas;
	}

	
    
    
}
