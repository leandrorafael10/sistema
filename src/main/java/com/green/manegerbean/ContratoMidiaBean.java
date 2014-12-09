/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.green.modelo.Cliente;
import com.green.modelo.Contato;
import com.green.modelo.ContratoMidia;
import com.green.modelo.Funcionario;
import com.green.modelo.Mailing;
import com.green.modelo.ObsContrato;
import com.green.modelo.Painel;
import com.green.modelo.Pessoa;
import com.green.modelo.Praca;
import com.green.modelo.Receita;
import com.green.modelo.TaxaParceiro;
import com.green.rn.ClienteRN;
import com.green.rn.ContatoRN;
import com.green.rn.ContratoMidiaRN;
import com.green.rn.MailingRN;
import com.green.rn.ObsContratoRN;
import com.green.rn.PessoaRN;
import com.green.util.CepWebService;
import com.green.util.ContextoUtil;

/**
 * 
 * @author leandro.silva
 */
@SuppressWarnings("serial")
@ManagedBean(name = "contratoMidiaBean")
@ViewScoped
public class ContratoMidiaBean implements Serializable {

	@ManagedProperty("#{pessoaRN}")
	private PessoaRN pessoaRN;
	@ManagedProperty("#{contratoMidiaRN}")
	private ContratoMidiaRN contratoMidiaRN;
	@ManagedProperty("#{contatoRN}")
	private ContatoRN contatoRN;
	@ManagedProperty("#{mailingRN}")
	private MailingRN mailingRN;
	@ManagedProperty("#{clienteRN}")
	private ClienteRN clienteRN;
	@ManagedProperty("#{obsContratoRN}")
	private ObsContratoRN obsContratoRN;
	private Cliente cliente = new Cliente();
	private Contato contato = new Contato();
	private ContratoMidia contratoMidia = new ContratoMidia();
	private List<ContratoMidia> contratoMidias;
	private List<Funcionario> vendedores;
	private List<ContratoMidia> selectContratos;
	private List<ContratoMidia> contratosVencidos;
	private List<ContratoMidia> contratoFiltro;
	private List<Praca> pracasParceiro = new ArrayList<>();
	private List<ObsContrato> listaDeObs = new ArrayList<>();
	private boolean resp = false;
	private String telefoneF = new String();
	private String telefoneC = new String();
	private String telefoneR = new String();
	private String telefoneCel = new String();
	private String Valor = new String();
	private Date dataInicioParcelas;
	private Date atualizaDataInc;
	private Date atualizaDataFin;
	private Integer ativoAtualizar;
	private ContratoMidia contratoC = new ContratoMidia();
	private TaxaParceiro taxaParceiro = new TaxaParceiro();
	private boolean producao;
	private boolean tipo = true;
	private boolean tipoCont = true;
	private String cpfCnpj = new String();
	private String razao = new String();
	private Mailing mailing;
	private ObsContrato obsContrato = new ObsContrato();
	private Funcionario vendedor = new Funcionario();
	private BigDecimal receitaLiquida = new BigDecimal("0.00");
	private BigDecimal repasse = new BigDecimal("0.00");
	private BigDecimal comissaoVendResp = new BigDecimal("0.00");
	private BigDecimal impostoResp = new BigDecimal("0.00");
	private BigDecimal bvResp = new BigDecimal("0.00");
	private BigDecimal despesaSoma = new BigDecimal("0.00");
	private List<Painel> listaPainel;

	@PostConstruct
	public void init() {
		recolheMeiling();
		this.contratoC.setPorcAgencia(new BigDecimal("0.00"));
		this.contratoMidia.setPorcAgencia(new BigDecimal("0.00"));
		this.contratoC.setIDcliente(new Cliente());
		this.contratoC.getIDcliente().setIDPessoa(new Pessoa());
		this.taxaParceiro.setBv(new BigDecimal("0.00"));
		this.taxaParceiro.setComissao(new BigDecimal("0.00"));
		this.taxaParceiro.setIss(new BigDecimal("0.00"));
		this.contratoMidia.setIDcliente(new Cliente());
		this.contratoMidia.getIDcliente().setIDPessoa(new Pessoa());
		this.contratoMidia.getIDcliente().getIDPessoa().setFisicaJuridica(false);

	}

