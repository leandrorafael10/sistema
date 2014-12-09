/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.converter;

import com.green.modelo.Tipoconta;
import com.green.rn.TipoContaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("tipoContaConverter")
public class TipoContaConverter implements Converter {

    private TipoContaRN tipoContaRN;

    public TipoContaConverter() {
        this.tipoContaRN = (TipoContaRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "tipoContaRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return tipoContaRN.carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            if (((Tipoconta) value).getIdtipoconta() != null) {
                return ((Tipoconta) value).getIdtipoconta().toString();
            }
        }
        return null;

    }

}
