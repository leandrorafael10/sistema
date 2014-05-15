/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import java.math.BigDecimal;

/**
 *
 * @author leandro.silva
 */
public class Venda {

    private String promotor = "";
    private String gerente = "";

    private BigDecimal valorTotal = new BigDecimal(0);
    private BigDecimal valorAtivo = new BigDecimal(0);
    private BigDecimal valorCancelado = new BigDecimal(0);
    private BigDecimal valorPendente = new BigDecimal(0);
    private BigDecimal valorAgendado = new BigDecimal(0);
    private BigDecimal valorEstorno = new BigDecimal(0);
    private BigDecimal valorRenovado = new BigDecimal(0);
    private Long total = (long) 0;
    private Long ativo = (long) 0;
    private Long cancelado = (long) 0;
    private Long pendente = (long) 0;
    private Long agendado = (long) 0;
    private Long estornado = (long) 0;
    private Long renovado = (long) 0;

    private Double media = new Double("0");

    public Venda(String gerente, String promotor, Long total,
            BigDecimal valorTotal, BigDecimal faturado, Long ativo,
            Long cancelado, Long pendente, Long agendado,
            Long estornado) {
        this.gerente = gerente;
        this.promotor = promotor;
        this.total = total;
        this.valorTotal = valorTotal;
        this.valorAtivo = faturado;
        this.ativo = ativo;
        this.cancelado = cancelado;
        this.pendente = pendente;
        this.agendado = agendado;
        this.estornado = estornado;
    }

    public Venda(String gerente, String promotor, Long ativo,
            BigDecimal valorAtivo, Double media, BigDecimal valorEstorno) {
        this.gerente = gerente;
        this.promotor = promotor;
        this.ativo = ativo;
        this.valorAtivo = valorAtivo;
        this.media = media;
        this.valorEstorno = valorEstorno;
    }

    public Venda(String gerente, String promotor, Long total,
            BigDecimal valorTotal, BigDecimal faturado, Long ativo,
            Long cancelado, Long pendente, Long agendado,
            Long renovado, BigDecimal valorCancelado, BigDecimal valorPendente, BigDecimal valorAgendado, BigDecimal valorRenovado) {
        this.gerente = gerente;
        this.promotor = promotor;
        this.total = total;
        this.valorTotal = valorTotal;
        this.valorAtivo = faturado;
        this.ativo = ativo;
        this.cancelado = cancelado;
        this.pendente = pendente;
        this.agendado = agendado;
        this.renovado = renovado;
        this.valorAgendado = valorAgendado;
        this.valorCancelado = valorCancelado;
        this.valorPendente = valorPendente;
        this.valorRenovado = valorRenovado;
    }

    public Venda() {
        // TODO Auto-generated constructor stub
    }

    public Venda(String g) {
        this.gerente = g;
        // TODO Auto-generated constructor stub
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public String getPromotor() {
        return promotor;
    }

    public void setPromotor(String promotor) {
        this.promotor = promotor;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorAtivo() {
        return valorAtivo;
    }

    public void setValorAtivo(BigDecimal valorAtivo) {
        this.valorAtivo = valorAtivo;
    }

    public Long getAtivo() {
        return ativo;
    }

    public void setAtivo(Long ativo) {
        this.ativo = ativo;
    }

    public Long getCancelado() {
        return cancelado;
    }

    public void setCancelado(Long cancelado) {
        this.cancelado = cancelado;
    }

    public Long getPendente() {
        return pendente;
    }

    public void setPendente(Long pendente) {
        this.pendente = pendente;
    }

    public Long getAgendado() {
        return agendado;
    }

    public void setAgendado(Long agendado) {
        this.agendado = agendado;
    }

    public Long getEstornado() {
        return estornado;
    }

    public void setEstornado(Long estornado) {
        this.estornado = estornado;
    }

    public BigDecimal getValorEstorno() {
        return valorEstorno;
    }

    public void setValorEstorno(BigDecimal valorEstorno) {
        this.valorEstorno = valorEstorno;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Long getRenovado() {
        return renovado;
    }

    public void setRenovado(Long renovado) {
        this.renovado = renovado;
    }

    public BigDecimal getValorCancelado() {
        return valorCancelado;
    }

    public void setValorCancelado(BigDecimal valorCancelado) {
        this.valorCancelado = valorCancelado;
    }

    public BigDecimal getValorPendente() {
        return valorPendente;
    }

    public void setValorPendente(BigDecimal valorPendente) {
        this.valorPendente = valorPendente;
    }

    public BigDecimal getValorAgendado() {
        return valorAgendado;
    }

    public void setValorAgendado(BigDecimal valorAgendado) {
        this.valorAgendado = valorAgendado;
    }

    public BigDecimal getValorRenovado() {
        return valorRenovado;
    }

    public void setValorRenovado(BigDecimal valorRenovado) {
        this.valorRenovado = valorRenovado;
    }

}
