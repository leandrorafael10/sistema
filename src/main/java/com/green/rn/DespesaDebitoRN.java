/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.DebitoDAO;
import com.green.dao.DespesaDAO;
import com.green.dao.DespesaDebitoDAO;
import com.green.modelo.Debito;
import com.green.modelo.Despesa;
import com.green.modelo.Despesadebito;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import java.math.BigDecimal;
import java.util.Date;
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
@Service("despesaDebitoRN")
public class DespesaDebitoRN {

    @Autowired
    private DespesaDebitoDAO despesaDebitoDAO;
    @Autowired
    private DespesaDAO despesaDAO;
    @Autowired
    private DebitoDAO debitoDAO;
    @Transactional("tmGreen")
    public void salvar(Despesadebito despesadebito, BigDecimal valorPago) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();
        Despesa d = getDespesaDAO().buscar(despesadebito.getIDDespesa().getIDDespesa());
        if (d.getAtzPg() == 2) {
            if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_2")) {

                Debito debito = new Debito();
                debito.setIDAtividade(despesadebito.getIDDespesa().getIDAtividade());
                debito.setIDCCusto(despesadebito.getIDDespesa().getIDCCusto());
                debito.setIDClassificacao(despesadebito.getIDDespesa().getIDClassificacao());
                debito.setIDUsuario(contextoBean.getUsuarioLogado());
                debito.setIDTpDocumento(despesadebito.getIDDebito().getIDTpDocumento());
                debito.setDTBaixa(despesadebito.getIDDebito().getDTBaixa());
                debito.setDTInc(new Date());
                debito.setValor(valorPago);
                despesadebito.getIDDespesa().setValorLiquido(valorPago);
                debito.setIDConta(despesadebito.getIDDebito().getIDConta());
                if (despesadebito.getIDDebito().getNumero() == null) {
                    debito.setNumero("1");
                } else {
                    debito.setNumero(despesadebito.getIDDebito().getNumero());
                }
                getDebitoDAO().salvar(debito);
                despesadebito.setIDDebito(debito);
                getDespesaDebitoDAO().salvar(despesadebito);
                despesadebito.getIDDespesa().setPago(true);
                despesadebito.getIDDespesa().setAtzPg(3);
                getDespesaDAO().atualizar(despesadebito.getIDDespesa());
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "pagamento efetuado com sucesso!", "pagamento efetuado com sucesso!"));
                context.update("for_listar_debito");
                context.execute("varDialogDespesaPagar.hide()");

            } else {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha,usuario não autorizado!", "Falha,usuario não autorizado!"));
                context.update("for_listar_debito");
                context.execute("varDialogDespesaPagar.hide()");
            }


        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este debito foi estornado por " + contextoBean.getUsuarioLogado().getIDFuncionario().getIDPessoa().getRazao()
                    + "No dia :" + d.getDTAlt(), "Este debito foi estornado por " + contextoBean.getUsuarioLogado().getIDFuncionario().getIDPessoa().getRazao()
                    + "No dia :" + d.getDTAlt()));
        }

    }

    public DespesaDebitoDAO getDespesaDebitoDAO() {
        return despesaDebitoDAO;
    }

    public void setDespesaDebitoDAO(DespesaDebitoDAO despesaDebitoDAO) {
        this.despesaDebitoDAO = despesaDebitoDAO;
    }

    public DebitoDAO getDebitoDAO() {
        return debitoDAO;
    }

    public void setDebitoDAO(DebitoDAO debitoDAO) {
        this.debitoDAO = debitoDAO;
    }

    public DespesaDAO getDespesaDAO() {
        return despesaDAO;
    }

    public void setDespesaDAO(DespesaDAO despesaDAO) {
        this.despesaDAO = despesaDAO;
    }
}
