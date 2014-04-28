/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.CCustoDAO;
import com.green.dao.ClassificacaoDAO;
import com.green.dao.ContaDAO;
import com.green.dao.ContatoDAO;
import com.green.dao.CreditoDAO;
import com.green.dao.DebitoDAO;
import com.green.dao.DocumentoDAO;
import com.green.dao.PessoaDao;
import com.green.modelo.Conta;
import com.green.modelo.Contato;
import com.green.modelo.Credito;
import com.green.modelo.Debito;
import com.green.modelo.Pessoa;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
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
@Service("contaRN")
public class ContaRN {

    @Autowired
    private ContaDAO contaDAO;
    @Autowired
    private PessoaDao pessoaDao;
    @Autowired
    private ContatoDAO contatoDAO;
    @Autowired
    private CreditoDAO creditoDAO;
    @Autowired
    private DebitoDAO debitoDAO;
    @Autowired
    private DocumentoDAO  documentoDAO;
    @Autowired
    private ClassificacaoDAO classificacaoDAO;
    @Autowired
    private CCustoDAO ccustoDAO;
    

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

    public ContaDAO getContaDAO() {
        return contaDAO;
    }

    public void setContaDAO(ContaDAO contaDAO) {
        this.contaDAO = contaDAO;
    }

    public List<Conta> listar() {
        return getContaDAO().listar();
    }

    public Conta carregar(Integer codigo) {
        return getContaDAO().carregar(codigo);
    }
    

    public void excluir(Conta conta) {
        getContaDAO().excluir(conta);
    }

    public void atualizar(Conta conta, Conta editada) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        editada.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
        editada.setDTAlt(new Date(System.currentTimeMillis()));
        getContaDAO().atializar(editada);
    }

    public void salvar(Conta conta, Pessoa pessoa, Contato contato) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        Conta c = getContaDAO().carregarNumeroConta(conta);
        if (c == null) {
            if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                    || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")) {
                pessoa.setCnpjCpf("0");
                pessoa.setFisicaJuridica(Boolean.TRUE);
                pessoa.setSituacao(Boolean.TRUE);
                getPessoaDao().salvar(pessoa);
                contato.setDTInc(new Date(System.currentTimeMillis()));
                contato.setIDUsuario(contextoBean.getUsuarioLogado());
                contato.setIDPessoa(pessoa);
                getContatoDAO().salvar(contato);
                conta.setIDPessoa(pessoa);
                conta.setIDUsuario(contextoBean.getUsuarioLogado());
                conta.setDTInc(new Date(System.currentTimeMillis()));
                getContaDAO().salvar(conta);
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK! Salvo com sucesso!", "Ok! Salvo com sucesso!"));
            } else {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha! Usuario não autorisado", "Falha! Usuario não autorisado"));
            }

        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha! Conta já cadastrada!", "Falha! Conta já cadastrada!"));
        }
    }

    @Transactional("tmGreen")
    public void transferencia(Credito credito, Debito debito) {
        debito.setIDTpDocumento(this.documentoDAO.carregar(17));
        debito.setIDCCusto(this.ccustoDAO.carregar(15));
        debito.setIDClassificacao(this.classificacaoDAO.carregar(137));
        debito.setDTInc(new Date());
        debito.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
        credito.setIDCCusto(debito.getIDCCusto());
        credito.setDTBaixa(debito.getDTBaixa());
        credito.setIDCCusto(this.ccustoDAO.carregar(14));
        credito.setIDClassificacao(debito.getIDClassificacao());
        credito.setIDTpDocumento(debito.getIDTpDocumento());
        credito.setIDAtividade(debito.getIDAtividade());
        credito.setValor(debito.getValor());
        credito.setNumero("Empr:"+debito.getIDConta().getTitular()+" / Cc:"+debito.getIDConta().getNumero()+"-"+debito.getIDConta().getNumeroDigito());
        credito.setDTInc(new Date());
        credito.setIDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
        debito.setNumero(credito.getNumero());
        getCreditoDAO().salvar(credito);
        getDebitoDAO().salvar(debito);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Trasferencia efetuada com sucesso!", "Trasferencia efetuada com sucesso!"));
        
    }

    public CreditoDAO getCreditoDAO() {
        return creditoDAO;
    }

    public void setCreditoDAO(CreditoDAO creditoDAO) {
        this.creditoDAO = creditoDAO;
    }

    public DebitoDAO getDebitoDAO() {
        return debitoDAO;
    }

    public void setDebitoDAO(DebitoDAO debitoDAO) {
        this.debitoDAO = debitoDAO;
    }
    
    
}
