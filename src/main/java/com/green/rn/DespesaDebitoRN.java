/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.dao.DebitoDAO;
import com.green.dao.DespesaDAO;
import com.green.dao.DespesaDebitoDAO;
import com.green.modelo.Despesadebito;
import com.green.util.ContextoUtil;

/**
 *
 * @author leandro.silva
 */
@Service("despesaDebitoRN")
@Transactional("tmGreen")
public class DespesaDebitoRN {

    @Autowired
    private DespesaDebitoDAO despesaDebitoDAO;
    @Autowired
    private DespesaDAO despesaDAO;
    @Autowired
    private DebitoDAO debitoDAO;
    
    public void salvar(Despesadebito despesadebito) {
    	despesadebito.getIDDespesa().setPago(true);
    	despesadebito.getIDDebito().setNumero(despesadebito.getIDDespesa().getCodigo());
    	despesadebito.getIDDebito().setValor(despesadebito.getIDDespesa().getValorLiquido());
    	despesadebito.getIDDebito().setDTInc(new Date());
    	despesadebito.getIDDebito().setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
    	despesadebito.getIDDebito().setIDTpDocumento(despesadebito.getIDDespesa().getIDDocumento());
        getDespesaDebitoDAO().salvar(despesadebito);

    }
    
    public void estornarDebito(Despesadebito despesadebito) {
    	despesadebito.getIDDespesa().setPago(false);
    	getDespesaDAO().atualizar(despesadebito.getIDDespesa());
		getDespesaDebitoDAO().estornarDebito(despesadebito);
				
	}
    
    public List<Despesadebito> listaPeriodo(Date inicio, Date fim) {
    	return getDespesaDebitoDAO().listaPeriodo(inicio, fim);
    }
    

    public DespesaDebitoDAO getDespesaDebitoDAO() {
        return despesaDebitoDAO;
    }

    public void setDespesaDebitoDAO(DespesaDebitoDAO despesaDebitoDAO) {
        this.despesaDebitoDAO = despesaDebitoDAO;
    }

    public DebitoDAO getDebitoDAO() {
        return debitoDAO;
    }

    public void setDebitoDAO(DebitoDAO debitoDAO) {
        this.debitoDAO = debitoDAO;
    }

    public DespesaDAO getDespesaDAO() {
        return despesaDAO;
    }

    public void setDespesaDAO(DespesaDAO despesaDAO) {
        this.despesaDAO = despesaDAO;
    }

	
}
