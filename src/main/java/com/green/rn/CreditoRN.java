/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.CreditoDAO;
import com.green.modelo.Conta;
import com.green.modelo.Credito;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service("creditoRN")
public class CreditoRN {

    @Autowired
    private CreditoDAO creditoDAO;

    public CreditoDAO getCreditoDAO() {
        return creditoDAO;
    }

    public void setCreditoDAO(CreditoDAO creditoDAO) {
        this.creditoDAO = creditoDAO;
    }

    public List<Credito> listando() {
        return getCreditoDAO().listando();
    }
    @Transactional("tmGreen")
    public void salvar(Credito credito) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO") 
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_2")) {
            credito.setDTInc(new Date());
            credito.setIDUsuario(contextoBean.getUsuarioLogado());
            getCreditoDAO().salvar(credito);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok,salvo com sucesso!", "Ok,salvo com sucesso!"));
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha,usuário não autorizado!", "Falha,usuário não autorizado!"));
        }
    }
/*
    public List<Credito> filtroCredito(Credito credito, Date fimBaixa, Date fimConciliacao) {
        return getCreditoDAO().filtroCredito(credito, fimBaixa, fimConciliacao);
    }
*/
    public void conciliarCredito(Credito[] creditos) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        boolean b = false;
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO") 
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_3")) {
            if (creditos != null) {
                for (Credito c : creditos) {


                    if (c.getDTConciliacao() == null) {
                        c.setDTConciliacao(new Date(System.currentTimeMillis()));
                        c.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
                        c.setDTAlt(new Date(System.currentTimeMillis()));
                        getCreditoDAO().atualizar(c);
                        b = true;
                    }
                }
                if (b) {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " crédito conciliado com sucesso!", "crédito conciliado com sucesso!"));
                }
            }
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha,usuário não autorizado!", "Falha,usuário não autorizado!"));
        }

    }

    public void cancelarConciliacao(Credito[] creditos, Conta conta) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Credito credito = getCreditoDAO().ultimoConciliado(conta);
        boolean b = false;
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO") 
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_3")) {
            if (creditos != null) {
                if (autorizaConciliar(creditos, credito)) {
                    for (Credito c : creditos) {
                        if (c.getDTConciliacao() != null) {
                            c.setDTConciliacao(null);
                            getCreditoDAO().atualizar(c);
                            c.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
                            c.setDTAlt(new Date(System.currentTimeMillis()));
                            b = true;
                        }
                    }
                    if (b) {
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Conciliacao de crédito estornado com sucesso!", "Conciliacao de crédito estornadoCancelando conciliação de com sucesso!"));
                    }
                } else {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "não foi possivel efetiar o extorno devido a conciliações posteriores!", "não foi possivel efetiar o extorno devido a conciliações posteriores!"));
                }
            }
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha,usuário não autorizado!", "Falha,usuário não autorizado!"));
        }
    }

    public boolean autorizaConciliar(Credito[] creditos, Credito credito) {
        boolean b = true;
        if (credito != null) {
            for (Credito c : creditos) {
                if (c.getDTConciliacao() != null) {
                    if (c.getDTConciliacao().before(credito.getDTConciliacao())) {
                        b = false;
                    }
                }
            }
        }
        return b;
    }
}
