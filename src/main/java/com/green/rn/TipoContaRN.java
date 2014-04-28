/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.TipoContaDAO;
import com.green.modelo.Tipoconta;
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
@Service("tipoContaRN")
public class TipoContaRN {

    @Autowired
    private TipoContaDAO tipoContaDAO;

    public TipoContaDAO getTipoContaDAO() {
        return tipoContaDAO;
    }

    public void setTipoContaDAO(TipoContaDAO tipoContaDAO) {
        this.tipoContaDAO = tipoContaDAO;
    }

    public List<Tipoconta> listar() {
        return getTipoContaDAO().listar();
    }

    public void salvar(Tipoconta tipoconta) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message;
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_2")) {
            tipoconta.setDTInc(new Date(System.currentTimeMillis()));
            tipoconta.setIDUsuario(contextoBean.getUsuarioLogado());
            getTipoContaDAO().salvar(tipoconta);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok!Salvo com sucesso!", "Ok!Salvo com sucesso!");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha,usuario não autorizado!", "Falha,usuario não autorizado!");
        }
        context.addMessage(null, message);
    }

    public Tipoconta carregar(Integer codigo) {
        return getTipoContaDAO().carregar(codigo);
    }

    public void excluir(Tipoconta tipoconta) {
        getTipoContaDAO().excluir(tipoconta);
    }

    public void atualizar(Tipoconta tipoconta) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message;
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO") 
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_2")) {
            tipoconta.setDTAlt(new Date(System.currentTimeMillis()));
            tipoconta.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
            getTipoContaDAO().atualizar(tipoconta);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok!Salvo com sucesso!", "Ok!Salvo com sucesso!");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha !Usuario não autorizado!", "Falha !Usuario não autorizado!");
        }
    }
}
