package com.green.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.green.modelo.ContratoMidia;
import com.green.rn.ContratoMidiaRN;
@FacesConverter("contratoMidiaConverter")
public class ContratoMidiaConverter implements Converter {

	private ContratoMidiaRN contratoMidiaRN;
	
	
	
	public ContratoMidiaConverter() {
		 this.contratoMidiaRN = (ContratoMidiaRN) FacesContext.getCurrentInstance().getELContext().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "contratoMidiaRN");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		 if (value != null && !value.equals("")&&!value.equals("todos")) {
	            return this.contratoMidiaRN.carregar(Integer.parseInt(value));
	        }
	        return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && !value.equals("")) {
            if (((ContratoMidia) value).getIdcontratoMidia() != null) {
                return ((ContratoMidia) value).getIdcontratoMidia().toString();
            }
        }
        return null;
		
	}

	public ContratoMidiaRN getContratoMidiaRN() {
		return contratoMidiaRN;
	}

	public void setContratoMidiaRN(ContratoMidiaRN contratoMidiaRN) {
		this.contratoMidiaRN = contratoMidiaRN;
	}
	
	

}
