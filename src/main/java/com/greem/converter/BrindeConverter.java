/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greem.converter;

import com.green.modelo.Brinde;
import com.green.rn.BrindeRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro.silva
 */
@FacesConverter("brindeConverter")
public class BrindeConverter implements Converter{

    private BrindeRN brindeRN;

    public BrindeConverter() {
        this.brindeRN =(BrindeRN) FacesContext.getCurrentInstance().getELContext().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext() , null, "brindeRN");
    }
    
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            return this.brindeRN.carregar(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            return ((Brinde)value).getIDBrinde().toString();
        }
        return null;
    }
    
}
