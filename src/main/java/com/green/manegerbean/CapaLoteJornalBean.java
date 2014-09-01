/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Brinde;
import com.green.modelo.Brindecapalote;
import com.green.modelo.Capalotejornal;
import com.green.modelo.Equipevenda;
import com.green.modelo.Histaltlote;
import com.green.modelo.Planovendaparcela;
import com.green.modelo.Tipopagamento;
import com.green.modelo.Vendaestorno;
import com.green.rn.CapaLoteJornalRN;
import com.green.rn.HistaltloteRN;
import com.green.rn.VendaestornoRN;
import com.green.util.ContextoUtil;
import com.green.util.RelatorioUtil;
import com.green.util.Venda;
import com.green.view.CapaloteDataModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Min;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author leandro.silva
 */
@SuppressWarnings("serial")
@ManagedBean(name = "capaLoteJornalBean")
@ViewScoped
public class CapaLoteJornalBean implements Serializable {

    private Capalotejornal capalotejornal;
    @ManagedProperty("#{capaLoteJornalRN}")
    private CapaLoteJornalRN capaLoteJornalRN;
    @ManagedProperty("#{histaltloteRN}")
    private HistaltloteRN histaltloteRN;
    @ManagedProperty("#{vendaestornoRN}")
    private VendaestornoRN vendaestornoRN;
    private Planovendaparcela planovendaparcela;
    private Vendaestorno vendaestorno;
    private String gerador = "";
    private java.util.List<Capalotejornal> filtroLista;
    private java.util.List<Capalotejornal> filtro;
    private int mes;
    private int ano;
    private int status;
    private int diaFechamento;
    private Equipevenda equipevenda;
    private Date datagerador;
    private String numeroGestor;
    @Min(value = 1, message = "Valor nao pode ser igual a 0!")
    private BigDecimal valorAlt;
    private String cliente;
    private int contrato;
    private List<Equipe> equipe;
    private List<Equipe> faturamentoEquipe;
    private CapaloteDataModel capaloteDataModel;
    private Venda vendaGeral;
    private Venda vendaFaturado;

    @PostConstruct
    private void init() {
       // this.filtroLista = getCapaLoteJornalRN().listarSemGestor();
        this.capalotejornal = new Capalotejornal();
        this.capalotejornal.setStatus(1);
        this.capalotejornal.setStatusBrinde(true);
        this.capalotejornal.setIDTipopagamento(new Tipopagamento());
        this.capalotejornal
                .setBrindecapaloteList(new ArrayList<Brindecapalote>());
        this.planovendaparcela = new Planovendaparcela();
        this.equipevenda = new Equipevenda();
        this.vendaestorno = new Vendaestorno();
        this.status = 5;
        this.equipe = new ArrayList<>();
        this.faturamentoEquipe = new ArrayList<>();
        this.vendaGeral = new Venda();
        this.vendaFaturado = new Venda();

    }

    public void onRowSelect(SelectEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        capalotejornal = (Capalotejornal) event.getObject();
        context.update("forIncGestor:dialog");
        context.execute("PF('dialogIncGestor').show()");
    }

