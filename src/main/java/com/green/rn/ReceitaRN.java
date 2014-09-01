/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.ContratoMidiaDAO;
import com.green.dao.ReceitaDAO;
import com.green.modelo.Cliente;
import com.green.modelo.Receita;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import com.green.view.ReceitaDataModelo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
@Service("receitaRN")
public class ReceitaRN {

    @Autowired
    private ReceitaDAO receitaDAO;
    @Autowired
    private ContratoMidiaDAO contratoMidiaDAO;

    public void setReceitaDAO(ReceitaDAO receitaDAO) {
        this.receitaDAO = receitaDAO;
    }

    public ReceitaDAO getReceitaDAO() {
        return receitaDAO;
    }

    public ContratoMidiaDAO getContratoMidiaDAO() {
        return contratoMidiaDAO;
    }

    public void setContratoMidiaDAO(ContratoMidiaDAO contratoMidiaDAO) {
        this.contratoMidiaDAO = contratoMidiaDAO;
    }

    /**
     * @author Leandro Metodo salvar de da regra de negocio de receita Somente
     * administração e financeiro tem autorização.
     *
     */
    public void salvar(Receita receita, int parcelas, int vencimento) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_2")) {
            receita.setDTInc(new Date(System.currentTimeMillis()));
            receita.setIDUsuario(contextoBean.getUsuarioLogado());
            receita.setValorLiquido(receita.getValorNominal());
            if (receita.getIDDocumento().getIDDocumento() == 2) {
                receita.setNumero("1");
            }
            if (parcelas > 1) {
                int cont = 0;
                Calendar nova = new GregorianCalendar();
                Date d = receita.getDTVencimento();
                String doc = receita.getNumero();
                if (vencimento != 2) {
                    for (int i = 0; parcelas > i; i++) {
                        Receita r = receita;
                        nova.setTime(d);
                        nova.add(Calendar.MONTH, i);
                        r.setNumero(doc + " / " + String.valueOf(i + 1));
                        r.setDTVencimento(nova.getTime());
                        getReceitaDAO().salvar(r);
                    }
                } else {
                    for (int i = 0; parcelas > i; i++) {
                        Receita r = receita;
                        nova.setTime(r.getDTVencimento());
                        nova.add(Calendar.DAY_OF_MONTH, cont);
                        r.setNumero(doc + " / " + String.valueOf(i + 1));
                        r.setDTVencimento(nova.getTime());
                        getReceitaDAO().salvar(r);
                        cont = 30;
                    }
                }

            } else if (parcelas == 0 || parcelas == 1) {
                getReceitaDAO().salvar(receita);
            }
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok! Salvo com sucesso!", "Ok! Salvo com sucesso!"));
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha! Usuario não autorizado!", "Falha! Usuario não autorizado!"));
        }

    }

    public List<Receita> listar() {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        List<Receita> receitas = new ArrayList<Receita>();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
            return getReceitaDAO().listar();
        } else {
            for (Receita receita : getReceitaDAO().listar()) {
                if (receita.getDel() == false) {
                    receitas.add(receita);
                }
            }
        }
        return receitas;
    }

    /**
     * Desativa a receita parcialmente não exclui definitivamente ,e sera
     * visualisada sua esclusão somente pelo administrador
     *
     * @param receita
     */
    public void excluirParcial(Receita receita) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext context = FacesContext.getCurrentInstance();

        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_2")) {
            getReceitaDAO().excluir(receita);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!", "Excluido com sucesso!"));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha!Usuario não autorizado!", "Falha,usuario não autorizado!"));
        }

    }

    public void autorizaPagamento(Receita[] receita, int aut) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        boolean b = false;
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
            if (receita != null) {

                for (Receita d : receita) {
                    int status = 0;
                }
                
            }
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha ,usuário não autorizado!", "Falha ,usuário não autorizado!"));
        }

    }

    public List<Receita> vencimentosDoDia() {
        return getReceitaDAO().vencimentosDoDia();
    }

    @Transactional("tmGreen")
    public void atualizar(Receita receita) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        receita.setDTAlt(new Date(System.currentTimeMillis()));
        receita.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
        getReceitaDAO().atualizar(receita);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Atualizado com sucesso!"));
    }

    public void atualizarLiquido(Receita receita) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_2")) {
            receita.setDTAlt(new Date(System.currentTimeMillis()));
            receita.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
            getReceitaDAO().atualizar(receita);

            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Atualizado com sucesso!"));
            context.execute("dialogEditValor.hide()");
            context.update("formEditValor");
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha,usuario não autorizado!", "Falha,usuario não autorizado!"));
            context.execute("dialogEditValor.hide()");
            context.update("formEditValor");
        }
    }

    public ReceitaDataModelo liberaPagamento() {
        ReceitaDataModelo dataModelo = new ReceitaDataModelo(getReceitaDAO().liberacaoPagamento());
        return dataModelo;
    }

    public ReceitaDataModelo faseDePagamento() {
        ReceitaDataModelo dataModelo = new ReceitaDataModelo(getReceitaDAO().pendentesPagamento());
        return dataModelo;
    }

    public Receita carregar(Integer codigo) {
        return getReceitaDAO().carregar(codigo);
    }

    public List<Receita> receitaVencidas() {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        List<Receita> receitas = new ArrayList<Receita>();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
            return getReceitaDAO().receitasVencidas();
        } else {
            for (Receita receita : getReceitaDAO().receitasVencidas()) {
                if (receita.getDel() == false) {
                    receitas.add(receita);
                }
            }
        }
        return receitas;
    }

    public List<Receita> receitasAvencer() {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        List<Receita> receitas = new ArrayList<Receita>();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
            return getReceitaDAO().receitasAvencer();
        } else {
            for (Receita receita : getReceitaDAO().receitasAvencer()) {
                if (receita.getDel() == false) {
                    receitas.add(receita);
                }
            }
        }
        return receitas;
    }

    /**
     * filtro para tabela de receitas
     *
     * @param receitaFiltro dados iniciais do filtro
     * @param fimVenc parametro final de data vencimento(Date)
     * @param fimCancel parametro final de data cancelamento(Date)
     * @param fimEmissao parametro final de data emiçao(Date)
     * @return
     */
    public List<Receita> filtro(Receita receitaFiltro, Date fimVenc, int pg, BigDecimal valorIni, BigDecimal valorFim) {

        List<Receita> receitas;
        receitas = getReceitaDAO().filtro(receitaFiltro, fimVenc, pg, valorIni, valorFim);
        return receitas;

    }

    public List<Receita> parcelasCliente(Date inicio, Date fim, Cliente c, boolean tipo) {
        return getReceitaDAO().parcelasCliente(inicio, fim, c, tipo);
    }

    public List<Receita> despesasPagas() {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        List<Receita> receitas;
        receitas = getReceitaDAO().receitasPagas();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
            return receitas;
        } else {
            List<Receita> ds = new ArrayList<Receita>();
            for (Receita receita : receitas) {
                if (receita.getDel() == false) {
                    ds.add(receita);
                }
            }
            receitas = ds;
        }
        return receitas;
    }

    public List<Receita> despesasApagar() {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        List<Receita> receitas;
        receitas = getReceitaDAO().receitasApagar();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
            return receitas;
        } else {
            List<Receita> ds = new ArrayList<Receita>();
            for (Receita receita : receitas) {
                if (receita.getDel() == false) {
                    ds.add(receita);
                }
            }
            receitas = ds;
        }
        return receitas;
    }

    public List<Receita> pendentesPagamento() {
        return getReceitaDAO().pendentesPagamento();
    }

    public List<Receita> pendentesPagamentoAteHoje() {
        return getReceitaDAO().pendentesPagamentoAteHoje();
    }

    public List<Receita> pagamentoProximoPendenteCliente() {
        return getReceitaDAO().pagamentoProximoPendenteCliente();
    }
    public List<Receita> pagamentoPendenteParceiro() {
        return getReceitaDAO().pagamentoPendenteParceiro();
    }
    public List<Receita> pagamentoPendenteContraApresentacao() {
        return getReceitaDAO().pagamentoPendenteContraApresentacao();
    }
}
