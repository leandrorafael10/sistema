/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.CreditoDAO;
import com.green.dao.ReceitaCreditoDAO;
import com.green.dao.ReceitaDAO;
import com.green.modelo.Receita;
import com.green.modelo.Receitacredito;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import java.math.BigDecimal;
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
@Service("receitaCreditoRN")
public class ReceitaCreditoRN {

    @Autowired
    private ReceitaCreditoDAO receitaCreditoDAO;
    @Autowired
    private ReceitaDAO receitaDAO;
    @Autowired
    private CreditoDAO creditoDAO;

    @Transactional("tmGreen")
    public void salvar(Receitacredito receitacredito, BigDecimal valor) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();
        Receita r = getReceitaDAO().carregar(receitacredito.getIDReceita().getIDReceita());
        if (r.getAtzPg() == 2) {
            if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_2")) {

                receitacredito.getIDCredito().setIDAtividade(receitacredito.getIDReceita().getIDAtividade());
                receitacredito.getIDCredito().setIDCCusto(receitacredito.getIDReceita().getIDCCusto());
                receitacredito.getIDCredito().setIDClassificacao(receitacredito.getIDReceita().getIDClassificacao());
                receitacredito.getIDCredito().setIDUsuario(contextoBean.getUsuarioLogado());
                receitacredito.getIDCredito().setIDTpDocumento(receitacredito.getIDReceita().getIDDocumento());
                receitacredito.getIDCredito().setDTInc(new Date());
                receitacredito.getIDCredito().setValor(receitacredito.getIDReceita().getValorLiquido());
                receitacredito.getIDCredito().setIDConta(receitacredito.getIDReceita().getIDConta());
                receitacredito.getIDCredito().setNumero(receitacredito.getIDReceita().getNumero());
                getCreditoDAO().salvar(receitacredito.getIDCredito());
                getReceitaCreditoDAO().salvar(receitacredito);
                receitacredito.getIDReceita().setPago(true);
                receitacredito.getIDReceita().setAtzPg(3);
                getReceitaDAO().atualizar(receitacredito.getIDReceita());
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "recebimento efetuado com sucesso!", "recebimento efetuado com sucesso!"));
                context.update("for_listar_credito");
                context.execute("varDialogReceitaPagar.hide()");

            } else {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha,usuario não autorizado!", "Falha,usuario não autorizado!"));
                context.update("for_listar_credito");
                context.execute("varDialogReceitaPagar.hide()");
            }

        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este debito foi estornado por " + contextoBean.getUsuarioLogado().getIDFuncionario().getIDPessoa().getRazao()
                    + "No dia :" + r.getDTAlt(), "Este debito foi estornado por " + contextoBean.getUsuarioLogado().getIDFuncionario().getIDPessoa().getRazao()
                    + "No dia :" + r.getDTAlt()));
        }

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
