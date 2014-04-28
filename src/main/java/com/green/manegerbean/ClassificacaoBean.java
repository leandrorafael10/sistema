/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Classificacao;
import com.green.rn.ClassificacaoRN;
import com.green.util.RelatorioUtil;
import java.io.Serializable;
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "classificacaoBean")
@ViewScoped
public class ClassificacaoBean implements Serializable {

    @ManagedProperty("#{classificacaoRN}")
    private ClassificacaoRN classificacaoRN;
    private Classificacao classificacao = new Classificacao();
    private Classificacao classificacaoNovo = new Classificacao();
    private List<Classificacao> classificacaos;
    private String codigoClassificacao = new String();
    private String filtroCodigo = new String();
    private String filtroDescricao = new String();
    private int tipoRetorno = 1;
    String nomeRelatorioSaida;

    @PostConstruct
    private void init() {
        listando();

    }

    public void listando() {
        setClassificacaos(getClassificacaoRN().listar());
        setFiltroCodigo("");
        setFiltroDescricao("");
    }

    public void salvar(ActionEvent actionEvent) {
        getClassificacaoRN().salvar(getClassificacaoNovo());
        this.classificacaoNovo = new Classificacao();
        setClassificacaos(getClassificacaoRN().listar());
    }

    public void excluir(ActionEvent actionEvent) {
        getClassificacaoRN().excluir(getClassificacao());
        this.classificacao = new Classificacao();
        setClassificacaos(getClassificacaoRN().listar());
    }

    public void atualizar(RowEditEvent editEvent) {
        Classificacao c = (Classificacao) editEvent.getObject();
        getClassificacaoRN().atualizar(c);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Atualizado com sucesso!"));
    }
    
    public void cancelarEdicao(RowEditEvent event){
        FacesMessage msg = new FacesMessage("Plano de contas Edição", ((Classificacao) event.getObject()).getDescricao());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    }
    public void arquivoRetorno(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        Date dateNomeRelatorio = new Date();
        String nomeRelatorioJasper = "classificacao";
        nomeRelatorioSaida = dateNomeRelatorio.getTime()+"plano_conta";
        RelatorioUtil relatorioUtil = new RelatorioUtil();
        HashMap<String, Object> parametrosRelatorio = new HashMap<String, Object>();
        parametrosRelatorio.put("codigo1",getFiltroCodigo()+"%");
        parametrosRelatorio.put("descricao1", getFiltroDescricao()+"%");
        //parametrosRelatorio.put("natureza", getClassificacao().getNatureza());
       // parametrosRelatorio.put("tipo", getClassificacao().getTipo());
        try {
            relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper, nomeRelatorioSaida, getTipoRetorno());
            requestContext.execute("dialogCarregando.hide()");
            setFiltroCodigo("");
            setFiltroDescricao("");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(e.getMessage()));
        }

    }

    public void filtroClassificacao(ActionEvent event) {
        setClassificacaos(getClassificacaoRN().filtroClassificacao(getFiltroCodigo(), getFiltroDescricao()));
    }

    public String getCodigoClassificacao() {
        return codigoClassificacao;
    }

    public void setCodigoClassificacao(String codigoClassificacao) {
        this.codigoClassificacao = codigoClassificacao;
    }

    public int getTipoRetorno() {
        return tipoRetorno;
    }

    public void setTipoRetorno(int tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

    public ClassificacaoRN getClassificacaoRN() {
        return classificacaoRN;
    }

    public void setClassificacaoRN(ClassificacaoRN classificacaoRN) {
        this.classificacaoRN = classificacaoRN;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public Classificacao getClassificacaoNovo() {
        return classificacaoNovo;
    }

    public void setClassificacaoNovo(Classificacao classificacaoNovo) {
        this.classificacaoNovo = classificacaoNovo;
    }

    public List<Classificacao> getClassificacaos() {
        //classificacaos = getClassificacaoRN().listar();
        return classificacaos;
    }

    public void setClassificacaos(List<Classificacao> classificacaos) {
        this.classificacaos = classificacaos;
    }

    public String getFiltroCodigo() {
        return filtroCodigo;
    }

    public void setFiltroCodigo(String filtroCodigo) {
        this.filtroCodigo = filtroCodigo;
    }

    public String getFiltroDescricao() {
        return filtroDescricao;
    }

    public void setFiltroDescricao(String filtroDescricao) {
        this.filtroDescricao = filtroDescricao;
    }

    public String getNomeRelatorioSaida() {
        return nomeRelatorioSaida;
    }

    public void setNomeRelatorioSaida(String nomeRelatorioSaida) {
        this.nomeRelatorioSaida = nomeRelatorioSaida;
    }
    
}
