/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import com.green.manegerbean.UsuarioBean;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leandro.silva
 */
@SuppressWarnings("serial")
public class AutorizarUsuario implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext contexto = event.getFacesContext();// Obtém o contexto atual          
        String paginaAtual = contexto.getViewRoot().getViewId();// Obtém a página que atualmente está interagindo com o ciclo       

        boolean isLoginPage = paginaAtual.lastIndexOf("logteste/login.xhtml") > -1; // Se for a página 'login.jsp' seta a variável como true         
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(true);// Obtém a sessão atual         

        UsuarioBean usuario = (UsuarioBean) sessao.getAttribute("USER_OK");// Restaga o nome do usuário logado  

        if (!isLoginPage && usuario == null) {
            // Redireciona o fluxo para a página de login  
            NavigationHandler nh = contexto.getApplication().getNavigationHandler();
            nh.handleNavigation(contexto, null, "logout");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}
