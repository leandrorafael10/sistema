/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Cliente;
import com.green.modelo.Conta;
import com.green.modelo.Credito;
import com.green.modelo.Receita;
import com.green.modelo.Receitacredito;
import com.green.rn.BoletoRN;
import com.green.rn.ReceitaCreditoRN;
import com.green.rn.ReceitaRN;
import com.green.util.BoletoCaelum;
import com.green.view.ReceitaDataModelo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leandro.silva
 */
@SuppressWarnings("serial")
@ManagedBean(name = "receitaBean")
@ViewScoped
public class ReceitaBean implements Serializable {

    @ManagedProperty("#{receitaRN}")
    private ReceitaRN receitaRN;
    @ManagedProperty("#{receitaCreditoRN}")
    private ReceitaCreditoRN receitaCreditoRN;
    @ManagedProperty("#{boletoRN}")
    private BoletoRN boletoRN;
    private Receitacredito receitacredito = new Receitacredito();
    private List<Receita> receitaCliente;
    private List<Receita> receitaParceiro;
    private List<Receita> receitaGoverno;
    private Receita novaReceita = new Receita();
    private Receita receitaFiltro = new Receita();
    private Cliente cliente = new Cliente();
    private Receita receitaPesquisa = new Receita();
    private Receita receitaDel = new Receita();
    private Receita receitaEdit = new Receita();
    private Conta conta;
    private List<Receita> receitas;
    private int opsCedente;
    private boolean opcFuncionario = false;
    private boolean opsCliente = false;
    private boolean opsFornecedor = false;
    private String tipoCedente;
    private String nunDocumento;
    private boolean tipoMulta;
    private boolean tipoDesconto;
    private Date dataAtual;
    private Date hoje;
    private Date dt_inicio;
    private Date dt_fim;
    private Date fimFiltroEmissao;
    private Date dataBaixa;
    private BigDecimal valorPago = BigDecimal.ZERO;
    private int parcelas = 1;
    private int vencimento = 1;
    private BigDecimal valorIni;
    private BigDecimal valorFim;
    private int pgFiltro;
    private ReceitaDataModelo receitaDataModelo;
    private Receita[] selectReceitas;
    private Receita[] selectReceitasLibera;
    private Receita[] selectReceitasLiberados;
    

    @PostConstruct
    private void init() {
        this.receitaDataModelo = new ReceitaDataModelo(getReceitaRN().listar());
        this.receitaCliente = new ArrayList<>();
        this.receitaParceiro = new ArrayList<>();
        this.receitaGoverno = new ArrayList<>();
        getNovaReceita().setValorMulta(BigDecimal.ZERO);
        getNovaReceita().setValorDesconto(BigDecimal.ZERO);
        getNovaReceita().setValorJuros(BigDecimal.ZERO);
        this.valorIni = new BigDecimal("0.00");
        this.valorFim = new BigDecimal("0.00");
        data();
        this.conta = new Conta();
        this.dt_inicio = new Date();
        this.dt_fim = new Date();
    }

    /**
     * carrega a data de hoje e de ontem automatico no construtor
     */
    @SuppressWarnings("deprecation")
    public void data() {

        Date d1 = new Date();
        Date d = new Date();
        d.setDate(d.getDate() - 1);
        setHoje(d1);
        setDataAtual(d);
    }

    

    public void filtarReceitas(ActionEvent event) {
        if (getDt_fim().after(getDt_inicio())||getDt_fim().equals(getDt_inicio())) {
            setReceitaCliente(getReceitaRN().pagamentoProximoPendenteClienteFlitro(getDt_inicio(), getDt_fim()));
            setReceitaParceiro(getReceitaRN().pagamentoPendenteParceiroFiltro(getDt_inicio(), getDt_fim()));
            setReceitaGoverno(getReceitaRN().pagamentoPendenteContraApresentacaoFiltro(getDt_inicio(), getDt_fim()));
        }
    }

    public void salvar(ActionEvent actionEvent) {
               getReceitaRN().salvar(getNovaReceita());
    }

    public void cancelar(ActionEvent event) {
        getReceitaRN().cancelar_parcela(getReceitaEdit());
    }

    

    public void atualizaParcela(ActionEvent event) {
        getReceitaRN().atualizar(getReceitaEdit());
        filtarReceitas(event);
        RequestContext.getCurrentInstance().update("formParcelas");
    }

