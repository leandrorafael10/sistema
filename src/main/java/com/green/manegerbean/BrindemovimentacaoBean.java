/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Brinde;
import com.green.modelo.BrindeTermo;
import com.green.modelo.Brindemovimentacao;
import com.green.modelo.TermoResponsabilidade;
import com.green.rn.BrindeRN;
import com.green.rn.BrindemovimentacaoRN;
import com.green.util.ContextoUtil;
import com.green.util.RelatorioUtil;
import java.io.Serializable;
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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "brindemovimentacaoBean")
@ViewScoped
public class BrindemovimentacaoBean implements Serializable {

    @ManagedProperty("#{brindemovimentacaoRN}")
    private BrindemovimentacaoRN brindemovimentacaoRN;
    @ManagedProperty("#{brindeRN}")
    private BrindeRN brindeRN;
    private Brindemovimentacao brindemovimentacao;
    private TermoResponsabilidade termoResponsabilidade;
    private TermoResponsabilidade termoRetorno;
    private List<BrindeTermo> brindeTermos;
    private List<BrindeTermo> brindeSoma;

    @PostConstruct
    private void init() {
        this.brindemovimentacao = new Brindemovimentacao();
        this.termoResponsabilidade = new TermoResponsabilidade();
    }

    public void novoBrindeTermo() {
        this.brindeTermos = new ArrayList<>();
        for (Brinde brinde : getBrindeRN().listar()) {
            this.brindeTermos.add(new BrindeTermo(null, brinde, 0));
        }
    }

    public void salvar(ActionEvent event) {
        getTermoResponsabilidade().setEntradaSaida(false);
        getBrindemovimentacao().setDTInc(new Date());
        getBrindemovimentacao().setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
        getTermoResponsabilidade().setIDBrindemovimentacao(getBrindemovimentacao());
        getBrindemovimentacao().getTermoResponsabilidadeList().add(getTermoResponsabilidade());
        getBrindemovimentacaoRN().salvar(getBrindemovimentacao(), getBrindeTermos());
    }

    public void salvarRetorno(ActionEvent event) {
        getTermoRetorno().setEntradaSaida(true);
        getBrindemovimentacaoRN().salvarRetornoExtra(getTermoRetorno());
        RequestContext.getCurrentInstance().execute("PF('dialogRetorno').hide();");
        RequestContext.getCurrentInstance().update("formNovoTermo");

    }

    public void salvarExtra(ActionEvent event) {
        getTermoRetorno().setEntradaSaida(false);
        getBrindemovimentacaoRN().salvarRetornoExtra(getTermoRetorno());
        RequestContext.getCurrentInstance().execute("PF('dialogExtra').hide();");
        RequestContext.getCurrentInstance().update("formNovoTermo");

    }

    public void somaBrindeMovimentacao(Brindemovimentacao movimentacao) {
        this.brindeSoma = new ArrayList<>();
        setBrindeSoma(getBrindemovimentacaoRN().somaBrindeMovimentacao(movimentacao));
    }

    public void retornoBrinde(Brindemovimentacao b) {
        novoBrindeTermo();
        this.termoRetorno = new TermoResponsabilidade();
        this.termoRetorno.setIDBrindemovimentacao(new Brindemovimentacao());
        this.termoRetorno.setIDBrindemovimentacao(b);
        this.termoRetorno.setBrindeTermoList(brindeTermos);
    }

    public void retiradaExtra(Brindemovimentacao b) {
        novoBrindeTermo();
        this.termoRetorno = new TermoResponsabilidade();
        this.termoRetorno.setIDBrindemovimentacao(new Brindemovimentacao());
        this.termoRetorno.setIDBrindemovimentacao(b);
        this.termoRetorno.setBrindeTermoList(brindeTermos);
    }

    public void geraPdfTermo() {
        HashMap<String, Object> parametros = new HashMap<>();
        String texto_termo = "Eu , " + getTermoResponsabilidade().getIDFuncionario().getIDPessoa().getRazao() + " ,estou ciente da quantidade de brindes relacionados abaixo, e sei também que tenho total responsabilidade sobre o mesmo.";
        String tipo_termo;
        if (getTermoResponsabilidade().getEntradaSaida()) {
            tipo_termo = "Devolução de Brindes";
        } else {
            tipo_termo = "Retirada de Brindes";
        }
        parametros.put("texto_termo", texto_termo);
        parametros.put("tipo_termo", tipo_termo);
        parametros.put("data_termo", getTermoResponsabilidade().getDataTermo());
        parametros.put("listaBrinde", getTermoResponsabilidade().getBrindeTermoList());
        JRDataSource jrds = new JRBeanCollectionDataSource(getTermoResponsabilidade().getBrindeTermoList());
        RelatorioUtil.geraRelatorioBean("termo_responsabilidade",jrds,parametros);
    }

    public void excluir(ActionEvent event) {
        getBrindemovimentacaoRN().excluir(getBrindemovimentacao());
    }

    public void atualizar(ActionEvent event) {
        getBrindemovimentacaoRN().atualizar(getBrindemovimentacao());
    }

    public void addTermo(ActionEvent event) {
        novoBrindeTermo();
        this.brindemovimentacao.setTermoResponsabilidadeList(new ArrayList<TermoResponsabilidade>());
        this.termoResponsabilidade.setBrindeTermoList(brindeTermos);
    }

    public BrindemovimentacaoRN getBrindemovimentacaoRN() {
        return brindemovimentacaoRN;
    }

    public void setBrindemovimentacaoRN(BrindemovimentacaoRN brindemovimentacaoRN) {
        this.brindemovimentacaoRN = brindemovimentacaoRN;
    }

    public Brindemovimentacao getBrindemovimentacao() {
        return brindemovimentacao;
    }

    public void setBrindemovimentacao(Brindemovimentacao brindemovimentacao) {
        this.brindemovimentacao = brindemovimentacao;
    }

    public TermoResponsabilidade getTermoResponsabilidade() {
        return termoResponsabilidade;
    }

    public void setTermoResponsabilidade(TermoResponsabilidade termoResponsabilidade) {
        this.termoResponsabilidade = termoResponsabilidade;
    }

    public BrindeRN getBrindeRN() {
        return brindeRN;
    }

    public void setBrindeRN(BrindeRN brindeRN) {
        this.brindeRN = brindeRN;
    }

    public List<BrindeTermo> getBrindeTermos() {
        return brindeTermos;
    }

    public void setBrindeTermos(List<BrindeTermo> brindeTermos) {
        this.brindeTermos = brindeTermos;
    }

    public TermoResponsabilidade getTermoRetorno() {
        return termoRetorno;
    }

    public void setTermoRetorno(TermoResponsabilidade termoRetorno) {
        this.termoRetorno = termoRetorno;
    }

    public List<BrindeTermo> getBrindeSoma() {
        return brindeSoma;
    }

    public void setBrindeSoma(List<BrindeTermo> brindeSoma) {
        this.brindeSoma = brindeSoma;
    }

}
