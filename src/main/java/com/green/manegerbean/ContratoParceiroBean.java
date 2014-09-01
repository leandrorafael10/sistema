/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Cliente;
import com.green.modelo.ContratoMidia;
import com.green.modelo.ContratoParceiro;
import com.green.modelo.Praca;
import com.green.rn.ContratoParceiroRN;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "contratoParceiroBean")
@ViewScoped
public class ContratoParceiroBean implements Serializable{

    @ManagedProperty("#{contratoParceiroRN}")
    private ContratoParceiroRN contratoParceiroRN;
    private ContratoParceiro contratoParceiro;
    private BigDecimal receitaLiquida;
    private BigDecimal lucro ;
    private BigDecimal comissaoVendResp;
    private BigDecimal impostoResp ;
    private BigDecimal bvResp;
    private BigDecimal despesaSoma;
    
    @PostConstruct
    private void init(){
        this.contratoParceiro = new ContratoParceiro();
        this.contratoParceiro.setIDContratoMidia(new ContratoMidia());
        this.contratoParceiro.getIDContratoMidia().setPracas(new ArrayList<Praca>());
        this.contratoParceiro.getIDContratoMidia().setIDcliente(new Cliente());
        this.receitaLiquida = new BigDecimal("0.00");
        this.lucro = new BigDecimal("0.00");
        this.comissaoVendResp = new BigDecimal("0.00");
        this.impostoResp = new BigDecimal("0.00");
        this.bvResp = new BigDecimal("0.00");
        this.despesaSoma = new BigDecimal("0.00");
    }

    public void calculoTaxas(ActionEvent event){
        setBvResp(getReceitaLiquida().divide(new BigDecimal("100.00")).multiply(getContratoParceiro().getBv()));
        setComissaoVendResp(getReceitaLiquida().subtract(getBvResp()).divide(new BigDecimal("100.00")).multiply(getContratoParceiro().getComissao()));
        setImpostoResp(getReceitaLiquida().divide(new BigDecimal("100.00")).multiply(getContratoParceiro().getImposto()));
        setDespesaSoma(getBvResp().add(getComissaoVendResp()).add(getImpostoResp()));
        setLucro(getReceitaLiquida().subtract(getDespesaSoma()));
        RequestContext.getCurrentInstance().update("formInsereContrato");
        RequestContext.getCurrentInstance().execute("PF('dialogInsereContrato').show()");
    }
    
    public void salvar(ActionEvent event){
        getContratoParceiro().getIDContratoMidia().setValor(this.receitaLiquida);
        getContratoParceiroRN().salvar(getContratoParceiro(),this.lucro);
        init();
    }
    
    public ContratoParceiroRN getContratoParceiroRN() {
        return contratoParceiroRN;
    }

    
    public void setContratoParceiroRN(ContratoParceiroRN contratoParceiroRN) {
        this.contratoParceiroRN = contratoParceiroRN;
    }

    public ContratoParceiro getContratoParceiro() {
        return contratoParceiro;
    }

    public void setContratoParceiro(ContratoParceiro contratoParceiro) {
        this.contratoParceiro = contratoParceiro;
    }

    public BigDecimal getReceitaLiquida() {
        return receitaLiquida;
    }

    public void setReceitaLiquida(BigDecimal receitaLiquida) {
        this.receitaLiquida = receitaLiquida;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public void setLucro(BigDecimal lucro) {
        this.lucro = lucro;
    }

    public BigDecimal getComissaoVendResp() {
        return comissaoVendResp;
    }

    public void setComissaoVendResp(BigDecimal comissaoVendResp) {
        this.comissaoVendResp = comissaoVendResp;
    }

    public BigDecimal getImpostoResp() {
        return impostoResp;
    }

    public void setImpostoResp(BigDecimal impostoResp) {
        this.impostoResp = impostoResp;
    }

    public BigDecimal getBvResp() {
        return bvResp;
    }

    public void setBvResp(BigDecimal bvResp) {
        this.bvResp = bvResp;
    }

    public BigDecimal getDespesaSoma() {
        return despesaSoma;
    }

    public void setDespesaSoma(BigDecimal despesaSoma) {
        this.despesaSoma = despesaSoma;
    }
    
}
