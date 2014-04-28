/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import com.green.modelo.Usuario;
import com.green.rn.UsuarioRn;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author leandro.silva
 */
@ManagedBean(name="contextoBean")
@SessionScoped
public class ContextoBean implements Serializable{
    
    private Usuario usuarioLogado = null;
    
    @ManagedProperty("#{usuarioRn}")
    private UsuarioRn usuarioRn;

    
    public UsuarioRn getUsuarioRn() {
        return usuarioRn;
    }

    public void setUsuarioRn(UsuarioRn usuarioRn) {
        this.usuarioRn = usuarioRn;
    }

    public Usuario getUsuarioLogado() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        ExternalContext external = contexto.getExternalContext();
        String login = external.getRemoteUser();
        if(this.usuarioLogado ==null || !login.equals(this.usuarioLogado.getLogin())){
            if(login != null){
                this.usuarioLogado = this.getUsuarioRn().carregarPeloLogin(login);
            }
        }
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
   
    
    
}
