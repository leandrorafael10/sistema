/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.ContatoDAO;
import com.green.dao.FornecedorDAO;
import com.green.dao.PessoaDao;
import com.green.modelo.Contato;
import com.green.modelo.Fornecedor;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("fornecedorRN")
public class FornecedorRN {

    @Autowired
    private FornecedorDAO fornecedorDAO;
    @Autowired
    private PessoaDao pessoaDao;
    @Autowired
    private ContatoDAO contatoDAO;

    public boolean salvar(Fornecedor fornecedor, Contato contato) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_2")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_PRODUCAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_3")) {
            fornecedor.setDTInc(new Date());
            fornecedor.setIDUsuario(contextoBean.getUsuarioLogado());
            fornecedor.setAtivo(true);
            fornecedor.getIDPessoa().setSituacao(true);
            getPessoaDao().salvar(fornecedor.getIDPessoa());
            contato.setIDPessoa(fornecedor.getIDPessoa());
            contato.setEmail(fornecedor.getIDPessoa().getEmail());
            contato.setHttp(fornecedor.getIDPessoa().getHttp());
            contato.setDTInc(new Date());
            contato.setIDUsuario(contextoBean.getUsuarioLogado());
            getContatoDAO().salvar(contato);
            getFornecedorDAO().salvar(fornecedor);
            return true;
        }
        return false;

    }

    public List<Fornecedor> listar() {
        return getFornecedorDAO().listar();
    }

    public List<Fornecedor> listarAgencia() {
        return getFornecedorDAO().listarAgencias();
    }

    public void excluir(Fornecedor fornecedor) {
        getFornecedorDAO().excluir(fornecedor);
    }

    public Fornecedor buscar(Integer id) {
        return getFornecedorDAO().buscar(id);
    }

    public boolean alterar(Fornecedor fornecedor, Contato contato) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();

        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_1")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_2")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_PRODUCAO")
                || contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_FINANCEIRO_3")) {
            fornecedor.setIDUsuarioAlt(contextoBean.getUsuarioLogado());
            fornecedor.setDTAlt(new Date());
            if (contato.getIDContato() == null) {
                contato.setDTInc(new Date());
                contato.setIDUsuario(contextoBean.getUsuarioLogado());
                contato.setIDPessoa(fornecedor.getIDPessoa());
                getContatoDAO().salvar(contato);
            } else {
                getContatoDAO().atualizar(contato);
            }
            getPessoaDao().atualizar(fornecedor.getIDPessoa());
            getFornecedorDAO().alterar(fornecedor);

            return true;
        } else {
            return false;
        }
    }

    public FornecedorDAO getFornecedorDAO() {
        return fornecedorDAO;
    }

    public void setFornecedorDAO(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
    }

    public PessoaDao getPessoaDao() {
        return pessoaDao;
    }

    public void setPessoaDao(PessoaDao pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    public ContatoDAO getContatoDAO() {
        return contatoDAO;
    }

    public void setContatoDAO(ContatoDAO contatoDAO) {
        this.contatoDAO = contatoDAO;
    }
}
