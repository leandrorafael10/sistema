package com.green.teste.dao;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.junit.Test;

import com.green.dao.CheckPontoDAO;
import com.green.dao.FuncionarioDAO;
import com.green.teste.TesteBase.AbstractTeste;

public class TesteCheckPonto extends AbstractTeste{
	
	private static Logger  logger = Logger.getLogger(new CheckPontoDAO().toString());

	@Inject
	private CheckPontoDAO checkPontoDAO;
	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Test
	public void testeConfirmarCheckinDia(){
		boolean resp = this.checkPontoDAO.confirmarCheckinDia(this.funcionarioDAO.carregar(40));
		logger.info("Resultado : "+String.valueOf(resp));
		//Assert.assertTrue(resp);
	}
}