    public void calculaRanking(ActionEvent event) {
        this.vendaGeral = new Venda();
        this.equipe = new ArrayList<>();
        List lista = getCapaLoteJornalRN().rankingEquipe(getMes(), getAno());
        List<Venda> vendas = new ArrayList<>();
        List<String> gerente = new ArrayList<>();
        List<Equipe> equipes = new ArrayList<>();
        for (Object o : lista) {
            Map row = (Map) o;
            String g = (String.valueOf(row.get("0")));
            vendas.add(new Venda(String.valueOf(row.get("0")), String.valueOf(row.get("1")),
                    (Long) row.get("2"),
                    (BigDecimal) row.get("3"),
                    (BigDecimal) row.get("4"),
                    (Long) row.get("5"),
                    (Long) row.get("6"),
                    (Long) row.get("7"),
                    (Long) row.get("8"),
                    (Long) row.get("9"),
                    (BigDecimal) row.get("11"),
                    (BigDecimal) row.get("12"),
                    (BigDecimal) row.get("13"),
                    (BigDecimal) row.get("14")));
            // se nao tiver o gerente na lista de gerentes addiciona um novo
            // gerente
            if (!gerente.contains(g)) {
                gerente.add(g);
                Equipe eq = new Equipe(new Venda(g), null);
                equipes.add(eq);
            }
        }

        for (Equipe equipeList : equipes) {
            equipeList.vendaPromotor = new ArrayList<>();

            for (Venda venda : vendas) {
                if (equipeList.getGerente().getGerente().equals(venda.getGerente())) {
                    equipeList.vendaPromotor.add(venda);
                    equipeList.gerente.setTotal(equipeList.gerente.getTotal() + venda.getTotal());
                    this.vendaGeral.setTotal(this.vendaGeral.getTotal() + venda.getTotal());
                    equipeList.gerente.setAtivo(equipeList.gerente.getAtivo() + venda.getAtivo());
                    this.vendaGeral.setAtivo(this.vendaGeral.getAtivo() + venda.getAtivo());
                    equipeList.gerente.setCancelado(equipeList.gerente.getCancelado() + venda.getCancelado());
                    this.vendaGeral.setCancelado(this.vendaGeral.getCancelado() + venda.getCancelado());
                    equipeList.gerente.setRenovado(equipeList.gerente.getRenovado() + venda.getRenovado());
                    this.vendaGeral.setRenovado(this.vendaGeral.getRenovado() + venda.getRenovado());
                    equipeList.gerente.setAgendado(equipeList.gerente.getAgendado() + venda.getAgendado());
                    this.vendaGeral.setAgendado(this.vendaGeral.getAgendado() + venda.getAgendado());
                    equipeList.gerente.setPendente(equipeList.gerente.getPendente() + venda.getPendente());
                    this.vendaGeral.setPendente(this.vendaGeral.getPendente() + venda.getPendente());
                    equipeList.gerente.setValorAtivo(equipeList.gerente.getValorAtivo().add(venda.getValorAtivo()));
                    this.vendaGeral.setValorAtivo(this.vendaGeral.getValorAtivo().add(venda.getValorAtivo()));
                    equipeList.gerente.setValorTotal(equipeList.gerente.getValorTotal().add(venda.getValorTotal()));
                    this.vendaGeral.setValorTotal(this.vendaGeral.getValorTotal().add(venda.getValorTotal()));
                    equipeList.gerente.setValorAgendado(equipeList.gerente.getValorAgendado().add(venda.getValorAgendado()));
                    this.vendaGeral.setValorAgendado(this.vendaGeral.getValorAgendado().add(venda.getValorAgendado()));
                    equipeList.gerente.setValorCancelado(equipeList.gerente.getValorCancelado().add(venda.getValorCancelado()));
                    this.vendaGeral.setValorCancelado(this.vendaGeral.getValorCancelado().add(venda.getValorCancelado()));
                    equipeList.gerente.setValorPendente(equipeList.gerente.getValorPendente().add(venda.getValorPendente()));
                    this.vendaGeral.setValorPendente(this.vendaGeral.getValorPendente().add(venda.getValorPendente()));
                    equipeList.gerente.setValorRenovado(equipeList.gerente.getValorRenovado().add(venda.getValorRenovado()));
                    this.vendaGeral.setValorRenovado(this.vendaGeral.getValorRenovado().add(venda.getValorRenovado()));
                }
            }
            this.equipe.add(equipeList);
        }

    }

