package com.green.rn;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.dao.CheckPontoDAO;
import com.green.modelo.CheckPonto;
import com.green.modelo.Funcionario;
import com.green.modelo.Pontodevenda;
import com.green.util.ContextoUtil;

@Service("checkPontoRN")
@Transactional("tmGreen")
public class CheckPontoRN {

	@Inject
	private CheckPontoDAO checkPontoDAO;

	public boolean fazerCheckin(Pontodevenda pontodevenda){
		if(pontodevenda==null){
			return false;
		}
		CheckPonto checkPonto = new CheckPonto();
		checkPonto.setIDFuncionario(ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario());
		checkPonto.setIDPontodevenda(pontodevenda);
		checkPonto.setDTInc(new Date());
		if(getCheckPontoDAO().salvar(checkPonto)!=null){
			return true;
		}else{
			return false;
		}
		
	}
	public boolean confirmarCheckinDia(Funcionario funcionario){
		return getCheckPontoDAO().confirmarCheckinDia(funcionario);
	}
	
	public CheckPontoDAO getCheckPontoDAO() {
		return checkPontoDAO;
	}

	public void setCheckPontoDAO(CheckPontoDAO checkPontoDAO) {
		this.checkPontoDAO = checkPontoDAO;
	}
	
	
	
}
