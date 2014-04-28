/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.TipoPagamentoDAO;
import com.green.modelo.Tipopagamento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("tipoPagamentoRN")
public class TipoPagamentoRN {
    @Autowired 
    private TipoPagamentoDAO tipoPagamentoDAO;

    public TipoPagamentoDAO getTipoPagamentoDAO() {
        return tipoPagamentoDAO;
    }

    public void setTipoPagamentoDAO(TipoPagamentoDAO tipoPagamentoDAO) {
        this.tipoPagamentoDAO = tipoPagamentoDAO;
    }
    
    public List<Tipopagamento> formaPagJornal(){
        return getTipoPagamentoDAO().listarJornal();
    }
    public Tipopagamento carregar(Integer pk){
        return getTipoPagamentoDAO().carregar(pk);
    }
    
    public List<Tipopagamento> listar(){
        return getTipoPagamentoDAO().listar();
    }
}