    public void confirmacaoReceita() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formReceitaReceber");
        context.execute("PF('dialogConfirmReceita').show()");
    }

    public void baixaPagamento(ActionEvent actionEvent) {
        getReceitaCreditoRN().baixaPagamento(getReceitaEdit(), getDataBaixa());
        filtarReceitas(actionEvent);
        RequestContext.getCurrentInstance().update("formParcelas");

    }
    public void confirmacaoReceita(ActionEvent event){
    	RequestContext context = RequestContext.getCurrentInstance();
        context.update("formConfirmaReceita");
        context.execute("PF('dialogReceita').show()");
    }

    @SuppressWarnings("unused")
	public void calcularTaxa(Receita receita) {
        RequestContext context = RequestContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date diaAtual = new Date();
        BigDecimal valorCalc = BigDecimal.ZERO;
        BigDecimal valorJuros = BigDecimal.ZERO;
        BigDecimal valorMulta = BigDecimal.ZERO;
        int situacaoData = 0;
        if (receita != null) {
            long dt = (diaAtual.getTime() - receita.getDTVencimento().getTime()) + 3600000;
            long diasAtraso = (dt / 86400000L);
            if (receita.getIsentoJuros() == false) {
                if (receita.getTipoJuros() == false) {
                    valorJuros = receita.getValorJuros().multiply(BigDecimal.valueOf(diasAtraso));
                } else {
                    LocalDate date2 = new LocalDate(receita.getDTVencimento());
                    LocalDate date1 = new LocalDate(diaAtual);
                    Months months = Months.monthsBetween(date2, date1);
                    valorJuros = receita.getValorJuros().multiply(BigDecimal.valueOf(months.getMonths()));
                }
            }
            if (receita.getIsentoMulta() == false) {
                valorMulta = receita.getValorMulta();
            }
            if (receita.getDTDesconto() == null) {
                situacaoData = 1;
            } else {
                try {
                    Date d1 = format.parse(format.format(diaAtual));
                    Date d2 = format.parse(format.format(receita.getDTDesconto()));
                    situacaoData = d2.compareTo(d1);
                } catch (ParseException ex) {
                    Logger.getLogger(DespesaBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //aqui o desconto ainda não foi cedido e a data limite para esse desconto nao pode ser maior que a data atual.
            receita.setValorLiquido(valorCalc);
            getReceitacredito().setIDReceita(receita);
            getReceitacredito().setIDCredito(new Credito());
            context.update("for_listar_credito");
            context.update("forPagar_credito");
            context.execute("varDialogReceitaPagar.show()");
        }
    }

    public void emitirBoleto(Receita r) {
        BoletoCaelum boletoCaelum = new BoletoCaelum();
        boletoCaelum.boletoModeloNovo(r, getBoletoRN().buscarBoletoPadrao());
    }

    public void emitirBoletoParceiro() {
        if (this.selectReceitas.length > 0) {
            BoletoCaelum boletoCaelum = new BoletoCaelum();
            Receita r = new Receita();
            BigDecimal soma = new BigDecimal("0.00");
            for (Receita receita : selectReceitas) {
                r = receita;
                soma = soma.add(receita.getValorLiquido());

            }
            r.setValorLiquido(soma);

            boletoCaelum.boletoModeloNovo(r, getBoletoRN().buscarBoletoPadrao());
        }
    }

    public void somarParcelasParceiro(ActionEvent event) {
        BigDecimal soma = new BigDecimal("0.00");
        for (Receita receita : selectReceitas) {
            this.receitaEdit = receita;
            soma = soma.add(receita.getValorLiquido());
        }
        this.valorPago = soma;
    }

    public void baixaPagamentoParceiro(ActionEvent actionEvent) {
        for (Receita receita : selectReceitas) {
            getReceitaCreditoRN().baixaPagamento(receita, getDataBaixa());
        }
        setReceitaParceiro(getReceitaRN().pagamentoPendenteParceiroFiltro(getDt_inicio(), getDt_fim()));
        RequestContext.getCurrentInstance().update("formParcelas");
    }

    public void filtroReceita() {
        getReceitaFiltro().setIDCliente(getCliente());
        this.receitaDataModelo = new ReceitaDataModelo(getReceitaRN().filtro(getReceitaFiltro(), getDt_inicio(), getPgFiltro(), getValorIni(), getValorFim()));

    }

    public void opcaoCedente() {
        switch (opsCedente) {
            case 1:
                setOpcFuncionario(true);
                setOpsCliente(false);
                setOpsFornecedor(false);
                getReceitaEdit().setIDFornecedor(null);
                getNovaReceita().setIDFornecedor(null);
                getNovaReceita().setIDCliente(null);
                getReceitaEdit().setIDCliente(null);
                setTipoCedente("Funcionario: ");
                break;
            case 2:
                setOpsCliente(true);
                setOpcFuncionario(false);
                setOpsFornecedor(false);
                getReceitaEdit().setIDFornecedor(null);
                getNovaReceita().setIDFornecedor(null);
                getNovaReceita().setIDFuncionario(null);
                getReceitaEdit().setIDFuncionario(null);
                setTipoCedente("Cliente: ");
                break;
            case 3:
                setOpsCliente(false);
                setOpcFuncionario(false);
                setOpsFornecedor(true);
                getNovaReceita().setIDFuncionario(null);
                getNovaReceita().setIDFuncionario(null);
                getNovaReceita().setIDCliente(null);
                getNovaReceita().setIDCliente(null);
                setTipoCedente("Fornecedor: ");
                break;
            case 0:
                setOpsCliente(false);
                setOpcFuncionario(false);
                setOpsFornecedor(false);
                getReceitaEdit().setIDFornecedor(null);
                getNovaReceita().setIDFornecedor(null);
                getNovaReceita().setIDFuncionario(null);
                getNovaReceita().setIDCliente(null);
                getNovaReceita().setIDFuncionario(null);
                getReceitaEdit().setIDCliente(null);
                setTipoCedente("");
                break;
            default:
        }
        if (opsCedente == 1) {
            setOpcFuncionario(true);
            setOpsCliente(false);
            getNovaReceita().setIDCliente(null);
            getReceitaEdit().setIDCliente(null);
            setTipoCedente("Funcionario: ");
        } else if (opsCedente == 2) {
            setOpsCliente(true);
            setOpcFuncionario(false);
            getNovaReceita().setIDFuncionario(null);
            getReceitaEdit().setIDFuncionario(null);
            setTipoCedente("Cliente: ");
        } else if (opsCedente == 0) {
            setOpsCliente(false);
            setOpcFuncionario(false);
            getNovaReceita().setIDFuncionario(null);
            getNovaReceita().setIDCliente(null);
            getReceitaEdit().setIDFuncionario(null);
            getReceitaEdit().setIDCliente(null);
            setTipoCedente("");
        }
    }

    public void depesaVisualizacao(Receita r) {
        RequestContext rc = RequestContext.getCurrentInstance();
        setReceitaPesquisa(r);
        rc.update("for_listar_credito");
        rc.execute("varDialogReceita.show()");
    }

    public Receita getReceitaEdit() {
        return receitaEdit;
    }

    public void setReceitaEdit(Receita receitaEdit) {
        this.receitaEdit = receitaEdit;
    }

    public Receita getReceitaDel() {
        return receitaDel;
    }

    public void setReceitaDel(Receita receitaDel) {
        this.receitaDel = receitaDel;
    }

    public Date getFimFiltroEmissao() {
        return fimFiltroEmissao;
    }

    public void setFimFiltroEmissao(Date fimFiltroEmissao) {
        this.fimFiltroEmissao = fimFiltroEmissao;
    }

    public Date getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(Date dt_fim) {
        this.dt_fim = dt_fim;
    }

    public Date getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Receita getReceitaPesquisa() {
        return receitaPesquisa;
    }

    public void setReceitaPesquisa(Receita receitaPesquisa) {
        this.receitaPesquisa = receitaPesquisa;
    }

    public Receita getReceitaFiltro() {
        return receitaFiltro;
    }

    public void setReceitaFiltro(Receita receitaFiltro) {
        this.receitaFiltro = receitaFiltro;
    }

    public Date getHoje() {
        return hoje;
    }

    public void setHoje(Date hoje) {
        this.hoje = hoje;
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    public boolean isTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(boolean tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public boolean isTipoMulta() {
        return tipoMulta;
    }

    public void setTipoMulta(boolean tipoMulta) {
        this.tipoMulta = tipoMulta;
    }

    public String getTipoCedente() {
        return tipoCedente;
    }

    public void setTipoCedente(String tipoCedente) {
        this.tipoCedente = tipoCedente;
    }

    public int getOpsCedente() {
        return opsCedente;
    }

    public boolean isOpcFuncionario() {
        return opcFuncionario;
    }

    public void setOpcFuncionario(boolean opcFuncionario) {
        this.opcFuncionario = opcFuncionario;
    }

    public boolean isOpsCliente() {
        return opsCliente;
    }

    public void setOpsCliente(boolean opsCliente) {
        this.opsCliente = opsCliente;
    }

    public boolean isOpsFornecedor() {
        return opsFornecedor;
    }

    public void setOpsFornecedor(boolean opsFornecedor) {
        this.opsFornecedor = opsFornecedor;
    }

    public void setOpsCedente(int opsCedente) {
        this.opsCedente = opsCedente;
    }

    public Receita getNovaReceita() {
        return novaReceita;
    }

    public void setNovaReceita(Receita novaReceita) {
        this.novaReceita = novaReceita;
    }

    public ReceitaRN getReceitaRN() {
        return receitaRN;
    }

    public void setReceitaRN(ReceitaRN receitaRN) {
        this.receitaRN = receitaRN;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public ReceitaCreditoRN getReceitaCreditoRN() {
        return receitaCreditoRN;
    }

    public void setReceitaCreditoRN(ReceitaCreditoRN receitaCreditoRN) {
        this.receitaCreditoRN = receitaCreditoRN;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Receitacredito getReceitacredito() {
        return receitacredito;
    }

    public void setReceitacredito(Receitacredito receitacredito) {
        this.receitacredito = receitacredito;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public int getVencimento() {
        return vencimento;
    }

    public void setVencimento(int vencimento) {
        this.vencimento = vencimento;
    }

    public BigDecimal getValorIni() {
        return valorIni;
    }

    public void setValorIni(BigDecimal valorIni) {
        this.valorIni = valorIni;
    }

    public BigDecimal getValorFim() {
        return valorFim;
    }

    public void setValorFim(BigDecimal valorFim) {
        this.valorFim = valorFim;
    }

    public int getPgFiltro() {
        return pgFiltro;
    }

    public void setPgFiltro(int pgFiltro) {
        this.pgFiltro = pgFiltro;
    }

    public ReceitaDataModelo getReceitaDataModelo() {
        return receitaDataModelo;
    }

    public void setReceitaDataModelo(ReceitaDataModelo receitaDataModelo) {
        this.receitaDataModelo = receitaDataModelo;
    }

    public Receita[] getSelectReceitas() {
        return selectReceitas;
    }

    public void setSelectReceitas(Receita[] selectReceitas) {
        this.selectReceitas = selectReceitas;
    }

    public Receita[] getSelectReceitasLibera() {
        return selectReceitasLibera;
    }

    public void setSelectReceitasLibera(Receita[] selectReceitasLibera) {
        this.selectReceitasLibera = selectReceitasLibera;
    }

    public Receita[] getSelectReceitasLiberados() {
        return selectReceitasLiberados;
    }

    public void setSelectReceitasLiberados(Receita[] selectReceitasLiberados) {
        this.selectReceitasLiberados = selectReceitasLiberados;
    }

    public String getNunDocumento() {
        return nunDocumento;
    }

    public void setNunDocumento(String nunDocumento) {
        this.nunDocumento = nunDocumento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public BoletoRN getBoletoRN() {
        return boletoRN;
    }

    public void setBoletoRN(BoletoRN boletoRN) {
        this.boletoRN = boletoRN;
    }

    public List<Receita> getReceitaCliente() {

        return receitaCliente;
    }

    public void setReceitaCliente(List<Receita> receitaCliente) {
        this.receitaCliente = receitaCliente;
    }

    public List<Receita> getReceitaParceiro() {
        return receitaParceiro;
    }

    public void setReceitaParceiro(List<Receita> receitaParceiro) {
        this.receitaParceiro = receitaParceiro;
    }

    public List<Receita> getReceitaGoverno() {
        return receitaGoverno;
    }

    public void setReceitaGoverno(List<Receita> receitaGoverno) {
        this.receitaGoverno = receitaGoverno;
    }

}
