/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.ClienteDAO;
import com.green.modelo.Cliente;
import com.green.modelo.Pessoa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leandro.silva
 */
@Service("clienteRN")
public class ClienteRN {

    @Autowired
    private ClienteDAO clienteDAO;

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public Cliente clienteBuscaPorPessoa(Pessoa p){
        return getClienteDAO().buscaPorPessoa(p);
    }
    public Integer salvar(Cliente cliente) {
        return getClienteDAO().salvar(cliente);
    }

    public Cliente carregar(Integer pk) {
        return this.clienteDAO.carregar(pk);
    }
    public Cliente carregarNome(String nome){
        return getClienteDAO().carregar2(nome);
    }

    public List<Cliente> listar() {
        return getClienteDAO().listar();
    }

    public List<Cliente> listarParceiros() {
        return getClienteDAO().listarParceiro();
    }
}
