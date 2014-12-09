/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.green.modelo.Cliente;
import com.green.rn.ClienteRN;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
    @ManagedProperty("#{clienteRN}")
    private ClienteRN clienteRN;
    private List<Cliente> clientes;
    
    public void listando(){
        setClientes(getClienteRN().listar());  
    }
    
    
    
    @PostConstruct
    private void init(){
      setClientes(getClienteRN().listar());  
    }
    
    public List<Cliente> completeCliente(String query) {
		List<Cliente> clientes = getClienteRN().listar();
		List<Cliente> clienteFiltro = new ArrayList<Cliente>();

		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);
			if (cliente.getIDPessoa().getRazao().toLowerCase()
					.startsWith(query.toLowerCase())) {
				clienteFiltro.add(cliente);
			}
		}

		return clienteFiltro;
	}

    public ClienteRN getClienteRN() {
        return clienteRN;
    }

    public void setClienteRN(ClienteRN clienteRN) {
        this.clienteRN = clienteRN;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
}