	public void onLoad() {
		this.contratoFiltro = getContratoMidiaRN().listar();
	}

	public void liberaPainel() {
		if (contratoMidia.getIDproduto().getIdprodutoMidia() == 7) {
			resp = true;
		} else {
			resp = false;
		}
	}

	public void confirmar(ActionEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("formConfCadastro");
		context.execute("PF('dialogConfirmContrato').show()");
	}

	public void excluir(ActionEvent event) {
		getContratoMidiaRN().excluir(getContratoMidia());
		setCliente(getClienteRN().carregar(getContratoMidia().getIDcliente().getIDCliente()));
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com sucesso!", "Excluido com sucesso!"));
	}

	public void cancelar(ActionEvent event) {
		getContratoMidiaRN().cancelar(getContratoMidia());
	}

	public void ativar(ActionEvent event) {
		getContratoMidiaRN().ativar(getContratoMidia());
	}

	public void buscaObsContrato(ContratoMidia midia) {
		setContratoMidia(midia);
		setListaDeObs(getObsContratoRN().buscarPorContrato(midia));

	}

	public void buscaContratosCliente(SelectEvent event) {
		Cliente cliente = (Cliente) event.getObject();
		this.setContratoFiltro(getContratoMidiaRN().contratosDoCliente(cliente));

	}

	public List<ContratoMidia> completeContrato(String query) {
		List<ContratoMidia> contratos = getContratoMidiaRN().listar();
		List<ContratoMidia> midias = new ArrayList<ContratoMidia>();

		for (int i = 0; i < contratos.size(); i++) {
			ContratoMidia contrato = contratos.get(i);
			if (contrato.getIDcliente().getIDPessoa().getRazao().toLowerCase().startsWith(query.toLowerCase())) {
				midias.add(contrato);
			}
		}

		return midias;
	}

	public void buscarRazaoLike(ActionEvent event) {
		this.contratoFiltro = getContratoMidiaRN().buscarRazaoLike(getRazao());
	}

	public void contratosDoCliente() {
		setSelectContratos(getContratoMidiaRN().contratosDoCliente(this.cliente));
	}

	/*
	 * public void calculoParceiro(boolean tipoCont, ContratoMidia cm) {
	 * RequestContext context = RequestContext.getCurrentInstance();
	 * Collection<String> updates = new ArrayList<>();
	 * setComissaoAg(cm.getValor().divide(new BigDecimal(100))
	 * .multiply(cm.getPorcAgencia()));
	 * setReceitaLiquida(cm.getValor().subtract(getComissaoAg()));
	 * 
	 * if (tipoCont) { setLucro(getReceitaLiquida()); if (cm.getBrutoLiq()) {
	 * setRepasse(getReceitaLiquida().divide(new BigDecimal(2))); } else {
	 * setRepasse(cm.getValor().divide(new BigDecimal(2))); }
	 * 
	 * updates.add("formInsereContrato"); context.update(updates);
	 * context.execute("PF('dialogInsereContrato').show()"); } else {
	 * setBvResp(getReceitaLiquida().divide(new BigDecimal(100)).multiply(
	 * getTaxaParceiro().getBv()));
	 * setComissaoVendResp(getReceitaLiquida().divide(new BigDecimal(100))
	 * .multiply(getTaxaParceiro().getComissao()));
	 * setImpostoResp(getReceitaLiquida().divide(new BigDecimal(100))
	 * .multiply(getTaxaParceiro().getIss())); despesaSoma = getBvResp().add(
	 * getComissaoVendResp().add(getImpostoResp()));
	 * setLucro(getReceitaLiquida().subtract(getDespesaSoma()));
	 * updates.add("formInsereContratoSaida"); context.update(updates);
	 * context.execute("PF('dialogInsereContratoSaida').show()"); } //
	 * updates.add("formEntrada");
	 * 
	 * }
	 */
	public void salvarContratoParceito(ActionEvent event) {
		getContratoMidiaRN().salvarParceiro(getContratoMidia(), getRepasse(), getPracasParceiro(), this.producao);
		setContratoMidia(new ContratoMidia());
		setRepasse(BigDecimal.ZERO);
		setPracasParceiro(new ArrayList<Praca>());
	}

