/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.MailingDAO;
import com.green.modelo.Funcao;
import com.green.modelo.Funcionario;
import com.green.modelo.Mailing;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leandro.silva
 */
@Service("mailingRN")
public class MailingRN {

    @Autowired
    private MailingDAO mailingDAO;

    public void salvar(Mailing mailing) {
        mailing.setIDusuarioInc(ContextoUtil.getContextoBean().getUsuarioLogado());
        mailing.setDTInc(new Date());
        getMailingDAO().salvar(mailing);

    }

    @Transactional("tmGreen")
    public void atualizar(Mailing mailing) {
        getMailingDAO().atualizar(mailing);
    }

    public List<Mailing> listar() {
        return getMailingDAO().listar();
    }

    public List<Funcionario> listarPorFuncao(Funcao funcao) {
        return getMailingDAO().listaPorFuncao(funcao);
    }

    public List<Mailing> listarPorVendedor() {
        return getMailingDAO().listarPorVendedor(ContextoUtil.getContextoBean().getUsuarioLogado().getIDFuncionario());
    }

    public List<Mailing> listaAtivVend(Funcionario funcionario) {
        if(funcionario==null){
            return getMailingDAO().listarPorVendedor(null);
        }
        if (funcionario.getIDFuncionario() == null) {
            return null;
        } else {
            return getMailingDAO().listarPorVendedor(funcionario);
        }

    }

    public MailingDAO getMailingDAO() {
        return mailingDAO;
    }

    public void setMailingDAO(MailingDAO mailingDAO) {
        this.mailingDAO = mailingDAO;
    }

    public Mailing buscar(Integer id) {
        System.out.println(id.toString());
        return getMailingDAO().buscar(id);
    }
}
