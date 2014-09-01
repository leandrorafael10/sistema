/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.AtividadeDAO;
import com.green.dao.CCustoDAO;
import com.green.dao.ClassificacaoDAO;
import com.green.dao.ContaDAO;
import com.green.dao.ContratoMidiaDAO;
import com.green.dao.ContratoParceiroDAO;
import com.green.dao.DocumentoDAO;
import com.green.dao.OrigemDAO;
import com.green.dao.ProducaoMidiaDAO;
import com.green.dao.ReceitaDAO;
import com.green.modelo.Cliente;
import com.green.modelo.ContratoParceiro;
import com.green.modelo.Origem;
import com.green.modelo.ProducaoMidia;
import com.green.modelo.Receita;
import com.green.util.ContextoUtil;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service("contratoParceiroRN")
public class ContratoParceiroRN {

    @Autowired
    private ContratoParceiroDAO contratoParceiroDAO;
    @Autowired
    private ContratoMidiaDAO contratoMidiaDAO;
    @Autowired
    private AtividadeDAO atividadeDAO;
    @Autowired
    private CCustoDAO cCustoDAO;
    @Autowired
    private ClassificacaoDAO classificacaoDAO;
    @Autowired
    private DocumentoDAO documentoDAO;
    @Autowired
    private ContaDAO contaDAO;
    @Autowired
    private OrigemDAO origemDAO;
    @Autowired
    private ProducaoMidiaDAO producaoMidiaDAO;
    @Autowired
    private ReceitaDAO receitaDAO;
    

    @Transactional("tmGreen")
    public void salvar(ContratoParceiro parceiro,BigDecimal valor) {
        Receita receitaContrato = new Receita();
        try {
            parceiro.getIDContratoMidia().setDTinc(new Date());
            parceiro.getIDContratoMidia().setIDusuario(ContextoUtil.getContextoBean().getUsuarioLogado());
            parceiro.getIDContratoMidia().setAtivo(1);
            if (parceiro.getIDContratoMidia().getIDtipopagamento().getDescricao().equals("Bonificacão") || parceiro.getIDContratoMidia().getIDtipopagamento().getDescricao().equals("Permuta")) {
                if (parceiro.getIDContratoMidia().getIDtipopagamento().getDescricao().equals("Bonificacão")) {
                    parceiro.getIDContratoMidia().setValor(BigDecimal.ZERO);
                }
                getContratoMidiaDAO().salvarContrato(parceiro.getIDContratoMidia());
                getContratoParceiroDAO().salvar(parceiro);
                ProducaoMidia midia = new ProducaoMidia();
                midia.setIDContratoMidia(parceiro.getIDContratoMidia());
                
            } else {

                receitaContrato.setValorNominal(valor.divide(new BigDecimal("2")));
                parceiro.getIDContratoMidia().setValor(parceiro.getIDContratoMidia().getValor().multiply(new BigDecimal(parceiro.getIDContratoMidia().getNumeroParcelas())));
                getContratoMidiaDAO().salvarContrato(parceiro.getIDContratoMidia());
                getContratoParceiroDAO().salvar(parceiro);
                Origem origem = new Origem();
                origem.setIDContratoMidia(parceiro.getIDContratoMidia());
                this.origemDAO.salvar(origem);
                receitaContrato.setIDAtividade(this.atividadeDAO.carregar(1));
                receitaContrato.setIDDocumento(this.documentoDAO.carregar(19));
                receitaContrato.setIDCCusto(this.cCustoDAO.carregar(1));
                receitaContrato.setIDClassificacao(this.classificacaoDAO.carregar(2));
                receitaContrato.setIDCliente(parceiro.getIDContratoMidia().getIDcliente());
                receitaContrato.setIDConta(this.contaDAO.carregar(1));
                receitaContrato.setTipoJuros(Boolean.FALSE);
                receitaContrato.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
                receitaContrato.setIdorigem(origem);
                receitaContrato.setDTInc(new Date());
                receitaContrato.setDTEmissao(new Date());
                receitaContrato.setNumero(parceiro.getIDContratoMidia().getNContrato()); //verificar
                receitaContrato.setValorDesconto(BigDecimal.ZERO);
                receitaContrato.setValorJuros(BigDecimal.ZERO);
                receitaContrato.setValorMulta(BigDecimal.ZERO);
                salvarRecContr(receitaContrato,parceiro.getIDContratoMidia().getDataInicioContrato(), Integer.parseInt(parceiro.getIDContratoMidia().getNumeroParcelas()), parceiro.getIDContratoMidia().getNContrato());
                ProducaoMidia midia = new ProducaoMidia();
                midia.setIDContratoMidia(parceiro.getIDContratoMidia());
            }
            ProducaoMidia midia = new ProducaoMidia();
            midia.setIDContratoMidia(parceiro.getIDContratoMidia());
            midia.setStatusMaterial(2);
            midia.setStatusProducao(3);
            this.producaoMidiaDAO.salvar(midia);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", "Salvo com sucesso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", "Erro ao salvar!"));
        }

    }
    
