/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.ClassificacaoDAO;
import com.green.modelo.Classificacao;
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
@Service("classificacaoRN")
public class ClassificacaoRN {

    @Autowired
    private ClassificacaoDAO classificacaoDAO;

    public ClassificacaoDAO getClassificacaoDAO() {
        return classificacaoDAO;
    }

    public void setClassificacaoDAO(ClassificacaoDAO classificacaoDAO) {
        this.classificacaoDAO = classificacaoDAO;
    }

    @Transactional("tmGreen")
    public void salvar(Classificacao classificacao) {
        RequestContext context = RequestContext.getCurrentInstance();
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesMessage msg;
        Classificacao c = getClassificacaoDAO().carregarCodigoEstrututal(classificacao.getCodigo());
        if (c == null) {
            if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
                classificacao.setDTInc(new Date(System.currentTimeMillis()));
                classificacao.setIDUsuario(contextoBean.getUsuarioLogado());
                getClassificacaoDAO().salvar(classificacao);
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok! Salvo com sucesso!", "Ok! Salvo com sucesso!");
                context.update("formPlanoContas");
                context.execute("dialogPlanoContas.hide()");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha! Usuario não autorizado!", "Falha! Usuario não autorizado!");
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha!Código estrutural já cadastrado!", "Falha! Código estrutural já cadastrado!");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public List<Classificacao> listar() {
        return getClassificacaoDAO().listar();
    }

    @Transactional("tmGreen")
    public void excluir(Classificacao classificacao) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesMessage msg;
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
            getClassificacaoDAO().excluir(classificacao);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha! Usuario não autorizado!", "Falha! Usuario não autorizado!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public Classificacao carregar(Integer codigo) {
        return getClassificacaoDAO().carregar(codigo);
    }

    @Transactional("tmGreen")
    public void atualizar(Classificacao classificacao) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesMessage msg;
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO") ||
                contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
            classificacao.setDtalt(new Date(System.currentTimeMillis()));
            classificacao.setIdusuarioAlt(contextoBean.getUsuarioLogado());
            getClassificacaoDAO().atualizar(classificacao);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok! Atualizado com sucesso!", "Ok! Atualizado com sucesso!");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha! Usuario não autorizado!", "Falha! Usuario não autorizado!");
        }

    }

    public List<Classificacao> filtroClassificacao(String codigo, String descricao) {
        return getClassificacaoDAO().filtroClassificacao(codigo, descricao);
    }
}
