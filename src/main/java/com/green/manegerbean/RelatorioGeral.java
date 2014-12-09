/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.green.modelo.Capalotejornal;

import com.green.modelo.Cliente;
import com.green.modelo.ContratoMidia;
import com.green.modelo.Despesa;
import com.green.modelo.Pessoa;
import com.green.modelo.Praca;
import com.green.modelo.Receita;
import com.green.modelo.Receitacredito;
import com.green.rn.CapaLoteJornalRN;
import com.green.rn.ContratoMidiaRN;
import com.green.rn.ContratoParceiroRN;
import com.green.rn.DespesaRN;
import com.green.rn.ReceitaCreditoRN;
import com.green.rn.ReceitaRN;
import com.green.rn.TipoPagamentoRN;
import com.green.util.ContextoUtil;
import com.green.util.ReceitaPraca;
import com.green.util.RelatorioUtil;

/**
 * 
 * @author leandro.silva
 */
@ManagedBean(name = "relatorioGeral")
@ViewScoped
public class RelatorioGeral implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{contratoMidiaRN}")
	private ContratoMidiaRN contratoMidiaRN;
	@ManagedProperty("#{receitaRN}")
	private ReceitaRN receitaRN;
	@ManagedProperty("#{despesaRN}")
	private DespesaRN despesaRN;
	@ManagedProperty("#{tipoPagamentoRN}")
	TipoPagamentoRN tipoPagamentoRN;
	@ManagedProperty("#{receitaCreditoRN}")
	private ReceitaCreditoRN receitacreditoRN;
	@ManagedProperty("#{capaLoteJornalRN}")
	private CapaLoteJornalRN capaLoteJornalRN;
	@ManagedProperty("#{contratoParceiroRN}")
	private ContratoParceiroRN contratoParceiroRN;
	private ContratoMidia contratoMidia = new ContratoMidia();
	private Praca praca;
	private Receita receita = new Receita();
	private Despesa despesa = new Despesa();
	private Date dataInicio;
	private Date dataFim;
	private int mes;
	private int ano;
	private int status;
	private boolean tipo;

	public RelatorioGeral() {
		this.receita.setIDCliente(new Cliente());
		this.receita.getIDCliente().setIDPessoa(new Pessoa());
		this.status = 5;
	}

	@PostConstruct
	private void init() {
		this.praca = new Praca();
	}

	public String GeraRelatorios(int codigo) {
		switch (codigo) {
		// todas parcelas á receber
		case 1: {
			Receita r = new Receita();
			r.setDTVencimento(getDataInicio());
			List<Receita> receitas = getReceitaRN().filtro(r, dataFim, 0,
					new BigDecimal("0.00"), new BigDecimal("0.00"));
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", "Relatório parcelas");
			JRDataSource jrds = new JRBeanCollectionDataSource(receitas);
			RelatorioUtil.geraRelatorioBean("parcelas_a_receber", jrds,
					parametros);
		}
			break;
		// parcelas recebidas em receita credito
		// tem de ser feito alteraÃ§oes
		case 2: {
			/*
			 * Receita r = new Receita(); r.setDTVencimento(getDataInicio());
			 * HashMap<String, Object> parametros = new HashMap<>();
			 * parametros.put("Usuario",
			 * ContextoUtil.getContextoBean().getUsuarioLogado
			 * ().getIDFuncionario().getIDPessoa().getRazao());
			 * parametros.put("DataInicio", getDataInicio());
			 * parametros.put("DataFim", getDataFim());
			 * 
			 * JRDataSource jrds = new
			 * JRBeanCollectionDataSource(getReceitacreditoRN().filtro(r,
			 * dataFim, 1, new BigDecimal("0.00"), new BigDecimal("0.00")));
			 * RelatorioUtil.geraRelatorioBean("parcelas_recebidas", jrds,
			 * parametros);
			 */
			Receita r = new Receita();
			r.setDTVencimento(getDataInicio());
			List<Receitacredito> receitacredito = getReceitacreditoRN()
					.listaCreditoClienterPorPeriodo(getDataInicio(), dataFim);
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("dt_inicio", getDataInicio());
			parametros.put("dt_fim", getDataFim());

			JRDataSource jrds = new JRBeanCollectionDataSource(receitacredito);
			RelatorioUtil.geraRelatorioBean("credito_pago", jrds, parametros);
		}
			break;
		// Contas a receber em aberto
		case 3: {
			
			List<Receita> receitas = getReceitaRN().contaReceberEmAberto(getDataInicio(), getDataFim());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", "Parcelas a receber em aberto");
			JRDataSource jrds = new JRBeanCollectionDataSource(receitas);
			RelatorioUtil.geraRelatorioBean("parcelas_a_receber", jrds,
					parametros);
		}
			break;
		// parcelas a receber parceiro
		case 4: {
			getReceita().setDTVencimento(getDataInicio());
			List<Receita> receitas = getReceitaRN().filtro(getReceita(),
					getDataFim(), 2, new BigDecimal("0.00"),
					new BigDecimal("0.00"));
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", "Parcelas a receber parceiro de ");
			JRDataSource jrds = new JRBeanCollectionDataSource(receitas);
			RelatorioUtil.geraRelatorioBean("rel_parceiro", jrds, parametros);
		}
			break;
		// parcelas a acertar com o parceiro
		case 5: {
			/*
			getDespesa().setDTVencimento(getDataInicio());
			List<Despesa> despesas = getDespesaRN().filtro(getDespesa(),
					getDataFim(), 2, new BigDecimal("0.00"),
					new BigDecimal("0.00"));
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", "Parcelas a acertar com");
			JRDataSource jrds = new JRBeanCollectionDataSource(despesas);
			RelatorioUtil.geraRelatorioBean("rel_parceiro", jrds, parametros);
			*/
		}
			break;
		// parcelas vencidas não pagas
		case 6: {
			Receita r = new Receita();
			r.setDTVencimento(getDataInicio());
			List<Receita> receitas = getReceitaRN().pendentesPagamentoAteHoje();
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataFim", new Date());
			parametros.put("Subtitulo", " Todas contas a receber em atrazo");
			JRDataSource jrds = new JRBeanCollectionDataSource(receitas);
			RelatorioUtil.geraRelatorioBean("parcelas_a_receber", jrds,
					parametros);
		}
			break;
		// vendas por periodo
		case 7: {

			List<ContratoMidia> contratos = getContratoMidiaRN()
					.buscaPorVendedor(null, getDataInicio(), getDataFim());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", " Contratos por periodo");
			JRDataSource jrds = new JRBeanCollectionDataSource(contratos);
			RelatorioUtil.geraRelatorioBean("rel_vendas", jrds, parametros);
		}
			break;
		// vendas por vendedor
		case 8: {
			List<ContratoMidia> contratos = getContratoMidiaRN()
					.buscaPorVendedor(getContratoMidia().getIDvendedor(),
							getDataInicio(), getDataFim());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", " Contratos por periodo");
			JRDataSource jrds = new JRBeanCollectionDataSource(contratos);
			RelatorioUtil.geraRelatorioBean("rel_vendas", jrds, parametros);
		}
			break;
		//
		// parcelas periodo e cliente
		case 9: {
			JRDataSource jrds = new JRBeanCollectionDataSource(getReceitaRN()
					.parcelasCliente(dataInicio, dataFim,
							this.receita.getIDCliente()));
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("dt_inicio", getDataInicio());
			parametros.put("dt_fim", getDataFim());
			parametros.put("cliente", receita.getIDCliente().getIDPessoa()
					.getRazao());
			RelatorioUtil.geraRelatorioBean("relatorio_cliente", jrds,
					parametros);
		}
			break;
		// busca contratos por periodo e tipo
		case 10: {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("dataInicio", getDataInicio());
			parametros.put("dataFim", getDataFim());
			parametros.put("titulo", " Contratos TV Embarque");
			parametros.put("tipo", (getStatus() == 1 ? "ativo"
					: (getStatus() == 0 ? "cancelado" : "todos")));
			JRDataSource jrds = new JRBeanCollectionDataSource(
					getContratoMidiaRN().buscaPorPeriodoTipo(dataInicio,
							dataFim, status));
			RelatorioUtil.geraRelatorioBean("contratos_periodo_tipo", jrds,
					parametros);
		}
			break;

		// busca contratos por periodo e tipo parceiro
		case 11: {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("dataInicio", getDataInicio());
			parametros.put("dataFim", getDataFim());
			parametros.put("parceiro", getReceita().getIDCliente()
					.getIDPessoa().getFantasia());
			parametros.put("titulo", " CONTRATOS PARCEIRO ");
			parametros.put("tipo", (getStatus() == 1 ? "ativo"
					: (getStatus() == 0 ? "cancelado" : "todos")));
			JRDataSource jrds = new JRBeanCollectionDataSource(
					getContratoParceiroRN().buscaPorPeriodoTipo_parceiro(
							dataInicio, dataFim, status,
							getReceita().getIDCliente()));
			RelatorioUtil.geraRelatorioBean("contratos_periodo_tipo_parceiro",
					jrds, parametros);
		}
			break;
		//
		case 12:
			break;
		// contratos sob permulta
		case 13: {

			List<ContratoMidia> contratos = getContratoMidiaRN()
					.buscaPorFormaPag(
							this.tipoPagamentoRN.carregar(new Integer(3)),
							getDataInicio(), getDataFim());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", " Contratos de premulta por periodo");
			JRDataSource jrds = new JRBeanCollectionDataSource(contratos);
			RelatorioUtil.geraRelatorioBean("rel_vendas", jrds, parametros);
		}
			break;
		// contratos bonificados
		case 14: {

			List<ContratoMidia> contratos = getContratoMidiaRN()
					.buscaPorFormaPag(
							this.tipoPagamentoRN.carregar(new Integer(5)),
							getDataInicio(), getDataFim());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", " Contratos de premulta por periodo");
			JRDataSource jrds = new JRBeanCollectionDataSource(contratos);
			RelatorioUtil.geraRelatorioBean("rel_vendas", jrds, parametros);
		}
			break;
		// comissao por vendedor
		case 15: {
			Receita r = new Receita();
			r.setDTVencimento(getDataInicio());
			List<Receitacredito> receitas_pagas = getReceitacreditoRN()
					.listaReceitaPorVendedor(dataInicio, dataFim,
							contratoMidia.getIDvendedor());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("data_inicio", getDataInicio());
			parametros.put("data_fim", getDataFim());
			parametros.put("percent", getReceita().getValorLiquido());
			parametros.put("vendedor", contratoMidia.getIDvendedor()
					.getIDPessoa().getRazao());
			JRDataSource jrds = new JRBeanCollectionDataSource(receitas_pagas);
			RelatorioUtil.geraRelatorioBean("comissao_vendedor", jrds,
					parametros);
		}
			break;
		//
		case 16:
			break;

		}
		return "";
	}

	
	public String relatorio(String opc) {
		switch (opc) {
		// todas parcelas á receber
		case "1": {
			
			List<Receita> receitas = getReceitaRN().todasReceitaPorPeriodo(getDataInicio(), getDataFim());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", "TODAS CONTAS");
			JRDataSource jrds = new JRBeanCollectionDataSource(receitas);
			RelatorioUtil.geraRelatorioBean("parcelas_a_receber", jrds,
					parametros);
		}
			break;
		// parcelas recebidas em receita credito
		// tem de ser feito alteraçoes
		case "2": {
			/*
			 * Receita r = new Receita(); r.setDTVencimento(getDataInicio());
			 * HashMap<String, Object> parametros = new HashMap<>();
			 * parametros.put("Usuario",
			 * ContextoUtil.getContextoBean().getUsuarioLogado
			 * ().getIDFuncionario().getIDPessoa().getRazao());
			 * parametros.put("DataInicio", getDataInicio());
			 * parametros.put("DataFim", getDataFim());
			 * 
			 * JRDataSource jrds = new
			 * JRBeanCollectionDataSource(getReceitacreditoRN().filtro(r,
			 * dataFim, 1, new BigDecimal("0.00"), new BigDecimal("0.00")));
			 * RelatorioUtil.geraRelatorioBean("parcelas_recebidas", jrds,
			 * parametros);
			 */
			Receita r = new Receita();
			r.setDTVencimento(getDataInicio());
			List<Receitacredito> receitacredito = getReceitacreditoRN()
					.listaCreditoClienterPorPeriodo(getDataInicio(), dataFim);
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("dt_inicio", getDataInicio());
			parametros.put("dt_fim", getDataFim());

			JRDataSource jrds = new JRBeanCollectionDataSource(receitacredito);
			RelatorioUtil.geraRelatorioBean("credito_pago", jrds, parametros);
		}
			break;
		// Contas a receber em aberto
		case "3": {
		
			List<Receita> receitas = getReceitaRN().contaReceberEmAberto(getDataInicio(), getDataFim());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", "CONTAS A RECEBER EM ABERTO");
			JRDataSource jrds = new JRBeanCollectionDataSource(receitas);
			RelatorioUtil.geraRelatorioBean("parcelas_a_receber", jrds,
					parametros);
		}
			break;
		// parcelas a receber parceiro
		case "4": {
			getReceita().setDTVencimento(getDataInicio());
			List<Receita> receitas = getReceitaRN().filtro(getReceita(),
					getDataFim(), 2, new BigDecimal("0.00"),
					new BigDecimal("0.00"));
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", "Parcelas a receber parceiro de ");
			JRDataSource jrds = new JRBeanCollectionDataSource(receitas);
			RelatorioUtil.geraRelatorioBean("rel_parceiro", jrds, parametros);
		}
			break;
		// parcelas a acertar com o parceiro
		case "5": {
			/*
			getDespesa().setDTVencimento(getDataInicio());
			List<Despesa> despesas = getDespesaRN().filtro(getDespesa(),
					getDataFim(), 2, new BigDecimal("0.00"),
					new BigDecimal("0.00"));
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", "Parcelas a acertar com");
			JRDataSource jrds = new JRBeanCollectionDataSource(despesas);
			RelatorioUtil.geraRelatorioBean("rel_parceiro", jrds, parametros);
			*/
		}
			break;
		// parcelas vencidas não pagas
		case "6": {
			Receita r = new Receita();
			r.setDTVencimento(getDataInicio());
			List<Receita> receitas = getReceitaRN().pendentesPagamentoAteHoje();
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataFim", new Date());
			parametros.put("Subtitulo", " TOTAS AS CONTAS EM ATRASO");
			JRDataSource jrds = new JRBeanCollectionDataSource(receitas);
			RelatorioUtil.geraRelatorioBean("parcelas_a_receber", jrds,
					parametros);
		}
			break;
		// vendas por periodo
		case "7": {

			List<ContratoMidia> contratos = getContratoMidiaRN()
					.buscaPorVendedor(null, getDataInicio(), getDataFim());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", " Contratos por periodo");
			JRDataSource jrds = new JRBeanCollectionDataSource(contratos);
			RelatorioUtil.geraRelatorioBean("rel_vendas", jrds, parametros);
		}
			break;
		// vendas por vendedor
		case "8": {
			List<ContratoMidia> contratos = getContratoMidiaRN()
					.buscaPorVendedor(getContratoMidia().getIDvendedor(),
							getDataInicio(), getDataFim());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", " Contratos por periodo");
			JRDataSource jrds = new JRBeanCollectionDataSource(contratos);
			RelatorioUtil.geraRelatorioBean("rel_vendas", jrds, parametros);
		}
			break;
		//
		// parcelas periodo e cliente
		case "9": {
			JRDataSource jrds = new JRBeanCollectionDataSource(getReceitaRN()
					.parcelasCliente(dataInicio, dataFim,
							this.receita.getIDCliente()));
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("dt_inicio", getDataInicio());
			parametros.put("dt_fim", getDataFim());
			parametros.put("cliente", receita.getIDCliente().getIDPessoa()
					.getRazao());
			RelatorioUtil.geraRelatorioBean("relatorio_cliente", jrds,
					parametros);
		}
			break;
		// busca contratos por periodo e tipo
		case "10": {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("dataInicio", getDataInicio());
			parametros.put("dataFim", getDataFim());
			parametros.put("titulo", " Contratos TV Embarque");
			parametros.put("tipo", (getStatus() == 1 ? "ativo"
					: (getStatus() == 0 ? "cancelado" : "todos")));
			JRDataSource jrds = new JRBeanCollectionDataSource(
					getContratoMidiaRN().buscaPorPeriodoTipo(dataInicio,
							dataFim, status));
			RelatorioUtil.geraRelatorioBean("contratos_periodo_tipo", jrds,
					parametros);
		}
			break;

		// busca contratos por periodo e tipo parceiro
		case "11": {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("dataInicio", getDataInicio());
			parametros.put("dataFim", getDataFim());
			parametros.put("parceiro", getReceita().getIDCliente()
					.getIDPessoa().getFantasia());
			parametros.put("titulo", " CONTRATOS PARCEIRO ");
			parametros.put("tipo", (getStatus() == 1 ? "ativo"
					: (getStatus() == 0 ? "cancelado" : "todos")));
			JRDataSource jrds = new JRBeanCollectionDataSource(
					getContratoParceiroRN().buscaPorPeriodoTipo_parceiro(
							dataInicio, dataFim, status,
							getReceita().getIDCliente()));
			RelatorioUtil.geraRelatorioBean("contratos_periodo_tipo_parceiro",
					jrds, parametros);
		}
			break;
		//
		case "12":
			break;
		// contratos sob permulta
		case "13": {

			List<ContratoMidia> contratos = getContratoMidiaRN()
					.buscaPorFormaPag(
							this.tipoPagamentoRN.carregar(new Integer(3)),
							getDataInicio(), getDataFim());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", " Contratos de premulta por periodo");
			JRDataSource jrds = new JRBeanCollectionDataSource(contratos);
			RelatorioUtil.geraRelatorioBean("rel_vendas", jrds, parametros);
		}
			break;
		// contratos bonificados
		case "14": {

			List<ContratoMidia> contratos = getContratoMidiaRN()
					.buscaPorFormaPag(
							this.tipoPagamentoRN.carregar(new Integer(5)),
							getDataInicio(), getDataFim());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("DataInicio", getDataInicio());
			parametros.put("DataFim", getDataFim());
			parametros.put("Subtitulo", " Contratos de premulta por periodo");
			JRDataSource jrds = new JRBeanCollectionDataSource(contratos);
			RelatorioUtil.geraRelatorioBean("rel_vendas", jrds, parametros);
		}
			break;
		// comissao por vendedor
		case "15": {
			Receita r = new Receita();
			r.setDTVencimento(getDataInicio());
			List<Receitacredito> receitas_pagas = getReceitacreditoRN()
					.listaReceitaPorVendedor(dataInicio, dataFim,
							contratoMidia.getIDvendedor());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("data_inicio", getDataInicio());
			parametros.put("data_fim", getDataFim());
			parametros.put("percent", getReceita().getValorLiquido());
			parametros.put("vendedor", contratoMidia.getIDvendedor()
					.getIDPessoa().getRazao());
			JRDataSource jrds = new JRBeanCollectionDataSource(receitas_pagas);
			RelatorioUtil.geraRelatorioBean("comissao_vendedor", jrds,
					parametros);
		}
			break;
		// faturamento por praca e periodo
		case "16": {
			Calendar periodo = Calendar.getInstance();
			periodo.set(Calendar.MONTH, getMes());
			periodo.set(Calendar.YEAR, getAno());
			List<ReceitaPraca> receitaPracas = getContratoMidiaRN().receitaPorPracaValorMensal(
					getPraca(), periodo);
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("mes",String.valueOf(getMes()+1));
			parametros.put("ano",String.valueOf(getAno()));
			parametros.put("praca",getPraca().getDescricao());
			parametros.put("usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			
			JRDataSource jrds = new JRBeanCollectionDataSource(receitaPracas);
			RelatorioUtil.geraRelatorioBean("receita_praca", jrds,
					parametros);
		}
			break;

		}
		return "";

	}

	public String geraRelatorioJornal(int opcao) {
		switch (opcao) {
		// lista de vendas por periodo
		case 1:

			List<Capalotejornal> vendas = getCapaLoteJornalRN()
					.listarPorPeriodo(getDataInicio(), getDataFim(),
							getStatus());
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", ContextoUtil.getContextoBean()
					.getUsuarioLogado().getIDFuncionario().getIDPessoa()
					.getRazao());
			parametros.put("periodo", getDataInicio());
			parametros.put("status",
					getStatus() == 0 ? "cancelado"
							: (getStatus() == 1 ? "ativo"
									: (getStatus() == 2 ? "pendente"
											: (getStatus() == 3 ? "agendado"
													: "todos"))));
			parametros.put("Subtitulo", " Contratos de vendas por periodo");
			JRDataSource jrds = new JRBeanCollectionDataSource(vendas);
			RelatorioUtil.geraRelatorioBean("venda_jornal_vend", jrds,
					parametros);
			return "";

			

		
		}
		return "";

	}

	public ContratoMidiaRN getContratoMidiaRN() {
		return contratoMidiaRN;
	}

	public void setContratoMidiaRN(ContratoMidiaRN contratoMidiaRN) {
		this.contratoMidiaRN = contratoMidiaRN;
	}

	public ReceitaRN getReceitaRN() {
		return receitaRN;
	}

	public void setReceitaRN(ReceitaRN receitaRN) {
		this.receitaRN = receitaRN;
	}

	public ContratoMidia getContratoMidia() {
		return contratoMidia;
	}

	public void setContratoMidia(ContratoMidia contratoMidia) {
		this.contratoMidia = contratoMidia;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public ReceitaCreditoRN getReceitacreditoRN() {
		return receitacreditoRN;
	}

	public void setReceitacreditoRN(ReceitaCreditoRN receitacreditoRN) {
		this.receitacreditoRN = receitacreditoRN;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public DespesaRN getDespesaRN() {
		return despesaRN;
	}

	public void setDespesaRN(DespesaRN despesaRN) {
		this.despesaRN = despesaRN;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public TipoPagamentoRN getTipoPagamentoRN() {
		return tipoPagamentoRN;
	}

	public void setTipoPagamentoRN(TipoPagamentoRN tipoPagamentoRN) {
		this.tipoPagamentoRN = tipoPagamentoRN;
	}

	public CapaLoteJornalRN getCapaLoteJornalRN() {
		return capaLoteJornalRN;
	}

	public void setCapaLoteJornalRN(CapaLoteJornalRN capaLoteJornalRN) {
		this.capaLoteJornalRN = capaLoteJornalRN;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean getTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public ContratoParceiroRN getContratoParceiroRN() {
		return contratoParceiroRN;
	}

	public void setContratoParceiroRN(ContratoParceiroRN contratoParceiroRN) {
		this.contratoParceiroRN = contratoParceiroRN;
	}

	public Praca getPraca() {
		return praca;
	}

	public void setPraca(Praca praca) {
		this.praca = praca;
	}
	
	

}
