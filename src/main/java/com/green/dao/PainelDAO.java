package com.green.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.green.modelo.Painel;
import com.green.modelo.Praca;

@SuppressWarnings("unchecked")
@Repository("painelDAO")
public class PainelDAO extends AbstractBaseDAO<Painel, Integer> {

	@Override
	public Painel buscar(Integer pk) {
		return (Painel) getSf()
				.getCurrentSession()
				.createQuery(
						"from com.green.modelo.Painel m where m.iDPainel = :id")
				.setInteger("id", pk).uniqueResult();

	}

	@Override
	public Painel salvar(Painel entidade) {
		return (Painel) getSf().getCurrentSession().merge(entidade);
	}

	@Override
	public Painel atualizar(Painel entidade) {
		return (Painel) getSf().getCurrentSession().merge(entidade);
	}

	@Override
	public List<Painel> listar() {
		return (List<Painel>) getSf().getCurrentSession()
				.createQuery("from com.green.modelo.Painel").list();

	}
	
	public List<Painel> listarPaineisDisponiveis(){
		Calendar dataHoje = Calendar.getInstance();
		String sql = "from com.green.modelo.Painel ms where ms.iDPainel  not in(select p.iDPainel from com.green.modelo.PainelContrato p " +
					"where p.iDContratoMidia.dataInicioContrato <= :hoje and p.iDContratoMidia.dataFimContrato >= :hoje)";
		Query query = getSf().getCurrentSession().createQuery(sql).setDate("hoje",dataHoje.getTime());
		return query.list();
	}

	@Override
	public void excluir(Painel entidade) {
		getSf().getCurrentSession().delete(entidade);
	}
	
	public List<Painel> listarPorPraca(Praca praca){
		return getSf().getCurrentSession().createQuery("from com.green.modelo.Painel m where m.iDPraca = :praca").setParameter("praca", praca).list();
	}
	
	public List<Painel> buscaPainelPorPracas(List<Praca> pracas){
		return getSf().getCurrentSession().createQuery("from com.green.modelo.Painel m where m.iDPraca in (:pracas)").setParameterList("pracas", pracas).list();
	}

}
