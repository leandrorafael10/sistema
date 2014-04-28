/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.AtividadeDAO;
import com.green.dao.CCustoDAO;
import com.green.dao.ClassificacaoDAO;
import com.green.dao.ClienteDAO;
import com.green.dao.ContaDAO;
import com.green.dao.ContatoDAO;
import com.green.dao.ContratoMidiaDAO;
import com.green.dao.ContratoPracasDAO;
import com.green.dao.DespesaDAO;
import com.green.dao.DocumentoDAO;
import com.green.dao.FuncionarioDAO;
import com.green.dao.MailingDAO;
import com.green.dao.OrigemDAO;
import com.green.dao.PessoaDao;
import com.green.dao.ProducaoMidiaDAO;
import com.green.dao.ReceitaDAO;
import com.green.dao.TaxaParceiroDAO;
import com.green.dao.TipoPagamentoDAO;
import com.green.modelo.Cliente;
import com.green.modelo.Contato;
import com.green.modelo.ContratoMidia;
import com.green.modelo.ContratoPracas;
import com.green.modelo.Despesa;
import com.green.modelo.Funcionario;
import com.green.modelo.Mailing;
import com.green.modelo.Origem;
import com.green.modelo.Praca;
import com.green.modelo.ProducaoMidia;
import com.green.modelo.Receita;
import com.green.modelo.TaxaParceiro;
import com.green.modelo.Tipopagamento;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Service("contratoMidiaRN")
public class ContratoMidiaRN {

    @Autowired
    private ContratoMidiaDAO contratoMidiaDAO;
    @Autowired
    private ContatoDAO contatoDAO;
    @Autowired
    private PessoaDao pessoaDao;
    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private ContratoPracasDAO contratoPracasDAO;
    @Autowired
    private ReceitaDAO receitaDAO;
    @Autowired
    private DespesaDAO despesaDAO;
    @Autowired
    private AtividadeDAO atividadeDAO;
    @Autowired
    private DocumentoDAO documentoDAO;
    @Autowired
    private CCustoDAO custoDAO;
    @Autowired
    private ClassificacaoDAO classificacaoDAO;
    @Autowired
    private ContaDAO contaDAO;
    @Autowired
    private MailingDAO mailingDAO;
    @Autowired
    private OrigemDAO origemDAO;
    @Autowired
    private ProducaoMidiaDAO producaoMidiaDAO;
    @Autowired
    private TipoPagamentoDAO tipoPagamentoDAO;
    @Autowired
    private FuncionarioDAO funcionarioDAO;
    @Autowired
    private TaxaParceiroDAO taxaParceiroDAO;

    public ContratoPracasDAO getContratoPracasDAO() {
        return contratoPracasDAO;
    }

    public void setContratoPracasDAO(ContratoPracasDAO contratoPracasDAO) {
        this.contratoPracasDAO = contratoPracasDAO;
    }

    public ContratoMidiaDAO getContratoMidiaDAO() {
        return contratoMidiaDAO;
    }

