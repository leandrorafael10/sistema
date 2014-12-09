/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.dao.ContratoMidiaDAO;
import com.green.dao.ReceitaDAO;
import com.green.modelo.Cliente;
import com.green.modelo.Receita;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import com.green.view.ReceitaDataModelo;

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
	 *         administração e financeiro tem autorização.
	 * 
	 */
	public void salvar(Receita receita) {
		receita.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
		receita.setDTInc(new Date());
		receita.setValorLiquido(receita.getValorNominal());
		getReceitaDAO().salvar(receita);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok! Salvo com sucesso!", "Ok! Salvo com sucesso!"));

	}

	public List<Receita> listar() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		List<Receita> receitas = new ArrayList<Receita>();
		if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
			return getReceitaDAO().listar();
		} else {
			for (Receita receita : getReceitaDAO().listar()) {
				if (receita.getAtivo() == false) {
					receitas.add(receita);
				}
			}
		}
		return receitas;
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

	public void cancelar_parcela(Receita receita) {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		receita.setDTAlt(new Date(System.currentTimeMillis()));
		receita.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
		receita.setAtivo(!receita.getAtivo());
		getReceitaDAO().atualizar(receita);
		if (receita.getAtivo()) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ativado com sucesso!", "Ativado com sucesso!"));
		} else {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado com sucesso!", "Cancelado com sucesso!"));
		}
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
				if (receita.getAtivo() == false) {
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
				if (receita.getAtivo() == false) {
					receitas.add(receita);
				}
			}
		}
		return receitas;
	}

	/**
	 * filtro para tabela de receitas
	 * 
	 * @param receitaFiltro
	 *            dados iniciais do filtro
	 * @param fimVenc
	 *            parametro final de data vencimento(Date)
	 * @param pg
	 * @param valorIni
	 * @param valorFim
	 * @return
	 */
	public List<Receita> filtro(Receita receitaFiltro, Date fimVenc, int pg, BigDecimal valorIni, BigDecimal valorFim) {

		List<Receita> receitas;
		receitas = getReceitaDAO().filtro(receitaFiltro, fimVenc, pg, valorIni, valorFim);
		return receitas;

	}

	public List<Receita> todasReceitaPorPeriodo(Date inicio, Date fim) {
		return receitaDAO.todasReceitaPorPeriodo(inicio, fim);
	}

	public List<Receita> receitaPorPeriodo(Date inicio, Date fim, boolean ativo) {
		return getReceitaDAO().receitaPorPeriodo(inicio, fim, ativo);
	}

	public List<Receita> parcelasCliente(Date inicio, Date fim, Cliente c) {
		return getReceitaDAO().parcelasCliente(inicio, fim, c);
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
				if (receita.getAtivo() == false) {
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
				if (receita.getAtivo() == false) {
					ds.add(receita);
				}
			}
			receitas = ds;
		}
		return receitas;
	}

	public List<Receita> contaReceberEmAberto(Date inicio, Date fim) {
		return getReceitaDAO().contaReceberEmAberto(inicio, fim);
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

	public List<Receita> pagamentoProximoPendenteClienteFlitro(Date inicio, Date fim) {
		return getReceitaDAO().pagamentoProximoPendenteClienteFlitro(inicio, fim);
	}

	public List<Receita> pagamentoPendenteParceiroFiltro(Date inicio, Date fim) {
		return getReceitaDAO().pagamentoPendenteParceiroFiltro(inicio, fim);
	}

	public List<Receita> pagamentoPendenteContraApresentacaoFiltro(Date inicio, Date fim) {
		return getReceitaDAO().pagamentoPendenteContraApresentacaoFiltro(inicio, fim);
	}
}
