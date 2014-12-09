/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.green.modelo.Brinde;

/**
 * 
 * @author leandro.silva
 */
@Repository
public class BrindeDAO extends AbstractBaseDAO<Brinde, Integer> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Brinde> listar() {
		return getSf().getCurrentSession().createCriteria(Brinde.class).list();
	}

	@Override
	public Brinde buscar(Integer pk) {
		Criteria criteria = getSf().getCurrentSession().createCriteria(
				Brinde.class);
		criteria.add(Restrictions.eq("iDBrinde", pk));
		return (Brinde) criteria.uniqueResult();
	}

	@Override
	public Brinde salvar(Brinde brinde) {
		return (Brinde) getSf().getCurrentSession().merge(brinde);
	}

	@SuppressWarnings("rawtypes")
	public List listarPeriodo(Date inicio, Date fim) {
		String sql = "select bc.iDCapalotejornal.iDPontovenda.descricao,bc.iDCapalotejornal.iDPontovenda.cidade, count(bc.iDBrinde)"
				+ " from com.green.modelo.Brindecapalote bc "
				+ "where bc.iDCapalotejornal.dTVenda > :inicio"
				+ " and bc.iDCapalotejornal.dTVenda < :fim group by bc.iDCapalotejornal.iDPontovenda order by count(bc.iDBrinde) desc";
		Query query = getSf().getCurrentSession().createQuery(sql)
				.setDate("inicio", inicio).setDate("fim", fim);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public Brinde atualizar(Brinde b) {
		return (Brinde) getSf().getCurrentSession().merge(b);
	}

	@Override
	public void excluir(Brinde entidade) {
		getSf().getCurrentSession().delete(entidade);

	}

}
