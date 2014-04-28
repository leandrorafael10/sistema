/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.*;
import com.green.rn.ContaRN;
import com.green.rn.CreditoRN;
import com.green.rn.DebitoRN;
import com.green.util.*;
import com.green.view.ContaConciliacaoDataModelo;
import com.green.view.CreditoDataModelo;
import com.green.view.DebitoDataModelo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "contaBean")
@ViewScoped
public class ContaBean implements Serializable {

    @ManagedProperty("#{contaRN}")
    private ContaRN contaRN;
    @ManagedProperty("#{debitoRN}")
    private DebitoRN debitoRN;
    @ManagedProperty("#{creditoRN}")
    private CreditoRN creditoRN;
    private List<Conta> contas;
    private List<Credito> creditos;
    private List<Debito> debitos;
    private Debito[] debitosData;
    private Credito[] creditosData;
    private List<ContaConciliacao> conciliacaos = new ArrayList<ContaConciliacao>();
    private Conta conta = new Conta();
    private Conta contaNova = new Conta();
    private Contato contato = new Contato();
    private Credito creditoFiltro = new Credito();
    private Debito debitoFiltro = new Debito();
    private Pessoa pessoa = new Pessoa();
    private String telComercial = new String();
    private String fax = new String();
    private String calular = new String();
    private String email = new String();
    String nomeRelatorioSaida;
    private Endereco endereco = new Endereco();
    private ContaConciliacao contaConciliacao = new ContaConciliacao(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    private ContaConciliacao contaConciliacaoData = new ContaConciliacao(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    private DebitoDataModelo debitoDataModelo;
    private CreditoDataModelo creditoDataModelo;
    private ContaConciliacaoDataModelo contaConciliacaoDataModelo;
    private Date fimFiltroBaixa;
    private Date fimFiltroConciliacao;
    private int opcFiltroSituacao = 1;
    private int opcFiltroTipo = 1;
    private BigDecimal valorConciliado = new BigDecimal(0);
    private BigDecimal valorAconciliar = new BigDecimal(0);
    private BigDecimal valorPrevisto = new BigDecimal(0);
    private boolean tab = true;
    private String filtroNomeBanco ;
    private Integer filtroTipoConta = 1;

    public void listando() {
        setContas(getContaRN().listar());
    }

    public void listandoCreditoDebito() {
        this.debitoDataModelo = new DebitoDataModelo(getDebitos());
        this.creditoDataModelo = new CreditoDataModelo(getCreditos());
    }

    public void listandoConciliado() {
        List<Credito> cs = getCreditoRN().listando();
        List<Debito> ds = getDebitoRN().listando();
        setConciliacaos(new ArrayList<ContaConciliacao>());
        for (Conta contaConciliada : getContas()) {
            ContaConciliacao cc = calculoSaldos(contaConciliada, cs, ds);
            getConciliacaos().add(cc);
        }
        setContaConciliacaoDataModelo(new ContaConciliacaoDataModelo(getConciliacaos()));
    }

    public void saldosConta() {
        setValorAconciliar(BigDecimal.ZERO);
        setValorConciliado(BigDecimal.ZERO);
        setValorPrevisto(BigDecimal.ZERO);
        ContaConciliacao cc = calculoSaldos(getCreditoFiltro().getIDConta(), getCreditoRN().listando(), getDebitoRN().listando());
        setValorPrevisto(cc.getValorPrevisto());
        setValorAconciliar(cc.getValorAconciliar());
        setValorConciliado(cc.getValorConciliado());
    }

    public String datahoje() {
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(d);
    }
    public void confirmaTrans(ActionEvent event){
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("form_tarns:gridtrans");
        context.execute("dialogtrans.show()");
    }

    public void filtroCreditoDebito() {
        List<Credito> c;
        List<Debito> d;
        getDebitoFiltro().setDTBaixa(getCreditoFiltro().getDTBaixa());
        getDebitoFiltro().setDTConciliacao(getCreditoFiltro().getDTConciliacao());
        getDebitoFiltro().setIDAtividade(getCreditoFiltro().getIDAtividade());
        getDebitoFiltro().setIDCCusto(getCreditoFiltro().getIDCCusto());
        getDebitoFiltro().setIDClassificacao(getCreditoFiltro().getIDClassificacao());
        getDebitoFiltro().setIDConta(getCreditoFiltro().getIDConta());
        getDebitoFiltro().setIDTpDocumento(getCreditoFiltro().getIDTpDocumento());
        getDebitoFiltro().setNumero(getCreditoFiltro().getNumero());
        getDebitoFiltro().setValor(getCreditoFiltro().getValor());
        getDebitoFiltro().setValor(getCreditoFiltro().getValor());
        c = getCreditoRN().filtroCredito(getCreditoFiltro(), getFimFiltroBaixa(), getFimFiltroConciliacao());
        d = getDebitoRN().filtroDebito(getDebitoFiltro(), getFimFiltroBaixa(), getFimFiltroConciliacao());
        if (opcFiltroSituacao == 2) {
            List<Credito> cs = new ArrayList<Credito>();
            for (Credito c1 : c) {
                if (c1.getDTConciliacao() != null) {
                    cs.add(c1);
                }
            }
            c = cs;
        }
        if (opcFiltroSituacao == 3) {
            List<Credito> cs = new ArrayList<Credito>();
            for (Credito c1 : c) {
                if (c1.getDTConciliacao() == null) {
                    cs.add(c1);
                }
            }
            c = cs;
        }
        if (opcFiltroSituacao == 2) {
            List<Debito> ds = new ArrayList<Debito>();
            for (Debito d1 : d) {
                if (d1.getDTConciliacao() != null) {
                    ds.add(d1);
                }
            }
            d = ds;
        }
        if (opcFiltroSituacao == 3) {
            List<Debito> ds = new ArrayList<Debito>();
            for (Debito d1 : d) {
                if (d1.getDTConciliacao() == null) {
                    ds.add(d1);
                }
            }
            d = ds;
        }
        if (opcFiltroTipo == 1) {
            setCreditos(c);
            setDebitos(d);
        }
        if (opcFiltroTipo == 2) {
            setCreditos(c);
            setDebitos(new ArrayList<Debito>());
        }
        if (opcFiltroTipo == 3) {
            setDebitos(d);
            setCreditos(new ArrayList<Credito>());
        }

        listandoCreditoDebito();
    }

    @PostConstruct
    private void init() {
        listando();
        listandoConciliado();
        listandoCreditoDebito();
    }

    public void contaSelecionada(ContaConciliacao cc) {
        RequestContext context = RequestContext.getCurrentInstance();
        setCreditoFiltro(new Credito());
        setDebitoFiltro(new Debito());
        this.opcFiltroSituacao = 1;
        this.opcFiltroTipo = 1;
        getCreditoFiltro().setIDConta(cc.getConta());
        getDebitoFiltro().setIDConta(cc.getConta());
        filtroCreditoDebito();
        saldosConta();
        setTab(false);
        context.update("formConciliacao:tabConciliacao:panelFiltroDataTable");
    }

    public void conciliar(ActionEvent event) {
        getDebitoRN().conciliarDebito(getDebitosData());
        getCreditoRN().conciliarCredito(getCreditosData());
        saldosConta();
        listandoConciliado();
    }

    public void cancelarConciliacao(ActionEvent event) {
        getCreditoRN().cancelarConciliacao(getCreditosData(), getCreditoFiltro().getIDConta());
        getDebitoRN().cancelarConciliacao(getDebitosData(), getCreditoFiltro().getIDConta());
        saldosConta();
        listandoConciliado();
    }

    public String salvar(ActionEvent actionEvent) {
        getPessoa().setCidade(getEndereco().getCidade());
        getPessoa().setEstado(getEndereco().getEstado());
        getPessoa().setEmail(getEmail());
        formatatel(getFax(), getCalular(), "", getTelComercial());
        getContaRN().salvar(getContaNova(), getPessoa(), getContato());
        setPessoa(new Pessoa());
        setConta(new Conta());
        setEndereco(new Endereco());
        return "/tesouraria/cadastros_tesouraria/lista_contas";

    }

    public ContaConciliacao calculoSaldos(Conta conta, List<Credito> creditos, List<Debito> debitos) {
        ContaConciliacao cc = new ContaConciliacao(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        BigDecimal saldoDebitoA = BigDecimal.ZERO;
        BigDecimal saldoDebitoC = BigDecimal.ZERO;
        BigDecimal saldoCreditoC = BigDecimal.ZERO;
        BigDecimal saldoCreditoA = BigDecimal.ZERO;
        for (Credito c : creditos) {
            if (conta != null) {
                if (c.getIDConta().equals(conta)) {
                    if (c.getDTConciliacao() != null) {
                        saldoCreditoC = saldoCreditoC.add(c.getValor());
                    } else {
                        saldoCreditoA = saldoCreditoA.add(c.getValor());
                    }
                }
            } else {
                if (c.getDTConciliacao() != null) {
                    saldoCreditoC = saldoCreditoC.add(c.getValor());
                } else {
                    saldoCreditoA = saldoCreditoA.add(c.getValor());
                }
            }
        }
        for (Debito d : debitos) {
            if (conta != null) {
                if (d.getIDConta().equals(conta)) {
                    if (d.getDTConciliacao() != null) {
                        saldoDebitoC = saldoDebitoC.add(d.getValor());
                    } else {
                        saldoDebitoA = saldoDebitoA.add(d.getValor());
                    }
                }
            } else {
                if (d.getDTConciliacao() != null) {
                    saldoDebitoC = saldoDebitoC.add(d.getValor());
                } else {
                    saldoDebitoA = saldoDebitoA.add(d.getValor());
                }
            }
        }
        cc.setConta(conta);
        cc.setValorPrevisto(saldoCreditoA.add(saldoCreditoC).subtract(saldoDebitoA.add(saldoDebitoC)));
        cc.setValorAconciliar(saldoCreditoA.subtract(saldoDebitoA));
        cc.setValorConciliado(saldoCreditoC.subtract(saldoDebitoC));
        return cc;
    }
    
    public void trasferencia(Credito credito,Debito debito){
        getContaRN().transferencia(credito,debito);
    }

    public void incluirCredito(Credito c) {
        RequestContext context = RequestContext.getCurrentInstance();
        getCreditoRN().salvar(c);
        listandoConciliado();
        context.execute("dialogIncluiCredito.hide()");
        context.execute("dialogIncluir.show()");
        context.update("formConciliacao");
    }

    public void incluirDebito(Debito d) {
        RequestContext context = RequestContext.getCurrentInstance();
        getDebitoRN().salvar(d);
        listandoConciliado();
        context.execute("dialogIncluirDebito.hide()");
        context.execute("dialogIncluir.show()");
        context.update("formConciliacao");
    }

    public void excluir() {
        getContaRN().excluir(getConta());
    }

    public void arquivoRetorno(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        Date dateNomeRelatorio = new Date();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        String nomeRelatorioJasper = "conta";
        nomeRelatorioSaida = dateNomeRelatorio.getTime() + "contas";
        RelatorioUtil relatorioUtil = new RelatorioUtil();
        HashMap<String, Object> parametrosRelatorio = new HashMap<String, Object>();
        if(getFiltroNomeBanco()==null){
            setFiltroNomeBanco("");
        }
        parametrosRelatorio.put("nome_banco", getFiltroNomeBanco()+"%");
        parametrosRelatorio.put("tipo_conta", getFiltroTipoConta());

        try {
            relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper, nomeRelatorioSaida, 1);
            requestContext.execute("dialogCarregando.hide()");
            parametrosRelatorio = new HashMap<String, Object>();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(e.getMessage()));
        }

    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCalular() {
        return calular;
    }

    public void setCalular(String calular) {
        this.calular = calular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTelComercial() {
        return telComercial;
    }

    public void setTelComercial(String telComercial) {
        this.telComercial = telComercial;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Conta getContaNova() {
        return contaNova;
    }

    public void setContaNova(Conta contaNova) {
        this.contaNova = contaNova;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public ContaRN getContaRN() {
        return contaRN;
    }

    public void setContaRN(ContaRN contaRN) {
        this.contaRN = contaRN;
    }

    public CreditoRN getCreditoRN() {
        return creditoRN;
    }

    public void setCreditoRN(CreditoRN creditoRN) {
        this.creditoRN = creditoRN;
    }

    public List<Credito> getCreditos() {
        return creditos;
    }

    public void setCreditos(List<Credito> creditos) {
        this.creditos = creditos;
    }

    public DebitoRN getDebitoRN() {
        return debitoRN;
    }

    public void setDebitoRN(DebitoRN debitoRN) {
        this.debitoRN = debitoRN;
    }

    public List<Debito> getDebitos() {
        return debitos;
    }

    public void setDebitos(List<Debito> debitos) {
        this.debitos = debitos;
    }

    public List<ContaConciliacao> getConciliacaos() {
        return conciliacaos;
    }

    public void setConciliacaos(List<ContaConciliacao> conciliacaos) {
        this.conciliacaos = conciliacaos;
    }

    public ContaConciliacao getContaConciliacao() {
        return contaConciliacao;
    }

    public void setContaConciliacao(ContaConciliacao contaConciliacao) {
        this.contaConciliacao = contaConciliacao;
    }

    public Debito[] getDebitosData() {
        return debitosData;
    }

    public void setDebitosData(Debito[] debitosData) {
        this.debitosData = debitosData;
    }

    public DebitoDataModelo getDebitoDataModelo() {
        return debitoDataModelo;
    }

    public void setDebitoDataModelo(DebitoDataModelo debitoDataModelo) {
        this.debitoDataModelo = debitoDataModelo;
    }

    public CreditoDataModelo getCreditoDataModelo() {
        return creditoDataModelo;
    }

    public void setCreditoDataModelo(CreditoDataModelo creditoDataModelo) {
        this.creditoDataModelo = creditoDataModelo;
    }

    public Credito[] getCreditosData() {
        return creditosData;
    }

    public void setCreditosData(Credito[] creditosData) {
        this.creditosData = creditosData;
    }

    public Credito getCreditoFiltro() {
        return creditoFiltro;
    }

    public void setCreditoFiltro(Credito creditoFiltro) {
        this.creditoFiltro = creditoFiltro;
    }

    public Date getFimFiltroBaixa() {
        return fimFiltroBaixa;
    }

    public void setFimFiltroBaixa(Date fimFiltroBaixa) {
        this.fimFiltroBaixa = fimFiltroBaixa;
    }

    public Date getFimFiltroConciliacao() {
        return fimFiltroConciliacao;
    }

    public void setFimFiltroConciliacao(Date fimFiltroConciliacao) {
        this.fimFiltroConciliacao = fimFiltroConciliacao;
    }

    public int getOpcFiltroSituacao() {
        return opcFiltroSituacao;
    }

    public void setOpcFiltroSituacao(int opcFiltroSituacao) {
        this.opcFiltroSituacao = opcFiltroSituacao;
    }

    public int getOpcFiltroTipo() {
        return opcFiltroTipo;
    }

    public void setOpcFiltroTipo(int opcFiltroTipo) {
        this.opcFiltroTipo = opcFiltroTipo;
    }

    public ContaConciliacaoDataModelo getContaConciliacaoDataModelo() {
        return contaConciliacaoDataModelo;

    }

    public void setContaConciliacaoDataModelo(ContaConciliacaoDataModelo contaConciliacaoDataModelo) {
        this.contaConciliacaoDataModelo = contaConciliacaoDataModelo;
    }

    public ContaConciliacao getContaConciliacaoData() {
        return contaConciliacaoData;
    }

    public void setContaConciliacaoData(ContaConciliacao contaConciliacaoData) {
        this.contaConciliacaoData = contaConciliacaoData;
    }

    public Debito getDebitoFiltro() {
        return debitoFiltro;
    }

    public void setDebitoFiltro(Debito debitoFiltro) {
        this.debitoFiltro = debitoFiltro;
    }

    public BigDecimal getValorAconciliar() {
        return valorAconciliar;
    }

    public void setValorAconciliar(BigDecimal valorAconciliar) {
        this.valorAconciliar = valorAconciliar;
    }

    public BigDecimal getValorConciliado() {
        return valorConciliado;
    }

    public void setValorConciliado(BigDecimal valorConciliado) {
        this.valorConciliado = valorConciliado;
    }

    public BigDecimal getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(BigDecimal valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public boolean isTab() {
        return tab;
    }

    public void setTab(boolean tab) {
        this.tab = tab;
    }

    public String getFiltroNomeBanco() {
        return filtroNomeBanco;
    }

    public void setFiltroNomeBanco(String filtroNomeBanco) {
        this.filtroNomeBanco = filtroNomeBanco;
    }

    public Integer getFiltroTipoConta() {
        return filtroTipoConta;
    }

    public void setFiltroTipoConta(Integer filtroTipoConta) {
        this.filtroTipoConta = filtroTipoConta;
    }

    public String getNomeRelatorioSaida() {
        return nomeRelatorioSaida;
    }

    public void setNomeRelatorioSaida(String nomeRelatorioSaida) {
        this.nomeRelatorioSaida = nomeRelatorioSaida;
    }

    public void formatatel(String fixo, String celular, String residencial, String comercial) {
        if (!fixo.equals("")) {
            getContato().setDddf(fixo.substring(1, 3));
            getContato().setTelefoneF(fixo.substring(4, 8) + fixo.substring(9));
        }
        if (!celular.equals("")) {
            getContato().setDDDCel(celular.substring(1, 3));
            getContato().setTelefoneCel(celular.substring(4, 8) + celular.substring(9));
        }
        if (!residencial.equals("")) {
            getContato().setDddf(residencial.substring(1, 3));
            getContato().setTelefoneF(residencial.substring(4, 8) + residencial.substring(9));
        }
        if (!comercial.equals("")) {
            getContato().setDddc(comercial.substring(1, 3));
            getContato().setTelefoneC(comercial.substring(4, 8) + comercial.substring(9));
        }
    }
}
