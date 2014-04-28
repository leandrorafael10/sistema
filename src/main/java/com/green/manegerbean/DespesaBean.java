/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Debito;
import com.green.modelo.Despesa;
import com.green.modelo.Despesadebito;
import com.green.rn.DespesaDebitoRN;
import com.green.rn.DespesaRN;
import com.green.util.ContextoUtil;
import com.green.util.RelatorioUtil;
import com.green.view.DespesaDataModelo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "despesaBean")
@ViewScoped
public class DespesaBean implements Serializable {

    @ManagedProperty("#{despesaRN}")
    private DespesaRN despesaRN;
    @ManagedProperty("#{despesaDebitoRN}")
    private DespesaDebitoRN despesaDebitoRN;
    private Despesa despesaNovo = new Despesa();
    private Despesa despesaFiltro = new Despesa();
    private Despesa despesaPesquisa = new Despesa();
    private Despesa despesaEdit = new Despesa();
    private Despesa despesaDel = new Despesa();
    private Despesadebito despesadebito = new Despesadebito();
    private List<Despesa> despesas;
    private Despesa[] selectDespesas;
    private Despesa[] selectDespesasLibera;
    private Despesa[] selectDespesasLiberados;
    private DespesaDataModelo despesaDataModelo;
    private DespesaDataModelo despesaDataLiberacao;
    private String tipoCedente;
    private int opsCedente;
    private boolean opcFuncionario = false;
    private boolean opsCliente = false;
    private boolean opsFornecedor = false;
    private boolean tipoMulta;
    private boolean tipoDesconto;
    private Date fimFiltroVenc;
    private BigDecimal valorIni;
    private BigDecimal valorFim;
    private Date dataAtual;
    private Date hoje;
    private BigDecimal valorPago = BigDecimal.ZERO;
    private int parcelas = 0;
    private int vencimento = 0;
    private int pgFiltro;
    private static final int INICIO = 0;
    private static final int LIBERACAO = 1;
    private static final int LIBERADO = 2;

    @PostConstruct
    private void init() {
        this.despesaDataModelo = new DespesaDataModelo(getDespesaRN().vencimentosDoDia());
        getDespesaNovo().setValorMulta(BigDecimal.ZERO);
        getDespesaNovo().setValorJuros(BigDecimal.ZERO);
        getDespesaNovo().setValorDesconto(BigDecimal.ZERO);
        valorIni = new BigDecimal("0.00");
        valorFim = new BigDecimal("0.00");
                
        data();
    }

    public void data() {
        Date d1 = new Date();
        Date d = new Date();
        d.setDate(d.getDate() - 1);
        setHoje(d1);
        setDataAtual(d);
    }

    public String salvar(ActionEvent actionEvent) {
        if (isTipoMulta()) {
            BigDecimal percenteMulta = new BigDecimal(getDespesaNovo().getValorMulta().toString());
            percenteMulta = percenteMulta.divide(new BigDecimal("100"));
            getDespesaNovo().setValorMulta(getDespesaNovo().getValorNominal().multiply(percenteMulta));
        }
        if (isTipoDesconto()) {
            BigDecimal percenteMulta = new BigDecimal(getDespesaNovo().getValorDesconto().toString());
            percenteMulta = percenteMulta.divide(new BigDecimal("100"));
            getDespesaNovo().setValorDesconto(getDespesaNovo().getValorNominal().multiply(percenteMulta));
        }
        getDespesaRN().salvar(getDespesaNovo(), getParcelas(), getVencimento());
        return "/tesouraria/lancamento_financeiro/despesa_paga.xhtml";
    }

    public void confirmacaoDespesa(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formDespesaPagar");
        context.execute("dialogConfirmDespesa.show()");
    }

    public void excluirParcial(ActionEvent event) {
        getDespesaRN().excluirParcial(getDespesaDel());
        this.despesaDataModelo = new DespesaDataModelo(getDespesaRN().vencimentosDoDia());
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("forDespesas");
        context.execute("varDialogDespesaDel.hide()");
    }

