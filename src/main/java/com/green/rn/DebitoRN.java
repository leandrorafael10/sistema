/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.DebitoDAO;
import com.green.modelo.Conta;
import com.green.modelo.Debito;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Repository("debitoRN")
public class DebitoRN {

    @Autowired
    private DebitoDAO debitoDAO;

    public DebitoDAO getDebitoDAO() {
        return debitoDAO;
    }

    public void setDebitoDAO(DebitoDAO debitoDAO) {
        this.debitoDAO = debitoDAO;
    }

    public List<Debito> listando() {
        return getDebitoDAO().listando();
    }

    public Debito carregar(Integer codigo) {
        return getDebitoDAO().carregar(codigo);
    }
    @Transactional("tmGreen")
    public void salvar(Debito debito) {
       
            debito.setDTInc(new Date());
            debito.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
            getDebitoDAO().salvar(debito);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok,salvo com sucesso!", "Ok,salvo com sucesso!"));
            
       

    }

   
    public void conciliarDebito(Debito[] debitos) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        boolean b = false;
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO") 
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_3")) {
            if (debitos != null) {
                for (Debito d : debitos) {
                    if (d.getDTConciliacao() == null) {
                        d.setDTConciliacao(new Date(System.currentTimeMillis()));
                        d.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
                        d.setDTAlt(new Date(System.currentTimeMillis()));
                        getDebitoDAO().atualizar(d);
                        b = true;
                    }
                }
                if (b) {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Debito conciliado com sucesso!", "Debito conciliado com sucesso!"));
                }
            }
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha ,usuário não autorizado!", "Falha ,usuário não autorizado!"));
        }

    }

    public void cancelarConciliacao(Debito[] debitos, Conta conta) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Debito debito = getDebitoDAO().ultimoConciliado(conta);
        boolean b = false;
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO") 
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_3")) {
            if (debitos != null) {
                if (autorizaConciliar(debitos, debito)) {
                    for (Debito d : debitos) {
                        if (d.getDTConciliacao() != null) {
                            d.setDTConciliacao(null);
                            d.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
                            d.setDTAlt(new Date(System.currentTimeMillis()));
                            getDebitoDAO().atualizar(d);
                            b = true;
                        }
                    }
                    if (b) {
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "conciliacao de debito estornado com sucesso!", "conciliacao de debito estornado com sucesso!"));
                    }
                } else {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi possivel efetiar o extorno devido a conciliações posteriores!", "não foi possivel efetiar o extorno devido a conciliações posteriores!"));
                }

            }
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha ,usuário não autorizado!", "Falha ,usuário não autorizado!"));
        }
    }

    public boolean autorizaConciliar(Debito[] debitos, Debito debito) {
        boolean b = true;
        if (debito != null) {
            for (Debito d : debitos) {
                if (d.getDTConciliacao() != null) {
                    if (d.getDTConciliacao().before(debito.getDTConciliacao())) {
                        b = false;
                    }
                }
            }
        }
        return b;
    }
}
