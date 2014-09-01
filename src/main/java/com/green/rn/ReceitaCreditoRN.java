/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.CreditoDAO;
import com.green.dao.ReceitaCreditoDAO;
import com.green.dao.ReceitaDAO;
import com.green.modelo.Credito;
import com.green.modelo.Funcionario;
import com.green.modelo.Receita;
import com.green.modelo.Receitacredito;
import com.green.util.ContextoUtil;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service("receitaCreditoRN")
public class ReceitaCreditoRN {

    @Autowired
    private ReceitaCreditoDAO receitaCreditoDAO;
    @Autowired
    private ReceitaDAO receitaDAO;
    @Autowired
    private CreditoDAO creditoDAO;

    @Transactional("tmGreen")
    public void baixaPagamento(Receita receita,Date dataBaixa) {
        Receitacredito receitacredito = new Receitacredito();
        receitacredito.setIDReceita(receita);
        receitacredito.setIDCredito(new Credito());
        receitacredito.getIDCredito().setDTBaixa(dataBaixa);
        receitacredito.getIDCredito().setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
        receitacredito.getIDCredito().setIDTpDocumento(receitacredito.getIDReceita().getIDDocumento());
        receitacredito.getIDCredito().setDTInc(new Date());
        receitacredito.getIDCredito().setValor(receitacredito.getIDReceita().getValorNominal());
        getCreditoDAO().salvar(receitacredito.getIDCredito());
        getReceitaCreditoDAO().salvar(receitacredito);
        receitacredito.getIDReceita().setPago(true);
        getReceitaDAO().atualizar(receitacredito.getIDReceita());
    }
    
    public List<Receitacredito> listaReceitaPorVendedor(Date inicio ,Date fim,Funcionario f){
        return getReceitaCreditoDAO().listaReceitaPorVendedor(inicio, fim, f);
    }

    public List<Receitacredito> filtro(Receita receitaFiltro, Date fimVenc, int pag, BigDecimal valorIni, BigDecimal valorFim) {
        return getReceitaCreditoDAO().filtro(receitaFiltro, fimVenc, pag, valorIni, valorFim);
    }

    public CreditoDAO getCreditoDAO() {
        return creditoDAO;
    }

    public void setCreditoDAO(CreditoDAO creditoDAO) {
        this.creditoDAO = creditoDAO;
    }

    public ReceitaCreditoDAO getReceitaCreditoDAO() {
        return receitaCreditoDAO;
    }

    public void setReceitaCreditoDAO(ReceitaCreditoDAO receitaCreditoDAO) {
        this.receitaCreditoDAO = receitaCreditoDAO;
    }

    public ReceitaDAO getReceitaDAO() {
        return receitaDAO;
    }

    public void setReceitaDAO(ReceitaDAO receitaDAO) {
        this.receitaDAO = receitaDAO;
    }
}
