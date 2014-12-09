/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.dao.DespesaDAO;
import com.green.modelo.Despesa;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;

/**
 * 
 * @author leandro.silva
 */
@Service("despesaRN")
@Transactional(value = "tmGreen")
public class DespesaRN {

	@Autowired
	private DespesaDAO despesaDAO;

	public List<Despesa> listando() {
		return getDespesaDAO().listando();
	}

	public Despesa buscar(Integer codigo) {
		return getDespesaDAO().buscar(codigo);
	}

	
	public void atualizar(Despesa despesa) {
			despesa.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
			despesa.setDTAlt(new Date(System.currentTimeMillis()));
			despesa.setValorLiquido(despesa.getValorNominal());
			getDespesaDAO().atualizar(despesa);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Atualizado com sucesso!"));
	}

	
	public void salvar(Despesa despesa) {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		despesa.setIDUsuario(contextoBean.getUsuarioLogado());
		despesa.setDTInc(new Date(System.currentTimeMillis()));
		despesa.setValorLiquido(despesa.getValorNominal());
		Calendar calendar = GregorianCalendar.getInstance();
		for (int i = 1; i <= despesa.getQuantidadeParcela(); i++) {
			despesa.setNumero(i);
			getDespesaDAO().salvar(despesa);
			calendar.setTime(despesa.getDTVencimento());
			calendar.add(Calendar.MONDAY, 1);
			despesa.setDTVencimento(calendar.getTime());
			calendar.setTime(despesa.getDTVencimento());
		}
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok,Salvo com sucesso!","Ok,Salvo com sucesso!"));
	}

	 public List<Despesa> filtroPeriodoSituacao(Date inicio,Date fim,boolean pago){
		 return getDespesaDAO().filtroPeriodoSituacao(inicio, fim,pago);
	 }

	public List<Despesa> despesaVencidas() {
		return getDespesaDAO().despesasVencidas();
	}

	public List<Despesa> despesasAvencer() {
		return getDespesaDAO().despesasAvencer();
	}

	public List<Despesa> despesasPagas() {
		return getDespesaDAO().despesasPagas();
	}

	public List<Despesa> despesasApagar() {
		return getDespesaDAO().despesasApagar();
	}

	public List<Despesa> pendentesPagamento() {
		return getDespesaDAO().pendentesPagamento();
	}

	public void excluir(Despesa despesa) {
		getDespesaDAO().excluir(despesa);
	}

	
	
	public DespesaDAO getDespesaDAO() {
		return despesaDAO;
	}

	public void setDespesaDAO(DespesaDAO despesaDAO) {
		this.despesaDAO = despesaDAO;
	}

	
}