    public void geraPDF() {
        List<Venda> vendas = new ArrayList<>();
        for (Equipe e : getEquipe()) {
            for (Venda venda : e.getVendaPromotor()) {
                vendas.add(venda);
            }
        }
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("total", getVendaGeral().getTotal().toString());
        parametros.put("totalValor", getVendaGeral().getValorTotal());
        parametros.put("ativo", getVendaGeral().getAtivo().toString());
        parametros.put("ativoValor", getVendaGeral().getValorAtivo());
        parametros.put("cancel", getVendaGeral().getCancelado().toString());
        parametros.put("cancelValor", getVendaGeral().getValorCancelado());
        parametros.put("agend", getVendaGeral().getAgendado().toString());
        parametros.put("agendValor", getVendaGeral().getValorAgendado());
        parametros.put("pend", getVendaGeral().getPendente().toString());
        parametros.put("pendValor", getVendaGeral().getValorPendente());
        parametros.put("renovado", getVendaGeral().getRenovado().toString());
        parametros.put("renovadoValor", getVendaGeral().getValorRenovado());
        parametros.put("mes", getMes());
        parametros.put("ano", getAno());
        parametros.put("Usuario", ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario().getIDPessoa().getRazao());
        JRDataSource jrds = new JRBeanCollectionDataSource(vendas);
        RelatorioUtil.geraRelatorioBean("ranking_parcial", jrds, parametros);
    }
    
    public void geraPdfFaturado() {
        List<Venda> vendas = new ArrayList<>();
        for (Equipe e : getEquipe()) {
            for (Venda venda : e.getVendaPromotor()) {
                vendas.add(venda);
            }
        }
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("mes", getMes());
        parametros.put("ano", getAno());
        parametros.put("usuario", ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario().getIDPessoa().getRazao());
        JRDataSource jrds = new JRBeanCollectionDataSource(vendas);
        RelatorioUtil.geraRelatorioBean("ranking_faturado", jrds, parametros);
    }

    public void calculaRankingFaturado(ActionEvent event) {
        this.faturamentoEquipe = new ArrayList<>();
        this.vendaFaturado = new Venda();
        List lista = getCapaLoteJornalRN().rankingEquipeFaturado(getMes(), getAno(), getDiaFechamento());
        List<Venda> vendas = new ArrayList<>();
        List<String> gerente = new ArrayList<>();
        List<Equipe> equipes = new ArrayList<>();
        for (Object o : lista) {
            Map row = (Map) o;
            String g = (String.valueOf(row.get("0")));
            /*
             0:gerente
             1:promotor
             2:quantidade vendas faturadas
             3:valor total das vendas faturadas
             4:media das vendas
             */
            vendas.add(new Venda(String.valueOf(row.get("0")), String.valueOf(row.get("1")),
                    (Long) row.get("2"), (BigDecimal) row.get("3"), (Double) row
                    .get("4"), new BigDecimal(0)));
            // se nao tiver o gerente na lista de gerentes addiciona um novo
            // gerente
            if (!gerente.contains(g)) {
                gerente.add(g);
                Equipe eq = new Equipe(new Venda(g), null);
                equipes.add(eq);
            }
        }
        for (Equipe equipeList : equipes) {
            equipeList.vendaPromotor = new ArrayList<>();
            for (Venda venda : vendas) {
                if (equipeList.getGerente().getGerente().equals(venda.getGerente())) {
                    equipeList.vendaPromotor.add(venda);
                    equipeList.gerente.setAtivo(equipeList.gerente.getAtivo() + venda.getAtivo());
                    this.vendaFaturado.setAtivo(this.vendaFaturado.getAtivo() + venda.getAtivo());
                    equipeList.gerente.setMedia(equipeList.gerente.getMedia() + venda.getMedia());
                    this.vendaFaturado.setMedia(this.vendaFaturado.getMedia() + venda.getMedia());
                    equipeList.gerente.setValorAtivo(equipeList.gerente.getValorAtivo().add(venda.getValorAtivo()));
                    this.vendaFaturado.setValorAtivo(this.vendaFaturado.getValorAtivo().add(venda.getValorAtivo()));
                }
            }
            BigDecimal v = equipeList.gerente.getValorAtivo().divide(new BigDecimal(equipeList.gerente.getAtivo().intValue()), 2, RoundingMode.HALF_UP);
            equipeList.gerente.setMedia(new Double(v.toString()));
            this.faturamentoEquipe.add(equipeList);
        }
        BigDecimal vf = this.vendaFaturado.getValorAtivo().divide(new BigDecimal(this.vendaFaturado.getAtivo().intValue()), 2, RoundingMode.HALF_UP);
        this.vendaFaturado.setMedia(new Double(vf.toString()));
    }

