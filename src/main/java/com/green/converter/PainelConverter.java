package com.green.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.green.dao.PainelDAO;
import com.green.modelo.Painel;

@FacesConverter("painelConverter")
public class PainelConverter implements Converter {

	private PainelDAO painelDAO; 

	public PainelConverter() {
		this.painelDAO = (PainelDAO) FacesContext.getCurrentInstance().getELContext().getELResolver()
				.getValue(FacesContext.getCurrentInstance().getELContext(), null, "painelDAO");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.equals("")) {
			return this.painelDAO.buscar(Integer.parseInt(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !value.equals("")) {
			if (((Painel) value).getIDPainel() != null) {
				return ((Painel) value).getIDPainel().toString();
			}
		}
		return null;
	}

}