	/*
	 * public void salvarContratoParceitoSaida(ActionEvent event) {
	 * 
	 * getContratoMidiaRN().salvarParceiroSaida(getContratoC(), getLucro(),
	 * getPracasParceiro(), getTaxaParceiro(), this.producao, true,
	 * getContratoMidia().getIDcliente()); setContratoC(new ContratoMidia());
	 * getContratoC().setIDcliente(new Cliente());
	 * getContratoC().getIDcliente().setIDPessoa(new Pessoa());
	 * getContratoC().getIDcliente().getIDPessoa().setFisicaJuridica(true);
	 * setLucro(new BigDecimal(BigInteger.ZERO)); setPracasParceiro(new
	 * ArrayList<Praca>()); setTaxaParceiro(new TaxaParceiro());
	 * setContratoMidia(new ContratoMidia()); setTelefoneC("");
	 * setTelefoneCel(""); setTelefoneF(""); setTelefoneR(""); this.tipoCont =
	 * true; this.tipo = true;
	 * 
	 * }
	 */

	public void onRowEdit(RowEditEvent event) {
		Receita r = (Receita) event.getObject();
		getContratoMidiaRN().atualizaParcela(r);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "atualizado", "atualizado"));
	}

	public void onRowCancel(RowEditEvent event) {

	}

	public void excluirParcela(Receita r) {
		getContratoMidiaRN().excluirParcela(r);
		if (getContratoMidia().getOrigen().getReceitas().remove(r)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "excluído com sucesso!", "excluído com sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "falha ao excluir!", "falha ao excluir!"));
		}
	}

	public void consultar(ActionEvent event) {
		Pessoa p;
		p = getPessoaRN().buscaCpfCnpj(getContratoC().getIDcliente().getIDPessoa().getCnpjCpf());
		RequestContext context = RequestContext.getCurrentInstance();
		if (p != null) {
			getContratoC().getIDcliente().setIDPessoa(p);
			this.tipo = true;
			this.tipoCont = false;
			Contato c;
			setTelefoneC("");
			setTelefoneCel("");
			setTelefoneF("");
			setTelefoneR("");
			c = getContatoRN().buscaPorContato(p);

			if (c != null) {
				setContato(c);
				setTelefoneC(c.getDddc() + c.getTelefoneC());
				setTelefoneCel(c.getDDDCel() + c.getTelefoneCel());
				setTelefoneF(c.getDddf() + c.getTelefoneF());
				setTelefoneR(c.getDddr() + c.getTelefoneR());
			}
			context.update("formPrin");
		} else {
			this.tipoCont = false;
			this.tipo = false;
			context.update("formPrin");
		}

	}

	public void consultarOutro(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		this.tipo = true;
		this.tipoCont = true;
		this.contratoC.setIDcliente(new Cliente());
		this.contratoC.getIDcliente().setIDPessoa(new Pessoa());
		setContato(new Contato());
		context.update("formPrin");
	}

	// data daqui a dez dias
	public Date dataAtualMaisDezDias() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, +10);
		return calendar.getTime();
	}

	public int diasFaltando(Date data) {
		Date d = new Date();
		long result;
		result = data.getTime() - d.getTime();
		return (int) ((((result / 1000) / 60) / 60) / 24) + 1;

	}

	public List<SelectItem> getDias() {
		List<SelectItem> dias = new ArrayList<>();
		dias.add(new SelectItem("1"));
		dias.add(new SelectItem("2"));
		dias.add(new SelectItem("3"));
		dias.add(new SelectItem("4"));
		dias.add(new SelectItem("5"));
		dias.add(new SelectItem("6"));
		dias.add(new SelectItem("7"));
		dias.add(new SelectItem("8"));
		dias.add(new SelectItem("9"));
		dias.add(new SelectItem("10"));
		dias.add(new SelectItem("11"));
		dias.add(new SelectItem("12"));
		dias.add(new SelectItem("13"));
		dias.add(new SelectItem("14"));
		dias.add(new SelectItem("15"));
		dias.add(new SelectItem("16"));
		dias.add(new SelectItem("17"));
		dias.add(new SelectItem("18"));
		dias.add(new SelectItem("19"));
		dias.add(new SelectItem("20"));
		dias.add(new SelectItem("21"));
		dias.add(new SelectItem("22"));
		dias.add(new SelectItem("23"));
		dias.add(new SelectItem("24"));
		dias.add(new SelectItem("25"));
		dias.add(new SelectItem("26"));
		dias.add(new SelectItem("27"));
		dias.add(new SelectItem("28"));
		dias.add(new SelectItem("29"));
		dias.add(new SelectItem("30"));
		return dias;
	}

	/*
	 * pega o objeto de outra pagina e de outro managedBeam em seta para essa
	 * sessão
	 */
	public void recolheMeiling() {

		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("meuObjetoId")) {
			// Recebe o ID informado
			Integer id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("meuObjetoId").toString());

			// Busca o objeto e preenche os valores na pagina
			this.mailing = getMailingRN().buscar(id);

			// Remove o atributo da sessão para utilizar novamente.
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("meuObjetoId");
		}

	}

	/*
	 * faz a busca antes de de cadastrar o contrato para não cadastrar os dados
	 * pessoais em pessoa duas vezes
	 */
	public void buscaCpfCnpj() {
		Pessoa p;
		String cpf = this.contratoMidia.getIDcliente().getIDPessoa().getCnpjCpf();
		boolean fj = this.contratoMidia.getIDcliente().getIDPessoa().getFisicaJuridica();
		this.contratoMidia = new ContratoMidia();
		this.contratoMidia.setIDcliente(new Cliente());
		this.contratoMidia.getIDcliente().setIDPessoa(new Pessoa());
		this.contratoMidia.getIDcliente().getIDPessoa().setCnpjCpf(cpf);
		this.contratoMidia.getIDcliente().getIDPessoa().setFisicaJuridica(fj);
		p = getPessoaRN().buscaCpfCnpj(this.contratoMidia.getIDcliente().getIDPessoa().getCnpjCpf());
		if (p != null) {
			Contato c;
			setTelefoneC("");
			setTelefoneCel("");
			setTelefoneF("");
			setTelefoneR("");
			c = getContatoRN().buscaPorContato(p);
			if (c != null) {
				setTelefoneC(c.getDddc() + c.getTelefoneC());
				setTelefoneCel(c.getDDDCel() + c.getTelefoneCel());
				setTelefoneF(c.getDddf() + c.getTelefoneF());
				setTelefoneR(c.getDddr() + c.getTelefoneR());
			}
			Cliente cli = this.clienteRN.clienteBuscaPorPessoa(p);
			if (cli != null) {
				this.contratoMidia.setIDcliente(cli);
			} else {
				getContratoMidia().getIDcliente().setIDPessoa(p);
			}
		} else {
			setTelefoneC("");
			setTelefoneCel("");
			setTelefoneF("");
			setTelefoneR("");
		}
	}

	public void limpar() {
		boolean fj = this.contratoMidia.getIDcliente().getIDPessoa().getFisicaJuridica();
		this.contratoMidia = new ContratoMidia();
		this.contratoMidia.setIDcliente(new Cliente());
		this.contratoMidia.getIDcliente().setIDPessoa(new Pessoa());
		this.contratoMidia.getIDcliente().getIDPessoa().setFisicaJuridica(fj);
		setTelefoneC("");
		setTelefoneCel("");
		setTelefoneF("");
		setTelefoneR("");
		setResp(false);
	}

	public String salvarContrato() {
		formatatel(getTelefoneF(), getTelefoneCel(), getTelefoneR(), getTelefoneC());
		setResp(getContratoMidiaRN().salvar(getContratoMidia(), getContato(), null, getMailing(), this.tipo, getProducao(), true));

		return "/principal/cadastro_cliente";

	}

	public void cadastrarNovo(ActionEvent event) {

		if (getContratoMidia().getIDcliente().getIDPessoa().getContatosList() == null) {
			getContratoMidia().getIDcliente().getIDPessoa().setContatosList(new ArrayList<Contato>());
			getContratoMidia().getIDcliente().getIDPessoa().getContatosList().add(formatatel(getTelefoneF(), getTelefoneCel(), getTelefoneR(), getTelefoneC()));
		}
		if(this.resp){
			getContratoMidiaRN().cadastrarNovo(getContratoMidia(), this.dataInicioParcelas, producao,getListaPainel());	
		}else{
			getContratoMidiaRN().cadastrarNovo(getContratoMidia(), this.dataInicioParcelas, producao,null);
		}
		
		
		limpar();

	}

	public void atualizar() {

		if (getAtivoAtualizar() != null) {
			getContratoMidia().setAtivo(getAtivoAtualizar());
		}

		if (getAtualizaDataFin() != null && getAtualizaDataInc() != null) {
			getContratoMidia().setDataInicioContrato(getAtualizaDataInc());
			getContratoMidia().setDataFimContrato(getAtualizaDataFin());
		}
		getContratoMidiaRN().atualizar(getContratoMidia());
		this.contratoMidia = new ContratoMidia();
		this.atualizaDataFin = null;
		this.atualizaDataInc = null;
	}

	public void editarContrato(ActionEvent event) {
		getContratoMidiaRN().atualizar(getContratoMidia());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Atualizado com sucesso!"));
	}

	public Contato formatatel(String fixo, String celular, String residencial, String comercial) {
		Contato con = new Contato();
		con.setDTInc(new Date());
		con.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());

		if (!fixo.equals("")) {
			con.setDddf(fixo.substring(1, 3));
			con.setTelefoneF(fixo.substring(4, 8) + fixo.substring(9));
		}
		if (!celular.equals("")) {
			con.setDDDCel(celular.substring(1, 3));
			con.setTelefoneCel(celular.substring(4, 8) + celular.substring(9));
		}
		if (!residencial.equals("")) {
			con.setDddf(residencial.substring(1, 3));
			con.setTelefoneF(residencial.substring(4, 8) + residencial.substring(9));
		}
		if (!comercial.equals("")) {
			con.setDddc(comercial.substring(1, 3));
			con.setTelefoneC(comercial.substring(4, 8) + comercial.substring(9));
		}
		return con;
	}

	public void buscaPorCep() {
		CepWebService cepWebService = new CepWebService(this.contratoMidia.getIDcliente().getIDPessoa().getCep());
		this.contratoMidia.getIDcliente().getIDPessoa().setLogradouro(cepWebService.getTipo_logradouro() + " " + cepWebService.getLogradouro());
		this.contratoMidia.getIDcliente().getIDPessoa().setCidade(cepWebService.getCidade());
		this.contratoMidia.getIDcliente().getIDPessoa().setEstado(cepWebService.getEstado());
		this.contratoMidia.getIDcliente().getIDPessoa().setBairro(cepWebService.getBairro());
	}

	public List<ContratoMidia> listarVencidos() {
		return getContratoMidiaRN().listVencidos();
	}

	public void renovarContrato(ActionEvent event) {
		getContratoMidiaRN().salvar(getContratoMidia(), getContato(), null, null, false, getProducao(), true);
		getContratoC().setAtivo(2);
		getContratoMidiaRN().atualizar(getContratoC());
		listarVencido();

	}

	public void addObs(ActionEvent event) {
		// RequestContext context = RequestContext.getCurrentInstance();
		getObsContrato().setIDFuncionario(ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario());
		getObsContrato().setDTObs(new Date());
		getObsContrato().setIDContratoMidia(getContratoMidia());
		getObsContratoRN().salvar(getObsContrato());
		buscaObsContrato(getContratoMidia());
		setObsContrato(new ObsContrato());
	}

	public void listarVencido() {
		setContratosVencidos(getContratoMidiaRN().listVencidos());
	}

	public String primeiraPalavra(String frase) {
		String palavra = "";
		for (int i = 0; i < frase.length(); i++) {
			if (frase.charAt(i) != ' ') {
				palavra = palavra + frase.charAt(i);
			} else {
				break;
			}
		}
		return palavra;
	}

	public void buscaPorVendedor(ActionEvent event) {
		setContratoMidias(getContratoMidiaRN().buscaPorVendedor(getVendedor(), getAtualizaDataInc(), getAtualizaDataFin()));
	}

	public boolean getProducao() {
		return producao;
	}

	public void setProducao(boolean producao) {
		this.producao = producao;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getValor() {
		return Valor;
	}

	public void setValor(String Valor) {
		this.Valor = Valor;
	}

	public ContratoMidia getContratoC() {
		return contratoC;
	}

	public void setContratoC(ContratoMidia contratoC) {
		this.contratoC = contratoC;
	}

	public PessoaRN getPessoaRN() {
		return pessoaRN;
	}

	public void setPessoaRN(PessoaRN pessoaRN) {
		this.pessoaRN = pessoaRN;
	}

	public ContatoRN getContatoRN() {
		return contatoRN;
	}

	public void setContatoRN(ContatoRN contatoRN) {
		this.contatoRN = contatoRN;
	}

	public Integer getAtivoAtualizar() {
		return ativoAtualizar;
	}

	public void setAtivoAtualizar(Integer ativoAtualizar) {
		this.ativoAtualizar = ativoAtualizar;
	}

	public Date getAtualizaDataFin() {
		return atualizaDataFin;
	}

	public void setAtualizaDataFin(Date atualizaDataFin) {
		this.atualizaDataFin = atualizaDataFin;
	}

	public Date getAtualizaDataInc() {
		return atualizaDataInc;
	}

	public void setAtualizaDataInc(Date atualizaDataInc) {
		this.atualizaDataInc = atualizaDataInc;
	}

	public String getTelefoneCel() {
		return telefoneCel;
	}

	public void setTelefoneCel(String telefoneCel) {
		this.telefoneCel = telefoneCel;
	}

	public String getTelefoneC() {
		return telefoneC;
	}

	public void setTelefoneC(String telefoneC) {
		this.telefoneC = telefoneC;
	}

	public String getTelefoneF() {
		return telefoneF;
	}

	public void setTelefoneF(String telefoneF) {
		this.telefoneF = telefoneF;
	}

	public String getTelefoneR() {
		return telefoneR;
	}

	public void setTelefoneR(String telefoneR) {
		this.telefoneR = telefoneR;
	}

	public boolean isResp() {
		return resp;
	}

	public void setResp(boolean resp) {
		this.resp = resp;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Funcionario> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Funcionario> vendedores) {
		this.vendedores = vendedores;
	}

	public ContratoMidia getContratoMidia() {
		return contratoMidia;
	}

	public void setContratoMidia(ContratoMidia contratoMidia) {
		this.contratoMidia = contratoMidia;
	}

	public List<ContratoMidia> getSelectContratos() {
		return selectContratos;
	}

	public void setSelectContratos(List<ContratoMidia> selectContratos) {
		this.selectContratos = selectContratos;
	}

	public List<ContratoMidia> getContratoMidias() {
		return contratoMidias;
	}

	public void setContratoMidias(List<ContratoMidia> contratoMidias) {
		this.contratoMidias = contratoMidias;
	}

	public ContratoMidiaRN getContratoMidiaRN() {
		return contratoMidiaRN;
	}

	public void setContratoMidiaRN(ContratoMidiaRN contratoMidiaRN) {
		this.contratoMidiaRN = contratoMidiaRN;
	}

	public Mailing getMailing() {
		return mailing;
	}

	public void setMailing(Mailing mailing) {
		this.mailing = mailing;
	}

	public MailingRN getMailingRN() {
		return mailingRN;
	}

	public void setMailingRN(MailingRN mailingRN) {
		this.mailingRN = mailingRN;
	}

	public ObsContratoRN getObsContratoRN() {
		return obsContratoRN;
	}

	public void setObsContratoRN(ObsContratoRN obsContratoRN) {
		this.obsContratoRN = obsContratoRN;
	}

	public ObsContrato getObsContrato() {
		return obsContrato;
	}

	public void setObsContrato(ObsContrato obsContrato) {
		this.obsContrato = obsContrato;
	}

	public Funcionario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Funcionario vendedor) {
		this.vendedor = vendedor;
	}

	public List<ContratoMidia> getContratosVencidos() {
		return contratosVencidos;
	}

	public void setContratosVencidos(List<ContratoMidia> contratosVencidos) {
		this.contratosVencidos = contratosVencidos;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public List<Praca> getPracasParceiro() {
		return pracasParceiro;
	}

	public void setPracasParceiro(List<Praca> pracasParceiro) {
		this.pracasParceiro = pracasParceiro;
	}

	public BigDecimal getReceitaLiquida() {
		return receitaLiquida;
	}

	public void setReceitaLiquida(BigDecimal receitaLiquida) {
		this.receitaLiquida = receitaLiquida;
	}

	public BigDecimal getRepasse() {
		return repasse;
	}

	public void setRepasse(BigDecimal repasse) {
		this.repasse = repasse;
	}

	public boolean isTipo() {
		return tipo;
	}

	public boolean isTipoCont() {
		return tipoCont;
	}

	public BigDecimal getComissaoVendResp() {
		return comissaoVendResp;
	}

	public void setComissaoVendResp(BigDecimal comissaoVendResp) {
		this.comissaoVendResp = comissaoVendResp;
	}

	public BigDecimal getImpostoResp() {
		return impostoResp;
	}

	public void setImpostoResp(BigDecimal impostoResp) {
		this.impostoResp = impostoResp;
	}

	public BigDecimal getBvResp() {
		return bvResp;
	}

	public void setBvResp(BigDecimal bvResp) {
		this.bvResp = bvResp;
	}

	public BigDecimal getDespesaSoma() {
		return despesaSoma;
	}

	public void setDespesaSoma(BigDecimal despesaSoma) {
		this.despesaSoma = despesaSoma;
	}

	public List<ObsContrato> getListaDeObs() {
		return listaDeObs;
	}

	public void setListaDeObs(List<ObsContrato> listaDeObs) {
		this.listaDeObs = listaDeObs;
	}

	public TaxaParceiro getTaxaParceiro() {
		return taxaParceiro;
	}

	public void setTaxaParceiro(TaxaParceiro taxaParceiro) {
		this.taxaParceiro = taxaParceiro;
	}

	public Date getDataInicioParcelas() {
		return dataInicioParcelas;
	}

	public void setDataInicioParcelas(Date dataInicioParcelas) {
		this.dataInicioParcelas = dataInicioParcelas;
	}

	public ClienteRN getClienteRN() {
		return clienteRN;
	}

	public void setClienteRN(ClienteRN clienteRN) {
		this.clienteRN = clienteRN;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ContratoMidia> getContratoFiltro() {

		return contratoFiltro;
	}

	public void setContratoFiltro(List<ContratoMidia> contratoFiltro) {
		this.contratoFiltro = contratoFiltro;
	}

	public List<Painel> getListaPainel() {
		return listaPainel;
	}

	public void setListaPainel(List<Painel> listaPainel) {
		this.listaPainel = listaPainel;
	}

}
