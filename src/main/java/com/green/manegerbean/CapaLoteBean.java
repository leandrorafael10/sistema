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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.CartesianChartModel;

import com.green.modelo.CapaLote;
import com.green.modelo.Terceiros;
import com.green.rn.CalculoComissaoRN;
import com.green.rn.CapaLoteRN;
import com.green.rn.FuncaoRN;
import com.green.rn.TerceirosRN;
import com.green.util.ComissaoVendas;
import com.green.util.ContextoUtil;
import com.green.util.RelatorioUtil;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "capaLoteBean")
@ViewScoped
public class CapaLoteBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{capaLoteRN}")
    private CapaLoteRN capaLoteRN;
    @ManagedProperty("#{funcaoRN}")
    private FuncaoRN funcaoRN;
    @ManagedProperty("#{terceirosRN}")
    private TerceirosRN terceirosRN;
    @ManagedProperty("#{calculoComissaoRN}")
    private CalculoComissaoRN  calculoComissaoRN;
    private List<CapaLote> capaLotes;
    private List<ComissaoVendas> comissaoVendas = new ArrayList<>();
    private CapaLote capaLote = new CapaLote();
    private ComissaoVendas vendasGerente = new ComissaoVendas();
    private Date inicioLote;
    private Date fimDeLote;
    private int codigoCancel = 0;
    private int mesFiltro;
    private int anoFiltro;
     private CartesianChartModel graficoBarra;

    public CapaLoteBean() {
    }

    public void salvar(ActionEvent event) {

        getCapaLoteRN().salvar(getCapaLote());
        RequestContext.getCurrentInstance().reset("formCapaLote:gridCapaLote");
    }

    public List<CapaLote> listaCancelados() {
        return getCapaLoteRN().listaCanceladoMes(getInicioLote(), getFimDeLote());
    }

    public List<CapaLote> listaVendas() {
        return getCapaLoteRN().listaVendas(getInicioLote(), getFimDeLote());
    }

    public void filtroPeriodo() {
        Calendar cal = GregorianCalendar.getInstance();
        if (!(getAnoFiltro() > 2029) && !(getMesFiltro() == 0)) {
            RequestContext context = RequestContext.getCurrentInstance();
            cal.set(getAnoFiltro(), getMesFiltro() - 1, 1);
            int dia = cal.getMaximum(Calendar.DAY_OF_MONTH);
            setInicioLote(cal.getTime());
            cal.set(getAnoFiltro(), getMesFiltro() - 1, dia);
            setFimDeLote(cal.getTime());
            context.update("formCapaLote");
        }

    }

    public List<ComissaoVendas> calculaComissao(Terceiros gerente) {
        List<CapaLote> capaLot = getCapaLoteRN().listaVendas(getInicioLote(), getFimDeLote());
        List<Terceiros> funcionarios = getTerceirosRN().listarPorFuncao(getFuncaoRN().carregar(5));
        List<ComissaoVendas> comissaoVend = new ArrayList<>();
        setVendasGerente(new ComissaoVendas());
        getVendasGerente().setTerceiros(gerente);
        for (Terceiros f : funcionarios) {
            if (f.getIDTerceirosGerente() != null) {
                if (f.getIDTerceirosGerente().equals(gerente)) {
                    ComissaoVendas cv = new ComissaoVendas();
                    for (CapaLote cl : capaLot) {
                        if (f.equals(cl.getIDTerceiros())) {
                            cv.setVendaBruta(cv.getVendaBruta() + 1);
                            cv.setFatBruto(cv.getFatBruto().add(cl.getValor()));
                            getVendasGerente().setVendaBruta(getVendasGerente().getVendaBruta() + 1);
                            if (cl.getAtivo() == true) {
                                cv.setVendaLiquida(cv.getVendaLiquida() + 1);
                                cv.setFatLiquido(cv.getFatLiquido().add(cl.getValor()));
                                getVendasGerente().setVendaLiquida(getVendasGerente().getVendaLiquida() + 1);
                                getVendasGerente().setFatLiquido(getVendasGerente().getFatLiquido().add(cl.getValor()));
                                cv.setProcetagemComissao(cl.getiDCalculoComissao());
                                getVendasGerente().setProcetagemComissao(cl.getiDCalculoComissao());
                            } else {

                                cv.setFatCancelado(cv.getFatCancelado().add(cl.getValor()));
                                cv.setVendaCancelada(cv.getVendaCancelada() + 1);
                                getVendasGerente().setVendaCancelada(getVendasGerente().getVendaCancelada() + 1);
                            }

                        }
                    }
                    cv.setTerceiros(f);

                    Float porcentagem;
                    if (cv.getVendaLiquida() != 0) {
                        
                        if (cv.getVendaLiquida() <= 50) {
                            porcentagem = cv.getProcetagemComissao().getPorcPromotor1().floatValue();
                            cv.setTipoComissao(porcentagem);

                        } else if (cv.getVendaLiquida() < 99) {
                            porcentagem = cv.getProcetagemComissao().getPorcPromotor2().floatValue();
                            cv.setTipoComissao(porcentagem);
                        } else {
                            porcentagem = cv.getProcetagemComissao().getPorcPromotor3().floatValue();
                            cv.setTipoComissao(porcentagem);
                        }
                        if (cv.getTerceiros().getUf().equals("BA")) {
                            porcentagem = new Float(13.0);
                            cv.setTipoComissao(new Float(13.0));
                        }
                        cv.setValorComissao(cv.getFatLiquido().multiply(new BigDecimal(porcentagem)).divide(new BigDecimal("100")));
                    }
                    comissaoVend.add(cv);
                }
            }
        }
        setComissaoVendas(comissaoVend);
        Float porcentagemGer;
        if (getVendasGerente().getVendaBruta() != 0) {
            if(getVendasGerente().getProcetagemComissao().getIdcalculoComissao()==null){
                getVendasGerente().setProcetagemComissao(getCalculoComissaoRN().carregarUltimo());
            }
            if (getVendasGerente().getFatLiquido().compareTo(new BigDecimal(450000)) <= 0) {
                porcentagemGer = getVendasGerente().getProcetagemComissao().getPorcGerente1().floatValue();
                getVendasGerente().setTipoComissao(porcentagemGer);

            } else if (getVendasGerente().getFatLiquido().compareTo(new BigDecimal(550000)) < 0) {
                porcentagemGer = getVendasGerente().getProcetagemComissao().getPorcGerente2().floatValue();
                getVendasGerente().setTipoComissao(porcentagemGer);
            } else {
                porcentagemGer = getVendasGerente().getProcetagemComissao().getPorcGerente3().floatValue();
                getVendasGerente().setTipoComissao(porcentagemGer);
            }
            if (getVendasGerente().getTerceiros().getUf().equals("BA")) {
                porcentagemGer = new Float(1.0);
                getVendasGerente().setTipoComissao(new Float(1.0));
            }
            getVendasGerente().setValorComissao(getVendasGerente().getFatLiquido().multiply(new BigDecimal(porcentagemGer)).divide(new BigDecimal("100")));
        } else {
            setVendasGerente(new ComissaoVendas(getVendasGerente().getTerceiros(), 0, 0, 0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, null, BigDecimal.ZERO, BigDecimal.ZERO));
        }
        return comissaoVend;
    }

    public void buscaPracancelamento() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (codigoCancel > 0) {
            setCapaLote(getCapaLoteRN().buscaPorLote(codigoCancel));
            if (getCapaLote() != null) {
                if (getCapaLote().getAtivo()) {
                    context.update("formCancelVenda");
                    context.execute("dialogCapaloteCancel.show()");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Contrato já cancelado!", "Contrato já cancelado!"));
                    context.update("formCancelVenda");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Código invalido!", "Código invalido!"));
                context.update("formCancelVenda");
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira o código!", "Insira o código!"));
            context.update("formCancelVenda");
        }
    }

    public void geraRelatorioPDF(Terceiros gerente) {
        JRDataSource jrds = new JRBeanCollectionDataSource(calculaComissao(gerente));
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("gerente", gerente.getNome());
        parametros.put("vdBruta", getVendasGerente().getVendaBruta().toString());
        parametros.put("vdCancel", getVendasGerente().getVendaCancelada().toString());
        parametros.put("vdLiq", getVendasGerente().getVendaLiquida().toString());
        parametros.put("fatBruta", getVendasGerente().getFatBruto().toString());
        parametros.put("fatCancel", getVendasGerente().getFatCancelado().toString());
        parametros.put("fatLiq", getVendasGerente().getFatLiquido().toString());
        parametros.put("percente", String.valueOf(getVendasGerente().getTipoComissao()));
        parametros.put("vlComissao", getVendasGerente().getValorComissao().toString());
        parametros.put("periodo", conterterPeriodo(getMesFiltro(), getAnoFiltro()));
        parametros.put("Usuario", ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario().getIDPessoa().getRazao());
        RelatorioUtil.geraRelatorioBean("lote", jrds, parametros);
    }

    @SuppressWarnings("deprecation")
	public String conterterPeriodo(Integer mes, Integer ano) {
        Date date = new Date();
        String periodo = "";
        if (mes == 0) {
            mes = date.getMonth() + 1;
        }
        if (ano == 2029) {
            ano = date.getYear() + 1900;
        }
        switch (mes) {
            case 1:
                periodo = "Janeiro de " + ano.toString();
                break;
            case 2:
                periodo = "Fevereiro de " + ano.toString();
                break;
            case 3:
                periodo = "Março de " + ano.toString();
                break;
            case 4:
                periodo = "Abril de " + ano.toString();
                break;
            case 5:
                periodo = "Maio de " + ano.toString();
                break;
            case 6:
                periodo = "Junho de " + ano.toString();
                break;
            case 7:
                periodo = "Julho de " + ano.toString();
                break;
            case 8:
                periodo = "Agosto de " + ano.toString();
                break;
            case 9:
                periodo = "Setembro de " + ano.toString();
                break;
            case 10:
                periodo = "Outubro de " + ano.toString();
                break;
            case 11:
                periodo = "Novembro de " + ano.toString();
                break;
            case 12:
                periodo = "Dezembro de " + ano.toString();
                break;

        }
        return periodo;
    }
    
	@SuppressWarnings("unused")
	private void carregaGrafico(){
        this.graficoBarra = new CartesianChartModel();
        
        
    }

    public CapaLoteRN getCapaLoteRN() {
        return capaLoteRN;
    }

    public void setCapaLoteRN(CapaLoteRN capaLoteRN) {
        this.capaLoteRN = capaLoteRN;
    }

    public List<CapaLote> getCapaLotes() {
        return capaLotes;
    }

    public void setCapaLotes(List<CapaLote> capaLotes) {
        this.capaLotes = capaLotes;
    }

    public CapaLote getCapaLote() {
        return capaLote;
    }

    public void setCapaLote(CapaLote capaLote) {
        this.capaLote = capaLote;
    }

    public Date getInicioLote() {
        return inicioLote;
    }

    public void setInicioLote(Date inicioLote) {
        this.inicioLote = inicioLote;
    }

    public Date getFimDeLote() {
        return fimDeLote;
    }

    public void setFimDeLote(Date fimDeLote) {
        this.fimDeLote = fimDeLote;
    }

    public int getCodigoCancel() {
        return codigoCancel;
    }

    public void setCodigoCancel(int codigoCancel) {
        this.codigoCancel = codigoCancel;
    }

    public List<ComissaoVendas> getComissaoVendas() {
        return comissaoVendas;
    }

    public void setComissaoVendas(List<ComissaoVendas> comissaoVendas) {
        this.comissaoVendas = comissaoVendas;
    }

    public FuncaoRN getFuncaoRN() {
        return funcaoRN;
    }

    public void setFuncaoRN(FuncaoRN funcaoRN) {
        this.funcaoRN = funcaoRN;
    }

    public TerceirosRN getTerceirosRN() {
        return terceirosRN;
    }

    public void setTerceirosRN(TerceirosRN terceirosRN) {
        this.terceirosRN = terceirosRN;
    }

    public ComissaoVendas getVendasGerente() {
        return vendasGerente;
    }

    public void setVendasGerente(ComissaoVendas vendasGerente) {
        this.vendasGerente = vendasGerente;
    }

    public int getMesFiltro() {
        return mesFiltro;
    }

    public void setMesFiltro(int mesFiltro) {
        this.mesFiltro = mesFiltro;
    }

    public int getAnoFiltro() {
        return anoFiltro;
    }

    public void setAnoFiltro(int anoFiltro) {
        this.anoFiltro = anoFiltro;
    }

    public CalculoComissaoRN getCalculoComissaoRN() {
        return calculoComissaoRN;
    }

    public void setCalculoComissaoRN(CalculoComissaoRN calculoComissaoRN) {
        this.calculoComissaoRN = calculoComissaoRN;
    }

    public CartesianChartModel getGraficoBarra() {
        return graficoBarra;
    }

    public void setGraficoBarra(CartesianChartModel graficoBarra) {
        this.graficoBarra = graficoBarra;
    }
    
    
}
