/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.CalculoComissaoDAO;
import com.green.dao.CapaLoteDAO;
import com.green.modelo.CapaLote;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service("capaLoteRN")
public class CapaLoteRN {

    @Autowired
    private CapaLoteDAO capaLoteDAO;
    @Autowired
    private CalculoComissaoDAO calculoComissaoDAO;

    public List<CapaLote> listar() {
        return getCapaLoteDAO().listar();
    }

    @SuppressWarnings("deprecation")
	@Transactional(value = "tmGreen")
    public void salvar(CapaLote capaLote) {
        CapaLote cl = getCapaLoteDAO().buscaPorCodigo(capaLote.getNumeroContrato());
        if (cl == null) {
            ContextoBean contextoBean = ContextoUtil.getContextoBean();
            capaLote.setIDUsuario(contextoBean.getUsuarioLogado());
            capaLote.setAtivo(Boolean.TRUE);
            capaLote.setDtinc(new Date());
            capaLote.setiDCalculoComissao(getCalculoComissaoDAO().carregarUltimo());
            getCapaLoteDAO().salvar(capaLote);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Código já cadastrato:"
                    + cl.getNumeroContrato()
                    + "\nCliente:" + cl.getNomeCliente() + "\nNome Promotor:" + cl.getIDTerceiros().getNome()
                    + "\nData da venda:" + cl.getDtvenda().getDate() + "/" + cl.getDtvenda().getMonth() + "" + cl.getDtvenda().getYear()+1900,
                    "Código já cadastrato:" + cl.getNumeroContrato()
                    + "\nCliente:" + cl.getNomeCliente() + "\nNome Promotor:" + cl.getIDTerceiros().getNome()
                    + "\nData da venda:" + cl.getDtvenda().getDate() + "/" + cl.getDtvenda().getMonth() + "" + cl.getDtvenda().getYear()));

        }

    }

    @Transactional(value = "tmGreen")
    public void alterar(CapaLote capaLote) {
        getCapaLoteDAO().atualizar(capaLote);
    }
    
    @Transactional(value = "tmGreen")
    public void cancelar(CapaLote capaLote) {
        RequestContext context = RequestContext.getCurrentInstance();
        capaLote.setAtivo(!capaLote.getAtivo());
        capaLote.setDtCancel(new Date());
        getCapaLoteDAO().atualizar(capaLote);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado com sucesso!", "Cancelado com sucesso!"));
        context.update("formCancelVenda");
    }

    @Transactional(value = "tmGreen")
    public void excluir(CapaLote capaLote) {
        getCapaLoteDAO().excluir(capaLote);
    }

    public List<CapaLote> listaCanceladoMes(Date datainicio, Date datafim) {
        return getCapaLoteDAO().listaCanceladosMes(datainicio, datafim);
    }
    public List<CapaLote> listaVendas(Date datainicio, Date datafim) {
        return getCapaLoteDAO().listaDeVendas(datainicio, datafim);
    }
    

    public CapaLoteDAO getCapaLoteDAO() {
        return capaLoteDAO;
    }

    public void setCapaLoteDAO(CapaLoteDAO capaLoteDAO) {
        this.capaLoteDAO = capaLoteDAO;
    }

    public CalculoComissaoDAO getCalculoComissaoDAO() {
        return calculoComissaoDAO;
    }

    public void setCalculoComissaoDAO(CalculoComissaoDAO calculoComissaoDAO) {
        this.calculoComissaoDAO = calculoComissaoDAO;
    }
    

    public List<CapaLote> listarDoDia() {
        return getCapaLoteDAO().listaDoDia();
    }

    public CapaLote buscaPorLote(Integer codigo) {
        return getCapaLoteDAO().buscaPorCodigo(codigo);
    }
}
