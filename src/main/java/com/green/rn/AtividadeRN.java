/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.AtividadeDAO;
import com.green.modelo.Atividade;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("atividadeRN")
public class AtividadeRN {

    @Autowired
    private AtividadeDAO atividadeDAO;
    
    

    public AtividadeDAO getAtividadeDAO() {
        return atividadeDAO;
    }

    public void setAtividadeDAO(AtividadeDAO atividadeDAO) {
        this.atividadeDAO = atividadeDAO;
    }

    public List<Atividade> listar() {
        return getAtividadeDAO().listar();
    }

    public Atividade carregar(Integer pk) {
        return getAtividadeDAO().carregar(pk);
    }

    public void salvar(Atividade atividade) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
    FacesContext facesContext = FacesContext.getCurrentInstance();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
            atividade.setIDUsuario(contextoBean.getUsuarioLogado());
            atividade.setDTInc(new Date(System.currentTimeMillis()));
            getAtividadeDAO().salvar(atividade);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso", "Salvo com sucesso! "));
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falha,usuario não autorizado!", "Falha!"));
        }
    }

    public void excluir(Atividade atividade) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
    FacesContext facesContext = FacesContext.getCurrentInstance();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO") 
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
            getAtividadeDAO().excluir(atividade);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão efetuada com sucesso.", "Exclusão efetuada com sucesso."));
        }else{
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuario não autorizado!", "Falha!Usuario não autorizado!"));
        }
    }
    public void atualizar(Atividade atividade){
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO") 
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
            atividade.setDTAlt(new Date(System.currentTimeMillis()));
            atividade.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
            getAtividadeDAO().atualizar(atividade);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Ok! Atualizado com sucesso"));
        }else{
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario não autorizado!", "Falha !Usuario não autorizado"));
        }
    }
}
