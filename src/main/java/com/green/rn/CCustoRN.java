/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.CCustoDAO;
import com.green.modelo.Ccusto;
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
@Service("ccustoRN")
public class CCustoRN {

    @Autowired
    private CCustoDAO custoDAO;

    public List<Ccusto> listar() {
        return getCustoDAO().listar();
    }

    public void excluir(Ccusto ccusto) {
        getCustoDAO().excluir(ccusto);
    }

    public void salvar(Ccusto ccusto) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        Ccusto c = getCustoDAO().carregarCodigoEstrutural(ccusto.getCodigo());
        FacesMessage message;
        if (c == null) {
            if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO") 
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                    ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
                ccusto.setIDUsuario(contextoBean.getUsuarioLogado());
                ccusto.setDTInc(new Date(System.currentTimeMillis()));
                getCustoDAO().salvar(ccusto);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok! Salvo com sucesso!", "Ok! Salvo com sucesso!");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha! Usuario não autorizado!", "Falha! Usuario não autorizado!");
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha!Código estrutural já cadastrado!", "Falha! Código estrutural já cadastrado!");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);


    }

    public Ccusto carregar(Integer integer) {
        return getCustoDAO().carregar(integer);
    }

    public void atualizar(Ccusto ccustoEditado, Ccusto ccusto) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesMessage message = null;
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                ||contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
            ccustoEditado.setDTInc(ccusto.getDTInc());
            ccustoEditado.setIDUsuario(ccusto.getIDUsuario());
            ccustoEditado.setIDCCusto(ccusto.getIDCCusto());
            ccustoEditado.setDTalt(new Date(System.currentTimeMillis()));
            ccustoEditado.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
            getCustoDAO().atualizar(ccustoEditado);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok! Atualizado com sucesso!", "Ok! Atualizado com sucesso!");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha! Usuario não autorizado!", "Falha! Usuario não autorizado!");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public CCustoDAO getCustoDAO() {
        return custoDAO;
    }

    public void setCustoDAO(CCustoDAO custoDAO) {
        this.custoDAO = custoDAO;
    }
}
