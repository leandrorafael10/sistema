package com.green.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class ReceitaPraca {

	private String nome;
	private String cnpj;
	private String contrato;
	private Date data_inicio;
	private Date data_fim;
	private BigDecimal valor_total;
	private BigInteger qtd_mes;
	private BigInteger mes_parcela;
	private BigDecimal valor_mes;

	
	public ReceitaPraca(){
		
	}
	public ReceitaPraca(String nome, String cnpj, String contrato,
			Date data_inicio, Date data_fim, BigDecimal valor_total,
			BigInteger qtd_mes, BigInteger mes_parcela, BigDecimal valor_mes) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.contrato = contrato;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.valor_total = valor_total;
		this.qtd_mes = qtd_mes;
		this.mes_parcela = mes_parcela;
		this.valor_mes = valor_mes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}

	public BigInteger getQtd_mes() {
		return qtd_mes;
	}

	public void setQtd_mes(BigInteger qtd_mes) {
		this.qtd_mes = qtd_mes;
	}

	public BigInteger getMes_parcela() {
		return mes_parcela;
	}

	public void setMes_parcela(BigInteger mes_parcela) {
		this.mes_parcela = mes_parcela;
	}

	public BigDecimal getValor_mes() {
		return valor_mes;
	}

	public void setValor_mes(BigDecimal valor_mes) {
		this.valor_mes = valor_mes;
	}

}