    public List<ContratoParceiro> buscaPorPeriodoTipo_parceiro(Date inicio,Date fim,int tipo,Cliente c){
       return getContratoParceiroDAO().buscaPorPeriodoTipo_parceiro(inicio, fim, tipo, c);
    }

    public void excluir(ContratoParceiro cp) {
        getContratoParceiroDAO().excluir(cp);
    }

    public void atualizar(ContratoParceiro cp) {
        getContratoParceiroDAO().atualizar(cp);
    }

    public List<ContratoParceiro> listar() {
        return getContratoParceiroDAO().listar();
    }
    
    public void salvarRecContr(Receita r, Date inicio, int numParc, String numero) {
        Calendar nova = new GregorianCalendar();
        nova.setTime(inicio);
        nova.set(GregorianCalendar.DAY_OF_MONTH, 10);
        r.setValorLiquido(r.getValorNominal());
        for (int i = 1; i <= numParc; i++) {
            nova.add(GregorianCalendar.MONTH, 1);
            r.setDTVencimento(nova.getTime());
            r.setNumero(String.valueOf(1));
            this.receitaDAO.salvar(r);
            
        }

    }

    public ContratoParceiroDAO getContratoParceiroDAO() {
        return contratoParceiroDAO;
    }

    public void setContratoParceiroDAO(ContratoParceiroDAO contratoParceiroDAO) {
        this.contratoParceiroDAO = contratoParceiroDAO;
    }

    public ContratoMidiaDAO getContratoMidiaDAO() {
        return contratoMidiaDAO;
    }

    public void setContratoMidiaDAO(ContratoMidiaDAO contratoMidiaDAO) {
        this.contratoMidiaDAO = contratoMidiaDAO;
    }

    public AtividadeDAO getAtividadeDAO() {
        return atividadeDAO;
    }

    public void setAtividadeDAO(AtividadeDAO atividadeDAO) {
        this.atividadeDAO = atividadeDAO;
    }

    public CCustoDAO getcCustoDAO() {
        return cCustoDAO;
    }

    public void setcCustoDAO(CCustoDAO cCustoDAO) {
        this.cCustoDAO = cCustoDAO;
    }

    public ClassificacaoDAO getClassificacaoDAO() {
        return classificacaoDAO;
    }

    public void setClassificacaoDAO(ClassificacaoDAO classificacaoDAO) {
        this.classificacaoDAO = classificacaoDAO;
    }

    public DocumentoDAO getDocumentoDAO() {
        return documentoDAO;
    }

    public void setDocumentoDAO(DocumentoDAO documentoDAO) {
        this.documentoDAO = documentoDAO;
    }

    public ContaDAO getContaDAO() {
        return contaDAO;
    }

    public void setContaDAO(ContaDAO contaDAO) {
        this.contaDAO = contaDAO;
    }

    public OrigemDAO getOrigemDAO() {
        return origemDAO;
    }

    public void setOrigemDAO(OrigemDAO origemDAO) {
        this.origemDAO = origemDAO;
    }

    public ProducaoMidiaDAO getProducaoMidiaDAO() {
        return producaoMidiaDAO;
    }

    public void setProducaoMidiaDAO(ProducaoMidiaDAO producaoMidiaDAO) {
        this.producaoMidiaDAO = producaoMidiaDAO;
    }

    public ReceitaDAO getReceitaDAO() {
        return receitaDAO;
    }

    public void setReceitaDAO(ReceitaDAO receitaDAO) {
        this.receitaDAO = receitaDAO;
    }

    
}