    public void setContratoMidiaDAO(ContratoMidiaDAO contratoMidiaDAO) {
        this.contratoMidiaDAO = contratoMidiaDAO;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public ContatoDAO getContatoDAO() {
        return contatoDAO;
    }

    public void setContatoDAO(ContatoDAO contatoDAO) {
        this.contatoDAO = contatoDAO;
    }

    public PessoaDao getPessoaDao() {
        return pessoaDao;
    }

    public void setPessoaDao(PessoaDao pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    public ReceitaDAO getReceitaDAO() {
        return receitaDAO;
    }

    public void setReceitaDAO(ReceitaDAO receitaDAO) {
        this.receitaDAO = receitaDAO;
    }

    public DespesaDAO getDespesaDAO() {
        return despesaDAO;
    }

    public void setDespesaDAO(DespesaDAO despesaDAO) {
        this.despesaDAO = despesaDAO;
    }

    public TipoPagamentoDAO getTipoPagamentoDAO() {
        return tipoPagamentoDAO;
    }

    public TaxaParceiroDAO getTaxaParceiroDAO() {
        return taxaParceiroDAO;
    }

    public void setTaxaParceiroDAO(TaxaParceiroDAO taxaParceiroDAO) {
        this.taxaParceiroDAO = taxaParceiroDAO;
    }

    /*
     *funcão salva contrato insere as parcelas nas receitas da conta da empresa 
     * e envia o status inicial para a producão começar a divulgar o video.
     * só messa nessa funcão se tiver plenos conhecimentos do que esta fazendo
     */
    @Transactional("tmGreen")
    public boolean salvar(ContratoMidia contratoMidia, Contato contato, List<Praca> pracas, Mailing mailing, boolean tipo, boolean producao, boolean inicio) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        try {

            if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_PRODUCAO")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_COMERCIAL_TV_EXE")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_COMERCIAL_TV_DIR")) {
                Receita receitaContrato = new Receita();

                if (!tipo) {
                    if (contratoMidia.getIDcliente().getIDCliente() == null && contratoMidia.getIDcliente().getIDPessoa().getIDPessoa() == null) {
                        contratoMidia.getIDcliente().setAtivo(true);
                        contratoMidia.getIDcliente().setDTInc(new Date());
                        contratoMidia.getIDcliente().setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
                        getPessoaDao().salvar(contratoMidia.getIDcliente().getIDPessoa());
                        getClienteDAO().salvar(contratoMidia.getIDcliente());
                    } else if (contratoMidia.getIDcliente().getIDCliente() == null && contratoMidia.getIDcliente().getIDPessoa().getIDPessoa() != null) {
                        Cliente c = getClienteDAO().buscaPorPessoa(contratoMidia.getIDcliente().getIDPessoa());
                        if (c == null) {
                            contratoMidia.getIDcliente().setAtivo(true);
                            contratoMidia.getIDcliente().setDTInc(new Date());
                            contratoMidia.getIDcliente().setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
                            getClienteDAO().salvar(contratoMidia.getIDcliente());
                        } else {
                            contratoMidia.setIDcliente(c);
                        }

                    }
                    if (contratoMidia.getIDtipopagamento().getDescricao().equals("Bonificacão") || contratoMidia.getIDtipopagamento().getDescricao().equals("Permuta")) {
                        if (contratoMidia.getIDtipopagamento().getDescricao().equals("Bonificacão")) {
                            contratoMidia.setValor(BigDecimal.ZERO);
                        }
                        contratoMidia.setAtivo(new Integer(0));
                        contratoMidia.setDTinc(new Date(System.currentTimeMillis()));
                        contratoMidia.setIDusuario(contextoBean.getUsuarioLogado());
                        getContratoMidiaDAO().salvarContrato(contratoMidia);
                        ProducaoMidia midia = new ProducaoMidia();
                        midia.setIDContratoMidia(contratoMidia);
                        
                    } else {

                        receitaContrato.setValorNominal(contratoMidia.getValor());
                        contratoMidia.setValor(contratoMidia.getValor().multiply(new BigDecimal(contratoMidia.getNumeroParcelas())));
                        contratoMidia.setAtivo(new Integer(0));
                        contratoMidia.setDTinc(new Date(System.currentTimeMillis()));
                        contratoMidia.setIDusuario(contextoBean.getUsuarioLogado());
                        getContratoMidiaDAO().salvarContrato(contratoMidia);
                        Origem origem = new Origem();
                        origem.setIDContratoMidia(contratoMidia);
                        this.origemDAO.salvar(origem);
                        receitaContrato.setIDAtividade(this.atividadeDAO.carregar(1));
                        receitaContrato.setIDDocumento(this.documentoDAO.carregar(19));
                        receitaContrato.setIDCCusto(this.custoDAO.carregar(1));
                        receitaContrato.setIDClassificacao(this.classificacaoDAO.carregar(2));
                        receitaContrato.setIDCliente(contratoMidia.getIDcliente());
                        receitaContrato.setIDConta(this.contaDAO.carregar(1));
                        receitaContrato.setTipoJuros(Boolean.FALSE);
                        receitaContrato.setIDUsuario(contextoBean.getUsuarioLogado());
                        receitaContrato.setIdorigem(origem);
                        receitaContrato.setDTInc(new Date());
                        receitaContrato.setDTEmissao(new Date());
                        receitaContrato.setAtzPg(0);
                        receitaContrato.setNumero(contratoMidia.getNContrato());
                        receitaContrato.setValorDesconto(BigDecimal.ZERO);
                        receitaContrato.setValorJuros(BigDecimal.ZERO);
                        receitaContrato.setValorMulta(BigDecimal.ZERO);
                        receitaContrato.setDTVencimento(new Date());
                        salvarReceitaContrato(receitaContrato, Integer.parseInt(contratoMidia.getNumeroParcelas()), contratoMidia.getDiaVencimento(), inicio);
                        ProducaoMidia midia = new ProducaoMidia();
                        midia.setIDContratoMidia(contratoMidia);
                       
                    }

                } else {
                    contratoMidia.getIDcliente().getIDPessoa().setSituacao(true);
                    getPessoaDao().salvarPessoa(contratoMidia.getIDcliente().getIDPessoa());
                    contato.setIDPessoa(contratoMidia.getIDcliente().getIDPessoa());
                    contratoMidia.getIDcliente().setDTInc(new Date(System.currentTimeMillis()));
                    contratoMidia.getIDcliente().setIDUsuario(contextoBean.getUsuarioLogado());
                    contratoMidia.getIDcliente().setAtivo(Boolean.TRUE);
                    contato.setEmail(contratoMidia.getIDcliente().getIDPessoa().getEmail());
                    contato.setDTInc(new Date(System.currentTimeMillis()));
                    contato.setIDUsuario(contextoBean.getUsuarioLogado());
                    contato.setHttp(contratoMidia.getIDcliente().getIDPessoa().getHttp());
                    getContatoDAO().salvarContato(contato);
                    getClienteDAO().salvar(contratoMidia.getIDcliente());
                    receitaContrato.setValorNominal(contratoMidia.getValor());
                    contratoMidia.setValor(contratoMidia.getValor().multiply(new BigDecimal(contratoMidia.getNumeroParcelas())));
                    contratoMidia.setAtivo(new Integer(0));
                    contratoMidia.setDTinc(new Date(System.currentTimeMillis()));
                    contratoMidia.setIDusuario(contextoBean.getUsuarioLogado());
                    getContratoMidiaDAO().salvarContrato(contratoMidia);
                    Origem origem = new Origem();
                    origem.setIDContratoMidia(contratoMidia);
                    this.origemDAO.salvar(origem);


                    receitaContrato.setIDAtividade(this.atividadeDAO.carregar(1));
                    receitaContrato.setIDDocumento(this.documentoDAO.carregar(19));
                    receitaContrato.setIDCCusto(this.custoDAO.carregar(1));
                    receitaContrato.setIDClassificacao(this.classificacaoDAO.carregar(2));
                    receitaContrato.setIDCliente(contratoMidia.getIDcliente());
                    receitaContrato.setIDConta(this.contaDAO.carregar(1));
                    receitaContrato.setTipoJuros(Boolean.FALSE);
                    receitaContrato.setIDUsuario(contextoBean.getUsuarioLogado());
                    receitaContrato.setIdorigem(origem);
                    receitaContrato.setDTInc(new Date());
                    receitaContrato.setDTEmissao(new Date());
                    receitaContrato.setAtzPg(0);
                    receitaContrato.setNumero(contratoMidia.getNContrato());

                    receitaContrato.setValorDesconto(BigDecimal.ZERO);
                    receitaContrato.setValorJuros(BigDecimal.ZERO);
                    receitaContrato.setValorMulta(BigDecimal.ZERO);
                    receitaContrato.setDTVencimento(new Date());

                    salvarReceitaContrato(receitaContrato, Integer.parseInt(contratoMidia.getNumeroParcelas()), contratoMidia.getDiaVencimento(), inicio);
                }



                for (Praca p : pracas) {
                    ContratoPracas contratoPracas = new ContratoPracas();
                    contratoPracas.setDTinc(new Date(System.currentTimeMillis()));
                    contratoPracas.setIDusuarioInc(contextoBean.getUsuarioLogado());
                    contratoPracas.setIDcontratomidia(contratoMidia);
                    contratoPracas.setIDpraca(p);
                    getContratoPracasDAO().salvar(contratoPracas);

                }
                //referente a inclusão do contrato en receitas paramentros fixos ja pre estabelecidos

                RequestContext requestContext = RequestContext.getCurrentInstance();
                FacesContext context = FacesContext.getCurrentInstance();
                requestContext.execute("wiz.loadStep(wiz.cfg.steps[0], true)");
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso", "Salvo com sucesso! "));
                if (mailing != null) {
                    ProducaoMidia midia = new ProducaoMidia();
                    mailing.setIDContratoMidia(contratoMidia);
                    mailing.setStatus(4);
                    mailing.setDTvalidade(null);
                    this.mailingDAO.atualizar(mailing);
                    midia.setIDContratoMidia(contratoMidia);
                    if (producao) {
                        midia.setStatusMaterial(0);
                        midia.setStatusProducao(0);
                    } else {
                        midia.setStatusMaterial(2);
                        midia.setStatusProducao(3);
                    }
                    this.producaoMidiaDAO.salvar(midia);

                } else {
                    ProducaoMidia midia = new ProducaoMidia();
                    midia.setIDContratoMidia(contratoMidia);
                    if (producao) {
                        midia.setStatusMaterial(0);
                        midia.setStatusProducao(0);
                    } else {
                        midia.setStatusMaterial(2);
                        midia.setStatusProducao(3);
                    }
                    this.producaoMidiaDAO.salvar(midia);
                }
                return true;
            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falha!", "Usuario não autorizado."));
            return false;
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falha na trasmissão de dados para o banco!", "Falha na trasmissão de dados para o banco!"));
            return false;
        }
    }

