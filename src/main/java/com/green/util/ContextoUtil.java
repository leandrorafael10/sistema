/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.util;

import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leandro.silva
 */
public class ContextoUtil implements Serializable {
    
    public static ContextoBean getContextoBean(){
        FacesContext contexto = FacesContext.getCurrentInstance();
        ExternalContext external = contexto.getExternalContext();
        HttpSession session = (HttpSession) external.getSession(true);
        ContextoBean contextoBean = (ContextoBean) session.getAttribute("contextoBean");
        return contextoBean;
    }
    
}