    public void salvar(ActionEvent event) {
        this.capalotejornal.setIDGerente(getEquipevenda().getIDGerente());
        this.capalotejornal.setIDFuncionarioPromotor(getEquipevenda()
                .getIDPromotor());
        this.capalotejornal
                .setValor(getPlanovendaparcela().getValor().multiply(
                                new BigDecimal(getPlanovendaparcela().getQtdParcela())));
        this.capalotejornal.setModalidade(getPlanovendaparcela()
                .getQtdParcela());
        getCapaLoteJornalRN().salvar(capalotejornal);
        this.capalotejornal = new Capalotejornal();
        this.capalotejornal.setStatus(1);
        this.capalotejornal.setStatusBrinde(true);
        this.capalotejornal.setIDTipopagamento(new Tipopagamento());
        this.capalotejornal
                .setBrindecapaloteList(new ArrayList<Brindecapalote>());
        this.planovendaparcela = new Planovendaparcela();
        this.equipevenda = new Equipevenda();
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet pagina = wb.getSheetAt(0);
        pagina.setColumnWidth(1, 10000);
        pagina.setColumnWidth(2, 10000);
        HSSFRow coluna = pagina.getRow(0);

        HSSFCellStyle corLinha = wb.createCellStyle();
        corLinha.setFillForegroundColor(HSSFColor.GREEN.index);
        corLinha.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < coluna.getPhysicalNumberOfCells(); i++) {
            HSSFCell celula = coluna.getCell(i);

            celula.setCellStyle(corLinha);
        }
    }

    /*
     * efetua busca pelo numero do contrato fisico atraves e uma busca
     * especifica entao todos os numeros tem de estar corretos
     */
    public void buscarPorNumeroLike(ActionEvent event) {
        setCapalotejornal(getCapaLoteJornalRN().buscarPorNumeroLike(
                getContrato()));
        if (getCapalotejornal() == null) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('capaloteErro').show();");
            this.capalotejornal = new Capalotejornal();
        } else {
            setContrato(getCapalotejornal().getContrato());
            setCliente(getCapalotejornal().getNome());
            setNumeroGestor(getCapalotejornal().getGerador());
            setValorAlt(getCapalotejornal().getValor());
        }
    }

    /*
     * efetua busca pelo numero do gestor atraves de um like no banco se ouver
     * mais de um retorno somente ira mostrara o primeiro da lista
     */
    public void buscaPorGestorLike(ActionEvent event) {
        setCapalotejornal(getCapaLoteJornalRN().buscarPorGestorLike(
                getNumeroGestor()));
        if (getCapalotejornal() == null) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('capaloteErro').show();");
            this.numeroGestor = "";
        } else {
            setContrato(getCapalotejornal().getContrato());
            setCliente(getCapalotejornal().getNome());
            setNumeroGestor(getCapalotejornal().getGerador());
            setValorAlt(getCapalotejornal().getValor());
        }
    }

    /*
     * efetua busca pelo nome do cliente atraves de um like no banco se ouver
     * mais de um retorno somente ira mostrara o primeiro da lista
     */
    public void buscaPorClienteLike(ActionEvent event) {
        setCapalotejornal(getCapaLoteJornalRN().buscarPorClienteLike(
                getCliente()));
        if (getCapalotejornal() == null) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('capaloteErro').show();");
            this.cliente = "";
        } else {
            setContrato(getCapalotejornal().getContrato());
            setCliente(getCapalotejornal().getNome());
            setNumeroGestor(getCapalotejornal().getGerador());
            setValorAlt(getCapalotejornal().getValor());
        }
    }

    public void buscarPorNumero(ActionEvent event) {
        setCapalotejornal(getCapaLoteJornalRN().buscarPorNumero(getContrato()));
        if (getCapalotejornal() == null) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('capaloteErro').show();");
            this.capalotejornal = new Capalotejornal();
        } else {
            setContrato(getCapalotejornal().getContrato());
            setCliente(getCapalotejornal().getNome());
            setNumeroGestor(getCapalotejornal().getGerador());
            setValorAlt(getCapalotejornal().getValor());
        }
    }

    public void buscaPorGestor(ActionEvent event) {
        setCapalotejornal(getCapaLoteJornalRN().buscaPorGestor(
                getNumeroGestor()));
        if (getCapalotejornal() == null) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('capaloteErro').show();");
            this.numeroGestor = "";
        } else {
            setContrato(getCapalotejornal().getContrato());
            setCliente(getCapalotejornal().getNome());
            setNumeroGestor(getCapalotejornal().getGerador());
            setValorAlt(getCapalotejornal().getValor());
        }
    }

    public void buscaPorCliente(ActionEvent event) {
        setCapalotejornal(getCapaLoteJornalRN().buscaPorCliente(getCliente()));
        if (getCapalotejornal() == null) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('capaloteErro').show();");
            this.cliente = "";
        } else {
            setContrato(getCapalotejornal().getContrato());
            setCliente(getCapalotejornal().getNome());
            setNumeroGestor(getCapalotejornal().getGerador());
            setValorAlt(getCapalotejornal().getValor());
        }
    }

    public void atualizar(ActionEvent event) {
        if (getPlanovendaparcela() != null) {
            getCapalotejornal().setValor(
                    getPlanovendaparcela().getValor().multiply(
                            new BigDecimal(getPlanovendaparcela()
                                    .getQtdParcela())));
            getCapalotejornal().setModalidade(
                    getPlanovendaparcela().getQtdParcela());
        }
        getCapaLoteJornalRN().atualizar(getCapalotejornal());
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Atualizado com sucesso!", "Atualizado com sucesso!"));
    }

    public void atualizarValor(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        getCapaLoteJornalRN()
                .atualizarValor(getCapalotejornal(), getValorAlt());
        context.update("formEditCapasLote");
        context.execute("PF('dialogEditVenda').hide();");
    }

    public void estornoDeVenda(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        if (getCapalotejornal().getStatus() == 1) {
            getVendaestorno()
                    .setDTGestor(getCapalotejornal().getDTIncGerador());
            getVendaestorno().setDTVenda(getCapalotejornal().getDTVenda());
            getVendaestorno().setIDCapalotejornal(getCapalotejornal());
            getVendaestornoRN().salvar(getVendaestorno());
            getCapalotejornal().setStatus(5);
            getCapaLoteJornalRN().atualizar(getCapalotejornal());
            getCapalotejornal().getVendaestornoList().add(getVendaestorno());
            setVendaestorno(new Vendaestorno());
            FacesContext.getCurrentInstance()
                    .addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Estornado com sucesso!",
                                    "Estornado com sucesso!"));
            context.update("formEditCapasLote");
            context.execute("PF('dialogEstornoVenda').hide();");
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Só e possivel estornar vendas ativas!",
                            "Só e possivel estornar vendas ativas!"));
            context.update("formEditCapasLote");
            context.execute("PF('dialogEstornoVenda').hide();");
        }

    }

    public void atualizaGestor(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        getCapaLoteJornalRN().atualizar(getCapalotejornal());
        context.execute("PF('dialogIncGestor').hide();");
        context.update("forIncGestor");

    }

    public void atualizarStatus(int tipo) {
        RequestContext context = RequestContext.getCurrentInstance();

        switch (tipo) {
            case 0:
                getCapalotejornal().setStatus(tipo);
                getCapaLoteJornalRN().atualizar(getCapalotejornal());
                context.execute("PF('DialogcancelCapaLote').hide();");
                break;
            case 1:
                getCapalotejornal().setStatus(tipo);
                getCapaLoteJornalRN().atualizar(getCapalotejornal());
                context.execute("PF('DialogAtivarCapaLote').hide();");
                break;
            case 2:
                getCapalotejornal().setStatus(tipo);
                getCapaLoteJornalRN().atualizar(getCapalotejornal());
                context.execute("PF('DialogsuspCapaLote').hide();");
                break;
            case 3:
                Calendar c = Calendar.getInstance();
                c.set(Calendar.DAY_OF_MONTH, 1);
                c.set(Calendar.MONTH, getMes());
                c.set(Calendar.YEAR, getAno());

                getHistaltloteRN().salvar(
                        new Histaltlote(null, getCapalotejornal().getDTVenda(), c
                                .getTime(), new Date(), ContextoUtil
                                .getContextoBean().getUsuarioLogado(),
                                getCapalotejornal()));
                getCapalotejornal().setDTVenda(c.getTime());
                getCapalotejornal().setAltLote(Boolean.TRUE);
                getCapaLoteJornalRN().atualizar(getCapalotejornal());
                context.execute("PF('DialogAltCapaLote').hide();");
                break;

        }
        context.update("formEditCapasLote");
    }

    public Capalotejornal getCapalotejornal() {
        return capalotejornal;
    }

    public void addRemove(int ops, Brinde b) {
        Brindecapalote bc = new Brindecapalote();
        bc.setIDCapalotejornal(getCapalotejornal());
        bc.setIDBrinde(b);
        switch (ops) {
            case 1:
                getCapalotejornal().getBrindecapaloteList().add(bc);
                break;
            case 2:
                int i = 0;
                for (Brindecapalote b1 : getCapalotejornal()
                        .getBrindecapaloteList()) {
                    if (b1.getIDBrinde().getDescricao()
                            .equals(bc.getIDBrinde().getDescricao())) {
                        getCapalotejornal().getBrindecapaloteList().remove(i);
                        break;
                    }
                    i = i + 1;
                }

                break;
        }
    }

    public void onEdit(RowEditEvent event) {

        Capalotejornal cp = (Capalotejornal) event.getObject();
        cp.setDTIncGerador(getDatagerador());
        getCapaLoteJornalRN().atualizarGerador(cp, getGerador());
        setGerador("");
        setDatagerador(new Date());
        this.filtroLista = getCapaLoteJornalRN().listarSemGestor();

    }

    public void editarVenda(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        setValorAlt(getCapalotejornal().getValor());
        context.update("formEditVenda");
        context.execute("PF('dialogEditVenda').show();");
    }

    public void pesquisar(ActionEvent event) {
        setFiltro(getCapaLoteJornalRN().listarComFiltro(getMes() + 1, getAno(),
                getStatus()));
    }

    @SuppressWarnings("unused")
    public void onCancel(RowEditEvent event) {
        Capalotejornal cp = (Capalotejornal) event.getObject();
    }

    public void setCapalotejornal(Capalotejornal capalotejornal) {
        this.capalotejornal = capalotejornal;
    }

    public CapaLoteJornalRN getCapaLoteJornalRN() {
        return capaLoteJornalRN;
    }

    public void setCapaLoteJornalRN(CapaLoteJornalRN capaLoteJornalRN) {
        this.capaLoteJornalRN = capaLoteJornalRN;
    }

    public Planovendaparcela getPlanovendaparcela() {
        return planovendaparcela;
    }

    public void setPlanovendaparcela(Planovendaparcela planovendaparcela) {
        this.planovendaparcela = planovendaparcela;
    }

    public String getGerador() {
        return gerador;
    }

    public void setGerador(String gerador) {
        this.gerador = gerador;
    }

    public HistaltloteRN getHistaltloteRN() {
        return histaltloteRN;
    }

    public void setHistaltloteRN(HistaltloteRN histaltloteRN) {
        this.histaltloteRN = histaltloteRN;
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

    public Equipevenda getEquipevenda() {
        return equipevenda;
    }

    public void setEquipevenda(Equipevenda equipevenda) {
        this.equipevenda = equipevenda;
    }

    public Date getDatagerador() {
        return datagerador;
    }

    public void setDatagerador(Date datagerador) {
        this.datagerador = datagerador;
    }

    public List<Capalotejornal> getFiltroLista() {
        return filtroLista;
    }

    public void setFiltroLista(java.util.List<Capalotejornal> filtroLista) {
        this.filtroLista = filtroLista;
    }

    public List<Capalotejornal> getFiltro() {
        return filtro;
    }

    public void setFiltro(java.util.List<Capalotejornal> filtro) {
        this.filtro = filtro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNumeroGestor() {
        return numeroGestor;
    }

    public void setNumeroGestor(String numeroGestor) {
        this.numeroGestor = numeroGestor;
    }

    public BigDecimal getValorAlt() {
        return valorAlt;
    }

    public void setValorAlt(BigDecimal valorAlt) {
        this.valorAlt = valorAlt;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getContrato() {
        return contrato;
    }

    public void setContrato(int contrato) {
        this.contrato = contrato;
    }

    public Vendaestorno getVendaestorno() {
        return vendaestorno;
    }

    public void setVendaestorno(Vendaestorno vendaestorno) {
        this.vendaestorno = vendaestorno;
    }

    public VendaestornoRN getVendaestornoRN() {
        return vendaestornoRN;
    }

    public void setVendaestornoRN(VendaestornoRN vendaestornoRN) {
        this.vendaestornoRN = vendaestornoRN;
    }

    public int getDiaFechamento() {
        return diaFechamento;
    }

    public void setDiaFechamento(int diaFechamento) {
        this.diaFechamento = diaFechamento;
    }

    public List<Equipe> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<Equipe> equipe) {
        this.equipe = equipe;
    }

    public List<Equipe> getFaturamentoEquipe() {
        return faturamentoEquipe;
    }

    public void setFaturamentoEquipe(List<Equipe> faturamentoEquipe) {
        this.faturamentoEquipe = faturamentoEquipe;
    }

    public CapaloteDataModel getCapaloteDataModel() {
        this.capaloteDataModel = new CapaloteDataModel(getCapaLoteJornalRN().listarSemGestor());
        return capaloteDataModel;
    }

    public Venda getVendaGeral() {
        return vendaGeral;
    }

    public void setVendaGeral(Venda vendaGeral) {
        this.vendaGeral = vendaGeral;
    }

    public Venda getVendaFaturado() {
        return vendaFaturado;
    }

    public void setVendaFaturado(Venda vendaFaturado) {
        this.vendaFaturado = vendaFaturado;
    }

    public class Equipe {

        private Venda gerente = new Venda();
        private List<Venda> vendaPromotor = new ArrayList<>();

        public Equipe(Venda gerente, List<Venda> vendaPromotor) {
            this.gerente = gerente;
            this.vendaPromotor = vendaPromotor;
        }

        public Equipe() {
            // TODO Auto-generated constructor stub
        }

        public Venda getGerente() {
            return gerente;
        }

        public void setGerente(Venda gerente) {
            this.gerente = gerente;
        }

        public List<Venda> getVendaPromotor() {
            return vendaPromotor;
        }

        public void setVendaPromotor(List<Venda> vendaPromotor) {
            this.vendaPromotor = vendaPromotor;
        }

    }

}
