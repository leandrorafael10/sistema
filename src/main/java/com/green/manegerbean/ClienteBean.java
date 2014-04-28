/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import com.green.modelo.Cliente;
import com.green.rn.ClienteRN;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

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
