/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.green.modelo.ProdutoMidia;
import com.green.rn.ProdutoMidiaRN;

/**
 * 
 * @author leandro.silva
 */
@FacesConverter("produtoMidiaConverter")
public class ProdutoMidiaConverter implements Converter {

	ProdutoMidiaRN produtoMidiaRN;

	public ProdutoMidiaConverter() {
		this.produtoMidiaRN = (ProdutoMidiaRN) FacesContext
				.getCurrentInstance()
				.getELContext()
				.getELResolver()
				.getValue(FacesContext.getCurrentInstance().getELContext(),
						null, "produtoMidiaRN");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		 if (value != null && !value.equals("")) {
			 return this.produtoMidiaRN.carregar(Integer.parseInt(value));
		 }
		 return null;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && !value.equals("")) {
			if (((ProdutoMidia) value).getIdprodutoMidia() != null) {
				return ((ProdutoMidia) value).getIdprodutoMidia().toString();
			}

		}
		return null;
	}

}
