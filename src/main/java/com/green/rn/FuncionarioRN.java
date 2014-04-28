/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.ContatoDAO;
import com.green.dao.EquipevendaDAO;
import com.green.dao.FuncionarioDAO;
import com.green.dao.PessoaDao;
import com.green.modelo.Contato;
import com.green.modelo.Equipevenda;
import com.green.modelo.Funcao;
import com.green.modelo.Funcionario;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import com.green.util.Endereco;
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
@Service("funcionarioRN")
public class FuncionarioRN {

    @Autowired
    private FuncionarioDAO funcionarioDAO;
    @Autowired
    private PessoaDao pessoaDao;
    @Autowired
    private ContatoDAO contatoDAO;
    @Autowired
    private EquipevendaDAO equipevendaDAO;

    public Integer Salvar(Funcionario funcionario, Endereco e, Contato c) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        funcionario.setDTInc(new Date(System.currentTimeMillis()));
        funcionario.setIDUsuario(contextoBean.getUsuarioLogado());
        funcionario.setAtivo(true);
        funcionario.getIDPessoa().setFisicaJuridica(true);
        funcionario.getIDPessoa().setCidade(e.getCidade());
        funcionario.getIDPessoa().setEstado(e.getEstado());
        getPessoaDao().salvar(funcionario.getIDPessoa());
        c.setDTInc(new Date(System.currentTimeMillis()));
        c.setEmail(funcionario.getIDPessoa().getEmail());
        c.setHttp(funcionario.getIDPessoa().getHttp());
        c.setIDPessoa(funcionario.getIDPessoa());
        c.setIDUsuario(contextoBean.getUsuarioLogado());
        getContatoDAO().salvar(c);
        funcionario.setIDPessoa(funcionario.getIDPessoa());
        getFuncionarioDAO().salvar(funcionario);
        if (funcionario.getIDFuncao().getIDFuncao() == 5) {
            Equipevenda equipevenda = new Equipevenda();
            equipevenda.setIDPromotor(funcionario);
            equipevenda.setiDUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
            equipevenda.setDTInc(new Date());
            this.equipevendaDAO.salvar(equipevenda);
        }
        return funcionario.getIDFuncionario();
    }

    public Funcionario carregar(Integer codigo) {
        return getFuncionarioDAO().carregar(codigo);
    }

    public void excluir(Funcionario funcionario) {
        getFuncionarioDAO().excluir(funcionario);
    }

    public List<Funcionario> listar() {
        return getFuncionarioDAO().listar();
    }

    public List<Funcionario> listarPorFuncao(Funcao funcao) {
        return getFuncionarioDAO().listarPorFuncao(funcao);
    }

    public List<Funcionario> listarPorFuncaoAtivo(Funcao funcao) {
        return getFuncionarioDAO().listarPorFuncaoAtivo(funcao);
    }

    public List<Funcionario> listarGerente() {
        return getFuncionarioDAO().listarGerente();
    }

    public List<Funcionario> listarGerenteVendedor() {
        return getFuncionarioDAO().listarGerenteVendedor();
    }

    @Transactional(value = "tmGreen")
    public void atualizar(Funcionario funcionario, Contato contato) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_RH") || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADM_ASSINAT")) {
            getPessoaDao().atualizar(funcionario.getIDPessoa());
            getContatoDAO().atualizar(contato);
            Equipevenda ev = this.equipevendaDAO.buscarPorPromotor(funcionario);
            if (ev == null) {
                if (funcionario.getIDFuncao().getIDFuncao() == 5) {
                    Equipevenda equipevenda = new Equipevenda();
                    equipevenda.setiDUsuario(contextoBean.getUsuarioLogado());
                    equipevenda.setDTInc(new Date());
                    equipevenda.setIDPromotor(funcionario);
                    this.equipevendaDAO.salvar(equipevenda);
                }
                getFuncionarioDAO().atualizar(funcionario);
            } else {
                ev.setIDPromotor(funcionario);
                getFuncionarioDAO().atualizar(ev.getIDPromotor());
            }



            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado com sucesso!", "Atualizado com sucesso!"));
            requestContext.update("formFuncionario:painelEditFuncionario");
            requestContext.execute("editarFuncDialog.hide()");
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falha,usuario nao autorizado para esta ação!", "Usuario não autorizado."));
        }

    }

    @Transactional(value = "tmGreen")
    public void demitirFuncionario(Funcionario funcionario) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        RequestContext rc = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_RH") || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADM_ASSINAT")) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Demissão efetuada com sucesso!", "Demissão efetuada com sucesso!"));
            funcionario.setAtivo(!funcionario.getAtivo());
            getFuncionarioDAO().atualizar(funcionario);
            rc.update("formFuncionario:painelEditFuncionario");
            rc.execute("demitirFuncDialog.hide()");

        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falha,usuario nao autorizado para esta ação!", "Usuario não autorizado."));
            rc.update("formFuncionario:painelEditFuncionario");
            rc.execute("demitirFuncDialog.hide()");
        }
    }

    public List<Funcionario> listarPorEquipe(Funcionario funcionario) {
        return getFuncionarioDAO().listarPorEquipe(funcionario);
    }

    @Transactional(value = "tmGreen")
    public void salvarFuncionarioEquipe(Funcionario promotor) {
        getFuncionarioDAO().atualizar(promotor);
    }

    public EquipevendaDAO getEquipevendaDAO() {
        return equipevendaDAO;
    }

    public void setEquipevendaDAO(EquipevendaDAO equipevendaDAO) {
        this.equipevendaDAO = equipevendaDAO;
    }

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

    public FuncionarioDAO getFuncionarioDAO() {
        return funcionarioDAO;
    }

    public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }
}
