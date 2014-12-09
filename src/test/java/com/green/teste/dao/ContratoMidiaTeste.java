package com.green.teste.dao;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import com.green.dao.ContratoMidiaDAO;
import com.green.modelo.ContratoMidia;
import com.green.modelo.Praca;
import com.green.teste.TesteBase.AbstractTeste;
public class ContratoMidiaTeste extends AbstractTeste {
	
	public static final Logger logger  =Logger.getLogger(ContratoMidiaTeste.class.toString());
	
	@Inject
	private ContratoMidiaDAO contratoMidiaDAO;

	
	public ContratoMidiaDAO getContratoMidiaDAO() {
		return contratoMidiaDAO;
	}

	public void setContratoMidiaDAO(ContratoMidiaDAO contratoMidiaDAO) {
		this.contratoMidiaDAO = contratoMidiaDAO;
	}
	
	@Test
	public void testeBuscaRazaoLike(){
		List<ContratoMidia> contratoMidias  = getContratoMidiaDAO().buscarRazaoLike("CONTREI");
		logger.info(contratoMidias.toString());
		Assert.assertNotNull(contratoMidias);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testeReceitaPorPracaValorMensal(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, 9, 1);
		Praca praca = new Praca();
		praca.setIdpraca(7);
		List list  = getContratoMidiaDAO().receitaPorPracaValorMensal(praca,calendar);
		logger.info(list.toString());
		Assert.assertNotNull(list);
	}
	@Test
	public void listarPorPraca(){
		List<ContratoMidia> list = getContratoMidiaDAO().listarPorPraca(7);
		logger.info(list.toString());
		Assert.assertNotNull(list);
	}

}
