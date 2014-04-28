/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greem.converter;

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
public class TipoContaConverter implements Converter{
    
    private TipoContaRN tipoContaRN;
    
    public TipoContaConverter() {
        this.tipoContaRN = (TipoContaRN)FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "tipoContaRN");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
             return tipoContaRN.carregar(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
            return ((Tipoconta)value).getIdtipoconta().toString();
    }
    
}
