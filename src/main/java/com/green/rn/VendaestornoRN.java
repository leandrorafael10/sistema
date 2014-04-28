package com.green.rn;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dao.VendaestornoDAO;
import com.green.modelo.Vendaestorno;
import com.green.util.ContextoUtil;


@Service("vendaestornoRN")
public class VendaestornoRN {

	@Autowired
	private VendaestornoDAO vendaestornoDAO;

	public void salvar(Vendaestorno vendaestorno){
		vendaestorno.setDTInc(new Date());
		vendaestorno.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
		getVendaestornoDAO().salvar(vendaestorno);
	}
	
	public VendaestornoDAO getVendaestornoDAO() {
		return vendaestornoDAO;
	}

	public void setVendaestornoDAO(VendaestornoDAO vendaestornoDAO) {
		this.vendaestornoDAO = vendaestornoDAO;
	}
	
	
}