    @Transactional(value = "tmGreen")
    public void salvarParceiro(ContratoMidia midia, BigDecimal valorReceita, List<Praca> pracas, boolean produzir) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        try {
            Receita receita = new Receita();
            Origem origem = new Origem();
            //salvando contrato
            receita.setIDCliente(midia.getIDcliente());
            midia.setDTinc(new Date());
            midia.setIDusuario(ContextoUtil.getContextoBean().getUsuarioLogado());

            midia.setIDvendedor(this.funcionarioDAO.carregar(17));
            midia.setIDgerentevendas(this.funcionarioDAO.carregar(96));
            midia.setDiaVencimento(10);

            Map<Date, BigDecimal> map = calculaProRata(midia.getDataInicioContrato(), midia.getDataFimContrato(), valorReceita);
            midia.setNumeroParcelas(String.valueOf(map.size()));
            if (midia.getIDtipopagamento().getIDTipoPagamento() == 5) {
                midia.setValor(new BigDecimal("0.00"));
                midia.setNumeroParcelas("1");
            }
            getContratoMidiaDAO().salvarContrato(midia);
            //salvando origem da receita

            if (midia.getIDtipopagamento().getIDTipoPagamento() != 3 && midia.getIDtipopagamento().getIDTipoPagamento() != 5) {
                origem.setIDContratoMidia(midia);
                this.origemDAO.salvar(origem);

                //gerando receita referente ao parceiro 
                receita.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
                receita.setDTInc(new Date());
                receita.setIdorigem(origem);
                receita.setValorDesconto(new BigDecimal("0"));
                receita.setValorMulta(new BigDecimal("0"));
                receita.setValorJuros(new BigDecimal("0"));
                receita.setIDAtividade(this.atividadeDAO.carregar(1));
                receita.setIDCCusto(this.custoDAO.carregar(1));
                receita.setIDClassificacao(this.classificacaoDAO.carregar(2));
                receita.setIDDocumento(this.documentoDAO.carregar(19));

                receita.setTipoJuros(Boolean.FALSE);
                receita.setDTEmissao(new Date());
                receita.setAtzPg(0);
                //calcular quantidade de meses;

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(midia.getDataInicioContrato());
                calendar.add(Calendar.MONTH, +1);
                calendar.set(Calendar.DAY_OF_MONTH, 10);
                receita.setDTVencimento(calendar.getTime());


                for (Map.Entry<Date, BigDecimal> entry : map.entrySet()) {
                    Integer parcela = 1;
                    Receita r = receita;
                    r.setValorLiquido(entry.getValue());
                    r.setValorNominal(entry.getValue());
                    r.setDTVencimento(entry.getKey());
                    r.setNumero("parc : " + parcela.toString());
                    receitaDAO.salvar(r);
                    parcela++;
                }

            }
            //salvando praças ao contrato
            for (Praca p : pracas) {
                ContratoPracas contratoPracas = new ContratoPracas();
                contratoPracas.setDTinc(new Date(System.currentTimeMillis()));
                contratoPracas.setIDusuarioInc(ContextoUtil.getContextoBean().getUsuarioLogado());
                contratoPracas.setIDcontratomidia(midia);
                contratoPracas.setIDpraca(p);
                getContratoPracasDAO().salvar(contratoPracas);
            }
            ProducaoMidia producao = new ProducaoMidia();
            producao.setIDContratoMidia(midia);
            if (produzir) {
                producao.setStatusMaterial(0);
                producao.setStatusProducao(0);

            } else {
                producao.setDTFimMaterial(new Date());
                producao.setDTFimProducao(new Date());
                producao.setStatusMaterial(2);
                producao.setStatusProducao(3);
            }
            this.producaoMidiaDAO.salvar(producao);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contrato salvo com sucesso!", "Contrato salvo com sucesso!"));

            requestContext.execute("dialogInsereContrato.hide()");
            requestContext.update("formPrin");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ocorreu erro ao salvar o contrato!", "Ocorreu erro ao salvar o contrato!"));

            requestContext.execute("dialogInsereContrato.hide()");
            requestContext.update("formPrin");


        }


    }

    @Transactional(value = "tmGreen")
    public void salvarParceiroSaida(ContratoMidia midia, BigDecimal valorLucro, List<Praca> pracas, TaxaParceiro tp, boolean produzir, boolean inicio, Cliente parceiro) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        try {
            Receita receita = new Receita();
            Origem origem = new Origem();
            Despesa despesa = new Despesa();
            //salvando contrato

            midia.setDTinc(new Date());
            midia.setIDusuario(ContextoUtil.getContextoBean().getUsuarioLogado());
            Map<Date, BigDecimal> map = calculaProRata(midia.getDataInicioContrato(), midia.getDataFimContrato(), valorLucro);
            midia.setAtivo(0);

            if (midia.getIDcliente().getIDCliente() == null && midia.getIDcliente().getIDPessoa().getIDPessoa() == null) {
                midia.getIDcliente().setAtivo(true);
                midia.getIDcliente().setDTInc(new Date());
                midia.getIDcliente().setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
                getPessoaDao().salvar(midia.getIDcliente().getIDPessoa());
                getClienteDAO().salvar(midia.getIDcliente());
            } else if (midia.getIDcliente().getIDCliente() == null && midia.getIDcliente().getIDPessoa().getIDPessoa() != null) {
                midia.setIDcliente(getClienteDAO().buscaPorPessoa(midia.getIDcliente().getIDPessoa()));
                if (midia.getIDcliente().getIDCliente() == null) {
                    getClienteDAO().salvar(midia.getIDcliente());
                }
            }
            receita.setValorNominal(midia.getValor());
            midia.setValor(receita.getValorNominal().multiply(new BigDecimal(midia.getNumeroParcelas())));
            if (midia.getIDtipopagamento().getIDTipoPagamento() == 5) {
                midia.setValor(new BigDecimal("0.00"));
            }
            midia.setClienteParceiro(midia.getIDcliente().getIDPessoa().getFantasia());
            tp.setIDContratoMidia(midia);
            getTaxaParceiroDAO().salvar(tp);
            //gerando receita referente ao parceiro 

            if (midia.getIDtipopagamento().getIDTipoPagamento() != 3 && midia.getIDtipopagamento().getIDTipoPagamento() != 5) {
                //salvando origem da receita
                origem.setIDContratoMidia(midia);
                this.origemDAO.salvar(origem);
                receita.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
                receita.setDTInc(new Date());
                receita.setIdorigem(origem);
                receita.setValorDesconto(new BigDecimal("0"));
                receita.setValorMulta(new BigDecimal("0"));
                receita.setValorJuros(new BigDecimal("0"));
                receita.setIDAtividade(this.atividadeDAO.carregar(1));
                receita.setIDCCusto(this.custoDAO.carregar(1));
                receita.setIDClassificacao(this.classificacaoDAO.carregar(2));
                receita.setIDDocumento(this.documentoDAO.carregar(19));
                receita.setTipoJuros(Boolean.FALSE);
                receita.setDTEmissao(new Date());
                receita.setDTMulta(new Date());
                receita.setDTDesconto(new Date());
                receita.setDTJuros(new Date());
                receita.setIDConta(this.contaDAO.carregar(1));
                receita.setAtzPg(0);
                receita.setNumero(midia.getNContrato());
                receita.setIDCliente(midia.getIDcliente());
                //calcular quantidade de meses;

                salvarReceitaContrato(receita, Integer.parseInt(midia.getNumeroParcelas()), midia.getDiaVencimento(), inicio);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(midia.getDataInicioContrato());
                calendar.add(Calendar.MONTH, +1);
                calendar.set(Calendar.DAY_OF_MONTH, 10);
                despesa.setAtzPg(0);
                despesa.setDTInc(receita.getDTInc());
                despesa.setDTEmissao(receita.getDTEmissao());
                despesa.setDTJuros(receita.getDTJuros());
                despesa.setDTMulta(receita.getDTMulta());
                despesa.setIDAtividade(receita.getIDAtividade());
                despesa.setIDCCusto(receita.getIDCCusto());
                despesa.setIDConta(receita.getIDConta());
                despesa.setIDDocumento(receita.getIDDocumento());
                despesa.setIDCliente(parceiro);
                despesa.setIDUsuario(receita.getIDUsuario());
                despesa.setValorDesconto(receita.getValorDesconto());
                despesa.setValorJuros(receita.getValorJuros());
                despesa.setValorMulta(receita.getValorJuros());
                despesa.setTipoJuros(false);
                despesa.setPago(false);
                despesa.setIdorigem(receita.getIdorigem());
                despesa.setDTVencimento(calendar.getTime());
                despesa.setIDClassificacao(receita.getIDClassificacao());
                int parcela = 1;
                for (Map.Entry<Date, BigDecimal> entry : map.entrySet()) {
                    Despesa d = despesa;
                    d.setValorLiquido(entry.getValue().divide(new BigDecimal(2)));
                    d.setValorNominal(entry.getValue().divide(new BigDecimal(2)));
                    d.setDTVencimento(entry.getKey());
                    d.setNumero(String.valueOf(parcela));
                    despesaDAO.salvar(d);
                    parcela++;
                }
            }
            ProducaoMidia producao = new ProducaoMidia();
            producao.setIDContratoMidia(midia);
            if (produzir) {
                producao.setStatusMaterial(0);
                producao.setStatusProducao(0);

            } else {
                producao.setDTFimMaterial(new Date());
                producao.setDTFimProducao(new Date());
                producao.setStatusMaterial(2);
                producao.setStatusProducao(3);
            }
            this.producaoMidiaDAO.salvar(producao);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contrato salvo com sucesso!", "Contrato salvo com sucesso!"));
            requestContext.execute("dialogInsereContratoSaida.hide()");
            requestContext.update("formPrin");
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ocorreu erro ao salvar o contrato!", "Ocorreu erro ao salvar o contrato!"));
            requestContext.execute("dialogInsereContratoSaida.hide()");
            requestContext.update("formPrin");
        }

    }

    public List<ContratoMidia> listar() {
        return getContratoMidiaDAO().listar();
    }

    public List<ContratoMidia> contratosUsuario() {
        ContextoBean usuario = ContextoUtil.getContextoBean();
        return getContratoMidiaDAO().contratoUsuario(usuario.getUsuarioLogado());
    }

    public void escluir(ContratoMidia contratoMidia) {
        getContratoMidiaDAO().excluir(contratoMidia);
    }

    public ContratoMidia carregar(Integer pk) {
        return getContratoMidiaDAO().carregar(pk);
    }

    @Transactional("tmGreen")
    public void atualizar(ContratoMidia contratoMidia) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext message = FacesContext.getCurrentInstance();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_2")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_PRODUCAO")) {
            contratoMidia.setDTalt(new Date(System.currentTimeMillis()));
            contratoMidia.setIDusuarioalt(contextoBean.getUsuarioLogado());
            getContratoMidiaDAO().atualizar(contratoMidia);
            message.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso", "Ok! Atualizado com sucesso!"));

        } else {
            message.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falha autorização", "Usuario não autorizado!"));
        }
    }

    /*
     * Calcula parcelas contando do dia inicial da ate o final gerando paracela com pro-rata do primeiro mês e do ultimo mês 
     * gerando as parcelas intermediarias do valor integral de cada parcela 
     */
    private Map<Date, BigDecimal> calculaProRata(Date inicio, Date fim, BigDecimal valor) {
        Map<Date, BigDecimal> map = new HashMap<>();
        Calendar dataInicio = Calendar.getInstance();
        Calendar dataFim = Calendar.getInstance();

        dataInicio.setTime(inicio);
        dataFim.setTime(fim);

        int ultimoDiaMesInicio = dataInicio.getActualMaximum(Calendar.DAY_OF_MONTH);//ultimo dia do mes de inicio
        int ultimoDiaMesFim = dataFim.getActualMaximum(Calendar.DAY_OF_MONTH); //ultimo dia do mes de termino
        int prorataInicio = (ultimoDiaMesInicio - dataInicio.get(Calendar.DAY_OF_MONTH)) + 1;// quantos dias serão cobrados do mês de inicio
        int prorataFim = dataFim.get(Calendar.DAY_OF_MONTH);//quantos dias seão cobrados do mês de termino
        //int ultimoMes = dataFim.get(Calendar.MONTH) + 1;
        dataInicio.set(Calendar.DAY_OF_MONTH, 10);
        dataFim.set(Calendar.DAY_OF_MONTH, 10);
        long result;
        result = fim.getTime() - inicio.getTime();
        int numDias = (int) ((((result / 1000) / 60) / 60) / 24) + 1; //calcular numero total de dias do periodo

        if (numDias <= 31) { //menos de 31 dias pegara o valor e dividira se for em dois meses  e se o periodo for dentro do mesmo mês ira gerar apenas uma parcela com o valor integral
            if (dataInicio.get(Calendar.MONTH) == dataFim.get(Calendar.MONTH) && dataInicio.get(Calendar.YEAR) == dataInicio.get(Calendar.YEAR)) {
                dataInicio.add(Calendar.MONTH, +1);
                map.put(dataInicio.getTime(), valor);
                return map;
            } else {
                BigDecimal valorDia = valor.divide(new BigDecimal(numDias), 2, RoundingMode.UP);
                BigDecimal valorPrimeira = valorDia.multiply(new BigDecimal(prorataInicio));
                BigDecimal valorSegunda = valor.subtract(valorPrimeira);
                dataInicio.add(Calendar.MONTH, +1);
                map.put(dataInicio.getTime(), valorPrimeira);
                dataInicio.add(Calendar.MONTH, +1);
                Date dFim = dataInicio.getTime();
                map.put(dFim, valorSegunda);
                return map;

            }
        } else {
            BigDecimal primeiraParcela = valor.divide(new BigDecimal(ultimoDiaMesInicio), 2, RoundingMode.UP);
            primeiraParcela = primeiraParcela.multiply(new BigDecimal(prorataInicio));//calculo do valor da pro-rata da primeira parcela

            //Calendar date = dataInicio;
            Calendar dataF = dataFim;
            dataInicio.add(Calendar.MONTH, +1);
            map.put(dataInicio.getTime(), primeiraParcela);//salvando mês evalor da primeira parcela
            // dataFim.roll(Calendar.MONTH, false);
            while (dataInicio.before(dataFim)) {//laço para gera as parcelas intermediarias ao periodo.
                if (dataInicio.get(Calendar.MONTH) == dataFim.get(Calendar.MONTH) && dataInicio.get(Calendar.YEAR) == dataFim.get(Calendar.YEAR)) {
                    break;
                } else {
                    Date dfim = new Date();
                    dataInicio.add(Calendar.MONTH, +1);
                    dfim = dataInicio.getTime();
                    map.put(dfim, valor);
                }

            }
            BigDecimal ultimaParcela = valor.divide(new BigDecimal(ultimoDiaMesFim), 2, RoundingMode.UP).multiply(new BigDecimal(prorataFim));
            dataInicio.add(Calendar.MONTH, +1);
            Date d = new Date();
            d = dataInicio.getTime();
            map.put(d, ultimaParcela);//calculo e guardando mês e valor pro-rata da ultima parcela 
            for (Map.Entry entry : map.entrySet()) {
                Date dt = (Date) entry.getKey();
                System.out.println("Data = " + dt.toGMTString() + "Valor =" + entry.getValue());
            }
            return map;

        }

    }

    public void salvarReceitaContrato(Receita receita, int parcelas, int vencimento, boolean inicio) throws Exception {
        Calendar nova = new GregorianCalendar();
        nova.set(Calendar.DAY_OF_MONTH, vencimento);
        receita.setValorLiquido(receita.getValorNominal());
        receita.setAtzPg(0);
        String doc = receita.getNumero();
        if (inicio) {
            nova.add(Calendar.MONTH, +1);
        }
        receita.setDTVencimento(nova.getTime());
        if (parcelas > 1) {
            int cont = 0;
            if (parcelas != 2) {
                for (int i = 0; parcelas > i; i++) {
                    Receita r = receita;
                    nova.add(Calendar.MONTH, +1);
                    r.setNumero(doc + " / parc: " + String.valueOf(i + 1));
                    r.setDTVencimento(nova.getTime());
                    getReceitaDAO().salvar(r);
                }
            } else {
                for (int i = 0; parcelas > i; i++) {
                    Receita r = receita;
                    nova.setTime(r.getDTVencimento());
                    nova.add(Calendar.DAY_OF_MONTH, cont);
                    r.setNumero(doc + " / parc: " + String.valueOf(i + 1));
                    r.setDTVencimento(nova.getTime());
                    getReceitaDAO().salvar(r);
                    cont = 30;
                }
            }

        } else if (parcelas == 0 || parcelas == 1) {
            getReceitaDAO().salvar(receita);
        }
    }

    public List<ContratoMidia> buscaPorVendedor(Funcionario funcionario, Date dtini, Date dtfim) {
        return getContratoMidiaDAO().buscaPorVendedor(funcionario, dtini, dtfim);
    }

    public List<ContratoMidia> buscaPorFormaPag(Tipopagamento tipopagamento, Date dtInicio, Date dtFim) {
        return getContratoMidiaDAO().buscaPorFormaPag(tipopagamento, dtInicio, dtFim);
    }

    public List<ContratoMidia> listaVemd() {
        return getContratoMidiaDAO().listaVend();
    }

    public List<ContratoMidia> listVencidos() {
        return getContratoMidiaDAO().listaVencidos();
    }

    public List<ContratoMidia> listarPorPraca(Integer id) {
        return getContratoMidiaDAO().listarPorPraca(id);
    }
}
