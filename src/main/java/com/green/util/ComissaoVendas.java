/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import com.green.modelo.CalculoComissao;
import com.green.modelo.Terceiros;
import java.math.BigDecimal;

/**
 *
 * @author leandro.silva
 */
public class ComissaoVendas {
    
    private Terceiros terceiros;
    private Integer vendaBruta =0;
    private Integer vendaCancelada=0;
    private Integer vendaLiquida=0;
    private BigDecimal fatBruto = new BigDecimal(0);
    private BigDecimal fatLiquido= new BigDecimal(0);
    private BigDecimal fatCancelado= new BigDecimal(0);
    private CalculoComissao procetagemComissao= new CalculoComissao();
    private BigDecimal valorComissao= new BigDecimal(0);
    private BigDecimal totalPagar = new BigDecimal(0);
    private float tipoComissao ;

    public ComissaoVendas() {
    }

    
    public ComissaoVendas(Terceiros terceiro, Integer vendaBruta, Integer vendaCancelada, Integer vendaLiquida, BigDecimal fatBruto, BigDecimal fatLiquido, BigDecimal fatCancelado, CalculoComissao procetagemComissao, BigDecimal valorComissao, BigDecimal totalPagar) {
        this.terceiros = terceiro;
        this.vendaBruta = vendaBruta;
        this.vendaCancelada = vendaCancelada;
        this.vendaLiquida = vendaLiquida;
        this.fatBruto = fatBruto;
        this.fatLiquido = fatLiquido;
        this.fatCancelado = fatCancelado;
        this.procetagemComissao = procetagemComissao;
        this.valorComissao = valorComissao;
        this.totalPagar = totalPagar;
    }

    public Terceiros getTerceiros() {
        return terceiros;
    }

    public void setTerceiros(Terceiros terceiros) {
        this.terceiros = terceiros;
    }

    public Integer getVendaBruta() {
        return vendaBruta;
    }

    public void setVendaBruta(Integer vendaBruta) {
        this.vendaBruta = vendaBruta;
    }

    public Integer getVendaCancelada() {
        return vendaCancelada;
    }

    public void setVendaCancelada(Integer vendaCancelada) {
        this.vendaCancelada = vendaCancelada;
    }

    public Integer getVendaLiquida() {
        return vendaLiquida;
    }

    public void setVendaLiquida(Integer vendaLiquida) {
        this.vendaLiquida = vendaLiquida;
    }

    public BigDecimal getFatBruto() {
        return fatBruto;
    }

    public void setFatBruto(BigDecimal fatBruto) {
        this.fatBruto = fatBruto;
    }

    public BigDecimal getFatLiquido() {
        return fatLiquido;
    }

    public void setFatLiquido(BigDecimal fatLiquido) {
        this.fatLiquido = fatLiquido;
    }

    public BigDecimal getFatCancelado() {
        return fatCancelado;
    }

    public void setFatCancelado(BigDecimal fatCancelado) {
        this.fatCancelado = fatCancelado;
    }

    public CalculoComissao getProcetagemComissao() {
        return procetagemComissao;
    }

    public void setProcetagemComissao(CalculoComissao procetagemComissao) {
        this.procetagemComissao = procetagemComissao;
    }

    public BigDecimal getValorComissao() {
        return valorComissao;
    }

    public void setValorComissao(BigDecimal valorComissao) {
        this.valorComissao = valorComissao;
    }

    public BigDecimal getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(BigDecimal totalPagar) {
        this.totalPagar = totalPagar;
    }

    public float getTipoComissao() {
        return tipoComissao;
    }

    public void setTipoComissao(float tipoComissao) {
        this.tipoComissao = tipoComissao;
    }
    
    
    
}
