/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greem.converter;

import com.green.modelo.Pontodevenda;
import com.green.rn.PontodevendaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("pontodevendaConverter")
public class PontodevendaConverter implements Converter{
    
    private PontodevendaRN pontodevendaRN;

    public PontodevendaConverter() {
        this.pontodevendaRN = (PontodevendaRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "pontodevendaRN");
    }
    
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if (value != null && !value.equals("")) {
            return this.pontodevendaRN.carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            if (((Pontodevenda) value).getIDPontodeVenda() != null) {
                return ((Pontodevenda) value).getIDPontodeVenda().toString();
            }
        }
        return null;
    }
    
}
