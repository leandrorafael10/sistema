package com.green.rn;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.dao.PainelContratoDAO;
import com.green.modelo.PainelContrato;

@Service("painelContratoRN")
@Transactional("tmGreen")
public class PainelContratoRN {

	@Inject
	private PainelContratoDAO painelContratoDAO;

	public boolean salvar(PainelContrato painelContrato) {
		if (getPainelContratoDAO().salvar(painelContrato) != null) {
			return true;
		} else {
			return false;
		}

	}
	public boolean atualizar(PainelContrato painelContrato){
		if (getPainelContratoDAO().atualizar(painelContrato) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<PainelContrato> listar(){
		return getPainelContratoDAO().listar();
	}
	
	public void excluir(PainelContrato painelContrato){
		getPainelContratoDAO().excluir(painelContrato);
	}

	public PainelContratoDAO getPainelContratoDAO() {
		return painelContratoDAO;
	}

	public void setPainelContratoDAO(PainelContratoDAO painelContratoDAO) {
		this.painelContratoDAO = painelContratoDAO;
	}

}