    public void pagandoDespesa(ActionEvent event) {
        getDespesaDebitoRN().salvar(getDespesadebito(),getValorPago());
        setValorPago(BigDecimal.ZERO);
    }

    public void depesaVisualizacao(Despesa d) {
        RequestContext rc = RequestContext.getCurrentInstance();
        setDespesaPesquisa(d);
        rc.update("for_listar_debito");
        rc.execute("varDialogDespesa.show()");
    }

    public void alterar(ActionEvent event) {
        if (isTipoMulta()) {
            BigDecimal percenteMulta = new BigDecimal(getDespesaEdit().getValorMulta().toString());
            percenteMulta = percenteMulta.divide(new BigDecimal("100"));
            getDespesaEdit().setValorMulta(getDespesaEdit().getValorNominal().multiply(percenteMulta));
        }
        if (isTipoDesconto()) {
            BigDecimal percenteMulta = new BigDecimal(getDespesaEdit().getValorDesconto().toString());
            percenteMulta = percenteMulta.divide(new BigDecimal("100"));
            getDespesaEdit().setValorDesconto(getDespesaEdit().getValorNominal().multiply(percenteMulta));
        }
        setTipoMulta(false);
        setTipoDesconto(false);

        getDespesaRN().atualizar(getDespesaEdit());

    }
   

    public void opcaoCedente() {
        switch (opsCedente) {
            case 0:
                setOpsCliente(false);
                setOpcFuncionario(false);
                setOpsFornecedor(false);
                getDespesaNovo().setIDFuncionario(null);
                getDespesaNovo().setIDFornecedor(null);
                getDespesaNovo().setIDCliente(null);
                getDespesaEdit().setIDFuncionario(null);
                getDespesaEdit().setIDCliente(null);
                getDespesaEdit().setIDFornecedor(null);
                setTipoCedente("");
                break;
            case 1:
                setOpcFuncionario(true);
                setOpsCliente(false);
                setOpsFornecedor(false);
                getDespesaNovo().setIDCliente(null);
                getDespesaEdit().setIDCliente(null);
                getDespesaNovo().setIDFornecedor(null);
                getDespesaEdit().setIDFornecedor(null);
                setTipoCedente("Funcionario: ");
                break;
            case 2:
                setOpsCliente(true);
                setOpcFuncionario(false);
                setOpsFornecedor(false);
                getDespesaNovo().setIDFuncionario(null);
                getDespesaEdit().setIDFuncionario(null);
                getDespesaNovo().setIDFornecedor(null);
                getDespesaEdit().setIDFornecedor(null);
                setTipoCedente("Cliente: ");
                break;
            case 3:
                setOpsCliente(false);
                setOpcFuncionario(false);
                setOpsFornecedor(true);
                getDespesaNovo().setIDFuncionario(null);
                getDespesaEdit().setIDFuncionario(null);
                getDespesaNovo().setIDCliente(null);
                getDespesaEdit().setIDCliente(null);
                setTipoCedente("Fornecedor: ");
                break;
            default:
                break;
        }

    }

    public void filtroDespesa() {
        this.despesaDataModelo = new DespesaDataModelo(getDespesaRN().filtro(getDespesaFiltro(), getFimFiltroVenc(), getPgFiltro(),getValorIni(),getValorFim()));
        setFimFiltroVenc(new Date());
    }

