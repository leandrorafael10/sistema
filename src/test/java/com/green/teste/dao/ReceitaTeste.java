package com.green.teste.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import com.green.dao.ReceitaDAO;
import com.green.modelo.Receita;
import com.green.teste.TesteBase.AbstractTeste;

@SuppressWarnings("deprecation")
public class ReceitaTeste extends AbstractTeste {
	// public static final Logger logger =Logger.getLogger(ReceitaDAO.class);

	@Inject
	private ReceitaDAO receitaDAO;

	@Test
	public void receitaPorPeriodoTeste() {
		List<Receita> receitas = getReceitaDAO().receitaPorPeriodo(
				new Date(114, 8, 1), new Date(114, 8, 30), false);
		// logger.debug(receitas);
		// logger.debug(new Date(114, 8,1).toString()+" inicio");

		Assert.assertNotNull("Receitas ok!", receitas);
	}

	public ReceitaDAO getReceitaDAO() {
		return receitaDAO;
	}

	public void setReceitaDAO(ReceitaDAO receitaDAO) {
		this.receitaDAO = receitaDAO;
	}

}
