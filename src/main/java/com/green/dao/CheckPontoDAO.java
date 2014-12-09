package com.green.dao;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.green.modelo.CheckPonto;
import com.green.modelo.Funcionario;


@Repository("checkPontoDAO")
public class CheckPontoDAO extends AbstractBaseDAO<CheckPonto, Integer> {

	@Override
	public CheckPonto buscar(Integer pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckPonto salvar(CheckPonto entidade) {
		
		return (CheckPonto)getSf().getCurrentSession().merge(entidade);
	}

	@Override
	public CheckPonto atualizar(CheckPonto entidade) {
		// TODO Auto-generated method stub
		return (CheckPonto)getSf().getCurrentSession().merge(entidade);
	}
	//retorna falso caso o check-in esteja autorizado, verdadeiro quando ja foi efetuado o check-dia.
	public boolean confirmarCheckinDia(Funcionario funcionario){
		Calendar hoje = Calendar.getInstance();
		Calendar amanha = Calendar.getInstance();
		
		amanha.add(Calendar.DATE, 1);
		
		Query query = getSf().getCurrentSession().createQuery("from com.green.modelo.CheckPonto cp where " +
				"cp.dTInc >= :hoje and cp.dTInc < :amanha and cp.iDFuncionario = :funcionario")
				.setParameter("funcionario", funcionario).setDate("hoje",hoje.getTime()).setDate("amanha", amanha.getTime());
		CheckPonto checkPonto = (CheckPonto)query.uniqueResult();
		if(checkPonto==null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public List<CheckPonto> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(CheckPonto entidade) {
		// TODO Auto-generated method stub
		
	}

}