    public void calcularTaxa(Despesa despesa) {
        RequestContext context = RequestContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date diaAtual = new Date();
        BigDecimal valorCalc = BigDecimal.ZERO;
        BigDecimal valorJuros = BigDecimal.ZERO;
        BigDecimal valorMulta = BigDecimal.ZERO;
        int situacaoData = 0;
        if (despesa != null) {
            long dt = (diaAtual.getTime() - despesa.getDTVencimento().getTime()) + 3600000;
            long diasAtraso = (dt / 86400000L);
            if (despesa.getIsentoJuros() == false) {
                if (despesa.getTipoJuros() == false) {
                    valorJuros = despesa.getValorJuros().multiply(BigDecimal.valueOf(diasAtraso));
                } else {
                    LocalDate date2 = new LocalDate(despesa.getDTVencimento());
                    LocalDate date1 = new LocalDate(diaAtual);
                    Months months = Months.monthsBetween(date2, date1);
                    valorJuros = despesa.getValorJuros().multiply(BigDecimal.valueOf(months.getMonths()));
                }
            }
            if (despesa.getIsentoMulta() == false) {
                valorMulta = despesa.getValorMulta();
            }
            if (despesa.getDTDesconto() == null) {
                situacaoData = 1;
            } else {
                try {
                    Date d1 = format.parse(format.format(diaAtual));
                    Date d2 = format.parse(format.format(despesa.getDTDesconto()));
                    situacaoData = d2.compareTo(d1);
                } catch (ParseException ex) {
                    Logger.getLogger(DespesaBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //aqui o desconto ainda não foi cedido e a data limite para esse desconto nao pode ser maior que a data atual.
            if (situacaoData >= 0) {
                valorCalc = despesa.getValorLiquido();
                valorCalc = valorCalc.add(valorJuros.add(valorMulta)).subtract(despesa.getValorDesconto());
            } else {
                valorCalc = despesa.getValorLiquido();
                valorCalc = valorCalc.add(valorJuros.add(valorMulta));
            }
            despesa.setValorLiquido(valorCalc);
            getDespesadebito().setIDDespesa(despesa);
            getDespesadebito().setIDDebito(new Debito());
            context.update("for_listar_debito");
            context.update("forPagar_debito");
            context.execute("varDialogDespesaPagar.show()");
        }
    }

    public void autorizaPagamento(ActionEvent event) {
        getDespesaRN().autorizaPagamento(getSelectDespesas(), LIBERACAO);
        this.despesaDataModelo = new DespesaDataModelo(getDespesaRN().vencimentosDoDia());
    }

    public void liberaPagamento(ActionEvent event) {
        getDespesaRN().autorizaPagamento(getSelectDespesasLibera(), LIBERADO);
        this.despesaDataModelo = new DespesaDataModelo(getDespesaRN().vencimentosDoDia());
    }

    public void estornoPagamento(ActionEvent event) {
        getDespesaRN().autorizaPagamento(getSelectDespesasLibera(), INICIO);
        this.despesaDataModelo = new DespesaDataModelo(getDespesaRN().vencimentosDoDia());
    }
    public void estornoLiberacaoPagamento(ActionEvent event) {
        getDespesaRN().autorizaPagamento(getSelectDespesasLiberados(), INICIO);
        this.despesaDataModelo = new DespesaDataModelo(getDespesaRN().vencimentosDoDia());
    }

    public void geraRelatorioPDF() {
        List<Despesa> ds = new ArrayList<Despesa>();

        for (Despesa d : getSelectDespesasLibera()) {
            ds.add(d);
        }
        HashMap<String,Object> parametros = new HashMap<>();
        parametros.put("Usuario", ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario().getIDPessoa().getRazao());
        JRDataSource jrds = new JRBeanCollectionDataSource(ds);
        RelatorioUtil.geraRelatorioBean("rel_despesas", jrds,parametros);
    }
    public void geraPDF() {
        List<Despesa> ds = new ArrayList<>();

        for (Despesa d : getDespesaDataModelo()) {
            ds.add(d);
        }
        HashMap<String,Object> parametros = new HashMap<>();
        parametros.put("Usuario", ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario().getIDPessoa().getRazao());
        JRDataSource jrds = new JRBeanCollectionDataSource(ds);
        RelatorioUtil.geraRelatorioBean("novo_despesas", jrds,parametros);
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    public Date getHoje() {
        return hoje;
    }

    public void setHoje(Date hoje) {
        this.hoje = hoje;
    }

   

    

    public Date getFimFiltroVenc() {
        return fimFiltroVenc;
    }

    public void setFimFiltroVenc(Date fimFiltroVenc) {
        this.fimFiltroVenc = fimFiltroVenc;
    }

    public Despesa getDespesaEdit() {
        return despesaEdit;
    }

    public void setDespesaEdit(Despesa despesaEdit) {
        this.despesaEdit = despesaEdit;
    }

    public Despesa getDespesaDel() {
        return despesaDel;
    }

    public void setDespesaDel(Despesa despesaDel) {
        this.despesaDel = despesaDel;
    }

    public Despesa getDespesaPesquisa() {
        return despesaPesquisa;
    }

    public void setDespesaPesquisa(Despesa despesaPesquisa) {
        this.despesaPesquisa = despesaPesquisa;
    }

    public Despesa getDespesaFiltro() {
        return despesaFiltro;
    }

    public void setDespesaFiltro(Despesa despesaFiltro) {
        this.despesaFiltro = despesaFiltro;
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
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

    public Despesa getDespesaNovo() {
        return despesaNovo;
    }

    public void setDespesaNovo(Despesa despesaNovo) {
        this.despesaNovo = despesaNovo;
    }

    public DespesaDebitoRN getDespesaDebitoRN() {
        return despesaDebitoRN;
    }

    public void setDespesaDebitoRN(DespesaDebitoRN despesaDebitoRN) {
        this.despesaDebitoRN = despesaDebitoRN;
    }

    public Despesadebito getDespesadebito() {
        return despesadebito;
    }

    public void setDespesadebito(Despesadebito despesadebito) {
        this.despesadebito = despesadebito;
    }

    public DespesaRN getDespesaRN() {
        return despesaRN;
    }

    public void setDespesaRN(DespesaRN despesaRN) {
        this.despesaRN = despesaRN;
    }

    public boolean isOpcFuncionario() {
        return opcFuncionario;
    }

    public void setOpcFuncionario(boolean opcFuncionario) {
        this.opcFuncionario = opcFuncionario;
    }

    public int getOpsCedente() {
        return opsCedente;
    }

    public void setOpsCedente(int opsCedente) {
        this.opsCedente = opsCedente;
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

    public String getTipoCedente() {
        return tipoCedente;
    }

    public void setTipoCedente(String tipoCedente) {
        this.tipoCedente = tipoCedente;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
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

    public DespesaDataModelo getDespesaDataModelo() {
        return despesaDataModelo;
    }

    public void setDespesaDataModelo(DespesaDataModelo despesaDataModelo) {
        this.despesaDataModelo = despesaDataModelo;
    }

    public Despesa[] getSelectDespesas() {
        return selectDespesas;
    }

    public void setSelectDespesas(Despesa[] selectDespesas) {
        this.selectDespesas = selectDespesas;
    }

    public int getPgFiltro() {
        return pgFiltro;
    }

    public void setPgFiltro(int pgFiltro) {
        this.pgFiltro = pgFiltro;
    }

    public DespesaDataModelo getDespesaDataLiberacao() {
        return despesaDataLiberacao;
    }

    public void setDespesaDataLiberacao(DespesaDataModelo despesaDataLiberacao) {
        this.despesaDataLiberacao = despesaDataLiberacao;
    }

    public Despesa[] getSelectDespesasLibera() {
        return selectDespesasLibera;
    }

    public void setSelectDespesasLibera(Despesa[] selectDespesasLibera) {
        this.selectDespesasLibera = selectDespesasLibera;
    }

    public Despesa[] getSelectDespesasLiberados() {
        return selectDespesasLiberados;
    }

    public void setSelectDespesasLiberados(Despesa[] selectDespesasLiberados) {
        this.selectDespesasLiberados = selectDespesasLiberados;
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
    
}
