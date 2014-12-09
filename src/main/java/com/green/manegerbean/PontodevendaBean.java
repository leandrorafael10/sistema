/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.green.modelo.Pontodevenda;
import com.green.rn.CheckPontoRN;
import com.green.rn.PontodevendaRN;
import com.green.util.ContadorPontoBrinde;
import com.green.util.ContextoUtil;
import com.green.util.RelatorioUtil;

/**
 * 
 * @author leandro.silva
 */
@ManagedBean(name = "pontodevendaBean")
@ViewScoped
public class PontodevendaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pontodevenda pontodevenda;
	@ManagedProperty("#{pontodevendaRN}")
	private PontodevendaRN pontodevendaRN;
	@ManagedProperty("#{checkPontoRN}")
	private CheckPontoRN checkPontoRN;
	private List<ContadorPontoBrinde> contadorPontos;
	private Date inicio;
	private Date fim;
	private int status;
	private boolean autorizacaoCheckin;

	@PostConstruct
	private void init() {
		this.pontodevenda = new Pontodevenda();
		this.autorizacaoCheckin = false;
	}

	@SuppressWarnings("rawtypes")
	public void listarPeriodo(ActionEvent event) {
		List lista = getPontodevendaRN().listarPeriodo(inicio, fim, status);
		this.contadorPontos = new ArrayList<>();
		for (Object object : lista) {
			Map row = (Map) object;
			this.contadorPontos.add(new ContadorPontoBrinde(String.valueOf(row
					.get("0")), String.valueOf(row.get("1")), (Long) row
					.get("2"), (BigDecimal) row.get("3")));
		}
	}

	public List<Pontodevenda> completePonto(String query) {
		List<Pontodevenda> pontosCadastrados = getPontodevendaRN().listar();
		List<Pontodevenda> filtroPontos = new ArrayList<Pontodevenda>();

		for (int i = 0; i < pontosCadastrados.size(); i++) {
			Pontodevenda ponto = pontosCadastrados.get(i);
			if (ponto.getDescricao().toLowerCase()
					.startsWith(query.toLowerCase())) {
				filtroPontos.add(ponto);
			}
		}

		return filtroPontos;
	}

	public void checkPonto() {
		if (this.autorizacaoCheckin = getCheckPontoRN().fazerCheckin(
				getPontodevenda())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!",
							"Chekin efetuado Com sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Erro ao fazer check-in!",
							"Insira um ponto de venda"));
		}

	}

	public void salvar(ActionEvent event) {

		getPontodevendaRN().salvar(getPontodevenda());
		setPontodevenda(new Pontodevenda());

	}

	public void atualizar(ActionEvent event) {

		getPontodevendaRN().atualizar(getPontodevenda());
		setPontodevenda(new Pontodevenda());
	}

	public void geraPDF() {
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("data_inicio", inicio);
		parametros.put("data_fim", fim);
		parametros
				.put("usuario", ContextoUtil.getContextoBean()
						.getUsuarioLogado().getIDFuncionario().getIDPessoa()
						.getRazao());
		JRDataSource jrds = new JRBeanCollectionDataSource(contadorPontos);
		RelatorioUtil.geraRelatorioBean("vendas_ponto", jrds, parametros);
	}

	public Pontodevenda getPontodevenda() {
		return pontodevenda;
	}

	public void setPontodevenda(Pontodevenda pontodevenda) {
		this.pontodevenda = pontodevenda;
	}

	public PontodevendaRN getPontodevendaRN() {
		return pontodevendaRN;
	}

	public void setPontodevendaRN(PontodevendaRN pontodevendaRN) {
		this.pontodevendaRN = pontodevendaRN;
	}

	public List<ContadorPontoBrinde> getContadorPontos() {
		return contadorPontos;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean getAutorizacaoCheckin() {
		this.autorizacaoCheckin = getCheckPontoRN().confirmarCheckinDia(ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario());
		return autorizacaoCheckin;
	}

	public void setAutorizacaoCheckin(boolean autorizacaoCheckin) {
		this.autorizacaoCheckin = autorizacaoCheckin;
	}

	public CheckPontoRN getCheckPontoRN() {
		return checkPontoRN;
	}

	public void setCheckPontoRN(CheckPontoRN checkPontoRN) {
		this.checkPontoRN = checkPontoRN;
	}

}
