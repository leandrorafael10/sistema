/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.DespesaDAO;
import com.green.modelo.Despesa;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import com.green.view.DespesaDataModelo;
import java.math.BigDecimal;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("despesaRN")
public class DespesaRN {

    @Autowired
    private DespesaDAO despesaDAO;

    public List<Despesa> listando() {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        List<Despesa> despesas;
        despesas = getDespesaDAO().listando();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
            return despesas;
        } else {
            List<Despesa> ds = new ArrayList<Despesa>();
            for (Despesa despesa : despesas) {
                if (despesa.getDel() == false) {
                    ds.add(despesa);
                }
            }
            despesas = ds;
        }
        return despesas;
    }

    public Despesa buscar(Integer codigo) {
        return getDespesaDAO().buscar(codigo);
    }

    public void excluirParcial(Despesa despesa) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext context = FacesContext.getCurrentInstance();

        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
            despesa.setDel(true);
            getDespesaDAO().excluir(despesa);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!", "Excluido com sucesso!"));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha!Usuario não autorizado!", "Falha,usuario não autorizado!"));
        }

    }

    public void atualizar(Despesa despesa) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
            despesa.setIDUsuario(contextoBean.getUsuarioLogado());
            despesa.setDTAlt(new Date(System.currentTimeMillis()));
            despesa.setValorLiquido(despesa.getValorNominal());
            getDespesaDAO().atualizar(despesa);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Atualizado com sucesso!"));
            context.execute("varDialogDespesaEdit.hide()");
            context.update("forDespesas");
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha,usuario não autorizado!", "Falha,usuario não autorizado!"));
            context.execute("varDialogDespesaEdit.hide()");
            context.update("forDespesas");
        }
    }

    public void salvar(Despesa despesa, int parcelas, int vencimento) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
            despesa.setIDUsuario(contextoBean.getUsuarioLogado());
            despesa.setDTInc(new Date(System.currentTimeMillis()));
            despesa.setValorLiquido(despesa.getValorNominal());
            despesa.setAtzPg(0);
            if (despesa.getIDDocumento().getIDDocumento() == 2) {
                despesa.setNumero("1");
            }
            if (parcelas > 1) {
                int cont = 0;
                Calendar nova = new GregorianCalendar();
                Date d = despesa.getDTVencimento();
                String doc = despesa.getNumero();
                if (vencimento != 2) {
                    for (int i = 0; parcelas > i; i++) {
                        Despesa des = despesa;
                        nova.setTime(d);
                        nova.add(Calendar.MONTH, i);
                        des.setNumero(doc + " / " + String.valueOf(i + 1));
                        des.setDTVencimento(nova.getTime());
                        getDespesaDAO().salvar(des);
                    }
                } else {
                    for (int i = 0; parcelas > i; i++) {
                        Despesa des = despesa;
                        nova.setTime(des.getDTVencimento());
                        nova.add(Calendar.DAY_OF_MONTH, cont);
                        des.setNumero(doc + " / " + String.valueOf(i + 1));
                        des.setDTVencimento(nova.getTime());
                        getDespesaDAO().salvar(des);
                        cont = 30;
                    }
                }

            } else if (parcelas == 0 || parcelas == 1) {
                getDespesaDAO().salvar(despesa);
            }
            mensagensDespesa(FacesMessage.SEVERITY_INFO, "Ok,Salvo com sucesso!");
        } else {
            mensagensDespesa(FacesMessage.SEVERITY_ERROR, "Falha ,usuario não autorizado!");
        }

    }

    public List<Despesa> filtro(Despesa despesaFiltro, Date fimVenc, int pg, BigDecimal valorIni, BigDecimal valorFim) {

        List<Despesa> despesas;
        despesas = getDespesaDAO().filtro(despesaFiltro, fimVenc, pg, valorIni, valorFim);

        return despesas;

    }

    public List<Despesa> despesaVencidas() {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        List<Despesa> despesas;
        despesas = getDespesaDAO().despesasVencidas();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
            return despesas;
        } else {
            List<Despesa> ds = new ArrayList<Despesa>();
            for (Despesa despesa : despesas) {
                if (despesa.getDel() == false) {
                    ds.add(despesa);
                }
            }
            despesas = ds;
        }
        return despesas;
    }

    public List<Despesa> despesasAvencer() {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        List<Despesa> despesas;
        despesas = getDespesaDAO().despesasAvencer();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
            return despesas;
        } else {
            List<Despesa> ds = new ArrayList<Despesa>();
            for (Despesa despesa : despesas) {
                if (despesa.getDel() == false) {
                    ds.add(despesa);
                }
            }
            despesas = ds;
        }
        return despesas;
    }

    public List<Despesa> despesasPagas() {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        List<Despesa> despesas;
        despesas = getDespesaDAO().despesasPagas();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
            return despesas;
        } else {
            List<Despesa> ds = new ArrayList<Despesa>();
            for (Despesa despesa : despesas) {
                if (despesa.getDel() == false) {
                    ds.add(despesa);
                }
            }
            despesas = ds;
        }
        return despesas;
    }

    public List<Despesa> despesasApagar() {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        List<Despesa> despesas;
        despesas = getDespesaDAO().despesasApagar();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
            return despesas;
        } else {
            List<Despesa> ds = new ArrayList<Despesa>();
            for (Despesa despesa : despesas) {
                if (despesa.getDel() == false) {
                    ds.add(despesa);
                }
            }
            despesas = ds;
        }
        return despesas;
    }

    public List<Despesa> vencimentosDoDia() {

        return getDespesaDAO().vencimentosDoDia();
    }

    public List<Despesa> pendentesPagamento() {
        return getDespesaDAO().pendentesPagamento();
    }

    public DespesaDataModelo faseDePagamento() {
        DespesaDataModelo dataModelo = new DespesaDataModelo(getDespesaDAO().pendentesPagamento());
        return dataModelo;
    }

    public DespesaDataModelo liberaPagamento() {
        DespesaDataModelo dataModelo = new DespesaDataModelo(getDespesaDAO().liberacaoPagamento());
        return dataModelo;
    }

    public void excluir(Despesa despesa) {
        getDespesaDAO().excluir(despesa);
    }

    public void autorizaPagamento(Despesa[] despesa, int aut) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        boolean b = false;
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
            if (despesa != null) {
                for (Despesa d : despesa) {
                    if (aut == 1 && d.getAtzPg() != 0) {
                    } else {
                        d.setAtzPg(aut);
                        d.setDtAtz(new Date(System.currentTimeMillis()));
                        d.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
                        d.setDTAlt(new Date(System.currentTimeMillis()));
                        getDespesaDAO().atualizar(d);
                        b = true;
                    }

                }
                if (b) {
                    if (aut == 0) {
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Desapesa(s) extornadas(s) com sucesso!", "Desapesa(s) extornadas(s) com sucesso!"));
                    }
                    if (aut == 1) {
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Desapesa(s) autorizada(s) com sucesso!", "Desapesa(s) autorizada(s) com sucesso!"));
                    }
                    if (aut == 2) {
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Desapesa(s) liberadas para pagamento(s) com sucesso!", "Desapesa(s) liberadas para pagamento(s) com sucesso!"));
                    }

                }
            }
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha ,usuário não autorizado!", "Falha ,usuário não autorizado!"));
        }

    }

    public DespesaDAO getDespesaDAO() {
        return despesaDAO;
    }

    public void setDespesaDAO(DespesaDAO despesaDAO) {
        this.despesaDAO = despesaDAO;
    }

    private void mensagensDespesa(FacesMessage.Severity severity, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(severity, msg, msg);
        context.addMessage(null, message);
    }
}
