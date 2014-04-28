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
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Min;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

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
import java.math.BigInteger;
import java.math.RoundingMode;

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

    @PostConstruct
    private void init() {
        this.filtroLista = getCapaLoteJornalRN().listarSemGestor();
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

    }

    public void calculaRanking(ActionEvent event) {
        this.equipe = new ArrayList<>();
        List lista = getCapaLoteJornalRN().rankingEquipe(getMes(), getAno());
        List<Venda> vendas = new ArrayList<>();
        List<String> gerente = new ArrayList<>();
        List<Equipe> equipes = new ArrayList<>();
        for (Object o : lista) {
            Map row = (Map) o;
            String g = (String.valueOf(row.get("0"))) ;
            vendas.add(new Venda(String.valueOf(row.get("0")), String.valueOf(row.get("1")),
                    (Long)row.get("2"),(BigDecimal) row.get("3"), (BigDecimal) row
                    .get("4"), (Long) row.get("5"), (Long) row
                    .get("6"), (Long) row.get("7"), (Long) row
                    .get("8"), (Long) row.get("9")));
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
                if (equipeList.getGerente().getGerente().equals(venda.gerente)) {
                    equipeList.vendaPromotor.add(venda);
                    equipeList.gerente.setTotal(equipeList.gerente.getTotal()+venda.total);
                    equipeList.gerente.setAtivo(equipeList.gerente.getAtivo()+venda.ativo);
                    equipeList.gerente.setCancelado(equipeList.gerente.getCancelado()+venda.cancelado);
                    equipeList.gerente.setEstornado(equipeList.gerente.getEstornado()+venda.estornado);
                    equipeList.gerente.setAgendado(equipeList.gerente.getAgendado()+venda.agendado);
                    equipeList.gerente.setPendente(equipeList.gerente.getAgendado()+venda.agendado);
                    equipeList.gerente.setFaturado(equipeList.gerente.getFaturado().add(venda.faturado));
                    equipeList.gerente.setVenda(equipeList.gerente.getVenda().add(venda.venda));
                }
            }
            this.equipe.add(equipeList);
        }

    }
    
    public void calculaRankingFaturado(ActionEvent event) {
        this.faturamentoEquipe = new ArrayList<>();
        List lista = getCapaLoteJornalRN().rankingEquipeFaturado(getMes(), getAno(),getDiaFechamento());
        List<Venda> vendas = new ArrayList<>();
        List<String> gerente = new ArrayList<>();
        List<Equipe> equipes = new ArrayList<>();
        for (Object o : lista) {
            Map row = (Map) o;
            String g = (String.valueOf(row.get("0"))) ;
            /*
            0:gerente
            1:promotor
            2:quantidade vendas faturadas
            3:valor total das vendas faturadas
            4:media das vendas
            */
            vendas.add(new Venda(String.valueOf(row.get("0")), String.valueOf(row.get("1")),
                    (Long)row.get("2"),(BigDecimal) row.get("3"), (Double) row
                    .get("4"),new BigDecimal(0)));
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
                if (equipeList.getGerente().getGerente().equals(venda.gerente)) {
                    equipeList.vendaPromotor.add(venda);
                    equipeList.gerente.setTotal(equipeList.gerente.getTotal()+venda.total);
                    equipeList.gerente.setMedia(equipeList.gerente.getMedia()+venda.media);
                    equipeList.gerente.setVenda(equipeList.gerente.getVenda().add(venda.venda));
                }
            }
            BigDecimal v = equipeList.gerente.getVenda().divide(new BigDecimal(equipeList.gerente.getTotal().intValue()),2, RoundingMode.HALF_UP);
            equipeList.gerente.setMedia(new Double(v.toString()));
            this.faturamentoEquipe.add(equipeList);
        }

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
        init();
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

    public class Venda {

        private String promotor = "";
        private String gerente = "";
        private Long total = (long) 0;
        private BigDecimal venda = new BigDecimal(0);
        private BigDecimal faturado = new BigDecimal(0);
        private Long ativo = (long) 0;
        private Long cancelado = (long) 0;
        private Long pendente = (long) 0;
        private Long agendado = (long) 0;
        private Long estornado = (long) 0;
        private BigDecimal valorEstorno = new BigDecimal(0);;
        private Double media = new Double("0");

        public Venda(String gerente, String promotor, Long total,
                BigDecimal venda, BigDecimal faturado, Long ativo,
                Long cancelado, Long pendente, Long agendado,
                Long estornado) {
            this.gerente = gerente;
            this.promotor = promotor;
            this.total = total;
            this.venda = venda;
            this.faturado = faturado;
            this.ativo = ativo;
            this.cancelado = cancelado;
            this.pendente = pendente;
            this.agendado = agendado;
            this.estornado = estornado;
        }

        public Venda(String gerente, String promotor, Long total,
                BigDecimal venda, Double media,BigDecimal valorEstorno) {
            this.gerente = gerente;
            this.promotor = promotor;
            this.total = total;
            this.venda = venda;
            this.media = media;
            this.valorEstorno = valorEstorno;
        }
        
        

        public Venda() {
            // TODO Auto-generated constructor stub
        }

        public Venda(String g) {
            this.gerente = g;
            // TODO Auto-generated constructor stub
        }

        public String getGerente() {
            return gerente;
        }

        public void setGerente(String gerente) {
            this.gerente = gerente;
        }

        public String getPromotor() {
            return promotor;
        }

        public void setPromotor(String promotor) {
            this.promotor = promotor;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }

        
        public BigDecimal getVenda() {
            return venda;
        }

        public void setVenda(BigDecimal venda) {
            this.venda = venda;
        }

        public BigDecimal getFaturado() {
            return faturado;
        }

        public void setFaturado(BigDecimal faturado) {
            this.faturado = faturado;
        }

        public Long getAtivo() {
            return ativo;
        }

        public void setAtivo(Long ativo) {
            this.ativo = ativo;
        }

        public Long getCancelado() {
            return cancelado;
        }

        public void setCancelado(Long cancelado) {
            this.cancelado = cancelado;
        }

        public Long getPendente() {
            return pendente;
        }

        public void setPendente(Long pendente) {
            this.pendente = pendente;
        }

        public Long getAgendado() {
            return agendado;
        }

        public void setAgendado(Long agendado) {
            this.agendado = agendado;
        }

        public Long getEstornado() {
            return estornado;
        }

        public void setEstornado(Long estornado) {
            this.estornado = estornado;
        }

        public BigDecimal getValorEstorno() {
            return valorEstorno;
        }

        public void setValorEstorno(BigDecimal valorEstorno) {
            this.valorEstorno = valorEstorno;
        }

        public Double getMedia() {
            return media;
        }

        public void setMedia(Double media) {
            this.media = media;
        }
        
        
        
        

    }

}
