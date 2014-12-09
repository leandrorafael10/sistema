package com.green.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.green.modelo.PainelContrato;

@Repository("painelContratoDAO")
public class PainelContratoDAO extends AbstractBaseDAO<PainelContrato, Integer> {

	@Override
	public PainelContrato buscar(Integer pk) {
		Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.PainelContrato where iDPainelContrato = :id").setInteger("id", pk);
		return (PainelContrato)query.uniqueResult();

	}

	@Override
	public PainelContrato salvar(PainelContrato entidade) {
		return (PainelContrato)getSf().getCurrentSession().merge(entidade);
		
	}

	@Override
	public PainelContrato atualizar(PainelContrato entidade) {
		return (PainelContrato)getSf().getCurrentSession().merge(entidade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PainelContrato> listar() {
		Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.PainelContrato");
		return query.list();
	}

	@Override
	public void excluir(PainelContrato entidade) {
		getSf().getCurrentSession().delete(entidade);
		
	}

}
