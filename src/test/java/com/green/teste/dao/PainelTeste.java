package com.green.teste.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import com.green.dao.PainelDAO;
import com.green.dao.PracaDAO;
import com.green.dao.UsuarioDao;
import com.green.modelo.Painel;
import com.green.teste.TesteBase.AbstractTeste;

public class PainelTeste extends AbstractTeste{
	private static final Logger logger = Logger.getLogger(PainelDAO.class.toString());

	@Inject
	private PainelDAO painelDAO;
	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private PracaDAO pracaDAO;
	
	
	@Test
	public void teste_salvar(){
		Painel midia = new Painel();
		midia.setIDUsuario(this.usuarioDao.carregarPeloLogin("master"));
		midia.setIDPraca(pracaDAO.carregar(1));
		midia.setDTInc(new Date());
		midia.setDescricao("teste");
		midia.setCodigo("test nome");
		Assert.assertNotNull(midia);
	}
	
	@Test
	public void teste_lista(){
		List<Painel> lista = getPainelDAO().listar();
		Assert.assertNotNull(lista);
	}
	
	@Test
	public void teste_busca_porPracas(){
		
		List<Painel> lista = getPainelDAO().buscaPainelPorPracas(this.pracaDAO.listar());
		Assert.assertEquals(4,lista.size());
	}
	
	@Test
	public void teste_carregar(){
		Painel midia =  getPainelDAO().buscar(1);
		Assert.assertNotNull(midia);
	}
	
	@Test
	public void teste_listarPaineisDisponiveis(){
		List<Painel> paineis = getPainelDAO().listarPaineisDisponiveis();
		logger.info(String.valueOf(paineis.size()));
		Assert.assertNotNull("Lista Dispiniveis",paineis);
	}


	public PainelDAO getPainelDAO() {
		return painelDAO;
	}


	public void setPainelDAO(PainelDAO painelDAO) {
		this.painelDAO = painelDAO;
	}
	
}
