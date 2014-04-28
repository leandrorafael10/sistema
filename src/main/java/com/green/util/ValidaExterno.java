package com.green.util;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.render.ResponseStateManager;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;


@ManagedBean(name="validaExterno")
@ViewScoped
public class ValidaExterno implements Serializable{
	
	public void direciona(){
		FacesContext facesContext = FacesContext.getCurrentInstance();  
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();  
		  
		                     try {
								response.sendRedirect("/externa/edicao.jsf");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}  
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("direciona();");

	}
	

}
