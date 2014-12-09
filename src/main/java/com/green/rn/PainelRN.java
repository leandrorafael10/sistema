package com.green.rn;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.dao.PainelDAO;
import com.green.modelo.Painel;
import com.green.modelo.Praca;
import com.green.util.ContextoUtil;

@Service("painelRN")
@Transactional("tmGreen")
public class PainelRN {
	
	@Inject
	private PainelDAO painelDAO;

	
	public boolean salvar(Painel painel){
		painel.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
		painel.setDTInc(new Date());
		painel.setAtivo(true);
		if(getMidiaStaticaDAO().salvar(painel)!=null){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void excluir(Painel painel){
		getMidiaStaticaDAO().excluir(painel);
	}
	
	public boolean atualizar(Painel painel){
		painel.setIDUsuarioAlt(ContextoUtil.getContextoBean().getUsuarioLogado());
		painel.setDTAlt(new Date());
		if(getMidiaStaticaDAO().atualizar(painel)!=null){
			return true;
		}else{
			return false;
		}
		
	}
	
	public List<Painel> listar(){
		return getMidiaStaticaDAO().listar();
	}
	
	public List<Painel> listaPainelDisponivel(){
		return getMidiaStaticaDAO().listarPaineisDisponiveis();
	}
	
	public List<Painel> buscaPainelPorPracas(List<Praca> pracas){
		return getMidiaStaticaDAO().buscaPainelPorPracas(pracas);
	}
	
	public PainelDAO getMidiaStaticaDAO() {
		return painelDAO;
	}

	public void setMidiaStaticaDAO(PainelDAO painelDAO) {
		this.painelDAO = painelDAO;
	}
	
	

}
