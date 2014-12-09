/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.modelo.Despesadebito;

/**
 * 
 * @author leandro.silva
 */
@Repository("despesaDebitoDAO")
@SuppressWarnings("unchecked")
public class DespesaDebitoDAO {
	@Autowired
	private SessionFactory sf;

	public void salvar(Despesadebito despesadebito) {
		despesadebito.setTipoPagamento(true);
		getSf().getCurrentSession().merge(despesadebito);
	}

	public List<Despesadebito> lista() {
		return (List<Despesadebito>) getSf().getCurrentSession().createQuery("from com.green.modelo.Despesadebito").list();

	}

	public List<Despesadebito> listaPeriodo(Date inicio, Date fim) {
		return (List<Despesadebito>) getSf().getCurrentSession()
				.createQuery("from com.green.modelo.Despesadebito d where d.iDDebito.dTBaixa >= :inicio and d.iDDebito.dTBaixa <= :fim").setDate("inicio", inicio)
				.setDate("fim", fim).list();
	}

	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	public void estornarDebito(Despesadebito despesadebito) {
		getSf().getCurrentSession().delete(despesadebito);

	}

}
